package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import javax.annotation.CheckForNull;

@Deprecated
@ElementTypesAreNonnullByDefault
@GwtCompatible
public class ComputationException extends RuntimeException {
  private static final long serialVersionUID = 0L;
  
  public ComputationException(@CheckForNull Throwable cause) {
    super(cause);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ComputationException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */