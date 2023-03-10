package com.google.common.eventbus;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

@ElementTypesAreNonnullByDefault
public class EventBus {
  private static final Logger logger = Logger.getLogger(EventBus.class.getName());
  
  private final String identifier;
  
  private final Executor executor;
  
  private final SubscriberExceptionHandler exceptionHandler;
  
  private final SubscriberRegistry subscribers = new SubscriberRegistry(this);
  
  private final Dispatcher dispatcher;
  
  public EventBus() {
    this("default");
  }
  
  public EventBus(String identifier) {
    this(identifier, 
        
        MoreExecutors.directExecutor(), 
        Dispatcher.perThreadDispatchQueue(), LoggingHandler.INSTANCE);
  }
  
  public EventBus(SubscriberExceptionHandler exceptionHandler) {
    this("default", 
        
        MoreExecutors.directExecutor(), 
        Dispatcher.perThreadDispatchQueue(), exceptionHandler);
  }
  
  EventBus(String identifier, Executor executor, Dispatcher dispatcher, SubscriberExceptionHandler exceptionHandler) {
    this.identifier = (String)Preconditions.checkNotNull(identifier);
    this.executor = (Executor)Preconditions.checkNotNull(executor);
    this.dispatcher = (Dispatcher)Preconditions.checkNotNull(dispatcher);
    this.exceptionHandler = (SubscriberExceptionHandler)Preconditions.checkNotNull(exceptionHandler);
  }
  
  public final String identifier() {
    return this.identifier;
  }
  
  final Executor executor() {
    return this.executor;
  }
  
  void handleSubscriberException(Throwable e, SubscriberExceptionContext context) {
    Preconditions.checkNotNull(e);
    Preconditions.checkNotNull(context);
    try {
      this.exceptionHandler.handleException(e, context);
    } catch (Throwable e2) {
      logger.log(Level.SEVERE, 
          
          String.format(Locale.ROOT, "Exception %s thrown while handling exception: %s", new Object[] { e2, e }), e2);
    } 
  }
  
  public void register(Object object) {
    this.subscribers.register(object);
  }
  
  public void unregister(Object object) {
    this.subscribers.unregister(object);
  }
  
  public void post(Object event) {
    Iterator<Subscriber> eventSubscribers = this.subscribers.getSubscribers(event);
    if (eventSubscribers.hasNext()) {
      this.dispatcher.dispatch(event, eventSubscribers);
    } else if (!(event instanceof DeadEvent)) {
      post(new DeadEvent(this, event));
    } 
  }
  
  public String toString() {
    return MoreObjects.toStringHelper(this).addValue(this.identifier).toString();
  }
  
  static final class LoggingHandler implements SubscriberExceptionHandler {
    static final LoggingHandler INSTANCE = new LoggingHandler();
    
    public void handleException(Throwable exception, SubscriberExceptionContext context) {
      Logger logger = logger(context);
      if (logger.isLoggable(Level.SEVERE))
        logger.log(Level.SEVERE, message(context), exception); 
    }
    
    private static Logger logger(SubscriberExceptionContext context) {
      String str1 = EventBus.class.getName(), str2 = context.getEventBus().identifier();
      return Logger.getLogger((new StringBuilder(1 + String.valueOf(str1).length() + String.valueOf(str2).length())).append(str1).append(".").append(str2).toString());
    }
    
    private static String message(SubscriberExceptionContext context) {
      Method method = context.getSubscriberMethod();
      String str1 = method.getName();
      String str2 = method.getParameterTypes()[0].getName();
      String str3 = String.valueOf(context.getSubscriber());
      String str4 = String.valueOf(context.getEvent());
      return (new StringBuilder(80 + String.valueOf(str1).length() + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length())).append("Exception thrown by subscriber method ").append(str1).append('(').append(str2).append(')').append(" on subscriber ").append(str3).append(" when dispatching event: ").append(str4).toString();
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\eventbus\EventBus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */