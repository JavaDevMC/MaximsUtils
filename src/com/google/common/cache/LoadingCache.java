package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface LoadingCache<K, V> extends Cache<K, V>, Function<K, V> {
  V get(K paramK) throws ExecutionException;
  
  V getUnchecked(K paramK);
  
  ImmutableMap<K, V> getAll(Iterable<? extends K> paramIterable) throws ExecutionException;
  
  @Deprecated
  V apply(K paramK);
  
  void refresh(K paramK);
  
  ConcurrentMap<K, V> asMap();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\cache\LoadingCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */