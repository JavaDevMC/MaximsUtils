package com.google.protobuf;

final class NewInstanceSchemaLite implements NewInstanceSchema {
  public Object newInstance(Object defaultInstance) {
    return ((GeneratedMessageLite)defaultInstance)
      .dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\NewInstanceSchemaLite.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */