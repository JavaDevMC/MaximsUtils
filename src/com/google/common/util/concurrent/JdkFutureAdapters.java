package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

@ElementTypesAreNonnullByDefault
@Beta
@GwtIncompatible
public final class JdkFutureAdapters {
  public static <V> ListenableFuture<V> listenInPoolThread(Future<V> future) {
    if (future instanceof ListenableFuture)
      return (ListenableFuture<V>)future; 
    return new ListenableFutureAdapter<>(future);
  }
  
  public static <V> ListenableFuture<V> listenInPoolThread(Future<V> future, Executor executor) {
    Preconditions.checkNotNull(executor);
    if (future instanceof ListenableFuture)
      return (ListenableFuture<V>)future; 
    return new ListenableFutureAdapter<>(future, executor);
  }
  
  private static class ListenableFutureAdapter<V> extends ForwardingFuture<V> implements ListenableFuture<V> {
    private static final ThreadFactory threadFactory = (new ThreadFactoryBuilder())
      
      .setDaemon(true)
      .setNameFormat("ListenableFutureAdapter-thread-%d")
      .build();
    
    private static final Executor defaultAdapterExecutor = Executors.newCachedThreadPool(threadFactory);
    
    private final Executor adapterExecutor;
    
    private final ExecutionList executionList = new ExecutionList();
    
    private final AtomicBoolean hasListeners = new AtomicBoolean(false);
    
    private final Future<V> delegate;
    
    ListenableFutureAdapter(Future<V> delegate) {
      this(delegate, defaultAdapterExecutor);
    }
    
    ListenableFutureAdapter(Future<V> delegate, Executor adapterExecutor) {
      this.delegate = (Future<V>)Preconditions.checkNotNull(delegate);
      this.adapterExecutor = (Executor)Preconditions.checkNotNull(adapterExecutor);
    }
    
    protected Future<V> delegate() {
      return this.delegate;
    }
    
    public void addListener(Runnable listener, Executor exec) {
      this.executionList.add(listener, exec);
      if (this.hasListeners.compareAndSet(false, true)) {
        if (this.delegate.isDone()) {
          this.executionList.execute();
          return;
        } 
        this.adapterExecutor.execute(new Runnable() {
              public void run() {
                try {
                  Uninterruptibles.getUninterruptibly(ListenableFutureAdapter.this.delegate);
                } catch (Throwable throwable) {}
                ListenableFutureAdapter.this.executionList.execute();
              }
            });
      } 
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\JdkFutureAdapters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */