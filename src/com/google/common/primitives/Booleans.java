package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Booleans {
  private enum BooleanComparator implements Comparator<Boolean> {
    TRUE_FIRST(1, "Booleans.trueFirst()"),
    FALSE_FIRST(-1, "Booleans.falseFirst()");
    
    private final int trueValue;
    
    private final String toString;
    
    BooleanComparator(int trueValue, String toString) {
      this.trueValue = trueValue;
      this.toString = toString;
    }
    
    public int compare(Boolean a, Boolean b) {
      int aVal = a.booleanValue() ? this.trueValue : 0;
      int bVal = b.booleanValue() ? this.trueValue : 0;
      return bVal - aVal;
    }
    
    public String toString() {
      return this.toString;
    }
  }
  
  @Beta
  public static Comparator<Boolean> trueFirst() {
    return BooleanComparator.TRUE_FIRST;
  }
  
  @Beta
  public static Comparator<Boolean> falseFirst() {
    return BooleanComparator.FALSE_FIRST;
  }
  
  public static int hashCode(boolean value) {
    return value ? 1231 : 1237;
  }
  
  public static int compare(boolean a, boolean b) {
    return (a == b) ? 0 : (a ? 1 : -1);
  }
  
  public static boolean contains(boolean[] array, boolean target) {
    for (boolean value : array) {
      if (value == target)
        return true; 
    } 
    return false;
  }
  
  public static int indexOf(boolean[] array, boolean target) {
    return indexOf(array, target, 0, array.length);
  }
  
  private static int indexOf(boolean[] array, boolean target, int start, int end) {
    for (int i = start; i < end; i++) {
      if (array[i] == target)
        return i; 
    } 
    return -1;
  }
  
  public static int indexOf(boolean[] array, boolean[] target) {
    Preconditions.checkNotNull(array, "array");
    Preconditions.checkNotNull(target, "target");
    if (target.length == 0)
      return 0; 
    for (int i = 0; i < array.length - target.length + 1; i++) {
      int j = 0;
      while (true) {
        if (j < target.length) {
          if (array[i + j] != target[j])
            break; 
          j++;
          continue;
        } 
        return i;
      } 
    } 
    return -1;
  }
  
  public static int lastIndexOf(boolean[] array, boolean target) {
    return lastIndexOf(array, target, 0, array.length);
  }
  
  private static int lastIndexOf(boolean[] array, boolean target, int start, int end) {
    for (int i = end - 1; i >= start; i--) {
      if (array[i] == target)
        return i; 
    } 
    return -1;
  }
  
  public static boolean[] concat(boolean[]... arrays) {
    int length = 0;
    for (boolean[] array : arrays)
      length += array.length; 
    boolean[] result = new boolean[length];
    int pos = 0;
    for (boolean[] array : arrays) {
      System.arraycopy(array, 0, result, pos, array.length);
      pos += array.length;
    } 
    return result;
  }
  
  public static boolean[] ensureCapacity(boolean[] array, int minLength, int padding) {
    Preconditions.checkArgument((minLength >= 0), "Invalid minLength: %s", minLength);
    Preconditions.checkArgument((padding >= 0), "Invalid padding: %s", padding);
    return (array.length < minLength) ? Arrays.copyOf(array, minLength + padding) : array;
  }
  
  public static String join(String separator, boolean... array) {
    Preconditions.checkNotNull(separator);
    if (array.length == 0)
      return ""; 
    StringBuilder builder = new StringBuilder(array.length * 7);
    builder.append(array[0]);
    for (int i = 1; i < array.length; i++)
      builder.append(separator).append(array[i]); 
    return builder.toString();
  }
  
  public static Comparator<boolean[]> lexicographicalComparator() {
    return LexicographicalComparator.INSTANCE;
  }
  
  private enum LexicographicalComparator implements Comparator<boolean[]> {
    INSTANCE;
    
    public int compare(boolean[] left, boolean[] right) {
      int minLength = Math.min(left.length, right.length);
      for (int i = 0; i < minLength; i++) {
        int result = Booleans.compare(left[i], right[i]);
        if (result != 0)
          return result; 
      } 
      return left.length - right.length;
    }
    
    public String toString() {
      return "Booleans.lexicographicalComparator()";
    }
  }
  
  public static boolean[] toArray(Collection<Boolean> collection) {
    if (collection instanceof BooleanArrayAsList)
      return ((BooleanArrayAsList)collection).toBooleanArray(); 
    Object[] boxedArray = collection.toArray();
    int len = boxedArray.length;
    boolean[] array = new boolean[len];
    for (int i = 0; i < len; i++)
      array[i] = ((Boolean)Preconditions.checkNotNull(boxedArray[i])).booleanValue(); 
    return array;
  }
  
  public static List<Boolean> asList(boolean... backingArray) {
    if (backingArray.length == 0)
      return Collections.emptyList(); 
    return new BooleanArrayAsList(backingArray);
  }
  
  @GwtCompatible
  private static class BooleanArrayAsList extends AbstractList<Boolean> implements RandomAccess, Serializable {
    final boolean[] array;
    
    final int start;
    
    final int end;
    
    private static final long serialVersionUID = 0L;
    
    BooleanArrayAsList(boolean[] array) {
      this(array, 0, array.length);
    }
    
    BooleanArrayAsList(boolean[] array, int start, int end) {
      this.array = array;
      this.start = start;
      this.end = end;
    }
    
    public int size() {
      return this.end - this.start;
    }
    
    public boolean isEmpty() {
      return false;
    }
    
    public Boolean get(int index) {
      Preconditions.checkElementIndex(index, size());
      return Boolean.valueOf(this.array[this.start + index]);
    }
    
    public boolean contains(@CheckForNull Object target) {
      return (target instanceof Boolean && Booleans
        .indexOf(this.array, ((Boolean)target).booleanValue(), this.start, this.end) != -1);
    }
    
    public int indexOf(@CheckForNull Object target) {
      if (target instanceof Boolean) {
        int i = Booleans.indexOf(this.array, ((Boolean)target).booleanValue(), this.start, this.end);
        if (i >= 0)
          return i - this.start; 
      } 
      return -1;
    }
    
    public int lastIndexOf(@CheckForNull Object target) {
      if (target instanceof Boolean) {
        int i = Booleans.lastIndexOf(this.array, ((Boolean)target).booleanValue(), this.start, this.end);
        if (i >= 0)
          return i - this.start; 
      } 
      return -1;
    }
    
    public Boolean set(int index, Boolean element) {
      Preconditions.checkElementIndex(index, size());
      boolean oldValue = this.array[this.start + index];
      this.array[this.start + index] = ((Boolean)Preconditions.checkNotNull(element)).booleanValue();
      return Boolean.valueOf(oldValue);
    }
    
    public List<Boolean> subList(int fromIndex, int toIndex) {
      int size = size();
      Preconditions.checkPositionIndexes(fromIndex, toIndex, size);
      if (fromIndex == toIndex)
        return Collections.emptyList(); 
      return new BooleanArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
    }
    
    public boolean equals(@CheckForNull Object object) {
      if (object == this)
        return true; 
      if (object instanceof BooleanArrayAsList) {
        BooleanArrayAsList that = (BooleanArrayAsList)object;
        int size = size();
        if (that.size() != size)
          return false; 
        for (int i = 0; i < size; i++) {
          if (this.array[this.start + i] != that.array[that.start + i])
            return false; 
        } 
        return true;
      } 
      return super.equals(object);
    }
    
    public int hashCode() {
      int result = 1;
      for (int i = this.start; i < this.end; i++)
        result = 31 * result + Booleans.hashCode(this.array[i]); 
      return result;
    }
    
    public String toString() {
      StringBuilder builder = new StringBuilder(size() * 7);
      builder.append(this.array[this.start] ? "[true" : "[false");
      for (int i = this.start + 1; i < this.end; i++)
        builder.append(this.array[i] ? ", true" : ", false"); 
      return builder.append(']').toString();
    }
    
    boolean[] toBooleanArray() {
      return Arrays.copyOfRange(this.array, this.start, this.end);
    }
  }
  
  @Beta
  public static int countTrue(boolean... values) {
    int count = 0;
    for (boolean value : values) {
      if (value)
        count++; 
    } 
    return count;
  }
  
  public static void reverse(boolean[] array) {
    Preconditions.checkNotNull(array);
    reverse(array, 0, array.length);
  }
  
  public static void reverse(boolean[] array, int fromIndex, int toIndex) {
    Preconditions.checkNotNull(array);
    Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
    for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
      boolean tmp = array[i];
      array[i] = array[j];
      array[j] = tmp;
    } 
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\primitives\Booleans.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */