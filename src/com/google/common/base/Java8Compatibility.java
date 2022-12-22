package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.nio.Buffer;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
final class Java8Compatibility {
  static void clear(Buffer b) {
    b.clear();
  }
  
  static void flip(Buffer b) {
    b.flip();
  }
  
  static void limit(Buffer b, int limit) {
    b.limit(limit);
  }
  
  static void position(Buffer b, int position) {
    b.position(position);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\base\Java8Compatibility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */