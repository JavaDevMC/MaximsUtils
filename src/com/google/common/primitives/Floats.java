package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
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
@GwtCompatible(emulated = true)
public final class Floats extends FloatsMethodsForWeb {
  public static final int BYTES = 4;
  
  public static int hashCode(float value) {
    return Float.valueOf(value).hashCode();
  }
  
  public static int compare(float a, float b) {
    return Float.compare(a, b);
  }
  
  public static boolean isFinite(float value) {
    return (Float.NEGATIVE_INFINITY < value && value < Float.POSITIVE_INFINITY);
  }
  
  public static boolean contains(float[] array, float target) {
    for (float value : array) {
      if (value == target)
        return true; 
    } 
    return false;
  }
  
  public static int indexOf(float[] array, float target) {
    return indexOf(array, target, 0, array.length);
  }
  
  private static int indexOf(float[] array, float target, int start, int end) {
    for (int i = start; i < end; i++) {
      if (array[i] == target)
        return i; 
    } 
    return -1;
  }
  
  public static int indexOf(float[] array, float[] target) {
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
  
  public static int lastIndexOf(float[] array, float target) {
    return lastIndexOf(array, target, 0, array.length);
  }
  
  private static int lastIndexOf(float[] array, float target, int start, int end) {
    for (int i = end - 1; i >= start; i--) {
      if (array[i] == target)
        return i; 
    } 
    return -1;
  }
  
  @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
  public static float min(float... array) {
    Preconditions.checkArgument((array.length > 0));
    float min = array[0];
    for (int i = 1; i < array.length; i++)
      min = Math.min(min, array[i]); 
    return min;
  }
  
  @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
  public static float max(float... array) {
    Preconditions.checkArgument((array.length > 0));
    float max = array[0];
    for (int i = 1; i < array.length; i++)
      max = Math.max(max, array[i]); 
    return max;
  }
  
  @Beta
  public static float constrainToRange(float value, float min, float max) {
    if (min <= max)
      return Math.min(Math.max(value, min), max); 
    throw new IllegalArgumentException(
        Strings.lenientFormat("min (%s) must be less than or equal to max (%s)", new Object[] { Float.valueOf(min), Float.valueOf(max) }));
  }
  
  public static float[] concat(float[]... arrays) {
    int length = 0;
    for (float[] array : arrays)
      length += array.length; 
    float[] result = new float[length];
    int pos = 0;
    for (float[] array : arrays) {
      System.arraycopy(array, 0, result, pos, array.length);
      pos += array.length;
    } 
    return result;
  }
  
  private static final class FloatConverter extends Converter<String, Float> implements Serializable {
    static final FloatConverter INSTANCE = new FloatConverter();
    
    private static final long serialVersionUID = 1L;
    
    protected Float doForward(String value) {
      return Float.valueOf(value);
    }
    
    protected String doBackward(Float value) {
      return value.toString();
    }
    
    public String toString() {
      return "Floats.stringConverter()";
    }
    
    private Object readResolve() {
      return INSTANCE;
    }
  }
  
  @Beta
  public static Converter<String, Float> stringConverter() {
    return FloatConverter.INSTANCE;
  }
  
  public static float[] ensureCapacity(float[] array, int minLength, int padding) {
    Preconditions.checkArgument((minLength >= 0), "Invalid minLength: %s", minLength);
    Preconditions.checkArgument((padding >= 0), "Invalid padding: %s", padding);
    return (array.length < minLength) ? Arrays.copyOf(array, minLength + padding) : array;
  }
  
  public static String join(String separator, float... array) {
    Preconditions.checkNotNull(separator);
    if (array.length == 0)
      return ""; 
    StringBuilder builder = new StringBuilder(array.length * 12);
    builder.append(array[0]);
    for (int i = 1; i < array.length; i++)
      builder.append(separator).append(array[i]); 
    return builder.toString();
  }
  
  public static Comparator<float[]> lexicographicalComparator() {
    return LexicographicalComparator.INSTANCE;
  }
  
  private enum LexicographicalComparator implements Comparator<float[]> {
    INSTANCE;
    
    public int compare(float[] left, float[] right) {
      int minLength = Math.min(left.length, right.length);
      for (int i = 0; i < minLength; i++) {
        int result = Float.compare(left[i], right[i]);
        if (result != 0)
          return result; 
      } 
      return left.length - right.length;
    }
    
    public String toString() {
      return "Floats.lexicographicalComparator()";
    }
  }
  
  public static void sortDescending(float[] array) {
    Preconditions.checkNotNull(array);
    sortDescending(array, 0, array.length);
  }
  
  public static void sortDescending(float[] array, int fromIndex, int toIndex) {
    Preconditions.checkNotNull(array);
    Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
    Arrays.sort(array, fromIndex, toIndex);
    reverse(array, fromIndex, toIndex);
  }
  
  public static void reverse(float[] array) {
    Preconditions.checkNotNull(array);
    reverse(array, 0, array.length);
  }
  
  public static void reverse(float[] array, int fromIndex, int toIndex) {
    Preconditions.checkNotNull(array);
    Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
    for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
      float tmp = array[i];
      array[i] = array[j];
      array[j] = tmp;
    } 
  }
  
  public static float[] toArray(Collection<? extends Number> collection) {
    if (collection instanceof FloatArrayAsList)
      return ((FloatArrayAsList)collection).toFloatArray(); 
    Object[] boxedArray = collection.toArray();
    int len = boxedArray.length;
    float[] array = new float[len];
    for (int i = 0; i < len; i++)
      array[i] = ((Number)Preconditions.checkNotNull(boxedArray[i])).floatValue(); 
    return array;
  }
  
  public static List<Float> asList(float... backingArray) {
    if (backingArray.length == 0)
      return Collections.emptyList(); 
    return new FloatArrayAsList(backingArray);
  }
  
  @GwtCompatible
  private static class FloatArrayAsList extends AbstractList<Float> implements RandomAccess, Serializable {
    final float[] array;
    
    final int start;
    
    final int end;
    
    private static final long serialVersionUID = 0L;
    
    FloatArrayAsList(float[] array) {
      this(array, 0, array.length);
    }
    
    FloatArrayAsList(float[] array, int start, int end) {
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
    
    public Float get(int index) {
      Preconditions.checkElementIndex(index, size());
      return Float.valueOf(this.array[this.start + index]);
    }
    
    public boolean contains(@CheckForNull Object target) {
      return (target instanceof Float && Floats.indexOf(this.array, ((Float)target).floatValue(), this.start, this.end) != -1);
    }
    
    public int indexOf(@CheckForNull Object target) {
      if (target instanceof Float) {
        int i = Floats.indexOf(this.array, ((Float)target).floatValue(), this.start, this.end);
        if (i >= 0)
          return i - this.start; 
      } 
      return -1;
    }
    
    public int lastIndexOf(@CheckForNull Object target) {
      if (target instanceof Float) {
        int i = Floats.lastIndexOf(this.array, ((Float)target).floatValue(), this.start, this.end);
        if (i >= 0)
          return i - this.start; 
      } 
      return -1;
    }
    
    public Float set(int index, Float element) {
      Preconditions.checkElementIndex(index, size());
      float oldValue = this.array[this.start + index];
      this.array[this.start + index] = ((Float)Preconditions.checkNotNull(element)).floatValue();
      return Float.valueOf(oldValue);
    }
    
    public List<Float> subList(int fromIndex, int toIndex) {
      int size = size();
      Preconditions.checkPositionIndexes(fromIndex, toIndex, size);
      if (fromIndex == toIndex)
        return Collections.emptyList(); 
      return new FloatArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
    }
    
    public boolean equals(@CheckForNull Object object) {
      if (object == this)
        return true; 
      if (object instanceof FloatArrayAsList) {
        FloatArrayAsList that = (FloatArrayAsList)object;
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
        result = 31 * result + Floats.hashCode(this.array[i]); 
      return result;
    }
    
    public String toString() {
      StringBuilder builder = new StringBuilder(size() * 12);
      builder.append('[').append(this.array[this.start]);
      for (int i = this.start + 1; i < this.end; i++)
        builder.append(", ").append(this.array[i]); 
      return builder.append(']').toString();
    }
    
    float[] toFloatArray() {
      return Arrays.copyOfRange(this.array, this.start, this.end);
    }
  }
  
  @CheckForNull
  @Beta
  @GwtIncompatible
  public static Float tryParse(String string) {
    if (Doubles.FLOATING_POINT_PATTERN.matcher(string).matches())
      try {
        return Float.valueOf(Float.parseFloat(string));
      } catch (NumberFormatException numberFormatException) {} 
    return null;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\primitives\Floats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */