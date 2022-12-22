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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class MysqlxCursor {
  private static final Descriptors.Descriptor internal_static_Mysqlx_Cursor_Open_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Cursor_Open_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Cursor_Open_OneOfMessage_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Cursor_Fetch_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Cursor_Fetch_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Cursor_Close_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Cursor_Close_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static interface OpenOrBuilder extends MessageOrBuilder {
    boolean hasCursorId();
    
    int getCursorId();
    
    boolean hasStmt();
    
    Open.OneOfMessage getStmt();
    
    Open.OneOfMessageOrBuilder getStmtOrBuilder();
    
    boolean hasFetchRows();
    
    long getFetchRows();
  }
  
  public static final class Open extends GeneratedMessageV3 implements OpenOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int CURSOR_ID_FIELD_NUMBER = 1;
    
    private int cursorId_;
    
    public static final int STMT_FIELD_NUMBER = 4;
    
    private OneOfMessage stmt_;
    
    public static final int FETCH_ROWS_FIELD_NUMBER = 5;
    
    private long fetchRows_;
    
    private byte memoizedIsInitialized;
    
    private Open(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Open() {
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Open();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Open(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      int mutable_bitField0_ = 0;
      UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          OneOfMessage.Builder subBuilder;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 8:
              this.bitField0_ |= 0x1;
              this.cursorId_ = input.readUInt32();
              continue;
            case 34:
              subBuilder = null;
              if ((this.bitField0_ & 0x2) != 0)
                subBuilder = this.stmt_.toBuilder(); 
              this.stmt_ = (OneOfMessage)input.readMessage(OneOfMessage.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(this.stmt_);
                this.stmt_ = subBuilder.buildPartial();
              } 
              this.bitField0_ |= 0x2;
              continue;
            case 40:
              this.bitField0_ |= 0x4;
              this.fetchRows_ = input.readUInt64();
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
      return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_fieldAccessorTable.ensureFieldAccessorsInitialized(Open.class, Builder.class);
    }
    
    public static final class OneOfMessage extends GeneratedMessageV3 implements OneOfMessageOrBuilder {
      private static final long serialVersionUID = 0L;
      
      private int bitField0_;
      
      public static final int TYPE_FIELD_NUMBER = 1;
      
      private int type_;
      
      public static final int PREPARE_EXECUTE_FIELD_NUMBER = 2;
      
      private MysqlxPrepare.Execute prepareExecute_;
      
      private byte memoizedIsInitialized;
      
      private OneOfMessage(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
      }
      
      private OneOfMessage() {
        this.memoizedIsInitialized = -1;
        this.type_ = 0;
      }
      
      protected Object newInstance(UnusedPrivateParameter unused) {
        return new OneOfMessage();
      }
      
      public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
      }
      
      private OneOfMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null)
          throw new NullPointerException(); 
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
          boolean done = false;
          while (!done) {
            int rawValue;
            MysqlxPrepare.Execute.Builder subBuilder;
            Type value;
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                continue;
              case 8:
                rawValue = input.readEnum();
                value = Type.valueOf(rawValue);
                if (value == null) {
                  unknownFields.mergeVarintField(1, rawValue);
                  continue;
                } 
                this.bitField0_ |= 0x1;
                this.type_ = rawValue;
                continue;
              case 18:
                subBuilder = null;
                if ((this.bitField0_ & 0x2) != 0)
                  subBuilder = this.prepareExecute_.toBuilder(); 
                this.prepareExecute_ = (MysqlxPrepare.Execute)input.readMessage(MysqlxPrepare.Execute.PARSER, extensionRegistry);
                if (subBuilder != null) {
                  subBuilder.mergeFrom(this.prepareExecute_);
                  this.prepareExecute_ = subBuilder.buildPartial();
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
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_OneOfMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(OneOfMessage.class, Builder.class);
      }
      
      public enum Type implements ProtocolMessageEnum {
        PREPARE_EXECUTE(0);
        
        private final int value;
        
        private static final Type[] VALUES = values();
        
        private static final Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() {
            public Type findValueByNumber(int number) {
              return Type.forNumber(number);
            }
          };
        
        public static final int PREPARE_EXECUTE_VALUE = 0;
        
        public final int getNumber() {
          return this.value;
        }
        
        public static Type forNumber(int value) {
          switch (value) {
            case 0:
              return PREPARE_EXECUTE;
          } 
          return null;
        }
        
        public static Internal.EnumLiteMap<Type> internalGetValueMap() {
          return internalValueMap;
        }
        
        static {
        
        }
        
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
          return getDescriptor().getValues().get(ordinal());
        }
        
        public final Descriptors.EnumDescriptor getDescriptorForType() {
          return getDescriptor();
        }
        
        public static final Descriptors.EnumDescriptor getDescriptor() {
          return OneOfMessage.getDescriptor().getEnumTypes().get(0);
        }
        
        Type(int value) {
          this.value = value;
        }
      }
      
      public boolean hasType() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public Type getType() {
        Type result = Type.valueOf(this.type_);
        return (result == null) ? Type.PREPARE_EXECUTE : result;
      }
      
      public boolean hasPrepareExecute() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public MysqlxPrepare.Execute getPrepareExecute() {
        return (this.prepareExecute_ == null) ? MysqlxPrepare.Execute.getDefaultInstance() : this.prepareExecute_;
      }
      
      public MysqlxPrepare.ExecuteOrBuilder getPrepareExecuteOrBuilder() {
        return (this.prepareExecute_ == null) ? MysqlxPrepare.Execute.getDefaultInstance() : this.prepareExecute_;
      }
      
      public final boolean isInitialized() {
        byte isInitialized = this.memoizedIsInitialized;
        if (isInitialized == 1)
          return true; 
        if (isInitialized == 0)
          return false; 
        if (!hasType()) {
          this.memoizedIsInitialized = 0;
          return false;
        } 
        if (hasPrepareExecute() && !getPrepareExecute().isInitialized()) {
          this.memoizedIsInitialized = 0;
          return false;
        } 
        this.memoizedIsInitialized = 1;
        return true;
      }
      
      public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 0x1) != 0)
          output.writeEnum(1, this.type_); 
        if ((this.bitField0_ & 0x2) != 0)
          output.writeMessage(2, (MessageLite)getPrepareExecute()); 
        this.unknownFields.writeTo(output);
      }
      
      public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1)
          return size; 
        size = 0;
        if ((this.bitField0_ & 0x1) != 0)
          size += CodedOutputStream.computeEnumSize(1, this.type_); 
        if ((this.bitField0_ & 0x2) != 0)
          size += CodedOutputStream.computeMessageSize(2, (MessageLite)getPrepareExecute()); 
        size += this.unknownFields.getSerializedSize();
        this.memoizedSize = size;
        return size;
      }
      
      public boolean equals(Object obj) {
        if (obj == this)
          return true; 
        if (!(obj instanceof OneOfMessage))
          return super.equals(obj); 
        OneOfMessage other = (OneOfMessage)obj;
        if (hasType() != other.hasType())
          return false; 
        if (hasType() && this.type_ != other.type_)
          return false; 
        if (hasPrepareExecute() != other.hasPrepareExecute())
          return false; 
        if (hasPrepareExecute() && !getPrepareExecute().equals(other.getPrepareExecute()))
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
        if (hasType()) {
          hash = 37 * hash + 1;
          hash = 53 * hash + this.type_;
        } 
        if (hasPrepareExecute()) {
          hash = 37 * hash + 2;
          hash = 53 * hash + getPrepareExecute().hashCode();
        } 
        hash = 29 * hash + this.unknownFields.hashCode();
        this.memoizedHashCode = hash;
        return hash;
      }
      
      public static OneOfMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (OneOfMessage)PARSER.parseFrom(data);
      }
      
      public static OneOfMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (OneOfMessage)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static OneOfMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (OneOfMessage)PARSER.parseFrom(data);
      }
      
      public static OneOfMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (OneOfMessage)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static OneOfMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (OneOfMessage)PARSER.parseFrom(data);
      }
      
      public static OneOfMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (OneOfMessage)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static OneOfMessage parseFrom(InputStream input) throws IOException {
        return (OneOfMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
      }
      
      public static OneOfMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (OneOfMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
      }
      
      public static OneOfMessage parseDelimitedFrom(InputStream input) throws IOException {
        return (OneOfMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
      }
      
      public static OneOfMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (OneOfMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }
      
      public static OneOfMessage parseFrom(CodedInputStream input) throws IOException {
        return (OneOfMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
      }
      
      public static OneOfMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (OneOfMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
      }
      
      public Builder newBuilderForType() {
        return newBuilder();
      }
      
      public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
      }
      
      public static Builder newBuilder(OneOfMessage prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }
      
      public Builder toBuilder() {
        return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder()).mergeFrom(this);
      }
      
      protected Builder newBuilderForType(BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
      }
      
      public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OneOfMessageOrBuilder {
        private int bitField0_;
        
        private int type_;
        
        private MysqlxPrepare.Execute prepareExecute_;
        
        private SingleFieldBuilderV3<MysqlxPrepare.Execute, MysqlxPrepare.Execute.Builder, MysqlxPrepare.ExecuteOrBuilder> prepareExecuteBuilder_;
        
        public static final Descriptors.Descriptor getDescriptor() {
          return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor;
        }
        
        protected FieldAccessorTable internalGetFieldAccessorTable() {
          return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_OneOfMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(OneOfMessage.class, Builder.class);
        }
        
        private Builder() {
          this.type_ = 0;
          maybeForceBuilderInitialization();
        }
        
        private Builder(BuilderParent parent) {
          super(parent);
          this.type_ = 0;
          maybeForceBuilderInitialization();
        }
        
        private void maybeForceBuilderInitialization() {
          if (OneOfMessage.alwaysUseFieldBuilders)
            getPrepareExecuteFieldBuilder(); 
        }
        
        public Builder clear() {
          super.clear();
          this.type_ = 0;
          this.bitField0_ &= 0xFFFFFFFE;
          if (this.prepareExecuteBuilder_ == null) {
            this.prepareExecute_ = null;
          } else {
            this.prepareExecuteBuilder_.clear();
          } 
          this.bitField0_ &= 0xFFFFFFFD;
          return this;
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
          return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor;
        }
        
        public OneOfMessage getDefaultInstanceForType() {
          return OneOfMessage.getDefaultInstance();
        }
        
        public OneOfMessage build() {
          OneOfMessage result = buildPartial();
          if (!result.isInitialized())
            throw newUninitializedMessageException(result); 
          return result;
        }
        
        public OneOfMessage buildPartial() {
          OneOfMessage result = new OneOfMessage(this);
          int from_bitField0_ = this.bitField0_;
          int to_bitField0_ = 0;
          if ((from_bitField0_ & 0x1) != 0)
            to_bitField0_ |= 0x1; 
          result.type_ = this.type_;
          if ((from_bitField0_ & 0x2) != 0) {
            if (this.prepareExecuteBuilder_ == null) {
              result.prepareExecute_ = this.prepareExecute_;
            } else {
              result.prepareExecute_ = (MysqlxPrepare.Execute)this.prepareExecuteBuilder_.build();
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
          if (other instanceof OneOfMessage)
            return mergeFrom((OneOfMessage)other);
          super.mergeFrom(other);
          return this;
        }
        
        public Builder mergeFrom(OneOfMessage other) {
          if (other == OneOfMessage.getDefaultInstance())
            return this; 
          if (other.hasType())
            setType(other.getType()); 
          if (other.hasPrepareExecute())
            mergePrepareExecute(other.getPrepareExecute()); 
          mergeUnknownFields(other.unknownFields);
          onChanged();
          return this;
        }
        
        public final boolean isInitialized() {
          if (!hasType())
            return false; 
          if (hasPrepareExecute() && !getPrepareExecute().isInitialized())
            return false; 
          return true;
        }
        
        public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
          OneOfMessage parsedMessage = null;
          try {
            parsedMessage = (OneOfMessage) OneOfMessage.PARSER.parsePartialFrom(input, extensionRegistry);
          } catch (InvalidProtocolBufferException e) {
            parsedMessage = (OneOfMessage)e.getUnfinishedMessage();
            throw e.unwrapIOException();
          } finally {
            if (parsedMessage != null)
              mergeFrom(parsedMessage); 
          } 
          return this;
        }
        
        public boolean hasType() {
          return ((this.bitField0_ & 0x1) != 0);
        }
        
        public Type getType() {
          Type result = Type.valueOf(this.type_);
          return (result == null) ? Type.PREPARE_EXECUTE : result;
        }
        
        public Builder setType(Type value) {
          if (value == null)
            throw new NullPointerException(); 
          this.bitField0_ |= 0x1;
          this.type_ = value.getNumber();
          onChanged();
          return this;
        }
        
        public Builder clearType() {
          this.bitField0_ &= 0xFFFFFFFE;
          this.type_ = 0;
          onChanged();
          return this;
        }
        
        public boolean hasPrepareExecute() {
          return ((this.bitField0_ & 0x2) != 0);
        }
        
        public MysqlxPrepare.Execute getPrepareExecute() {
          if (this.prepareExecuteBuilder_ == null)
            return (this.prepareExecute_ == null) ? MysqlxPrepare.Execute.getDefaultInstance() : this.prepareExecute_; 
          return (MysqlxPrepare.Execute)this.prepareExecuteBuilder_.getMessage();
        }
        
        public Builder setPrepareExecute(MysqlxPrepare.Execute value) {
          if (this.prepareExecuteBuilder_ == null) {
            if (value == null)
              throw new NullPointerException(); 
            this.prepareExecute_ = value;
            onChanged();
          } else {
            this.prepareExecuteBuilder_.setMessage((AbstractMessage)value);
          } 
          this.bitField0_ |= 0x2;
          return this;
        }
        
        public Builder setPrepareExecute(MysqlxPrepare.Execute.Builder builderForValue) {
          if (this.prepareExecuteBuilder_ == null) {
            this.prepareExecute_ = builderForValue.build();
            onChanged();
          } else {
            this.prepareExecuteBuilder_.setMessage((AbstractMessage)builderForValue.build());
          } 
          this.bitField0_ |= 0x2;
          return this;
        }
        
        public Builder mergePrepareExecute(MysqlxPrepare.Execute value) {
          if (this.prepareExecuteBuilder_ == null) {
            if ((this.bitField0_ & 0x2) != 0 && this.prepareExecute_ != null && this.prepareExecute_ != MysqlxPrepare.Execute.getDefaultInstance()) {
              this.prepareExecute_ = MysqlxPrepare.Execute.newBuilder(this.prepareExecute_).mergeFrom(value).buildPartial();
            } else {
              this.prepareExecute_ = value;
            } 
            onChanged();
          } else {
            this.prepareExecuteBuilder_.mergeFrom((AbstractMessage)value);
          } 
          this.bitField0_ |= 0x2;
          return this;
        }
        
        public Builder clearPrepareExecute() {
          if (this.prepareExecuteBuilder_ == null) {
            this.prepareExecute_ = null;
            onChanged();
          } else {
            this.prepareExecuteBuilder_.clear();
          } 
          this.bitField0_ &= 0xFFFFFFFD;
          return this;
        }
        
        public MysqlxPrepare.Execute.Builder getPrepareExecuteBuilder() {
          this.bitField0_ |= 0x2;
          onChanged();
          return (MysqlxPrepare.Execute.Builder)getPrepareExecuteFieldBuilder().getBuilder();
        }
        
        public MysqlxPrepare.ExecuteOrBuilder getPrepareExecuteOrBuilder() {
          if (this.prepareExecuteBuilder_ != null)
            return (MysqlxPrepare.ExecuteOrBuilder)this.prepareExecuteBuilder_.getMessageOrBuilder(); 
          return (this.prepareExecute_ == null) ? MysqlxPrepare.Execute.getDefaultInstance() : this.prepareExecute_;
        }
        
        private SingleFieldBuilderV3<MysqlxPrepare.Execute, MysqlxPrepare.Execute.Builder, MysqlxPrepare.ExecuteOrBuilder> getPrepareExecuteFieldBuilder() {
          if (this.prepareExecuteBuilder_ == null) {
            this.prepareExecuteBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getPrepareExecute(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
            this.prepareExecute_ = null;
          } 
          return this.prepareExecuteBuilder_;
        }
        
        public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
          return (Builder)super.setUnknownFields(unknownFields);
        }
        
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
          return (Builder)super.mergeUnknownFields(unknownFields);
        }
      }
      
      private static final OneOfMessage DEFAULT_INSTANCE = new OneOfMessage();
      
      public static OneOfMessage getDefaultInstance() {
        return DEFAULT_INSTANCE;
      }
      
      @Deprecated
      public static final Parser<OneOfMessage> PARSER = (Parser<OneOfMessage>)new AbstractParser<OneOfMessage>() {
          public OneOfMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new OneOfMessage(input, extensionRegistry);
          }
        };
      
      public static Parser<OneOfMessage> parser() {
        return PARSER;
      }
      
      public Parser<OneOfMessage> getParserForType() {
        return PARSER;
      }
      
      public OneOfMessage getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
      }
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<OneOfMessage.Builder> implements OneOfMessageOrBuilder {
      private int bitField0_;
      
      private int type_;
      
      private MysqlxPrepare.Execute prepareExecute_;
      
      private SingleFieldBuilderV3<MysqlxPrepare.Execute, MysqlxPrepare.Execute.Builder, MysqlxPrepare.ExecuteOrBuilder> prepareExecuteBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_OneOfMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(OneOfMessage.class, Builder.class);
      }
      
      private Builder() {
        this.type_ = 0;
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.type_ = 0;
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (OneOfMessage.alwaysUseFieldBuilders)
          getPrepareExecuteFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        this.type_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        if (this.prepareExecuteBuilder_ == null) {
          this.prepareExecute_ = null;
        } else {
          this.prepareExecuteBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor;
      }
      
      public OneOfMessage getDefaultInstanceForType() {
        return OneOfMessage.getDefaultInstance();
      }
      
      public OneOfMessage build() {
        OneOfMessage result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public OneOfMessage buildPartial() {
        OneOfMessage result = new OneOfMessage(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.type_ = this.type_;
        if ((from_bitField0_ & 0x2) != 0) {
          if (this.prepareExecuteBuilder_ == null) {
            result.prepareExecute_ = this.prepareExecute_;
          } else {
            result.prepareExecute_ = (MysqlxPrepare.Execute)this.prepareExecuteBuilder_.build();
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
        if (other instanceof OneOfMessage)
          return mergeFrom((OneOfMessage)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(OneOfMessage other) {
        if (other == OneOfMessage.getDefaultInstance())
          return this; 
        if (other.hasType())
          setType(other.getType()); 
        if (other.hasPrepareExecute())
          mergePrepareExecute(other.getPrepareExecute()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasType())
          return false; 
        if (hasPrepareExecute() && !getPrepareExecute().isInitialized())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        OneOfMessage parsedMessage = null;
        try {
          parsedMessage = (OneOfMessage) OneOfMessage.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (OneOfMessage)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasType() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public OneOfMessage.Type getType() {
        OneOfMessage.Type result = OneOfMessage.Type.valueOf(this.type_);
        return (result == null) ? OneOfMessage.Type.PREPARE_EXECUTE : result;
      }
      
      public Builder setType(OneOfMessage.Type value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.type_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearType() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.type_ = 0;
        onChanged();
        return this;
      }
      
      public boolean hasPrepareExecute() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public MysqlxPrepare.Execute getPrepareExecute() {
        if (this.prepareExecuteBuilder_ == null)
          return (this.prepareExecute_ == null) ? MysqlxPrepare.Execute.getDefaultInstance() : this.prepareExecute_; 
        return (MysqlxPrepare.Execute)this.prepareExecuteBuilder_.getMessage();
      }
      
      public Builder setPrepareExecute(MysqlxPrepare.Execute value) {
        if (this.prepareExecuteBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.prepareExecute_ = value;
          onChanged();
        } else {
          this.prepareExecuteBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder setPrepareExecute(MysqlxPrepare.Execute.Builder builderForValue) {
        if (this.prepareExecuteBuilder_ == null) {
          this.prepareExecute_ = builderForValue.build();
          onChanged();
        } else {
          this.prepareExecuteBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder mergePrepareExecute(MysqlxPrepare.Execute value) {
        if (this.prepareExecuteBuilder_ == null) {
          if ((this.bitField0_ & 0x2) != 0 && this.prepareExecute_ != null && this.prepareExecute_ != MysqlxPrepare.Execute.getDefaultInstance()) {
            this.prepareExecute_ = MysqlxPrepare.Execute.newBuilder(this.prepareExecute_).mergeFrom(value).buildPartial();
          } else {
            this.prepareExecute_ = value;
          } 
          onChanged();
        } else {
          this.prepareExecuteBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder clearPrepareExecute() {
        if (this.prepareExecuteBuilder_ == null) {
          this.prepareExecute_ = null;
          onChanged();
        } else {
          this.prepareExecuteBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public MysqlxPrepare.Execute.Builder getPrepareExecuteBuilder() {
        this.bitField0_ |= 0x2;
        onChanged();
        return (MysqlxPrepare.Execute.Builder)getPrepareExecuteFieldBuilder().getBuilder();
      }
      
      public MysqlxPrepare.ExecuteOrBuilder getPrepareExecuteOrBuilder() {
        if (this.prepareExecuteBuilder_ != null)
          return (MysqlxPrepare.ExecuteOrBuilder)this.prepareExecuteBuilder_.getMessageOrBuilder(); 
        return (this.prepareExecute_ == null) ? MysqlxPrepare.Execute.getDefaultInstance() : this.prepareExecute_;
      }
      
      private SingleFieldBuilderV3<MysqlxPrepare.Execute, MysqlxPrepare.Execute.Builder, MysqlxPrepare.ExecuteOrBuilder> getPrepareExecuteFieldBuilder() {
        if (this.prepareExecuteBuilder_ == null) {
          this.prepareExecuteBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getPrepareExecute(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.prepareExecute_ = null;
        } 
        return this.prepareExecuteBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    public boolean hasCursorId() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public int getCursorId() {
      return this.cursorId_;
    }
    
    public boolean hasStmt() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public OneOfMessage getStmt() {
      return (this.stmt_ == null) ? OneOfMessage.getDefaultInstance() : this.stmt_;
    }
    
    public OneOfMessageOrBuilder getStmtOrBuilder() {
      return (this.stmt_ == null) ? OneOfMessage.getDefaultInstance() : this.stmt_;
    }
    
    public boolean hasFetchRows() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public long getFetchRows() {
      return this.fetchRows_;
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      if (!hasCursorId()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      if (!hasStmt()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      if (!getStmt().isInitialized()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        output.writeUInt32(1, this.cursorId_); 
      if ((this.bitField0_ & 0x2) != 0)
        output.writeMessage(4, (MessageLite)getStmt()); 
      if ((this.bitField0_ & 0x4) != 0)
        output.writeUInt64(5, this.fetchRows_); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeUInt32Size(1, this.cursorId_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeMessageSize(4, (MessageLite)getStmt()); 
      if ((this.bitField0_ & 0x4) != 0)
        size += 
          CodedOutputStream.computeUInt64Size(5, this.fetchRows_); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Open))
        return super.equals(obj); 
      Open other = (Open)obj;
      if (hasCursorId() != other.hasCursorId())
        return false; 
      if (hasCursorId() && 
        getCursorId() != other
        .getCursorId())
        return false; 
      if (hasStmt() != other.hasStmt())
        return false; 
      if (hasStmt() && 
        
        !getStmt().equals(other.getStmt()))
        return false; 
      if (hasFetchRows() != other.hasFetchRows())
        return false; 
      if (hasFetchRows() && 
        getFetchRows() != other
        .getFetchRows())
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
      if (hasCursorId()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getCursorId();
      } 
      if (hasStmt()) {
        hash = 37 * hash + 4;
        hash = 53 * hash + getStmt().hashCode();
      } 
      if (hasFetchRows()) {
        hash = 37 * hash + 5;
        hash = 53 * hash + Internal.hashLong(
            getFetchRows());
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Open parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Open)PARSER.parseFrom(data);
    }
    
    public static Open parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Open)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Open parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Open)PARSER.parseFrom(data);
    }
    
    public static Open parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Open)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Open parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Open)PARSER.parseFrom(data);
    }
    
    public static Open parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Open)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Open parseFrom(InputStream input) throws IOException {
      return 
        (Open)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Open parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Open)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Open parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Open)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Open parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Open)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Open parseFrom(CodedInputStream input) throws IOException {
      return 
        (Open)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Open parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Open)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Open prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OpenOrBuilder {
      private int bitField0_;
      
      private int cursorId_;
      
      private OneOfMessage stmt_;
      
      private SingleFieldBuilderV3<OneOfMessage, OneOfMessage.Builder, OneOfMessageOrBuilder> stmtBuilder_;
      
      private long fetchRows_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Open.class, Builder.class);
      }
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Open.alwaysUseFieldBuilders)
          getStmtFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        this.cursorId_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        if (this.stmtBuilder_ == null) {
          this.stmt_ = null;
        } else {
          this.stmtBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFD;
        this.fetchRows_ = 0L;
        this.bitField0_ &= 0xFFFFFFFB;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Open_descriptor;
      }
      
      public Open getDefaultInstanceForType() {
        return Open.getDefaultInstance();
      }
      
      public Open build() {
        Open result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Open buildPartial() {
        Open result = new Open(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0) {
          result.cursorId_ = this.cursorId_;
          to_bitField0_ |= 0x1;
        } 
        if ((from_bitField0_ & 0x2) != 0) {
          if (this.stmtBuilder_ == null) {
            result.stmt_ = this.stmt_;
          } else {
            result.stmt_ = (OneOfMessage)this.stmtBuilder_.build();
          } 
          to_bitField0_ |= 0x2;
        } 
        if ((from_bitField0_ & 0x4) != 0) {
          result.fetchRows_ = this.fetchRows_;
          to_bitField0_ |= 0x4;
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
        if (other instanceof Open)
          return mergeFrom((Open)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Open other) {
        if (other == Open.getDefaultInstance())
          return this; 
        if (other.hasCursorId())
          setCursorId(other.getCursorId()); 
        if (other.hasStmt())
          mergeStmt(other.getStmt()); 
        if (other.hasFetchRows())
          setFetchRows(other.getFetchRows()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasCursorId())
          return false; 
        if (!hasStmt())
          return false; 
        if (!getStmt().isInitialized())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Open parsedMessage = null;
        try {
          parsedMessage = (Open) Open.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Open)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasCursorId() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public int getCursorId() {
        return this.cursorId_;
      }
      
      public Builder setCursorId(int value) {
        this.bitField0_ |= 0x1;
        this.cursorId_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearCursorId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.cursorId_ = 0;
        onChanged();
        return this;
      }
      
      public boolean hasStmt() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public OneOfMessage getStmt() {
        if (this.stmtBuilder_ == null)
          return (this.stmt_ == null) ? OneOfMessage.getDefaultInstance() : this.stmt_;
        return (OneOfMessage)this.stmtBuilder_.getMessage();
      }
      
      public Builder setStmt(OneOfMessage value) {
        if (this.stmtBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.stmt_ = value;
          onChanged();
        } else {
          this.stmtBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder setStmt(OneOfMessage.Builder builderForValue) {
        if (this.stmtBuilder_ == null) {
          this.stmt_ = builderForValue.build();
          onChanged();
        } else {
          this.stmtBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder mergeStmt(OneOfMessage value) {
        if (this.stmtBuilder_ == null) {
          if ((this.bitField0_ & 0x2) != 0 && this.stmt_ != null && this.stmt_ != 
            
            OneOfMessage.getDefaultInstance()) {
            this
              .stmt_ = OneOfMessage.newBuilder(this.stmt_).mergeFrom(value).buildPartial();
          } else {
            this.stmt_ = value;
          } 
          onChanged();
        } else {
          this.stmtBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder clearStmt() {
        if (this.stmtBuilder_ == null) {
          this.stmt_ = null;
          onChanged();
        } else {
          this.stmtBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public OneOfMessage.Builder getStmtBuilder() {
        this.bitField0_ |= 0x2;
        onChanged();
        return (OneOfMessage.Builder)getStmtFieldBuilder().getBuilder();
      }
      
      public OneOfMessageOrBuilder getStmtOrBuilder() {
        if (this.stmtBuilder_ != null)
          return (OneOfMessageOrBuilder)this.stmtBuilder_.getMessageOrBuilder();
        return (this.stmt_ == null) ? 
          OneOfMessage.getDefaultInstance() : this.stmt_;
      }
      
      private SingleFieldBuilderV3<OneOfMessage, OneOfMessage.Builder, OneOfMessageOrBuilder> getStmtFieldBuilder() {
        if (this.stmtBuilder_ == null) {
          this
            
            .stmtBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getStmt(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.stmt_ = null;
        } 
        return this.stmtBuilder_;
      }
      
      public boolean hasFetchRows() {
        return ((this.bitField0_ & 0x4) != 0);
      }
      
      public long getFetchRows() {
        return this.fetchRows_;
      }
      
      public Builder setFetchRows(long value) {
        this.bitField0_ |= 0x4;
        this.fetchRows_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearFetchRows() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.fetchRows_ = 0L;
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
    
    private static final Open DEFAULT_INSTANCE = new Open();
    
    public static Open getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Open> PARSER = (Parser<Open>)new AbstractParser<Open>() {
        public Open parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Open(input, extensionRegistry);
        }
      };
    
    public static Parser<Open> parser() {
      return PARSER;
    }
    
    public Parser<Open> getParserForType() {
      return PARSER;
    }
    
    public Open getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public static interface OneOfMessageOrBuilder extends MessageOrBuilder {
      boolean hasType();
      
      OneOfMessage.Type getType();
      
      boolean hasPrepareExecute();
      
      MysqlxPrepare.Execute getPrepareExecute();
      
      MysqlxPrepare.ExecuteOrBuilder getPrepareExecuteOrBuilder();
    }
  }
  
  public static interface FetchOrBuilder extends MessageOrBuilder {
    boolean hasCursorId();
    
    int getCursorId();
    
    boolean hasFetchRows();
    
    long getFetchRows();
  }
  
  public static final class Fetch extends GeneratedMessageV3 implements FetchOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int CURSOR_ID_FIELD_NUMBER = 1;
    
    private int cursorId_;
    
    public static final int FETCH_ROWS_FIELD_NUMBER = 5;
    
    private long fetchRows_;
    
    private byte memoizedIsInitialized;
    
    private Fetch(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Fetch() {
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Fetch();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Fetch(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
              this.cursorId_ = input.readUInt32();
              continue;
            case 40:
              this.bitField0_ |= 0x2;
              this.fetchRows_ = input.readUInt64();
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
      return MysqlxCursor.internal_static_Mysqlx_Cursor_Fetch_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxCursor.internal_static_Mysqlx_Cursor_Fetch_fieldAccessorTable.ensureFieldAccessorsInitialized(Fetch.class, Builder.class);
    }
    
    public boolean hasCursorId() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public int getCursorId() {
      return this.cursorId_;
    }
    
    public boolean hasFetchRows() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public long getFetchRows() {
      return this.fetchRows_;
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      if (!hasCursorId()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        output.writeUInt32(1, this.cursorId_); 
      if ((this.bitField0_ & 0x2) != 0)
        output.writeUInt64(5, this.fetchRows_); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeUInt32Size(1, this.cursorId_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeUInt64Size(5, this.fetchRows_); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Fetch))
        return super.equals(obj); 
      Fetch other = (Fetch)obj;
      if (hasCursorId() != other.hasCursorId())
        return false; 
      if (hasCursorId() && 
        getCursorId() != other
        .getCursorId())
        return false; 
      if (hasFetchRows() != other.hasFetchRows())
        return false; 
      if (hasFetchRows() && 
        getFetchRows() != other
        .getFetchRows())
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
      if (hasCursorId()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getCursorId();
      } 
      if (hasFetchRows()) {
        hash = 37 * hash + 5;
        hash = 53 * hash + Internal.hashLong(
            getFetchRows());
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Fetch parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Fetch)PARSER.parseFrom(data);
    }
    
    public static Fetch parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Fetch)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Fetch parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Fetch)PARSER.parseFrom(data);
    }
    
    public static Fetch parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Fetch)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Fetch parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Fetch)PARSER.parseFrom(data);
    }
    
    public static Fetch parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Fetch)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Fetch parseFrom(InputStream input) throws IOException {
      return 
        (Fetch)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Fetch parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Fetch)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Fetch parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Fetch)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Fetch parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Fetch)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Fetch parseFrom(CodedInputStream input) throws IOException {
      return 
        (Fetch)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Fetch parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Fetch)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Fetch prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FetchOrBuilder {
      private int bitField0_;
      
      private int cursorId_;
      
      private long fetchRows_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Fetch_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Fetch_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Fetch.class, Builder.class);
      }
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Fetch.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        this.cursorId_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.fetchRows_ = 0L;
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Fetch_descriptor;
      }
      
      public Fetch getDefaultInstanceForType() {
        return Fetch.getDefaultInstance();
      }
      
      public Fetch build() {
        Fetch result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Fetch buildPartial() {
        Fetch result = new Fetch(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0) {
          result.cursorId_ = this.cursorId_;
          to_bitField0_ |= 0x1;
        } 
        if ((from_bitField0_ & 0x2) != 0) {
          result.fetchRows_ = this.fetchRows_;
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
        if (other instanceof Fetch)
          return mergeFrom((Fetch)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Fetch other) {
        if (other == Fetch.getDefaultInstance())
          return this; 
        if (other.hasCursorId())
          setCursorId(other.getCursorId()); 
        if (other.hasFetchRows())
          setFetchRows(other.getFetchRows()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasCursorId())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Fetch parsedMessage = null;
        try {
          parsedMessage = (Fetch) Fetch.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Fetch)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasCursorId() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public int getCursorId() {
        return this.cursorId_;
      }
      
      public Builder setCursorId(int value) {
        this.bitField0_ |= 0x1;
        this.cursorId_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearCursorId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.cursorId_ = 0;
        onChanged();
        return this;
      }
      
      public boolean hasFetchRows() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public long getFetchRows() {
        return this.fetchRows_;
      }
      
      public Builder setFetchRows(long value) {
        this.bitField0_ |= 0x2;
        this.fetchRows_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearFetchRows() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.fetchRows_ = 0L;
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
    
    private static final Fetch DEFAULT_INSTANCE = new Fetch();
    
    public static Fetch getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Fetch> PARSER = (Parser<Fetch>)new AbstractParser<Fetch>() {
        public Fetch parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Fetch(input, extensionRegistry);
        }
      };
    
    public static Parser<Fetch> parser() {
      return PARSER;
    }
    
    public Parser<Fetch> getParserForType() {
      return PARSER;
    }
    
    public Fetch getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface CloseOrBuilder extends MessageOrBuilder {
    boolean hasCursorId();
    
    int getCursorId();
  }
  
  public static final class Close extends GeneratedMessageV3 implements CloseOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int CURSOR_ID_FIELD_NUMBER = 1;
    
    private int cursorId_;
    
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
              this.cursorId_ = input.readUInt32();
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
      return MysqlxCursor.internal_static_Mysqlx_Cursor_Close_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxCursor.internal_static_Mysqlx_Cursor_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
    }
    
    public boolean hasCursorId() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public int getCursorId() {
      return this.cursorId_;
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      if (!hasCursorId()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        output.writeUInt32(1, this.cursorId_); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeUInt32Size(1, this.cursorId_); 
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
      if (hasCursorId() != other.hasCursorId())
        return false; 
      if (hasCursorId() && 
        getCursorId() != other
        .getCursorId())
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
      if (hasCursorId()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getCursorId();
      } 
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
      private int bitField0_;
      
      private int cursorId_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Close_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Close_fieldAccessorTable
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
        this.cursorId_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxCursor.internal_static_Mysqlx_Cursor_Close_descriptor;
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
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0) {
          result.cursorId_ = this.cursorId_;
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
        if (other instanceof Close)
          return mergeFrom((Close)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Close other) {
        if (other == Close.getDefaultInstance())
          return this; 
        if (other.hasCursorId())
          setCursorId(other.getCursorId()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasCursorId())
          return false; 
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
      
      public boolean hasCursorId() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public int getCursorId() {
        return this.cursorId_;
      }
      
      public Builder setCursorId(int value) {
        this.bitField0_ |= 0x1;
        this.cursorId_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearCursorId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.cursorId_ = 0;
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
    String[] descriptorData = { "\n\023mysqlx_cursor.proto\022\rMysqlx.Cursor\032\fmysqlx.proto\032\024mysqlx_prepare.proto\"ø\001\n\004Open\022\021\n\tcursor_id\030\001 \002(\r\022.\n\004stmt\030\004 \002(\0132 .Mysqlx.Cursor.Open.OneOfMessage\022\022\n\nfetch_rows\030\005 \001(\004\032\001\n\fOneOfMessage\0223\n\004type\030\001 \002(\0162%.Mysqlx.Cursor.Open.OneOfMessage.Type\0220\n\017prepare_execute\030\002 \001(\0132\027.Mysqlx.Prepare.Execute\"\033\n\004Type\022\023\n\017PREPARE_EXECUTE\020\000:\004ê0+\"4\n\005Fetch\022\021\n\tcursor_id\030\001 \002(\r\022\022\n\nfetch_rows\030\005 \001(\004:\004ê0-\" \n\005Close\022\021\n\tcursor_id\030\001 \002(\r:\004ê0,B\031\n\027com.mysql.cj.x.protobuf" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { Mysqlx.getDescriptor(), 
          MysqlxPrepare.getDescriptor() });
    internal_static_Mysqlx_Cursor_Open_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_Mysqlx_Cursor_Open_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Cursor_Open_descriptor, new String[] { "CursorId", "Stmt", "FetchRows" });
    internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor = internal_static_Mysqlx_Cursor_Open_descriptor.getNestedTypes().get(0);
    internal_static_Mysqlx_Cursor_Open_OneOfMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Cursor_Open_OneOfMessage_descriptor, new String[] { "Type", "PrepareExecute" });
    internal_static_Mysqlx_Cursor_Fetch_descriptor = getDescriptor().getMessageTypes().get(1);
    internal_static_Mysqlx_Cursor_Fetch_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Cursor_Fetch_descriptor, new String[] { "CursorId", "FetchRows" });
    internal_static_Mysqlx_Cursor_Close_descriptor = getDescriptor().getMessageTypes().get(2);
    internal_static_Mysqlx_Cursor_Close_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Cursor_Close_descriptor, new String[] { "CursorId" });
    ExtensionRegistry registry = ExtensionRegistry.newInstance();
    registry.add(Mysqlx.clientMessageId);
    Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
    Mysqlx.getDescriptor();
    MysqlxPrepare.getDescriptor();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\x\protobuf\MysqlxCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */