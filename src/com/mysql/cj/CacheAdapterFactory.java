package com.mysql.cj;

public interface CacheAdapterFactory<K, V> {
  CacheAdapter<K, V> getInstance(Object paramObject, String paramString, int paramInt1, int paramInt2);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\CacheAdapterFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */