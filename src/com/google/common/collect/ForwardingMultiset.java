package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingMultiset<E> extends ForwardingCollection<E> implements Multiset<E> {
  public int count(@CheckForNull Object element) {
    return delegate().count(element);
  }
  
  @CanIgnoreReturnValue
  public int add(@ParametricNullness E element, int occurrences) {
    return delegate().add(element, occurrences);
  }
  
  @CanIgnoreReturnValue
  public int remove(@CheckForNull Object element, int occurrences) {
    return delegate().remove(element, occurrences);
  }
  
  public Set<E> elementSet() {
    return delegate().elementSet();
  }
  
  public Set<Entry<E>> entrySet() {
    return delegate().entrySet();
  }
  
  public boolean equals(@CheckForNull Object object) {
    return (object == this || delegate().equals(object));
  }
  
  public int hashCode() {
    return delegate().hashCode();
  }
  
  @CanIgnoreReturnValue
  public int setCount(@ParametricNullness E element, int count) {
    return delegate().setCount(element, count);
  }
  
  @CanIgnoreReturnValue
  public boolean setCount(@ParametricNullness E element, int oldCount, int newCount) {
    return delegate().setCount(element, oldCount, newCount);
  }
  
  protected boolean standardContains(@CheckForNull Object object) {
    return (count(object) > 0);
  }
  
  protected void standardClear() {
    Iterators.clear(entrySet().iterator());
  }
  
  @Beta
  protected int standardCount(@CheckForNull Object object) {
    for (Entry<?> entry : entrySet()) {
      if (Objects.equal(entry.getElement(), object))
        return entry.getCount(); 
    } 
    return 0;
  }
  
  protected boolean standardAdd(@ParametricNullness E element) {
    add(element, 1);
    return true;
  }
  
  @Beta
  protected boolean standardAddAll(Collection<? extends E> elementsToAdd) {
    return Multisets.addAllImpl(this, elementsToAdd);
  }
  
  protected boolean standardRemove(@CheckForNull Object element) {
    return (remove(element, 1) > 0);
  }
  
  protected boolean standardRemoveAll(Collection<?> elementsToRemove) {
    return Multisets.removeAllImpl(this, elementsToRemove);
  }
  
  protected boolean standardRetainAll(Collection<?> elementsToRetain) {
    return Multisets.retainAllImpl(this, elementsToRetain);
  }
  
  protected int standardSetCount(@ParametricNullness E element, int count) {
    return Multisets.setCountImpl(this, element, count);
  }
  
  protected boolean standardSetCount(@ParametricNullness E element, int oldCount, int newCount) {
    return Multisets.setCountImpl(this, element, oldCount, newCount);
  }
  
  @Beta
  protected class StandardElementSet extends Multisets.ElementSet<E> {
    Multiset<E> multiset() {
      return ForwardingMultiset.this;
    }
    
    public Iterator<E> iterator() {
      return Multisets.elementIterator(multiset().entrySet().iterator());
    }
  }
  
  protected Iterator<E> standardIterator() {
    return Multisets.iteratorImpl(this);
  }
  
  protected int standardSize() {
    return Multisets.linearTimeSizeImpl(this);
  }
  
  protected boolean standardEquals(@CheckForNull Object object) {
    return Multisets.equalsImpl(this, object);
  }
  
  protected int standardHashCode() {
    return entrySet().hashCode();
  }
  
  protected String standardToString() {
    return entrySet().toString();
  }
  
  protected abstract Multiset<E> delegate();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ForwardingMultiset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */