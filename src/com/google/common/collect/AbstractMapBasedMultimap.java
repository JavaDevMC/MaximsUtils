package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractMapBasedMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
  private transient Map<K, Collection<V>> map;
  
  private transient int totalSize;
  
  private static final long serialVersionUID = 2447537837011683357L;
  
  protected AbstractMapBasedMultimap(Map<K, Collection<V>> map) {
    Preconditions.checkArgument(map.isEmpty());
    this.map = map;
  }
  
  final void setMap(Map<K, Collection<V>> map) {
    this.map = map;
    this.totalSize = 0;
    for (Collection<V> values : map.values()) {
      Preconditions.checkArgument(!values.isEmpty());
      this.totalSize += values.size();
    } 
  }
  
  Collection<V> createUnmodifiableEmptyCollection() {
    return unmodifiableCollectionSubclass(createCollection());
  }
  
  Collection<V> createCollection(@ParametricNullness K key) {
    return createCollection();
  }
  
  Map<K, Collection<V>> backingMap() {
    return this.map;
  }
  
  public int size() {
    return this.totalSize;
  }
  
  public boolean containsKey(@CheckForNull Object key) {
    return this.map.containsKey(key);
  }
  
  public boolean put(@ParametricNullness K key, @ParametricNullness V value) {
    Collection<V> collection = this.map.get(key);
    if (collection == null) {
      collection = createCollection(key);
      if (collection.add(value)) {
        this.totalSize++;
        this.map.put(key, collection);
        return true;
      } 
      throw new AssertionError("New Collection violated the Collection spec");
    } 
    if (collection.add(value)) {
      this.totalSize++;
      return true;
    } 
    return false;
  }
  
  private Collection<V> getOrCreateCollection(@ParametricNullness K key) {
    Collection<V> collection = this.map.get(key);
    if (collection == null) {
      collection = createCollection(key);
      this.map.put(key, collection);
    } 
    return collection;
  }
  
  public Collection<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
    Iterator<? extends V> iterator = values.iterator();
    if (!iterator.hasNext())
      return removeAll(key); 
    Collection<V> collection = getOrCreateCollection(key);
    Collection<V> oldValues = createCollection();
    oldValues.addAll(collection);
    this.totalSize -= collection.size();
    collection.clear();
    while (iterator.hasNext()) {
      if (collection.add(iterator.next()))
        this.totalSize++; 
    } 
    return unmodifiableCollectionSubclass(oldValues);
  }
  
  public Collection<V> removeAll(@CheckForNull Object key) {
    Collection<V> collection = this.map.remove(key);
    if (collection == null)
      return createUnmodifiableEmptyCollection(); 
    Collection<V> output = createCollection();
    output.addAll(collection);
    this.totalSize -= collection.size();
    collection.clear();
    return unmodifiableCollectionSubclass(output);
  }
  
  <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
    return Collections.unmodifiableCollection(collection);
  }
  
  public void clear() {
    for (Collection<V> collection : this.map.values())
      collection.clear(); 
    this.map.clear();
    this.totalSize = 0;
  }
  
  public Collection<V> get(@ParametricNullness K key) {
    Collection<V> collection = this.map.get(key);
    if (collection == null)
      collection = createCollection(key); 
    return wrapCollection(key, collection);
  }
  
  Collection<V> wrapCollection(@ParametricNullness K key, Collection<V> collection) {
    return new WrappedCollection(key, collection, null);
  }
  
  final List<V> wrapList(@ParametricNullness K key, List<V> list, @CheckForNull WrappedCollection ancestor) {
    return (list instanceof RandomAccess) ? 
      new RandomAccessWrappedList(this, key, list, ancestor) : 
      new WrappedList(key, list, ancestor);
  }
  
  class WrappedCollection extends AbstractCollection<V> {
    @ParametricNullness
    final K key;
    
    Collection<V> delegate;
    
    @CheckForNull
    final WrappedCollection ancestor;
    
    @CheckForNull
    final Collection<V> ancestorDelegate;
    
    WrappedCollection(K key, @CheckForNull Collection<V> delegate, WrappedCollection ancestor) {
      this.key = key;
      this.delegate = delegate;
      this.ancestor = ancestor;
      this.ancestorDelegate = (ancestor == null) ? null : ancestor.getDelegate();
    }
    
    void refreshIfEmpty() {
      if (this.ancestor != null) {
        this.ancestor.refreshIfEmpty();
        if (this.ancestor.getDelegate() != this.ancestorDelegate)
          throw new ConcurrentModificationException(); 
      } else if (this.delegate.isEmpty()) {
        Collection<V> newDelegate = (Collection<V>)AbstractMapBasedMultimap.this.map.get(this.key);
        if (newDelegate != null)
          this.delegate = newDelegate; 
      } 
    }
    
    void removeIfEmpty() {
      if (this.ancestor != null) {
        this.ancestor.removeIfEmpty();
      } else if (this.delegate.isEmpty()) {
        AbstractMapBasedMultimap.this.map.remove(this.key);
      } 
    }
    
    @ParametricNullness
    K getKey() {
      return this.key;
    }
    
    void addToMap() {
      if (this.ancestor != null) {
        this.ancestor.addToMap();
      } else {
        AbstractMapBasedMultimap.this.map.put(this.key, this.delegate);
      } 
    }
    
    public int size() {
      refreshIfEmpty();
      return this.delegate.size();
    }
    
    public boolean equals(@CheckForNull Object object) {
      if (object == this)
        return true; 
      refreshIfEmpty();
      return this.delegate.equals(object);
    }
    
    public int hashCode() {
      refreshIfEmpty();
      return this.delegate.hashCode();
    }
    
    public String toString() {
      refreshIfEmpty();
      return this.delegate.toString();
    }
    
    Collection<V> getDelegate() {
      return this.delegate;
    }
    
    public Iterator<V> iterator() {
      refreshIfEmpty();
      return new WrappedIterator();
    }
    
    public Spliterator<V> spliterator() {
      refreshIfEmpty();
      return this.delegate.spliterator();
    }
    
    class WrappedIterator implements Iterator<V> {
      final Iterator<V> delegateIterator;
      
      final Collection<V> originalDelegate = WrappedCollection.this.delegate;
      
      WrappedIterator() {
        this.delegateIterator = AbstractMapBasedMultimap.iteratorOrListIterator(WrappedCollection.this.delegate);
      }
      
      WrappedIterator(Iterator<V> delegateIterator) {
        this.delegateIterator = delegateIterator;
      }
      
      void validateIterator() {
        WrappedCollection.this.refreshIfEmpty();
        if (WrappedCollection.this.delegate != this.originalDelegate)
          throw new ConcurrentModificationException(); 
      }
      
      public boolean hasNext() {
        validateIterator();
        return this.delegateIterator.hasNext();
      }
      
      @ParametricNullness
      public V next() {
        validateIterator();
        return this.delegateIterator.next();
      }
      
      public void remove() {
        this.delegateIterator.remove();
        AbstractMapBasedMultimap.this.totalSize--;
        WrappedCollection.this.removeIfEmpty();
      }
      
      Iterator<V> getDelegateIterator() {
        validateIterator();
        return this.delegateIterator;
      }
    }
    
    public boolean add(@ParametricNullness V value) {
      refreshIfEmpty();
      boolean wasEmpty = this.delegate.isEmpty();
      boolean changed = this.delegate.add(value);
      if (changed) {
        AbstractMapBasedMultimap.this.totalSize++;
        if (wasEmpty)
          addToMap(); 
      } 
      return changed;
    }
    
    @CheckForNull
    WrappedCollection getAncestor() {
      return this.ancestor;
    }
    
    public boolean addAll(Collection<? extends V> collection) {
      if (collection.isEmpty())
        return false; 
      int oldSize = size();
      boolean changed = this.delegate.addAll(collection);
      if (changed) {
        int newSize = this.delegate.size();
        AbstractMapBasedMultimap.this.totalSize += newSize - oldSize;
        if (oldSize == 0)
          addToMap(); 
      } 
      return changed;
    }
    
    public boolean contains(@CheckForNull Object o) {
      refreshIfEmpty();
      return this.delegate.contains(o);
    }
    
    public boolean containsAll(Collection<?> c) {
      refreshIfEmpty();
      return this.delegate.containsAll(c);
    }
    
    public void clear() {
      int oldSize = size();
      if (oldSize == 0)
        return; 
      this.delegate.clear();
      AbstractMapBasedMultimap.this.totalSize -= oldSize;
      removeIfEmpty();
    }
    
    public boolean remove(@CheckForNull Object o) {
      refreshIfEmpty();
      boolean changed = this.delegate.remove(o);
      if (changed) {
        AbstractMapBasedMultimap.this.totalSize--;
        removeIfEmpty();
      } 
      return changed;
    }
    
    public boolean removeAll(Collection<?> c) {
      if (c.isEmpty())
        return false; 
      int oldSize = size();
      boolean changed = this.delegate.removeAll(c);
      if (changed) {
        int newSize = this.delegate.size();
        AbstractMapBasedMultimap.this.totalSize += newSize - oldSize;
        removeIfEmpty();
      } 
      return changed;
    }
    
    public boolean retainAll(Collection<?> c) {
      Preconditions.checkNotNull(c);
      int oldSize = size();
      boolean changed = this.delegate.retainAll(c);
      if (changed) {
        int newSize = this.delegate.size();
        AbstractMapBasedMultimap.this.totalSize += newSize - oldSize;
        removeIfEmpty();
      } 
      return changed;
    }
  }
  
  private static <E> Iterator<E> iteratorOrListIterator(Collection<E> collection) {
    return (collection instanceof List) ? (
      (List<E>)collection).listIterator() : 
      collection.iterator();
  }
  
  class WrappedSet extends WrappedCollection implements Set<V> {
    WrappedSet(K key, Set<V> delegate) {
      super(key, delegate, null);
    }
    
    public boolean removeAll(Collection<?> c) {
      if (c.isEmpty())
        return false; 
      int oldSize = size();
      boolean changed = Sets.removeAllImpl((Set)this.delegate, c);
      if (changed) {
        int newSize = this.delegate.size();
        AbstractMapBasedMultimap.this.totalSize += newSize - oldSize;
        removeIfEmpty();
      } 
      return changed;
    }
  }
  
  class WrappedSortedSet extends WrappedCollection implements SortedSet<V> {
    WrappedSortedSet(K key, @CheckForNull SortedSet<V> delegate, WrappedCollection ancestor) {
      super(key, delegate, ancestor);
    }
    
    SortedSet<V> getSortedSetDelegate() {
      return (SortedSet<V>)getDelegate();
    }
    
    @CheckForNull
    public Comparator<? super V> comparator() {
      return getSortedSetDelegate().comparator();
    }
    
    @ParametricNullness
    public V first() {
      refreshIfEmpty();
      return getSortedSetDelegate().first();
    }
    
    @ParametricNullness
    public V last() {
      refreshIfEmpty();
      return getSortedSetDelegate().last();
    }
    
    public SortedSet<V> headSet(@ParametricNullness V toElement) {
      refreshIfEmpty();
      return new WrappedSortedSet(
          getKey(), 
          getSortedSetDelegate().headSet(toElement), 
          (getAncestor() == null) ? this : getAncestor());
    }
    
    public SortedSet<V> subSet(@ParametricNullness V fromElement, @ParametricNullness V toElement) {
      refreshIfEmpty();
      return new WrappedSortedSet(
          getKey(), 
          getSortedSetDelegate().subSet(fromElement, toElement), 
          (getAncestor() == null) ? this : getAncestor());
    }
    
    public SortedSet<V> tailSet(@ParametricNullness V fromElement) {
      refreshIfEmpty();
      return new WrappedSortedSet(
          getKey(), 
          getSortedSetDelegate().tailSet(fromElement), 
          (getAncestor() == null) ? this : getAncestor());
    }
  }
  
  class WrappedNavigableSet extends WrappedSortedSet implements NavigableSet<V> {
    WrappedNavigableSet(K key, @CheckForNull NavigableSet<V> delegate, WrappedCollection ancestor) {
      super(key, delegate, ancestor);
    }
    
    NavigableSet<V> getSortedSetDelegate() {
      return (NavigableSet<V>)super.getSortedSetDelegate();
    }
    
    @CheckForNull
    public V lower(@ParametricNullness V v) {
      return getSortedSetDelegate().lower(v);
    }
    
    @CheckForNull
    public V floor(@ParametricNullness V v) {
      return getSortedSetDelegate().floor(v);
    }
    
    @CheckForNull
    public V ceiling(@ParametricNullness V v) {
      return getSortedSetDelegate().ceiling(v);
    }
    
    @CheckForNull
    public V higher(@ParametricNullness V v) {
      return getSortedSetDelegate().higher(v);
    }
    
    @CheckForNull
    public V pollFirst() {
      return Iterators.pollNext(iterator());
    }
    
    @CheckForNull
    public V pollLast() {
      return Iterators.pollNext(descendingIterator());
    }
    
    private NavigableSet<V> wrap(NavigableSet<V> wrapped) {
      return new WrappedNavigableSet(this.key, wrapped, (getAncestor() == null) ? this : getAncestor());
    }
    
    public NavigableSet<V> descendingSet() {
      return wrap(getSortedSetDelegate().descendingSet());
    }
    
    public Iterator<V> descendingIterator() {
      return new WrappedIterator(this, getSortedSetDelegate().descendingIterator());
    }
    
    public NavigableSet<V> subSet(@ParametricNullness V fromElement, boolean fromInclusive, @ParametricNullness V toElement, boolean toInclusive) {
      return wrap(
          getSortedSetDelegate().subSet(fromElement, fromInclusive, toElement, toInclusive));
    }
    
    public NavigableSet<V> headSet(@ParametricNullness V toElement, boolean inclusive) {
      return wrap(getSortedSetDelegate().headSet(toElement, inclusive));
    }
    
    public NavigableSet<V> tailSet(@ParametricNullness V fromElement, boolean inclusive) {
      return wrap(getSortedSetDelegate().tailSet(fromElement, inclusive));
    }
  }
  
  class WrappedList extends WrappedCollection implements List<V> {
    WrappedList(K key, @CheckForNull List<V> delegate, WrappedCollection ancestor) {
      super(key, delegate, ancestor);
    }
    
    List<V> getListDelegate() {
      return (List<V>)getDelegate();
    }
    
    public boolean addAll(int index, Collection<? extends V> c) {
      if (c.isEmpty())
        return false; 
      int oldSize = size();
      boolean changed = getListDelegate().addAll(index, c);
      if (changed) {
        int newSize = getDelegate().size();
        AbstractMapBasedMultimap.this.totalSize += newSize - oldSize;
        if (oldSize == 0)
          addToMap(); 
      } 
      return changed;
    }
    
    @ParametricNullness
    public V get(int index) {
      refreshIfEmpty();
      return getListDelegate().get(index);
    }
    
    @ParametricNullness
    public V set(int index, @ParametricNullness V element) {
      refreshIfEmpty();
      return getListDelegate().set(index, element);
    }
    
    public void add(int index, @ParametricNullness V element) {
      refreshIfEmpty();
      boolean wasEmpty = getDelegate().isEmpty();
      getListDelegate().add(index, element);
      AbstractMapBasedMultimap.this.totalSize++;
      if (wasEmpty)
        addToMap(); 
    }
    
    @ParametricNullness
    public V remove(int index) {
      refreshIfEmpty();
      V value = getListDelegate().remove(index);
      AbstractMapBasedMultimap.this.totalSize--;
      removeIfEmpty();
      return value;
    }
    
    public int indexOf(@CheckForNull Object o) {
      refreshIfEmpty();
      return getListDelegate().indexOf(o);
    }
    
    public int lastIndexOf(@CheckForNull Object o) {
      refreshIfEmpty();
      return getListDelegate().lastIndexOf(o);
    }
    
    public ListIterator<V> listIterator() {
      refreshIfEmpty();
      return new WrappedListIterator();
    }
    
    public ListIterator<V> listIterator(int index) {
      refreshIfEmpty();
      return new WrappedListIterator(index);
    }
    
    public List<V> subList(int fromIndex, int toIndex) {
      refreshIfEmpty();
      return AbstractMapBasedMultimap.this.wrapList(
          getKey(), 
          getListDelegate().subList(fromIndex, toIndex), 
          (getAncestor() == null) ? this : getAncestor());
    }
    
    private class WrappedListIterator extends WrappedIterator implements ListIterator<V> {
      WrappedListIterator() {}
      
      public WrappedListIterator(int index) {
        super(WrappedList.this.getListDelegate().listIterator(index));
      }
      
      private ListIterator<V> getDelegateListIterator() {
        return (ListIterator<V>)getDelegateIterator();
      }
      
      public boolean hasPrevious() {
        return getDelegateListIterator().hasPrevious();
      }
      
      @ParametricNullness
      public V previous() {
        return getDelegateListIterator().previous();
      }
      
      public int nextIndex() {
        return getDelegateListIterator().nextIndex();
      }
      
      public int previousIndex() {
        return getDelegateListIterator().previousIndex();
      }
      
      public void set(@ParametricNullness V value) {
        getDelegateListIterator().set(value);
      }
      
      public void add(@ParametricNullness V value) {
        boolean wasEmpty = WrappedList.this.isEmpty();
        getDelegateListIterator().add(value);
        AbstractMapBasedMultimap.this.totalSize++;
        if (wasEmpty)
          WrappedList.this.addToMap();
      }
    }
  }
  
  private class RandomAccessWrappedList extends WrappedList implements RandomAccess {
    RandomAccessWrappedList(@ParametricNullness AbstractMapBasedMultimap this$0, K key, @CheckForNull List<V> delegate, WrappedCollection ancestor) {
      super(this$0, key, delegate, ancestor);
    }
  }
  
  Set<K> createKeySet() {
    return new KeySet(this.map);
  }
  
  final Set<K> createMaybeNavigableKeySet() {
    if (this.map instanceof NavigableMap)
      return new NavigableKeySet((NavigableMap<K, Collection<V>>)this.map); 
    if (this.map instanceof SortedMap)
      return new SortedKeySet((SortedMap<K, Collection<V>>)this.map); 
    return new KeySet(this.map);
  }
  
  private class KeySet extends Maps.KeySet<K, Collection<V>> {
    KeySet(Map<K, Collection<V>> subMap) {
      super(subMap);
    }
    
    public Iterator<K> iterator() {
      final Iterator<Map.Entry<K, Collection<V>>> entryIterator = map().entrySet().iterator();
      return new Iterator<K>() {
          @CheckForNull
          Map.Entry<K, Collection<V>> entry;
          
          public boolean hasNext() {
            return entryIterator.hasNext();
          }
          
          @ParametricNullness
          public K next() {
            this.entry = entryIterator.next();
            return this.entry.getKey();
          }
          
          public void remove() {
            Preconditions.checkState((this.entry != null), "no calls to next() since the last call to remove()");
            Collection<V> collection = this.entry.getValue();
            entryIterator.remove();
            AbstractMapBasedMultimap.this.totalSize -= collection.size();
            collection.clear();
            this.entry = null;
          }
        };
    }
    
    public Spliterator<K> spliterator() {
      return map().keySet().spliterator();
    }
    
    public boolean remove(@CheckForNull Object key) {
      int count = 0;
      Collection<V> collection = map().remove(key);
      if (collection != null) {
        count = collection.size();
        collection.clear();
        AbstractMapBasedMultimap.this.totalSize -= count;
      } 
      return (count > 0);
    }
    
    public void clear() {
      Iterators.clear(iterator());
    }
    
    public boolean containsAll(Collection<?> c) {
      return map().keySet().containsAll(c);
    }
    
    public boolean equals(@CheckForNull Object object) {
      return (this == object || map().keySet().equals(object));
    }
    
    public int hashCode() {
      return map().keySet().hashCode();
    }
  }
  
  private class SortedKeySet extends KeySet implements SortedSet<K> {
    SortedKeySet(SortedMap<K, Collection<V>> subMap) {
      super(subMap);
    }
    
    SortedMap<K, Collection<V>> sortedMap() {
      return (SortedMap<K, Collection<V>>)map();
    }
    
    @CheckForNull
    public Comparator<? super K> comparator() {
      return sortedMap().comparator();
    }
    
    @ParametricNullness
    public K first() {
      return sortedMap().firstKey();
    }
    
    public SortedSet<K> headSet(@ParametricNullness K toElement) {
      return new SortedKeySet(sortedMap().headMap(toElement));
    }
    
    @ParametricNullness
    public K last() {
      return sortedMap().lastKey();
    }
    
    public SortedSet<K> subSet(@ParametricNullness K fromElement, @ParametricNullness K toElement) {
      return new SortedKeySet(sortedMap().subMap(fromElement, toElement));
    }
    
    public SortedSet<K> tailSet(@ParametricNullness K fromElement) {
      return new SortedKeySet(sortedMap().tailMap(fromElement));
    }
  }
  
  class NavigableKeySet extends SortedKeySet implements NavigableSet<K> {
    NavigableKeySet(NavigableMap<K, Collection<V>> subMap) {
      super(subMap);
    }
    
    NavigableMap<K, Collection<V>> sortedMap() {
      return (NavigableMap<K, Collection<V>>)super.sortedMap();
    }
    
    @CheckForNull
    public K lower(@ParametricNullness K k) {
      return sortedMap().lowerKey(k);
    }
    
    @CheckForNull
    public K floor(@ParametricNullness K k) {
      return sortedMap().floorKey(k);
    }
    
    @CheckForNull
    public K ceiling(@ParametricNullness K k) {
      return sortedMap().ceilingKey(k);
    }
    
    @CheckForNull
    public K higher(@ParametricNullness K k) {
      return sortedMap().higherKey(k);
    }
    
    @CheckForNull
    public K pollFirst() {
      return Iterators.pollNext(iterator());
    }
    
    @CheckForNull
    public K pollLast() {
      return Iterators.pollNext(descendingIterator());
    }
    
    public NavigableSet<K> descendingSet() {
      return new NavigableKeySet(sortedMap().descendingMap());
    }
    
    public Iterator<K> descendingIterator() {
      return descendingSet().iterator();
    }
    
    public NavigableSet<K> headSet(@ParametricNullness K toElement) {
      return headSet(toElement, false);
    }
    
    public NavigableSet<K> headSet(@ParametricNullness K toElement, boolean inclusive) {
      return new NavigableKeySet(sortedMap().headMap(toElement, inclusive));
    }
    
    public NavigableSet<K> subSet(@ParametricNullness K fromElement, @ParametricNullness K toElement) {
      return subSet(fromElement, true, toElement, false);
    }
    
    public NavigableSet<K> subSet(@ParametricNullness K fromElement, boolean fromInclusive, @ParametricNullness K toElement, boolean toInclusive) {
      return new NavigableKeySet(
          sortedMap().subMap(fromElement, fromInclusive, toElement, toInclusive));
    }
    
    public NavigableSet<K> tailSet(@ParametricNullness K fromElement) {
      return tailSet(fromElement, true);
    }
    
    public NavigableSet<K> tailSet(@ParametricNullness K fromElement, boolean inclusive) {
      return new NavigableKeySet(sortedMap().tailMap(fromElement, inclusive));
    }
  }
  
  private void removeValuesForKey(@CheckForNull Object key) {
    Collection<V> collection = Maps.<Collection<V>>safeRemove(this.map, key);
    if (collection != null) {
      int count = collection.size();
      collection.clear();
      this.totalSize -= count;
    } 
  }
  
  private abstract class Itr<T> implements Iterator<T> {
    final Iterator<Map.Entry<K, Collection<V>>> keyIterator = AbstractMapBasedMultimap.this.map.entrySet().iterator();
    
    @CheckForNull
    K key = null;
    
    @CheckForNull
    Collection<V> collection = null;
    
    Iterator<V> valueIterator = Iterators.emptyModifiableIterator();
    
    abstract T output(@ParametricNullness K param1K, @ParametricNullness V param1V);
    
    public boolean hasNext() {
      return (this.keyIterator.hasNext() || this.valueIterator.hasNext());
    }
    
    public T next() {
      if (!this.valueIterator.hasNext()) {
        Map.Entry<K, Collection<V>> mapEntry = this.keyIterator.next();
        this.key = mapEntry.getKey();
        this.collection = mapEntry.getValue();
        this.valueIterator = this.collection.iterator();
      } 
      return output(NullnessCasts.uncheckedCastNullableTToT(this.key), this.valueIterator.next());
    }
    
    public void remove() {
      this.valueIterator.remove();
      if (((Collection)Objects.<Collection>requireNonNull(this.collection)).isEmpty())
        this.keyIterator.remove(); 
      AbstractMapBasedMultimap.this.totalSize--;
    }
  }
  
  public Collection<V> values() {
    return super.values();
  }
  
  Collection<V> createValues() {
    return new Values(this);
  }
  
  Iterator<V> valueIterator() {
    return new Itr<V>(this) {
        @ParametricNullness
        V output(@ParametricNullness K key, @ParametricNullness V value) {
          return value;
        }
      };
  }
  
  Spliterator<V> valueSpliterator() {
    return CollectSpliterators.flatMap(this.map
        .values().spliterator(), Collection::spliterator, 64, size());
  }
  
  Multiset<K> createKeys() {
    return new Multimaps.Keys<>(this);
  }
  
  public Collection<Map.Entry<K, V>> entries() {
    return super.entries();
  }
  
  Collection<Map.Entry<K, V>> createEntries() {
    if (this instanceof SetMultimap)
      return new EntrySet(this);
    return new Entries(this);
  }
  
  Iterator<Map.Entry<K, V>> entryIterator() {
    return new Itr<Map.Entry<K, V>>(this) {
        Map.Entry<K, V> output(@ParametricNullness K key, @ParametricNullness V value) {
          return Maps.immutableEntry(key, value);
        }
      };
  }
  
  Spliterator<Map.Entry<K, V>> entrySpliterator() {
    return CollectSpliterators.flatMap(this.map
        .entrySet().spliterator(), keyToValueCollectionEntry -> {
          K key = (K)keyToValueCollectionEntry.getKey();
          Collection<V> valueCollection = (Collection<V>)keyToValueCollectionEntry.getValue();
          return CollectSpliterators.map(valueCollection.spliterator(), ());
        }64, 
        
        size());
  }
  
  public void forEach(BiConsumer<? super K, ? super V> action) {
    Preconditions.checkNotNull(action);
    this.map.forEach((key, valueCollection) -> valueCollection.forEach(()));
  }
  
  Map<K, Collection<V>> createAsMap() {
    return new AsMap(this.map);
  }
  
  final Map<K, Collection<V>> createMaybeNavigableAsMap() {
    if (this.map instanceof NavigableMap)
      return new NavigableAsMap((NavigableMap<K, Collection<V>>)this.map); 
    if (this.map instanceof SortedMap)
      return new SortedAsMap((SortedMap<K, Collection<V>>)this.map); 
    return new AsMap(this.map);
  }
  
  abstract Collection<V> createCollection();
  
  private class AsMap extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
    final transient Map<K, Collection<V>> submap;
    
    AsMap(Map<K, Collection<V>> submap) {
      this.submap = submap;
    }
    
    protected Set<Entry<K, Collection<V>>> createEntrySet() {
      return new AsMapEntries();
    }
    
    public boolean containsKey(@CheckForNull Object key) {
      return Maps.safeContainsKey(this.submap, key);
    }
    
    @CheckForNull
    public Collection<V> get(@CheckForNull Object key) {
      Collection<V> collection = Maps.<Collection<V>>safeGet(this.submap, key);
      if (collection == null)
        return null; 
      K k = (K)key;
      return AbstractMapBasedMultimap.this.wrapCollection(k, collection);
    }
    
    public Set<K> keySet() {
      return AbstractMapBasedMultimap.this.keySet();
    }
    
    public int size() {
      return this.submap.size();
    }
    
    @CheckForNull
    public Collection<V> remove(@CheckForNull Object key) {
      Collection<V> collection = this.submap.remove(key);
      if (collection == null)
        return null; 
      Collection<V> output = AbstractMapBasedMultimap.this.createCollection();
      output.addAll(collection);
      AbstractMapBasedMultimap.this.totalSize -= collection.size();
      collection.clear();
      return output;
    }
    
    public boolean equals(@CheckForNull Object object) {
      return (this == object || this.submap.equals(object));
    }
    
    public int hashCode() {
      return this.submap.hashCode();
    }
    
    public String toString() {
      return this.submap.toString();
    }
    
    public void clear() {
      if (this.submap == AbstractMapBasedMultimap.this.map) {
        AbstractMapBasedMultimap.this.clear();
      } else {
        Iterators.clear(new AsMapIterator());
      } 
    }
    
    Entry<K, Collection<V>> wrapEntry(Entry<K, Collection<V>> entry) {
      K key = entry.getKey();
      return Maps.immutableEntry(key, AbstractMapBasedMultimap.this.wrapCollection(key, entry.getValue()));
    }
    
    class AsMapEntries extends Maps.EntrySet<K, Collection<V>> {
      Map<K, Collection<V>> map() {
        return AsMap.this;
      }
      
      public Iterator<Entry<K, Collection<V>>> iterator() {
        return new AsMapIterator();
      }
      
      public Spliterator<Entry<K, Collection<V>>> spliterator() {
        return CollectSpliterators.map(AsMap.this.submap.entrySet().spliterator(), AsMap.this::wrapEntry);
      }
      
      public boolean contains(@CheckForNull Object o) {
        return Collections2.safeContains(AsMap.this.submap.entrySet(), o);
      }
      
      public boolean remove(@CheckForNull Object o) {
        if (!contains(o))
          return false; 
        Entry<?, ?> entry = Objects.<Entry<?, ?>>requireNonNull((Entry<?, ?>)o);
        AbstractMapBasedMultimap.this.removeValuesForKey(entry.getKey());
        return true;
      }
    }
    
    class AsMapIterator implements Iterator<Entry<K, Collection<V>>> {
      final Iterator<Entry<K, Collection<V>>> delegateIterator = AsMap.this.submap.entrySet().iterator();
      
      @CheckForNull
      Collection<V> collection;
      
      public boolean hasNext() {
        return this.delegateIterator.hasNext();
      }
      
      public Entry<K, Collection<V>> next() {
        Entry<K, Collection<V>> entry = this.delegateIterator.next();
        this.collection = entry.getValue();
        return AsMap.this.wrapEntry(entry);
      }
      
      public void remove() {
        Preconditions.checkState((this.collection != null), "no calls to next() since the last call to remove()");
        this.delegateIterator.remove();
        AbstractMapBasedMultimap.this.totalSize -= this.collection.size();
        this.collection.clear();
        this.collection = null;
      }
    }
  }
  
  private class SortedAsMap extends AsMap implements SortedMap<K, Collection<V>> {
    @CheckForNull
    SortedSet<K> sortedKeySet;
    
    SortedAsMap(SortedMap<K, Collection<V>> submap) {
      super(submap);
    }
    
    SortedMap<K, Collection<V>> sortedMap() {
      return (SortedMap<K, Collection<V>>)this.submap;
    }
    
    @CheckForNull
    public Comparator<? super K> comparator() {
      return sortedMap().comparator();
    }
    
    @ParametricNullness
    public K firstKey() {
      return sortedMap().firstKey();
    }
    
    @ParametricNullness
    public K lastKey() {
      return sortedMap().lastKey();
    }
    
    public SortedMap<K, Collection<V>> headMap(@ParametricNullness K toKey) {
      return new SortedAsMap(sortedMap().headMap(toKey));
    }
    
    public SortedMap<K, Collection<V>> subMap(@ParametricNullness K fromKey, @ParametricNullness K toKey) {
      return new SortedAsMap(sortedMap().subMap(fromKey, toKey));
    }
    
    public SortedMap<K, Collection<V>> tailMap(@ParametricNullness K fromKey) {
      return new SortedAsMap(sortedMap().tailMap(fromKey));
    }
    
    public SortedSet<K> keySet() {
      SortedSet<K> result = this.sortedKeySet;
      return (result == null) ? (this.sortedKeySet = createKeySet()) : result;
    }
    
    SortedSet<K> createKeySet() {
      return new SortedKeySet(sortedMap());
    }
  }
  
  class NavigableAsMap extends SortedAsMap implements NavigableMap<K, Collection<V>> {
    NavigableAsMap(NavigableMap<K, Collection<V>> submap) {
      super(submap);
    }
    
    NavigableMap<K, Collection<V>> sortedMap() {
      return (NavigableMap<K, Collection<V>>)super.sortedMap();
    }
    
    @CheckForNull
    public Map.Entry<K, Collection<V>> lowerEntry(@ParametricNullness K key) {
      Entry<K, Collection<V>> entry = sortedMap().lowerEntry(key);
      return (entry == null) ? null : wrapEntry(entry);
    }
    
    @CheckForNull
    public K lowerKey(@ParametricNullness K key) {
      return sortedMap().lowerKey(key);
    }
    
    @CheckForNull
    public Map.Entry<K, Collection<V>> floorEntry(@ParametricNullness K key) {
      Entry<K, Collection<V>> entry = sortedMap().floorEntry(key);
      return (entry == null) ? null : wrapEntry(entry);
    }
    
    @CheckForNull
    public K floorKey(@ParametricNullness K key) {
      return sortedMap().floorKey(key);
    }
    
    @CheckForNull
    public Map.Entry<K, Collection<V>> ceilingEntry(@ParametricNullness K key) {
      Entry<K, Collection<V>> entry = sortedMap().ceilingEntry(key);
      return (entry == null) ? null : wrapEntry(entry);
    }
    
    @CheckForNull
    public K ceilingKey(@ParametricNullness K key) {
      return sortedMap().ceilingKey(key);
    }
    
    @CheckForNull
    public Map.Entry<K, Collection<V>> higherEntry(@ParametricNullness K key) {
      Entry<K, Collection<V>> entry = sortedMap().higherEntry(key);
      return (entry == null) ? null : wrapEntry(entry);
    }
    
    @CheckForNull
    public K higherKey(@ParametricNullness K key) {
      return sortedMap().higherKey(key);
    }
    
    @CheckForNull
    public Map.Entry<K, Collection<V>> firstEntry() {
      Entry<K, Collection<V>> entry = sortedMap().firstEntry();
      return (entry == null) ? null : wrapEntry(entry);
    }
    
    @CheckForNull
    public Map.Entry<K, Collection<V>> lastEntry() {
      Entry<K, Collection<V>> entry = sortedMap().lastEntry();
      return (entry == null) ? null : wrapEntry(entry);
    }
    
    @CheckForNull
    public Map.Entry<K, Collection<V>> pollFirstEntry() {
      return pollAsMapEntry(entrySet().iterator());
    }
    
    @CheckForNull
    public Map.Entry<K, Collection<V>> pollLastEntry() {
      return pollAsMapEntry(descendingMap().entrySet().iterator());
    }
    
    @CheckForNull
    Map.Entry<K, Collection<V>> pollAsMapEntry(Iterator<Entry<K, Collection<V>>> entryIterator) {
      if (!entryIterator.hasNext())
        return null; 
      Entry<K, Collection<V>> entry = entryIterator.next();
      Collection<V> output = AbstractMapBasedMultimap.this.createCollection();
      output.addAll(entry.getValue());
      entryIterator.remove();
      return Maps.immutableEntry(entry.getKey(), AbstractMapBasedMultimap.this.unmodifiableCollectionSubclass(output));
    }
    
    public NavigableMap<K, Collection<V>> descendingMap() {
      return new NavigableAsMap(sortedMap().descendingMap());
    }
    
    public NavigableSet<K> keySet() {
      return (NavigableSet<K>)super.keySet();
    }
    
    NavigableSet<K> createKeySet() {
      return new NavigableKeySet(sortedMap());
    }
    
    public NavigableSet<K> navigableKeySet() {
      return keySet();
    }
    
    public NavigableSet<K> descendingKeySet() {
      return descendingMap().navigableKeySet();
    }
    
    public NavigableMap<K, Collection<V>> subMap(@ParametricNullness K fromKey, @ParametricNullness K toKey) {
      return subMap(fromKey, true, toKey, false);
    }
    
    public NavigableMap<K, Collection<V>> subMap(@ParametricNullness K fromKey, boolean fromInclusive, @ParametricNullness K toKey, boolean toInclusive) {
      return new NavigableAsMap(sortedMap().subMap(fromKey, fromInclusive, toKey, toInclusive));
    }
    
    public NavigableMap<K, Collection<V>> headMap(@ParametricNullness K toKey) {
      return headMap(toKey, false);
    }
    
    public NavigableMap<K, Collection<V>> headMap(@ParametricNullness K toKey, boolean inclusive) {
      return new NavigableAsMap(sortedMap().headMap(toKey, inclusive));
    }
    
    public NavigableMap<K, Collection<V>> tailMap(@ParametricNullness K fromKey) {
      return tailMap(fromKey, true);
    }
    
    public NavigableMap<K, Collection<V>> tailMap(@ParametricNullness K fromKey, boolean inclusive) {
      return new NavigableAsMap(sortedMap().tailMap(fromKey, inclusive));
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\AbstractMapBasedMultimap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */