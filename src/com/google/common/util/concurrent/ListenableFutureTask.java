package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public class ListenableFutureTask<V> extends FutureTask<V> implements ListenableFuture<V> {
  private final ExecutionList executionList = new ExecutionList();
  
  public static <V> ListenableFutureTask<V> create(Callable<V> callable) {
    return new ListenableFutureTask<>(callable);
  }
  
  public static <V> ListenableFutureTask<V> create(Runnable runnable, @ParametricNullness V result) {
    return new ListenableFutureTask<>(runnable, result);
  }
  
  ListenableFutureTask(Callable<V> callable) {
    super(callable);
  }
  
  ListenableFutureTask(Runnable runnable, @ParametricNullness V result) {
    super(runnable, result);
  }
  
  public void addListener(Runnable listener, Executor exec) {
    this.executionList.add(listener, exec);
  }
  
  @ParametricNullness
  @CanIgnoreReturnValue
  public V get(long timeout, TimeUnit unit) throws TimeoutException, InterruptedException, ExecutionException {
    long timeoutNanos = unit.toNanos(timeout);
    if (timeoutNanos <= 2147483647999999999L)
      return super.get(timeout, unit); 
    return super.get(
        Math.min(timeoutNanos, 2147483647999999999L), TimeUnit.NANOSECONDS);
  }
  
  protected void done() {
    this.executionList.execute();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\ListenableFutureTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */