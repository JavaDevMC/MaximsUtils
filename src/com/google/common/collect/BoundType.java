package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public enum BoundType {
  OPEN(false),
  CLOSED(true);
  
  final boolean inclusive;
  
  BoundType(boolean inclusive) {
    this.inclusive = inclusive;
  }
  
  static BoundType forBoolean(boolean inclusive) {
    return inclusive ? CLOSED : OPEN;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\BoundType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */