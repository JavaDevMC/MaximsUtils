package com.mysql.cj.x.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxConnection {
  private static final Descriptors.Descriptor internal_static_Mysqlx_Connection_Capability_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Connection_Capability_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Connection_Capabilities_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Connection_Capabilities_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Connection_CapabilitiesGet_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Connection_CapabilitiesGet_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Connection_CapabilitiesSet_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Connection_CapabilitiesSet_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Connection_Close_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Connection_Close_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Connection_Compression_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Connection_Compression_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static interface CapabilityOrBuilder extends MessageOrBuilder {
    boolean hasName();
    
    String getName();
    
    ByteString getNameBytes();
    
    boolean hasValue();
    
    MysqlxDatatypes.Any getValue();
    
    MysqlxDatatypes.AnyOrBuilder getValueOrBuilder();
  }
  
  public static final class Capability extends GeneratedMessageV3 implements CapabilityOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int NAME_FIELD_NUMBER = 1;
    
    private volatile Object name_;
    
    public static final int VALUE_FIELD_NUMBER = 2;
    
    private MysqlxDatatypes.Any value_;
    
    private byte memoizedIsInitialized;
    
    private Capability(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Capability() {
      this.memoizedIsInitialized = -1;
      this.name_ = "";
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Capability();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Capability(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      int mutable_bitField0_ = 0;
      UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          ByteString bs;
          MysqlxDatatypes.Any.Builder subBuilder;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              bs = input.readBytes();
              this.bitField0_ |= 0x1;
              this.name_ = bs;
              continue;
            case 18:
              subBuilder = null;
              if ((this.bitField0_ & 0x2) != 0)
                subBuilder = this.value_.toBuilder(); 
              this.value_ = (MysqlxDatatypes.Any)input.readMessage(MysqlxDatatypes.Any.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(this.value_);
                this.value_ = subBuilder.buildPartial();
              } 
              this.bitField0_ |= 0x2;
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
      return MysqlxConnection.internal_static_Mysqlx_Connection_Capability_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxConnection.internal_static_Mysqlx_Connection_Capability_fieldAccessorTable.ensureFieldAccessorsInitialized(Capability.class, Builder.class);
    }
    
    public boolean hasName() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public String getName() {
      Object ref = this.name_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      if (bs.isValidUtf8())
        this.name_ = s; 
      return s;
    }
    
    public ByteString getNameBytes() {
      Object ref = this.name_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.name_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public boolean hasValue() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public MysqlxDatatypes.Any getValue() {
      return (this.value_ == null) ? MysqlxDatatypes.Any.getDefaultInstance() : this.value_;
    }
    
    public MysqlxDatatypes.AnyOrBuilder getValueOrBuilder() {
      return (this.value_ == null) ? MysqlxDatatypes.Any.getDefaultInstance() : this.value_;
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      if (!hasName()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      if (!hasValue()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      if (!getValue().isInitialized()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        GeneratedMessageV3.writeString(output, 1, this.name_); 
      if ((this.bitField0_ & 0x2) != 0)
        output.writeMessage(2, (MessageLite)getValue()); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += GeneratedMessageV3.computeStringSize(1, this.name_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeMessageSize(2, (MessageLite)getValue()); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Capability))
        return super.equals(obj); 
      Capability other = (Capability)obj;
      if (hasName() != other.hasName())
        return false; 
      if (hasName() && 
        
        !getName().equals(other.getName()))
        return false; 
      if (hasValue() != other.hasValue())
        return false; 
      if (hasValue() && 
        
        !getValue().equals(other.getValue()))
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
      if (hasName()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getName().hashCode();
      } 
      if (hasValue()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getValue().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Capability parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Capability)PARSER.parseFrom(data);
    }
    
    public static Capability parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Capability)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Capability parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Capability)PARSER.parseFrom(data);
    }
    
    public static Capability parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Capability)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Capability parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Capability)PARSER.parseFrom(data);
    }
    
    public static Capability parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Capability)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Capability parseFrom(InputStream input) throws IOException {
      return 
        (Capability)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Capability parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Capability)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Capability parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Capability)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Capability parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Capability)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Capability parseFrom(CodedInputStream input) throws IOException {
      return 
        (Capability)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Capability parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Capability)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Capability prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CapabilityOrBuilder {
      private int bitField0_;
      
      private Object name_;
      
      private MysqlxDatatypes.Any value_;
      
      private SingleFieldBuilderV3<MysqlxDatatypes.Any, MysqlxDatatypes.Any.Builder, MysqlxDatatypes.AnyOrBuilder> valueBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_Capability_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_Capability_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Capability.class, Builder.class);
      }
      
      private Builder() {
        this.name_ = "";
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.name_ = "";
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Capability.alwaysUseFieldBuilders)
          getValueFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        if (this.valueBuilder_ == null) {
          this.value_ = null;
        } else {
          this.valueBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_Capability_descriptor;
      }
      
      public Capability getDefaultInstanceForType() {
        return Capability.getDefaultInstance();
      }
      
      public Capability build() {
        Capability result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Capability buildPartial() {
        Capability result = new Capability(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.name_ = this.name_;
        if ((from_bitField0_ & 0x2) != 0) {
          if (this.valueBuilder_ == null) {
            result.value_ = this.value_;
          } else {
            result.value_ = (MysqlxDatatypes.Any)this.valueBuilder_.build();
          } 
          to_bitField0_ |= 0x2;
        } 
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public Builder setField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.setField(field, value);
      }
      
      public Builder clearField(Descriptors.FieldDescriptor field) {
        return (Builder)super.clearField(field);
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
        return (Builder)super.clearOneof(oneof);
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
        return (Builder)super.setRepeatedField(field, index, value);
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.addRepeatedField(field, value);
      }
      
      public Builder mergeFrom(Message other) {
        if (other instanceof Capability)
          return mergeFrom((Capability)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Capability other) {
        if (other == Capability.getDefaultInstance())
          return this; 
        if (other.hasName()) {
          this.bitField0_ |= 0x1;
          this.name_ = other.name_;
          onChanged();
        } 
        if (other.hasValue())
          mergeValue(other.getValue()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasName())
          return false; 
        if (!hasValue())
          return false; 
        if (!getValue().isInitialized())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Capability parsedMessage = null;
        try {
          parsedMessage = (Capability) Capability.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Capability)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasName() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public String getName() {
        Object ref = this.name_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8())
            this.name_ = s; 
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getNameBytes() {
        Object ref = this.name_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.name_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setName(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.name_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearName() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.name_ = Capability.getDefaultInstance().getName();
        onChanged();
        return this;
      }
      
      public Builder setNameBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.name_ = value;
        onChanged();
        return this;
      }
      
      public boolean hasValue() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public MysqlxDatatypes.Any getValue() {
        if (this.valueBuilder_ == null)
          return (this.value_ == null) ? MysqlxDatatypes.Any.getDefaultInstance() : this.value_; 
        return (MysqlxDatatypes.Any)this.valueBuilder_.getMessage();
      }
      
      public Builder setValue(MysqlxDatatypes.Any value) {
        if (this.valueBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.value_ = value;
          onChanged();
        } else {
          this.valueBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder setValue(MysqlxDatatypes.Any.Builder builderForValue) {
        if (this.valueBuilder_ == null) {
          this.value_ = builderForValue.build();
          onChanged();
        } else {
          this.valueBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder mergeValue(MysqlxDatatypes.Any value) {
        if (this.valueBuilder_ == null) {
          if ((this.bitField0_ & 0x2) != 0 && this.value_ != null && this.value_ != 
            
            MysqlxDatatypes.Any.getDefaultInstance()) {
            this
              .value_ = MysqlxDatatypes.Any.newBuilder(this.value_).mergeFrom(value).buildPartial();
          } else {
            this.value_ = value;
          } 
          onChanged();
        } else {
          this.valueBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder clearValue() {
        if (this.valueBuilder_ == null) {
          this.value_ = null;
          onChanged();
        } else {
          this.valueBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public MysqlxDatatypes.Any.Builder getValueBuilder() {
        this.bitField0_ |= 0x2;
        onChanged();
        return (MysqlxDatatypes.Any.Builder)getValueFieldBuilder().getBuilder();
      }
      
      public MysqlxDatatypes.AnyOrBuilder getValueOrBuilder() {
        if (this.valueBuilder_ != null)
          return (MysqlxDatatypes.AnyOrBuilder)this.valueBuilder_.getMessageOrBuilder(); 
        return (this.value_ == null) ? 
          MysqlxDatatypes.Any.getDefaultInstance() : this.value_;
      }
      
      private SingleFieldBuilderV3<MysqlxDatatypes.Any, MysqlxDatatypes.Any.Builder, MysqlxDatatypes.AnyOrBuilder> getValueFieldBuilder() {
        if (this.valueBuilder_ == null) {
          this
            
            .valueBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getValue(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.value_ = null;
        } 
        return this.valueBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final Capability DEFAULT_INSTANCE = new Capability();
    
    public static Capability getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Capability> PARSER = (Parser<Capability>)new AbstractParser<Capability>() {
        public Capability parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Capability(input, extensionRegistry);
        }
      };
    
    public static Parser<Capability> parser() {
      return PARSER;
    }
    
    public Parser<Capability> getParserForType() {
      return PARSER;
    }
    
    public Capability getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface CapabilitiesOrBuilder extends MessageOrBuilder {
    List<Capability> getCapabilitiesList();
    
    Capability getCapabilities(int param1Int);
    
    int getCapabilitiesCount();
    
    List<? extends CapabilityOrBuilder> getCapabilitiesOrBuilderList();
    
    CapabilityOrBuilder getCapabilitiesOrBuilder(int param1Int);
  }
  
  public static final class Capabilities extends GeneratedMessageV3 implements CapabilitiesOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int CAPABILITIES_FIELD_NUMBER = 1;
    
    private List<Capability> capabilities_;
    
    private byte memoizedIsInitialized;
    
    private Capabilities(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Capabilities() {
      this.memoizedIsInitialized = -1;
      this.capabilities_ = Collections.emptyList();
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Capabilities();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Capabilities(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      int mutable_bitField0_ = 0;
      UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              if ((mutable_bitField0_ & 0x1) == 0) {
                this.capabilities_ = new ArrayList<>();
                mutable_bitField0_ |= 0x1;
              } 
              this.capabilities_.add(input.readMessage(Capability.PARSER, extensionRegistry));
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
        if ((mutable_bitField0_ & 0x1) != 0)
          this.capabilities_ = Collections.unmodifiableList(this.capabilities_); 
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MysqlxConnection.internal_static_Mysqlx_Connection_Capabilities_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxConnection.internal_static_Mysqlx_Connection_Capabilities_fieldAccessorTable.ensureFieldAccessorsInitialized(Capabilities.class, Builder.class);
    }
    
    public List<Capability> getCapabilitiesList() {
      return this.capabilities_;
    }
    
    public List<? extends CapabilityOrBuilder> getCapabilitiesOrBuilderList() {
      return (List)this.capabilities_;
    }
    
    public int getCapabilitiesCount() {
      return this.capabilities_.size();
    }
    
    public Capability getCapabilities(int index) {
      return this.capabilities_.get(index);
    }
    
    public CapabilityOrBuilder getCapabilitiesOrBuilder(int index) {
      return this.capabilities_.get(index);
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      for (int i = 0; i < getCapabilitiesCount(); i++) {
        if (!getCapabilities(i).isInitialized()) {
          this.memoizedIsInitialized = 0;
          return false;
        } 
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      for (int i = 0; i < this.capabilities_.size(); i++)
        output.writeMessage(1, (MessageLite)this.capabilities_.get(i)); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      for (int i = 0; i < this.capabilities_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(1, (MessageLite)this.capabilities_.get(i)); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Capabilities))
        return super.equals(obj); 
      Capabilities other = (Capabilities)obj;
      if (!getCapabilitiesList().equals(other.getCapabilitiesList()))
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
      if (getCapabilitiesCount() > 0) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getCapabilitiesList().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Capabilities parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Capabilities)PARSER.parseFrom(data);
    }
    
    public static Capabilities parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Capabilities)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Capabilities parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Capabilities)PARSER.parseFrom(data);
    }
    
    public static Capabilities parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Capabilities)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Capabilities parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Capabilities)PARSER.parseFrom(data);
    }
    
    public static Capabilities parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Capabilities)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Capabilities parseFrom(InputStream input) throws IOException {
      return 
        (Capabilities)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Capabilities parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Capabilities)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Capabilities parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Capabilities)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Capabilities parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Capabilities)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Capabilities parseFrom(CodedInputStream input) throws IOException {
      return 
        (Capabilities)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Capabilities parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Capabilities)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Capabilities prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CapabilitiesOrBuilder {
      private int bitField0_;
      
      private List<Capability> capabilities_;
      
      private RepeatedFieldBuilderV3<Capability, Capability.Builder, CapabilityOrBuilder> capabilitiesBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_Capabilities_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_Capabilities_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Capabilities.class, Builder.class);
      }
      
      private Builder() {
        this
          .capabilities_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.capabilities_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Capabilities.alwaysUseFieldBuilders)
          getCapabilitiesFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        if (this.capabilitiesBuilder_ == null) {
          this.capabilities_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFE;
        } else {
          this.capabilitiesBuilder_.clear();
        } 
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_Capabilities_descriptor;
      }
      
      public Capabilities getDefaultInstanceForType() {
        return Capabilities.getDefaultInstance();
      }
      
      public Capabilities build() {
        Capabilities result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Capabilities buildPartial() {
        Capabilities result = new Capabilities(this);
        int from_bitField0_ = this.bitField0_;
        if (this.capabilitiesBuilder_ == null) {
          if ((this.bitField0_ & 0x1) != 0) {
            this.capabilities_ = Collections.unmodifiableList(this.capabilities_);
            this.bitField0_ &= 0xFFFFFFFE;
          } 
          result.capabilities_ = this.capabilities_;
        } else {
          result.capabilities_ = this.capabilitiesBuilder_.build();
        } 
        onBuilt();
        return result;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public Builder setField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.setField(field, value);
      }
      
      public Builder clearField(Descriptors.FieldDescriptor field) {
        return (Builder)super.clearField(field);
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
        return (Builder)super.clearOneof(oneof);
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
        return (Builder)super.setRepeatedField(field, index, value);
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.addRepeatedField(field, value);
      }
      
      public Builder mergeFrom(Message other) {
        if (other instanceof Capabilities)
          return mergeFrom((Capabilities)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Capabilities other) {
        if (other == Capabilities.getDefaultInstance())
          return this; 
        if (this.capabilitiesBuilder_ == null) {
          if (!other.capabilities_.isEmpty()) {
            if (this.capabilities_.isEmpty()) {
              this.capabilities_ = other.capabilities_;
              this.bitField0_ &= 0xFFFFFFFE;
            } else {
              ensureCapabilitiesIsMutable();
              this.capabilities_.addAll(other.capabilities_);
            } 
            onChanged();
          } 
        } else if (!other.capabilities_.isEmpty()) {
          if (this.capabilitiesBuilder_.isEmpty()) {
            this.capabilitiesBuilder_.dispose();
            this.capabilitiesBuilder_ = null;
            this.capabilities_ = other.capabilities_;
            this.bitField0_ &= 0xFFFFFFFE;
            this.capabilitiesBuilder_ = Capabilities.alwaysUseFieldBuilders ? getCapabilitiesFieldBuilder() : null;
          } else {
            this.capabilitiesBuilder_.addAllMessages(other.capabilities_);
          } 
        } 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        for (int i = 0; i < getCapabilitiesCount(); i++) {
          if (!getCapabilities(i).isInitialized())
            return false; 
        } 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Capabilities parsedMessage = null;
        try {
          parsedMessage = (Capabilities) Capabilities.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Capabilities)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      private void ensureCapabilitiesIsMutable() {
        if ((this.bitField0_ & 0x1) == 0) {
          this.capabilities_ = new ArrayList<>(this.capabilities_);
          this.bitField0_ |= 0x1;
        } 
      }
      
      public List<Capability> getCapabilitiesList() {
        if (this.capabilitiesBuilder_ == null)
          return Collections.unmodifiableList(this.capabilities_); 
        return this.capabilitiesBuilder_.getMessageList();
      }
      
      public int getCapabilitiesCount() {
        if (this.capabilitiesBuilder_ == null)
          return this.capabilities_.size(); 
        return this.capabilitiesBuilder_.getCount();
      }
      
      public Capability getCapabilities(int index) {
        if (this.capabilitiesBuilder_ == null)
          return this.capabilities_.get(index); 
        return (Capability)this.capabilitiesBuilder_.getMessage(index);
      }
      
      public Builder setCapabilities(int index, Capability value) {
        if (this.capabilitiesBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureCapabilitiesIsMutable();
          this.capabilities_.set(index, value);
          onChanged();
        } else {
          this.capabilitiesBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setCapabilities(int index, Capability.Builder builderForValue) {
        if (this.capabilitiesBuilder_ == null) {
          ensureCapabilitiesIsMutable();
          this.capabilities_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.capabilitiesBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addCapabilities(Capability value) {
        if (this.capabilitiesBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureCapabilitiesIsMutable();
          this.capabilities_.add(value);
          onChanged();
        } else {
          this.capabilitiesBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addCapabilities(int index, Capability value) {
        if (this.capabilitiesBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureCapabilitiesIsMutable();
          this.capabilities_.add(index, value);
          onChanged();
        } else {
          this.capabilitiesBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addCapabilities(Capability.Builder builderForValue) {
        if (this.capabilitiesBuilder_ == null) {
          ensureCapabilitiesIsMutable();
          this.capabilities_.add(builderForValue.build());
          onChanged();
        } else {
          this.capabilitiesBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addCapabilities(int index, Capability.Builder builderForValue) {
        if (this.capabilitiesBuilder_ == null) {
          ensureCapabilitiesIsMutable();
          this.capabilities_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.capabilitiesBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllCapabilities(Iterable<? extends Capability> values) {
        if (this.capabilitiesBuilder_ == null) {
          ensureCapabilitiesIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.capabilities_);
          onChanged();
        } else {
          this.capabilitiesBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearCapabilities() {
        if (this.capabilitiesBuilder_ == null) {
          this.capabilities_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFE;
          onChanged();
        } else {
          this.capabilitiesBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeCapabilities(int index) {
        if (this.capabilitiesBuilder_ == null) {
          ensureCapabilitiesIsMutable();
          this.capabilities_.remove(index);
          onChanged();
        } else {
          this.capabilitiesBuilder_.remove(index);
        } 
        return this;
      }
      
      public Capability.Builder getCapabilitiesBuilder(int index) {
        return (Capability.Builder)getCapabilitiesFieldBuilder().getBuilder(index);
      }
      
      public CapabilityOrBuilder getCapabilitiesOrBuilder(int index) {
        if (this.capabilitiesBuilder_ == null)
          return this.capabilities_.get(index); 
        return (CapabilityOrBuilder)this.capabilitiesBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends CapabilityOrBuilder> getCapabilitiesOrBuilderList() {
        if (this.capabilitiesBuilder_ != null)
          return this.capabilitiesBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.capabilities_);
      }
      
      public Capability.Builder addCapabilitiesBuilder() {
        return (Capability.Builder)getCapabilitiesFieldBuilder().addBuilder(
            (AbstractMessage) Capability.getDefaultInstance());
      }
      
      public Capability.Builder addCapabilitiesBuilder(int index) {
        return (Capability.Builder)getCapabilitiesFieldBuilder().addBuilder(index,
            (AbstractMessage) Capability.getDefaultInstance());
      }
      
      public List<Capability.Builder> getCapabilitiesBuilderList() {
        return getCapabilitiesFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<Capability, Capability.Builder, CapabilityOrBuilder> getCapabilitiesFieldBuilder() {
        if (this.capabilitiesBuilder_ == null) {
          this
            
            .capabilitiesBuilder_ = new RepeatedFieldBuilderV3(this.capabilities_, ((this.bitField0_ & 0x1) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.capabilities_ = null;
        } 
        return this.capabilitiesBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final Capabilities DEFAULT_INSTANCE = new Capabilities();
    
    public static Capabilities getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Capabilities> PARSER = (Parser<Capabilities>)new AbstractParser<Capabilities>() {
        public Capabilities parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Capabilities(input, extensionRegistry);
        }
      };
    
    public static Parser<Capabilities> parser() {
      return PARSER;
    }
    
    public Parser<Capabilities> getParserForType() {
      return PARSER;
    }
    
    public Capabilities getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface CapabilitiesGetOrBuilder extends MessageOrBuilder {}
  
  public static final class CapabilitiesGet extends GeneratedMessageV3 implements CapabilitiesGetOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private byte memoizedIsInitialized;
    
    private CapabilitiesGet(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private CapabilitiesGet() {
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new CapabilitiesGet();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private CapabilitiesGet(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
      return MysqlxConnection.internal_static_Mysqlx_Connection_CapabilitiesGet_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxConnection.internal_static_Mysqlx_Connection_CapabilitiesGet_fieldAccessorTable.ensureFieldAccessorsInitialized(CapabilitiesGet.class, Builder.class);
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
      if (!(obj instanceof CapabilitiesGet))
        return super.equals(obj); 
      CapabilitiesGet other = (CapabilitiesGet)obj;
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
    
    public static CapabilitiesGet parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (CapabilitiesGet)PARSER.parseFrom(data);
    }
    
    public static CapabilitiesGet parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CapabilitiesGet)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CapabilitiesGet parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (CapabilitiesGet)PARSER.parseFrom(data);
    }
    
    public static CapabilitiesGet parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CapabilitiesGet)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CapabilitiesGet parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (CapabilitiesGet)PARSER.parseFrom(data);
    }
    
    public static CapabilitiesGet parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CapabilitiesGet)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CapabilitiesGet parseFrom(InputStream input) throws IOException {
      return 
        (CapabilitiesGet)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CapabilitiesGet parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CapabilitiesGet)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CapabilitiesGet parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (CapabilitiesGet)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static CapabilitiesGet parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CapabilitiesGet)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CapabilitiesGet parseFrom(CodedInputStream input) throws IOException {
      return 
        (CapabilitiesGet)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CapabilitiesGet parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CapabilitiesGet)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(CapabilitiesGet prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CapabilitiesGetOrBuilder {
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_CapabilitiesGet_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_CapabilitiesGet_fieldAccessorTable
          .ensureFieldAccessorsInitialized(CapabilitiesGet.class, Builder.class);
      }
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (CapabilitiesGet.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_CapabilitiesGet_descriptor;
      }
      
      public CapabilitiesGet getDefaultInstanceForType() {
        return CapabilitiesGet.getDefaultInstance();
      }
      
      public CapabilitiesGet build() {
        CapabilitiesGet result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public CapabilitiesGet buildPartial() {
        CapabilitiesGet result = new CapabilitiesGet(this);
        onBuilt();
        return result;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public Builder setField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.setField(field, value);
      }
      
      public Builder clearField(Descriptors.FieldDescriptor field) {
        return (Builder)super.clearField(field);
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
        return (Builder)super.clearOneof(oneof);
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
        return (Builder)super.setRepeatedField(field, index, value);
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.addRepeatedField(field, value);
      }
      
      public Builder mergeFrom(Message other) {
        if (other instanceof CapabilitiesGet)
          return mergeFrom((CapabilitiesGet)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(CapabilitiesGet other) {
        if (other == CapabilitiesGet.getDefaultInstance())
          return this; 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        CapabilitiesGet parsedMessage = null;
        try {
          parsedMessage = (CapabilitiesGet) CapabilitiesGet.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (CapabilitiesGet)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final CapabilitiesGet DEFAULT_INSTANCE = new CapabilitiesGet();
    
    public static CapabilitiesGet getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<CapabilitiesGet> PARSER = (Parser<CapabilitiesGet>)new AbstractParser<CapabilitiesGet>() {
        public CapabilitiesGet parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new CapabilitiesGet(input, extensionRegistry);
        }
      };
    
    public static Parser<CapabilitiesGet> parser() {
      return PARSER;
    }
    
    public Parser<CapabilitiesGet> getParserForType() {
      return PARSER;
    }
    
    public CapabilitiesGet getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface CapabilitiesSetOrBuilder extends MessageOrBuilder {
    boolean hasCapabilities();
    
    Capabilities getCapabilities();
    
    CapabilitiesOrBuilder getCapabilitiesOrBuilder();
  }
  
  public static final class CapabilitiesSet extends GeneratedMessageV3 implements CapabilitiesSetOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int CAPABILITIES_FIELD_NUMBER = 1;
    
    private Capabilities capabilities_;
    
    private byte memoizedIsInitialized;
    
    private CapabilitiesSet(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private CapabilitiesSet() {
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new CapabilitiesSet();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private CapabilitiesSet(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      int mutable_bitField0_ = 0;
      UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          Capabilities.Builder subBuilder;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              subBuilder = null;
              if ((this.bitField0_ & 0x1) != 0)
                subBuilder = this.capabilities_.toBuilder(); 
              this.capabilities_ = (Capabilities)input.readMessage(Capabilities.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(this.capabilities_);
                this.capabilities_ = subBuilder.buildPartial();
              } 
              this.bitField0_ |= 0x1;
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
      return MysqlxConnection.internal_static_Mysqlx_Connection_CapabilitiesSet_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxConnection.internal_static_Mysqlx_Connection_CapabilitiesSet_fieldAccessorTable.ensureFieldAccessorsInitialized(CapabilitiesSet.class, Builder.class);
    }
    
    public boolean hasCapabilities() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public Capabilities getCapabilities() {
      return (this.capabilities_ == null) ? Capabilities.getDefaultInstance() : this.capabilities_;
    }
    
    public CapabilitiesOrBuilder getCapabilitiesOrBuilder() {
      return (this.capabilities_ == null) ? Capabilities.getDefaultInstance() : this.capabilities_;
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      if (!hasCapabilities()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      if (!getCapabilities().isInitialized()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        output.writeMessage(1, (MessageLite)getCapabilities()); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeMessageSize(1, (MessageLite)getCapabilities()); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof CapabilitiesSet))
        return super.equals(obj); 
      CapabilitiesSet other = (CapabilitiesSet)obj;
      if (hasCapabilities() != other.hasCapabilities())
        return false; 
      if (hasCapabilities() && 
        
        !getCapabilities().equals(other.getCapabilities()))
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
      if (hasCapabilities()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getCapabilities().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static CapabilitiesSet parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (CapabilitiesSet)PARSER.parseFrom(data);
    }
    
    public static CapabilitiesSet parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CapabilitiesSet)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CapabilitiesSet parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (CapabilitiesSet)PARSER.parseFrom(data);
    }
    
    public static CapabilitiesSet parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CapabilitiesSet)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CapabilitiesSet parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (CapabilitiesSet)PARSER.parseFrom(data);
    }
    
    public static CapabilitiesSet parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CapabilitiesSet)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CapabilitiesSet parseFrom(InputStream input) throws IOException {
      return 
        (CapabilitiesSet)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CapabilitiesSet parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CapabilitiesSet)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CapabilitiesSet parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (CapabilitiesSet)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static CapabilitiesSet parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CapabilitiesSet)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CapabilitiesSet parseFrom(CodedInputStream input) throws IOException {
      return 
        (CapabilitiesSet)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CapabilitiesSet parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CapabilitiesSet)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(CapabilitiesSet prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CapabilitiesSetOrBuilder {
      private int bitField0_;
      
      private Capabilities capabilities_;
      
      private SingleFieldBuilderV3<Capabilities, Capabilities.Builder, CapabilitiesOrBuilder> capabilitiesBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_CapabilitiesSet_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_CapabilitiesSet_fieldAccessorTable
          .ensureFieldAccessorsInitialized(CapabilitiesSet.class, Builder.class);
      }
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (CapabilitiesSet.alwaysUseFieldBuilders)
          getCapabilitiesFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        if (this.capabilitiesBuilder_ == null) {
          this.capabilities_ = null;
        } else {
          this.capabilitiesBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_CapabilitiesSet_descriptor;
      }
      
      public CapabilitiesSet getDefaultInstanceForType() {
        return CapabilitiesSet.getDefaultInstance();
      }
      
      public CapabilitiesSet build() {
        CapabilitiesSet result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public CapabilitiesSet buildPartial() {
        CapabilitiesSet result = new CapabilitiesSet(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0) {
          if (this.capabilitiesBuilder_ == null) {
            result.capabilities_ = this.capabilities_;
          } else {
            result.capabilities_ = (Capabilities)this.capabilitiesBuilder_.build();
          } 
          to_bitField0_ |= 0x1;
        } 
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public Builder setField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.setField(field, value);
      }
      
      public Builder clearField(Descriptors.FieldDescriptor field) {
        return (Builder)super.clearField(field);
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
        return (Builder)super.clearOneof(oneof);
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
        return (Builder)super.setRepeatedField(field, index, value);
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.addRepeatedField(field, value);
      }
      
      public Builder mergeFrom(Message other) {
        if (other instanceof CapabilitiesSet)
          return mergeFrom((CapabilitiesSet)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(CapabilitiesSet other) {
        if (other == CapabilitiesSet.getDefaultInstance())
          return this; 
        if (other.hasCapabilities())
          mergeCapabilities(other.getCapabilities()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasCapabilities())
          return false; 
        if (!getCapabilities().isInitialized())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        CapabilitiesSet parsedMessage = null;
        try {
          parsedMessage = (CapabilitiesSet) CapabilitiesSet.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (CapabilitiesSet)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasCapabilities() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public Capabilities getCapabilities() {
        if (this.capabilitiesBuilder_ == null)
          return (this.capabilities_ == null) ? Capabilities.getDefaultInstance() : this.capabilities_;
        return (Capabilities)this.capabilitiesBuilder_.getMessage();
      }
      
      public Builder setCapabilities(Capabilities value) {
        if (this.capabilitiesBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.capabilities_ = value;
          onChanged();
        } else {
          this.capabilitiesBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x1;
        return this;
      }
      
      public Builder setCapabilities(Capabilities.Builder builderForValue) {
        if (this.capabilitiesBuilder_ == null) {
          this.capabilities_ = builderForValue.build();
          onChanged();
        } else {
          this.capabilitiesBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x1;
        return this;
      }
      
      public Builder mergeCapabilities(Capabilities value) {
        if (this.capabilitiesBuilder_ == null) {
          if ((this.bitField0_ & 0x1) != 0 && this.capabilities_ != null && this.capabilities_ != 
            
            Capabilities.getDefaultInstance()) {
            this
              .capabilities_ = Capabilities.newBuilder(this.capabilities_).mergeFrom(value).buildPartial();
          } else {
            this.capabilities_ = value;
          } 
          onChanged();
        } else {
          this.capabilitiesBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x1;
        return this;
      }
      
      public Builder clearCapabilities() {
        if (this.capabilitiesBuilder_ == null) {
          this.capabilities_ = null;
          onChanged();
        } else {
          this.capabilitiesBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
      }
      
      public Capabilities.Builder getCapabilitiesBuilder() {
        this.bitField0_ |= 0x1;
        onChanged();
        return (Capabilities.Builder)getCapabilitiesFieldBuilder().getBuilder();
      }
      
      public CapabilitiesOrBuilder getCapabilitiesOrBuilder() {
        if (this.capabilitiesBuilder_ != null)
          return (CapabilitiesOrBuilder)this.capabilitiesBuilder_.getMessageOrBuilder();
        return (this.capabilities_ == null) ? 
          Capabilities.getDefaultInstance() : this.capabilities_;
      }
      
      private SingleFieldBuilderV3<Capabilities, Capabilities.Builder, CapabilitiesOrBuilder> getCapabilitiesFieldBuilder() {
        if (this.capabilitiesBuilder_ == null) {
          this
            
            .capabilitiesBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getCapabilities(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.capabilities_ = null;
        } 
        return this.capabilitiesBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final CapabilitiesSet DEFAULT_INSTANCE = new CapabilitiesSet();
    
    public static CapabilitiesSet getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<CapabilitiesSet> PARSER = (Parser<CapabilitiesSet>)new AbstractParser<CapabilitiesSet>() {
        public CapabilitiesSet parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new CapabilitiesSet(input, extensionRegistry);
        }
      };
    
    public static Parser<CapabilitiesSet> parser() {
      return PARSER;
    }
    
    public Parser<CapabilitiesSet> getParserForType() {
      return PARSER;
    }
    
    public CapabilitiesSet getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface CloseOrBuilder extends MessageOrBuilder {}
  
  public static final class Close extends GeneratedMessageV3 implements CloseOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private byte memoizedIsInitialized;
    
    private Close(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Close() {
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Close();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Close(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
      return MysqlxConnection.internal_static_Mysqlx_Connection_Close_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxConnection.internal_static_Mysqlx_Connection_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
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
      if (!(obj instanceof Close))
        return super.equals(obj); 
      Close other = (Close)obj;
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
    
    public static Close parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Close)PARSER.parseFrom(data);
    }
    
    public static Close parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Close)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Close parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Close)PARSER.parseFrom(data);
    }
    
    public static Close parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Close)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Close parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Close)PARSER.parseFrom(data);
    }
    
    public static Close parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Close)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Close parseFrom(InputStream input) throws IOException {
      return 
        (Close)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Close parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Close)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Close parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Close)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Close parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Close)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Close parseFrom(CodedInputStream input) throws IOException {
      return 
        (Close)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Close parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Close)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Close prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CloseOrBuilder {
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_Close_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_Close_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Close.class, Builder.class);
      }
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Close.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_Close_descriptor;
      }
      
      public Close getDefaultInstanceForType() {
        return Close.getDefaultInstance();
      }
      
      public Close build() {
        Close result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Close buildPartial() {
        Close result = new Close(this);
        onBuilt();
        return result;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public Builder setField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.setField(field, value);
      }
      
      public Builder clearField(Descriptors.FieldDescriptor field) {
        return (Builder)super.clearField(field);
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
        return (Builder)super.clearOneof(oneof);
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
        return (Builder)super.setRepeatedField(field, index, value);
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.addRepeatedField(field, value);
      }
      
      public Builder mergeFrom(Message other) {
        if (other instanceof Close)
          return mergeFrom((Close)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Close other) {
        if (other == Close.getDefaultInstance())
          return this; 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Close parsedMessage = null;
        try {
          parsedMessage = (Close) Close.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Close)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final Close DEFAULT_INSTANCE = new Close();
    
    public static Close getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Close> PARSER = (Parser<Close>)new AbstractParser<Close>() {
        public Close parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Close(input, extensionRegistry);
        }
      };
    
    public static Parser<Close> parser() {
      return PARSER;
    }
    
    public Parser<Close> getParserForType() {
      return PARSER;
    }
    
    public Close getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface CompressionOrBuilder extends MessageOrBuilder {
    boolean hasUncompressedSize();
    
    long getUncompressedSize();
    
    boolean hasServerMessages();
    
    Mysqlx.ServerMessages.Type getServerMessages();
    
    boolean hasClientMessages();
    
    Mysqlx.ClientMessages.Type getClientMessages();
    
    boolean hasPayload();
    
    ByteString getPayload();
  }
  
  public static final class Compression extends GeneratedMessageV3 implements CompressionOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int UNCOMPRESSED_SIZE_FIELD_NUMBER = 1;
    
    private long uncompressedSize_;
    
    public static final int SERVER_MESSAGES_FIELD_NUMBER = 2;
    
    private int serverMessages_;
    
    public static final int CLIENT_MESSAGES_FIELD_NUMBER = 3;
    
    private int clientMessages_;
    
    public static final int PAYLOAD_FIELD_NUMBER = 4;
    
    private ByteString payload_;
    
    private byte memoizedIsInitialized;
    
    private Compression(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Compression() {
      this.memoizedIsInitialized = -1;
      this.serverMessages_ = 0;
      this.clientMessages_ = 1;
      this.payload_ = ByteString.EMPTY;
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Compression();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Compression(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      int mutable_bitField0_ = 0;
      UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int rawValue;
          Mysqlx.ServerMessages.Type type;
          Mysqlx.ClientMessages.Type value;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 8:
              this.bitField0_ |= 0x1;
              this.uncompressedSize_ = input.readUInt64();
              continue;
            case 16:
              rawValue = input.readEnum();
              type = Mysqlx.ServerMessages.Type.valueOf(rawValue);
              if (type == null) {
                unknownFields.mergeVarintField(2, rawValue);
                continue;
              } 
              this.bitField0_ |= 0x2;
              this.serverMessages_ = rawValue;
              continue;
            case 24:
              rawValue = input.readEnum();
              value = Mysqlx.ClientMessages.Type.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(3, rawValue);
                continue;
              } 
              this.bitField0_ |= 0x4;
              this.clientMessages_ = rawValue;
              continue;
            case 34:
              this.bitField0_ |= 0x8;
              this.payload_ = input.readBytes();
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
      return MysqlxConnection.internal_static_Mysqlx_Connection_Compression_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxConnection.internal_static_Mysqlx_Connection_Compression_fieldAccessorTable.ensureFieldAccessorsInitialized(Compression.class, Builder.class);
    }
    
    public boolean hasUncompressedSize() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public long getUncompressedSize() {
      return this.uncompressedSize_;
    }
    
    public boolean hasServerMessages() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public Mysqlx.ServerMessages.Type getServerMessages() {
      Mysqlx.ServerMessages.Type result = Mysqlx.ServerMessages.Type.valueOf(this.serverMessages_);
      return (result == null) ? Mysqlx.ServerMessages.Type.OK : result;
    }
    
    public boolean hasClientMessages() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public Mysqlx.ClientMessages.Type getClientMessages() {
      Mysqlx.ClientMessages.Type result = Mysqlx.ClientMessages.Type.valueOf(this.clientMessages_);
      return (result == null) ? Mysqlx.ClientMessages.Type.CON_CAPABILITIES_GET : result;
    }
    
    public boolean hasPayload() {
      return ((this.bitField0_ & 0x8) != 0);
    }
    
    public ByteString getPayload() {
      return this.payload_;
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      if (!hasPayload()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        output.writeUInt64(1, this.uncompressedSize_); 
      if ((this.bitField0_ & 0x2) != 0)
        output.writeEnum(2, this.serverMessages_); 
      if ((this.bitField0_ & 0x4) != 0)
        output.writeEnum(3, this.clientMessages_); 
      if ((this.bitField0_ & 0x8) != 0)
        output.writeBytes(4, this.payload_); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeUInt64Size(1, this.uncompressedSize_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeEnumSize(2, this.serverMessages_); 
      if ((this.bitField0_ & 0x4) != 0)
        size += 
          CodedOutputStream.computeEnumSize(3, this.clientMessages_); 
      if ((this.bitField0_ & 0x8) != 0)
        size += 
          CodedOutputStream.computeBytesSize(4, this.payload_); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Compression))
        return super.equals(obj); 
      Compression other = (Compression)obj;
      if (hasUncompressedSize() != other.hasUncompressedSize())
        return false; 
      if (hasUncompressedSize() && 
        getUncompressedSize() != other
        .getUncompressedSize())
        return false; 
      if (hasServerMessages() != other.hasServerMessages())
        return false; 
      if (hasServerMessages() && 
        this.serverMessages_ != other.serverMessages_)
        return false; 
      if (hasClientMessages() != other.hasClientMessages())
        return false; 
      if (hasClientMessages() && 
        this.clientMessages_ != other.clientMessages_)
        return false; 
      if (hasPayload() != other.hasPayload())
        return false; 
      if (hasPayload() && 
        
        !getPayload().equals(other.getPayload()))
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
      if (hasUncompressedSize()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + Internal.hashLong(
            getUncompressedSize());
      } 
      if (hasServerMessages()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + this.serverMessages_;
      } 
      if (hasClientMessages()) {
        hash = 37 * hash + 3;
        hash = 53 * hash + this.clientMessages_;
      } 
      if (hasPayload()) {
        hash = 37 * hash + 4;
        hash = 53 * hash + getPayload().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Compression parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Compression)PARSER.parseFrom(data);
    }
    
    public static Compression parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Compression)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Compression parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Compression)PARSER.parseFrom(data);
    }
    
    public static Compression parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Compression)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Compression parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Compression)PARSER.parseFrom(data);
    }
    
    public static Compression parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Compression)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Compression parseFrom(InputStream input) throws IOException {
      return 
        (Compression)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Compression parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Compression)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Compression parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Compression)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Compression parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Compression)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Compression parseFrom(CodedInputStream input) throws IOException {
      return 
        (Compression)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Compression parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Compression)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Compression prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CompressionOrBuilder {
      private int bitField0_;
      
      private long uncompressedSize_;
      
      private int serverMessages_;
      
      private int clientMessages_;
      
      private ByteString payload_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_Compression_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_Compression_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Compression.class, Builder.class);
      }
      
      private Builder() {
        this.serverMessages_ = 0;
        this.clientMessages_ = 1;
        this.payload_ = ByteString.EMPTY;
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.serverMessages_ = 0;
        this.clientMessages_ = 1;
        this.payload_ = ByteString.EMPTY;
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Compression.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        this.uncompressedSize_ = 0L;
        this.bitField0_ &= 0xFFFFFFFE;
        this.serverMessages_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        this.clientMessages_ = 1;
        this.bitField0_ &= 0xFFFFFFFB;
        this.payload_ = ByteString.EMPTY;
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxConnection.internal_static_Mysqlx_Connection_Compression_descriptor;
      }
      
      public Compression getDefaultInstanceForType() {
        return Compression.getDefaultInstance();
      }
      
      public Compression build() {
        Compression result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Compression buildPartial() {
        Compression result = new Compression(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0) {
          result.uncompressedSize_ = this.uncompressedSize_;
          to_bitField0_ |= 0x1;
        } 
        if ((from_bitField0_ & 0x2) != 0)
          to_bitField0_ |= 0x2; 
        result.serverMessages_ = this.serverMessages_;
        if ((from_bitField0_ & 0x4) != 0)
          to_bitField0_ |= 0x4; 
        result.clientMessages_ = this.clientMessages_;
        if ((from_bitField0_ & 0x8) != 0)
          to_bitField0_ |= 0x8; 
        result.payload_ = this.payload_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public Builder setField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.setField(field, value);
      }
      
      public Builder clearField(Descriptors.FieldDescriptor field) {
        return (Builder)super.clearField(field);
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
        return (Builder)super.clearOneof(oneof);
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
        return (Builder)super.setRepeatedField(field, index, value);
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.addRepeatedField(field, value);
      }
      
      public Builder mergeFrom(Message other) {
        if (other instanceof Compression)
          return mergeFrom((Compression)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Compression other) {
        if (other == Compression.getDefaultInstance())
          return this; 
        if (other.hasUncompressedSize())
          setUncompressedSize(other.getUncompressedSize()); 
        if (other.hasServerMessages())
          setServerMessages(other.getServerMessages()); 
        if (other.hasClientMessages())
          setClientMessages(other.getClientMessages()); 
        if (other.hasPayload())
          setPayload(other.getPayload()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasPayload())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Compression parsedMessage = null;
        try {
          parsedMessage = (Compression) Compression.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Compression)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasUncompressedSize() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public long getUncompressedSize() {
        return this.uncompressedSize_;
      }
      
      public Builder setUncompressedSize(long value) {
        this.bitField0_ |= 0x1;
        this.uncompressedSize_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearUncompressedSize() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.uncompressedSize_ = 0L;
        onChanged();
        return this;
      }
      
      public boolean hasServerMessages() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public Mysqlx.ServerMessages.Type getServerMessages() {
        Mysqlx.ServerMessages.Type result = Mysqlx.ServerMessages.Type.valueOf(this.serverMessages_);
        return (result == null) ? Mysqlx.ServerMessages.Type.OK : result;
      }
      
      public Builder setServerMessages(Mysqlx.ServerMessages.Type value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.serverMessages_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearServerMessages() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.serverMessages_ = 0;
        onChanged();
        return this;
      }
      
      public boolean hasClientMessages() {
        return ((this.bitField0_ & 0x4) != 0);
      }
      
      public Mysqlx.ClientMessages.Type getClientMessages() {
        Mysqlx.ClientMessages.Type result = Mysqlx.ClientMessages.Type.valueOf(this.clientMessages_);
        return (result == null) ? Mysqlx.ClientMessages.Type.CON_CAPABILITIES_GET : result;
      }
      
      public Builder setClientMessages(Mysqlx.ClientMessages.Type value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x4;
        this.clientMessages_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearClientMessages() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.clientMessages_ = 1;
        onChanged();
        return this;
      }
      
      public boolean hasPayload() {
        return ((this.bitField0_ & 0x8) != 0);
      }
      
      public ByteString getPayload() {
        return this.payload_;
      }
      
      public Builder setPayload(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x8;
        this.payload_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearPayload() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.payload_ = Compression.getDefaultInstance().getPayload();
        onChanged();
        return this;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final Compression DEFAULT_INSTANCE = new Compression();
    
    public static Compression getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Compression> PARSER = (Parser<Compression>)new AbstractParser<Compression>() {
        public Compression parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Compression(input, extensionRegistry);
        }
      };
    
    public static Parser<Compression> parser() {
      return PARSER;
    }
    
    public Parser<Compression> getParserForType() {
      return PARSER;
    }
    
    public Compression getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\027mysqlx_connection.proto\022\021Mysqlx.Connection\032\026mysqlx_datatypes.proto\032\fmysqlx.proto\"@\n\nCapability\022\f\n\004name\030\001 \002(\t\022$\n\005value\030\002 \002(\0132\025.Mysqlx.Datatypes.Any\"I\n\fCapabilities\0223\n\fcapabilities\030\001 \003(\0132\035.Mysqlx.Connection.Capability:\004????0\002\"\027\n\017CapabilitiesGet:\004????0\001\"N\n\017CapabilitiesSet\0225\n\fcapabilities\030\001 \002(\0132\037.Mysqlx.Connection.Capabilities:\004????0\002\"\r\n\005Close:\004????0\003\"??\001\n\013Compression\022\031\n\021uncompressed_size\030\001 \001(\004\0224\n\017server_messages\030\002 \001(\0162\033.Mysqlx.ServerMessages.Type\0224\n\017client_messages\030\003 \001(\0162\033.Mysqlx.ClientMessages.Type\022\017\n\007payload\030\004 \002(\f:\b????0\023????0.B\031\n\027com.mysql.cj.x.protobuf" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { MysqlxDatatypes.getDescriptor(), 
          Mysqlx.getDescriptor() });
    internal_static_Mysqlx_Connection_Capability_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_Mysqlx_Connection_Capability_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Connection_Capability_descriptor, new String[] { "Name", "Value" });
    internal_static_Mysqlx_Connection_Capabilities_descriptor = getDescriptor().getMessageTypes().get(1);
    internal_static_Mysqlx_Connection_Capabilities_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Connection_Capabilities_descriptor, new String[] { "Capabilities" });
    internal_static_Mysqlx_Connection_CapabilitiesGet_descriptor = getDescriptor().getMessageTypes().get(2);
    internal_static_Mysqlx_Connection_CapabilitiesGet_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Connection_CapabilitiesGet_descriptor, new String[0]);
    internal_static_Mysqlx_Connection_CapabilitiesSet_descriptor = getDescriptor().getMessageTypes().get(3);
    internal_static_Mysqlx_Connection_CapabilitiesSet_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Connection_CapabilitiesSet_descriptor, new String[] { "Capabilities" });
    internal_static_Mysqlx_Connection_Close_descriptor = getDescriptor().getMessageTypes().get(4);
    internal_static_Mysqlx_Connection_Close_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Connection_Close_descriptor, new String[0]);
    internal_static_Mysqlx_Connection_Compression_descriptor = getDescriptor().getMessageTypes().get(5);
    internal_static_Mysqlx_Connection_Compression_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Connection_Compression_descriptor, new String[] { "UncompressedSize", "ServerMessages", "ClientMessages", "Payload" });
    ExtensionRegistry registry = ExtensionRegistry.newInstance();
    registry.add(Mysqlx.clientMessageId);
    registry.add(Mysqlx.serverMessageId);
    Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
    MysqlxDatatypes.getDescriptor();
    Mysqlx.getDescriptor();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\x\protobuf\MysqlxConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */