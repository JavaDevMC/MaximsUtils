package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.lang.ref.WeakReference;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class ServiceManager implements ServiceManagerBridge {
  private static final Logger logger = Logger.getLogger(ServiceManager.class.getName());
  
  private static final ListenerCallQueue.Event<Listener> HEALTHY_EVENT = new ListenerCallQueue.Event<Listener>() {
      public void call(Listener listener) {
        listener.healthy();
      }
      
      public String toString() {
        return "healthy()";
      }
    };
  
  private static final ListenerCallQueue.Event<Listener> STOPPED_EVENT = new ListenerCallQueue.Event<Listener>() {
      public void call(Listener listener) {
        listener.stopped();
      }
      
      public String toString() {
        return "stopped()";
      }
    };
  
  private final ServiceManagerState state;
  
  private final ImmutableList<Service> services;
  
  public static abstract class Listener {
    public void healthy() {}
    
    public void stopped() {}
    
    public void failure(Service service) {}
  }
  
  public ServiceManager(Iterable<? extends Service> services) {
    ImmutableList<Service> copy = ImmutableList.copyOf(services);
    if (copy.isEmpty()) {
      logger.log(Level.WARNING, "ServiceManager configured with no services.  Is your application configured properly?", new EmptyServiceManagerWarning());
      copy = ImmutableList.of(new NoOpService());
    } 
    this.state = new ServiceManagerState((ImmutableCollection<Service>)copy);
    this.services = copy;
    WeakReference<ServiceManagerState> stateReference = new WeakReference<>(this.state);
    for (UnmodifiableIterator<Service> unmodifiableIterator = copy.iterator(); unmodifiableIterator.hasNext(); ) {
      Service service = unmodifiableIterator.next();
      service.addListener(new ServiceListener(service, stateReference), MoreExecutors.directExecutor());
      Preconditions.checkArgument((service.state() == Service.State.NEW), "Can only manage NEW services, %s", service);
    } 
    this.state.markReady();
  }
  
  public void addListener(Listener listener, Executor executor) {
    this.state.addListener(listener, executor);
  }
  
  @CanIgnoreReturnValue
  public ServiceManager startAsync() {
    UnmodifiableIterator<Service> unmodifiableIterator;
    for (unmodifiableIterator = this.services.iterator(); unmodifiableIterator.hasNext(); ) {
      Service service = unmodifiableIterator.next();
      Service.State state = service.state();
      Preconditions.checkState((state == Service.State.NEW), "Service %s is %s, cannot start it.", service, state);
    } 
    for (unmodifiableIterator = this.services.iterator(); unmodifiableIterator.hasNext(); ) {
      Service service = unmodifiableIterator.next();
      try {
        this.state.tryStartTiming(service);
        service.startAsync();
      } catch (IllegalStateException e) {
        String str = String.valueOf(service);
        logger.log(Level.WARNING, (new StringBuilder(24 + String.valueOf(str).length())).append("Unable to start Service ").append(str).toString(), e);
      } 
    } 
    return this;
  }
  
  public void awaitHealthy() {
    this.state.awaitHealthy();
  }
  
  public void awaitHealthy(Duration timeout) throws TimeoutException {
    awaitHealthy(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
  }
  
  public void awaitHealthy(long timeout, TimeUnit unit) throws TimeoutException {
    this.state.awaitHealthy(timeout, unit);
  }
  
  @CanIgnoreReturnValue
  public ServiceManager stopAsync() {
    for (UnmodifiableIterator<Service> unmodifiableIterator = this.services.iterator(); unmodifiableIterator.hasNext(); ) {
      Service service = unmodifiableIterator.next();
      service.stopAsync();
    } 
    return this;
  }
  
  public void awaitStopped() {
    this.state.awaitStopped();
  }
  
  public void awaitStopped(Duration timeout) throws TimeoutException {
    awaitStopped(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
  }
  
  public void awaitStopped(long timeout, TimeUnit unit) throws TimeoutException {
    this.state.awaitStopped(timeout, unit);
  }
  
  public boolean isHealthy() {
    for (UnmodifiableIterator<Service> unmodifiableIterator = this.services.iterator(); unmodifiableIterator.hasNext(); ) {
      Service service = unmodifiableIterator.next();
      if (!service.isRunning())
        return false; 
    } 
    return true;
  }
  
  public ImmutableSetMultimap<Service.State, Service> servicesByState() {
    return this.state.servicesByState();
  }
  
  public ImmutableMap<Service, Long> startupTimes() {
    return this.state.startupTimes();
  }
  
  public ImmutableMap<Service, Duration> startupDurations() {
    return ImmutableMap.copyOf(
        Maps.transformValues((Map)startupTimes(), Duration::ofMillis));
  }
  
  public String toString() {
    return MoreObjects.toStringHelper(ServiceManager.class)
      .add("services", Collections2.filter((Collection)this.services, Predicates.not(Predicates.instanceOf(NoOpService.class))))
      .toString();
  }
  
  private static final class ServiceManagerState {
    final Monitor monitor = new Monitor();
    
    @GuardedBy("monitor")
    final SetMultimap<Service.State, Service> servicesByState = MultimapBuilder.enumKeys(Service.State.class).linkedHashSetValues().build();
    
    @GuardedBy("monitor")
    final Multiset<Service.State> states = this.servicesByState
      .keys();
    
    @GuardedBy("monitor")
    final Map<Service, Stopwatch> startupTimers = Maps.newIdentityHashMap();
    
    @GuardedBy("monitor")
    boolean ready;
    
    @GuardedBy("monitor")
    boolean transitioned;
    
    final int numberOfServices;
    
    final Monitor.Guard awaitHealthGuard = new AwaitHealthGuard();
    
    final class AwaitHealthGuard extends Monitor.Guard {
      @GuardedBy("ServiceManagerState.this.monitor")
      public boolean isSatisfied() {
        return (ServiceManagerState.this.states.count(Service.State.RUNNING) == ServiceManagerState.this.numberOfServices || ServiceManagerState.this.states
          .contains(Service.State.STOPPING) || ServiceManagerState.this.states
          .contains(Service.State.TERMINATED) || ServiceManagerState.this.states
          .contains(Service.State.FAILED));
      }
    }
    
    final Monitor.Guard stoppedGuard = new StoppedGuard();
    
    final class StoppedGuard extends Monitor.Guard {
      @GuardedBy("ServiceManagerState.this.monitor")
      public boolean isSatisfied() {
        return (ServiceManagerState.this.states.count(Service.State.TERMINATED) + ServiceManagerState.this.states.count(Service.State.FAILED) == ServiceManagerState.this.numberOfServices);
      }
    }
    
    final ListenerCallQueue<Listener> listeners = new ListenerCallQueue<>();
    
    ServiceManagerState(ImmutableCollection<Service> services) {
      this.numberOfServices = services.size();
      this.servicesByState.putAll(Service.State.NEW, (Iterable)services);
    }
    
    void tryStartTiming(Service service) {
      this.monitor.enter();
      try {
        Stopwatch stopwatch = this.startupTimers.get(service);
        if (stopwatch == null)
          this.startupTimers.put(service, Stopwatch.createStarted()); 
      } finally {
        this.monitor.leave();
      } 
    }
    
    void markReady() {
      this.monitor.enter();
      try {
        if (!this.transitioned) {
          this.ready = true;
        } else {
          List<Service> servicesInBadStates = Lists.newArrayList();
          for (UnmodifiableIterator<Service> unmodifiableIterator = servicesByState().values().iterator(); unmodifiableIterator.hasNext(); ) {
            Service service = unmodifiableIterator.next();
            if (service.state() != Service.State.NEW)
              servicesInBadStates.add(service); 
          } 
          String str = String.valueOf(servicesInBadStates);
          throw new IllegalArgumentException((new StringBuilder(89 + String.valueOf(str).length())).append("Services started transitioning asynchronously before the ServiceManager was constructed: ").append(str).toString());
        } 
      } finally {
        this.monitor.leave();
      } 
    }
    
    void addListener(Listener listener, Executor executor) {
      this.listeners.addListener(listener, executor);
    }
    
    void awaitHealthy() {
      this.monitor.enterWhenUninterruptibly(this.awaitHealthGuard);
      try {
        checkHealthy();
      } finally {
        this.monitor.leave();
      } 
    }
    
    void awaitHealthy(long timeout, TimeUnit unit) throws TimeoutException {
      this.monitor.enter();
      try {
        if (!this.monitor.waitForUninterruptibly(this.awaitHealthGuard, timeout, unit)) {
          String str = String.valueOf(Multimaps.filterKeys(this.servicesByState, Predicates.in((Collection)ImmutableSet.of(Service.State.NEW, Service.State.STARTING))));
          throw new TimeoutException((new StringBuilder(93 + String.valueOf(str).length())).append("Timeout waiting for the services to become healthy. The following services have not started: ").append(str).toString());
        } 
        checkHealthy();
      } finally {
        this.monitor.leave();
      } 
    }
    
    void awaitStopped() {
      this.monitor.enterWhenUninterruptibly(this.stoppedGuard);
      this.monitor.leave();
    }
    
    void awaitStopped(long timeout, TimeUnit unit) throws TimeoutException {
      this.monitor.enter();
      try {
        if (!this.monitor.waitForUninterruptibly(this.stoppedGuard, timeout, unit)) {
          String str = String.valueOf(Multimaps.filterKeys(this.servicesByState, Predicates.not(Predicates.in(EnumSet.of(Service.State.TERMINATED, Service.State.FAILED)))));
          throw new TimeoutException((new StringBuilder(83 + String.valueOf(str).length())).append("Timeout waiting for the services to stop. The following services have not stopped: ").append(str).toString());
        } 
      } finally {
        this.monitor.leave();
      } 
    }
    
    ImmutableSetMultimap<Service.State, Service> servicesByState() {
      ImmutableSetMultimap.Builder<Service.State, Service> builder = ImmutableSetMultimap.builder();
      this.monitor.enter();
      try {
        for (Map.Entry<Service.State, Service> entry : (Iterable<Map.Entry<Service.State, Service>>)this.servicesByState.entries()) {
          if (!(entry.getValue() instanceof NoOpService))
            builder.put(entry); 
        } 
      } finally {
        this.monitor.leave();
      } 
      return builder.build();
    }
    
    ImmutableMap<Service, Long> startupTimes() {
      List<Map.Entry<Service, Long>> loadTimes;
      this.monitor.enter();
      try {
        loadTimes = Lists.newArrayListWithCapacity(this.startupTimers.size());
        for (Map.Entry<Service, Stopwatch> entry : this.startupTimers.entrySet()) {
          Service service = entry.getKey();
          Stopwatch stopwatch = entry.getValue();
          if (!stopwatch.isRunning() && !(service instanceof NoOpService))
            loadTimes.add(Maps.immutableEntry(service, Long.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS)))); 
        } 
      } finally {
        this.monitor.leave();
      } 
      Collections.sort(loadTimes, 
          
          (Comparator<? super Map.Entry<Service, Long>>)Ordering.natural()
          .onResultOf(new Function<Map.Entry<Service, Long>, Long>(this) {
              public Long apply(Map.Entry<Service, Long> input) {
                return input.getValue();
              }
            }));
      return ImmutableMap.copyOf(loadTimes);
    }
    
    void transitionService(Service service, Service.State from, Service.State to) {
      Preconditions.checkNotNull(service);
      Preconditions.checkArgument((from != to));
      this.monitor.enter();
      try {
        this.transitioned = true;
        if (!this.ready)
          return; 
        Preconditions.checkState(this.servicesByState
            .remove(from, service), "Service %s not at the expected location in the state map %s", service, from);
        Preconditions.checkState(this.servicesByState
            .put(to, service), "Service %s in the state map unexpectedly at %s", service, to);
        Stopwatch stopwatch = this.startupTimers.get(service);
        if (stopwatch == null) {
          stopwatch = Stopwatch.createStarted();
          this.startupTimers.put(service, stopwatch);
        } 
        if (to.compareTo(Service.State.RUNNING) >= 0 && stopwatch.isRunning()) {
          stopwatch.stop();
          if (!(service instanceof NoOpService))
            ServiceManager.logger.log(Level.FINE, "Started {0} in {1}.", new Object[] { service, stopwatch }); 
        } 
        if (to == Service.State.FAILED)
          enqueueFailedEvent(service); 
        if (this.states.count(Service.State.RUNNING) == this.numberOfServices) {
          enqueueHealthyEvent();
        } else if (this.states.count(Service.State.TERMINATED) + this.states.count(Service.State.FAILED) == this.numberOfServices) {
          enqueueStoppedEvent();
        } 
      } finally {
        this.monitor.leave();
        dispatchListenerEvents();
      } 
    }
    
    void enqueueStoppedEvent() {
      this.listeners.enqueue(ServiceManager.STOPPED_EVENT);
    }
    
    void enqueueHealthyEvent() {
      this.listeners.enqueue(ServiceManager.HEALTHY_EVENT);
    }
    
    void enqueueFailedEvent(final Service service) {
      this.listeners.enqueue(new ListenerCallQueue.Event<Listener>(this) {
            public void call(Listener listener) {
              listener.failure(service);
            }
            
            public String toString() {
              String str = String.valueOf(service);
              return (new StringBuilder(18 + String.valueOf(str).length())).append("failed({service=").append(str).append("})").toString();
            }
          });
    }
    
    void dispatchListenerEvents() {
      Preconditions.checkState(
          !this.monitor.isOccupiedByCurrentThread(), "It is incorrect to execute listeners with the monitor held.");
      this.listeners.dispatch();
    }
    
    @GuardedBy("monitor")
    void checkHealthy() {
      if (this.states.count(Service.State.RUNNING) != this.numberOfServices) {
        String str = String.valueOf(Multimaps.filterKeys(this.servicesByState, Predicates.not(Predicates.equalTo(Service.State.RUNNING))));
        IllegalStateException exception = new IllegalStateException((new StringBuilder(79 + String.valueOf(str).length())).append("Expected to be healthy after starting. The following services are not running: ").append(str).toString());
        for (Service service : this.servicesByState.get(Service.State.FAILED))
          exception.addSuppressed(new FailedService(service));
        throw exception;
      } 
    }
  }
  
  private static final class ServiceListener extends Service.Listener {
    final Service service;
    
    final WeakReference<ServiceManagerState> state;
    
    ServiceListener(Service service, WeakReference<ServiceManagerState> state) {
      this.service = service;
      this.state = state;
    }
    
    public void starting() {
      ServiceManagerState state = this.state.get();
      if (state != null) {
        state.transitionService(this.service, Service.State.NEW, Service.State.STARTING);
        if (!(this.service instanceof NoOpService))
          ServiceManager.logger.log(Level.FINE, "Starting {0}.", this.service); 
      } 
    }
    
    public void running() {
      ServiceManagerState state = this.state.get();
      if (state != null)
        state.transitionService(this.service, Service.State.STARTING, Service.State.RUNNING); 
    }
    
    public void stopping(Service.State from) {
      ServiceManagerState state = this.state.get();
      if (state != null)
        state.transitionService(this.service, from, Service.State.STOPPING); 
    }
    
    public void terminated(Service.State from) {
      ServiceManagerState state = this.state.get();
      if (state != null) {
        if (!(this.service instanceof NoOpService))
          ServiceManager.logger.log(Level.FINE, "Service {0} has terminated. Previous state was: {1}", new Object[] { this.service, from }); 
        state.transitionService(this.service, from, Service.State.TERMINATED);
      } 
    }
    
    public void failed(Service.State from, Throwable failure) {
      ServiceManagerState state = this.state.get();
      if (state != null) {
        boolean log = !(this.service instanceof NoOpService);
        int i = log & ((from != Service.State.STARTING) ? 1 : 0);
        if (i != 0) {
          String str1 = String.valueOf(this.service), str2 = String.valueOf(from);
          ServiceManager.logger.log(Level.SEVERE, (new StringBuilder(34 + String.valueOf(str1).length() + String.valueOf(str2).length())).append("Service ").append(str1).append(" has failed in the ").append(str2).append(" state.").toString(), failure);
        } 
        state.transitionService(this.service, from, Service.State.FAILED);
      } 
    }
  }
  
  private static final class NoOpService extends AbstractService {
    private NoOpService() {}
    
    protected void doStart() {
      notifyStarted();
    }
    
    protected void doStop() {
      notifyStopped();
    }
  }
  
  private static final class EmptyServiceManagerWarning extends Throwable {
    private EmptyServiceManagerWarning() {}
  }
  
  private static final class FailedService extends Throwable {
    FailedService(Service service) {
      super(service
          .toString(), service
          .failureCause(), false, false);
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\ServiceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */