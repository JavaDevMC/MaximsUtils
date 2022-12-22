package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.DoNotCall;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
abstract class ImmutableBiMapFauxverideShim<K, V> extends ImmutableMap<K, V> {
  @Deprecated
  @DoNotCall("Use toImmutableBiMap")
  public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Use toImmutableBiMap")
  public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction) {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ImmutableBiMapFauxverideShim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */