package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ForwardingMapEntry;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Beta
public final class MutableTypeToInstanceMap<B> extends ForwardingMap<TypeToken<? extends B>, B> implements TypeToInstanceMap<B> {
  private final Map<TypeToken<? extends B>, B> backingMap = Maps.newHashMap();
  
  public <T extends B> T getInstance(Class<T> type) {
    return trustedGet(TypeToken.of(type));
  }
  
  public <T extends B> T getInstance(TypeToken<T> type) {
    return trustedGet(type.rejectTypeVariables());
  }
  
  @CanIgnoreReturnValue
  public <T extends B> T putInstance(Class<T> type, T value) {
    return trustedPut(TypeToken.of(type), value);
  }
  
  @CanIgnoreReturnValue
  public <T extends B> T putInstance(TypeToken<T> type, T value) {
    return trustedPut(type.rejectTypeVariables(), value);
  }
  
  @Deprecated
  @CanIgnoreReturnValue
  @DoNotCall("Always throws UnsupportedOperationException")
  public B put(TypeToken<? extends B> key, B value) {
    throw new UnsupportedOperationException("Please use putInstance() instead.");
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public void putAll(Map<? extends TypeToken<? extends B>, ? extends B> map) {
    throw new UnsupportedOperationException("Please use putInstance() instead.");
  }
  
  public Set<Entry<TypeToken<? extends B>, B>> entrySet() {
    return UnmodifiableEntry.transformEntries(super.entrySet());
  }
  
  protected Map<TypeToken<? extends B>, B> delegate() {
    return this.backingMap;
  }
  
  private <T extends B> T trustedPut(TypeToken<T> type, T value) {
    return (T)this.backingMap.put(type, (B)value);
  }
  
  private <T extends B> T trustedGet(TypeToken<T> type) {
    return (T)this.backingMap.get(type);
  }
  
  private static final class UnmodifiableEntry<K, V> extends ForwardingMapEntry<K, V> {
    private final Entry<K, V> delegate;
    
    static <K, V> Set<Entry<K, V>> transformEntries(final Set<Entry<K, V>> entries) {
      return (Set<Entry<K, V>>)new ForwardingSet<Entry<K, V>>() {
          protected Set<Entry<K, V>> delegate() {
            return entries;
          }
          
          public Iterator<Entry<K, V>> iterator() {
            return UnmodifiableEntry.transformEntries(super.iterator());
          }
          
          public Object[] toArray() {
            return standardToArray();
          }
          
          public <T> T[] toArray(T[] array) {
            return (T[])standardToArray((Object[])array);
          }
        };
    }
    
    private static <K, V> Iterator<Entry<K, V>> transformEntries(Iterator<Entry<K, V>> entries) {
      return Iterators.transform(entries, new Function<Entry<K, V>, Entry<K, V>>() {
            public Entry<K, V> apply(Entry<K, V> entry) {
              return (Entry)new UnmodifiableEntry<>(entry);
            }
          });
    }
    
    private UnmodifiableEntry(Entry<K, V> delegate) {
      this.delegate = (Entry<K, V>)Preconditions.checkNotNull(delegate);
    }
    
    protected Entry<K, V> delegate() {
      return this.delegate;
    }
    
    public V setValue(V value) {
      throw new UnsupportedOperationException();
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\reflect\MutableTypeToInstanceMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */