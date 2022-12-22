package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@Beta
@GwtCompatible
public final class Runnables {
  private static final Runnable EMPTY_RUNNABLE = new Runnable() {
      public void run() {}
    };
  
  public static Runnable doNothing() {
    return EMPTY_RUNNABLE;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\Runnables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */