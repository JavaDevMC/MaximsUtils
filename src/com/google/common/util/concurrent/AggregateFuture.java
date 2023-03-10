package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AggregateFuture<InputT, OutputT> extends AggregateFutureState<OutputT> {
  private static final Logger logger = Logger.getLogger(AggregateFuture.class.getName());
  
  @CheckForNull
  private ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures;
  
  private final boolean allMustSucceed;
  
  private final boolean collectsValues;
  
  AggregateFuture(ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures, boolean allMustSucceed, boolean collectsValues) {
    super(futures.size());
    this.futures = (ImmutableCollection<? extends ListenableFuture<? extends InputT>>)Preconditions.checkNotNull(futures);
    this.allMustSucceed = allMustSucceed;
    this.collectsValues = collectsValues;
  }
  
  protected final void afterDone() {
    super.afterDone();
    ImmutableCollection<? extends Future<?>> localFutures = (ImmutableCollection)this.futures;
    releaseResources(ReleaseResourcesReason.OUTPUT_FUTURE_DONE);
    if ((isCancelled() & ((localFutures != null) ? 1 : 0)) != 0) {
      boolean wasInterrupted = wasInterrupted();
      for (UnmodifiableIterator<Future> unmodifiableIterator = localFutures.iterator(); unmodifiableIterator.hasNext(); ) {
        Future<?> future = unmodifiableIterator.next();
        future.cancel(wasInterrupted);
      } 
    } 
  }
  
  @CheckForNull
  protected final String pendingToString() {
    ImmutableCollection<? extends Future<?>> localFutures = (ImmutableCollection)this.futures;
    if (localFutures != null) {
      String str = String.valueOf(localFutures);
      return (new StringBuilder(8 + String.valueOf(str).length())).append("futures=").append(str).toString();
    } 
    return super.pendingToString();
  }
  
  final void init() {
    Objects.requireNonNull(this.futures);
    if (this.futures.isEmpty()) {
      handleAllCompleted();
      return;
    } 
    if (this.allMustSucceed) {
      int i = 0;
      for (UnmodifiableIterator<ListenableFuture<? extends InputT>> unmodifiableIterator = this.futures.iterator(); unmodifiableIterator.hasNext(); ) {
        final ListenableFuture<? extends InputT> future = unmodifiableIterator.next();
        final int index = i++;
        future.addListener(new Runnable() {
              public void run() {
                try {
                  if (future.isCancelled()) {
                    AggregateFuture.this.futures = null;
                    AggregateFuture.this.cancel(false);
                  } else {
                    AggregateFuture.this.collectValueFromNonCancelledFuture(index, future);
                  } 
                } finally {
                  AggregateFuture.this.decrementCountAndMaybeComplete((ImmutableCollection<? extends Future<? extends InputT>>)null);
                } 
              }
            }MoreExecutors.directExecutor());
      } 
    } else {
      final ImmutableCollection<? extends ListenableFuture<? extends InputT>> localFutures = this.collectsValues ? this.futures : null;
      Runnable listener = new Runnable() {
          public void run() {
            AggregateFuture.this.decrementCountAndMaybeComplete(localFutures);
          }
        };
      for (UnmodifiableIterator<ListenableFuture<? extends InputT>> unmodifiableIterator = this.futures.iterator(); unmodifiableIterator.hasNext(); ) {
        final ListenableFuture<? extends InputT> future = unmodifiableIterator.next();
        future.addListener(listener, MoreExecutors.directExecutor());
      } 
    } 
  }
  
  private void handleException(Throwable throwable) {
    Preconditions.checkNotNull(throwable);
    if (this.allMustSucceed) {
      boolean completedWithFailure = setException(throwable);
      if (!completedWithFailure) {
        boolean firstTimeSeeingThisException = addCausalChain(getOrInitSeenExceptions(), throwable);
        if (firstTimeSeeingThisException) {
          log(throwable);
          return;
        } 
      } 
    } 
    if (throwable instanceof Error)
      log(throwable); 
  }
  
  private static void log(Throwable throwable) {
    String message = (throwable instanceof Error) ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first";
    logger.log(Level.SEVERE, message, throwable);
  }
  
  final void addInitialException(Set<Throwable> seen) {
    Preconditions.checkNotNull(seen);
    if (!isCancelled())
      boolean bool = addCausalChain(seen, Objects.<Throwable>requireNonNull(tryInternalFastPathGetFailure())); 
  }
  
  private void collectValueFromNonCancelledFuture(int index, Future<? extends InputT> future) {
    try {
      collectOneValue(index, Futures.getDone((Future)future));
    } catch (ExecutionException e) {
      handleException(e.getCause());
    } catch (Throwable t) {
      handleException(t);
    } 
  }
  
  private void decrementCountAndMaybeComplete(@CheckForNull ImmutableCollection<? extends Future<? extends InputT>> futuresIfNeedToCollectAtCompletion) {
    int newRemaining = decrementRemainingAndGet();
    Preconditions.checkState((newRemaining >= 0), "Less than 0 remaining futures");
    if (newRemaining == 0)
      processCompleted(futuresIfNeedToCollectAtCompletion); 
  }
  
  private void processCompleted(@CheckForNull ImmutableCollection<? extends Future<? extends InputT>> futuresIfNeedToCollectAtCompletion) {
    if (futuresIfNeedToCollectAtCompletion != null) {
      int i = 0;
      for (UnmodifiableIterator<Future<? extends InputT>> unmodifiableIterator = futuresIfNeedToCollectAtCompletion.iterator(); unmodifiableIterator.hasNext(); ) {
        Future<? extends InputT> future = unmodifiableIterator.next();
        if (!future.isCancelled())
          collectValueFromNonCancelledFuture(i, future); 
        i++;
      } 
    } 
    clearSeenExceptions();
    handleAllCompleted();
    releaseResources(ReleaseResourcesReason.ALL_INPUT_FUTURES_PROCESSED);
  }
  
  @ForOverride
  @OverridingMethodsMustInvokeSuper
  void releaseResources(ReleaseResourcesReason reason) {
    Preconditions.checkNotNull(reason);
    this.futures = null;
  }
  
  enum ReleaseResourcesReason {
    OUTPUT_FUTURE_DONE, ALL_INPUT_FUTURES_PROCESSED;
  }
  
  private static boolean addCausalChain(Set<Throwable> seen, Throwable param) {
    Throwable t = param;
    for (; t != null; t = t.getCause()) {
      boolean firstTimeSeen = seen.add(t);
      if (!firstTimeSeen)
        return false; 
    } 
    return true;
  }
  
  abstract void collectOneValue(int paramInt, @ParametricNullness InputT paramInputT);
  
  abstract void handleAllCompleted();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\AggregateFuture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */