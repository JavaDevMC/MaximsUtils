package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(emulated = true)
public final class Multimaps {
  public static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> toMultimap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction, Supplier<M> multimapSupplier) {
    return CollectCollectors.toMultimap(keyFunction, valueFunction, multimapSupplier);
  }
  
  @Beta
  public static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> flatteningToMultimap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends Stream<? extends V>> valueFunction, Supplier<M> multimapSupplier) {
    return CollectCollectors.flatteningToMultimap(keyFunction, valueFunction, multimapSupplier);
  }
  
  public static <K, V> Multimap<K, V> newMultimap(Map<K, Collection<V>> map, Supplier<? extends Collection<V>> factory) {
    return new CustomMultimap<>(map, factory);
  }
  
  private static class CustomMultimap<K, V> extends AbstractMapBasedMultimap<K, V> {
    transient Supplier<? extends Collection<V>> factory;
    
    @GwtIncompatible
    private static final long serialVersionUID = 0L;
    
    CustomMultimap(Map<K, Collection<V>> map, Supplier<? extends Collection<V>> factory) {
      super(map);
      this.factory = (Supplier<? extends Collection<V>>)Preconditions.checkNotNull(factory);
    }
    
    Set<K> createKeySet() {
      return createMaybeNavigableKeySet();
    }
    
    Map<K, Collection<V>> createAsMap() {
      return createMaybeNavigableAsMap();
    }
    
    protected Collection<V> createCollection() {
      return (Collection<V>)this.factory.get();
    }
    
    <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
      if (collection instanceof NavigableSet)
        return Sets.unmodifiableNavigableSet((NavigableSet<E>)collection); 
      if (collection instanceof SortedSet)
        return Collections.unmodifiableSortedSet((SortedSet<E>)collection); 
      if (collection instanceof Set)
        return Collections.unmodifiableSet((Set<? extends E>)collection); 
      if (collection instanceof List)
        return Collections.unmodifiableList((List<? extends E>)collection); 
      return Collections.unmodifiableCollection(collection);
    }
    
    Collection<V> wrapCollection(@ParametricNullness K key, Collection<V> collection) {
      if (collection instanceof List)
        return wrapList(key, (List<V>)collection, null); 
      if (collection instanceof NavigableSet)
        return new WrappedNavigableSet(this, key, (NavigableSet<V>)collection, null);
      if (collection instanceof SortedSet)
        return new WrappedSortedSet(this, key, (SortedSet<V>)collection, null);
      if (collection instanceof Set)
        return new WrappedSet(this, key, (Set<V>)collection);
      return new WrappedCollection(this, key, collection, null);
    }
    
    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeObject(this.factory);
      stream.writeObject(backingMap());
    }
    
    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.factory = (Supplier<? extends Collection<V>>)stream.readObject();
      Map<K, Collection<V>> map = (Map<K, Collection<V>>)stream.readObject();
      setMap(map);
    }
  }
  
  public static <K, V> ListMultimap<K, V> newListMultimap(Map<K, Collection<V>> map, Supplier<? extends List<V>> factory) {
    return new CustomListMultimap<>(map, factory);
  }
  
  private static class CustomListMultimap<K, V> extends AbstractListMultimap<K, V> {
    transient Supplier<? extends List<V>> factory;
    
    @GwtIncompatible
    private static final long serialVersionUID = 0L;
    
    CustomListMultimap(Map<K, Collection<V>> map, Supplier<? extends List<V>> factory) {
      super(map);
      this.factory = (Supplier<? extends List<V>>)Preconditions.checkNotNull(factory);
    }
    
    Set<K> createKeySet() {
      return createMaybeNavigableKeySet();
    }
    
    Map<K, Collection<V>> createAsMap() {
      return createMaybeNavigableAsMap();
    }
    
    protected List<V> createCollection() {
      return (List<V>)this.factory.get();
    }
    
    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeObject(this.factory);
      stream.writeObject(backingMap());
    }
    
    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.factory = (Supplier<? extends List<V>>)stream.readObject();
      Map<K, Collection<V>> map = (Map<K, Collection<V>>)stream.readObject();
      setMap(map);
    }
  }
  
  public static <K, V> SetMultimap<K, V> newSetMultimap(Map<K, Collection<V>> map, Supplier<? extends Set<V>> factory) {
    return new CustomSetMultimap<>(map, factory);
  }
  
  private static class CustomSetMultimap<K, V> extends AbstractSetMultimap<K, V> {
    transient Supplier<? extends Set<V>> factory;
    
    @GwtIncompatible
    private static final long serialVersionUID = 0L;
    
    CustomSetMultimap(Map<K, Collection<V>> map, Supplier<? extends Set<V>> factory) {
      super(map);
      this.factory = (Supplier<? extends Set<V>>)Preconditions.checkNotNull(factory);
    }
    
    Set<K> createKeySet() {
      return createMaybeNavigableKeySet();
    }
    
    Map<K, Collection<V>> createAsMap() {
      return createMaybeNavigableAsMap();
    }
    
    protected Set<V> createCollection() {
      return (Set<V>)this.factory.get();
    }
    
    <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
      if (collection instanceof NavigableSet)
        return Sets.unmodifiableNavigableSet((NavigableSet<E>)collection); 
      if (collection instanceof SortedSet)
        return Collections.unmodifiableSortedSet((SortedSet<E>)collection); 
      return Collections.unmodifiableSet((Set<? extends E>)collection);
    }
    
    Collection<V> wrapCollection(@ParametricNullness K key, Collection<V> collection) {
      if (collection instanceof NavigableSet)
        return new WrappedNavigableSet(this, key, (NavigableSet<V>)collection, null);
      if (collection instanceof SortedSet)
        return new WrappedSortedSet(this, key, (SortedSet<V>)collection, null);
      return new WrappedSet(this, key, (Set<V>)collection);
    }
    
    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeObject(this.factory);
      stream.writeObject(backingMap());
    }
    
    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.factory = (Supplier<? extends Set<V>>)stream.readObject();
      Map<K, Collection<V>> map = (Map<K, Collection<V>>)stream.readObject();
      setMap(map);
    }
  }
  
  public static <K, V> SortedSetMultimap<K, V> newSortedSetMultimap(Map<K, Collection<V>> map, Supplier<? extends SortedSet<V>> factory) {
    return new CustomSortedSetMultimap<>(map, factory);
  }
  
  private static class CustomSortedSetMultimap<K, V> extends AbstractSortedSetMultimap<K, V> {
    transient Supplier<? extends SortedSet<V>> factory;
    
    @CheckForNull
    transient Comparator<? super V> valueComparator;
    
    @GwtIncompatible
    private static final long serialVersionUID = 0L;
    
    CustomSortedSetMultimap(Map<K, Collection<V>> map, Supplier<? extends SortedSet<V>> factory) {
      super(map);
      this.factory = (Supplier<? extends SortedSet<V>>)Preconditions.checkNotNull(factory);
      this.valueComparator = ((SortedSet<V>)factory.get()).comparator();
    }
    
    Set<K> createKeySet() {
      return createMaybeNavigableKeySet();
    }
    
    Map<K, Collection<V>> createAsMap() {
      return createMaybeNavigableAsMap();
    }
    
    protected SortedSet<V> createCollection() {
      return (SortedSet<V>)this.factory.get();
    }
    
    @CheckForNull
    public Comparator<? super V> valueComparator() {
      return this.valueComparator;
    }
    
    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeObject(this.factory);
      stream.writeObject(backingMap());
    }
    
    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.factory = (Supplier<? extends SortedSet<V>>)stream.readObject();
      this.valueComparator = ((SortedSet<V>)this.factory.get()).comparator();
      Map<K, Collection<V>> map = (Map<K, Collection<V>>)stream.readObject();
      setMap(map);
    }
  }
  
  @CanIgnoreReturnValue
  public static <K, V, M extends Multimap<K, V>> M invertFrom(Multimap<? extends V, ? extends K> source, M dest) {
    Preconditions.checkNotNull(dest);
    for (Map.Entry<? extends V, ? extends K> entry : source.entries())
      dest.put(entry.getValue(), entry.getKey()); 
    return dest;
  }
  
  public static <K, V> Multimap<K, V> synchronizedMultimap(Multimap<K, V> multimap) {
    return Synchronized.multimap(multimap, null);
  }
  
  public static <K, V> Multimap<K, V> unmodifiableMultimap(Multimap<K, V> delegate) {
    if (delegate instanceof UnmodifiableMultimap || delegate instanceof ImmutableMultimap)
      return delegate; 
    return new UnmodifiableMultimap<>(delegate);
  }
  
  @Deprecated
  public static <K, V> Multimap<K, V> unmodifiableMultimap(ImmutableMultimap<K, V> delegate) {
    return (Multimap<K, V>)Preconditions.checkNotNull(delegate);
  }
  
  private static class UnmodifiableMultimap<K, V> extends ForwardingMultimap<K, V> implements Serializable {
    final Multimap<K, V> delegate;
    
    @LazyInit
    @CheckForNull
    transient Collection<Map.Entry<K, V>> entries;
    
    @LazyInit
    @CheckForNull
    transient Multiset<K> keys;
    
    @LazyInit
    @CheckForNull
    transient Set<K> keySet;
    
    @LazyInit
    @CheckForNull
    transient Collection<V> values;
    
    @LazyInit
    @CheckForNull
    transient Map<K, Collection<V>> map;
    
    private static final long serialVersionUID = 0L;
    
    UnmodifiableMultimap(Multimap<K, V> delegate) {
      this.delegate = (Multimap<K, V>)Preconditions.checkNotNull(delegate);
    }
    
    protected Multimap<K, V> delegate() {
      return this.delegate;
    }
    
    public void clear() {
      throw new UnsupportedOperationException();
    }
    
    public Map<K, Collection<V>> asMap() {
      Map<K, Collection<V>> result = this.map;
      if (result == null)
        result = this.map = Collections.<K, V>unmodifiableMap(
            Maps.transformValues(this.delegate
              .asMap(), new Function<Collection<V>, Collection<V>>(this) {
                public Collection<V> apply(Collection<V> collection) {
                  return Multimaps.unmodifiableValueCollection(collection);
                }
              })); 
      return result;
    }
    
    public Collection<Map.Entry<K, V>> entries() {
      Collection<Map.Entry<K, V>> result = this.entries;
      if (result == null)
        this.entries = result = Multimaps.unmodifiableEntries(this.delegate.entries()); 
      return result;
    }
    
    public void forEach(BiConsumer<? super K, ? super V> consumer) {
      this.delegate.forEach((BiConsumer<? super K, ? super V>)Preconditions.checkNotNull(consumer));
    }
    
    public Collection<V> get(@ParametricNullness K key) {
      return Multimaps.unmodifiableValueCollection(this.delegate.get(key));
    }
    
    public Multiset<K> keys() {
      Multiset<K> result = this.keys;
      if (result == null)
        this.keys = result = Multisets.unmodifiableMultiset(this.delegate.keys()); 
      return result;
    }
    
    public Set<K> keySet() {
      Set<K> result = this.keySet;
      if (result == null)
        this.keySet = result = Collections.unmodifiableSet(this.delegate.keySet()); 
      return result;
    }
    
    public boolean put(@ParametricNullness K key, @ParametricNullness V value) {
      throw new UnsupportedOperationException();
    }
    
    public boolean putAll(@ParametricNullness K key, Iterable<? extends V> values) {
      throw new UnsupportedOperationException();
    }
    
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
      throw new UnsupportedOperationException();
    }
    
    public Collection<V> removeAll(@CheckForNull Object key) {
      throw new UnsupportedOperationException();
    }
    
    public Collection<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
      throw new UnsupportedOperationException();
    }
    
    public Collection<V> values() {
      Collection<V> result = this.values;
      if (result == null)
        this.values = result = Collections.unmodifiableCollection(this.delegate.values()); 
      return result;
    }
  }
  
  private static class UnmodifiableListMultimap<K, V> extends UnmodifiableMultimap<K, V> implements ListMultimap<K, V> {
    private static final long serialVersionUID = 0L;
    
    UnmodifiableListMultimap(ListMultimap<K, V> delegate) {
      super(delegate);
    }
    
    public ListMultimap<K, V> delegate() {
      return (ListMultimap<K, V>)super.delegate();
    }
    
    public List<V> get(@ParametricNullness K key) {
      return Collections.unmodifiableList(delegate().get(key));
    }
    
    public List<V> removeAll(@CheckForNull Object key) {
      throw new UnsupportedOperationException();
    }
    
    public List<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
      throw new UnsupportedOperationException();
    }
  }
  
  private static class UnmodifiableSetMultimap<K, V> extends UnmodifiableMultimap<K, V> implements SetMultimap<K, V> {
    private static final long serialVersionUID = 0L;
    
    UnmodifiableSetMultimap(SetMultimap<K, V> delegate) {
      super(delegate);
    }
    
    public SetMultimap<K, V> delegate() {
      return (SetMultimap<K, V>)super.delegate();
    }
    
    public Set<V> get(@ParametricNullness K key) {
      return Collections.unmodifiableSet(delegate().get(key));
    }
    
    public Set<Map.Entry<K, V>> entries() {
      return Maps.unmodifiableEntrySet(delegate().entries());
    }
    
    public Set<V> removeAll(@CheckForNull Object key) {
      throw new UnsupportedOperationException();
    }
    
    public Set<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
      throw new UnsupportedOperationException();
    }
  }
  
  private static class UnmodifiableSortedSetMultimap<K, V> extends UnmodifiableSetMultimap<K, V> implements SortedSetMultimap<K, V> {
    private static final long serialVersionUID = 0L;
    
    UnmodifiableSortedSetMultimap(SortedSetMultimap<K, V> delegate) {
      super(delegate);
    }
    
    public SortedSetMultimap<K, V> delegate() {
      return (SortedSetMultimap<K, V>)super.delegate();
    }
    
    public SortedSet<V> get(@ParametricNullness K key) {
      return Collections.unmodifiableSortedSet(delegate().get(key));
    }
    
    public SortedSet<V> removeAll(@CheckForNull Object key) {
      throw new UnsupportedOperationException();
    }
    
    public SortedSet<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
      throw new UnsupportedOperationException();
    }
    
    @CheckForNull
    public Comparator<? super V> valueComparator() {
      return delegate().valueComparator();
    }
  }
  
  public static <K, V> SetMultimap<K, V> synchronizedSetMultimap(SetMultimap<K, V> multimap) {
    return Synchronized.setMultimap(multimap, null);
  }
  
  public static <K, V> SetMultimap<K, V> unmodifiableSetMultimap(SetMultimap<K, V> delegate) {
    if (delegate instanceof UnmodifiableSetMultimap || delegate instanceof ImmutableSetMultimap)
      return delegate; 
    return new UnmodifiableSetMultimap<>(delegate);
  }
  
  @Deprecated
  public static <K, V> SetMultimap<K, V> unmodifiableSetMultimap(ImmutableSetMultimap<K, V> delegate) {
    return (SetMultimap<K, V>)Preconditions.checkNotNull(delegate);
  }
  
  public static <K, V> SortedSetMultimap<K, V> synchronizedSortedSetMultimap(SortedSetMultimap<K, V> multimap) {
    return Synchronized.sortedSetMultimap(multimap, null);
  }
  
  public static <K, V> SortedSetMultimap<K, V> unmodifiableSortedSetMultimap(SortedSetMultimap<K, V> delegate) {
    if (delegate instanceof UnmodifiableSortedSetMultimap)
      return delegate; 
    return new UnmodifiableSortedSetMultimap<>(delegate);
  }
  
  public static <K, V> ListMultimap<K, V> synchronizedListMultimap(ListMultimap<K, V> multimap) {
    return Synchronized.listMultimap(multimap, null);
  }
  
  public static <K, V> ListMultimap<K, V> unmodifiableListMultimap(ListMultimap<K, V> delegate) {
    if (delegate instanceof UnmodifiableListMultimap || delegate instanceof ImmutableListMultimap)
      return delegate; 
    return new UnmodifiableListMultimap<>(delegate);
  }
  
  @Deprecated
  public static <K, V> ListMultimap<K, V> unmodifiableListMultimap(ImmutableListMultimap<K, V> delegate) {
    return (ListMultimap<K, V>)Preconditions.checkNotNull(delegate);
  }
  
  private static <V> Collection<V> unmodifiableValueCollection(Collection<V> collection) {
    if (collection instanceof SortedSet)
      return Collections.unmodifiableSortedSet((SortedSet<V>)collection); 
    if (collection instanceof Set)
      return Collections.unmodifiableSet((Set<? extends V>)collection); 
    if (collection instanceof List)
      return Collections.unmodifiableList((List<? extends V>)collection); 
    return Collections.unmodifiableCollection(collection);
  }
  
  private static <K, V> Collection<Map.Entry<K, V>> unmodifiableEntries(Collection<Map.Entry<K, V>> entries) {
    if (entries instanceof Set)
      return Maps.unmodifiableEntrySet((Set<Map.Entry<K, V>>)entries); 
    return new Maps.UnmodifiableEntries<>(Collections.unmodifiableCollection(entries));
  }
  
  @Beta
  public static <K, V> Map<K, List<V>> asMap(ListMultimap<K, V> multimap) {
    return (Map)multimap.asMap();
  }
  
  @Beta
  public static <K, V> Map<K, Set<V>> asMap(SetMultimap<K, V> multimap) {
    return (Map)multimap.asMap();
  }
  
  @Beta
  public static <K, V> Map<K, SortedSet<V>> asMap(SortedSetMultimap<K, V> multimap) {
    return (Map)multimap.asMap();
  }
  
  @Beta
  public static <K, V> Map<K, Collection<V>> asMap(Multimap<K, V> multimap) {
    return multimap.asMap();
  }
  
  public static <K, V> SetMultimap<K, V> forMap(Map<K, V> map) {
    return new MapMultimap<>(map);
  }
  
  private static class MapMultimap<K, V> extends AbstractMultimap<K, V> implements SetMultimap<K, V>, Serializable {
    final Map<K, V> map;
    
    private static final long serialVersionUID = 7845222491160860175L;
    
    MapMultimap(Map<K, V> map) {
      this.map = (Map<K, V>)Preconditions.checkNotNull(map);
    }
    
    public int size() {
      return this.map.size();
    }
    
    public boolean containsKey(@CheckForNull Object key) {
      return this.map.containsKey(key);
    }
    
    public boolean containsValue(@CheckForNull Object value) {
      return this.map.containsValue(value);
    }
    
    public boolean containsEntry(@CheckForNull Object key, @CheckForNull Object value) {
      return this.map.entrySet().contains(Maps.immutableEntry(key, value));
    }
    
    public Set<V> get(@ParametricNullness final K key) {
      return new Sets.ImprovedAbstractSet<V>() {
          public Iterator<V> iterator() {
            return new Iterator<V>() {
                int i;
                
                public boolean hasNext() {
                  return (this.i == 0 && MapMultimap.this.map.containsKey(key));
                }
                
                @ParametricNullness
                public V next() {
                  if (!hasNext())
                    throw new NoSuchElementException(); 
                  this.i++;
                  return NullnessCasts.uncheckedCastNullableTToT((V) MapMultimap.this.map.get(key));
                }
                
                public void remove() {
                  CollectPreconditions.checkRemove((this.i == 1));
                  this.i = -1;
                  MapMultimap.this.map.remove(key);
                }
              };
          }
          
          public int size() {
            return MapMultimap.this.map.containsKey(key) ? 1 : 0;
          }
        };
    }
    
    public boolean put(@ParametricNullness K key, @ParametricNullness V value) {
      throw new UnsupportedOperationException();
    }
    
    public boolean putAll(@ParametricNullness K key, Iterable<? extends V> values) {
      throw new UnsupportedOperationException();
    }
    
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
      throw new UnsupportedOperationException();
    }
    
    public Set<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
      return this.map.entrySet().remove(Maps.immutableEntry(key, value));
    }
    
    public Set<V> removeAll(@CheckForNull Object key) {
      Set<V> values = new HashSet<>(2);
      if (!this.map.containsKey(key))
        return values; 
      values.add(this.map.remove(key));
      return values;
    }
    
    public void clear() {
      this.map.clear();
    }
    
    Set<K> createKeySet() {
      return this.map.keySet();
    }
    
    Collection<V> createValues() {
      return this.map.values();
    }
    
    public Set<Map.Entry<K, V>> entries() {
      return this.map.entrySet();
    }
    
    Collection<Map.Entry<K, V>> createEntries() {
      throw new AssertionError("unreachable");
    }
    
    Multiset<K> createKeys() {
      return new Keys<>(this);
    }
    
    Iterator<Map.Entry<K, V>> entryIterator() {
      return this.map.entrySet().iterator();
    }
    
    Map<K, Collection<V>> createAsMap() {
      return new AsMap<>(this);
    }
    
    public int hashCode() {
      return this.map.hashCode();
    }
  }
  
  public static <K, V1, V2> Multimap<K, V2> transformValues(Multimap<K, V1> fromMultimap, Function<? super V1, V2> function) {
    Preconditions.checkNotNull(function);
    Maps.EntryTransformer<K, V1, V2> transformer = Maps.asEntryTransformer(function);
    return transformEntries(fromMultimap, transformer);
  }
  
  public static <K, V1, V2> ListMultimap<K, V2> transformValues(ListMultimap<K, V1> fromMultimap, Function<? super V1, V2> function) {
    Preconditions.checkNotNull(function);
    Maps.EntryTransformer<K, V1, V2> transformer = Maps.asEntryTransformer(function);
    return transformEntries(fromMultimap, transformer);
  }
  
  public static <K, V1, V2> Multimap<K, V2> transformEntries(Multimap<K, V1> fromMap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
    return new TransformedEntriesMultimap<>(fromMap, transformer);
  }
  
  public static <K, V1, V2> ListMultimap<K, V2> transformEntries(ListMultimap<K, V1> fromMap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
    return new TransformedEntriesListMultimap<>(fromMap, transformer);
  }
  
  private static class TransformedEntriesMultimap<K, V1, V2> extends AbstractMultimap<K, V2> {
    final Multimap<K, V1> fromMultimap;
    
    final Maps.EntryTransformer<? super K, ? super V1, V2> transformer;
    
    TransformedEntriesMultimap(Multimap<K, V1> fromMultimap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
      this.fromMultimap = (Multimap<K, V1>)Preconditions.checkNotNull(fromMultimap);
      this.transformer = (Maps.EntryTransformer<? super K, ? super V1, V2>)Preconditions.checkNotNull(transformer);
    }
    
    Collection<V2> transform(@ParametricNullness K key, Collection<V1> values) {
      Function<? super V1, V2> function = Maps.asValueToValueFunction(this.transformer, key);
      if (values instanceof List)
        return Lists.transform((List<V1>)values, function); 
      return Collections2.transform(values, function);
    }
    
    Map<K, Collection<V2>> createAsMap() {
      return Maps.transformEntries(this.fromMultimap
          .asMap(), (Maps.EntryTransformer)new Maps.EntryTransformer<K, Collection<Collection<V1>>, Collection<Collection<V2>>>() {
            public Collection<V2> transformEntry(@ParametricNullness K key, Collection<V1> value) {
              return TransformedEntriesMultimap.this.transform(key, value);
            }
          });
    }
    
    public void clear() {
      this.fromMultimap.clear();
    }
    
    public boolean containsKey(@CheckForNull Object key) {
      return this.fromMultimap.containsKey(key);
    }
    
    Collection<Map.Entry<K, V2>> createEntries() {
      return new Entries(this);
    }
    
    Iterator<Map.Entry<K, V2>> entryIterator() {
      return Iterators.transform(this.fromMultimap
          .entries().iterator(), Maps.asEntryToEntryFunction(this.transformer));
    }
    
    public Collection<V2> get(@ParametricNullness K key) {
      return transform(key, this.fromMultimap.get(key));
    }
    
    public boolean isEmpty() {
      return this.fromMultimap.isEmpty();
    }
    
    Set<K> createKeySet() {
      return this.fromMultimap.keySet();
    }
    
    Multiset<K> createKeys() {
      return this.fromMultimap.keys();
    }
    
    public boolean put(@ParametricNullness K key, @ParametricNullness V2 value) {
      throw new UnsupportedOperationException();
    }
    
    public boolean putAll(@ParametricNullness K key, Iterable<? extends V2> values) {
      throw new UnsupportedOperationException();
    }
    
    public boolean putAll(Multimap<? extends K, ? extends V2> multimap) {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
      return get((K)key).remove(value);
    }
    
    public Collection<V2> removeAll(@CheckForNull Object key) {
      return transform((K)key, this.fromMultimap.removeAll(key));
    }
    
    public Collection<V2> replaceValues(@ParametricNullness K key, Iterable<? extends V2> values) {
      throw new UnsupportedOperationException();
    }
    
    public int size() {
      return this.fromMultimap.size();
    }
    
    Collection<V2> createValues() {
      return Collections2.transform(this.fromMultimap
          .entries(), Maps.asEntryToValueFunction(this.transformer));
    }
  }
  
  private static final class TransformedEntriesListMultimap<K, V1, V2> extends TransformedEntriesMultimap<K, V1, V2> implements ListMultimap<K, V2> {
    TransformedEntriesListMultimap(ListMultimap<K, V1> fromMultimap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
      super(fromMultimap, transformer);
    }
    
    List<V2> transform(@ParametricNullness K key, Collection<V1> values) {
      return Lists.transform((List)values, Maps.asValueToValueFunction(this.transformer, key));
    }
    
    public List<V2> get(@ParametricNullness K key) {
      return transform(key, this.fromMultimap.get(key));
    }
    
    public List<V2> removeAll(@CheckForNull Object key) {
      return transform((K)key, this.fromMultimap.removeAll(key));
    }
    
    public List<V2> replaceValues(@ParametricNullness K key, Iterable<? extends V2> values) {
      throw new UnsupportedOperationException();
    }
  }
  
  public static <K, V> ImmutableListMultimap<K, V> index(Iterable<V> values, Function<? super V, K> keyFunction) {
    return index(values.iterator(), keyFunction);
  }
  
  public static <K, V> ImmutableListMultimap<K, V> index(Iterator<V> values, Function<? super V, K> keyFunction) {
    Preconditions.checkNotNull(keyFunction);
    ImmutableListMultimap.Builder<K, V> builder = ImmutableListMultimap.builder();
    while (values.hasNext()) {
      V value = values.next();
      Preconditions.checkNotNull(value, values);
      builder.put((K)keyFunction.apply(value), value);
    } 
    return builder.build();
  }
  
  static class Keys<K, V> extends AbstractMultiset<K> {
    @Weak
    final Multimap<K, V> multimap;
    
    Keys(Multimap<K, V> multimap) {
      this.multimap = multimap;
    }
    
    Iterator<Entry<K>> entryIterator() {
      return new TransformedIterator<Map.Entry<K, Collection<V>>, Entry<K>>(this, this.multimap
          .asMap().entrySet().iterator()) {
          Entry<K> transform(final Map.Entry<K, Collection<V>> backingEntry) {
            return new Multisets.AbstractEntry<K>(this) {
                @ParametricNullness
                public K getElement() {
                  return (K)backingEntry.getKey();
                }
                
                public int getCount() {
                  return ((Collection)backingEntry.getValue()).size();
                }
              };
          }
        };
    }
    
    public Spliterator<K> spliterator() {
      return CollectSpliterators.map(this.multimap.entries().spliterator(), Map.Entry::getKey);
    }
    
    public void forEach(Consumer<? super K> consumer) {
      Preconditions.checkNotNull(consumer);
      this.multimap.entries().forEach(entry -> consumer.accept(entry.getKey()));
    }
    
    int distinctElements() {
      return this.multimap.asMap().size();
    }
    
    public int size() {
      return this.multimap.size();
    }
    
    public boolean contains(@CheckForNull Object element) {
      return this.multimap.containsKey(element);
    }
    
    public Iterator<K> iterator() {
      return Maps.keyIterator(this.multimap.entries().iterator());
    }
    
    public int count(@CheckForNull Object element) {
      Collection<V> values = Maps.<Collection<V>>safeGet(this.multimap.asMap(), element);
      return (values == null) ? 0 : values.size();
    }
    
    public int remove(@CheckForNull Object element, int occurrences) {
      CollectPreconditions.checkNonnegative(occurrences, "occurrences");
      if (occurrences == 0)
        return count(element); 
      Collection<V> values = Maps.<Collection<V>>safeGet(this.multimap.asMap(), element);
      if (values == null)
        return 0; 
      int oldCount = values.size();
      if (occurrences >= oldCount) {
        values.clear();
      } else {
        Iterator<V> iterator = values.iterator();
        for (int i = 0; i < occurrences; i++) {
          iterator.next();
          iterator.remove();
        } 
      } 
      return oldCount;
    }
    
    public void clear() {
      this.multimap.clear();
    }
    
    public Set<K> elementSet() {
      return this.multimap.keySet();
    }
    
    Iterator<K> elementIterator() {
      throw new AssertionError("should never be called");
    }
  }
  
  static abstract class Entries<K, V> extends AbstractCollection<Map.Entry<K, V>> {
    abstract Multimap<K, V> multimap();
    
    public int size() {
      return multimap().size();
    }
    
    public boolean contains(@CheckForNull Object o) {
      if (o instanceof Map.Entry) {
        Map.Entry<?, ?> entry = (Map.Entry<?, ?>)o;
        return multimap().containsEntry(entry.getKey(), entry.getValue());
      } 
      return false;
    }
    
    public boolean remove(@CheckForNull Object o) {
      if (o instanceof Map.Entry) {
        Map.Entry<?, ?> entry = (Map.Entry<?, ?>)o;
        return multimap().remove(entry.getKey(), entry.getValue());
      } 
      return false;
    }
    
    public void clear() {
      multimap().clear();
    }
  }
  
  static final class AsMap<K, V> extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
    @Weak
    private final Multimap<K, V> multimap;
    
    AsMap(Multimap<K, V> multimap) {
      this.multimap = (Multimap<K, V>)Preconditions.checkNotNull(multimap);
    }
    
    public int size() {
      return this.multimap.keySet().size();
    }
    
    protected Set<Entry<K, Collection<V>>> createEntrySet() {
      return new EntrySet();
    }
    
    void removeValuesForKey(@CheckForNull Object key) {
      this.multimap.keySet().remove(key);
    }
    
    class EntrySet extends Maps.EntrySet<K, Collection<V>> {
      Map<K, Collection<V>> map() {
        return AsMap.this;
      }
      
      public Iterator<Entry<K, Collection<V>>> iterator() {
        return Maps.asMapEntryIterator(AsMap.this
            .multimap.keySet(), new Function<K, Collection<V>>() {
              public Collection<V> apply(@ParametricNullness K key) {
                return AsMap.this.multimap.get(key);
              }
            });
      }
      
      public boolean remove(@CheckForNull Object o) {
        if (!contains(o))
          return false; 
        Entry<?, ?> entry = Objects.<Entry<?, ?>>requireNonNull((Entry<?, ?>)o);
        AsMap.this.removeValuesForKey(entry.getKey());
        return true;
      }
    }
    
    @CheckForNull
    public Collection<V> get(@CheckForNull Object key) {
      return containsKey(key) ? this.multimap.get((K)key) : null;
    }
    
    @CheckForNull
    public Collection<V> remove(@CheckForNull Object key) {
      return containsKey(key) ? this.multimap.removeAll(key) : null;
    }
    
    public Set<K> keySet() {
      return this.multimap.keySet();
    }
    
    public boolean isEmpty() {
      return this.multimap.isEmpty();
    }
    
    public boolean containsKey(@CheckForNull Object key) {
      return this.multimap.containsKey(key);
    }
    
    public void clear() {
      this.multimap.clear();
    }
  }
  
  public static <K, V> Multimap<K, V> filterKeys(Multimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
    if (unfiltered instanceof SetMultimap)
      return filterKeys((SetMultimap<K, V>)unfiltered, keyPredicate); 
    if (unfiltered instanceof ListMultimap)
      return filterKeys((ListMultimap<K, V>)unfiltered, keyPredicate); 
    if (unfiltered instanceof FilteredKeyMultimap) {
      FilteredKeyMultimap<K, V> prev = (FilteredKeyMultimap<K, V>)unfiltered;
      return new FilteredKeyMultimap<>(prev.unfiltered, 
          Predicates.and(prev.keyPredicate, keyPredicate));
    } 
    if (unfiltered instanceof FilteredMultimap) {
      FilteredMultimap<K, V> prev = (FilteredMultimap<K, V>)unfiltered;
      return filterFiltered(prev, (Predicate)Maps.keyPredicateOnEntries(keyPredicate));
    } 
    return new FilteredKeyMultimap<>(unfiltered, keyPredicate);
  }
  
  public static <K, V> SetMultimap<K, V> filterKeys(SetMultimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
    if (unfiltered instanceof FilteredKeySetMultimap) {
      FilteredKeySetMultimap<K, V> prev = (FilteredKeySetMultimap<K, V>)unfiltered;
      return new FilteredKeySetMultimap<>(prev
          .unfiltered(), Predicates.and(prev.keyPredicate, keyPredicate));
    } 
    if (unfiltered instanceof FilteredSetMultimap) {
      FilteredSetMultimap<K, V> prev = (FilteredSetMultimap<K, V>)unfiltered;
      return filterFiltered(prev, (Predicate)Maps.keyPredicateOnEntries(keyPredicate));
    } 
    return new FilteredKeySetMultimap<>(unfiltered, keyPredicate);
  }
  
  public static <K, V> ListMultimap<K, V> filterKeys(ListMultimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
    if (unfiltered instanceof FilteredKeyListMultimap) {
      FilteredKeyListMultimap<K, V> prev = (FilteredKeyListMultimap<K, V>)unfiltered;
      return new FilteredKeyListMultimap<>(prev
          .unfiltered(), Predicates.and(prev.keyPredicate, keyPredicate));
    } 
    return new FilteredKeyListMultimap<>(unfiltered, keyPredicate);
  }
  
  public static <K, V> Multimap<K, V> filterValues(Multimap<K, V> unfiltered, Predicate<? super V> valuePredicate) {
    return filterEntries(unfiltered, (Predicate)Maps.valuePredicateOnEntries(valuePredicate));
  }
  
  public static <K, V> SetMultimap<K, V> filterValues(SetMultimap<K, V> unfiltered, Predicate<? super V> valuePredicate) {
    return filterEntries(unfiltered, (Predicate)Maps.valuePredicateOnEntries(valuePredicate));
  }
  
  public static <K, V> Multimap<K, V> filterEntries(Multimap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
    Preconditions.checkNotNull(entryPredicate);
    if (unfiltered instanceof SetMultimap)
      return filterEntries((SetMultimap<K, V>)unfiltered, entryPredicate); 
    return (unfiltered instanceof FilteredMultimap) ? 
      filterFiltered((FilteredMultimap<K, V>)unfiltered, entryPredicate) : 
      new FilteredEntryMultimap<>((Multimap<K, V>)Preconditions.checkNotNull(unfiltered), entryPredicate);
  }
  
  public static <K, V> SetMultimap<K, V> filterEntries(SetMultimap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
    Preconditions.checkNotNull(entryPredicate);
    return (unfiltered instanceof FilteredSetMultimap) ? 
      filterFiltered((FilteredSetMultimap<K, V>)unfiltered, entryPredicate) : 
      new FilteredEntrySetMultimap<>((SetMultimap<K, V>)Preconditions.checkNotNull(unfiltered), entryPredicate);
  }
  
  private static <K, V> Multimap<K, V> filterFiltered(FilteredMultimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> entryPredicate) {
    Predicate<Map.Entry<K, V>> predicate = Predicates.and(multimap.entryPredicate(), entryPredicate);
    return new FilteredEntryMultimap<>(multimap.unfiltered(), predicate);
  }
  
  private static <K, V> SetMultimap<K, V> filterFiltered(FilteredSetMultimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> entryPredicate) {
    Predicate<Map.Entry<K, V>> predicate = Predicates.and(multimap.entryPredicate(), entryPredicate);
    return new FilteredEntrySetMultimap<>(multimap.unfiltered(), predicate);
  }
  
  static boolean equalsImpl(Multimap<?, ?> multimap, @CheckForNull Object object) {
    if (object == multimap)
      return true; 
    if (object instanceof Multimap) {
      Multimap<?, ?> that = (Multimap<?, ?>)object;
      return multimap.asMap().equals(that.asMap());
    } 
    return false;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\Multimaps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */