package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractSortedSetMultimap<K, V> extends AbstractSetMultimap<K, V> implements SortedSetMultimap<K, V> {
  private static final long serialVersionUID = 430848587173315748L;
  
  protected AbstractSortedSetMultimap(Map<K, Collection<V>> map) {
    super(map);
  }
  
  SortedSet<V> createUnmodifiableEmptyCollection() {
    return unmodifiableCollectionSubclass(createCollection());
  }
  
  <E> SortedSet<E> unmodifiableCollectionSubclass(Collection<E> collection) {
    if (collection instanceof NavigableSet)
      return Sets.unmodifiableNavigableSet((NavigableSet<E>)collection); 
    return Collections.unmodifiableSortedSet((SortedSet<E>)collection);
  }
  
  Collection<V> wrapCollection(@ParametricNullness K key, Collection<V> collection) {
    if (collection instanceof NavigableSet)
      return new WrappedNavigableSet(this, key, (NavigableSet<V>)collection, null);
    return new WrappedSortedSet(this, key, (SortedSet<V>)collection, null);
  }
  
  public SortedSet<V> get(@ParametricNullness K key) {
    return (SortedSet<V>)super.get(key);
  }
  
  @CanIgnoreReturnValue
  public SortedSet<V> removeAll(@CheckForNull Object key) {
    return (SortedSet<V>)super.removeAll(key);
  }
  
  @CanIgnoreReturnValue
  public SortedSet<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
    return (SortedSet<V>)super.replaceValues(key, values);
  }
  
  public Map<K, Collection<V>> asMap() {
    return super.asMap();
  }
  
  public Collection<V> values() {
    return super.values();
  }
  
  abstract SortedSet<V> createCollection();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\AbstractSortedSetMultimap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */