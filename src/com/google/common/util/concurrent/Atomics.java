package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class Atomics {
  public static <V> AtomicReference<V> newReference() {
    return new AtomicReference<>();
  }
  
  public static <V> AtomicReference<V> newReference(@ParametricNullness V initialValue) {
    return new AtomicReference<>(initialValue);
  }
  
  public static <E> AtomicReferenceArray<E> newReferenceArray(int length) {
    return new AtomicReferenceArray<>(length);
  }
  
  public static <E> AtomicReferenceArray<E> newReferenceArray(E[] array) {
    return new AtomicReferenceArray<>(array);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\Atomics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */