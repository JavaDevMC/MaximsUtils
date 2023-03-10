package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.Immutable;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;

@Immutable(containerOf = {"R", "C", "V"})
@ElementTypesAreNonnullByDefault
@GwtCompatible
final class DenseImmutableTable<R, C, V> extends RegularImmutableTable<R, C, V> {
  private final ImmutableMap<R, Integer> rowKeyToIndex;
  
  private final ImmutableMap<C, Integer> columnKeyToIndex;
  
  private final ImmutableMap<R, ImmutableMap<C, V>> rowMap;
  
  private final ImmutableMap<C, ImmutableMap<R, V>> columnMap;
  
  private final int[] rowCounts;
  
  private final int[] columnCounts;
  
  private final V[][] values;
  
  private final int[] cellRowIndices;
  
  private final int[] cellColumnIndices;
  
  DenseImmutableTable(ImmutableList<Cell<R, C, V>> cellList, ImmutableSet<R> rowSpace, ImmutableSet<C> columnSpace) {
    V[][] array = (V[][])new Object[rowSpace.size()][columnSpace.size()];
    this.values = array;
    this.rowKeyToIndex = Maps.indexMap(rowSpace);
    this.columnKeyToIndex = Maps.indexMap(columnSpace);
    this.rowCounts = new int[this.rowKeyToIndex.size()];
    this.columnCounts = new int[this.columnKeyToIndex.size()];
    int[] cellRowIndices = new int[cellList.size()];
    int[] cellColumnIndices = new int[cellList.size()];
    for (int i = 0; i < cellList.size(); i++) {
      Cell<R, C, V> cell = cellList.get(i);
      R rowKey = cell.getRowKey();
      C columnKey = cell.getColumnKey();
      int rowIndex = ((Integer)Objects.<Integer>requireNonNull(this.rowKeyToIndex.get(rowKey))).intValue();
      int columnIndex = ((Integer)Objects.<Integer>requireNonNull(this.columnKeyToIndex.get(columnKey))).intValue();
      V existingValue = this.values[rowIndex][columnIndex];
      checkNoDuplicate(rowKey, columnKey, existingValue, cell.getValue());
      this.values[rowIndex][columnIndex] = cell.getValue();
      this.rowCounts[rowIndex] = this.rowCounts[rowIndex] + 1;
      this.columnCounts[columnIndex] = this.columnCounts[columnIndex] + 1;
      cellRowIndices[i] = rowIndex;
      cellColumnIndices[i] = columnIndex;
    } 
    this.cellRowIndices = cellRowIndices;
    this.cellColumnIndices = cellColumnIndices;
    this.rowMap = new RowMap();
    this.columnMap = new ColumnMap();
  }
  
  private static abstract class ImmutableArrayMap<K, V> extends ImmutableMap.IteratorBasedImmutableMap<K, V> {
    private final int size;
    
    ImmutableArrayMap(int size) {
      this.size = size;
    }
    
    abstract ImmutableMap<K, Integer> keyToIndex();
    
    private boolean isFull() {
      return (this.size == keyToIndex().size());
    }
    
    K getKey(int index) {
      return keyToIndex().keySet().asList().get(index);
    }
    
    @CheckForNull
    abstract V getValue(int param1Int);
    
    ImmutableSet<K> createKeySet() {
      return isFull() ? keyToIndex().keySet() : super.createKeySet();
    }
    
    public int size() {
      return this.size;
    }
    
    @CheckForNull
    public V get(@CheckForNull Object key) {
      Integer keyIndex = keyToIndex().get(key);
      return (keyIndex == null) ? null : getValue(keyIndex.intValue());
    }
    
    UnmodifiableIterator<Entry<K, V>> entryIterator() {
      return new AbstractIterator<Entry<K, V>>() {
          private int index = -1;
          
          private final int maxIndex = ImmutableArrayMap.this.keyToIndex().size();
          
          @CheckForNull
          protected Map.Entry<K, V> computeNext() {
            this.index++;
            for (; this.index < this.maxIndex; this.index++) {
              V value = (V) ImmutableArrayMap.this.getValue(this.index);
              if (value != null)
                return Maps.immutableEntry((K) ImmutableArrayMap.this.getKey(this.index), value);
            } 
            return endOfData();
          }
        };
    }
  }
  
  private final class Row extends ImmutableArrayMap<C, V> {
    private final int rowIndex;
    
    Row(int rowIndex) {
      super(DenseImmutableTable.this.rowCounts[rowIndex]);
      this.rowIndex = rowIndex;
    }
    
    ImmutableMap<C, Integer> keyToIndex() {
      return DenseImmutableTable.this.columnKeyToIndex;
    }
    
    @CheckForNull
    V getValue(int keyIndex) {
      return (V)DenseImmutableTable.this.values[this.rowIndex][keyIndex];
    }
    
    boolean isPartialView() {
      return true;
    }
  }
  
  private final class Column extends ImmutableArrayMap<R, V> {
    private final int columnIndex;
    
    Column(int columnIndex) {
      super(DenseImmutableTable.this.columnCounts[columnIndex]);
      this.columnIndex = columnIndex;
    }
    
    ImmutableMap<R, Integer> keyToIndex() {
      return DenseImmutableTable.this.rowKeyToIndex;
    }
    
    @CheckForNull
    V getValue(int keyIndex) {
      return (V)DenseImmutableTable.this.values[keyIndex][this.columnIndex];
    }
    
    boolean isPartialView() {
      return true;
    }
  }
  
  private final class RowMap extends ImmutableArrayMap<R, ImmutableMap<C, V>> {
    private RowMap() {
      super(DenseImmutableTable.this.rowCounts.length);
    }
    
    ImmutableMap<R, Integer> keyToIndex() {
      return DenseImmutableTable.this.rowKeyToIndex;
    }
    
    ImmutableMap<C, V> getValue(int keyIndex) {
      return new Row(keyIndex);
    }
    
    boolean isPartialView() {
      return false;
    }
  }
  
  private final class ColumnMap extends ImmutableArrayMap<C, ImmutableMap<R, V>> {
    private ColumnMap() {
      super(DenseImmutableTable.this.columnCounts.length);
    }
    
    ImmutableMap<C, Integer> keyToIndex() {
      return DenseImmutableTable.this.columnKeyToIndex;
    }
    
    ImmutableMap<R, V> getValue(int keyIndex) {
      return new Column(keyIndex);
    }
    
    boolean isPartialView() {
      return false;
    }
  }
  
  public ImmutableMap<C, Map<R, V>> columnMap() {
    ImmutableMap<C, ImmutableMap<R, V>> columnMap = this.columnMap;
    return ImmutableMap.copyOf((Map)columnMap);
  }
  
  public ImmutableMap<R, Map<C, V>> rowMap() {
    ImmutableMap<R, ImmutableMap<C, V>> rowMap = this.rowMap;
    return ImmutableMap.copyOf((Map)rowMap);
  }
  
  @CheckForNull
  public V get(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
    Integer rowIndex = this.rowKeyToIndex.get(rowKey);
    Integer columnIndex = this.columnKeyToIndex.get(columnKey);
    return (rowIndex == null || columnIndex == null) ? null : this.values[rowIndex.intValue()][columnIndex.intValue()];
  }
  
  public int size() {
    return this.cellRowIndices.length;
  }
  
  Cell<R, C, V> getCell(int index) {
    int rowIndex = this.cellRowIndices[index];
    int columnIndex = this.cellColumnIndices[index];
    R rowKey = rowKeySet().asList().get(rowIndex);
    C columnKey = columnKeySet().asList().get(columnIndex);
    V value = Objects.requireNonNull(this.values[rowIndex][columnIndex]);
    return cellOf(rowKey, columnKey, value);
  }
  
  V getValue(int index) {
    return Objects.requireNonNull(this.values[this.cellRowIndices[index]][this.cellColumnIndices[index]]);
  }
  
  SerializedForm createSerializedForm() {
    return SerializedForm.create(this, this.cellRowIndices, this.cellColumnIndices);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\DenseImmutableTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */