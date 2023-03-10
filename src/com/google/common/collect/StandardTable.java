package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
class StandardTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {
  @GwtTransient
  final Map<R, Map<C, V>> backingMap;
  
  @GwtTransient
  final Supplier<? extends Map<C, V>> factory;
  
  @CheckForNull
  private transient Set<C> columnKeySet;
  
  @CheckForNull
  private transient Map<R, Map<C, V>> rowMap;
  
  @CheckForNull
  private transient ColumnMap columnMap;
  
  private static final long serialVersionUID = 0L;
  
  StandardTable(Map<R, Map<C, V>> backingMap, Supplier<? extends Map<C, V>> factory) {
    this.backingMap = backingMap;
    this.factory = factory;
  }
  
  public boolean contains(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
    return (rowKey != null && columnKey != null && super.contains(rowKey, columnKey));
  }
  
  public boolean containsColumn(@CheckForNull Object columnKey) {
    if (columnKey == null)
      return false; 
    for (Map<C, V> map : this.backingMap.values()) {
      if (Maps.safeContainsKey(map, columnKey))
        return true; 
    } 
    return false;
  }
  
  public boolean containsRow(@CheckForNull Object rowKey) {
    return (rowKey != null && Maps.safeContainsKey(this.backingMap, rowKey));
  }
  
  public boolean containsValue(@CheckForNull Object value) {
    return (value != null && super.containsValue(value));
  }
  
  @CheckForNull
  public V get(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
    return (rowKey == null || columnKey == null) ? null : super.get(rowKey, columnKey);
  }
  
  public boolean isEmpty() {
    return this.backingMap.isEmpty();
  }
  
  public int size() {
    int size = 0;
    for (Map<C, V> map : this.backingMap.values())
      size += map.size(); 
    return size;
  }
  
  public void clear() {
    this.backingMap.clear();
  }
  
  private Map<C, V> getOrCreate(R rowKey) {
    Map<C, V> map = this.backingMap.get(rowKey);
    if (map == null) {
      map = (Map<C, V>)this.factory.get();
      this.backingMap.put(rowKey, map);
    } 
    return map;
  }
  
  @CheckForNull
  @CanIgnoreReturnValue
  public V put(R rowKey, C columnKey, V value) {
    Preconditions.checkNotNull(rowKey);
    Preconditions.checkNotNull(columnKey);
    Preconditions.checkNotNull(value);
    return getOrCreate(rowKey).put(columnKey, value);
  }
  
  @CheckForNull
  @CanIgnoreReturnValue
  public V remove(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
    if (rowKey == null || columnKey == null)
      return null; 
    Map<C, V> map = Maps.<Map<C, V>>safeGet(this.backingMap, rowKey);
    if (map == null)
      return null; 
    V value = map.remove(columnKey);
    if (map.isEmpty())
      this.backingMap.remove(rowKey); 
    return value;
  }
  
  @CanIgnoreReturnValue
  private Map<R, V> removeColumn(@CheckForNull Object column) {
    Map<R, V> output = new LinkedHashMap<>();
    Iterator<Map.Entry<R, Map<C, V>>> iterator = this.backingMap.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<R, Map<C, V>> entry = iterator.next();
      V value = (V)((Map)entry.getValue()).remove(column);
      if (value != null) {
        output.put(entry.getKey(), value);
        if (((Map)entry.getValue()).isEmpty())
          iterator.remove(); 
      } 
    } 
    return output;
  }
  
  private boolean containsMapping(@CheckForNull Object rowKey, @CheckForNull Object columnKey, @CheckForNull Object value) {
    return (value != null && value.equals(get(rowKey, columnKey)));
  }
  
  private boolean removeMapping(@CheckForNull Object rowKey, @CheckForNull Object columnKey, @CheckForNull Object value) {
    if (containsMapping(rowKey, columnKey, value)) {
      remove(rowKey, columnKey);
      return true;
    } 
    return false;
  }
  
  private abstract class TableSet<T> extends Sets.ImprovedAbstractSet<T> {
    private TableSet() {}
    
    public boolean isEmpty() {
      return StandardTable.this.backingMap.isEmpty();
    }
    
    public void clear() {
      StandardTable.this.backingMap.clear();
    }
  }
  
  public Set<Cell<R, C, V>> cellSet() {
    return super.cellSet();
  }
  
  Iterator<Cell<R, C, V>> cellIterator() {
    return new CellIterator();
  }
  
  private class CellIterator implements Iterator<Cell<R, C, V>> {
    final Iterator<Map.Entry<R, Map<C, V>>> rowIterator = StandardTable.this.backingMap.entrySet().iterator();
    
    @CheckForNull
    Map.Entry<R, Map<C, V>> rowEntry;
    
    Iterator<Map.Entry<C, V>> columnIterator = Iterators.emptyModifiableIterator();
    
    public boolean hasNext() {
      return (this.rowIterator.hasNext() || this.columnIterator.hasNext());
    }
    
    public Cell<R, C, V> next() {
      if (!this.columnIterator.hasNext()) {
        this.rowEntry = this.rowIterator.next();
        this.columnIterator = ((Map<C, V>)this.rowEntry.getValue()).entrySet().iterator();
      } 
      Objects.requireNonNull(this.rowEntry);
      Map.Entry<C, V> columnEntry = this.columnIterator.next();
      return Tables.immutableCell(this.rowEntry.getKey(), columnEntry.getKey(), columnEntry.getValue());
    }
    
    public void remove() {
      this.columnIterator.remove();
      if (((Map)((Map.Entry)Objects.<Map.Entry>requireNonNull(this.rowEntry)).getValue()).isEmpty()) {
        this.rowIterator.remove();
        this.rowEntry = null;
      } 
    }
    
    private CellIterator() {}
  }
  
  Spliterator<Cell<R, C, V>> cellSpliterator() {
    return CollectSpliterators.flatMap(this.backingMap
        .entrySet().spliterator(), rowEntry -> CollectSpliterators.map(((Map)rowEntry.getValue()).entrySet().spliterator(), ()), 65, 
        
        size());
  }
  
  public Map<C, V> row(R rowKey) {
    return new Row(rowKey);
  }
  
  class Row extends Maps.IteratorBasedAbstractMap<C, V> {
    final R rowKey;
    
    @CheckForNull
    Map<C, V> backingRowMap;
    
    Row(R rowKey) {
      this.rowKey = (R)Preconditions.checkNotNull(rowKey);
    }
    
    final void updateBackingRowMapField() {
      if (this.backingRowMap == null || (this.backingRowMap.isEmpty() && StandardTable.this.backingMap.containsKey(this.rowKey)))
        this.backingRowMap = computeBackingRowMap(); 
    }
    
    @CheckForNull
    Map<C, V> computeBackingRowMap() {
      return (Map<C, V>)StandardTable.this.backingMap.get(this.rowKey);
    }
    
    void maintainEmptyInvariant() {
      updateBackingRowMapField();
      if (this.backingRowMap != null && this.backingRowMap.isEmpty()) {
        StandardTable.this.backingMap.remove(this.rowKey);
        this.backingRowMap = null;
      } 
    }
    
    public boolean containsKey(@CheckForNull Object key) {
      updateBackingRowMapField();
      return (key != null && this.backingRowMap != null && Maps.safeContainsKey(this.backingRowMap, key));
    }
    
    @CheckForNull
    public V get(@CheckForNull Object key) {
      updateBackingRowMapField();
      return (key != null && this.backingRowMap != null) ? Maps.<V>safeGet(this.backingRowMap, key) : null;
    }
    
    @CheckForNull
    public V put(C key, V value) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(value);
      if (this.backingRowMap != null && !this.backingRowMap.isEmpty())
        return this.backingRowMap.put(key, value); 
      return StandardTable.this.put(this.rowKey, key, value);
    }
    
    @CheckForNull
    public V remove(@CheckForNull Object key) {
      updateBackingRowMapField();
      if (this.backingRowMap == null)
        return null; 
      V result = Maps.safeRemove(this.backingRowMap, key);
      maintainEmptyInvariant();
      return result;
    }
    
    public void clear() {
      updateBackingRowMapField();
      if (this.backingRowMap != null)
        this.backingRowMap.clear(); 
      maintainEmptyInvariant();
    }
    
    public int size() {
      updateBackingRowMapField();
      return (this.backingRowMap == null) ? 0 : this.backingRowMap.size();
    }
    
    Iterator<Entry<C, V>> entryIterator() {
      updateBackingRowMapField();
      if (this.backingRowMap == null)
        return Iterators.emptyModifiableIterator(); 
      final Iterator<Entry<C, V>> iterator = this.backingRowMap.entrySet().iterator();
      return new Iterator<Entry<C, V>>() {
          public boolean hasNext() {
            return iterator.hasNext();
          }
          
          public Entry<C, V> next() {
            return Row.this.wrapEntry(iterator.next());
          }
          
          public void remove() {
            iterator.remove();
            Row.this.maintainEmptyInvariant();
          }
        };
    }
    
    Spliterator<Entry<C, V>> entrySpliterator() {
      updateBackingRowMapField();
      if (this.backingRowMap == null)
        return Spliterators.emptySpliterator(); 
      return CollectSpliterators.map(this.backingRowMap.entrySet().spliterator(), this::wrapEntry);
    }
    
    Entry<C, V> wrapEntry(final Entry<C, V> entry) {
      return new ForwardingMapEntry<C, V>(this) {
          protected Entry<C, V> delegate() {
            return entry;
          }
          
          public V setValue(V value) {
            return super.setValue((V)Preconditions.checkNotNull(value));
          }
          
          public boolean equals(@CheckForNull Object object) {
            return standardEquals(object);
          }
        };
    }
  }
  
  public Map<R, V> column(C columnKey) {
    return new Column(columnKey);
  }
  
  private class Column extends Maps.ViewCachingAbstractMap<R, V> {
    final C columnKey;
    
    Column(C columnKey) {
      this.columnKey = (C)Preconditions.checkNotNull(columnKey);
    }
    
    @CheckForNull
    public V put(R key, V value) {
      return StandardTable.this.put(key, this.columnKey, value);
    }
    
    @CheckForNull
    public V get(@CheckForNull Object key) {
      return (V)StandardTable.this.get(key, this.columnKey);
    }
    
    public boolean containsKey(@CheckForNull Object key) {
      return StandardTable.this.contains(key, this.columnKey);
    }
    
    @CheckForNull
    public V remove(@CheckForNull Object key) {
      return (V)StandardTable.this.remove(key, this.columnKey);
    }
    
    @CanIgnoreReturnValue
    boolean removeFromColumnIf(Predicate<? super Entry<R, V>> predicate) {
      boolean changed = false;
      Iterator<Entry<R, Map<C, V>>> iterator = StandardTable.this.backingMap.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<R, Map<C, V>> entry = iterator.next();
        Map<C, V> map = entry.getValue();
        V value = map.get(this.columnKey);
        if (value != null && predicate.apply(Maps.immutableEntry(entry.getKey(), value))) {
          map.remove(this.columnKey);
          changed = true;
          if (map.isEmpty())
            iterator.remove(); 
        } 
      } 
      return changed;
    }
    
    Set<Entry<R, V>> createEntrySet() {
      return new EntrySet();
    }
    
    private class EntrySet extends Sets.ImprovedAbstractSet<Entry<R, V>> {
      private EntrySet() {}
      
      public Iterator<Entry<R, V>> iterator() {
        return new EntrySetIterator();
      }
      
      public int size() {
        int size = 0;
        for (Map<C, V> map : (Iterable<Map<C, V>>)StandardTable.this.backingMap.values()) {
          if (map.containsKey(Column.this.columnKey))
            size++; 
        } 
        return size;
      }
      
      public boolean isEmpty() {
        return !StandardTable.this.containsColumn(Column.this.columnKey);
      }
      
      public void clear() {
        Column.this.removeFromColumnIf(Predicates.alwaysTrue());
      }
      
      public boolean contains(@CheckForNull Object o) {
        if (o instanceof Map.Entry) {
          Entry<?, ?> entry = (Entry<?, ?>)o;
          return StandardTable.this.containsMapping(entry.getKey(), Column.this.columnKey, entry.getValue());
        } 
        return false;
      }
      
      public boolean remove(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
          Entry<?, ?> entry = (Entry<?, ?>)obj;
          return StandardTable.this.removeMapping(entry.getKey(), Column.this.columnKey, entry.getValue());
        } 
        return false;
      }
      
      public boolean retainAll(Collection<?> c) {
        return Column.this.removeFromColumnIf(Predicates.not(Predicates.in(c)));
      }
    }
    
    private class EntrySetIterator extends AbstractIterator<Entry<R, V>> {
      final Iterator<Entry<R, Map<C, V>>> iterator = StandardTable.this.backingMap.entrySet().iterator();
      
      @CheckForNull
      protected Map.Entry<R, V> computeNext() {
        while (this.iterator.hasNext()) {
          final Entry<R, Map<C, V>> entry = this.iterator.next();
          if (((Map)entry.getValue()).containsKey(Column.this.columnKey))
            return new EntryImpl(); 
        } 
        class EntryImpl extends AbstractMapEntry<R, V> {
          public R getKey() {
            return (R)entry.getKey();
          }
          
          public V getValue() {
            return (V)((Map)entry.getValue()).get(Column.this.columnKey);
          }
          
          public V setValue(V value) {
            return NullnessCasts.uncheckedCastNullableTToT(((Map<C, V>)entry.getValue()).put(Column.this.columnKey, (V)Preconditions.checkNotNull(value)));
          }
        };
        return endOfData();
      }
      
      private EntrySetIterator() {}
    }
    
    Set<R> createKeySet() {
      return new KeySet();
    }
    
    private class KeySet extends Maps.KeySet<R, V> {
      KeySet() {
        super(Column.this);
      }
      
      public boolean contains(@CheckForNull Object obj) {
        return StandardTable.this.contains(obj, Column.this.columnKey);
      }
      
      public boolean remove(@CheckForNull Object obj) {
        return (StandardTable.this.remove(obj, Column.this.columnKey) != null);
      }
      
      public boolean retainAll(Collection<?> c) {
        return Column.this.removeFromColumnIf((Predicate)Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(c))));
      }
    }
    
    Collection<V> createValues() {
      return new Values();
    }
    
    private class Values extends Maps.Values<R, V> {
      Values() {
        super(Column.this);
      }
      
      public boolean remove(@CheckForNull Object obj) {
        return (obj != null && Column.this.removeFromColumnIf((Predicate)Maps.valuePredicateOnEntries(Predicates.equalTo(obj))));
      }
      
      public boolean removeAll(Collection<?> c) {
        return Column.this.removeFromColumnIf((Predicate)Maps.valuePredicateOnEntries(Predicates.in(c)));
      }
      
      public boolean retainAll(Collection<?> c) {
        return Column.this.removeFromColumnIf((Predicate)Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(c))));
      }
    }
  }
  
  public Set<R> rowKeySet() {
    return rowMap().keySet();
  }
  
  public Set<C> columnKeySet() {
    Set<C> result = this.columnKeySet;
    return (result == null) ? (this.columnKeySet = new ColumnKeySet()) : result;
  }
  
  private class ColumnKeySet extends TableSet<C> {
    private ColumnKeySet() {}
    
    public Iterator<C> iterator() {
      return StandardTable.this.createColumnKeyIterator();
    }
    
    public int size() {
      return Iterators.size(iterator());
    }
    
    public boolean remove(@CheckForNull Object obj) {
      if (obj == null)
        return false; 
      boolean changed = false;
      Iterator<Map<C, V>> iterator = StandardTable.this.backingMap.values().iterator();
      while (iterator.hasNext()) {
        Map<C, V> map = iterator.next();
        if (map.keySet().remove(obj)) {
          changed = true;
          if (map.isEmpty())
            iterator.remove(); 
        } 
      } 
      return changed;
    }
    
    public boolean removeAll(Collection<?> c) {
      Preconditions.checkNotNull(c);
      boolean changed = false;
      Iterator<Map<C, V>> iterator = StandardTable.this.backingMap.values().iterator();
      while (iterator.hasNext()) {
        Map<C, V> map = iterator.next();
        if (Iterators.removeAll(map.keySet().iterator(), c)) {
          changed = true;
          if (map.isEmpty())
            iterator.remove(); 
        } 
      } 
      return changed;
    }
    
    public boolean retainAll(Collection<?> c) {
      Preconditions.checkNotNull(c);
      boolean changed = false;
      Iterator<Map<C, V>> iterator = StandardTable.this.backingMap.values().iterator();
      while (iterator.hasNext()) {
        Map<C, V> map = iterator.next();
        if (map.keySet().retainAll(c)) {
          changed = true;
          if (map.isEmpty())
            iterator.remove(); 
        } 
      } 
      return changed;
    }
    
    public boolean contains(@CheckForNull Object obj) {
      return StandardTable.this.containsColumn(obj);
    }
  }
  
  Iterator<C> createColumnKeyIterator() {
    return new ColumnKeyIterator();
  }
  
  private class ColumnKeyIterator extends AbstractIterator<C> {
    final Map<C, V> seen = (Map<C, V>)StandardTable.this.factory.get();
    
    final Iterator<Map<C, V>> mapIterator = StandardTable.this.backingMap.values().iterator();
    
    Iterator<Map.Entry<C, V>> entryIterator = Iterators.emptyIterator();
    
    @CheckForNull
    protected C computeNext() {
      while (true) {
        while (this.entryIterator.hasNext()) {
          Map.Entry<C, V> entry = this.entryIterator.next();
          if (!this.seen.containsKey(entry.getKey())) {
            this.seen.put(entry.getKey(), entry.getValue());
            return entry.getKey();
          } 
        } 
        if (this.mapIterator.hasNext()) {
          this.entryIterator = ((Map<C, V>)this.mapIterator.next()).entrySet().iterator();
          continue;
        } 
        break;
      } 
      return endOfData();
    }
    
    private ColumnKeyIterator() {}
  }
  
  public Collection<V> values() {
    return super.values();
  }
  
  public Map<R, Map<C, V>> rowMap() {
    Map<R, Map<C, V>> result = this.rowMap;
    return (result == null) ? (this.rowMap = createRowMap()) : result;
  }
  
  Map<R, Map<C, V>> createRowMap() {
    return new RowMap();
  }
  
  class RowMap extends Maps.ViewCachingAbstractMap<R, Map<C, V>> {
    public boolean containsKey(@CheckForNull Object key) {
      return StandardTable.this.containsRow(key);
    }
    
    @CheckForNull
    public Map<C, V> get(@CheckForNull Object key) {
      return StandardTable.this.containsRow(key) ? StandardTable.this.row(Objects.requireNonNull(key)) : null;
    }
    
    @CheckForNull
    public Map<C, V> remove(@CheckForNull Object key) {
      return (key == null) ? null : (Map<C, V>)StandardTable.this.backingMap.remove(key);
    }
    
    protected Set<Entry<R, Map<C, V>>> createEntrySet() {
      return new EntrySet();
    }
    
    class EntrySet extends TableSet<Entry<R, Map<C, V>>> {
      public Iterator<Entry<R, Map<C, V>>> iterator() {
        return Maps.asMapEntryIterator(StandardTable.this.backingMap
            .keySet(), new Function<R, Map<C, V>>() {
              public Map<C, V> apply(R rowKey) {
                return StandardTable.this.row(rowKey);
              }
            });
      }
      
      public int size() {
        return StandardTable.this.backingMap.size();
      }
      
      public boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
          Entry<?, ?> entry = (Entry<?, ?>)obj;
          return (entry.getKey() != null && entry
            .getValue() instanceof Map && 
            Collections2.safeContains(StandardTable.this.backingMap.entrySet(), entry));
        } 
        return false;
      }
      
      public boolean remove(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
          Entry<?, ?> entry = (Entry<?, ?>)obj;
          return (entry.getKey() != null && entry
            .getValue() instanceof Map && StandardTable.this.backingMap
            .entrySet().remove(entry));
        } 
        return false;
      }
    }
  }
  
  public Map<C, Map<R, V>> columnMap() {
    ColumnMap result = this.columnMap;
    return (result == null) ? (this.columnMap = new ColumnMap()) : result;
  }
  
  private class ColumnMap extends Maps.ViewCachingAbstractMap<C, Map<R, V>> {
    private ColumnMap() {}
    
    @CheckForNull
    public Map<R, V> get(@CheckForNull Object key) {
      return StandardTable.this.containsColumn(key) ? StandardTable.this.column(Objects.requireNonNull(key)) : null;
    }
    
    public boolean containsKey(@CheckForNull Object key) {
      return StandardTable.this.containsColumn(key);
    }
    
    @CheckForNull
    public Map<R, V> remove(@CheckForNull Object key) {
      return StandardTable.this.containsColumn(key) ? StandardTable.this.removeColumn(key) : null;
    }
    
    public Set<Entry<C, Map<R, V>>> createEntrySet() {
      return new ColumnMapEntrySet();
    }
    
    public Set<C> keySet() {
      return StandardTable.this.columnKeySet();
    }
    
    Collection<Map<R, V>> createValues() {
      return new ColumnMapValues();
    }
    
    class ColumnMapEntrySet extends TableSet<Entry<C, Map<R, V>>> {
      public Iterator<Entry<C, Map<R, V>>> iterator() {
        return Maps.asMapEntryIterator(StandardTable.this
            .columnKeySet(), new Function<C, Map<R, V>>() {
              public Map<R, V> apply(C columnKey) {
                return StandardTable.this.column(columnKey);
              }
            });
      }
      
      public int size() {
        return StandardTable.this.columnKeySet().size();
      }
      
      public boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
          Entry<?, ?> entry = (Entry<?, ?>)obj;
          if (StandardTable.this.containsColumn(entry.getKey()))
            return ((Map)Objects.<Map>requireNonNull(ColumnMap.this.get(entry.getKey()))).equals(entry.getValue());
        } 
        return false;
      }
      
      public boolean remove(@CheckForNull Object obj) {
        if (contains(obj) && obj instanceof Map.Entry) {
          Entry<?, ?> entry = (Entry<?, ?>)obj;
          StandardTable.this.removeColumn(entry.getKey());
          return true;
        } 
        return false;
      }
      
      public boolean removeAll(Collection<?> c) {
        Preconditions.checkNotNull(c);
        return Sets.removeAllImpl(this, c.iterator());
      }
      
      public boolean retainAll(Collection<?> c) {
        Preconditions.checkNotNull(c);
        boolean changed = false;
        for (C columnKey : Lists.newArrayList(StandardTable.this.columnKeySet().iterator())) {
          if (!c.contains(Maps.immutableEntry(columnKey, StandardTable.this.column(columnKey)))) {
            StandardTable.this.removeColumn(columnKey);
            changed = true;
          } 
        } 
        return changed;
      }
    }
    
    private class ColumnMapValues extends Maps.Values<C, Map<R, V>> {
      ColumnMapValues() {
        super(ColumnMap.this);
      }
      
      public boolean remove(@CheckForNull Object obj) {
        for (Entry<C, Map<R, V>> entry : ColumnMap.this.entrySet()) {
          if (((Map)entry.getValue()).equals(obj)) {
            StandardTable.this.removeColumn(entry.getKey());
            return true;
          } 
        } 
        return false;
      }
      
      public boolean removeAll(Collection<?> c) {
        Preconditions.checkNotNull(c);
        boolean changed = false;
        for (C columnKey : Lists.newArrayList(StandardTable.this.columnKeySet().iterator())) {
          if (c.contains(StandardTable.this.column(columnKey))) {
            StandardTable.this.removeColumn(columnKey);
            changed = true;
          } 
        } 
        return changed;
      }
      
      public boolean retainAll(Collection<?> c) {
        Preconditions.checkNotNull(c);
        boolean changed = false;
        for (C columnKey : Lists.newArrayList(StandardTable.this.columnKeySet().iterator())) {
          if (!c.contains(StandardTable.this.column(columnKey))) {
            StandardTable.this.removeColumn(columnKey);
            changed = true;
          } 
        } 
        return changed;
      }
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\StandardTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */