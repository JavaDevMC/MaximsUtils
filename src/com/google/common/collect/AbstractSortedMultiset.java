package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(emulated = true)
abstract class AbstractSortedMultiset<E> extends AbstractMultiset<E> implements SortedMultiset<E> {
  @GwtTransient
  final Comparator<? super E> comparator;
  
  @CheckForNull
  private transient SortedMultiset<E> descendingMultiset;
  
  AbstractSortedMultiset() {
    this(Ordering.natural());
  }
  
  AbstractSortedMultiset(Comparator<? super E> comparator) {
    this.comparator = (Comparator<? super E>)Preconditions.checkNotNull(comparator);
  }
  
  public NavigableSet<E> elementSet() {
    return (NavigableSet<E>)super.elementSet();
  }
  
  NavigableSet<E> createElementSet() {
    return new SortedMultisets.NavigableElementSet<>(this);
  }
  
  public Comparator<? super E> comparator() {
    return this.comparator;
  }
  
  @CheckForNull
  public Multiset.Entry<E> firstEntry() {
    Iterator<Entry<E>> entryIterator = entryIterator();
    return entryIterator.hasNext() ? entryIterator.next() : null;
  }
  
  @CheckForNull
  public Multiset.Entry<E> lastEntry() {
    Iterator<Entry<E>> entryIterator = descendingEntryIterator();
    return entryIterator.hasNext() ? entryIterator.next() : null;
  }
  
  @CheckForNull
  public Multiset.Entry<E> pollFirstEntry() {
    Iterator<Entry<E>> entryIterator = entryIterator();
    if (entryIterator.hasNext()) {
      Entry<E> result = entryIterator.next();
      result = Multisets.immutableEntry(result.getElement(), result.getCount());
      entryIterator.remove();
      return result;
    } 
    return null;
  }
  
  @CheckForNull
  public Multiset.Entry<E> pollLastEntry() {
    Iterator<Entry<E>> entryIterator = descendingEntryIterator();
    if (entryIterator.hasNext()) {
      Entry<E> result = entryIterator.next();
      result = Multisets.immutableEntry(result.getElement(), result.getCount());
      entryIterator.remove();
      return result;
    } 
    return null;
  }
  
  public SortedMultiset<E> subMultiset(@ParametricNullness E fromElement, BoundType fromBoundType, @ParametricNullness E toElement, BoundType toBoundType) {
    Preconditions.checkNotNull(fromBoundType);
    Preconditions.checkNotNull(toBoundType);
    return tailMultiset(fromElement, fromBoundType).headMultiset(toElement, toBoundType);
  }
  
  Iterator<E> descendingIterator() {
    return Multisets.iteratorImpl(descendingMultiset());
  }
  
  public SortedMultiset<E> descendingMultiset() {
    SortedMultiset<E> result = this.descendingMultiset;
    return (result == null) ? (this.descendingMultiset = createDescendingMultiset()) : result;
  }
  
  SortedMultiset<E> createDescendingMultiset() {
    class DescendingMultisetImpl extends DescendingMultiset<E> {
      SortedMultiset<E> forwardMultiset() {
        return AbstractSortedMultiset.this;
      }
      
      Iterator<Entry<E>> entryIterator() {
        return AbstractSortedMultiset.this.descendingEntryIterator();
      }
      
      public Iterator<E> iterator() {
        return AbstractSortedMultiset.this.descendingIterator();
      }
    };
    return new DescendingMultisetImpl();
  }
  
  abstract Iterator<Entry<E>> descendingEntryIterator();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\AbstractSortedMultiset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */