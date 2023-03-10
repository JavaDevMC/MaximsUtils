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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class MysqlxSession {
  private static final Descriptors.Descriptor internal_static_Mysqlx_Session_AuthenticateStart_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Session_AuthenticateStart_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Session_AuthenticateContinue_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Session_AuthenticateContinue_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Session_AuthenticateOk_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Session_AuthenticateOk_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Session_Reset_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Session_Reset_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Session_Close_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Session_Close_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static interface AuthenticateStartOrBuilder extends MessageOrBuilder {
    boolean hasMechName();
    
    String getMechName();
    
    ByteString getMechNameBytes();
    
    boolean hasAuthData();
    
    ByteString getAuthData();
    
    boolean hasInitialResponse();
    
    ByteString getInitialResponse();
  }
  
  public static final class AuthenticateStart extends GeneratedMessageV3 implements AuthenticateStartOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int MECH_NAME_FIELD_NUMBER = 1;
    
    private volatile Object mechName_;
    
    public static final int AUTH_DATA_FIELD_NUMBER = 2;
    
    private ByteString authData_;
    
    public static final int INITIAL_RESPONSE_FIELD_NUMBER = 3;
    
    private ByteString initialResponse_;
    
    private byte memoizedIsInitialized;
    
    private AuthenticateStart(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private AuthenticateStart() {
      this.memoizedIsInitialized = -1;
      this.mechName_ = "";
      this.authData_ = ByteString.EMPTY;
      this.initialResponse_ = ByteString.EMPTY;
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new AuthenticateStart();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private AuthenticateStart(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      int mutable_bitField0_ = 0;
      UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          ByteString bs;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              bs = input.readBytes();
              this.bitField0_ |= 0x1;
              this.mechName_ = bs;
              continue;
            case 18:
              this.bitField0_ |= 0x2;
              this.authData_ = input.readBytes();
              continue;
            case 26:
              this.bitField0_ |= 0x4;
              this.initialResponse_ = input.readBytes();
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
      return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateStart_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateStart_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateStart.class, Builder.class);
    }
    
    public boolean hasMechName() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public String getMechName() {
      Object ref = this.mechName_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      if (bs.isValidUtf8())
        this.mechName_ = s; 
      return s;
    }
    
    public ByteString getMechNameBytes() {
      Object ref = this.mechName_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.mechName_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public boolean hasAuthData() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public ByteString getAuthData() {
      return this.authData_;
    }
    
    public boolean hasInitialResponse() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public ByteString getInitialResponse() {
      return this.initialResponse_;
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      if (!hasMechName()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        GeneratedMessageV3.writeString(output, 1, this.mechName_); 
      if ((this.bitField0_ & 0x2) != 0)
        output.writeBytes(2, this.authData_); 
      if ((this.bitField0_ & 0x4) != 0)
        output.writeBytes(3, this.initialResponse_); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += GeneratedMessageV3.computeStringSize(1, this.mechName_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeBytesSize(2, this.authData_); 
      if ((this.bitField0_ & 0x4) != 0)
        size += 
          CodedOutputStream.computeBytesSize(3, this.initialResponse_); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof AuthenticateStart))
        return super.equals(obj); 
      AuthenticateStart other = (AuthenticateStart)obj;
      if (hasMechName() != other.hasMechName())
        return false; 
      if (hasMechName() && 
        
        !getMechName().equals(other.getMechName()))
        return false; 
      if (hasAuthData() != other.hasAuthData())
        return false; 
      if (hasAuthData() && 
        
        !getAuthData().equals(other.getAuthData()))
        return false; 
      if (hasInitialResponse() != other.hasInitialResponse())
        return false; 
      if (hasInitialResponse() && 
        
        !getInitialResponse().equals(other.getInitialResponse()))
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
      if (hasMechName()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getMechName().hashCode();
      } 
      if (hasAuthData()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getAuthData().hashCode();
      } 
      if (hasInitialResponse()) {
        hash = 37 * hash + 3;
        hash = 53 * hash + getInitialResponse().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static AuthenticateStart parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (AuthenticateStart)PARSER.parseFrom(data);
    }
    
    public static AuthenticateStart parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AuthenticateStart)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static AuthenticateStart parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (AuthenticateStart)PARSER.parseFrom(data);
    }
    
    public static AuthenticateStart parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AuthenticateStart)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static AuthenticateStart parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (AuthenticateStart)PARSER.parseFrom(data);
    }
    
    public static AuthenticateStart parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AuthenticateStart)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static AuthenticateStart parseFrom(InputStream input) throws IOException {
      return 
        (AuthenticateStart)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static AuthenticateStart parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (AuthenticateStart)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static AuthenticateStart parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (AuthenticateStart)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static AuthenticateStart parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (AuthenticateStart)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static AuthenticateStart parseFrom(CodedInputStream input) throws IOException {
      return 
        (AuthenticateStart)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static AuthenticateStart parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (AuthenticateStart)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(AuthenticateStart prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuthenticateStartOrBuilder {
      private int bitField0_;
      
      private Object mechName_;
      
      private ByteString authData_;
      
      private ByteString initialResponse_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateStart_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateStart_fieldAccessorTable
          .ensureFieldAccessorsInitialized(AuthenticateStart.class, Builder.class);
      }
      
      private Builder() {
        this.mechName_ = "";
        this.authData_ = ByteString.EMPTY;
        this.initialResponse_ = ByteString.EMPTY;
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.mechName_ = "";
        this.authData_ = ByteString.EMPTY;
        this.initialResponse_ = ByteString.EMPTY;
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (AuthenticateStart.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        this.mechName_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.authData_ = ByteString.EMPTY;
        this.bitField0_ &= 0xFFFFFFFD;
        this.initialResponse_ = ByteString.EMPTY;
        this.bitField0_ &= 0xFFFFFFFB;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateStart_descriptor;
      }
      
      public AuthenticateStart getDefaultInstanceForType() {
        return AuthenticateStart.getDefaultInstance();
      }
      
      public AuthenticateStart build() {
        AuthenticateStart result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public AuthenticateStart buildPartial() {
        AuthenticateStart result = new AuthenticateStart(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.mechName_ = this.mechName_;
        if ((from_bitField0_ & 0x2) != 0)
          to_bitField0_ |= 0x2; 
        result.authData_ = this.authData_;
        if ((from_bitField0_ & 0x4) != 0)
          to_bitField0_ |= 0x4; 
        result.initialResponse_ = this.initialResponse_;
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
        if (other instanceof AuthenticateStart)
          return mergeFrom((AuthenticateStart)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(AuthenticateStart other) {
        if (other == AuthenticateStart.getDefaultInstance())
          return this; 
        if (other.hasMechName()) {
          this.bitField0_ |= 0x1;
          this.mechName_ = other.mechName_;
          onChanged();
        } 
        if (other.hasAuthData())
          setAuthData(other.getAuthData()); 
        if (other.hasInitialResponse())
          setInitialResponse(other.getInitialResponse()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasMechName())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        AuthenticateStart parsedMessage = null;
        try {
          parsedMessage = (AuthenticateStart) AuthenticateStart.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (AuthenticateStart)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasMechName() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public String getMechName() {
        Object ref = this.mechName_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8())
            this.mechName_ = s; 
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getMechNameBytes() {
        Object ref = this.mechName_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.mechName_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setMechName(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.mechName_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearMechName() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.mechName_ = AuthenticateStart.getDefaultInstance().getMechName();
        onChanged();
        return this;
      }
      
      public Builder setMechNameBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.mechName_ = value;
        onChanged();
        return this;
      }
      
      public boolean hasAuthData() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public ByteString getAuthData() {
        return this.authData_;
      }
      
      public Builder setAuthData(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.authData_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearAuthData() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.authData_ = AuthenticateStart.getDefaultInstance().getAuthData();
        onChanged();
        return this;
      }
      
      public boolean hasInitialResponse() {
        return ((this.bitField0_ & 0x4) != 0);
      }
      
      public ByteString getInitialResponse() {
        return this.initialResponse_;
      }
      
      public Builder setInitialResponse(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x4;
        this.initialResponse_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearInitialResponse() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.initialResponse_ = AuthenticateStart.getDefaultInstance().getInitialResponse();
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
    
    private static final AuthenticateStart DEFAULT_INSTANCE = new AuthenticateStart();
    
    public static AuthenticateStart getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<AuthenticateStart> PARSER = (Parser<AuthenticateStart>)new AbstractParser<AuthenticateStart>() {
        public AuthenticateStart parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new AuthenticateStart(input, extensionRegistry);
        }
      };
    
    public static Parser<AuthenticateStart> parser() {
      return PARSER;
    }
    
    public Parser<AuthenticateStart> getParserForType() {
      return PARSER;
    }
    
    public AuthenticateStart getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface AuthenticateContinueOrBuilder extends MessageOrBuilder {
    boolean hasAuthData();
    
    ByteString getAuthData();
  }
  
  public static final class AuthenticateContinue extends GeneratedMessageV3 implements AuthenticateContinueOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int AUTH_DATA_FIELD_NUMBER = 1;
    
    private ByteString authData_;
    
    private byte memoizedIsInitialized;
    
    private AuthenticateContinue(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private AuthenticateContinue() {
      this.memoizedIsInitialized = -1;
      this.authData_ = ByteString.EMPTY;
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new AuthenticateContinue();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private AuthenticateContinue(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
              this.bitField0_ |= 0x1;
              this.authData_ = input.readBytes();
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
      return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateContinue_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateContinue_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateContinue.class, Builder.class);
    }
    
    public boolean hasAuthData() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public ByteString getAuthData() {
      return this.authData_;
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      if (!hasAuthData()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        output.writeBytes(1, this.authData_); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeBytesSize(1, this.authData_); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof AuthenticateContinue))
        return super.equals(obj); 
      AuthenticateContinue other = (AuthenticateContinue)obj;
      if (hasAuthData() != other.hasAuthData())
        return false; 
      if (hasAuthData() && 
        
        !getAuthData().equals(other.getAuthData()))
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
      if (hasAuthData()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getAuthData().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static AuthenticateContinue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (AuthenticateContinue)PARSER.parseFrom(data);
    }
    
    public static AuthenticateContinue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AuthenticateContinue)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static AuthenticateContinue parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (AuthenticateContinue)PARSER.parseFrom(data);
    }
    
    public static AuthenticateContinue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AuthenticateContinue)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static AuthenticateContinue parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (AuthenticateContinue)PARSER.parseFrom(data);
    }
    
    public static AuthenticateContinue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AuthenticateContinue)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static AuthenticateContinue parseFrom(InputStream input) throws IOException {
      return 
        (AuthenticateContinue)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static AuthenticateContinue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (AuthenticateContinue)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static AuthenticateContinue parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (AuthenticateContinue)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static AuthenticateContinue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (AuthenticateContinue)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static AuthenticateContinue parseFrom(CodedInputStream input) throws IOException {
      return 
        (AuthenticateContinue)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static AuthenticateContinue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (AuthenticateContinue)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(AuthenticateContinue prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuthenticateContinueOrBuilder {
      private int bitField0_;
      
      private ByteString authData_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateContinue_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateContinue_fieldAccessorTable
          .ensureFieldAccessorsInitialized(AuthenticateContinue.class, Builder.class);
      }
      
      private Builder() {
        this.authData_ = ByteString.EMPTY;
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.authData_ = ByteString.EMPTY;
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (AuthenticateContinue.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        this.authData_ = ByteString.EMPTY;
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateContinue_descriptor;
      }
      
      public AuthenticateContinue getDefaultInstanceForType() {
        return AuthenticateContinue.getDefaultInstance();
      }
      
      public AuthenticateContinue build() {
        AuthenticateContinue result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public AuthenticateContinue buildPartial() {
        AuthenticateContinue result = new AuthenticateContinue(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.authData_ = this.authData_;
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
        if (other instanceof AuthenticateContinue)
          return mergeFrom((AuthenticateContinue)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(AuthenticateContinue other) {
        if (other == AuthenticateContinue.getDefaultInstance())
          return this; 
        if (other.hasAuthData())
          setAuthData(other.getAuthData()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasAuthData())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        AuthenticateContinue parsedMessage = null;
        try {
          parsedMessage = (AuthenticateContinue) AuthenticateContinue.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (AuthenticateContinue)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasAuthData() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public ByteString getAuthData() {
        return this.authData_;
      }
      
      public Builder setAuthData(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.authData_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearAuthData() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.authData_ = AuthenticateContinue.getDefaultInstance().getAuthData();
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
    
    private static final AuthenticateContinue DEFAULT_INSTANCE = new AuthenticateContinue();
    
    public static AuthenticateContinue getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<AuthenticateContinue> PARSER = (Parser<AuthenticateContinue>)new AbstractParser<AuthenticateContinue>() {
        public AuthenticateContinue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new AuthenticateContinue(input, extensionRegistry);
        }
      };
    
    public static Parser<AuthenticateContinue> parser() {
      return PARSER;
    }
    
    public Parser<AuthenticateContinue> getParserForType() {
      return PARSER;
    }
    
    public AuthenticateContinue getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface AuthenticateOkOrBuilder extends MessageOrBuilder {
    boolean hasAuthData();
    
    ByteString getAuthData();
  }
  
  public static final class AuthenticateOk extends GeneratedMessageV3 implements AuthenticateOkOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int AUTH_DATA_FIELD_NUMBER = 1;
    
    private ByteString authData_;
    
    private byte memoizedIsInitialized;
    
    private AuthenticateOk(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private AuthenticateOk() {
      this.memoizedIsInitialized = -1;
      this.authData_ = ByteString.EMPTY;
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new AuthenticateOk();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private AuthenticateOk(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
              this.bitField0_ |= 0x1;
              this.authData_ = input.readBytes();
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
      return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateOk_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateOk_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateOk.class, Builder.class);
    }
    
    public boolean hasAuthData() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public ByteString getAuthData() {
      return this.authData_;
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
      if ((this.bitField0_ & 0x1) != 0)
        output.writeBytes(1, this.authData_); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeBytesSize(1, this.authData_); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof AuthenticateOk))
        return super.equals(obj); 
      AuthenticateOk other = (AuthenticateOk)obj;
      if (hasAuthData() != other.hasAuthData())
        return false; 
      if (hasAuthData() && 
        
        !getAuthData().equals(other.getAuthData()))
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
      if (hasAuthData()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getAuthData().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static AuthenticateOk parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (AuthenticateOk)PARSER.parseFrom(data);
    }
    
    public static AuthenticateOk parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AuthenticateOk)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static AuthenticateOk parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (AuthenticateOk)PARSER.parseFrom(data);
    }
    
    public static AuthenticateOk parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AuthenticateOk)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static AuthenticateOk parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (AuthenticateOk)PARSER.parseFrom(data);
    }
    
    public static AuthenticateOk parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AuthenticateOk)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static AuthenticateOk parseFrom(InputStream input) throws IOException {
      return 
        (AuthenticateOk)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static AuthenticateOk parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (AuthenticateOk)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static AuthenticateOk parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (AuthenticateOk)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static AuthenticateOk parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (AuthenticateOk)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static AuthenticateOk parseFrom(CodedInputStream input) throws IOException {
      return 
        (AuthenticateOk)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static AuthenticateOk parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (AuthenticateOk)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(AuthenticateOk prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuthenticateOkOrBuilder {
      private int bitField0_;
      
      private ByteString authData_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateOk_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateOk_fieldAccessorTable
          .ensureFieldAccessorsInitialized(AuthenticateOk.class, Builder.class);
      }
      
      private Builder() {
        this.authData_ = ByteString.EMPTY;
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.authData_ = ByteString.EMPTY;
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (AuthenticateOk.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        this.authData_ = ByteString.EMPTY;
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxSession.internal_static_Mysqlx_Session_AuthenticateOk_descriptor;
      }
      
      public AuthenticateOk getDefaultInstanceForType() {
        return AuthenticateOk.getDefaultInstance();
      }
      
      public AuthenticateOk build() {
        AuthenticateOk result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public AuthenticateOk buildPartial() {
        AuthenticateOk result = new AuthenticateOk(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.authData_ = this.authData_;
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
        if (other instanceof AuthenticateOk)
          return mergeFrom((AuthenticateOk)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(AuthenticateOk other) {
        if (other == AuthenticateOk.getDefaultInstance())
          return this; 
        if (other.hasAuthData())
          setAuthData(other.getAuthData()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        AuthenticateOk parsedMessage = null;
        try {
          parsedMessage = (AuthenticateOk) AuthenticateOk.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (AuthenticateOk)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasAuthData() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public ByteString getAuthData() {
        return this.authData_;
      }
      
      public Builder setAuthData(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.authData_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearAuthData() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.authData_ = AuthenticateOk.getDefaultInstance().getAuthData();
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
    
    private static final AuthenticateOk DEFAULT_INSTANCE = new AuthenticateOk();
    
    public static AuthenticateOk getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<AuthenticateOk> PARSER = (Parser<AuthenticateOk>)new AbstractParser<AuthenticateOk>() {
        public AuthenticateOk parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new AuthenticateOk(input, extensionRegistry);
        }
      };
    
    public static Parser<AuthenticateOk> parser() {
      return PARSER;
    }
    
    public Parser<AuthenticateOk> getParserForType() {
      return PARSER;
    }
    
    public AuthenticateOk getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface ResetOrBuilder extends MessageOrBuilder {
    boolean hasKeepOpen();
    
    boolean getKeepOpen();
  }
  
  public static final class Reset extends GeneratedMessageV3 implements ResetOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int KEEP_OPEN_FIELD_NUMBER = 1;
    
    private boolean keepOpen_;
    
    private byte memoizedIsInitialized;
    
    private Reset(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Reset() {
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Reset();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Reset(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
            case 8:
              this.bitField0_ |= 0x1;
              this.keepOpen_ = input.readBool();
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
      return MysqlxSession.internal_static_Mysqlx_Session_Reset_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxSession.internal_static_Mysqlx_Session_Reset_fieldAccessorTable.ensureFieldAccessorsInitialized(Reset.class, Builder.class);
    }
    
    public boolean hasKeepOpen() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public boolean getKeepOpen() {
      return this.keepOpen_;
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
      if ((this.bitField0_ & 0x1) != 0)
        output.writeBool(1, this.keepOpen_); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeBoolSize(1, this.keepOpen_); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Reset))
        return super.equals(obj); 
      Reset other = (Reset)obj;
      if (hasKeepOpen() != other.hasKeepOpen())
        return false; 
      if (hasKeepOpen() && 
        getKeepOpen() != other
        .getKeepOpen())
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
      if (hasKeepOpen()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + Internal.hashBoolean(
            getKeepOpen());
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Reset parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Reset)PARSER.parseFrom(data);
    }
    
    public static Reset parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Reset)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Reset parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Reset)PARSER.parseFrom(data);
    }
    
    public static Reset parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Reset)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Reset parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Reset)PARSER.parseFrom(data);
    }
    
    public static Reset parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Reset)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Reset parseFrom(InputStream input) throws IOException {
      return 
        (Reset)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Reset parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Reset)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Reset parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Reset)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Reset parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Reset)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Reset parseFrom(CodedInputStream input) throws IOException {
      return 
        (Reset)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Reset parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Reset)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Reset prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ResetOrBuilder {
      private int bitField0_;
      
      private boolean keepOpen_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxSession.internal_static_Mysqlx_Session_Reset_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxSession.internal_static_Mysqlx_Session_Reset_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Reset.class, Builder.class);
      }
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Reset.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        this.keepOpen_ = false;
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxSession.internal_static_Mysqlx_Session_Reset_descriptor;
      }
      
      public Reset getDefaultInstanceForType() {
        return Reset.getDefaultInstance();
      }
      
      public Reset build() {
        Reset result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Reset buildPartial() {
        Reset result = new Reset(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0) {
          result.keepOpen_ = this.keepOpen_;
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
        if (other instanceof Reset)
          return mergeFrom((Reset)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Reset other) {
        if (other == Reset.getDefaultInstance())
          return this; 
        if (other.hasKeepOpen())
          setKeepOpen(other.getKeepOpen()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Reset parsedMessage = null;
        try {
          parsedMessage = (Reset) Reset.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Reset)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasKeepOpen() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public boolean getKeepOpen() {
        return this.keepOpen_;
      }
      
      public Builder setKeepOpen(boolean value) {
        this.bitField0_ |= 0x1;
        this.keepOpen_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearKeepOpen() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.keepOpen_ = false;
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
    
    private static final Reset DEFAULT_INSTANCE = new Reset();
    
    public static Reset getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Reset> PARSER = (Parser<Reset>)new AbstractParser<Reset>() {
        public Reset parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Reset(input, extensionRegistry);
        }
      };
    
    public static Parser<Reset> parser() {
      return PARSER;
    }
    
    public Parser<Reset> getParserForType() {
      return PARSER;
    }
    
    public Reset getDefaultInstanceForType() {
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
      return MysqlxSession.internal_static_Mysqlx_Session_Close_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxSession.internal_static_Mysqlx_Session_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
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
        return MysqlxSession.internal_static_Mysqlx_Session_Close_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxSession.internal_static_Mysqlx_Session_Close_fieldAccessorTable
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
        return MysqlxSession.internal_static_Mysqlx_Session_Close_descriptor;
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
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\024mysqlx_session.proto\022\016Mysqlx.Session\032\fmysqlx.proto\"Y\n\021AuthenticateStart\022\021\n\tmech_name\030\001 \002(\t\022\021\n\tauth_data\030\002 \001(\f\022\030\n\020initial_response\030\003 \001(\f:\004????0\004\"3\n\024AuthenticateContinue\022\021\n\tauth_data\030\001 \002(\f:\b????0\003????0\005\")\n\016AuthenticateOk\022\021\n\tauth_data\030\001 \001(\f:\004????0\004\"'\n\005Reset\022\030\n\tkeep_open\030\001 \001(\b:\005false:\004????0\006\"\r\n\005Close:\004????0\007B\031\n\027com.mysql.cj.x.protobuf" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { Mysqlx.getDescriptor() });
    internal_static_Mysqlx_Session_AuthenticateStart_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_Mysqlx_Session_AuthenticateStart_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Session_AuthenticateStart_descriptor, new String[] { "MechName", "AuthData", "InitialResponse" });
    internal_static_Mysqlx_Session_AuthenticateContinue_descriptor = getDescriptor().getMessageTypes().get(1);
    internal_static_Mysqlx_Session_AuthenticateContinue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Session_AuthenticateContinue_descriptor, new String[] { "AuthData" });
    internal_static_Mysqlx_Session_AuthenticateOk_descriptor = getDescriptor().getMessageTypes().get(2);
    internal_static_Mysqlx_Session_AuthenticateOk_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Session_AuthenticateOk_descriptor, new String[] { "AuthData" });
    internal_static_Mysqlx_Session_Reset_descriptor = getDescriptor().getMessageTypes().get(3);
    internal_static_Mysqlx_Session_Reset_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Session_Reset_descriptor, new String[] { "KeepOpen" });
    internal_static_Mysqlx_Session_Close_descriptor = getDescriptor().getMessageTypes().get(4);
    internal_static_Mysqlx_Session_Close_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Session_Close_descriptor, new String[0]);
    ExtensionRegistry registry = ExtensionRegistry.newInstance();
    registry.add(Mysqlx.clientMessageId);
    registry.add(Mysqlx.serverMessageId);
    Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
    Mysqlx.getDescriptor();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\x\protobuf\MysqlxSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */