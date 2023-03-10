package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.DoNotCall;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
abstract class ImmutableSortedMapFauxverideShim<K, V> extends ImmutableMap<K, V> {
  @Deprecated
  @DoNotCall("Use toImmutableSortedMap")
  public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Use toImmutableSortedMap")
  public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Use naturalOrder")
  public static <K, V> ImmutableSortedMap.Builder<K, V> builder() {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Use naturalOrder (which does not accept an expected size)")
  public static <K, V> ImmutableSortedMap.Builder<K, V> builderWithExpectedSize(int expectedSize) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Pass a key of type Comparable")
  public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Pass keys of type Comparable")
  public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Pass keys of type Comparable")
  public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Pass keys of type Comparable")
  public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Pass keys of type Comparable")
  public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Pass keys of type Comparable")
  public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Pass keys of type Comparable")
  public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Pass keys of type Comparable")
  public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Pass keys of type Comparable")
  public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Pass keys of type Comparable")
  public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("ImmutableSortedMap.ofEntries not currently available; use ImmutableSortedMap.copyOf")
  public static <K, V> ImmutableSortedMap<K, V> ofEntries(Entry<? extends K, ? extends V>... entries) {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ImmutableSortedMapFauxverideShim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */