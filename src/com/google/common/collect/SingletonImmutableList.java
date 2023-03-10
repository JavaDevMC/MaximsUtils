package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

@ElementTypesAreNonnullByDefault
@GwtCompatible(serializable = true, emulated = true)
final class SingletonImmutableList<E> extends ImmutableList<E> {
  final transient E element;
  
  SingletonImmutableList(E element) {
    this.element = (E)Preconditions.checkNotNull(element);
  }
  
  public E get(int index) {
    Preconditions.checkElementIndex(index, 1);
    return this.element;
  }
  
  public UnmodifiableIterator<E> iterator() {
    return Iterators.singletonIterator(this.element);
  }
  
  public Spliterator<E> spliterator() {
    return Collections.<E>singleton(this.element).spliterator();
  }
  
  public int size() {
    return 1;
  }
  
  public ImmutableList<E> subList(int fromIndex, int toIndex) {
    Preconditions.checkPositionIndexes(fromIndex, toIndex, 1);
    return (fromIndex == toIndex) ? ImmutableList.<E>of() : this;
  }
  
  public String toString() {
    String str = this.element.toString();
    return (new StringBuilder(2 + String.valueOf(str).length())).append('[').append(str).append(']').toString();
  }
  
  boolean isPartialView() {
    return false;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\SingletonImmutableList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */