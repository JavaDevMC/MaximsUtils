package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.DoNotMock;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;

@DoNotMock("Use ImmutableMap.of or another implementation")
@ElementTypesAreNonnullByDefault
@GwtCompatible(serializable = true, emulated = true)
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {
  public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
    return CollectCollectors.toImmutableMap(keyFunction, valueFunction);
  }
  
  public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction) {
    return CollectCollectors.toImmutableMap(keyFunction, valueFunction, mergeFunction);
  }
  
  public static <K, V> ImmutableMap<K, V> of() {
    return (ImmutableMap)RegularImmutableMap.EMPTY;
  }
  
  public static <K, V> ImmutableMap<K, V> of(K k1, V v1) {
    return ImmutableBiMap.of(k1, v1);
  }
  
  public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2) {
    return RegularImmutableMap.fromEntries((Entry<K, V>[])new Entry[] { entryOf(k1, v1), entryOf(k2, v2) });
  }
  
  public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
    return RegularImmutableMap.fromEntries((Entry<K, V>[])new Entry[] { entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3) });
  }
  
  public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
    return RegularImmutableMap.fromEntries((Entry<K, V>[])new Entry[] { entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4) });
  }
  
  public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
    return RegularImmutableMap.fromEntries((Entry<K, V>[])new Entry[] { entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5) });
  }
  
  public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
    return RegularImmutableMap.fromEntries((Entry<K, V>[])new Entry[] { entryOf(k1, v1),
          entryOf(k2, v2), 
          entryOf(k3, v3), 
          entryOf(k4, v4), 
          entryOf(k5, v5), 
          entryOf(k6, v6) });
  }
  
  public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
    return RegularImmutableMap.fromEntries((Entry<K, V>[])new Entry[] { entryOf(k1, v1),
          entryOf(k2, v2), 
          entryOf(k3, v3), 
          entryOf(k4, v4), 
          entryOf(k5, v5), 
          entryOf(k6, v6), 
          entryOf(k7, v7) });
  }
  
  public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8) {
    return RegularImmutableMap.fromEntries((Entry<K, V>[])new Entry[] { entryOf(k1, v1),
          entryOf(k2, v2), 
          entryOf(k3, v3), 
          entryOf(k4, v4), 
          entryOf(k5, v5), 
          entryOf(k6, v6), 
          entryOf(k7, v7), 
          entryOf(k8, v8) });
  }
  
  public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) {
    return RegularImmutableMap.fromEntries((Entry<K, V>[])new Entry[] { entryOf(k1, v1),
          entryOf(k2, v2), 
          entryOf(k3, v3), 
          entryOf(k4, v4), 
          entryOf(k5, v5), 
          entryOf(k6, v6), 
          entryOf(k7, v7), 
          entryOf(k8, v8), 
          entryOf(k9, v9) });
  }
  
  public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) {
    return RegularImmutableMap.fromEntries((Entry<K, V>[])new Entry[] { entryOf(k1, v1),
          entryOf(k2, v2), 
          entryOf(k3, v3), 
          entryOf(k4, v4), 
          entryOf(k5, v5), 
          entryOf(k6, v6), 
          entryOf(k7, v7), 
          entryOf(k8, v8), 
          entryOf(k9, v9), 
          entryOf(k10, v10) });
  }
  
  @SafeVarargs
  public static <K, V> ImmutableMap<K, V> ofEntries(Entry<? extends K, ? extends V>... entries) {
    Entry<? extends K, ? extends V>[] arrayOfEntry = entries;
    return RegularImmutableMap.fromEntries((Entry[])arrayOfEntry);
  }
  
  static <K, V> Entry<K, V> entryOf(K key, V value) {
    CollectPreconditions.checkEntryNotNull(key, value);
    return new AbstractMap.SimpleImmutableEntry<>(key, value);
  }
  
  public static <K, V> Builder<K, V> builder() {
    return new Builder<>();
  }
  
  @Beta
  public static <K, V> Builder<K, V> builderWithExpectedSize(int expectedSize) {
    CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
    return new Builder<>(expectedSize);
  }
  
  static void checkNoConflict(boolean safe, String conflictDescription, Entry<?, ?> entry1, Entry<?, ?> entry2) {
    if (!safe)
      throw conflictException(conflictDescription, entry1, entry2); 
  }
  
  static IllegalArgumentException conflictException(String conflictDescription, Object entry1, Object entry2) {
    String str1 = String.valueOf(entry1), str2 = String.valueOf(entry2);
    return new IllegalArgumentException((new StringBuilder(34 + String.valueOf(conflictDescription).length() + String.valueOf(str1).length() + String.valueOf(str2).length())).append("Multiple entries with same ").append(conflictDescription).append(": ").append(str1).append(" and ").append(str2).toString());
  }
  
  @DoNotMock
  public static class Builder<K, V> {
    @CheckForNull
    Comparator<? super V> valueComparator;
    
    Entry<K, V>[] entries;
    
    int size;
    
    boolean entriesUsed;
    
    public Builder() {
      this(4);
    }
    
    Builder(int initialCapacity) {
      this.entries = (Entry<K, V>[])new Entry[initialCapacity];
      this.size = 0;
      this.entriesUsed = false;
    }
    
    private void ensureCapacity(int minCapacity) {
      if (minCapacity > this.entries.length) {
        this
          .entries = Arrays.<Entry<K, V>>copyOf(this.entries,
            ImmutableCollection.Builder.expandedCapacity(this.entries.length, minCapacity));
        this.entriesUsed = false;
      } 
    }
    
    @CanIgnoreReturnValue
    public Builder<K, V> put(K key, V value) {
      ensureCapacity(this.size + 1);
      Entry<K, V> entry = ImmutableMap.entryOf(key, value);
      this.entries[this.size++] = entry;
      return this;
    }
    
    @CanIgnoreReturnValue
    public Builder<K, V> put(Entry<? extends K, ? extends V> entry) {
      return put(entry.getKey(), entry.getValue());
    }
    
    @CanIgnoreReturnValue
    public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
      return putAll(map.entrySet());
    }
    
    @CanIgnoreReturnValue
    @Beta
    public Builder<K, V> putAll(Iterable<? extends Entry<? extends K, ? extends V>> entries) {
      if (entries instanceof Collection)
        ensureCapacity(this.size + ((Collection)entries).size()); 
      for (Entry<? extends K, ? extends V> entry : entries)
        put(entry); 
      return this;
    }
    
    @CanIgnoreReturnValue
    @Beta
    public Builder<K, V> orderEntriesByValue(Comparator<? super V> valueComparator) {
      Preconditions.checkState((this.valueComparator == null), "valueComparator was already set");
      this.valueComparator = (Comparator<? super V>)Preconditions.checkNotNull(valueComparator, "valueComparator");
      return this;
    }
    
    @CanIgnoreReturnValue
    Builder<K, V> combine(Builder<K, V> other) {
      Preconditions.checkNotNull(other);
      ensureCapacity(this.size + other.size);
      System.arraycopy(other.entries, 0, this.entries, this.size, other.size);
      this.size += other.size;
      return this;
    }
    
    public ImmutableMap<K, V> build() {
      return buildOrThrow();
    }
    
    public ImmutableMap<K, V> buildOrThrow() {
      Entry<K, V> onlyEntry;
      if (this.valueComparator != null) {
        if (this.entriesUsed)
          this.entries = Arrays.<Entry<K, V>>copyOf(this.entries, this.size);
        Arrays.sort(this.entries, 0, this.size, 
            Ordering.<V>from(this.valueComparator).onResultOf(Maps.valueFunction()));
      } 
      switch (this.size) {
        case 0:
          return ImmutableMap.of();
        case 1:
          onlyEntry = Objects.<Entry<K, V>>requireNonNull(this.entries[0]);
          return ImmutableMap.of(onlyEntry.getKey(), onlyEntry.getValue());
      } 
      this.entriesUsed = true;
      return RegularImmutableMap.fromEntryArray(this.size, this.entries);
    }
    
    @VisibleForTesting
    ImmutableMap<K, V> buildJdkBacked() {
      Entry<K, V> onlyEntry;
      Preconditions.checkState((this.valueComparator == null), "buildJdkBacked is only for testing; can't use valueComparator");
      switch (this.size) {
        case 0:
          return ImmutableMap.of();
        case 1:
          onlyEntry = Objects.<Entry<K, V>>requireNonNull(this.entries[0]);
          return ImmutableMap.of(onlyEntry.getKey(), onlyEntry.getValue());
      } 
      this.entriesUsed = true;
      return JdkBackedImmutableMap.create(this.size, this.entries);
    }
  }
  
  public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
    if (map instanceof ImmutableMap && !(map instanceof java.util.SortedMap)) {
      ImmutableMap<K, V> kvMap = (ImmutableMap)map;
      if (!kvMap.isPartialView())
        return kvMap; 
    } else if (map instanceof EnumMap) {
      ImmutableMap<K, V> kvMap = (ImmutableMap)copyOfEnumMap((EnumMap)map);
      return kvMap;
    } 
    return copyOf(map.entrySet());
  }
  
  @Beta
  public static <K, V> ImmutableMap<K, V> copyOf(Iterable<? extends Entry<? extends K, ? extends V>> entries) {
    Entry<K, V> onlyEntry;
    Entry[] arrayOfEntry = Iterables.<Entry>toArray((Iterable)entries, (Entry[])EMPTY_ENTRY_ARRAY);
    switch (arrayOfEntry.length) {
      case 0:
        return of();
      case 1:
        onlyEntry = Objects.<Entry<K, V>>requireNonNull(arrayOfEntry[0]);
        return of(onlyEntry.getKey(), onlyEntry.getValue());
    } 
    return RegularImmutableMap.fromEntries((Entry<K, V>[])arrayOfEntry);
  }
  
  private static <K extends Enum<K>, V> ImmutableMap<K, V> copyOfEnumMap(EnumMap<K, ? extends V> original) {
    EnumMap<K, V> copy = new EnumMap<>(original);
    for (Entry<K, V> entry : copy.entrySet())
      CollectPreconditions.checkEntryNotNull(entry.getKey(), entry.getValue()); 
    return ImmutableEnumMap.asImmutable(copy);
  }
  
  static final Entry<?, ?>[] EMPTY_ENTRY_ARRAY = (Entry<?, ?>[])new Entry[0];
  
  @LazyInit
  @CheckForNull
  @RetainedWith
  private transient ImmutableSet<Entry<K, V>> entrySet;
  
  @LazyInit
  @CheckForNull
  @RetainedWith
  private transient ImmutableSet<K> keySet;
  
  @LazyInit
  @CheckForNull
  @RetainedWith
  private transient ImmutableCollection<V> values;
  
  @LazyInit
  @CheckForNull
  private transient ImmutableSetMultimap<K, V> multimapView;
  
  static abstract class IteratorBasedImmutableMap<K, V> extends ImmutableMap<K, V> {
    Spliterator<Entry<K, V>> entrySpliterator() {
      return Spliterators.spliterator(
          entryIterator(), 
          size(), 1297);
    }
    
    ImmutableSet<K> createKeySet() {
      return new ImmutableMapKeySet<>(this);
    }
    
    ImmutableSet<Entry<K, V>> createEntrySet() {
      class EntrySetImpl extends ImmutableMapEntrySet<K, V> {
        ImmutableMap<K, V> map() {
          return IteratorBasedImmutableMap.this;
        }
        
        public UnmodifiableIterator<Entry<K, V>> iterator() {
          return IteratorBasedImmutableMap.this.entryIterator();
        }
      };
      return new EntrySetImpl();
    }
    
    ImmutableCollection<V> createValues() {
      return new ImmutableMapValues<>(this);
    }
    
    abstract UnmodifiableIterator<Entry<K, V>> entryIterator();
  }
  
  @Deprecated
  @CheckForNull
  @CanIgnoreReturnValue
  @DoNotCall("Always throws UnsupportedOperationException")
  public final V put(K k, V v) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @CheckForNull
  @CanIgnoreReturnValue
  @DoNotCall("Always throws UnsupportedOperationException")
  public final V putIfAbsent(K key, V value) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final boolean replace(K key, V oldValue, V newValue) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @CheckForNull
  @DoNotCall("Always throws UnsupportedOperationException")
  public final V replace(K key, V value) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final void putAll(Map<? extends K, ? extends V> map) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @CheckForNull
  @DoNotCall("Always throws UnsupportedOperationException")
  public final V remove(@CheckForNull Object o) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final void clear() {
    throw new UnsupportedOperationException();
  }
  
  public boolean isEmpty() {
    return (size() == 0);
  }
  
  public boolean containsKey(@CheckForNull Object key) {
    return (get(key) != null);
  }
  
  public boolean containsValue(@CheckForNull Object value) {
    return values().contains(value);
  }
  
  @CheckForNull
  public final V getOrDefault(@CheckForNull Object key, @CheckForNull V defaultValue) {
    V result = get(key);
    if (result != null)
      return result; 
    return defaultValue;
  }
  
  public ImmutableSet<Entry<K, V>> entrySet() {
    ImmutableSet<Entry<K, V>> result = this.entrySet;
    return (result == null) ? (this.entrySet = createEntrySet()) : result;
  }
  
  public ImmutableSet<K> keySet() {
    ImmutableSet<K> result = this.keySet;
    return (result == null) ? (this.keySet = createKeySet()) : result;
  }
  
  UnmodifiableIterator<K> keyIterator() {
    final UnmodifiableIterator<Entry<K, V>> entryIterator = entrySet().iterator();
    return new UnmodifiableIterator<K>(this) {
        public boolean hasNext() {
          return entryIterator.hasNext();
        }
        
        public K next() {
          return (K)((Entry)entryIterator.next()).getKey();
        }
      };
  }
  
  Spliterator<K> keySpliterator() {
    return CollectSpliterators.map(entrySet().spliterator(), Entry::getKey);
  }
  
  public ImmutableCollection<V> values() {
    ImmutableCollection<V> result = this.values;
    return (result == null) ? (this.values = createValues()) : result;
  }
  
  public ImmutableSetMultimap<K, V> asMultimap() {
    if (isEmpty())
      return ImmutableSetMultimap.of(); 
    ImmutableSetMultimap<K, V> result = this.multimapView;
    return (result == null) ? (
      this
      .multimapView = new ImmutableSetMultimap<>(new MapViewOfValuesAsSingletonSets(), size(), null)) : 
      result;
  }
  
  private final class MapViewOfValuesAsSingletonSets extends IteratorBasedImmutableMap<K, ImmutableSet<V>> {
    private MapViewOfValuesAsSingletonSets() {}
    
    public int size() {
      return ImmutableMap.this.size();
    }
    
    ImmutableSet<K> createKeySet() {
      return ImmutableMap.this.keySet();
    }
    
    public boolean containsKey(@CheckForNull Object key) {
      return ImmutableMap.this.containsKey(key);
    }
    
    @CheckForNull
    public ImmutableSet<V> get(@CheckForNull Object key) {
      V outerValue = (V)ImmutableMap.this.get(key);
      return (outerValue == null) ? null : ImmutableSet.<V>of(outerValue);
    }
    
    boolean isPartialView() {
      return ImmutableMap.this.isPartialView();
    }
    
    public int hashCode() {
      return ImmutableMap.this.hashCode();
    }
    
    boolean isHashCodeFast() {
      return ImmutableMap.this.isHashCodeFast();
    }
    
    UnmodifiableIterator<Entry<K, ImmutableSet<V>>> entryIterator() {
      final Iterator<Entry<K, V>> backingIterator = ImmutableMap.this.entrySet().iterator();
      return new UnmodifiableIterator<Entry<K, ImmutableSet<V>>>(this) {
          public boolean hasNext() {
            return backingIterator.hasNext();
          }
          
          public Entry<K, ImmutableSet<V>> next() {
            final Entry<K, V> backingEntry = backingIterator.next();
            return (Entry)new AbstractMapEntry<K, ImmutableSet<ImmutableSet<V>>>(this) {
                public K getKey() {
                  return (K)backingEntry.getKey();
                }
                
                public ImmutableSet<V> getValue() {
                  return ImmutableSet.of((V)backingEntry.getValue());
                }
              };
          }
        };
    }
  }
  
  public boolean equals(@CheckForNull Object object) {
    return Maps.equalsImpl(this, object);
  }
  
  public int hashCode() {
    return Sets.hashCodeImpl(entrySet());
  }
  
  boolean isHashCodeFast() {
    return false;
  }
  
  public String toString() {
    return Maps.toStringImpl(this);
  }
  
  static class SerializedForm<K, V> implements Serializable {
    private static final boolean USE_LEGACY_SERIALIZATION = true;
    
    private final Object keys;
    
    private final Object values;
    
    private static final long serialVersionUID = 0L;
    
    SerializedForm(ImmutableMap<K, V> map) {
      Object[] keys = new Object[map.size()];
      Object[] values = new Object[map.size()];
      int i = 0;
      for (UnmodifiableIterator<Entry<? extends Object, ? extends Object>> unmodifiableIterator = map.entrySet().iterator(); unmodifiableIterator.hasNext(); ) {
        Entry<? extends Object, ? extends Object> entry = unmodifiableIterator.next();
        keys[i] = entry.getKey();
        values[i] = entry.getValue();
        i++;
      } 
      this.keys = keys;
      this.values = values;
    }
    
    final Object readResolve() {
      if (!(this.keys instanceof ImmutableSet))
        return legacyReadResolve(); 
      ImmutableSet<K> keySet = (ImmutableSet<K>)this.keys;
      ImmutableCollection<V> values = (ImmutableCollection<V>)this.values;
      Builder<K, V> builder = makeBuilder(keySet.size());
      UnmodifiableIterator<K> keyIter = keySet.iterator();
      UnmodifiableIterator<V> valueIter = values.iterator();
      while (keyIter.hasNext())
        builder.put(keyIter.next(), valueIter.next()); 
      return builder.build();
    }
    
    final Object legacyReadResolve() {
      K[] keys = (K[])this.keys;
      V[] values = (V[])this.values;
      Builder<K, V> builder = makeBuilder(keys.length);
      for (int i = 0; i < keys.length; i++)
        builder.put(keys[i], values[i]); 
      return builder.build();
    }
    
    Builder<K, V> makeBuilder(int size) {
      return new Builder<>(size);
    }
  }
  
  Object writeReplace() {
    return new SerializedForm<>(this);
  }
  
  @CheckForNull
  public abstract V get(@CheckForNull Object paramObject);
  
  abstract ImmutableSet<Entry<K, V>> createEntrySet();
  
  abstract ImmutableSet<K> createKeySet();
  
  abstract ImmutableCollection<V> createValues();
  
  abstract boolean isPartialView();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ImmutableMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */