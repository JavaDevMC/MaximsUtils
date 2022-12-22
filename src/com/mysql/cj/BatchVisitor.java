package com.mysql.cj;

public interface BatchVisitor {
  BatchVisitor increment();
  
  BatchVisitor decrement();
  
  BatchVisitor append(byte[] paramArrayOfbyte);
  
  BatchVisitor merge(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2);
  
  BatchVisitor mergeWithLast(byte[] paramArrayOfbyte);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\BatchVisitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */