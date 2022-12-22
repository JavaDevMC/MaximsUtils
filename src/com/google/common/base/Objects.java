package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.util.Arrays;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Objects extends ExtraObjectsMethodsForWeb {
  public static boolean equal(@CheckForNull Object a, @CheckForNull Object b) {
    return (a == b || (a != null && a.equals(b)));
  }
  
  public static int hashCode(@CheckForNull Object... objects) {
    return Arrays.hashCode(objects);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\base\Objects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */