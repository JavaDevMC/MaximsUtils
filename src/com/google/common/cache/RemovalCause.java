package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public enum RemovalCause {
  EXPLICIT {
    boolean wasEvicted() {
      return false;
    }
  },
  REPLACED {
    boolean wasEvicted() {
      return false;
    }
  },
  COLLECTED {
    boolean wasEvicted() {
      return true;
    }
  },
  EXPIRED {
    boolean wasEvicted() {
      return true;
    }
  },
  SIZE {
    boolean wasEvicted() {
      return true;
    }
  };
  
  abstract boolean wasEvicted();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\cache\RemovalCause.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */