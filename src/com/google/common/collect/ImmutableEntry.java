package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(serializable = true)
class ImmutableEntry<K, V> extends AbstractMapEntry<K, V> implements Serializable {
  @ParametricNullness
  final K key;
  
  @ParametricNullness
  final V value;
  
  private static final long serialVersionUID = 0L;
  
  ImmutableEntry(@ParametricNullness K key, @ParametricNullness V value) {
    this.key = key;
    this.value = value;
  }
  
  @ParametricNullness
  public final K getKey() {
    return this.key;
  }
  
  @ParametricNullness
  public final V getValue() {
    return this.value;
  }
  
  @ParametricNullness
  public final V setValue(@ParametricNullness V value) {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ImmutableEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */