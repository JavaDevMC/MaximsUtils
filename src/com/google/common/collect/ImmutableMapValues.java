package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(emulated = true)
final class ImmutableMapValues<K, V> extends ImmutableCollection<V> {
  private final ImmutableMap<K, V> map;
  
  ImmutableMapValues(ImmutableMap<K, V> map) {
    this.map = map;
  }
  
  public int size() {
    return this.map.size();
  }
  
  public UnmodifiableIterator<V> iterator() {
    return new UnmodifiableIterator<V>() {
        final UnmodifiableIterator<Map.Entry<K, V>> entryItr = ImmutableMapValues.this.map.entrySet().iterator();
        
        public boolean hasNext() {
          return this.entryItr.hasNext();
        }
        
        public V next() {
          return (V)((Map.Entry)this.entryItr.next()).getValue();
        }
      };
  }
  
  public Spliterator<V> spliterator() {
    return CollectSpliterators.map(this.map.entrySet().spliterator(), Map.Entry::getValue);
  }
  
  public boolean contains(@CheckForNull Object object) {
    return (object != null && Iterators.contains(iterator(), object));
  }
  
  boolean isPartialView() {
    return true;
  }
  
  public ImmutableList<V> asList() {
    final ImmutableList<Map.Entry<K, V>> entryList = this.map.entrySet().asList();
    return new ImmutableAsList<V>() {
        public V get(int index) {
          return (V)((Map.Entry)entryList.get(index)).getValue();
        }
        
        ImmutableCollection<V> delegateCollection() {
          return ImmutableMapValues.this;
        }
      };
  }
  
  @GwtIncompatible
  public void forEach(Consumer<? super V> action) {
    Preconditions.checkNotNull(action);
    this.map.forEach((k, v) -> action.accept(v));
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


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ImmutableMapValues.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */