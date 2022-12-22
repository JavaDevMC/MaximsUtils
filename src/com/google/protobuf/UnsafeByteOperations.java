package com.google.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;

public final class UnsafeByteOperations {
  public static ByteString unsafeWrap(byte[] buffer) {
    return ByteString.wrap(buffer);
  }
  
  public static ByteString unsafeWrap(byte[] buffer, int offset, int length) {
    return ByteString.wrap(buffer, offset, length);
  }
  
  public static ByteString unsafeWrap(ByteBuffer buffer) {
    return ByteString.wrap(buffer);
  }
  
  public static void unsafeWriteTo(ByteString bytes, ByteOutput output) throws IOException {
    bytes.writeTo(output);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\UnsafeByteOperations.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */