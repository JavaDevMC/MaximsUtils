package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class Defaults {
  private static final Double DOUBLE_DEFAULT = Double.valueOf(0.0D);
  
  private static final Float FLOAT_DEFAULT = Float.valueOf(0.0F);
  
  @CheckForNull
  public static <T> T defaultValue(Class<T> type) {
    Preconditions.checkNotNull(type);
    if (type.isPrimitive()) {
      if (type == boolean.class)
        return (T)Boolean.FALSE; 
      if (type == char.class)
        return (T)Character.valueOf(false); 
      if (type == byte.class)
        return (T)Byte.valueOf((byte)0); 
      if (type == short.class)
        return (T)Short.valueOf((short)0); 
      if (type == int.class)
        return (T)Integer.valueOf(0); 
      if (type == long.class)
        return (T)Long.valueOf(0L); 
      if (type == float.class)
        return (T)FLOAT_DEFAULT; 
      if (type == double.class)
        return (T)DOUBLE_DEFAULT; 
    } 
    return null;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\base\Defaults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */