package com.google.gson.internal;

public final class $Gson$Preconditions {
  private $Gson$Preconditions() {
    throw new UnsupportedOperationException();
  }
  
  public static <T> T checkNotNull(T obj) {
    if (obj == null)
      throw new NullPointerException(); 
    return obj;
  }
  
  public static void checkArgument(boolean condition) {
    if (!condition)
      throw new IllegalArgumentException(); 
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\gson\internal\$Gson$Preconditions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */