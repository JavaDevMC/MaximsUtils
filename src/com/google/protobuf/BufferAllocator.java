package com.google.protobuf;

import java.nio.ByteBuffer;

abstract class BufferAllocator {
  private static final BufferAllocator UNPOOLED = new BufferAllocator() {
      public AllocatedBuffer allocateHeapBuffer(int capacity) {
        return AllocatedBuffer.wrap(new byte[capacity]);
      }
      
      public AllocatedBuffer allocateDirectBuffer(int capacity) {
        return AllocatedBuffer.wrap(ByteBuffer.allocateDirect(capacity));
      }
    };
  
  public static BufferAllocator unpooled() {
    return UNPOOLED;
  }
  
  public abstract AllocatedBuffer allocateHeapBuffer(int paramInt);
  
  public abstract AllocatedBuffer allocateDirectBuffer(int paramInt);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\BufferAllocator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */