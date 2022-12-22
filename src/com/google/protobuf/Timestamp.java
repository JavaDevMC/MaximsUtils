package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Timestamp extends GeneratedMessageV3 implements TimestampOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int SECONDS_FIELD_NUMBER = 1;
  
  private long seconds_;
  
  public static final int NANOS_FIELD_NUMBER = 2;
  
  private int nanos_;
  
  private byte memoizedIsInitialized;
  
  private Timestamp(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memoizedIsInitialized = -1;
  }
  
  private Timestamp() {
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(UnusedPrivateParameter unused) {
    return new Timestamp();
  }
  
  public final UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }
  
  private Timestamp(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
            this.seconds_ = input.readInt64();
            continue;
          case 16:
            this.nanos_ = input.readInt32();
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
    return TimestampProto.internal_static_google_protobuf_Timestamp_descriptor;
  }
  
  protected FieldAccessorTable internalGetFieldAccessorTable() {
    return TimestampProto.internal_static_google_protobuf_Timestamp_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Timestamp.class, (Class)Builder.class);
  }
  
  public long getSeconds() {
    return this.seconds_;
  }
  
  public int getNanos() {
    return this.nanos_;
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
    if (this.seconds_ != 0L)
      output.writeInt64(1, this.seconds_); 
    if (this.nanos_ != 0)
      output.writeInt32(2, this.nanos_); 
    this.unknownFields.writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.seconds_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(1, this.seconds_); 
    if (this.nanos_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(2, this.nanos_); 
    size += this.unknownFields.getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof Timestamp))
      return super.equals(obj); 
    Timestamp other = (Timestamp)obj;
    if (getSeconds() != other
      .getSeconds())
      return false; 
    if (getNanos() != other
      .getNanos())
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
        getSeconds());
    hash = 37 * hash + 2;
    hash = 53 * hash + getNanos();
    hash = 29 * hash + this.unknownFields.hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static Timestamp parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static Timestamp parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Timestamp parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static Timestamp parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Timestamp parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  
  public static Timestamp parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Timestamp parseFrom(InputStream input) throws IOException {
    return 
      GeneratedMessageV3.<Timestamp>parseWithIOException(PARSER, input);
  }
  
  public static Timestamp parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<Timestamp>parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Timestamp parseDelimitedFrom(InputStream input) throws IOException {
    return 
      GeneratedMessageV3.<Timestamp>parseDelimitedWithIOException(PARSER, input);
  }
  
  public static Timestamp parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<Timestamp>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Timestamp parseFrom(CodedInputStream input) throws IOException {
    return 
      GeneratedMessageV3.<Timestamp>parseWithIOException(PARSER, input);
  }
  
  public static Timestamp parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      GeneratedMessageV3.<Timestamp>parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Timestamp prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TimestampOrBuilder {
    private long seconds_;
    
    private int nanos_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return TimestampProto.internal_static_google_protobuf_Timestamp_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return TimestampProto.internal_static_google_protobuf_Timestamp_fieldAccessorTable
        .ensureFieldAccessorsInitialized((Class)Timestamp.class, (Class)Builder.class);
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
      this.seconds_ = 0L;
      this.nanos_ = 0;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return TimestampProto.internal_static_google_protobuf_Timestamp_descriptor;
    }
    
    public Timestamp getDefaultInstanceForType() {
      return Timestamp.getDefaultInstance();
    }
    
    public Timestamp build() {
      Timestamp result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public Timestamp buildPartial() {
      Timestamp result = new Timestamp(this);
      result.seconds_ = this.seconds_;
      result.nanos_ = this.nanos_;
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
      if (other instanceof Timestamp)
        return mergeFrom((Timestamp)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(Timestamp other) {
      if (other == Timestamp.getDefaultInstance())
        return this; 
      if (other.getSeconds() != 0L)
        setSeconds(other.getSeconds()); 
      if (other.getNanos() != 0)
        setNanos(other.getNanos()); 
      mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      Timestamp parsedMessage = null;
      try {
        parsedMessage = Timestamp.PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (InvalidProtocolBufferException e) {
        parsedMessage = (Timestamp)e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null)
          mergeFrom(parsedMessage); 
      } 
      return this;
    }
    
    public long getSeconds() {
      return this.seconds_;
    }
    
    public Builder setSeconds(long value) {
      this.seconds_ = value;
      onChanged();
      return this;
    }
    
    public Builder clearSeconds() {
      this.seconds_ = 0L;
      onChanged();
      return this;
    }
    
    public int getNanos() {
      return this.nanos_;
    }
    
    public Builder setNanos(int value) {
      this.nanos_ = value;
      onChanged();
      return this;
    }
    
    public Builder clearNanos() {
      this.nanos_ = 0;
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
  
  private static final Timestamp DEFAULT_INSTANCE = new Timestamp();
  
  public static Timestamp getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<Timestamp> PARSER = new AbstractParser<Timestamp>() {
      public Timestamp parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return new Timestamp(input, extensionRegistry);
      }
    };
  
  public static Parser<Timestamp> parser() {
    return PARSER;
  }
  
  public Parser<Timestamp> getParserForType() {
    return PARSER;
  }
  
  public Timestamp getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\Timestamp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */