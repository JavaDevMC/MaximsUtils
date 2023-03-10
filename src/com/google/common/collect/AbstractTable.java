package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractTable<R, C, V> implements Table<R, C, V> {
  @LazyInit
  @CheckForNull
  private transient Set<Cell<R, C, V>> cellSet;
  
  @LazyInit
  @CheckForNull
  private transient Collection<V> values;
  
  public boolean containsRow(@CheckForNull Object rowKey) {
    return Maps.safeContainsKey(rowMap(), rowKey);
  }
  
  public boolean containsColumn(@CheckForNull Object columnKey) {
    return Maps.safeContainsKey(columnMap(), columnKey);
  }
  
  public Set<R> rowKeySet() {
    return rowMap().keySet();
  }
  
  public Set<C> columnKeySet() {
    return columnMap().keySet();
  }
  
  public boolean containsValue(@CheckForNull Object value) {
    for (Map<C, V> row : rowMap().values()) {
      if (row.containsValue(value))
        return true; 
    } 
    return false;
  }
  
  public boolean contains(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
    Map<C, V> row = Maps.<Map<C, V>>safeGet(rowMap(), rowKey);
    return (row != null && Maps.safeContainsKey(row, columnKey));
  }
  
  @CheckForNull
  public V get(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
    Map<C, V> row = Maps.<Map<C, V>>safeGet(rowMap(), rowKey);
    return (row == null) ? null : Maps.<V>safeGet(row, columnKey);
  }
  
  public boolean isEmpty() {
    return (size() == 0);
  }
  
  public void clear() {
    Iterators.clear(cellSet().iterator());
  }
  
  @CheckForNull
  @CanIgnoreReturnValue
  public V remove(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
    Map<C, V> row = Maps.<Map<C, V>>safeGet(rowMap(), rowKey);
    return (row == null) ? null : Maps.<V>safeRemove(row, columnKey);
  }
  
  @CheckForNull
  @CanIgnoreReturnValue
  public V put(@ParametricNullness R rowKey, @ParametricNullness C columnKey, @ParametricNullness V value) {
    return row(rowKey).put(columnKey, value);
  }
  
  public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
    for (Cell<? extends R, ? extends C, ? extends V> cell : table.cellSet())
      put(cell.getRowKey(), cell.getColumnKey(), cell.getValue()); 
  }
  
  public Set<Cell<R, C, V>> cellSet() {
    Set<Cell<R, C, V>> result = this.cellSet;
    return (result == null) ? (this.cellSet = createCellSet()) : result;
  }
  
  Set<Cell<R, C, V>> createCellSet() {
    return new CellSet();
  }
  
  abstract Iterator<Cell<R, C, V>> cellIterator();
  
  abstract Spliterator<Cell<R, C, V>> cellSpliterator();
  
  class CellSet extends AbstractSet<Cell<R, C, V>> {
    public boolean contains(@CheckForNull Object o) {
      if (o instanceof Table.Cell) {
        Cell<?, ?, ?> cell = (Cell<?, ?, ?>)o;
        Map<C, V> row = Maps.<Map<C, V>>safeGet(AbstractTable.this.rowMap(), cell.getRowKey());
        return (row != null && 
          Collections2.safeContains(row
            .entrySet(), Maps.immutableEntry(cell.getColumnKey(), cell.getValue())));
      } 
      return false;
    }
    
    public boolean remove(@CheckForNull Object o) {
      if (o instanceof Table.Cell) {
        Cell<?, ?, ?> cell = (Cell<?, ?, ?>)o;
        Map<C, V> row = Maps.<Map<C, V>>safeGet(AbstractTable.this.rowMap(), cell.getRowKey());
        return (row != null && 
          Collections2.safeRemove(row
            .entrySet(), Maps.immutableEntry(cell.getColumnKey(), cell.getValue())));
      } 
      return false;
    }
    
    public void clear() {
      AbstractTable.this.clear();
    }
    
    public Iterator<Cell<R, C, V>> iterator() {
      return AbstractTable.this.cellIterator();
    }
    
    public Spliterator<Cell<R, C, V>> spliterator() {
      return AbstractTable.this.cellSpliterator();
    }
    
    public int size() {
      return AbstractTable.this.size();
    }
  }
  
  public Collection<V> values() {
    Collection<V> result = this.values;
    return (result == null) ? (this.values = createValues()) : result;
  }
  
  Collection<V> createValues() {
    return new Values();
  }
  
  Iterator<V> valuesIterator() {
    return new TransformedIterator<Cell<R, C, V>, V>(this, cellSet().iterator()) {
        @ParametricNullness
        V transform(Cell<R, C, V> cell) {
          return cell.getValue();
        }
      };
  }
  
  Spliterator<V> valuesSpliterator() {
    return CollectSpliterators.map(cellSpliterator(), Cell::getValue);
  }
  
  class Values extends AbstractCollection<V> {
    public Iterator<V> iterator() {
      return AbstractTable.this.valuesIterator();
    }
    
    public Spliterator<V> spliterator() {
      return AbstractTable.this.valuesSpliterator();
    }
    
    public boolean contains(@CheckForNull Object o) {
      return AbstractTable.this.containsValue(o);
    }
    
    public void clear() {
      AbstractTable.this.clear();
    }
    
    public int size() {
      return AbstractTable.this.size();
    }
  }
  
  public boolean equals(@CheckForNull Object obj) {
    return Tables.equalsImpl(this, obj);
  }
  
  public int hashCode() {
    return cellSet().hashCode();
  }
  
  public String toString() {
    return rowMap().toString();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\AbstractTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */