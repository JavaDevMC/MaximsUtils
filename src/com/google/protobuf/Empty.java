package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Empty extends GeneratedMessageV3 implements EmptyOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private byte memoizedIsInitialized;
  
  private Empty(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memoizedIsInitialized = -1;
  }
  
  private Empty() {
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(UnusedPrivateParameter unused) {
    return new Empty();
  }
  
  public final UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }
  
  private Empty(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
    return EmptyProto.internal_static_google_protobuf_Empty_descriptor;
  }
  
  protected FieldAccessorTable internalGetFieldAccessorTable() {
    return EmptyProto.internal_static_google_protobuf_Empty_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Empty.class, (Class)Builder.class);
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
    this.unknownFields.writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    size += this.unknownFields.getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof Empty))
      return super.equals(obj); 
    Empty other = (Empty)obj;
    if (!this.unknownFields.equals(other.unknownFields))
      return false; 
    return true;
  }
  
  public int hashCode() {
    if (this.memoizedHashCode != 0)
      return this.memoizedHashCode; 
    int hash = 41;
    hash = 19 * hash + getDescriptor().hashCode();
    hash = 29 * hash + this.unknownFields.hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static Empty parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static Empty parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Empty parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static Empty parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Empty parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static Empty parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Empty parseFrom(InputStream input) throws IOException {
    return 
      GeneratedMessageV3.<Empty>parseWithIOException(PARSER, input);
  }
  
  public static Empty parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<Empty>parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Empty parseDelimitedFrom(InputStream input) throws IOException {
    return 
      GeneratedMessageV3.<Empty>parseDelimitedWithIOException(PARSER, input);
  }
  
  public static Empty parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<Empty>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Empty parseFrom(CodedInputStream input) throws IOException {
    return 
      GeneratedMessageV3.<Empty>parseWithIOException(PARSER, input);
  }
  
  public static Empty parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<Empty>parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Empty prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EmptyOrBuilder {
    public static final Descriptors.Descriptor getDescriptor() {
      return EmptyProto.internal_static_google_protobuf_Empty_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return EmptyProto.internal_static_google_protobuf_Empty_fieldAccessorTable
        .ensureFieldAccessorsInitialized((Class)Empty.class, (Class)Builder.class);
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
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return EmptyProto.internal_static_google_protobuf_Empty_descriptor;
    }
    
    public Empty getDefaultInstanceForType() {
      return Empty.getDefaultInstance();
    }
    
    public Empty build() {
      Empty result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public Empty buildPartial() {
      Empty result = new Empty(this);
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
      if (other instanceof Empty)
        return mergeFrom((Empty)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(Empty other) {
      if (other == Empty.getDefaultInstance())
        return this; 
      mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      Empty parsedMessage = null;
      try {
        parsedMessage = Empty.PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (InvalidProtocolBufferException e) {
        parsedMessage = (Empty)e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null)
          mergeFrom(parsedMessage); 
      } 
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final Empty DEFAULT_INSTANCE = new Empty();
  
  public static Empty getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<Empty> PARSER = new AbstractParser<Empty>() {
      public Empty parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return new Empty(input, extensionRegistry);
      }
    };
  
  public static Parser<Empty> parser() {
    return PARSER;
  }
  
  public Parser<Empty> getParserForType() {
    return PARSER;
  }
  
  public Empty getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\Empty.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */