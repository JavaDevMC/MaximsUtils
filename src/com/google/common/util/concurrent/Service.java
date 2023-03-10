package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@DoNotMock("Create an AbstractIdleService")
@ElementTypesAreNonnullByDefault
@GwtIncompatible
public interface Service {
  @CanIgnoreReturnValue
  Service startAsync();
  
  boolean isRunning();
  
  State state();
  
  @CanIgnoreReturnValue
  Service stopAsync();
  
  void awaitRunning();
  
  default void awaitRunning(Duration timeout) throws TimeoutException {
    awaitRunning(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
  }
  
  void awaitRunning(long paramLong, TimeUnit paramTimeUnit) throws TimeoutException;
  
  void awaitTerminated();
  
  default void awaitTerminated(Duration timeout) throws TimeoutException {
    awaitTerminated(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
  }
  
  void awaitTerminated(long paramLong, TimeUnit paramTimeUnit) throws TimeoutException;
  
  Throwable failureCause();
  
  void addListener(Listener paramListener, Executor paramExecutor);
  
  public enum State {
    NEW, STARTING, RUNNING, STOPPING, TERMINATED, FAILED;
  }
  
  public static abstract class Listener {
    public void starting() {}
    
    public void running() {}
    
    public void stopping(State from) {}
    
    public void terminated(State from) {}
    
    public void failed(State from, Throwable failure) {}
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\Service.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */