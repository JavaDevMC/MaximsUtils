package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(serializable = true, emulated = true)
final class SingletonImmutableSet<E> extends ImmutableSet<E> {
  final transient E element;
  
  SingletonImmutableSet(E element) {
    this.element = (E)Preconditions.checkNotNull(element);
  }
  
  public int size() {
    return 1;
  }
  
  public boolean contains(@CheckForNull Object target) {
    return this.element.equals(target);
  }
  
  public UnmodifiableIterator<E> iterator() {
    return Iterators.singletonIterator(this.element);
  }
  
  public ImmutableList<E> asList() {
    return ImmutableList.of(this.element);
  }
  
  boolean isPartialView() {
    return false;
  }
  
  int copyIntoArray(Object[] dst, int offset) {
    dst[offset] = this.element;
    return offset + 1;
  }
  
  public final int hashCode() {
    return this.element.hashCode();
  }
  
  public String toString() {
    String str = this.element.toString();
    return (new StringBuilder(2 + String.valueOf(str).length())).append('[').append(str).append(']').toString();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\SingletonImmutableSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */