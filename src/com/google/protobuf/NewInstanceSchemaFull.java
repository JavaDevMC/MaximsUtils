package com.google.protobuf;

final class NewInstanceSchemaFull implements NewInstanceSchema {
  public Object newInstance(Object defaultInstance) {
    return ((GeneratedMessageV3)defaultInstance)
      .newInstance(GeneratedMessageV3.UnusedPrivateParameter.INSTANCE);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\NewInstanceSchemaFull.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */