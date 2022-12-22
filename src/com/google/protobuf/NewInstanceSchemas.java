package com.google.protobuf;

final class NewInstanceSchemas {
  private static final NewInstanceSchema FULL_SCHEMA = loadSchemaForFullRuntime();
  
  private static final NewInstanceSchema LITE_SCHEMA = new NewInstanceSchemaLite();
  
  static NewInstanceSchema full() {
    return FULL_SCHEMA;
  }
  
  static NewInstanceSchema lite() {
    return LITE_SCHEMA;
  }
  
  private static NewInstanceSchema loadSchemaForFullRuntime() {
    try {
      Class<?> clazz = Class.forName("com.google.protobuf.NewInstanceSchemaFull");
      return clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (Exception e) {
      return null;
    } 
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\NewInstanceSchemas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */