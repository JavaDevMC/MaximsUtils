package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(emulated = true)
abstract class AbstractBiMap<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {
  private transient Map<K, V> delegate;
  
  @RetainedWith
  transient AbstractBiMap<V, K> inverse;
  
  @CheckForNull
  private transient Set<K> keySet;
  
  @CheckForNull
  private transient Set<V> valueSet;
  
  @CheckForNull
  private transient Set<Entry<K, V>> entrySet;
  
  @GwtIncompatible
  private static final long serialVersionUID = 0L;
  
  AbstractBiMap(Map<K, V> forward, Map<V, K> backward) {
    setDelegates(forward, backward);
  }
  
  private AbstractBiMap(Map<K, V> backward, AbstractBiMap<V, K> forward) {
    this.delegate = backward;
    this.inverse = forward;
  }
  
  protected Map<K, V> delegate() {
    return this.delegate;
  }
  
  @ParametricNullness
  @CanIgnoreReturnValue
  K checkKey(@ParametricNullness K key) {
    return key;
  }
  
  @ParametricNullness
  @CanIgnoreReturnValue
  V checkValue(@ParametricNullness V value) {
    return value;
  }
  
  void setDelegates(Map<K, V> forward, Map<V, K> backward) {
    Preconditions.checkState((this.delegate == null));
    Preconditions.checkState((this.inverse == null));
    Preconditions.checkArgument(forward.isEmpty());
    Preconditions.checkArgument(backward.isEmpty());
    Preconditions.checkArgument((forward != backward));
    this.delegate = forward;
    this.inverse = makeInverse(backward);
  }
  
  AbstractBiMap<V, K> makeInverse(Map<V, K> backward) {
    return new Inverse<>(backward, this);
  }
  
  void setInverse(AbstractBiMap<V, K> inverse) {
    this.inverse = inverse;
  }
  
  public boolean containsValue(@CheckForNull Object value) {
    return this.inverse.containsKey(value);
  }
  
  @CheckForNull
  @CanIgnoreReturnValue
  public V put(@ParametricNullness K key, @ParametricNullness V value) {
    return putInBothMaps(key, value, false);
  }
  
  @CheckForNull
  @CanIgnoreReturnValue
  public V forcePut(@ParametricNullness K key, @ParametricNullness V value) {
    return putInBothMaps(key, value, true);
  }
  
  @CheckForNull
  private V putInBothMaps(@ParametricNullness K key, @ParametricNullness V value, boolean force) {
    checkKey(key);
    checkValue(value);
    boolean containedKey = containsKey(key);
    if (containedKey && Objects.equal(value, get(key)))
      return value; 
    if (force) {
      inverse().remove(value);
    } else {
      Preconditions.checkArgument(!containsValue(value), "value already present: %s", value);
    } 
    V oldValue = this.delegate.put(key, value);
    updateInverseMap(key, containedKey, oldValue, value);
    return oldValue;
  }
  
  private void updateInverseMap(@ParametricNullness K key, boolean containedKey, @CheckForNull V oldValue, @ParametricNullness V newValue) {
    if (containedKey)
      removeFromInverseMap(NullnessCasts.uncheckedCastNullableTToT(oldValue)); 
    this.inverse.delegate.put((K)newValue, (V)key);
  }
  
  @CheckForNull
  @CanIgnoreReturnValue
  public V remove(@CheckForNull Object key) {
    return containsKey(key) ? removeFromBothMaps(key) : null;
  }
  
  @ParametricNullness
  @CanIgnoreReturnValue
  private V removeFromBothMaps(@CheckForNull Object key) {
    V oldValue = NullnessCasts.uncheckedCastNullableTToT(this.delegate.remove(key));
    removeFromInverseMap(oldValue);
    return oldValue;
  }
  
  private void removeFromInverseMap(@ParametricNullness V oldValue) {
    this.inverse.delegate.remove(oldValue);
  }
  
  public void putAll(Map<? extends K, ? extends V> map) {
    for (Entry<? extends K, ? extends V> entry : map.entrySet())
      put(entry.getKey(), entry.getValue()); 
  }
  
  public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
    this.delegate.replaceAll(function);
    this.inverse.delegate.clear();
    Entry<K, V> broken = null;
    Iterator<Entry<K, V>> itr = this.delegate.entrySet().iterator();
    while (itr.hasNext()) {
      Entry<K, V> entry = itr.next();
      K k = entry.getKey();
      V v = entry.getValue();
      K conflict = (K)this.inverse.delegate.putIfAbsent((K)v, (V)k);
      if (conflict != null) {
        broken = entry;
        itr.remove();
      } 
    } 
    if (broken != null) {
      String str = String.valueOf(broken.getValue());
      throw new IllegalArgumentException((new StringBuilder(23 + String.valueOf(str).length())).append("value already present: ").append(str).toString());
    } 
  }
  
  public void clear() {
    this.delegate.clear();
    this.inverse.delegate.clear();
  }
  
  public BiMap<V, K> inverse() {
    return this.inverse;
  }
  
  public Set<K> keySet() {
    Set<K> result = this.keySet;
    return (result == null) ? (this.keySet = new KeySet()) : result;
  }
  
  private class KeySet extends ForwardingSet<K> {
    private KeySet() {}
    
    protected Set<K> delegate() {
      return AbstractBiMap.this.delegate.keySet();
    }
    
    public void clear() {
      AbstractBiMap.this.clear();
    }
    
    public boolean remove(@CheckForNull Object key) {
      if (!contains(key))
        return false; 
      AbstractBiMap.this.removeFromBothMaps(key);
      return true;
    }
    
    public boolean removeAll(Collection<?> keysToRemove) {
      return standardRemoveAll(keysToRemove);
    }
    
    public boolean retainAll(Collection<?> keysToRetain) {
      return standardRetainAll(keysToRetain);
    }
    
    public Iterator<K> iterator() {
      return Maps.keyIterator(AbstractBiMap.this.entrySet().iterator());
    }
  }
  
  public Set<V> values() {
    Set<V> result = this.valueSet;
    return (result == null) ? (this.valueSet = new ValueSet()) : result;
  }
  
  private class ValueSet extends ForwardingSet<V> {
    final Set<V> valuesDelegate = AbstractBiMap.this.inverse.keySet();
    
    protected Set<V> delegate() {
      return this.valuesDelegate;
    }
    
    public Iterator<V> iterator() {
      return Maps.valueIterator(AbstractBiMap.this.entrySet().iterator());
    }
    
    public Object[] toArray() {
      return standardToArray();
    }
    
    public <T> T[] toArray(T[] array) {
      return (T[])standardToArray((Object[])array);
    }
    
    public String toString() {
      return standardToString();
    }
    
    private ValueSet() {}
  }
  
  public Set<Entry<K, V>> entrySet() {
    Set<Entry<K, V>> result = this.entrySet;
    return (result == null) ? (this.entrySet = new EntrySet()) : result;
  }
  
  class BiMapEntry extends ForwardingMapEntry<K, V> {
    private final Entry<K, V> delegate;
    
    BiMapEntry(Entry<K, V> delegate) {
      this.delegate = delegate;
    }
    
    protected Entry<K, V> delegate() {
      return this.delegate;
    }
    
    public V setValue(V value) {
      AbstractBiMap.this.checkValue(value);
      Preconditions.checkState(AbstractBiMap.this.entrySet().contains(this), "entry no longer in map");
      if (Objects.equal(value, getValue()))
        return value; 
      Preconditions.checkArgument(!AbstractBiMap.this.containsValue(value), "value already present: %s", value);
      V oldValue = this.delegate.setValue(value);
      Preconditions.checkState(Objects.equal(value, AbstractBiMap.this.get(getKey())), "entry no longer in map");
      AbstractBiMap.this.updateInverseMap(getKey(), true, oldValue, value);
      return oldValue;
    }
  }
  
  Iterator<Entry<K, V>> entrySetIterator() {
    final Iterator<Entry<K, V>> iterator = this.delegate.entrySet().iterator();
    return new Iterator<Entry<K, V>>() {
        @CheckForNull
        Map.Entry<K, V> entry;
        
        public boolean hasNext() {
          return iterator.hasNext();
        }
        
        public Entry<K, V> next() {
          this.entry = iterator.next();
          return new BiMapEntry(this.entry);
        }
        
        public void remove() {
          if (this.entry == null)
            throw new IllegalStateException("no calls to next() since the last call to remove()"); 
          V value = this.entry.getValue();
          iterator.remove();
          AbstractBiMap.this.removeFromInverseMap(value);
          this.entry = null;
        }
      };
  }
  
  private class EntrySet extends ForwardingSet<Entry<K, V>> {
    final Set<Entry<K, V>> esDelegate = AbstractBiMap.this.delegate.entrySet();
    
    protected Set<Entry<K, V>> delegate() {
      return this.esDelegate;
    }
    
    public void clear() {
      AbstractBiMap.this.clear();
    }
    
    public boolean remove(@CheckForNull Object object) {
      if (!this.esDelegate.contains(object) || !(object instanceof Map.Entry))
        return false; 
      Entry<?, ?> entry = (Entry<?, ?>)object;
      AbstractBiMap.this.inverse.delegate.remove(entry.getValue());
      this.esDelegate.remove(entry);
      return true;
    }
    
    public Iterator<Entry<K, V>> iterator() {
      return AbstractBiMap.this.entrySetIterator();
    }
    
    public Object[] toArray() {
      Object[] result = standardToArray();
      return result;
    }
    
    public <T> T[] toArray(T[] array) {
      return (T[])standardToArray((Object[])array);
    }
    
    public boolean contains(@CheckForNull Object o) {
      return Maps.containsEntryImpl(delegate(), o);
    }
    
    public boolean containsAll(Collection<?> c) {
      return standardContainsAll(c);
    }
    
    public boolean removeAll(Collection<?> c) {
      return standardRemoveAll(c);
    }
    
    public boolean retainAll(Collection<?> c) {
      return standardRetainAll(c);
    }
    
    private EntrySet() {}
  }
  
  static class Inverse<K, V> extends AbstractBiMap<K, V> {
    @GwtIncompatible
    private static final long serialVersionUID = 0L;
    
    Inverse(Map<K, V> backward, AbstractBiMap<V, K> forward) {
      super(backward, forward);
    }
    
    @ParametricNullness
    K checkKey(@ParametricNullness K key) {
      return this.inverse.checkValue(key);
    }
    
    @ParametricNullness
    V checkValue(@ParametricNullness V value) {
      return this.inverse.checkKey(value);
    }
    
    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeObject(inverse());
    }
    
    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      setInverse((AbstractBiMap<V, K>)stream.readObject());
    }
    
    @GwtIncompatible
    Object readResolve() {
      return inverse().inverse();
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\AbstractBiMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */