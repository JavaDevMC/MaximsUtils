package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
abstract class AbstractRangeSet<C extends Comparable> implements RangeSet<C> {
  public boolean contains(C value) {
    return (rangeContaining(value) != null);
  }
  
  @CheckForNull
  public abstract Range<C> rangeContaining(C paramC);
  
  public boolean isEmpty() {
    return asRanges().isEmpty();
  }
  
  public void add(Range<C> range) {
    throw new UnsupportedOperationException();
  }
  
  public void remove(Range<C> range) {
    throw new UnsupportedOperationException();
  }
  
  public void clear() {
    remove((Range)Range.all());
  }
  
  public boolean enclosesAll(RangeSet<C> other) {
    return enclosesAll(other.asRanges());
  }
  
  public void addAll(RangeSet<C> other) {
    addAll(other.asRanges());
  }
  
  public void removeAll(RangeSet<C> other) {
    removeAll(other.asRanges());
  }
  
  public boolean intersects(Range<C> otherRange) {
    return !subRangeSet(otherRange).isEmpty();
  }
  
  public abstract boolean encloses(Range<C> paramRange);
  
  public boolean equals(@CheckForNull Object obj) {
    if (obj == this)
      return true; 
    if (obj instanceof RangeSet) {
      RangeSet<?> other = (RangeSet)obj;
      return asRanges().equals(other.asRanges());
    } 
    return false;
  }
  
  public final int hashCode() {
    return asRanges().hashCode();
  }
  
  public final String toString() {
    return asRanges().toString();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\AbstractRangeSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */