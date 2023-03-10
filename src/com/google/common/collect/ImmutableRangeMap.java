package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.DoNotMock;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
@GwtIncompatible
public class ImmutableRangeMap<K extends Comparable<?>, V> implements RangeMap<K, V>, Serializable {
  private static final ImmutableRangeMap<Comparable<?>, Object> EMPTY = new ImmutableRangeMap(
      ImmutableList.of(), ImmutableList.of());
  
  private final transient ImmutableList<Range<K>> ranges;
  
  private final transient ImmutableList<V> values;
  
  private static final long serialVersionUID = 0L;
  
  public static <T, K extends Comparable<? super K>, V> Collector<T, ?, ImmutableRangeMap<K, V>> toImmutableRangeMap(Function<? super T, Range<K>> keyFunction, Function<? super T, ? extends V> valueFunction) {
    return CollectCollectors.toImmutableRangeMap(keyFunction, valueFunction);
  }
  
  public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> of() {
    return (ImmutableRangeMap)EMPTY;
  }
  
  public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> of(Range<K> range, V value) {
    return new ImmutableRangeMap<>(ImmutableList.of(range), ImmutableList.of(value));
  }
  
  public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> copyOf(RangeMap<K, ? extends V> rangeMap) {
    if (rangeMap instanceof ImmutableRangeMap)
      return (ImmutableRangeMap)rangeMap; 
    Map<Range<K>, ? extends V> map = rangeMap.asMapOfRanges();
    ImmutableList.Builder<Range<K>> rangesBuilder = new ImmutableList.Builder<>(map.size());
    ImmutableList.Builder<V> valuesBuilder = new ImmutableList.Builder<>(map.size());
    for (Map.Entry<Range<K>, ? extends V> entry : map.entrySet()) {
      rangesBuilder.add(entry.getKey());
      valuesBuilder.add(entry.getValue());
    } 
    return new ImmutableRangeMap<>(rangesBuilder.build(), valuesBuilder.build());
  }
  
  public static <K extends Comparable<?>, V> Builder<K, V> builder() {
    return new Builder<>();
  }
  
  @DoNotMock
  public static final class Builder<K extends Comparable<?>, V> {
    private final List<Map.Entry<Range<K>, V>> entries = Lists.newArrayList();
    
    @CanIgnoreReturnValue
    public Builder<K, V> put(Range<K> range, V value) {
      Preconditions.checkNotNull(range);
      Preconditions.checkNotNull(value);
      Preconditions.checkArgument(!range.isEmpty(), "Range must not be empty, but was %s", range);
      this.entries.add(Maps.immutableEntry(range, value));
      return this;
    }
    
    @CanIgnoreReturnValue
    public Builder<K, V> putAll(RangeMap<K, ? extends V> rangeMap) {
      for (Map.Entry<Range<K>, ? extends V> entry : (Iterable<Map.Entry<Range<K>, ? extends V>>)rangeMap.asMapOfRanges().entrySet())
        put(entry.getKey(), entry.getValue()); 
      return this;
    }
    
    @CanIgnoreReturnValue
    Builder<K, V> combine(Builder<K, V> builder) {
      this.entries.addAll(builder.entries);
      return this;
    }
    
    public ImmutableRangeMap<K, V> build() {
      Collections.sort(this.entries, Range.<Comparable<?>>rangeLexOrdering().onKeys());
      ImmutableList.Builder<Range<K>> rangesBuilder = new ImmutableList.Builder<>(this.entries.size());
      ImmutableList.Builder<V> valuesBuilder = new ImmutableList.Builder<>(this.entries.size());
      for (int i = 0; i < this.entries.size(); i++) {
        Range<K> range = (Range<K>)((Map.Entry)this.entries.get(i)).getKey();
        if (i > 0) {
          Range<K> prevRange = (Range<K>)((Map.Entry)this.entries.get(i - 1)).getKey();
          if (range.isConnected(prevRange) && !range.intersection(prevRange).isEmpty()) {
            String str1 = String.valueOf(prevRange), str2 = String.valueOf(range);
            throw new IllegalArgumentException((new StringBuilder(47 + String.valueOf(str1).length() + String.valueOf(str2).length())).append("Overlapping ranges: range ").append(str1).append(" overlaps with entry ").append(str2).toString());
          } 
        } 
        rangesBuilder.add(range);
        valuesBuilder.add((V)((Map.Entry)this.entries.get(i)).getValue());
      } 
      return new ImmutableRangeMap<>(rangesBuilder.build(), valuesBuilder.build());
    }
  }
  
  ImmutableRangeMap(ImmutableList<Range<K>> ranges, ImmutableList<V> values) {
    this.ranges = ranges;
    this.values = values;
  }
  
  @CheckForNull
  public V get(K key) {
    int index = SortedLists.binarySearch(this.ranges, 
        
        (Function)Range.lowerBoundFn(), 
        Cut.belowValue(key), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
    if (index == -1)
      return null; 
    Range<K> range = this.ranges.get(index);
    return range.contains(key) ? this.values.get(index) : null;
  }
  
  @CheckForNull
  public Map.Entry<Range<K>, V> getEntry(K key) {
    int index = SortedLists.binarySearch(this.ranges, 
        
        (Function)Range.lowerBoundFn(), 
        Cut.belowValue(key), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
    if (index == -1)
      return null; 
    Range<K> range = this.ranges.get(index);
    return range.contains(key) ? Maps.<Range<K>, V>immutableEntry(range, this.values.get(index)) : null;
  }
  
  public Range<K> span() {
    if (this.ranges.isEmpty())
      throw new NoSuchElementException(); 
    Range<K> firstRange = this.ranges.get(0);
    Range<K> lastRange = this.ranges.get(this.ranges.size() - 1);
    return Range.create(firstRange.lowerBound, lastRange.upperBound);
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final void put(Range<K> range, V value) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final void putCoalescing(Range<K> range, V value) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final void putAll(RangeMap<K, V> rangeMap) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final void clear() {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final void remove(Range<K> range) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final void merge(Range<K> range, @CheckForNull V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
    throw new UnsupportedOperationException();
  }
  
  public ImmutableMap<Range<K>, V> asMapOfRanges() {
    if (this.ranges.isEmpty())
      return ImmutableMap.of(); 
    RegularImmutableSortedSet<Range<K>> rangeSet = new RegularImmutableSortedSet<>(this.ranges, (Comparator)Range.rangeLexOrdering());
    return new ImmutableSortedMap<>(rangeSet, this.values);
  }
  
  public ImmutableMap<Range<K>, V> asDescendingMapOfRanges() {
    if (this.ranges.isEmpty())
      return ImmutableMap.of(); 
    RegularImmutableSortedSet<Range<K>> rangeSet = new RegularImmutableSortedSet<>(this.ranges.reverse(), Range.<Comparable<?>>rangeLexOrdering().reverse());
    return new ImmutableSortedMap<>(rangeSet, this.values.reverse());
  }
  
  public ImmutableRangeMap<K, V> subRangeMap(final Range<K> range) {
    if (((Range)Preconditions.checkNotNull(range)).isEmpty())
      return of(); 
    if (this.ranges.isEmpty() || range.encloses(span()))
      return this; 
    int lowerIndex = SortedLists.binarySearch(this.ranges, 
        
        Range.upperBoundFn(), range.lowerBound, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
    int upperIndex = SortedLists.binarySearch(this.ranges, 
        
        Range.lowerBoundFn(), range.upperBound, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
    if (lowerIndex >= upperIndex)
      return of(); 
    final int off = lowerIndex;
    final int len = upperIndex - lowerIndex;
    ImmutableList<Range<K>> subRanges = new ImmutableList<Range<K>>() {
        public int size() {
          return len;
        }
        
        public Range<K> get(int index) {
          Preconditions.checkElementIndex(index, len);
          if (index == 0 || index == len - 1)
            return ((Range<K>)ImmutableRangeMap.this.ranges.get(index + off)).intersection(range); 
          return ImmutableRangeMap.this.ranges.get(index + off);
        }
        
        boolean isPartialView() {
          return true;
        }
      };
    final ImmutableRangeMap<K, V> outer = this;
    return new ImmutableRangeMap<K, V>(this, subRanges, this.values.subList(lowerIndex, upperIndex)) {
        public ImmutableRangeMap<K, V> subRangeMap(Range<K> subRange) {
          if (range.isConnected(subRange))
            return outer.subRangeMap(subRange.intersection(range)); 
          return ImmutableRangeMap.of();
        }
      };
  }
  
  public int hashCode() {
    return asMapOfRanges().hashCode();
  }
  
  public boolean equals(@CheckForNull Object o) {
    if (o instanceof RangeMap) {
      RangeMap<?, ?> rangeMap = (RangeMap<?, ?>)o;
      return asMapOfRanges().equals(rangeMap.asMapOfRanges());
    } 
    return false;
  }
  
  public String toString() {
    return asMapOfRanges().toString();
  }
  
  private static class SerializedForm<K extends Comparable<?>, V> implements Serializable {
    private final ImmutableMap<Range<K>, V> mapOfRanges;
    
    private static final long serialVersionUID = 0L;
    
    SerializedForm(ImmutableMap<Range<K>, V> mapOfRanges) {
      this.mapOfRanges = mapOfRanges;
    }
    
    Object readResolve() {
      if (this.mapOfRanges.isEmpty())
        return ImmutableRangeMap.of(); 
      return createRangeMap();
    }
    
    Object createRangeMap() {
      Builder<K, V> builder = new Builder<>();
      for (UnmodifiableIterator<Map.Entry<Range<K>, V>> unmodifiableIterator = this.mapOfRanges.entrySet().iterator(); unmodifiableIterator.hasNext(); ) {
        Map.Entry<Range<K>, V> entry = unmodifiableIterator.next();
        builder.put(entry.getKey(), entry.getValue());
      } 
      return builder.build();
    }
  }
  
  Object writeReplace() {
    return new SerializedForm<>(asMapOfRanges());
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ImmutableRangeMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */