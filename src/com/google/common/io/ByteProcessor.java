package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.io.IOException;

@DoNotMock("Implement it normally")
@ElementTypesAreNonnullByDefault
@Beta
@GwtIncompatible
public interface ByteProcessor<T> {
  @CanIgnoreReturnValue
  boolean processBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  @ParametricNullness
  T getResult();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\io\ByteProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */