package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public class VerifyException extends RuntimeException {
  public VerifyException() {}
  
  public VerifyException(@CheckForNull String message) {
    super(message);
  }
  
  public VerifyException(@CheckForNull Throwable cause) {
    super(cause);
  }
  
  public VerifyException(@CheckForNull String message, @CheckForNull Throwable cause) {
    super(message, cause);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\base\VerifyException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */