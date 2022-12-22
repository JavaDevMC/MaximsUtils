package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Int64Value extends GeneratedMessageV3 implements Int64ValueOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int VALUE_FIELD_NUMBER = 1;
  
  private long value_;
  
  private byte memoizedIsInitialized;
  
  private Int64Value(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memoizedIsInitialized = -1;
  }
  
  private Int64Value() {
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(UnusedPrivateParameter unused) {
    return new Int64Value();
  }
  
  public final UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }
  
  private Int64Value(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null)
      throw new NullPointerException(); 
    UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            continue;
          case 8:
            this.value_ = input.readInt64();
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
    return WrappersProto.internal_static_google_protobuf_Int64Value_descriptor;
  }
  
  protected FieldAccessorTable internalGetFieldAccessorTable() {
    return WrappersProto.internal_static_google_protobuf_Int64Value_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Int64Value.class, (Class)Builder.class);
  }
  
  public long getValue() {
    return this.value_;
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
    if (this.value_ != 0L)
      output.writeInt64(1, this.value_); 
    this.unknownFields.writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.value_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(1, this.value_); 
    size += this.unknownFields.getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof Int64Value))
      return super.equals(obj); 
    Int64Value other = (Int64Value)obj;
    if (getValue() != other
      .getValue())
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
    hash = 37 * hash + 1;
    hash = 53 * hash + Internal.hashLong(
        getValue());
    hash = 29 * hash + this.unknownFields.hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static Int64Value parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static Int64Value parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Int64Value parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static Int64Value parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Int64Value parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static Int64Value parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Int64Value parseFrom(InputStream input) throws IOException {
    return 
      GeneratedMessageV3.<Int64Value>parseWithIOException(PARSER, input);
  }
  
  public static Int64Value parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<Int64Value>parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Int64Value parseDelimitedFrom(InputStream input) throws IOException {
    return 
      GeneratedMessageV3.<Int64Value>parseDelimitedWithIOException(PARSER, input);
  }
  
  public static Int64Value parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<Int64Value>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Int64Value parseFrom(CodedInputStream input) throws IOException {
    return 
      GeneratedMessageV3.<Int64Value>parseWithIOException(PARSER, input);
  }
  
  public static Int64Value parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<Int64Value>parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Int64Value prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements Int64ValueOrBuilder {
    private long value_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return WrappersProto.internal_static_google_protobuf_Int64Value_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return WrappersProto.internal_static_google_protobuf_Int64Value_fieldAccessorTable
        .ensureFieldAccessorsInitialized((Class)Int64Value.class, (Class)Builder.class);
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
      this.value_ = 0L;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return WrappersProto.internal_static_google_protobuf_Int64Value_descriptor;
    }
    
    public Int64Value getDefaultInstanceForType() {
      return Int64Value.getDefaultInstance();
    }
    
    public Int64Value build() {
      Int64Value result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public Int64Value buildPartial() {
      Int64Value result = new Int64Value(this);
      result.value_ = this.value_;
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
      if (other instanceof Int64Value)
        return mergeFrom((Int64Value)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(Int64Value other) {
      if (other == Int64Value.getDefaultInstance())
        return this; 
      if (other.getValue() != 0L)
        setValue(other.getValue()); 
      mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      Int64Value parsedMessage = null;
      try {
        parsedMessage = Int64Value.PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (InvalidProtocolBufferException e) {
        parsedMessage = (Int64Value)e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null)
          mergeFrom(parsedMessage); 
      } 
      return this;
    }
    
    public long getValue() {
      return this.value_;
    }
    
    public Builder setValue(long value) {
      this.value_ = value;
      onChanged();
      return this;
    }
    
    public Builder clearValue() {
      this.value_ = 0L;
      onChanged();
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final Int64Value DEFAULT_INSTANCE = new Int64Value();
  
  public static Int64Value getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  public static Int64Value of(long value) {
    return newBuilder().setValue(value).build();
  }
  
  private static final Parser<Int64Value> PARSER = new AbstractParser<Int64Value>() {
      public Int64Value parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return new Int64Value(input, extensionRegistry);
      }
    };
  
  public static Parser<Int64Value> parser() {
    return PARSER;
  }
  
  public Parser<Int64Value> getParserForType() {
    return PARSER;
  }
  
  public Int64Value getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\Int64Value.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */