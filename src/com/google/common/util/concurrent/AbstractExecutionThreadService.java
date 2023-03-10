package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public abstract class AbstractExecutionThreadService implements Service {
  private static final Logger logger = Logger.getLogger(AbstractExecutionThreadService.class.getName());
  
  private final Service delegate = new AbstractService() {
      protected final void doStart() {
        Executor executor = MoreExecutors.renamingDecorator(AbstractExecutionThreadService.this
            .executor(), new Supplier<String>() {
              public String get() {
                return AbstractExecutionThreadService.this.serviceName();
              }
            });
        executor.execute(new Runnable() {
              public void run() {
                try {
                  AbstractExecutionThreadService.this.startUp();
                  AbstractExecutionThreadService.null.this.notifyStarted();
                  if (AbstractExecutionThreadService.null.this.isRunning())
                    try {
                      AbstractExecutionThreadService.this.run();
                    } catch (Throwable t) {
                      try {
                        AbstractExecutionThreadService.this.shutDown();
                      } catch (Exception ignored) {
                        AbstractExecutionThreadService.logger.log(Level.WARNING, "Error while attempting to shut down the service after failure.", ignored);
                      } 
                      AbstractExecutionThreadService.null.this.notifyFailed(t);
                      return;
                    }  
                  AbstractExecutionThreadService.this.shutDown();
                  AbstractExecutionThreadService.null.this.notifyStopped();
                } catch (Throwable t) {
                  AbstractExecutionThreadService.null.this.notifyFailed(t);
                } 
              }
            });
      }
      
      protected void doStop() {
        AbstractExecutionThreadService.this.triggerShutdown();
      }
      
      public String toString() {
        return AbstractExecutionThreadService.this.toString();
      }
    };
  
  protected void startUp() throws Exception {}
  
  protected void shutDown() throws Exception {}
  
  @Beta
  protected void triggerShutdown() {}
  
  protected Executor executor() {
    return new Executor() {
        public void execute(Runnable command) {
          MoreExecutors.newThread(AbstractExecutionThreadService.this.serviceName(), command).start();
        }
      };
  }
  
  public String toString() {
    String str1 = serviceName(), str2 = String.valueOf(state());
    return (new StringBuilder(3 + String.valueOf(str1).length() + String.valueOf(str2).length())).append(str1).append(" [").append(str2).append("]").toString();
  }
  
  public final boolean isRunning() {
    return this.delegate.isRunning();
  }
  
  public final State state() {
    return this.delegate.state();
  }
  
  public final void addListener(Listener listener, Executor executor) {
    this.delegate.addListener(listener, executor);
  }
  
  public final Throwable failureCause() {
    return this.delegate.failureCause();
  }
  
  @CanIgnoreReturnValue
  public final Service startAsync() {
    this.delegate.startAsync();
    return this;
  }
  
  @CanIgnoreReturnValue
  public final Service stopAsync() {
    this.delegate.stopAsync();
    return this;
  }
  
  public final void awaitRunning() {
    this.delegate.awaitRunning();
  }
  
  public final void awaitRunning(Duration timeout) throws TimeoutException {
    super.awaitRunning(timeout);
  }
  
  public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
    this.delegate.awaitRunning(timeout, unit);
  }
  
  public final void awaitTerminated() {
    this.delegate.awaitTerminated();
  }
  
  public final void awaitTerminated(Duration timeout) throws TimeoutException {
    super.awaitTerminated(timeout);
  }
  
  public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
    this.delegate.awaitTerminated(timeout, unit);
  }
  
  protected String serviceName() {
    return getClass().getSimpleName();
  }
  
  protected abstract void run() throws Exception;
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\AbstractExecutionThreadService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */