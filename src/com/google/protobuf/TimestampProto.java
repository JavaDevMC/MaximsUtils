package com.google.protobuf;

public final class TimestampProto {
  static final Descriptors.Descriptor internal_static_google_protobuf_Timestamp_descriptor;
  
  static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_Timestamp_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions(registry);
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\037google/protobuf/timestamp.proto\022\017google.protobuf\"+\n\tTimestamp\022\017\n\007seconds\030\001 \001(\003\022\r\n\005nanos\030\002 \001(\005B~\n\023com.google.protobufB\016TimestampProtoP\001Z+github.com/golang/protobuf/ptypes/timestampø\001\001¢\002\003GPBª\002\036Google.Protobuf.WellKnownTypesb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_google_protobuf_Timestamp_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_google_protobuf_Timestamp_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_Timestamp_descriptor, new String[] { "Seconds", "Nanos" });
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\TimestampProto.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */