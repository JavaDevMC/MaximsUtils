package com.google.protobuf;

public enum NullValue implements ProtocolMessageEnum {
  NULL_VALUE(0),
  UNRECOGNIZED(-1);
  
  public static final int NULL_VALUE_VALUE = 0;
  
  private static final Internal.EnumLiteMap<NullValue> internalValueMap;
  
  private static final NullValue[] VALUES;
  
  private final int value;
  
  public final int getNumber() {
    if (this == UNRECOGNIZED)
      throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
    return this.value;
  }
  
  public static NullValue forNumber(int value) {
    switch (value) {
      case 0:
        return NULL_VALUE;
    } 
    return null;
  }
  
  public static Internal.EnumLiteMap<NullValue> internalGetValueMap() {
    return internalValueMap;
  }
  
  static {
    internalValueMap = new Internal.EnumLiteMap<NullValue>() {
        public NullValue findValueByNumber(int number) {
          return NullValue.forNumber(number);
        }
      };
    VALUES = values();
  }
  
  public final Descriptors.EnumValueDescriptor getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  
  public final Descriptors.EnumDescriptor getDescriptorForType() {
    return getDescriptor();
  }
  
  public static final Descriptors.EnumDescriptor getDescriptor() {
    return StructProto.getDescriptor().getEnumTypes().get(0);
  }
  
  NullValue(int value) {
    this.value = value;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\NullValue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */