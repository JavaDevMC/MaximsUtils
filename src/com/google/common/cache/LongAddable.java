package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
interface LongAddable {
  void increment();
  
  void add(long paramLong);
  
  long sum();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\cache\LongAddable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */