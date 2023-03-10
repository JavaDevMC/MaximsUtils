package com.google.common.io;

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
  
  static void mark(Buffer b) {
    b.mark();
  }
  
  static void position(Buffer b, int position) {
    b.position(position);
  }
  
  static void reset(Buffer b) {
    b.reset();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\io\Java8Compatibility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */