package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(emulated = true)
abstract class DescendingMultiset<E> extends ForwardingMultiset<E> implements SortedMultiset<E> {
  @CheckForNull
  private transient Comparator<? super E> comparator;
  
  @CheckForNull
  private transient NavigableSet<E> elementSet;
  
  @CheckForNull
  private transient Set<Entry<E>> entrySet;
  
  public Comparator<? super E> comparator() {
    Comparator<? super E> result = this.comparator;
    if (result == null)
      return this.comparator = Ordering.from(forwardMultiset().comparator()).<Object>reverse(); 
    return result;
  }
  
  public NavigableSet<E> elementSet() {
    NavigableSet<E> result = this.elementSet;
    if (result == null)
      return this.elementSet = new SortedMultisets.NavigableElementSet<>(this); 
    return result;
  }
  
  @CheckForNull
  public Multiset.Entry<E> pollFirstEntry() {
    return forwardMultiset().pollLastEntry();
  }
  
  @CheckForNull
  public Multiset.Entry<E> pollLastEntry() {
    return forwardMultiset().pollFirstEntry();
  }
  
  public SortedMultiset<E> headMultiset(@ParametricNullness E toElement, BoundType boundType) {
    return forwardMultiset().tailMultiset(toElement, boundType).descendingMultiset();
  }
  
  public SortedMultiset<E> subMultiset(@ParametricNullness E fromElement, BoundType fromBoundType, @ParametricNullness E toElement, BoundType toBoundType) {
    return forwardMultiset()
      .subMultiset(toElement, toBoundType, fromElement, fromBoundType)
      .descendingMultiset();
  }
  
  public SortedMultiset<E> tailMultiset(@ParametricNullness E fromElement, BoundType boundType) {
    return forwardMultiset().headMultiset(fromElement, boundType).descendingMultiset();
  }
  
  protected Multiset<E> delegate() {
    return forwardMultiset();
  }
  
  public SortedMultiset<E> descendingMultiset() {
    return forwardMultiset();
  }
  
  @CheckForNull
  public Multiset.Entry<E> firstEntry() {
    return forwardMultiset().lastEntry();
  }
  
  @CheckForNull
  public Multiset.Entry<E> lastEntry() {
    return forwardMultiset().firstEntry();
  }
  
  public Set<Entry<E>> entrySet() {
    Set<Entry<E>> result = this.entrySet;
    return (result == null) ? (this.entrySet = createEntrySet()) : result;
  }
  
  Set<Entry<E>> createEntrySet() {
    class EntrySetImpl extends Multisets.EntrySet<E> {
      Multiset<E> multiset() {
        return DescendingMultiset.this;
      }
      
      public Iterator<Entry<E>> iterator() {
        return DescendingMultiset.this.entryIterator();
      }
      
      public int size() {
        return DescendingMultiset.this.forwardMultiset().entrySet().size();
      }
    };
    return new EntrySetImpl();
  }
  
  public Iterator<E> iterator() {
    return Multisets.iteratorImpl(this);
  }
  
  public Object[] toArray() {
    return standardToArray();
  }
  
  public <T> T[] toArray(T[] array) {
    return (T[])standardToArray((Object[])array);
  }
  
  public String toString() {
    return entrySet().toString();
  }
  
  abstract SortedMultiset<E> forwardMultiset();
  
  abstract Iterator<Entry<E>> entryIterator();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\DescendingMultiset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */