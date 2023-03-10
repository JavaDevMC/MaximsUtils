package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractMultiset<E> extends AbstractCollection<E> implements Multiset<E> {
  @LazyInit
  @CheckForNull
  private transient Set<E> elementSet;
  
  @LazyInit
  @CheckForNull
  private transient Set<Entry<E>> entrySet;
  
  public boolean isEmpty() {
    return entrySet().isEmpty();
  }
  
  public boolean contains(@CheckForNull Object element) {
    return (count(element) > 0);
  }
  
  @CanIgnoreReturnValue
  public final boolean add(@ParametricNullness E element) {
    add(element, 1);
    return true;
  }
  
  @CanIgnoreReturnValue
  public int add(@ParametricNullness E element, int occurrences) {
    throw new UnsupportedOperationException();
  }
  
  @CanIgnoreReturnValue
  public final boolean remove(@CheckForNull Object element) {
    return (remove(element, 1) > 0);
  }
  
  @CanIgnoreReturnValue
  public int remove(@CheckForNull Object element, int occurrences) {
    throw new UnsupportedOperationException();
  }
  
  @CanIgnoreReturnValue
  public int setCount(@ParametricNullness E element, int count) {
    return Multisets.setCountImpl(this, element, count);
  }
  
  @CanIgnoreReturnValue
  public boolean setCount(@ParametricNullness E element, int oldCount, int newCount) {
    return Multisets.setCountImpl(this, element, oldCount, newCount);
  }
  
  @CanIgnoreReturnValue
  public final boolean addAll(Collection<? extends E> elementsToAdd) {
    return Multisets.addAllImpl(this, elementsToAdd);
  }
  
  @CanIgnoreReturnValue
  public final boolean removeAll(Collection<?> elementsToRemove) {
    return Multisets.removeAllImpl(this, elementsToRemove);
  }
  
  @CanIgnoreReturnValue
  public final boolean retainAll(Collection<?> elementsToRetain) {
    return Multisets.retainAllImpl(this, elementsToRetain);
  }
  
  public abstract void clear();
  
  public Set<E> elementSet() {
    Set<E> result = this.elementSet;
    if (result == null)
      this.elementSet = result = createElementSet(); 
    return result;
  }
  
  Set<E> createElementSet() {
    return new ElementSet();
  }
  
  abstract Iterator<E> elementIterator();
  
  class ElementSet extends Multisets.ElementSet<E> {
    Multiset<E> multiset() {
      return AbstractMultiset.this;
    }
    
    public Iterator<E> iterator() {
      return AbstractMultiset.this.elementIterator();
    }
  }
  
  public Set<Entry<E>> entrySet() {
    Set<Entry<E>> result = this.entrySet;
    if (result == null)
      this.entrySet = result = createEntrySet(); 
    return result;
  }
  
  class EntrySet extends Multisets.EntrySet<E> {
    Multiset<E> multiset() {
      return AbstractMultiset.this;
    }
    
    public Iterator<Entry<E>> iterator() {
      return AbstractMultiset.this.entryIterator();
    }
    
    public int size() {
      return AbstractMultiset.this.distinctElements();
    }
  }
  
  Set<Entry<E>> createEntrySet() {
    return new EntrySet();
  }
  
  abstract Iterator<Entry<E>> entryIterator();
  
  abstract int distinctElements();
  
  public final boolean equals(@CheckForNull Object object) {
    return Multisets.equalsImpl(this, object);
  }
  
  public final int hashCode() {
    return entrySet().hashCode();
  }
  
  public final String toString() {
    return entrySet().toString();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\AbstractMultiset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */