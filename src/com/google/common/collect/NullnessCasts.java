package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class NullnessCasts {
  @ParametricNullness
  static <T> T uncheckedCastNullableTToT(@CheckForNull T t) {
    return t;
  }
  
  @ParametricNullness
  static <T> T unsafeNull() {
    return null;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\NullnessCasts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */