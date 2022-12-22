package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(serializable = true, emulated = true)
final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
  static final ImmutableMap<Object, Object> EMPTY = new RegularImmutableMap((Entry[])ImmutableMap.EMPTY_ENTRY_ARRAY, null, 0);
  
  @VisibleForTesting
  static final double MAX_LOAD_FACTOR = 1.2D;
  
  @VisibleForTesting
  static final double HASH_FLOODING_FPP = 0.001D;
  
  @VisibleForTesting
  static final int MAX_HASH_BUCKET_LENGTH = 8;
  
  @VisibleForTesting
  final transient Entry<K, V>[] entries;
  
  @CheckForNull
  private final transient ImmutableMapEntry<K, V>[] table;
  
  private final transient int mask;
  
  private static final long serialVersionUID = 0L;
  
  static <K, V> ImmutableMap<K, V> fromEntries(Entry<K, V>... entries) {
    return fromEntryArray(entries.length, entries);
  }
  
  static <K, V> ImmutableMap<K, V> fromEntryArray(int n, Entry<K, V>[] entryArray) {
    Preconditions.checkPositionIndex(n, entryArray.length);
    if (n == 0)
      return (RegularImmutableMap)EMPTY; 
    Entry<K, V>[] entries = (n == entryArray.length) ? entryArray : (Entry[])ImmutableMapEntry.createEntryArray(n);
    int tableSize = Hashing.closedTableSize(n, 1.2D);
    ImmutableMapEntry[] arrayOfImmutableMapEntry = (ImmutableMapEntry[])ImmutableMapEntry.createEntryArray(tableSize);
    int mask = tableSize - 1;
    for (int entryIndex = 0; entryIndex < n; entryIndex++) {
      Entry<K, V> entry = Objects.<Entry<K, V>>requireNonNull(entryArray[entryIndex]);
      K key = entry.getKey();
      V value = entry.getValue();
      CollectPreconditions.checkEntryNotNull(key, value);
      int tableIndex = Hashing.smear(key.hashCode()) & mask;
      ImmutableMapEntry<K, V> existing = arrayOfImmutableMapEntry[tableIndex];
      ImmutableMapEntry<K, V> newEntry = (existing == null) ? makeImmutable(entry, key, value) : new ImmutableMapEntry.NonTerminalImmutableMapEntry<>(key, value, existing);
      arrayOfImmutableMapEntry[tableIndex] = newEntry;
      entries[entryIndex] = newEntry;
      int bucketSize = checkNoConflictInKeyBucket(key, newEntry, existing);
      if (bucketSize > 8)
        return JdkBackedImmutableMap.create(n, entryArray); 
    } 
    return new RegularImmutableMap<>(entries, (ImmutableMapEntry<K, V>[])arrayOfImmutableMapEntry, mask);
  }
  
  static <K, V> ImmutableMapEntry<K, V> makeImmutable(Entry<K, V> entry, K key, V value) {
    boolean reusable = (entry instanceof ImmutableMapEntry && ((ImmutableMapEntry)entry).isReusable());
    return reusable ? (ImmutableMapEntry<K, V>)entry : new ImmutableMapEntry<>(key, value);
  }
  
  static <K, V> ImmutableMapEntry<K, V> makeImmutable(Entry<K, V> entry) {
    return makeImmutable(entry, entry.getKey(), entry.getValue());
  }
  
  private RegularImmutableMap(Entry<K, V>[] entries, @CheckForNull ImmutableMapEntry<K, V>[] table, int mask) {
    this.entries = entries;
    this.table = table;
    this.mask = mask;
  }
  
  @CanIgnoreReturnValue
  static int checkNoConflictInKeyBucket(Object key, Entry<?, ?> entry, @CheckForNull ImmutableMapEntry<?, ?> keyBucketHead) {
    int bucketSize = 0;
    for (; keyBucketHead != null; keyBucketHead = keyBucketHead.getNextInKeyBucket()) {
      checkNoConflict(!key.equals(keyBucketHead.getKey()), "key", entry, keyBucketHead);
      bucketSize++;
    } 
    return bucketSize;
  }
  
  @CheckForNull
  public V get(@CheckForNull Object key) {
    return get(key, (ImmutableMapEntry<?, V>[])this.table, this.mask);
  }
  
  @CheckForNull
  static <V> V get(@CheckForNull Object key, @CheckForNull ImmutableMapEntry<?, V>[] keyTable, int mask) {
    if (key == null || keyTable == null)
      return null; 
    int index = Hashing.smear(key.hashCode()) & mask;
    ImmutableMapEntry<?, V> entry = keyTable[index];
    for (; entry != null; 
      entry = entry.getNextInKeyBucket()) {
      Object candidateKey = entry.getKey();
      if (key.equals(candidateKey))
        return entry.getValue(); 
    } 
    return null;
  }
  
  public void forEach(BiConsumer<? super K, ? super V> action) {
    Preconditions.checkNotNull(action);
    for (Entry<K, V> entry : this.entries)
      action.accept(entry.getKey(), entry.getValue()); 
  }
  
  public int size() {
    return this.entries.length;
  }
  
  boolean isPartialView() {
    return false;
  }
  
  ImmutableSet<Entry<K, V>> createEntrySet() {
    return new ImmutableMapEntrySet.RegularEntrySet<>(this, this.entries);
  }
  
  ImmutableSet<K> createKeySet() {
    return new KeySet<>(this);
  }
  
  @GwtCompatible(emulated = true)
  private static final class KeySet<K> extends IndexedImmutableSet<K> {
    private final RegularImmutableMap<K, ?> map;
    
    KeySet(RegularImmutableMap<K, ?> map) {
      this.map = map;
    }
    
    K get(int index) {
      return this.map.entries[index].getKey();
    }
    
    public boolean contains(@CheckForNull Object object) {
      return this.map.containsKey(object);
    }
    
    boolean isPartialView() {
      return true;
    }
    
    public int size() {
      return this.map.size();
    }
    
    @GwtIncompatible
    private static class SerializedForm<K> implements Serializable {
      final ImmutableMap<K, ?> map;
      
      private static final long serialVersionUID = 0L;
      
      SerializedForm(ImmutableMap<K, ?> map) {
        this.map = map;
      }
      
      Object readResolve() {
        return this.map.keySet();
      }
    }
  }
  
  ImmutableCollection<V> createValues() {
    return new Values<>(this);
  }
  
  @GwtCompatible(emulated = true)
  private static final class Values<K, V> extends ImmutableList<V> {
    final RegularImmutableMap<K, V> map;
    
    Values(RegularImmutableMap<K, V> map) {
      this.map = map;
    }
    
    public V get(int index) {
      return this.map.entries[index].getValue();
    }
    
    public int size() {
      return this.map.size();
    }
    
    boolean isPartialView() {
      return true;
    }
    
    @GwtIncompatible
    private static class SerializedForm<V> implements Serializable {
      final ImmutableMap<?, V> map;
      
      private static final long serialVersionUID = 0L;
      
      SerializedForm(ImmutableMap<?, V> map) {
        this.map = map;
      }
      
      Object readResolve() {
        return this.map.values();
      }
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\RegularImmutableMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */