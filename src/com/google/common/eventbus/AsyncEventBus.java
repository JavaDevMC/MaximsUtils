package com.google.common.eventbus;

import java.util.concurrent.Executor;

@ElementTypesAreNonnullByDefault
public class AsyncEventBus extends EventBus {
  public AsyncEventBus(String identifier, Executor executor) {
    super(identifier, executor, Dispatcher.legacyAsync(), LoggingHandler.INSTANCE);
  }
  
  public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
    super("default", executor, Dispatcher.legacyAsync(), subscriberExceptionHandler);
  }
  
  public AsyncEventBus(Executor executor) {
    super("default", executor, Dispatcher.legacyAsync(), LoggingHandler.INSTANCE);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\eventbus\AsyncEventBus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */