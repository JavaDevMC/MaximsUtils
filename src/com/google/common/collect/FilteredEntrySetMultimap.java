package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class FilteredEntrySetMultimap<K, V> extends FilteredEntryMultimap<K, V> implements FilteredSetMultimap<K, V> {
  FilteredEntrySetMultimap(SetMultimap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> predicate) {
    super(unfiltered, predicate);
  }
  
  public SetMultimap<K, V> unfiltered() {
    return (SetMultimap<K, V>)this.unfiltered;
  }
  
  public Set<V> get(@ParametricNullness K key) {
    return (Set<V>)super.get(key);
  }
  
  public Set<V> removeAll(@CheckForNull Object key) {
    return (Set<V>)super.removeAll(key);
  }
  
  public Set<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
    return (Set<V>)super.replaceValues(key, values);
  }
  
  Set<Map.Entry<K, V>> createEntries() {
    return Sets.filter(unfiltered().entries(), entryPredicate());
  }
  
  public Set<Map.Entry<K, V>> entries() {
    return (Set<Map.Entry<K, V>>)super.entries();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\FilteredEntrySetMultimap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */