package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingObject;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@ElementTypesAreNonnullByDefault
@CanIgnoreReturnValue
@GwtCompatible
public abstract class ForwardingFuture<V> extends ForwardingObject implements Future<V> {
  public boolean cancel(boolean mayInterruptIfRunning) {
    return delegate().cancel(mayInterruptIfRunning);
  }
  
  public boolean isCancelled() {
    return delegate().isCancelled();
  }
  
  public boolean isDone() {
    return delegate().isDone();
  }
  
  @ParametricNullness
  public V get() throws InterruptedException, ExecutionException {
    return delegate().get();
  }
  
  @ParametricNullness
  public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    return delegate().get(timeout, unit);
  }
  
  protected abstract Future<? extends V> delegate();
  
  public static abstract class SimpleForwardingFuture<V> extends ForwardingFuture<V> {
    private final Future<V> delegate;
    
    protected SimpleForwardingFuture(Future<V> delegate) {
      this.delegate = (Future<V>)Preconditions.checkNotNull(delegate);
    }
    
    protected final Future<V> delegate() {
      return this.delegate;
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\ForwardingFuture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */