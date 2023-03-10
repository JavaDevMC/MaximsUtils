package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractMultimap<K, V> implements Multimap<K, V> {
  @LazyInit
  @CheckForNull
  private transient Collection<Map.Entry<K, V>> entries;
  
  @LazyInit
  @CheckForNull
  private transient Set<K> keySet;
  
  @LazyInit
  @CheckForNull
  private transient Multiset<K> keys;
  
  @LazyInit
  @CheckForNull
  private transient Collection<V> values;
  
  @LazyInit
  @CheckForNull
  private transient Map<K, Collection<V>> asMap;
  
  public boolean isEmpty() {
    return (size() == 0);
  }
  
  public boolean containsValue(@CheckForNull Object value) {
    for (Collection<V> collection : asMap().values()) {
      if (collection.contains(value))
        return true; 
    } 
    return false;
  }
  
  public boolean containsEntry(@CheckForNull Object key, @CheckForNull Object value) {
    Collection<V> collection = asMap().get(key);
    return (collection != null && collection.contains(value));
  }
  
  @CanIgnoreReturnValue
  public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
    Collection<V> collection = asMap().get(key);
    return (collection != null && collection.remove(value));
  }
  
  @CanIgnoreReturnValue
  public boolean put(@ParametricNullness K key, @ParametricNullness V value) {
    return get(key).add(value);
  }
  
  @CanIgnoreReturnValue
  public boolean putAll(@ParametricNullness K key, Iterable<? extends V> values) {
    Preconditions.checkNotNull(values);
    if (values instanceof Collection) {
      Collection<? extends V> valueCollection = (Collection<? extends V>)values;
      return (!valueCollection.isEmpty() && get(key).addAll(valueCollection));
    } 
    Iterator<? extends V> valueItr = values.iterator();
    return (valueItr.hasNext() && Iterators.addAll(get(key), valueItr));
  }
  
  @CanIgnoreReturnValue
  public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
    boolean changed = false;
    for (Map.Entry<? extends K, ? extends V> entry : multimap.entries())
      changed |= put(entry.getKey(), entry.getValue()); 
    return changed;
  }
  
  @CanIgnoreReturnValue
  public Collection<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
    Preconditions.checkNotNull(values);
    Collection<V> result = removeAll(key);
    putAll(key, values);
    return result;
  }
  
  public Collection<Map.Entry<K, V>> entries() {
    Collection<Map.Entry<K, V>> result = this.entries;
    return (result == null) ? (this.entries = createEntries()) : result;
  }
  
  abstract Collection<Map.Entry<K, V>> createEntries();
  
  abstract Iterator<Map.Entry<K, V>> entryIterator();
  
  class Entries extends Multimaps.Entries<K, V> {
    Multimap<K, V> multimap() {
      return AbstractMultimap.this;
    }
    
    public Iterator<Map.Entry<K, V>> iterator() {
      return AbstractMultimap.this.entryIterator();
    }
    
    public Spliterator<Map.Entry<K, V>> spliterator() {
      return AbstractMultimap.this.entrySpliterator();
    }
  }
  
  class EntrySet extends Entries implements Set<Map.Entry<K, V>> {
    EntrySet(AbstractMultimap this$0) {
      super(this$0);
    }
    
    public int hashCode() {
      return Sets.hashCodeImpl(this);
    }
    
    public boolean equals(@CheckForNull Object obj) {
      return Sets.equalsImpl(this, obj);
    }
  }
  
  Spliterator<Map.Entry<K, V>> entrySpliterator() {
    return Spliterators.spliterator(
        entryIterator(), size(), (this instanceof SetMultimap) ? 1 : 0);
  }
  
  public Set<K> keySet() {
    Set<K> result = this.keySet;
    return (result == null) ? (this.keySet = createKeySet()) : result;
  }
  
  abstract Set<K> createKeySet();
  
  public Multiset<K> keys() {
    Multiset<K> result = this.keys;
    return (result == null) ? (this.keys = createKeys()) : result;
  }
  
  abstract Multiset<K> createKeys();
  
  public Collection<V> values() {
    Collection<V> result = this.values;
    return (result == null) ? (this.values = createValues()) : result;
  }
  
  abstract Collection<V> createValues();
  
  class Values extends AbstractCollection<V> {
    public Iterator<V> iterator() {
      return AbstractMultimap.this.valueIterator();
    }
    
    public Spliterator<V> spliterator() {
      return AbstractMultimap.this.valueSpliterator();
    }
    
    public int size() {
      return AbstractMultimap.this.size();
    }
    
    public boolean contains(@CheckForNull Object o) {
      return AbstractMultimap.this.containsValue(o);
    }
    
    public void clear() {
      AbstractMultimap.this.clear();
    }
  }
  
  Iterator<V> valueIterator() {
    return Maps.valueIterator(entries().iterator());
  }
  
  Spliterator<V> valueSpliterator() {
    return Spliterators.spliterator(valueIterator(), size(), 0);
  }
  
  public Map<K, Collection<V>> asMap() {
    Map<K, Collection<V>> result = this.asMap;
    return (result == null) ? (this.asMap = createAsMap()) : result;
  }
  
  abstract Map<K, Collection<V>> createAsMap();
  
  public boolean equals(@CheckForNull Object object) {
    return Multimaps.equalsImpl(this, object);
  }
  
  public int hashCode() {
    return asMap().hashCode();
  }
  
  public String toString() {
    return asMap().toString();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\AbstractMultimap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */