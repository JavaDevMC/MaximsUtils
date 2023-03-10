package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.nio.file.FileSystemException;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
@GwtIncompatible
public final class InsecureRecursiveDeleteException extends FileSystemException {
  public InsecureRecursiveDeleteException(@CheckForNull String file) {
    super(file, null, "unable to guarantee security of recursive delete");
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\io\InsecureRecursiveDeleteException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */