package com.mysql.cj.protocol;

public interface ResultBuilder<T> {
  boolean addProtocolEntity(ProtocolEntity paramProtocolEntity);
  
  T build();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\ResultBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */