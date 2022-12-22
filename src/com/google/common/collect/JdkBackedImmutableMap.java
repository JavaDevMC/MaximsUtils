package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(emulated = true)
final class JdkBackedImmutableMap<K, V> extends ImmutableMap<K, V> {
  private final transient Map<K, V> delegateMap;
  
  private final transient ImmutableList<Entry<K, V>> entries;
  
  static <K, V> ImmutableMap<K, V> create(int n, Entry<K, V>[] entryArray) {
    Map<K, V> delegateMap = Maps.newHashMapWithExpectedSize(n);
    for (int i = 0; i < n; i++) {
      entryArray[i] = RegularImmutableMap.makeImmutable(Objects.<Entry<K, V>>requireNonNull(entryArray[i]));
      V oldValue = delegateMap.putIfAbsent(entryArray[i].getKey(), entryArray[i].getValue());
      if (oldValue != null) {
        String str1 = String.valueOf(entryArray[i].getKey()), str2 = String.valueOf(oldValue);
        throw conflictException("key", entryArray[i], (new StringBuilder(1 + String.valueOf(str1).length() + String.valueOf(str2).length())).append(str1).append("=").append(str2).toString());
      } 
    } 
    return new JdkBackedImmutableMap<>(delegateMap, ImmutableList.asImmutableList((Object[])entryArray, n));
  }
  
  JdkBackedImmutableMap(Map<K, V> delegateMap, ImmutableList<Entry<K, V>> entries) {
    this.delegateMap = delegateMap;
    this.entries = entries;
  }
  
  public int size() {
    return this.entries.size();
  }
  
  @CheckForNull
  public V get(@CheckForNull Object key) {
    return this.delegateMap.get(key);
  }
  
  ImmutableSet<Entry<K, V>> createEntrySet() {
    return new ImmutableMapEntrySet.RegularEntrySet<>(this, this.entries);
  }
  
  public void forEach(BiConsumer<? super K, ? super V> action) {
    Preconditions.checkNotNull(action);
    this.entries.forEach(e -> action.accept(e.getKey(), e.getValue()));
  }
  
  ImmutableSet<K> createKeySet() {
    return new ImmutableMapKeySet<>(this);
  }
  
  ImmutableCollection<V> createValues() {
    return new ImmutableMapValues<>(this);
  }
  
  boolean isPartialView() {
    return false;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\JdkBackedImmutableMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */