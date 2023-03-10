package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
class FilteredEntryMultimap<K, V> extends AbstractMultimap<K, V> implements FilteredMultimap<K, V> {
  final Multimap<K, V> unfiltered;
  
  final Predicate<? super Map.Entry<K, V>> predicate;
  
  FilteredEntryMultimap(Multimap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> predicate) {
    this.unfiltered = (Multimap<K, V>)Preconditions.checkNotNull(unfiltered);
    this.predicate = (Predicate<? super Map.Entry<K, V>>)Preconditions.checkNotNull(predicate);
  }
  
  public Multimap<K, V> unfiltered() {
    return this.unfiltered;
  }
  
  public Predicate<? super Map.Entry<K, V>> entryPredicate() {
    return this.predicate;
  }
  
  public int size() {
    return entries().size();
  }
  
  private boolean satisfies(@ParametricNullness K key, @ParametricNullness V value) {
    return this.predicate.apply(Maps.immutableEntry(key, value));
  }
  
  final class ValuePredicate implements Predicate<V> {
    @ParametricNullness
    private final K key;
    
    ValuePredicate(K key) {
      this.key = key;
    }
    
    public boolean apply(@ParametricNullness V value) {
      return FilteredEntryMultimap.this.satisfies(this.key, value);
    }
  }
  
  static <E> Collection<E> filterCollection(Collection<E> collection, Predicate<? super E> predicate) {
    if (collection instanceof Set)
      return Sets.filter((Set<E>)collection, predicate); 
    return Collections2.filter(collection, predicate);
  }
  
  public boolean containsKey(@CheckForNull Object key) {
    return (asMap().get(key) != null);
  }
  
  public Collection<V> removeAll(@CheckForNull Object key) {
    return (Collection<V>)MoreObjects.firstNonNull(asMap().remove(key), unmodifiableEmptyCollection());
  }
  
  Collection<V> unmodifiableEmptyCollection() {
    return (this.unfiltered instanceof SetMultimap) ? 
      Collections.<V>emptySet() : 
      Collections.<V>emptyList();
  }
  
  public void clear() {
    entries().clear();
  }
  
  public Collection<V> get(@ParametricNullness K key) {
    return filterCollection(this.unfiltered.get(key), new ValuePredicate(key));
  }
  
  Collection<Map.Entry<K, V>> createEntries() {
    return filterCollection(this.unfiltered.entries(), this.predicate);
  }
  
  Collection<V> createValues() {
    return new FilteredMultimapValues<>(this);
  }
  
  Iterator<Map.Entry<K, V>> entryIterator() {
    throw new AssertionError("should never be called");
  }
  
  Map<K, Collection<V>> createAsMap() {
    return new AsMap();
  }
  
  Set<K> createKeySet() {
    return asMap().keySet();
  }
  
  boolean removeEntriesIf(Predicate<? super Map.Entry<K, Collection<V>>> predicate) {
    Iterator<Map.Entry<K, Collection<V>>> entryIterator = this.unfiltered.asMap().entrySet().iterator();
    boolean changed = false;
    while (entryIterator.hasNext()) {
      Map.Entry<K, Collection<V>> entry = entryIterator.next();
      K key = entry.getKey();
      Collection<V> collection = filterCollection(entry.getValue(), new ValuePredicate(key));
      if (!collection.isEmpty() && predicate.apply(Maps.immutableEntry(key, collection))) {
        if (collection.size() == ((Collection)entry.getValue()).size()) {
          entryIterator.remove();
        } else {
          collection.clear();
        } 
        changed = true;
      } 
    } 
    return changed;
  }
  
  class AsMap extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
    public boolean containsKey(@CheckForNull Object key) {
      return (get(key) != null);
    }
    
    public void clear() {
      FilteredEntryMultimap.this.clear();
    }
    
    @CheckForNull
    public Collection<V> get(@CheckForNull Object key) {
      Collection<V> result = (Collection<V>)FilteredEntryMultimap.this.unfiltered.asMap().get(key);
      if (result == null)
        return null; 
      K k = (K)key;
      result = FilteredEntryMultimap.filterCollection(result, new ValuePredicate(k));
      return result.isEmpty() ? null : result;
    }
    
    @CheckForNull
    public Collection<V> remove(@CheckForNull Object key) {
      Collection<V> collection = (Collection<V>)FilteredEntryMultimap.this.unfiltered.asMap().get(key);
      if (collection == null)
        return null; 
      K k = (K)key;
      List<V> result = Lists.newArrayList();
      Iterator<V> itr = collection.iterator();
      while (itr.hasNext()) {
        V v = itr.next();
        if (FilteredEntryMultimap.this.satisfies(k, v)) {
          itr.remove();
          result.add(v);
        } 
      } 
      if (result.isEmpty())
        return null; 
      if (FilteredEntryMultimap.this.unfiltered instanceof SetMultimap)
        return Collections.unmodifiableSet(Sets.newLinkedHashSet(result)); 
      return Collections.unmodifiableList(result);
    }
    
    Set<K> createKeySet() {
      class KeySetImpl extends Maps.KeySet<K, Collection<V>> {
        KeySetImpl() {
          super(AsMap.this);
        }
        
        public boolean removeAll(Collection<?> c) {
          return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.in(c)));
        }
        
        public boolean retainAll(Collection<?> c) {
          return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(c))));
        }
        
        public boolean remove(@CheckForNull Object o) {
          return (AsMap.this.remove(o) != null);
        }
      };
      return new KeySetImpl();
    }
    
    Set<Entry<K, Collection<V>>> createEntrySet() {
      class EntrySetImpl extends Maps.EntrySet<K, Collection<V>> {
        Map<K, Collection<V>> map() {
          return AsMap.this;
        }
        
        public Iterator<Entry<K, Collection<V>>> iterator() {
          return new AbstractIterator<Entry<K, Collection<V>>>() {
              final Iterator<Entry<K, Collection<V>>> backingIterator = FilteredEntryMultimap.this.unfiltered
                .asMap().entrySet().iterator();
              
              @CheckForNull
              protected Map.Entry<K, Collection<V>> computeNext() {
                while (this.backingIterator.hasNext()) {
                  Entry<K, Collection<V>> entry = this.backingIterator.next();
                  K key = entry.getKey();
                  Collection<V> collection = FilteredEntryMultimap.filterCollection(entry.getValue(), new ValuePredicate(key));
                  if (!collection.isEmpty())
                    return Maps.immutableEntry(key, collection); 
                } 
                return endOfData();
              }
            };
        }
        
        public boolean removeAll(Collection<?> c) {
          return FilteredEntryMultimap.this.removeEntriesIf(Predicates.in(c));
        }
        
        public boolean retainAll(Collection<?> c) {
          return FilteredEntryMultimap.this.removeEntriesIf(Predicates.not(Predicates.in(c)));
        }
        
        public int size() {
          return Iterators.size(iterator());
        }
      };
      return new EntrySetImpl();
    }
    
    Collection<Collection<V>> createValues() {
      class ValuesImpl extends Maps.Values<K, Collection<V>> {
        ValuesImpl() {
          super(AsMap.this);
        }
        
        public boolean remove(@CheckForNull Object o) {
          if (o instanceof Collection) {
            Collection<?> c = (Collection)o;
            Iterator<Entry<K, Collection<V>>> entryIterator = FilteredEntryMultimap.this.unfiltered.asMap().entrySet().iterator();
            while (entryIterator.hasNext()) {
              Entry<K, Collection<V>> entry = entryIterator.next();
              K key = entry.getKey();
              Collection<V> collection = FilteredEntryMultimap.filterCollection(entry.getValue(), new ValuePredicate(key));
              if (!collection.isEmpty() && c.equals(collection)) {
                if (collection.size() == ((Collection)entry.getValue()).size()) {
                  entryIterator.remove();
                } else {
                  collection.clear();
                } 
                return true;
              } 
            } 
          } 
          return false;
        }
        
        public boolean removeAll(Collection<?> c) {
          return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.in(c)));
        }
        
        public boolean retainAll(Collection<?> c) {
          return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(c))));
        }
      };
      return new ValuesImpl();
    }
  }
  
  Multiset<K> createKeys() {
    return new Keys();
  }
  
  class Keys extends Multimaps.Keys<K, V> {
    Keys() {
      super(FilteredEntryMultimap.this);
    }
    
    public int remove(@CheckForNull Object key, int occurrences) {
      CollectPreconditions.checkNonnegative(occurrences, "occurrences");
      if (occurrences == 0)
        return count(key); 
      Collection<V> collection = (Collection<V>)FilteredEntryMultimap.this.unfiltered.asMap().get(key);
      if (collection == null)
        return 0; 
      K k = (K)key;
      int oldCount = 0;
      Iterator<V> itr = collection.iterator();
      while (itr.hasNext()) {
        V v = itr.next();
        oldCount++;
        if (FilteredEntryMultimap.this.satisfies(k, v) && oldCount <= occurrences)
          itr.remove(); 
      } 
      return oldCount;
    }
    
    public Set<Entry<K>> entrySet() {
      return new Multisets.EntrySet<K>() {
          Multiset<K> multiset() {
            return Keys.this;
          }
          
          public Iterator<Entry<K>> iterator() {
            return Keys.this.entryIterator();
          }
          
          public int size() {
            return FilteredEntryMultimap.this.keySet().size();
          }
          
          private boolean removeEntriesIf(final Predicate<? super Entry<K>> predicate) {
            return FilteredEntryMultimap.this.removeEntriesIf(new Predicate<Map.Entry<K, Collection<V>>>(this) {
                  public boolean apply(Map.Entry<K, Collection<V>> entry) {
                    return predicate.apply(
                        Multisets.immutableEntry(entry.getKey(), ((Collection)entry.getValue()).size()));
                  }
                });
          }
          
          public boolean removeAll(Collection<?> c) {
            return removeEntriesIf(Predicates.in(c));
          }
          
          public boolean retainAll(Collection<?> c) {
            return removeEntriesIf(Predicates.not(Predicates.in(c)));
          }
        };
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\FilteredEntryMultimap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */