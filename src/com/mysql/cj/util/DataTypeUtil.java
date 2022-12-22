package com.mysql.cj.util;

public class DataTypeUtil {
  public static long bitToLong(byte[] bytes, int offset, int length) {
    long valueAsLong = 0L;
    for (int i = 0; i < length; i++)
      valueAsLong = valueAsLong << 8L | (bytes[offset + i] & 0xFF); 
    return valueAsLong;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\c\\util\DataTypeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */