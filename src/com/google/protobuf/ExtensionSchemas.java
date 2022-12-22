package com.google.protobuf;

final class ExtensionSchemas {
  private static final ExtensionSchema<?> LITE_SCHEMA = new ExtensionSchemaLite();
  
  private static final ExtensionSchema<?> FULL_SCHEMA = loadSchemaForFullRuntime();
  
  private static ExtensionSchema<?> loadSchemaForFullRuntime() {
    try {
      Class<?> clazz = Class.forName("com.google.protobuf.ExtensionSchemaFull");
      return clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (Exception e) {
      return null;
    } 
  }
  
  static ExtensionSchema<?> lite() {
    return LITE_SCHEMA;
  }
  
  static ExtensionSchema<?> full() {
    if (FULL_SCHEMA == null)
      throw new IllegalStateException("Protobuf runtime is not correctly loaded."); 
    return FULL_SCHEMA;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\ExtensionSchemas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */