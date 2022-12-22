package com.google.common.util.concurrent;

import java.util.concurrent.locks.LockSupport;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class OverflowAvoidingLockSupport {
  static final long MAX_NANOSECONDS_THRESHOLD = 2147483647999999999L;
  
  static void parkNanos(@CheckForNull Object blocker, long nanos) {
    LockSupport.parkNanos(blocker, Math.min(nanos, 2147483647999999999L));
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\OverflowAvoidingLockSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */