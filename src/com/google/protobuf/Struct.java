package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public final class Struct extends GeneratedMessageV3 implements StructOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int FIELDS_FIELD_NUMBER = 1;
  
  private MapField<String, Value> fields_;
  
  private byte memoizedIsInitialized;
  
  private Struct(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memoizedIsInitialized = -1;
  }
  
  private Struct() {
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(UnusedPrivateParameter unused) {
    return new Struct();
  }
  
  public final UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }
  
  private Struct(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null)
      throw new NullPointerException(); 
    int mutable_bitField0_ = 0;
    UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        MapEntry<String, Value> fields__;
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            continue;
          case 10:
            if ((mutable_bitField0_ & 0x1) == 0) {
              this.fields_ = MapField.newMapField(FieldsDefaultEntryHolder.defaultEntry);
              mutable_bitField0_ |= 0x1;
            } 
            fields__ = input.<MapEntry<String, Value>>readMessage(FieldsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
            this.fields_.getMutableMap().put(fields__.getKey(), fields__.getValue());
            continue;
        } 
        if (!parseUnknownField(input, unknownFields, extensionRegistry, tag))
          done = true; 
      } 
    } catch (InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (IOException e) {
      throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    } 
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return StructProto.internal_static_google_protobuf_Struct_descriptor;
  }
  
  protected MapField internalGetMapField(int number) {
    switch (number) {
      case 1:
        return internalGetFields();
    } 
    throw new RuntimeException("Invalid map field number: " + number);
  }
  
  protected FieldAccessorTable internalGetFieldAccessorTable() {
    return StructProto.internal_static_google_protobuf_Struct_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Struct.class, (Class)Builder.class);
  }
  
  private static final class FieldsDefaultEntryHolder {
    static final MapEntry<String, Value> defaultEntry = MapEntry.newDefaultInstance(StructProto.internal_static_google_protobuf_Struct_FieldsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.getDefaultInstance());
  }
  
  private MapField<String, Value> internalGetFields() {
    if (this.fields_ == null)
      return MapField.emptyMapField(FieldsDefaultEntryHolder.defaultEntry); 
    return this.fields_;
  }
  
  public int getFieldsCount() {
    return internalGetFields().getMap().size();
  }
  
  public boolean containsFields(String key) {
    if (key == null)
      throw new NullPointerException(); 
    return internalGetFields().getMap().containsKey(key);
  }
  
  @Deprecated
  public Map<String, Value> getFields() {
    return getFieldsMap();
  }
  
  public Map<String, Value> getFieldsMap() {
    return internalGetFields().getMap();
  }
  
  public Value getFieldsOrDefault(String key, Value defaultValue) {
    if (key == null)
      throw new NullPointerException(); 
    Map<String, Value> map = internalGetFields().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  
  public Value getFieldsOrThrow(String key) {
    if (key == null)
      throw new NullPointerException(); 
    Map<String, Value> map = internalGetFields().getMap();
    if (!map.containsKey(key))
      throw new IllegalArgumentException(); 
    return map.get(key);
  }
  
  public final boolean isInitialized() {
    byte isInitialized = this.memoizedIsInitialized;
    if (isInitialized == 1)
      return true; 
    if (isInitialized == 0)
      return false; 
    this.memoizedIsInitialized = 1;
    return true;
  }
  
  public void writeTo(CodedOutputStream output) throws IOException {
    GeneratedMessageV3.serializeStringMapTo(output, 
        
        internalGetFields(), FieldsDefaultEntryHolder.defaultEntry, 1);
    this.unknownFields.writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    for (Map.Entry<String, Value> entry : (Iterable<Map.Entry<String, Value>>)internalGetFields().getMap().entrySet()) {
      MapEntry<String, Value> fields__ = FieldsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build();
      size += 
        CodedOutputStream.computeMessageSize(1, fields__);
    } 
    size += this.unknownFields.getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof Struct))
      return super.equals(obj); 
    Struct other = (Struct)obj;
    if (!internalGetFields().equals(other
        .internalGetFields()))
      return false; 
    if (!this.unknownFields.equals(other.unknownFields))
      return false; 
    return true;
  }
  
  public int hashCode() {
    if (this.memoizedHashCode != 0)
      return this.memoizedHashCode; 
    int hash = 41;
    hash = 19 * hash + getDescriptor().hashCode();
    if (!internalGetFields().getMap().isEmpty()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + internalGetFields().hashCode();
    } 
    hash = 29 * hash + this.unknownFields.hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static Struct parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static Struct parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Struct parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static Struct parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Struct parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static Struct parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Struct parseFrom(InputStream input) throws IOException {
    return 
      GeneratedMessageV3.<Struct>parseWithIOException(PARSER, input);
  }
  
  public static Struct parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<Struct>parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Struct parseDelimitedFrom(InputStream input) throws IOException {
    return 
      GeneratedMessageV3.<Struct>parseDelimitedWithIOException(PARSER, input);
  }
  
  public static Struct parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<Struct>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Struct parseFrom(CodedInputStream input) throws IOException {
    return 
      GeneratedMessageV3.<Struct>parseWithIOException(PARSER, input);
  }
  
  public static Struct parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<Struct>parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Struct prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  
  public Builder toBuilder() {
    return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
      .mergeFrom(this);
  }
  
  protected Builder newBuilderForType(BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StructOrBuilder {
    private int bitField0_;
    
    private MapField<String, Value> fields_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return StructProto.internal_static_google_protobuf_Struct_descriptor;
    }
    
    protected MapField internalGetMapField(int number) {
      switch (number) {
        case 1:
          return internalGetFields();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected MapField internalGetMutableMapField(int number) {
      switch (number) {
        case 1:
          return internalGetMutableFields();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return StructProto.internal_static_google_protobuf_Struct_fieldAccessorTable
        .ensureFieldAccessorsInitialized((Class)Struct.class, (Class)Builder.class);
    }
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (GeneratedMessageV3.alwaysUseFieldBuilders);
    }
    
    public Builder clear() {
      super.clear();
      internalGetMutableFields().clear();
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return StructProto.internal_static_google_protobuf_Struct_descriptor;
    }
    
    public Struct getDefaultInstanceForType() {
      return Struct.getDefaultInstance();
    }
    
    public Struct build() {
      Struct result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public Struct buildPartial() {
      Struct result = new Struct(this);
      int from_bitField0_ = this.bitField0_;
      result.fields_ = internalGetFields();
      result.fields_.makeImmutable();
      onBuilt();
      return result;
    }
    
    public Builder clone() {
      return super.clone();
    }
    
    public Builder setField(Descriptors.FieldDescriptor field, Object value) {
      return super.setField(field, value);
    }
    
    public Builder clearField(Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
      return super.setRepeatedField(field, index, value);
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
      return super.addRepeatedField(field, value);
    }
    
    public Builder mergeFrom(Message other) {
      if (other instanceof Struct)
        return mergeFrom((Struct)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(Struct other) {
      if (other == Struct.getDefaultInstance())
        return this; 
      internalGetMutableFields().mergeFrom(other
          .internalGetFields());
      mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      Struct parsedMessage = null;
      try {
        parsedMessage = Struct.PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (InvalidProtocolBufferException e) {
        parsedMessage = (Struct)e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null)
          mergeFrom(parsedMessage); 
      } 
      return this;
    }
    
    private MapField<String, Value> internalGetFields() {
      if (this.fields_ == null)
        return MapField.emptyMapField(FieldsDefaultEntryHolder.defaultEntry);
      return this.fields_;
    }
    
    private MapField<String, Value> internalGetMutableFields() {
      onChanged();
      if (this.fields_ == null)
        this.fields_ = MapField.newMapField(FieldsDefaultEntryHolder.defaultEntry);
      if (!this.fields_.isMutable())
        this.fields_ = this.fields_.copy(); 
      return this.fields_;
    }
    
    public int getFieldsCount() {
      return internalGetFields().getMap().size();
    }
    
    public boolean containsFields(String key) {
      if (key == null)
        throw new NullPointerException(); 
      return internalGetFields().getMap().containsKey(key);
    }
    
    @Deprecated
    public Map<String, Value> getFields() {
      return getFieldsMap();
    }
    
    public Map<String, Value> getFieldsMap() {
      return internalGetFields().getMap();
    }
    
    public Value getFieldsOrDefault(String key, Value defaultValue) {
      if (key == null)
        throw new NullPointerException(); 
      Map<String, Value> map = internalGetFields().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    
    public Value getFieldsOrThrow(String key) {
      if (key == null)
        throw new NullPointerException(); 
      Map<String, Value> map = internalGetFields().getMap();
      if (!map.containsKey(key))
        throw new IllegalArgumentException(); 
      return map.get(key);
    }
    
    public Builder clearFields() {
      internalGetMutableFields().getMutableMap()
        .clear();
      return this;
    }
    
    public Builder removeFields(String key) {
      if (key == null)
        throw new NullPointerException(); 
      internalGetMutableFields().getMutableMap()
        .remove(key);
      return this;
    }
    
    @Deprecated
    public Map<String, Value> getMutableFields() {
      return internalGetMutableFields().getMutableMap();
    }
    
    public Builder putFields(String key, Value value) {
      if (key == null)
        throw new NullPointerException(); 
      if (value == null)
        throw new NullPointerException(); 
      internalGetMutableFields().getMutableMap()
        .put(key, value);
      return this;
    }
    
    public Builder putAllFields(Map<String, Value> values) {
      internalGetMutableFields().getMutableMap()
        .putAll(values);
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final Struct DEFAULT_INSTANCE = new Struct();
  
  public static Struct getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<Struct> PARSER = new AbstractParser<Struct>() {
      public Struct parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return new Struct(input, extensionRegistry);
      }
    };
  
  public static Parser<Struct> parser() {
    return PARSER;
  }
  
  public Parser<Struct> getParserForType() {
    return PARSER;
  }
  
  public Struct getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\Struct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */