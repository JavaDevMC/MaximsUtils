package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import sun.misc.Unsafe;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class UnsignedBytes {
  public static final byte MAX_POWER_OF_TWO = -128;
  
  public static final byte MAX_VALUE = -1;
  
  private static final int UNSIGNED_MASK = 255;
  
  public static int toInt(byte value) {
    return value & 0xFF;
  }
  
  @CanIgnoreReturnValue
  public static byte checkedCast(long value) {
    Preconditions.checkArgument((value >> 8L == 0L), "out of range: %s", value);
    return (byte)(int)value;
  }
  
  public static byte saturatedCast(long value) {
    if (value > toInt((byte)-1))
      return -1; 
    if (value < 0L)
      return 0; 
    return (byte)(int)value;
  }
  
  public static int compare(byte a, byte b) {
    return toInt(a) - toInt(b);
  }
  
  public static byte min(byte... array) {
    Preconditions.checkArgument((array.length > 0));
    int min = toInt(array[0]);
    for (int i = 1; i < array.length; i++) {
      int next = toInt(array[i]);
      if (next < min)
        min = next; 
    } 
    return (byte)min;
  }
  
  public static byte max(byte... array) {
    Preconditions.checkArgument((array.length > 0));
    int max = toInt(array[0]);
    for (int i = 1; i < array.length; i++) {
      int next = toInt(array[i]);
      if (next > max)
        max = next; 
    } 
    return (byte)max;
  }
  
  @Beta
  public static String toString(byte x) {
    return toString(x, 10);
  }
  
  @Beta
  public static String toString(byte x, int radix) {
    Preconditions.checkArgument((radix >= 2 && radix <= 36), "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", radix);
    return Integer.toString(toInt(x), radix);
  }
  
  @Beta
  @CanIgnoreReturnValue
  public static byte parseUnsignedByte(String string) {
    return parseUnsignedByte(string, 10);
  }
  
  @Beta
  @CanIgnoreReturnValue
  public static byte parseUnsignedByte(String string, int radix) {
    int parse = Integer.parseInt((String)Preconditions.checkNotNull(string), radix);
    if (parse >> 8 == 0)
      return (byte)parse; 
    throw new NumberFormatException((new StringBuilder(25)).append("out of range: ").append(parse).toString());
  }
  
  public static String join(String separator, byte... array) {
    Preconditions.checkNotNull(separator);
    if (array.length == 0)
      return ""; 
    StringBuilder builder = new StringBuilder(array.length * (3 + separator.length()));
    builder.append(toInt(array[0]));
    for (int i = 1; i < array.length; i++)
      builder.append(separator).append(toString(array[i])); 
    return builder.toString();
  }
  
  public static Comparator<byte[]> lexicographicalComparator() {
    return LexicographicalComparatorHolder.BEST_COMPARATOR;
  }
  
  @VisibleForTesting
  static Comparator<byte[]> lexicographicalComparatorJavaImpl() {
    return LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
  }
  
  @VisibleForTesting
  static class LexicographicalComparatorHolder {
    static final String UNSAFE_COMPARATOR_NAME = String.valueOf(LexicographicalComparatorHolder.class.getName()).concat("$UnsafeComparator");
    
    static final Comparator<byte[]> BEST_COMPARATOR = getBestComparator();
    
    @VisibleForTesting
    enum UnsafeComparator implements Comparator<byte[]> {
      INSTANCE;
      
      static final int BYTE_ARRAY_BASE_OFFSET;
      
      static final Unsafe theUnsafe = getUnsafe();
      
      static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
      
      static {
        BYTE_ARRAY_BASE_OFFSET = theUnsafe.arrayBaseOffset(byte[].class);
        if (!"64".equals(System.getProperty("sun.arch.data.model")) || BYTE_ARRAY_BASE_OFFSET % 8 != 0 || theUnsafe
          
          .arrayIndexScale(byte[].class) != 1)
          throw new Error(); 
      }
      
      private static Unsafe getUnsafe() {
        try {
          return Unsafe.getUnsafe();
        } catch (SecurityException securityException) {
          try {
            return AccessController.<Unsafe>doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
                  public Unsafe run() throws Exception {
                    Class<Unsafe> k = Unsafe.class;
                    for (Field f : k.getDeclaredFields()) {
                      f.setAccessible(true);
                      Object x = f.get(null);
                      if (k.isInstance(x))
                        return k.cast(x); 
                    } 
                    throw new NoSuchFieldError("the Unsafe");
                  }
                });
          } catch (PrivilegedActionException e) {
            throw new RuntimeException("Could not initialize intrinsics", e.getCause());
          } 
        } 
      }
      
      public int compare(byte[] left, byte[] right) {
        int stride = 8;
        int minLength = Math.min(left.length, right.length);
        int strideLimit = minLength & 0xFFFFFFF8;
        int i;
        for (i = 0; i < strideLimit; i += 8) {
          long lw = theUnsafe.getLong(left, BYTE_ARRAY_BASE_OFFSET + i);
          long rw = theUnsafe.getLong(right, BYTE_ARRAY_BASE_OFFSET + i);
          if (lw != rw) {
            if (BIG_ENDIAN)
              return UnsignedLongs.compare(lw, rw); 
            int n = Long.numberOfTrailingZeros(lw ^ rw) & 0xFFFFFFF8;
            return (int)(lw >>> n & 0xFFL) - (int)(rw >>> n & 0xFFL);
          } 
        } 
        for (; i < minLength; i++) {
          int result = UnsignedBytes.compare(left[i], right[i]);
          if (result != 0)
            return result; 
        } 
        return left.length - right.length;
      }
      
      public String toString() {
        return "UnsignedBytes.lexicographicalComparator() (sun.misc.Unsafe version)";
      }
    }
    
    enum PureJavaComparator implements Comparator<byte[]> {
      INSTANCE;
      
      public int compare(byte[] left, byte[] right) {
        int minLength = Math.min(left.length, right.length);
        for (int i = 0; i < minLength; i++) {
          int result = UnsignedBytes.compare(left[i], right[i]);
          if (result != 0)
            return result; 
        } 
        return left.length - right.length;
      }
      
      public String toString() {
        return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
      }
    }
    
    static Comparator<byte[]> getBestComparator() {
      try {
        Class<?> theClass = Class.forName(UNSAFE_COMPARATOR_NAME);
        Object[] constants = Objects.<Object[]>requireNonNull(theClass.getEnumConstants());
        Comparator<byte[]> comparator = (Comparator<byte[]>)constants[0];
        return comparator;
      } catch (Throwable t) {
        return UnsignedBytes.lexicographicalComparatorJavaImpl();
      } 
    }
  }
  
  enum PureJavaComparator implements Comparator<byte[]> {
    INSTANCE;
    
    public int compare(byte[] left, byte[] right) {
      int minLength = Math.min(left.length, right.length);
      for (int i = 0; i < minLength; i++) {
        int result = UnsignedBytes.compare(left[i], right[i]);
        if (result != 0)
          return result; 
      } 
      return left.length - right.length;
    }
    
    public String toString() {
      return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
    }
  }
  
  private static byte flip(byte b) {
    return (byte)(b ^ 0x80);
  }
  
  public static void sort(byte[] array) {
    Preconditions.checkNotNull(array);
    sort(array, 0, array.length);
  }
  
  public static void sort(byte[] array, int fromIndex, int toIndex) {
    Preconditions.checkNotNull(array);
    Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
    int i;
    for (i = fromIndex; i < toIndex; i++)
      array[i] = flip(array[i]); 
    Arrays.sort(array, fromIndex, toIndex);
    for (i = fromIndex; i < toIndex; i++)
      array[i] = flip(array[i]); 
  }
  
  public static void sortDescending(byte[] array) {
    Preconditions.checkNotNull(array);
    sortDescending(array, 0, array.length);
  }
  
  public static void sortDescending(byte[] array, int fromIndex, int toIndex) {
    Preconditions.checkNotNull(array);
    Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
    int i;
    for (i = fromIndex; i < toIndex; i++)
      array[i] = (byte)(array[i] ^ Byte.MAX_VALUE); 
    Arrays.sort(array, fromIndex, toIndex);
    for (i = fromIndex; i < toIndex; i++)
      array[i] = (byte)(array[i] ^ Byte.MAX_VALUE); 
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\primitives\UnsignedBytes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */