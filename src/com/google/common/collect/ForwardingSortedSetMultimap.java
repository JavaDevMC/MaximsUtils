package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingSortedSetMultimap<K, V> extends ForwardingSetMultimap<K, V> implements SortedSetMultimap<K, V> {
  public SortedSet<V> get(@ParametricNullness K key) {
    return delegate().get(key);
  }
  
  public SortedSet<V> removeAll(@CheckForNull Object key) {
    return delegate().removeAll(key);
  }
  
  public SortedSet<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
    return delegate().replaceValues(key, values);
  }
  
  @CheckForNull
  public Comparator<? super V> valueComparator() {
    return delegate().valueComparator();
  }
  
  protected abstract SortedSetMultimap<K, V> delegate();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ForwardingSortedSetMultimap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */