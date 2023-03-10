package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingSetMultimap<K, V> extends ForwardingMultimap<K, V> implements SetMultimap<K, V> {
  public Set<Map.Entry<K, V>> entries() {
    return delegate().entries();
  }
  
  public Set<V> get(@ParametricNullness K key) {
    return delegate().get(key);
  }
  
  @CanIgnoreReturnValue
  public Set<V> removeAll(@CheckForNull Object key) {
    return delegate().removeAll(key);
  }
  
  @CanIgnoreReturnValue
  public Set<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
    return delegate().replaceValues(key, values);
  }
  
  protected abstract SetMultimap<K, V> delegate();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ForwardingSetMultimap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */