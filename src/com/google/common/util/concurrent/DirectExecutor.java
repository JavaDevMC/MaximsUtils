package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.Executor;

@ElementTypesAreNonnullByDefault
@GwtCompatible
enum DirectExecutor implements Executor {
  INSTANCE;
  
  public void execute(Runnable command) {
    command.run();
  }
  
  public String toString() {
    return "MoreExecutors.directExecutor()";
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\DirectExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */