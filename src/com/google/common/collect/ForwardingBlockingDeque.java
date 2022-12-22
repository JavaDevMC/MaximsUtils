package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.util.Collection;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;

@Deprecated
@ElementTypesAreNonnullByDefault
@GwtIncompatible
public abstract class ForwardingBlockingDeque<E> extends ForwardingDeque<E> implements BlockingDeque<E> {
  public int remainingCapacity() {
    return delegate().remainingCapacity();
  }
  
  public void putFirst(E e) throws InterruptedException {
    delegate().putFirst(e);
  }
  
  public void putLast(E e) throws InterruptedException {
    delegate().putLast(e);
  }
  
  public boolean offerFirst(E e, long timeout, TimeUnit unit) throws InterruptedException {
    return delegate().offerFirst(e, timeout, unit);
  }
  
  public boolean offerLast(E e, long timeout, TimeUnit unit) throws InterruptedException {
    return delegate().offerLast(e, timeout, unit);
  }
  
  public E takeFirst() throws InterruptedException {
    return delegate().takeFirst();
  }
  
  public E takeLast() throws InterruptedException {
    return delegate().takeLast();
  }
  
  @CheckForNull
  public E pollFirst(long timeout, TimeUnit unit) throws InterruptedException {
    return delegate().pollFirst(timeout, unit);
  }
  
  @CheckForNull
  public E pollLast(long timeout, TimeUnit unit) throws InterruptedException {
    return delegate().pollLast(timeout, unit);
  }
  
  public void put(E e) throws InterruptedException {
    delegate().put(e);
  }
  
  public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
    return delegate().offer(e, timeout, unit);
  }
  
  public E take() throws InterruptedException {
    return delegate().take();
  }
  
  @CheckForNull
  public E poll(long timeout, TimeUnit unit) throws InterruptedException {
    return delegate().poll(timeout, unit);
  }
  
  public int drainTo(Collection<? super E> c) {
    return delegate().drainTo(c);
  }
  
  public int drainTo(Collection<? super E> c, int maxElements) {
    return delegate().drainTo(c, maxElements);
  }
  
  protected abstract BlockingDeque<E> delegate();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ForwardingBlockingDeque.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */