package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(emulated = true)
final class UnmodifiableSortedMultiset<E> extends Multisets.UnmodifiableMultiset<E> implements SortedMultiset<E> {
  @CheckForNull
  private transient UnmodifiableSortedMultiset<E> descendingMultiset;
  
  private static final long serialVersionUID = 0L;
  
  UnmodifiableSortedMultiset(SortedMultiset<E> delegate) {
    super(delegate);
  }
  
  protected SortedMultiset<E> delegate() {
    return (SortedMultiset<E>)super.delegate();
  }
  
  public Comparator<? super E> comparator() {
    return delegate().comparator();
  }
  
  NavigableSet<E> createElementSet() {
    return Sets.unmodifiableNavigableSet(delegate().elementSet());
  }
  
  public NavigableSet<E> elementSet() {
    return (NavigableSet<E>)super.elementSet();
  }
  
  public SortedMultiset<E> descendingMultiset() {
    UnmodifiableSortedMultiset<E> result = this.descendingMultiset;
    if (result == null) {
      result = new UnmodifiableSortedMultiset(delegate().descendingMultiset());
      result.descendingMultiset = this;
      return this.descendingMultiset = result;
    } 
    return result;
  }
  
  @CheckForNull
  public Multiset.Entry<E> firstEntry() {
    return delegate().firstEntry();
  }
  
  @CheckForNull
  public Multiset.Entry<E> lastEntry() {
    return delegate().lastEntry();
  }
  
  @CheckForNull
  public Multiset.Entry<E> pollFirstEntry() {
    throw new UnsupportedOperationException();
  }
  
  @CheckForNull
  public Multiset.Entry<E> pollLastEntry() {
    throw new UnsupportedOperationException();
  }
  
  public SortedMultiset<E> headMultiset(@ParametricNullness E upperBound, BoundType boundType) {
    return Multisets.unmodifiableSortedMultiset(delegate().headMultiset(upperBound, boundType));
  }
  
  public SortedMultiset<E> subMultiset(@ParametricNullness E lowerBound, BoundType lowerBoundType, @ParametricNullness E upperBound, BoundType upperBoundType) {
    return Multisets.unmodifiableSortedMultiset(
        delegate().subMultiset(lowerBound, lowerBoundType, upperBound, upperBoundType));
  }
  
  public SortedMultiset<E> tailMultiset(@ParametricNullness E lowerBound, BoundType boundType) {
    return Multisets.unmodifiableSortedMultiset(delegate().tailMultiset(lowerBound, boundType));
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\UnmodifiableSortedMultiset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */