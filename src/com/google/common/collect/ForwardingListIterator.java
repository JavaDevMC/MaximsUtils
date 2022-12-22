package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import java.util.ListIterator;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingListIterator<E> extends ForwardingIterator<E> implements ListIterator<E> {
  public void add(@ParametricNullness E element) {
    delegate().add(element);
  }
  
  public boolean hasPrevious() {
    return delegate().hasPrevious();
  }
  
  public int nextIndex() {
    return delegate().nextIndex();
  }
  
  @ParametricNullness
  @CanIgnoreReturnValue
  public E previous() {
    return delegate().previous();
  }
  
  public int previousIndex() {
    return delegate().previousIndex();
  }
  
  public void set(@ParametricNullness E element) {
    delegate().set(element);
  }
  
  protected abstract ListIterator<E> delegate();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ForwardingListIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */