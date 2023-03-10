package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(serializable = true, emulated = true)
final class RegularImmutableSet<E> extends ImmutableSet.CachingAsList<E> {
  private static final Object[] EMPTY_ARRAY = new Object[0];
  
  static final RegularImmutableSet<Object> EMPTY = new RegularImmutableSet(EMPTY_ARRAY, 0, EMPTY_ARRAY, 0);
  
  private final transient Object[] elements;
  
  private final transient int hashCode;
  
  @VisibleForTesting
  final transient Object[] table;
  
  private final transient int mask;
  
  RegularImmutableSet(Object[] elements, int hashCode, Object[] table, int mask) {
    this.elements = elements;
    this.hashCode = hashCode;
    this.table = table;
    this.mask = mask;
  }
  
  public boolean contains(@CheckForNull Object target) {
    Object[] table = this.table;
    if (target == null || table.length == 0)
      return false; 
    for (int i = Hashing.smearedHash(target);; i++) {
      i &= this.mask;
      Object candidate = table[i];
      if (candidate == null)
        return false; 
      if (candidate.equals(target))
        return true; 
    } 
  }
  
  public int size() {
    return this.elements.length;
  }
  
  public UnmodifiableIterator<E> iterator() {
    return Iterators.forArray((E[])this.elements);
  }
  
  public Spliterator<E> spliterator() {
    return Spliterators.spliterator(this.elements, 1297);
  }
  
  Object[] internalArray() {
    return this.elements;
  }
  
  int internalArrayStart() {
    return 0;
  }
  
  int internalArrayEnd() {
    return this.elements.length;
  }
  
  int copyIntoArray(Object[] dst, int offset) {
    System.arraycopy(this.elements, 0, dst, offset, this.elements.length);
    return offset + this.elements.length;
  }
  
  ImmutableList<E> createAsList() {
    return (this.table.length == 0) ? 
      ImmutableList.<E>of() : 
      new RegularImmutableAsList<>(this, this.elements);
  }
  
  boolean isPartialView() {
    return false;
  }
  
  public int hashCode() {
    return this.hashCode;
  }
  
  boolean isHashCodeFast() {
    return true;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\RegularImmutableSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */