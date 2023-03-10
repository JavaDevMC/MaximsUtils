package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
@GwtIncompatible
public final class Closeables {
  @VisibleForTesting
  static final Logger logger = Logger.getLogger(Closeables.class.getName());
  
  public static void close(@CheckForNull Closeable closeable, boolean swallowIOException) throws IOException {
    if (closeable == null)
      return; 
    try {
      closeable.close();
    } catch (IOException e) {
      if (swallowIOException) {
        logger.log(Level.WARNING, "IOException thrown while closing Closeable.", e);
      } else {
        throw e;
      } 
    } 
  }
  
  public static void closeQuietly(@CheckForNull InputStream inputStream) {
    try {
      close(inputStream, true);
    } catch (IOException impossible) {
      throw new AssertionError(impossible);
    } 
  }
  
  public static void closeQuietly(@CheckForNull Reader reader) {
    try {
      close(reader, true);
    } catch (IOException impossible) {
      throw new AssertionError(impossible);
    } 
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\io\Closeables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */