package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
@GwtCompatible(emulated = true)
public abstract class ForwardingSortedMultiset<E> extends ForwardingMultiset<E> implements SortedMultiset<E> {
  public NavigableSet<E> elementSet() {
    return delegate().elementSet();
  }
  
  protected class StandardElementSet extends SortedMultisets.NavigableElementSet<E> {
    public StandardElementSet(ForwardingSortedMultiset<E> this$0) {
      super(this$0);
    }
  }
  
  public Comparator<? super E> comparator() {
    return delegate().comparator();
  }
  
  public SortedMultiset<E> descendingMultiset() {
    return delegate().descendingMultiset();
  }
  
  protected abstract class StandardDescendingMultiset extends DescendingMultiset<E> {
    SortedMultiset<E> forwardMultiset() {
      return ForwardingSortedMultiset.this;
    }
  }
  
  @CheckForNull
  public Multiset.Entry<E> firstEntry() {
    return delegate().firstEntry();
  }
  
  @CheckForNull
  protected Multiset.Entry<E> standardFirstEntry() {
    Iterator<Entry<E>> entryIterator = entrySet().iterator();
    if (!entryIterator.hasNext())
      return null; 
    Entry<E> entry = entryIterator.next();
    return Multisets.immutableEntry(entry.getElement(), entry.getCount());
  }
  
  @CheckForNull
  public Multiset.Entry<E> lastEntry() {
    return delegate().lastEntry();
  }
  
  @CheckForNull
  protected Multiset.Entry<E> standardLastEntry() {
    Iterator<Entry<E>> entryIterator = descendingMultiset().entrySet().iterator();
    if (!entryIterator.hasNext())
      return null; 
    Entry<E> entry = entryIterator.next();
    return Multisets.immutableEntry(entry.getElement(), entry.getCount());
  }
  
  @CheckForNull
  public Multiset.Entry<E> pollFirstEntry() {
    return delegate().pollFirstEntry();
  }
  
  @CheckForNull
  protected Multiset.Entry<E> standardPollFirstEntry() {
    Iterator<Entry<E>> entryIterator = entrySet().iterator();
    if (!entryIterator.hasNext())
      return null; 
    Entry<E> entry = entryIterator.next();
    entry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
    entryIterator.remove();
    return entry;
  }
  
  @CheckForNull
  public Multiset.Entry<E> pollLastEntry() {
    return delegate().pollLastEntry();
  }
  
  @CheckForNull
  protected Multiset.Entry<E> standardPollLastEntry() {
    Iterator<Entry<E>> entryIterator = descendingMultiset().entrySet().iterator();
    if (!entryIterator.hasNext())
      return null; 
    Entry<E> entry = entryIterator.next();
    entry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
    entryIterator.remove();
    return entry;
  }
  
  public SortedMultiset<E> headMultiset(@ParametricNullness E upperBound, BoundType boundType) {
    return delegate().headMultiset(upperBound, boundType);
  }
  
  public SortedMultiset<E> subMultiset(@ParametricNullness E lowerBound, BoundType lowerBoundType, @ParametricNullness E upperBound, BoundType upperBoundType) {
    return delegate().subMultiset(lowerBound, lowerBoundType, upperBound, upperBoundType);
  }
  
  protected SortedMultiset<E> standardSubMultiset(@ParametricNullness E lowerBound, BoundType lowerBoundType, @ParametricNullness E upperBound, BoundType upperBoundType) {
    return tailMultiset(lowerBound, lowerBoundType).headMultiset(upperBound, upperBoundType);
  }
  
  public SortedMultiset<E> tailMultiset(@ParametricNullness E lowerBound, BoundType boundType) {
    return delegate().tailMultiset(lowerBound, boundType);
  }
  
  protected abstract SortedMultiset<E> delegate();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ForwardingSortedMultiset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */