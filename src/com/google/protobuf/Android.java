package com.google.protobuf;

final class Android {
  private static final Class<?> MEMORY_CLASS = getClassForName("libcore.io.Memory");
  
  private static final boolean IS_ROBOLECTRIC = (getClassForName("org.robolectric.Robolectric") != null);
  
  static boolean isOnAndroidDevice() {
    return (MEMORY_CLASS != null && !IS_ROBOLECTRIC);
  }
  
  static Class<?> getMemoryClass() {
    return MEMORY_CLASS;
  }
  
  private static <T> Class<T> getClassForName(String name) {
    try {
      return (Class)Class.forName(name);
    } catch (Throwable e) {
      return null;
    } 
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\Android.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */