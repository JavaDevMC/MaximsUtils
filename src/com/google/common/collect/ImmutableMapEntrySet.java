package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(emulated = true)
abstract class ImmutableMapEntrySet<K, V> extends ImmutableSet.CachingAsList<Map.Entry<K, V>> {
  abstract ImmutableMap<K, V> map();
  
  static final class RegularEntrySet<K, V> extends ImmutableMapEntrySet<K, V> {
    private final transient ImmutableMap<K, V> map;
    
    private final transient ImmutableList<Map.Entry<K, V>> entries;
    
    RegularEntrySet(ImmutableMap<K, V> map, Map.Entry<K, V>[] entries) {
      this(map, ImmutableList.asImmutableList((Object[])entries));
    }
    
    RegularEntrySet(ImmutableMap<K, V> map, ImmutableList<Map.Entry<K, V>> entries) {
      this.map = map;
      this.entries = entries;
    }
    
    ImmutableMap<K, V> map() {
      return this.map;
    }
    
    @GwtIncompatible("not used in GWT")
    int copyIntoArray(Object[] dst, int offset) {
      return this.entries.copyIntoArray(dst, offset);
    }
    
    public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
      return this.entries.iterator();
    }
    
    public Spliterator<Map.Entry<K, V>> spliterator() {
      return this.entries.spliterator();
    }
    
    public void forEach(Consumer<? super Map.Entry<K, V>> action) {
      this.entries.forEach(action);
    }
    
    ImmutableList<Map.Entry<K, V>> createAsList() {
      return new RegularImmutableAsList<>(this, this.entries);
    }
  }
  
  public int size() {
    return map().size();
  }
  
  public boolean contains(@CheckForNull Object object) {
    if (object instanceof Map.Entry) {
      Map.Entry<?, ?> entry = (Map.Entry<?, ?>)object;
      V value = map().get(entry.getKey());
      return (value != null && value.equals(entry.getValue()));
    } 
    return false;
  }
  
  boolean isPartialView() {
    return map().isPartialView();
  }
  
  @GwtIncompatible
  boolean isHashCodeFast() {
    return map().isHashCodeFast();
  }
  
  public int hashCode() {
    return map().hashCode();
  }
  
  @GwtIncompatible
  Object writeReplace() {
    return new EntrySetSerializedForm<>(map());
  }
  
  @GwtIncompatible
  private static class EntrySetSerializedForm<K, V> implements Serializable {
    final ImmutableMap<K, V> map;
    
    private static final long serialVersionUID = 0L;
    
    EntrySetSerializedForm(ImmutableMap<K, V> map) {
      this.map = map;
    }
    
    Object readResolve() {
      return this.map.entrySet();
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ImmutableMapEntrySet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */