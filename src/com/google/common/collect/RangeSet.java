package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Set;
import javax.annotation.CheckForNull;

@DoNotMock("Use ImmutableRangeSet or TreeRangeSet")
@ElementTypesAreNonnullByDefault
@Beta
@GwtIncompatible
public interface RangeSet<C extends Comparable> {
  boolean contains(C paramC);
  
  @CheckForNull
  Range<C> rangeContaining(C paramC);
  
  boolean intersects(Range<C> paramRange);
  
  boolean encloses(Range<C> paramRange);
  
  boolean enclosesAll(RangeSet<C> paramRangeSet);
  
  default boolean enclosesAll(Iterable<Range<C>> other) {
    for (Range<C> range : other) {
      if (!encloses(range))
        return false; 
    } 
    return true;
  }
  
  boolean isEmpty();
  
  Range<C> span();
  
  Set<Range<C>> asRanges();
  
  Set<Range<C>> asDescendingSetOfRanges();
  
  RangeSet<C> complement();
  
  RangeSet<C> subRangeSet(Range<C> paramRange);
  
  void add(Range<C> paramRange);
  
  void remove(Range<C> paramRange);
  
  void clear();
  
  void addAll(RangeSet<C> paramRangeSet);
  
  default void addAll(Iterable<Range<C>> ranges) {
    for (Range<C> range : ranges)
      add(range); 
  }
  
  void removeAll(RangeSet<C> paramRangeSet);
  
  default void removeAll(Iterable<Range<C>> ranges) {
    for (Range<C> range : ranges)
      remove(range); 
  }
  
  boolean equals(@CheckForNull Object paramObject);
  
  int hashCode();
  
  String toString();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\RangeSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */