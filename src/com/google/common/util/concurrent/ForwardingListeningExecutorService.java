package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@ElementTypesAreNonnullByDefault
@CanIgnoreReturnValue
@GwtIncompatible
public abstract class ForwardingListeningExecutorService extends ForwardingExecutorService implements ListeningExecutorService {
  public <T> ListenableFuture<T> submit(Callable<T> task) {
    return delegate().submit(task);
  }
  
  public ListenableFuture<?> submit(Runnable task) {
    return delegate().submit(task);
  }
  
  public <T> ListenableFuture<T> submit(Runnable task, @ParametricNullness T result) {
    return delegate().submit(task, result);
  }
  
  protected abstract ListeningExecutorService delegate();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\ForwardingListeningExecutorService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */