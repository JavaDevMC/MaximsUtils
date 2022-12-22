package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class BoolValue extends GeneratedMessageV3 implements BoolValueOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int VALUE_FIELD_NUMBER = 1;
  
  private boolean value_;
  
  private byte memoizedIsInitialized;
  
  private BoolValue(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memoizedIsInitialized = -1;
  }
  
  private BoolValue() {
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(UnusedPrivateParameter unused) {
    return new BoolValue();
  }
  
  public final UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }
  
  private BoolValue(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
            this.value_ = input.readBool();
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
    return WrappersProto.internal_static_google_protobuf_BoolValue_descriptor;
  }
  
  protected FieldAccessorTable internalGetFieldAccessorTable() {
    return WrappersProto.internal_static_google_protobuf_BoolValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)BoolValue.class, (Class)Builder.class);
  }
  
  public boolean getValue() {
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
    if (this.value_)
      output.writeBool(1, this.value_); 
    this.unknownFields.writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.value_)
      size += 
        CodedOutputStream.computeBoolSize(1, this.value_); 
    size += this.unknownFields.getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof BoolValue))
      return super.equals(obj); 
    BoolValue other = (BoolValue)obj;
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
    hash = 53 * hash + Internal.hashBoolean(
        getValue());
    hash = 29 * hash + this.unknownFields.hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static BoolValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static BoolValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static BoolValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static BoolValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static BoolValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static BoolValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static BoolValue parseFrom(InputStream input) throws IOException {
    return 
      GeneratedMessageV3.<BoolValue>parseWithIOException(PARSER, input);
  }
  
  public static BoolValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<BoolValue>parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static BoolValue parseDelimitedFrom(InputStream input) throws IOException {
    return 
      GeneratedMessageV3.<BoolValue>parseDelimitedWithIOException(PARSER, input);
  }
  
  public static BoolValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<BoolValue>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static BoolValue parseFrom(CodedInputStream input) throws IOException {
    return 
      GeneratedMessageV3.<BoolValue>parseWithIOException(PARSER, input);
  }
  
  public static BoolValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<BoolValue>parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(BoolValue prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BoolValueOrBuilder {
    private boolean value_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return WrappersProto.internal_static_google_protobuf_BoolValue_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return WrappersProto.internal_static_google_protobuf_BoolValue_fieldAccessorTable
        .ensureFieldAccessorsInitialized((Class)BoolValue.class, (Class)Builder.class);
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
      this.value_ = false;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return WrappersProto.internal_static_google_protobuf_BoolValue_descriptor;
    }
    
    public BoolValue getDefaultInstanceForType() {
      return BoolValue.getDefaultInstance();
    }
    
    public BoolValue build() {
      BoolValue result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public BoolValue buildPartial() {
      BoolValue result = new BoolValue(this);
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
      if (other instanceof BoolValue)
        return mergeFrom((BoolValue)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(BoolValue other) {
      if (other == BoolValue.getDefaultInstance())
        return this; 
      if (other.getValue())
        setValue(other.getValue()); 
      mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      BoolValue parsedMessage = null;
      try {
        parsedMessage = BoolValue.PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (InvalidProtocolBufferException e) {
        parsedMessage = (BoolValue)e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null)
          mergeFrom(parsedMessage); 
      } 
      return this;
    }
    
    public boolean getValue() {
      return this.value_;
    }
    
    public Builder setValue(boolean value) {
      this.value_ = value;
      onChanged();
      return this;
    }
    
    public Builder clearValue() {
      this.value_ = false;
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
  
  private static final BoolValue DEFAULT_INSTANCE = new BoolValue();
  
  public static BoolValue getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  public static BoolValue of(boolean value) {
    return newBuilder().setValue(value).build();
  }
  
  private static final Parser<BoolValue> PARSER = new AbstractParser<BoolValue>() {
      public BoolValue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return new BoolValue(input, extensionRegistry);
      }
    };
  
  public static Parser<BoolValue> parser() {
    return PARSER;
  }
  
  public Parser<BoolValue> getParserForType() {
    return PARSER;
  }
  
  public BoolValue getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\BoolValue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */