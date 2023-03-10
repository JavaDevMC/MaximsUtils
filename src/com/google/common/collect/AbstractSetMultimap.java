package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractSetMultimap<K, V> extends AbstractMapBasedMultimap<K, V> implements SetMultimap<K, V> {
  private static final long serialVersionUID = 7431625294878419160L;
  
  protected AbstractSetMultimap(Map<K, Collection<V>> map) {
    super(map);
  }
  
  Set<V> createUnmodifiableEmptyCollection() {
    return Collections.emptySet();
  }
  
  <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
    return Collections.unmodifiableSet((Set<? extends E>)collection);
  }
  
  Collection<V> wrapCollection(@ParametricNullness K key, Collection<V> collection) {
    return new WrappedSet(this, key, (Set<V>)collection);
  }
  
  public Set<V> get(@ParametricNullness K key) {
    return (Set<V>)super.get(key);
  }
  
  public Set<Map.Entry<K, V>> entries() {
    return (Set<Map.Entry<K, V>>)super.entries();
  }
  
  @CanIgnoreReturnValue
  public Set<V> removeAll(@CheckForNull Object key) {
    return (Set<V>)super.removeAll(key);
  }
  
  @CanIgnoreReturnValue
  public Set<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
    return (Set<V>)super.replaceValues(key, values);
  }
  
  public Map<K, Collection<V>> asMap() {
    return super.asMap();
  }
  
  @CanIgnoreReturnValue
  public boolean put(@ParametricNullness K key, @ParametricNullness V value) {
    return super.put(key, value);
  }
  
  public boolean equals(@CheckForNull Object object) {
    return super.equals(object);
  }
  
  abstract Set<V> createCollection();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\AbstractSetMultimap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */