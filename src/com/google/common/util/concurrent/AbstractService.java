package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public abstract class AbstractService implements Service {
  private static final ListenerCallQueue.Event<Listener> STARTING_EVENT = new ListenerCallQueue.Event<Listener>() {
      public void call(Listener listener) {
        listener.starting();
      }
      
      public String toString() {
        return "starting()";
      }
    };
  
  private static final ListenerCallQueue.Event<Listener> RUNNING_EVENT = new ListenerCallQueue.Event<Listener>() {
      public void call(Listener listener) {
        listener.running();
      }
      
      public String toString() {
        return "running()";
      }
    };
  
  private static final ListenerCallQueue.Event<Listener> STOPPING_FROM_STARTING_EVENT = stoppingEvent(State.STARTING);
  
  private static final ListenerCallQueue.Event<Listener> STOPPING_FROM_RUNNING_EVENT = stoppingEvent(State.RUNNING);
  
  private static final ListenerCallQueue.Event<Listener> TERMINATED_FROM_NEW_EVENT = terminatedEvent(State.NEW);
  
  private static final ListenerCallQueue.Event<Listener> TERMINATED_FROM_STARTING_EVENT = terminatedEvent(State.STARTING);
  
  private static final ListenerCallQueue.Event<Listener> TERMINATED_FROM_RUNNING_EVENT = terminatedEvent(State.RUNNING);
  
  private static final ListenerCallQueue.Event<Listener> TERMINATED_FROM_STOPPING_EVENT = terminatedEvent(State.STOPPING);
  
  private static ListenerCallQueue.Event<Listener> terminatedEvent(final State from) {
    return new ListenerCallQueue.Event<Listener>() {
        public void call(Listener listener) {
          listener.terminated(from);
        }
        
        public String toString() {
          String str = String.valueOf(from);
          return (new StringBuilder(21 + String.valueOf(str).length())).append("terminated({from = ").append(str).append("})").toString();
        }
      };
  }
  
  private static ListenerCallQueue.Event<Listener> stoppingEvent(final State from) {
    return new ListenerCallQueue.Event<Listener>() {
        public void call(Listener listener) {
          listener.stopping(from);
        }
        
        public String toString() {
          String str = String.valueOf(from);
          return (new StringBuilder(19 + String.valueOf(str).length())).append("stopping({from = ").append(str).append("})").toString();
        }
      };
  }
  
  private final Monitor monitor = new Monitor();
  
  private final Monitor.Guard isStartable = new IsStartableGuard();
  
  private final class IsStartableGuard extends Monitor.Guard {
    public boolean isSatisfied() {
      return (AbstractService.this.state() == State.NEW);
    }
  }
  
  private final Monitor.Guard isStoppable = new IsStoppableGuard();
  
  private final class IsStoppableGuard extends Monitor.Guard {
    public boolean isSatisfied() {
      return (AbstractService.this.state().compareTo(State.RUNNING) <= 0);
    }
  }
  
  private final Monitor.Guard hasReachedRunning = new HasReachedRunningGuard();
  
  private final class HasReachedRunningGuard extends Monitor.Guard {
    public boolean isSatisfied() {
      return (AbstractService.this.state().compareTo(State.RUNNING) >= 0);
    }
  }
  
  private final Monitor.Guard isStopped = new IsStoppedGuard();
  
  private final class IsStoppedGuard extends Monitor.Guard {
    public boolean isSatisfied() {
      return (AbstractService.this.state().compareTo(State.TERMINATED) >= 0);
    }
  }
  
  private final ListenerCallQueue<Listener> listeners = new ListenerCallQueue<>();
  
  private volatile StateSnapshot snapshot = new StateSnapshot(State.NEW);
  
  @Beta
  @ForOverride
  protected void doCancelStart() {}
  
  @CanIgnoreReturnValue
  public final Service startAsync() {
    if (this.monitor.enterIf(this.isStartable)) {
      try {
        this.snapshot = new StateSnapshot(State.STARTING);
        enqueueStartingEvent();
        doStart();
      } catch (Throwable startupFailure) {
        notifyFailed(startupFailure);
      } finally {
        this.monitor.leave();
        dispatchListenerEvents();
      } 
    } else {
      String str = String.valueOf(this);
      throw new IllegalStateException((new StringBuilder(33 + String.valueOf(str).length())).append("Service ").append(str).append(" has already been started").toString());
    } 
    return this;
  }
  
  @CanIgnoreReturnValue
  public final Service stopAsync() {
    if (this.monitor.enterIf(this.isStoppable))
      try {
        String str;
        State previous = state();
        switch (previous) {
          case NEW:
            this.snapshot = new StateSnapshot(State.TERMINATED);
            enqueueTerminatedEvent(State.NEW);
            break;
          case STARTING:
            this.snapshot = new StateSnapshot(State.STARTING, true, null);
            enqueueStoppingEvent(State.STARTING);
            doCancelStart();
            break;
          case RUNNING:
            this.snapshot = new StateSnapshot(State.STOPPING);
            enqueueStoppingEvent(State.RUNNING);
            doStop();
            break;
          case STOPPING:
          case TERMINATED:
          case FAILED:
            str = String.valueOf(previous);
            throw new AssertionError((new StringBuilder(45 + String.valueOf(str).length())).append("isStoppable is incorrectly implemented, saw: ").append(str).toString());
        } 
      } catch (Throwable shutdownFailure) {
        notifyFailed(shutdownFailure);
      } finally {
        this.monitor.leave();
        dispatchListenerEvents();
      }  
    return this;
  }
  
  public final void awaitRunning() {
    this.monitor.enterWhenUninterruptibly(this.hasReachedRunning);
    try {
      checkCurrentState(State.RUNNING);
    } finally {
      this.monitor.leave();
    } 
  }
  
  public final void awaitRunning(Duration timeout) throws TimeoutException {
    super.awaitRunning(timeout);
  }
  
  public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
    if (this.monitor.enterWhenUninterruptibly(this.hasReachedRunning, timeout, unit)) {
      try {
        checkCurrentState(State.RUNNING);
      } finally {
        this.monitor.leave();
      } 
    } else {
      String str = String.valueOf(this);
      throw new TimeoutException((new StringBuilder(50 + String.valueOf(str).length())).append("Timed out waiting for ").append(str).append(" to reach the RUNNING state.").toString());
    } 
  }
  
  public final void awaitTerminated() {
    this.monitor.enterWhenUninterruptibly(this.isStopped);
    try {
      checkCurrentState(State.TERMINATED);
    } finally {
      this.monitor.leave();
    } 
  }
  
  public final void awaitTerminated(Duration timeout) throws TimeoutException {
    super.awaitTerminated(timeout);
  }
  
  public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
    if (this.monitor.enterWhenUninterruptibly(this.isStopped, timeout, unit)) {
      try {
        checkCurrentState(State.TERMINATED);
      } finally {
        this.monitor.leave();
      } 
    } else {
      String str1 = String.valueOf(this);
      String str2 = String.valueOf(state());
      throw new TimeoutException((new StringBuilder(65 + String.valueOf(str1).length() + String.valueOf(str2).length())).append("Timed out waiting for ").append(str1).append(" to reach a terminal state. Current state: ").append(str2).toString());
    } 
  }
  
  @GuardedBy("monitor")
  private void checkCurrentState(State expected) {
    State actual = state();
    if (actual != expected) {
      if (actual == State.FAILED) {
        String str4 = String.valueOf(this), str5 = String.valueOf(expected);
        throw new IllegalStateException((new StringBuilder(56 + String.valueOf(str4).length() + String.valueOf(str5).length())).append("Expected the service ").append(str4).append(" to be ").append(str5).append(", but the service has FAILED").toString(), 
            
            failureCause());
      } 
      String str1 = String.valueOf(this), str2 = String.valueOf(expected), str3 = String.valueOf(actual);
      throw new IllegalStateException((new StringBuilder(38 + String.valueOf(str1).length() + String.valueOf(str2).length() + String.valueOf(str3).length())).append("Expected the service ").append(str1).append(" to be ").append(str2).append(", but was ").append(str3).toString());
    } 
  }
  
  protected final void notifyStarted() {
    this.monitor.enter();
    try {
      if (this.snapshot.state != State.STARTING) {
        String str = String.valueOf(this.snapshot.state);
        IllegalStateException failure = new IllegalStateException((new StringBuilder(43 + String.valueOf(str).length())).append("Cannot notifyStarted() when the service is ").append(str).toString());
        notifyFailed(failure);
        throw failure;
      } 
      if (this.snapshot.shutdownWhenStartupFinishes) {
        this.snapshot = new StateSnapshot(State.STOPPING);
        doStop();
      } else {
        this.snapshot = new StateSnapshot(State.RUNNING);
        enqueueRunningEvent();
      } 
    } finally {
      this.monitor.leave();
      dispatchListenerEvents();
    } 
  }
  
  protected final void notifyStopped() {
    this.monitor.enter();
    try {
      String str;
      State previous = state();
      switch (previous) {
        case NEW:
        case TERMINATED:
        case FAILED:
          str = String.valueOf(previous);
          throw new IllegalStateException((new StringBuilder(43 + String.valueOf(str).length())).append("Cannot notifyStopped() when the service is ").append(str).toString());
        case STARTING:
        case RUNNING:
        case STOPPING:
          this.snapshot = new StateSnapshot(State.TERMINATED);
          enqueueTerminatedEvent(previous);
          break;
      } 
    } finally {
      this.monitor.leave();
      dispatchListenerEvents();
    } 
  }
  
  protected final void notifyFailed(Throwable cause) {
    Preconditions.checkNotNull(cause);
    this.monitor.enter();
    try {
      String str;
      State previous = state();
      switch (previous) {
        case NEW:
        case TERMINATED:
          str = String.valueOf(previous);
          throw new IllegalStateException((new StringBuilder(22 + String.valueOf(str).length())).append("Failed while in state:").append(str).toString(), cause);
        case STARTING:
        case RUNNING:
        case STOPPING:
          this.snapshot = new StateSnapshot(State.FAILED, false, cause);
          enqueueFailedEvent(previous, cause);
          break;
      } 
    } finally {
      this.monitor.leave();
      dispatchListenerEvents();
    } 
  }
  
  public final boolean isRunning() {
    return (state() == State.RUNNING);
  }
  
  public final State state() {
    return this.snapshot.externalState();
  }
  
  public final Throwable failureCause() {
    return this.snapshot.failureCause();
  }
  
  public final void addListener(Listener listener, Executor executor) {
    this.listeners.addListener(listener, executor);
  }
  
  public String toString() {
    String str1 = getClass().getSimpleName(), str2 = String.valueOf(state());
    return (new StringBuilder(3 + String.valueOf(str1).length() + String.valueOf(str2).length())).append(str1).append(" [").append(str2).append("]").toString();
  }
  
  private void dispatchListenerEvents() {
    if (!this.monitor.isOccupiedByCurrentThread())
      this.listeners.dispatch(); 
  }
  
  private void enqueueStartingEvent() {
    this.listeners.enqueue(STARTING_EVENT);
  }
  
  private void enqueueRunningEvent() {
    this.listeners.enqueue(RUNNING_EVENT);
  }
  
  private void enqueueStoppingEvent(State from) {
    if (from == State.STARTING) {
      this.listeners.enqueue(STOPPING_FROM_STARTING_EVENT);
    } else if (from == State.RUNNING) {
      this.listeners.enqueue(STOPPING_FROM_RUNNING_EVENT);
    } else {
      throw new AssertionError();
    } 
  }
  
  private void enqueueTerminatedEvent(State from) {
    switch (from) {
      case NEW:
        this.listeners.enqueue(TERMINATED_FROM_NEW_EVENT);
        break;
      case STARTING:
        this.listeners.enqueue(TERMINATED_FROM_STARTING_EVENT);
        break;
      case RUNNING:
        this.listeners.enqueue(TERMINATED_FROM_RUNNING_EVENT);
        break;
      case STOPPING:
        this.listeners.enqueue(TERMINATED_FROM_STOPPING_EVENT);
        break;
      case TERMINATED:
      case FAILED:
        throw new AssertionError();
    } 
  }
  
  private void enqueueFailedEvent(final State from, final Throwable cause) {
    this.listeners.enqueue(new ListenerCallQueue.Event<Listener>(this) {
          public void call(Listener listener) {
            listener.failed(from, cause);
          }
          
          public String toString() {
            String str1 = String.valueOf(from), str2 = String.valueOf(cause);
            return (new StringBuilder(27 + String.valueOf(str1).length() + String.valueOf(str2).length())).append("failed({from = ").append(str1).append(", cause = ").append(str2).append("})").toString();
          }
        });
  }
  
  @ForOverride
  protected abstract void doStart();
  
  @ForOverride
  protected abstract void doStop();
  
  private static final class StateSnapshot {
    final State state;
    
    final boolean shutdownWhenStartupFinishes;
    
    @CheckForNull
    final Throwable failure;
    
    StateSnapshot(State internalState) {
      this(internalState, false, null);
    }
    
    StateSnapshot(State internalState, boolean shutdownWhenStartupFinishes, @CheckForNull Throwable failure) {
      Preconditions.checkArgument((!shutdownWhenStartupFinishes || internalState == State.STARTING), "shutdownWhenStartupFinishes can only be set if state is STARTING. Got %s instead.", internalState);
      Preconditions.checkArgument((((failure != null) ? true : false) == ((internalState == State.FAILED) ? true : false)), "A failure cause should be set if and only if the state is failed.  Got %s and %s instead.", internalState, failure);
      this.state = internalState;
      this.shutdownWhenStartupFinishes = shutdownWhenStartupFinishes;
      this.failure = failure;
    }
    
    State externalState() {
      if (this.shutdownWhenStartupFinishes && this.state == State.STARTING)
        return State.STOPPING;
      return this.state;
    }
    
    Throwable failureCause() {
      Preconditions.checkState((this.state == State.FAILED), "failureCause() is only valid if the service has failed, service is %s", this.state);
      return Objects.<Throwable>requireNonNull(this.failure);
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\AbstractService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */