package com.google.protobuf;

public abstract class ExtensionLite<ContainingType extends MessageLite, Type> {
  public abstract int getNumber();
  
  public abstract WireFormat.FieldType getLiteType();
  
  public abstract boolean isRepeated();
  
  public abstract Type getDefaultValue();
  
  public abstract MessageLite getMessageDefaultInstance();
  
  boolean isLite() {
    return true;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\ExtensionLite.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */