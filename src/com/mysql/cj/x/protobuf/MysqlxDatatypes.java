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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxDatatypes {
  private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Scalar_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Scalar_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Object_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Object_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Object_ObjectField_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Array_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Array_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Any_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Datatypes_Any_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static interface ScalarOrBuilder extends MessageOrBuilder {
    boolean hasType();
    
    Scalar.Type getType();
    
    boolean hasVSignedInt();
    
    long getVSignedInt();
    
    boolean hasVUnsignedInt();
    
    long getVUnsignedInt();
    
    boolean hasVOctets();
    
    Scalar.Octets getVOctets();
    
    Scalar.OctetsOrBuilder getVOctetsOrBuilder();
    
    boolean hasVDouble();
    
    double getVDouble();
    
    boolean hasVFloat();
    
    float getVFloat();
    
    boolean hasVBool();
    
    boolean getVBool();
    
    boolean hasVString();
    
    Scalar.String getVString();
    
    Scalar.StringOrBuilder getVStringOrBuilder();
  }
  
  public static final class Scalar extends GeneratedMessageV3 implements ScalarOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int TYPE_FIELD_NUMBER = 1;
    
    private int type_;
    
    public static final int V_SIGNED_INT_FIELD_NUMBER = 2;
    
    private long vSignedInt_;
    
    public static final int V_UNSIGNED_INT_FIELD_NUMBER = 3;
    
    private long vUnsignedInt_;
    
    public static final int V_OCTETS_FIELD_NUMBER = 5;
    
    private Octets vOctets_;
    
    public static final int V_DOUBLE_FIELD_NUMBER = 6;
    
    private double vDouble_;
    
    public static final int V_FLOAT_FIELD_NUMBER = 7;
    
    private float vFloat_;
    
    public static final int V_BOOL_FIELD_NUMBER = 8;
    
    private boolean vBool_;
    
    public static final int V_STRING_FIELD_NUMBER = 9;
    
    private String vString_;
    
    private byte memoizedIsInitialized;
    
    private Scalar(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Scalar() {
      this.memoizedIsInitialized = -1;
      this.type_ = 1;
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Scalar();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Scalar(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      int mutable_bitField0_ = 0;
      UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int rawValue;
          Octets.Builder builder;
          String.Builder subBuilder;
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
            case 16:
              this.bitField0_ |= 0x2;
              this.vSignedInt_ = input.readSInt64();
              continue;
            case 24:
              this.bitField0_ |= 0x4;
              this.vUnsignedInt_ = input.readUInt64();
              continue;
            case 42:
              builder = null;
              if ((this.bitField0_ & 0x8) != 0)
                builder = this.vOctets_.toBuilder(); 
              this.vOctets_ = (Octets)input.readMessage(Octets.PARSER, extensionRegistry);
              if (builder != null) {
                builder.mergeFrom(this.vOctets_);
                this.vOctets_ = builder.buildPartial();
              } 
              this.bitField0_ |= 0x8;
              continue;
            case 49:
              this.bitField0_ |= 0x10;
              this.vDouble_ = input.readDouble();
              continue;
            case 61:
              this.bitField0_ |= 0x20;
              this.vFloat_ = input.readFloat();
              continue;
            case 64:
              this.bitField0_ |= 0x40;
              this.vBool_ = input.readBool();
              continue;
            case 74:
              subBuilder = null;
              if ((this.bitField0_ & 0x80) != 0)
                subBuilder = this.vString_.toBuilder(); 
              this.vString_ = (String)input.readMessage(String.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(this.vString_);
                this.vString_ = subBuilder.buildPartial();
              } 
              this.bitField0_ |= 0x80;
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
      return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_fieldAccessorTable.ensureFieldAccessorsInitialized(Scalar.class, Builder.class);
    }
    
    public enum Type implements ProtocolMessageEnum {
      V_SINT(1),
      V_UINT(2),
      V_NULL(3),
      V_OCTETS(4),
      V_DOUBLE(5),
      V_FLOAT(6),
      V_BOOL(7),
      V_STRING(8);
      
      public static final int V_SINT_VALUE = 1;
      
      public static final int V_UINT_VALUE = 2;
      
      public static final int V_NULL_VALUE = 3;
      
      public static final int V_OCTETS_VALUE = 4;
      
      public static final int V_DOUBLE_VALUE = 5;
      
      public static final int V_FLOAT_VALUE = 6;
      
      public static final int V_BOOL_VALUE = 7;
      
      public static final int V_STRING_VALUE = 8;
      
      private static final Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() {
          public Type findValueByNumber(int number) {
            return Type.forNumber(number);
          }
        };
      
      private static final Type[] VALUES = values();
      
      private final int value;
      
      public final int getNumber() {
        return this.value;
      }
      
      public static Type forNumber(int value) {
        switch (value) {
          case 1:
            return V_SINT;
          case 2:
            return V_UINT;
          case 3:
            return V_NULL;
          case 4:
            return V_OCTETS;
          case 5:
            return V_DOUBLE;
          case 6:
            return V_FLOAT;
          case 7:
            return V_BOOL;
          case 8:
            return V_STRING;
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
        return Scalar.getDescriptor().getEnumTypes().get(0);
      }
      
      Type(int value) {
        this.value = value;
      }
    }
    
    public static final class String extends GeneratedMessageV3 implements StringOrBuilder {
      private static final long serialVersionUID = 0L;
      
      private int bitField0_;
      
      public static final int VALUE_FIELD_NUMBER = 1;
      
      private ByteString value_;
      
      public static final int COLLATION_FIELD_NUMBER = 2;
      
      private long collation_;
      
      private byte memoizedIsInitialized;
      
      private String(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
      }
      
      private String() {
        this.memoizedIsInitialized = -1;
        this.value_ = ByteString.EMPTY;
      }
      
      protected Object newInstance(UnusedPrivateParameter unused) {
        return new String();
      }
      
      public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
      }
      
      private String(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                this.value_ = input.readBytes();
                continue;
              case 16:
                this.bitField0_ |= 0x2;
                this.collation_ = input.readUInt64();
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
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable.ensureFieldAccessorsInitialized(String.class, Builder.class);
      }
      
      public boolean hasValue() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public ByteString getValue() {
        return this.value_;
      }
      
      public boolean hasCollation() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public long getCollation() {
        return this.collation_;
      }
      
      public final boolean isInitialized() {
        byte isInitialized = this.memoizedIsInitialized;
        if (isInitialized == 1)
          return true; 
        if (isInitialized == 0)
          return false; 
        if (!hasValue()) {
          this.memoizedIsInitialized = 0;
          return false;
        } 
        this.memoizedIsInitialized = 1;
        return true;
      }
      
      public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 0x1) != 0)
          output.writeBytes(1, this.value_); 
        if ((this.bitField0_ & 0x2) != 0)
          output.writeUInt64(2, this.collation_); 
        this.unknownFields.writeTo(output);
      }
      
      public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1)
          return size; 
        size = 0;
        if ((this.bitField0_ & 0x1) != 0)
          size += CodedOutputStream.computeBytesSize(1, this.value_); 
        if ((this.bitField0_ & 0x2) != 0)
          size += CodedOutputStream.computeUInt64Size(2, this.collation_); 
        size += this.unknownFields.getSerializedSize();
        this.memoizedSize = size;
        return size;
      }
      
      public boolean equals(Object obj) {
        if (obj == this)
          return true; 
        if (!(obj instanceof String))
          return super.equals(obj); 
        String other = (String)obj;
        if (hasValue() != other.hasValue())
          return false; 
        if (hasValue() && !getValue().equals(other.getValue()))
          return false; 
        if (hasCollation() != other.hasCollation())
          return false; 
        if (hasCollation() && getCollation() != other.getCollation())
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
        if (hasValue()) {
          hash = 37 * hash + 1;
          hash = 53 * hash + getValue().hashCode();
        } 
        if (hasCollation()) {
          hash = 37 * hash + 2;
          hash = 53 * hash + Internal.hashLong(getCollation());
        } 
        hash = 29 * hash + this.unknownFields.hashCode();
        this.memoizedHashCode = hash;
        return hash;
      }
      
      public static String parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (String)PARSER.parseFrom(data);
      }
      
      public static String parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (String)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static String parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (String)PARSER.parseFrom(data);
      }
      
      public static String parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (String)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static String parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (String)PARSER.parseFrom(data);
      }
      
      public static String parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (String)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static String parseFrom(InputStream input) throws IOException {
        return (String)GeneratedMessageV3.parseWithIOException(PARSER, input);
      }
      
      public static String parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (String)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
      }
      
      public static String parseDelimitedFrom(InputStream input) throws IOException {
        return (String)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
      }
      
      public static String parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (String)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }
      
      public static String parseFrom(CodedInputStream input) throws IOException {
        return (String)GeneratedMessageV3.parseWithIOException(PARSER, input);
      }
      
      public static String parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (String)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
      }
      
      public Builder newBuilderForType() {
        return newBuilder();
      }
      
      public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
      }
      
      public static Builder newBuilder(String prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }
      
      public Builder toBuilder() {
        return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder()).mergeFrom(this);
      }
      
      protected Builder newBuilderForType(BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
      }
      
      public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StringOrBuilder {
        private int bitField0_;
        
        private ByteString value_;
        
        private long collation_;
        
        public static final Descriptors.Descriptor getDescriptor() {
          return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
        }
        
        protected FieldAccessorTable internalGetFieldAccessorTable() {
          return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable.ensureFieldAccessorsInitialized(String.class, Builder.class);
        }
        
        private Builder() {
          this.value_ = ByteString.EMPTY;
          maybeForceBuilderInitialization();
        }
        
        private Builder(BuilderParent parent) {
          super(parent);
          this.value_ = ByteString.EMPTY;
          maybeForceBuilderInitialization();
        }
        
        private void maybeForceBuilderInitialization() {
          if (String.alwaysUseFieldBuilders);
        }
        
        public Builder clear() {
          super.clear();
          this.value_ = ByteString.EMPTY;
          this.bitField0_ &= 0xFFFFFFFE;
          this.collation_ = 0L;
          this.bitField0_ &= 0xFFFFFFFD;
          return this;
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
          return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
        }
        
        public String getDefaultInstanceForType() {
          return String.getDefaultInstance();
        }
        
        public String build() {
          String result = buildPartial();
          if (!result.isInitialized())
            throw newUninitializedMessageException(result); 
          return result;
        }
        
        public String buildPartial() {
          String result = new String(this);
          int from_bitField0_ = this.bitField0_;
          int to_bitField0_ = 0;
          if ((from_bitField0_ & 0x1) != 0)
            to_bitField0_ |= 0x1; 
          result.value_ = this.value_;
          if ((from_bitField0_ & 0x2) != 0) {
            result.collation_ = this.collation_;
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
          if (other instanceof String)
            return mergeFrom((String)other);
          super.mergeFrom(other);
          return this;
        }
        
        public Builder mergeFrom(String other) {
          if (other == String.getDefaultInstance())
            return this; 
          if (other.hasValue())
            setValue(other.getValue()); 
          if (other.hasCollation())
            setCollation(other.getCollation()); 
          mergeUnknownFields(other.unknownFields);
          onChanged();
          return this;
        }
        
        public final boolean isInitialized() {
          if (!hasValue())
            return false; 
          return true;
        }
        
        public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
          String parsedMessage = null;
          try {
            parsedMessage = (String) String.PARSER.parsePartialFrom(input, extensionRegistry);
          } catch (InvalidProtocolBufferException e) {
            parsedMessage = (String)e.getUnfinishedMessage();
            throw e.unwrapIOException();
          } finally {
            if (parsedMessage != null)
              mergeFrom(parsedMessage); 
          } 
          return this;
        }
        
        public boolean hasValue() {
          return ((this.bitField0_ & 0x1) != 0);
        }
        
        public ByteString getValue() {
          return this.value_;
        }
        
        public Builder setValue(ByteString value) {
          if (value == null)
            throw new NullPointerException(); 
          this.bitField0_ |= 0x1;
          this.value_ = value;
          onChanged();
          return this;
        }
        
        public Builder clearValue() {
          this.bitField0_ &= 0xFFFFFFFE;
          this.value_ = String.getDefaultInstance().getValue();
          onChanged();
          return this;
        }
        
        public boolean hasCollation() {
          return ((this.bitField0_ & 0x2) != 0);
        }
        
        public long getCollation() {
          return this.collation_;
        }
        
        public Builder setCollation(long value) {
          this.bitField0_ |= 0x2;
          this.collation_ = value;
          onChanged();
          return this;
        }
        
        public Builder clearCollation() {
          this.bitField0_ &= 0xFFFFFFFD;
          this.collation_ = 0L;
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
      
      private static final String DEFAULT_INSTANCE = new String();
      
      public static String getDefaultInstance() {
        return DEFAULT_INSTANCE;
      }
      
      @Deprecated
      public static final Parser<String> PARSER = (Parser<String>)new AbstractParser<String>() {
          public String parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new String(input, extensionRegistry);
          }
        };
      
      public static Parser<String> parser() {
        return PARSER;
      }
      
      public Parser<String> getParserForType() {
        return PARSER;
      }
      
      public String getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
      }
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<String.Builder> implements StringOrBuilder {
      private int bitField0_;
      
      private ByteString value_;
      
      private long collation_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable.ensureFieldAccessorsInitialized(String.class, Builder.class);
      }
      
      private Builder() {
        this.value_ = ByteString.EMPTY;
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.value_ = ByteString.EMPTY;
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (String.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        this.value_ = ByteString.EMPTY;
        this.bitField0_ &= 0xFFFFFFFE;
        this.collation_ = 0L;
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
      }
      
      public String getDefaultInstanceForType() {
        return String.getDefaultInstance();
      }
      
      public String build() {
        String result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public String buildPartial() {
        String result = new String(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.value_ = this.value_;
        if ((from_bitField0_ & 0x2) != 0) {
          result.collation_ = this.collation_;
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
        if (other instanceof String)
          return mergeFrom((String)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(String other) {
        if (other == String.getDefaultInstance())
          return this; 
        if (other.hasValue())
          setValue(other.getValue()); 
        if (other.hasCollation())
          setCollation(other.getCollation()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasValue())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        String parsedMessage = null;
        try {
          parsedMessage = (String) String.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (String)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasValue() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public ByteString getValue() {
        return this.value_;
      }
      
      public Builder setValue(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.value_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearValue() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.value_ = String.getDefaultInstance().getValue();
        onChanged();
        return this;
      }
      
      public boolean hasCollation() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public long getCollation() {
        return this.collation_;
      }
      
      public Builder setCollation(long value) {
        this.bitField0_ |= 0x2;
        this.collation_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearCollation() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.collation_ = 0L;
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
    
    public static final class Octets extends GeneratedMessageV3 implements OctetsOrBuilder {
      private static final long serialVersionUID = 0L;
      
      private int bitField0_;
      
      public static final int VALUE_FIELD_NUMBER = 1;
      
      private ByteString value_;
      
      public static final int CONTENT_TYPE_FIELD_NUMBER = 2;
      
      private int contentType_;
      
      private byte memoizedIsInitialized;
      
      private Octets(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
      }
      
      private Octets() {
        this.memoizedIsInitialized = -1;
        this.value_ = ByteString.EMPTY;
      }
      
      protected Object newInstance(UnusedPrivateParameter unused) {
        return new Octets();
      }
      
      public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
      }
      
      private Octets(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                this.value_ = input.readBytes();
                continue;
              case 16:
                this.bitField0_ |= 0x2;
                this.contentType_ = input.readUInt32();
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
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable.ensureFieldAccessorsInitialized(Octets.class, Builder.class);
      }
      
      public boolean hasValue() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public ByteString getValue() {
        return this.value_;
      }
      
      public boolean hasContentType() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public int getContentType() {
        return this.contentType_;
      }
      
      public final boolean isInitialized() {
        byte isInitialized = this.memoizedIsInitialized;
        if (isInitialized == 1)
          return true; 
        if (isInitialized == 0)
          return false; 
        if (!hasValue()) {
          this.memoizedIsInitialized = 0;
          return false;
        } 
        this.memoizedIsInitialized = 1;
        return true;
      }
      
      public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 0x1) != 0)
          output.writeBytes(1, this.value_); 
        if ((this.bitField0_ & 0x2) != 0)
          output.writeUInt32(2, this.contentType_); 
        this.unknownFields.writeTo(output);
      }
      
      public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1)
          return size; 
        size = 0;
        if ((this.bitField0_ & 0x1) != 0)
          size += CodedOutputStream.computeBytesSize(1, this.value_); 
        if ((this.bitField0_ & 0x2) != 0)
          size += CodedOutputStream.computeUInt32Size(2, this.contentType_); 
        size += this.unknownFields.getSerializedSize();
        this.memoizedSize = size;
        return size;
      }
      
      public boolean equals(Object obj) {
        if (obj == this)
          return true; 
        if (!(obj instanceof Octets))
          return super.equals(obj); 
        Octets other = (Octets)obj;
        if (hasValue() != other.hasValue())
          return false; 
        if (hasValue() && !getValue().equals(other.getValue()))
          return false; 
        if (hasContentType() != other.hasContentType())
          return false; 
        if (hasContentType() && getContentType() != other.getContentType())
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
        if (hasValue()) {
          hash = 37 * hash + 1;
          hash = 53 * hash + getValue().hashCode();
        } 
        if (hasContentType()) {
          hash = 37 * hash + 2;
          hash = 53 * hash + getContentType();
        } 
        hash = 29 * hash + this.unknownFields.hashCode();
        this.memoizedHashCode = hash;
        return hash;
      }
      
      public static Octets parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Octets)PARSER.parseFrom(data);
      }
      
      public static Octets parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Octets)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static Octets parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Octets)PARSER.parseFrom(data);
      }
      
      public static Octets parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Octets)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static Octets parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Octets)PARSER.parseFrom(data);
      }
      
      public static Octets parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Octets)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static Octets parseFrom(InputStream input) throws IOException {
        return (Octets)GeneratedMessageV3.parseWithIOException(PARSER, input);
      }
      
      public static Octets parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Octets)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
      }
      
      public static Octets parseDelimitedFrom(InputStream input) throws IOException {
        return (Octets)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
      }
      
      public static Octets parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Octets)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }
      
      public static Octets parseFrom(CodedInputStream input) throws IOException {
        return (Octets)GeneratedMessageV3.parseWithIOException(PARSER, input);
      }
      
      public static Octets parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Octets)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
      }
      
      public Builder newBuilderForType() {
        return newBuilder();
      }
      
      public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
      }
      
      public static Builder newBuilder(Octets prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }
      
      public Builder toBuilder() {
        return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder()).mergeFrom(this);
      }
      
      protected Builder newBuilderForType(BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
      }
      
      public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OctetsOrBuilder {
        private int bitField0_;
        
        private ByteString value_;
        
        private int contentType_;
        
        public static final Descriptors.Descriptor getDescriptor() {
          return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
        }
        
        protected FieldAccessorTable internalGetFieldAccessorTable() {
          return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable.ensureFieldAccessorsInitialized(Octets.class, Builder.class);
        }
        
        private Builder() {
          this.value_ = ByteString.EMPTY;
          maybeForceBuilderInitialization();
        }
        
        private Builder(BuilderParent parent) {
          super(parent);
          this.value_ = ByteString.EMPTY;
          maybeForceBuilderInitialization();
        }
        
        private void maybeForceBuilderInitialization() {
          if (Octets.alwaysUseFieldBuilders);
        }
        
        public Builder clear() {
          super.clear();
          this.value_ = ByteString.EMPTY;
          this.bitField0_ &= 0xFFFFFFFE;
          this.contentType_ = 0;
          this.bitField0_ &= 0xFFFFFFFD;
          return this;
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
          return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
        }
        
        public Octets getDefaultInstanceForType() {
          return Octets.getDefaultInstance();
        }
        
        public Octets build() {
          Octets result = buildPartial();
          if (!result.isInitialized())
            throw newUninitializedMessageException(result); 
          return result;
        }
        
        public Octets buildPartial() {
          Octets result = new Octets(this);
          int from_bitField0_ = this.bitField0_;
          int to_bitField0_ = 0;
          if ((from_bitField0_ & 0x1) != 0)
            to_bitField0_ |= 0x1; 
          result.value_ = this.value_;
          if ((from_bitField0_ & 0x2) != 0) {
            result.contentType_ = this.contentType_;
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
          if (other instanceof Octets)
            return mergeFrom((Octets)other);
          super.mergeFrom(other);
          return this;
        }
        
        public Builder mergeFrom(Octets other) {
          if (other == Octets.getDefaultInstance())
            return this; 
          if (other.hasValue())
            setValue(other.getValue()); 
          if (other.hasContentType())
            setContentType(other.getContentType()); 
          mergeUnknownFields(other.unknownFields);
          onChanged();
          return this;
        }
        
        public final boolean isInitialized() {
          if (!hasValue())
            return false; 
          return true;
        }
        
        public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
          Octets parsedMessage = null;
          try {
            parsedMessage = (Octets) Octets.PARSER.parsePartialFrom(input, extensionRegistry);
          } catch (InvalidProtocolBufferException e) {
            parsedMessage = (Octets)e.getUnfinishedMessage();
            throw e.unwrapIOException();
          } finally {
            if (parsedMessage != null)
              mergeFrom(parsedMessage); 
          } 
          return this;
        }
        
        public boolean hasValue() {
          return ((this.bitField0_ & 0x1) != 0);
        }
        
        public ByteString getValue() {
          return this.value_;
        }
        
        public Builder setValue(ByteString value) {
          if (value == null)
            throw new NullPointerException(); 
          this.bitField0_ |= 0x1;
          this.value_ = value;
          onChanged();
          return this;
        }
        
        public Builder clearValue() {
          this.bitField0_ &= 0xFFFFFFFE;
          this.value_ = Octets.getDefaultInstance().getValue();
          onChanged();
          return this;
        }
        
        public boolean hasContentType() {
          return ((this.bitField0_ & 0x2) != 0);
        }
        
        public int getContentType() {
          return this.contentType_;
        }
        
        public Builder setContentType(int value) {
          this.bitField0_ |= 0x2;
          this.contentType_ = value;
          onChanged();
          return this;
        }
        
        public Builder clearContentType() {
          this.bitField0_ &= 0xFFFFFFFD;
          this.contentType_ = 0;
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
      
      private static final Octets DEFAULT_INSTANCE = new Octets();
      
      public static Octets getDefaultInstance() {
        return DEFAULT_INSTANCE;
      }
      
      @Deprecated
      public static final Parser<Octets> PARSER = (Parser<Octets>)new AbstractParser<Octets>() {
          public Octets parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Octets(input, extensionRegistry);
          }
        };
      
      public static Parser<Octets> parser() {
        return PARSER;
      }
      
      public Parser<Octets> getParserForType() {
        return PARSER;
      }
      
      public Octets getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
      }
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Octets.Builder> implements OctetsOrBuilder {
      private int bitField0_;
      
      private ByteString value_;
      
      private int contentType_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable.ensureFieldAccessorsInitialized(Octets.class, Builder.class);
      }
      
      private Builder() {
        this.value_ = ByteString.EMPTY;
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.value_ = ByteString.EMPTY;
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Octets.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        this.value_ = ByteString.EMPTY;
        this.bitField0_ &= 0xFFFFFFFE;
        this.contentType_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
      }
      
      public Octets getDefaultInstanceForType() {
        return Octets.getDefaultInstance();
      }
      
      public Octets build() {
        Octets result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Octets buildPartial() {
        Octets result = new Octets(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.value_ = this.value_;
        if ((from_bitField0_ & 0x2) != 0) {
          result.contentType_ = this.contentType_;
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
        if (other instanceof Octets)
          return mergeFrom((Octets)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Octets other) {
        if (other == Octets.getDefaultInstance())
          return this; 
        if (other.hasValue())
          setValue(other.getValue()); 
        if (other.hasContentType())
          setContentType(other.getContentType()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasValue())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Octets parsedMessage = null;
        try {
          parsedMessage = (Octets) Octets.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Octets)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasValue() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public ByteString getValue() {
        return this.value_;
      }
      
      public Builder setValue(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.value_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearValue() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.value_ = Octets.getDefaultInstance().getValue();
        onChanged();
        return this;
      }
      
      public boolean hasContentType() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public int getContentType() {
        return this.contentType_;
      }
      
      public Builder setContentType(int value) {
        this.bitField0_ |= 0x2;
        this.contentType_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearContentType() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.contentType_ = 0;
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
    
    public boolean hasType() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public Type getType() {
      Type result = Type.valueOf(this.type_);
      return (result == null) ? Type.V_SINT : result;
    }
    
    public boolean hasVSignedInt() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public long getVSignedInt() {
      return this.vSignedInt_;
    }
    
    public boolean hasVUnsignedInt() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public long getVUnsignedInt() {
      return this.vUnsignedInt_;
    }
    
    public boolean hasVOctets() {
      return ((this.bitField0_ & 0x8) != 0);
    }
    
    public Octets getVOctets() {
      return (this.vOctets_ == null) ? Octets.getDefaultInstance() : this.vOctets_;
    }
    
    public OctetsOrBuilder getVOctetsOrBuilder() {
      return (this.vOctets_ == null) ? Octets.getDefaultInstance() : this.vOctets_;
    }
    
    public boolean hasVDouble() {
      return ((this.bitField0_ & 0x10) != 0);
    }
    
    public double getVDouble() {
      return this.vDouble_;
    }
    
    public boolean hasVFloat() {
      return ((this.bitField0_ & 0x20) != 0);
    }
    
    public float getVFloat() {
      return this.vFloat_;
    }
    
    public boolean hasVBool() {
      return ((this.bitField0_ & 0x40) != 0);
    }
    
    public boolean getVBool() {
      return this.vBool_;
    }
    
    public boolean hasVString() {
      return ((this.bitField0_ & 0x80) != 0);
    }
    
    public String getVString() {
      return (this.vString_ == null) ? String.getDefaultInstance() : this.vString_;
    }
    
    public StringOrBuilder getVStringOrBuilder() {
      return (this.vString_ == null) ? String.getDefaultInstance() : this.vString_;
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
      if (hasVOctets() && 
        !getVOctets().isInitialized()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      if (hasVString() && 
        !getVString().isInitialized()) {
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
        output.writeSInt64(2, this.vSignedInt_); 
      if ((this.bitField0_ & 0x4) != 0)
        output.writeUInt64(3, this.vUnsignedInt_); 
      if ((this.bitField0_ & 0x8) != 0)
        output.writeMessage(5, (MessageLite)getVOctets()); 
      if ((this.bitField0_ & 0x10) != 0)
        output.writeDouble(6, this.vDouble_); 
      if ((this.bitField0_ & 0x20) != 0)
        output.writeFloat(7, this.vFloat_); 
      if ((this.bitField0_ & 0x40) != 0)
        output.writeBool(8, this.vBool_); 
      if ((this.bitField0_ & 0x80) != 0)
        output.writeMessage(9, (MessageLite)getVString()); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeEnumSize(1, this.type_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeSInt64Size(2, this.vSignedInt_); 
      if ((this.bitField0_ & 0x4) != 0)
        size += 
          CodedOutputStream.computeUInt64Size(3, this.vUnsignedInt_); 
      if ((this.bitField0_ & 0x8) != 0)
        size += 
          CodedOutputStream.computeMessageSize(5, (MessageLite)getVOctets()); 
      if ((this.bitField0_ & 0x10) != 0)
        size += 
          CodedOutputStream.computeDoubleSize(6, this.vDouble_); 
      if ((this.bitField0_ & 0x20) != 0)
        size += 
          CodedOutputStream.computeFloatSize(7, this.vFloat_); 
      if ((this.bitField0_ & 0x40) != 0)
        size += 
          CodedOutputStream.computeBoolSize(8, this.vBool_); 
      if ((this.bitField0_ & 0x80) != 0)
        size += 
          CodedOutputStream.computeMessageSize(9, (MessageLite)getVString()); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Scalar))
        return super.equals(obj); 
      Scalar other = (Scalar)obj;
      if (hasType() != other.hasType())
        return false; 
      if (hasType() && 
        this.type_ != other.type_)
        return false; 
      if (hasVSignedInt() != other.hasVSignedInt())
        return false; 
      if (hasVSignedInt() && 
        getVSignedInt() != other
        .getVSignedInt())
        return false; 
      if (hasVUnsignedInt() != other.hasVUnsignedInt())
        return false; 
      if (hasVUnsignedInt() && 
        getVUnsignedInt() != other
        .getVUnsignedInt())
        return false; 
      if (hasVOctets() != other.hasVOctets())
        return false; 
      if (hasVOctets() && 
        
        !getVOctets().equals(other.getVOctets()))
        return false; 
      if (hasVDouble() != other.hasVDouble())
        return false; 
      if (hasVDouble() && 
        Double.doubleToLongBits(getVDouble()) != 
        Double.doubleToLongBits(other
          .getVDouble()))
        return false; 
      if (hasVFloat() != other.hasVFloat())
        return false; 
      if (hasVFloat() && 
        Float.floatToIntBits(getVFloat()) != 
        Float.floatToIntBits(other
          .getVFloat()))
        return false; 
      if (hasVBool() != other.hasVBool())
        return false; 
      if (hasVBool() && 
        getVBool() != other
        .getVBool())
        return false; 
      if (hasVString() != other.hasVString())
        return false; 
      if (hasVString() && 
        
        !getVString().equals(other.getVString()))
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
      if (hasVSignedInt()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + Internal.hashLong(
            getVSignedInt());
      } 
      if (hasVUnsignedInt()) {
        hash = 37 * hash + 3;
        hash = 53 * hash + Internal.hashLong(
            getVUnsignedInt());
      } 
      if (hasVOctets()) {
        hash = 37 * hash + 5;
        hash = 53 * hash + getVOctets().hashCode();
      } 
      if (hasVDouble()) {
        hash = 37 * hash + 6;
        hash = 53 * hash + Internal.hashLong(
            Double.doubleToLongBits(getVDouble()));
      } 
      if (hasVFloat()) {
        hash = 37 * hash + 7;
        hash = 53 * hash + Float.floatToIntBits(
            getVFloat());
      } 
      if (hasVBool()) {
        hash = 37 * hash + 8;
        hash = 53 * hash + Internal.hashBoolean(
            getVBool());
      } 
      if (hasVString()) {
        hash = 37 * hash + 9;
        hash = 53 * hash + getVString().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Scalar parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Scalar)PARSER.parseFrom(data);
    }
    
    public static Scalar parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Scalar)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Scalar parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Scalar)PARSER.parseFrom(data);
    }
    
    public static Scalar parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Scalar)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Scalar parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Scalar)PARSER.parseFrom(data);
    }
    
    public static Scalar parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Scalar)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Scalar parseFrom(InputStream input) throws IOException {
      return 
        (Scalar)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Scalar parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Scalar)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Scalar parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Scalar)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Scalar parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Scalar)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Scalar parseFrom(CodedInputStream input) throws IOException {
      return 
        (Scalar)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Scalar parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Scalar)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Scalar prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ScalarOrBuilder {
      private int bitField0_;
      
      private int type_;
      
      private long vSignedInt_;
      
      private long vUnsignedInt_;
      
      private Octets vOctets_;
      
      private SingleFieldBuilderV3<Octets, Octets.Builder, OctetsOrBuilder> vOctetsBuilder_;
      
      private double vDouble_;
      
      private float vFloat_;
      
      private boolean vBool_;
      
      private String vString_;
      
      private SingleFieldBuilderV3<String, String.Builder, StringOrBuilder> vStringBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Scalar.class, Builder.class);
      }
      
      private Builder() {
        this.type_ = 1;
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.type_ = 1;
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Scalar.alwaysUseFieldBuilders) {
          getVOctetsFieldBuilder();
          getVStringFieldBuilder();
        } 
      }
      
      public Builder clear() {
        super.clear();
        this.type_ = 1;
        this.bitField0_ &= 0xFFFFFFFE;
        this.vSignedInt_ = 0L;
        this.bitField0_ &= 0xFFFFFFFD;
        this.vUnsignedInt_ = 0L;
        this.bitField0_ &= 0xFFFFFFFB;
        if (this.vOctetsBuilder_ == null) {
          this.vOctets_ = null;
        } else {
          this.vOctetsBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFF7;
        this.vDouble_ = 0.0D;
        this.bitField0_ &= 0xFFFFFFEF;
        this.vFloat_ = 0.0F;
        this.bitField0_ &= 0xFFFFFFDF;
        this.vBool_ = false;
        this.bitField0_ &= 0xFFFFFFBF;
        if (this.vStringBuilder_ == null) {
          this.vString_ = null;
        } else {
          this.vStringBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFF7F;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Scalar_descriptor;
      }
      
      public Scalar getDefaultInstanceForType() {
        return Scalar.getDefaultInstance();
      }
      
      public Scalar build() {
        Scalar result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Scalar buildPartial() {
        Scalar result = new Scalar(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.type_ = this.type_;
        if ((from_bitField0_ & 0x2) != 0) {
          result.vSignedInt_ = this.vSignedInt_;
          to_bitField0_ |= 0x2;
        } 
        if ((from_bitField0_ & 0x4) != 0) {
          result.vUnsignedInt_ = this.vUnsignedInt_;
          to_bitField0_ |= 0x4;
        } 
        if ((from_bitField0_ & 0x8) != 0) {
          if (this.vOctetsBuilder_ == null) {
            result.vOctets_ = this.vOctets_;
          } else {
            result.vOctets_ = (Octets)this.vOctetsBuilder_.build();
          } 
          to_bitField0_ |= 0x8;
        } 
        if ((from_bitField0_ & 0x10) != 0) {
          result.vDouble_ = this.vDouble_;
          to_bitField0_ |= 0x10;
        } 
        if ((from_bitField0_ & 0x20) != 0) {
          result.vFloat_ = this.vFloat_;
          to_bitField0_ |= 0x20;
        } 
        if ((from_bitField0_ & 0x40) != 0) {
          result.vBool_ = this.vBool_;
          to_bitField0_ |= 0x40;
        } 
        if ((from_bitField0_ & 0x80) != 0) {
          if (this.vStringBuilder_ == null) {
            result.vString_ = this.vString_;
          } else {
            result.vString_ = (String)this.vStringBuilder_.build();
          } 
          to_bitField0_ |= 0x80;
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
        if (other instanceof Scalar)
          return mergeFrom((Scalar)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Scalar other) {
        if (other == Scalar.getDefaultInstance())
          return this; 
        if (other.hasType())
          setType(other.getType()); 
        if (other.hasVSignedInt())
          setVSignedInt(other.getVSignedInt()); 
        if (other.hasVUnsignedInt())
          setVUnsignedInt(other.getVUnsignedInt()); 
        if (other.hasVOctets())
          mergeVOctets(other.getVOctets()); 
        if (other.hasVDouble())
          setVDouble(other.getVDouble()); 
        if (other.hasVFloat())
          setVFloat(other.getVFloat()); 
        if (other.hasVBool())
          setVBool(other.getVBool()); 
        if (other.hasVString())
          mergeVString(other.getVString()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasType())
          return false; 
        if (hasVOctets() && !getVOctets().isInitialized())
          return false; 
        if (hasVString() && !getVString().isInitialized())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Scalar parsedMessage = null;
        try {
          parsedMessage = (Scalar) Scalar.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Scalar)e.getUnfinishedMessage();
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
        return (result == null) ? Type.V_SINT : result;
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
        this.type_ = 1;
        onChanged();
        return this;
      }
      
      public boolean hasVSignedInt() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public long getVSignedInt() {
        return this.vSignedInt_;
      }
      
      public Builder setVSignedInt(long value) {
        this.bitField0_ |= 0x2;
        this.vSignedInt_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearVSignedInt() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.vSignedInt_ = 0L;
        onChanged();
        return this;
      }
      
      public boolean hasVUnsignedInt() {
        return ((this.bitField0_ & 0x4) != 0);
      }
      
      public long getVUnsignedInt() {
        return this.vUnsignedInt_;
      }
      
      public Builder setVUnsignedInt(long value) {
        this.bitField0_ |= 0x4;
        this.vUnsignedInt_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearVUnsignedInt() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.vUnsignedInt_ = 0L;
        onChanged();
        return this;
      }
      
      public boolean hasVOctets() {
        return ((this.bitField0_ & 0x8) != 0);
      }
      
      public Octets getVOctets() {
        if (this.vOctetsBuilder_ == null)
          return (this.vOctets_ == null) ? Octets.getDefaultInstance() : this.vOctets_;
        return (Octets)this.vOctetsBuilder_.getMessage();
      }
      
      public Builder setVOctets(Octets value) {
        if (this.vOctetsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.vOctets_ = value;
          onChanged();
        } else {
          this.vOctetsBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x8;
        return this;
      }
      
      public Builder setVOctets(Octets.Builder builderForValue) {
        if (this.vOctetsBuilder_ == null) {
          this.vOctets_ = builderForValue.build();
          onChanged();
        } else {
          this.vOctetsBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x8;
        return this;
      }
      
      public Builder mergeVOctets(Octets value) {
        if (this.vOctetsBuilder_ == null) {
          if ((this.bitField0_ & 0x8) != 0 && this.vOctets_ != null && this.vOctets_ != 
            
            Octets.getDefaultInstance()) {
            this
              .vOctets_ = Octets.newBuilder(this.vOctets_).mergeFrom(value).buildPartial();
          } else {
            this.vOctets_ = value;
          } 
          onChanged();
        } else {
          this.vOctetsBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x8;
        return this;
      }
      
      public Builder clearVOctets() {
        if (this.vOctetsBuilder_ == null) {
          this.vOctets_ = null;
          onChanged();
        } else {
          this.vOctetsBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
      }
      
      public Octets.Builder getVOctetsBuilder() {
        this.bitField0_ |= 0x8;
        onChanged();
        return (Octets.Builder)getVOctetsFieldBuilder().getBuilder();
      }
      
      public OctetsOrBuilder getVOctetsOrBuilder() {
        if (this.vOctetsBuilder_ != null)
          return (OctetsOrBuilder)this.vOctetsBuilder_.getMessageOrBuilder();
        return (this.vOctets_ == null) ? 
          Octets.getDefaultInstance() : this.vOctets_;
      }
      
      private SingleFieldBuilderV3<Octets, Octets.Builder, OctetsOrBuilder> getVOctetsFieldBuilder() {
        if (this.vOctetsBuilder_ == null) {
          this
            
            .vOctetsBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getVOctets(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.vOctets_ = null;
        } 
        return this.vOctetsBuilder_;
      }
      
      public boolean hasVDouble() {
        return ((this.bitField0_ & 0x10) != 0);
      }
      
      public double getVDouble() {
        return this.vDouble_;
      }
      
      public Builder setVDouble(double value) {
        this.bitField0_ |= 0x10;
        this.vDouble_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearVDouble() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.vDouble_ = 0.0D;
        onChanged();
        return this;
      }
      
      public boolean hasVFloat() {
        return ((this.bitField0_ & 0x20) != 0);
      }
      
      public float getVFloat() {
        return this.vFloat_;
      }
      
      public Builder setVFloat(float value) {
        this.bitField0_ |= 0x20;
        this.vFloat_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearVFloat() {
        this.bitField0_ &= 0xFFFFFFDF;
        this.vFloat_ = 0.0F;
        onChanged();
        return this;
      }
      
      public boolean hasVBool() {
        return ((this.bitField0_ & 0x40) != 0);
      }
      
      public boolean getVBool() {
        return this.vBool_;
      }
      
      public Builder setVBool(boolean value) {
        this.bitField0_ |= 0x40;
        this.vBool_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearVBool() {
        this.bitField0_ &= 0xFFFFFFBF;
        this.vBool_ = false;
        onChanged();
        return this;
      }
      
      public boolean hasVString() {
        return ((this.bitField0_ & 0x80) != 0);
      }
      
      public String getVString() {
        if (this.vStringBuilder_ == null)
          return (this.vString_ == null) ? String.getDefaultInstance() : this.vString_;
        return (String)this.vStringBuilder_.getMessage();
      }
      
      public Builder setVString(String value) {
        if (this.vStringBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.vString_ = value;
          onChanged();
        } else {
          this.vStringBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x80;
        return this;
      }
      
      public Builder setVString(String.Builder builderForValue) {
        if (this.vStringBuilder_ == null) {
          this.vString_ = builderForValue.build();
          onChanged();
        } else {
          this.vStringBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x80;
        return this;
      }
      
      public Builder mergeVString(String value) {
        if (this.vStringBuilder_ == null) {
          if ((this.bitField0_ & 0x80) != 0 && this.vString_ != null && this.vString_ != 
            
            String.getDefaultInstance()) {
            this
              .vString_ = String.newBuilder(this.vString_).mergeFrom(value).buildPartial();
          } else {
            this.vString_ = value;
          } 
          onChanged();
        } else {
          this.vStringBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x80;
        return this;
      }
      
      public Builder clearVString() {
        if (this.vStringBuilder_ == null) {
          this.vString_ = null;
          onChanged();
        } else {
          this.vStringBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFF7F;
        return this;
      }
      
      public String.Builder getVStringBuilder() {
        this.bitField0_ |= 0x80;
        onChanged();
        return (String.Builder)getVStringFieldBuilder().getBuilder();
      }
      
      public StringOrBuilder getVStringOrBuilder() {
        if (this.vStringBuilder_ != null)
          return (StringOrBuilder)this.vStringBuilder_.getMessageOrBuilder();
        return (this.vString_ == null) ? 
          String.getDefaultInstance() : this.vString_;
      }
      
      private SingleFieldBuilderV3<String, String.Builder, StringOrBuilder> getVStringFieldBuilder() {
        if (this.vStringBuilder_ == null) {
          this
            
            .vStringBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getVString(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.vString_ = null;
        } 
        return this.vStringBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final Scalar DEFAULT_INSTANCE = new Scalar();
    
    public static Scalar getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Scalar> PARSER = (Parser<Scalar>)new AbstractParser<Scalar>() {
        public Scalar parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Scalar(input, extensionRegistry);
        }
      };
    
    public static Parser<Scalar> parser() {
      return PARSER;
    }
    
    public Parser<Scalar> getParserForType() {
      return PARSER;
    }
    
    public Scalar getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public static interface OctetsOrBuilder extends MessageOrBuilder {
      boolean hasValue();
      
      ByteString getValue();
      
      boolean hasContentType();
      
      int getContentType();
    }
    
    public static interface StringOrBuilder extends MessageOrBuilder {
      boolean hasValue();
      
      ByteString getValue();
      
      boolean hasCollation();
      
      long getCollation();
    }
  }
  
  public static interface ObjectOrBuilder extends MessageOrBuilder {
    List<Object.ObjectField> getFldList();
    
    Object.ObjectField getFld(int param1Int);
    
    int getFldCount();
    
    List<? extends Object.ObjectFieldOrBuilder> getFldOrBuilderList();
    
    Object.ObjectFieldOrBuilder getFldOrBuilder(int param1Int);
  }
  
  public static final class Object extends GeneratedMessageV3 implements ObjectOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int FLD_FIELD_NUMBER = 1;
    
    private List<ObjectField> fld_;
    
    private byte memoizedIsInitialized;
    
    private Object(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Object() {
      this.memoizedIsInitialized = -1;
      this.fld_ = Collections.emptyList();
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Object();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Object(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                this.fld_ = new ArrayList<>();
                mutable_bitField0_ |= 0x1;
              } 
              this.fld_.add(input.readMessage(ObjectField.PARSER, extensionRegistry));
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
          this.fld_ = Collections.unmodifiableList(this.fld_); 
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Object_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Object_fieldAccessorTable.ensureFieldAccessorsInitialized(Object.class, Builder.class);
    }
    
    public static final class ObjectField extends GeneratedMessageV3 implements ObjectFieldOrBuilder {
      private static final long serialVersionUID = 0L;
      
      private int bitField0_;
      
      public static final int KEY_FIELD_NUMBER = 1;
      
      private volatile Object key_;
      
      public static final int VALUE_FIELD_NUMBER = 2;
      
      private Any value_;
      
      private byte memoizedIsInitialized;
      
      private ObjectField(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
      }
      
      private ObjectField() {
        this.memoizedIsInitialized = -1;
        this.key_ = "";
      }
      
      protected Object newInstance(UnusedPrivateParameter unused) {
        return new ObjectField();
      }
      
      public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
      }
      
      private ObjectField(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null)
          throw new NullPointerException(); 
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
          boolean done = false;
          while (!done) {
            ByteString bs;
            Any.Builder subBuilder;
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                continue;
              case 10:
                bs = input.readBytes();
                this.bitField0_ |= 0x1;
                this.key_ = bs;
                continue;
              case 18:
                subBuilder = null;
                if ((this.bitField0_ & 0x2) != 0)
                  subBuilder = this.value_.toBuilder(); 
                this.value_ = (Any)input.readMessage(Any.PARSER, extensionRegistry);
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
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Object_ObjectField_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectField.class, Builder.class);
      }
      
      public boolean hasKey() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public String getKey() {
        Object ref = this.key_;
        if (ref instanceof String)
          return (String)ref; 
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8())
          this.key_ = s; 
        return s;
      }
      
      public ByteString getKeyBytes() {
        Object ref = this.key_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.key_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public boolean hasValue() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public Any getValue() {
        return (this.value_ == null) ? Any.getDefaultInstance() : this.value_;
      }
      
      public AnyOrBuilder getValueOrBuilder() {
        return (this.value_ == null) ? Any.getDefaultInstance() : this.value_;
      }
      
      public final boolean isInitialized() {
        byte isInitialized = this.memoizedIsInitialized;
        if (isInitialized == 1)
          return true; 
        if (isInitialized == 0)
          return false; 
        if (!hasKey()) {
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
          GeneratedMessageV3.writeString(output, 1, this.key_); 
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
          size += GeneratedMessageV3.computeStringSize(1, this.key_); 
        if ((this.bitField0_ & 0x2) != 0)
          size += CodedOutputStream.computeMessageSize(2, (MessageLite)getValue()); 
        size += this.unknownFields.getSerializedSize();
        this.memoizedSize = size;
        return size;
      }
      
      public boolean equals(Object obj) {
        if (obj == this)
          return true; 
        if (!(obj instanceof ObjectField))
          return super.equals(obj); 
        ObjectField other = (ObjectField)obj;
        if (hasKey() != other.hasKey())
          return false; 
        if (hasKey() && !getKey().equals(other.getKey()))
          return false; 
        if (hasValue() != other.hasValue())
          return false; 
        if (hasValue() && !getValue().equals(other.getValue()))
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
        if (hasKey()) {
          hash = 37 * hash + 1;
          hash = 53 * hash + getKey().hashCode();
        } 
        if (hasValue()) {
          hash = 37 * hash + 2;
          hash = 53 * hash + getValue().hashCode();
        } 
        hash = 29 * hash + this.unknownFields.hashCode();
        this.memoizedHashCode = hash;
        return hash;
      }
      
      public static ObjectField parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ObjectField)PARSER.parseFrom(data);
      }
      
      public static ObjectField parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ObjectField)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static ObjectField parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ObjectField)PARSER.parseFrom(data);
      }
      
      public static ObjectField parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ObjectField)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static ObjectField parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ObjectField)PARSER.parseFrom(data);
      }
      
      public static ObjectField parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ObjectField)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static ObjectField parseFrom(InputStream input) throws IOException {
        return (ObjectField)GeneratedMessageV3.parseWithIOException(PARSER, input);
      }
      
      public static ObjectField parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ObjectField)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
      }
      
      public static ObjectField parseDelimitedFrom(InputStream input) throws IOException {
        return (ObjectField)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
      }
      
      public static ObjectField parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ObjectField)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }
      
      public static ObjectField parseFrom(CodedInputStream input) throws IOException {
        return (ObjectField)GeneratedMessageV3.parseWithIOException(PARSER, input);
      }
      
      public static ObjectField parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ObjectField)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
      }
      
      public Builder newBuilderForType() {
        return newBuilder();
      }
      
      public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
      }
      
      public static Builder newBuilder(ObjectField prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }
      
      public Builder toBuilder() {
        return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder()).mergeFrom(this);
      }
      
      protected Builder newBuilderForType(BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
      }
      
      public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ObjectFieldOrBuilder {
        private int bitField0_;
        
        private Object key_;
        
        private Any value_;
        
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> valueBuilder_;
        
        public static final Descriptors.Descriptor getDescriptor() {
          return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor;
        }
        
        protected FieldAccessorTable internalGetFieldAccessorTable() {
          return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Object_ObjectField_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectField.class, Builder.class);
        }
        
        private Builder() {
          this.key_ = "";
          maybeForceBuilderInitialization();
        }
        
        private Builder(BuilderParent parent) {
          super(parent);
          this.key_ = "";
          maybeForceBuilderInitialization();
        }
        
        private void maybeForceBuilderInitialization() {
          if (ObjectField.alwaysUseFieldBuilders)
            getValueFieldBuilder(); 
        }
        
        public Builder clear() {
          super.clear();
          this.key_ = "";
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
          return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor;
        }
        
        public ObjectField getDefaultInstanceForType() {
          return ObjectField.getDefaultInstance();
        }
        
        public ObjectField build() {
          ObjectField result = buildPartial();
          if (!result.isInitialized())
            throw newUninitializedMessageException(result); 
          return result;
        }
        
        public ObjectField buildPartial() {
          ObjectField result = new ObjectField(this);
          int from_bitField0_ = this.bitField0_;
          int to_bitField0_ = 0;
          if ((from_bitField0_ & 0x1) != 0)
            to_bitField0_ |= 0x1; 
          result.key_ = this.key_;
          if ((from_bitField0_ & 0x2) != 0) {
            if (this.valueBuilder_ == null) {
              result.value_ = this.value_;
            } else {
              result.value_ = (Any)this.valueBuilder_.build();
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
          if (other instanceof ObjectField)
            return mergeFrom((ObjectField)other);
          super.mergeFrom(other);
          return this;
        }
        
        public Builder mergeFrom(ObjectField other) {
          if (other == ObjectField.getDefaultInstance())
            return this; 
          if (other.hasKey()) {
            this.bitField0_ |= 0x1;
            this.key_ = other.key_;
            onChanged();
          } 
          if (other.hasValue())
            mergeValue(other.getValue()); 
          mergeUnknownFields(other.unknownFields);
          onChanged();
          return this;
        }
        
        public final boolean isInitialized() {
          if (!hasKey())
            return false; 
          if (!hasValue())
            return false; 
          if (!getValue().isInitialized())
            return false; 
          return true;
        }
        
        public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
          ObjectField parsedMessage = null;
          try {
            parsedMessage = (ObjectField) ObjectField.PARSER.parsePartialFrom(input, extensionRegistry);
          } catch (InvalidProtocolBufferException e) {
            parsedMessage = (ObjectField)e.getUnfinishedMessage();
            throw e.unwrapIOException();
          } finally {
            if (parsedMessage != null)
              mergeFrom(parsedMessage); 
          } 
          return this;
        }
        
        public boolean hasKey() {
          return ((this.bitField0_ & 0x1) != 0);
        }
        
        public String getKey() {
          Object ref = this.key_;
          if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8())
              this.key_ = s; 
            return s;
          } 
          return (String)ref;
        }
        
        public ByteString getKeyBytes() {
          Object ref = this.key_;
          if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.key_ = b;
            return b;
          } 
          return (ByteString)ref;
        }
        
        public Builder setKey(String value) {
          if (value == null)
            throw new NullPointerException(); 
          this.bitField0_ |= 0x1;
          this.key_ = value;
          onChanged();
          return this;
        }
        
        public Builder clearKey() {
          this.bitField0_ &= 0xFFFFFFFE;
          this.key_ = ObjectField.getDefaultInstance().getKey();
          onChanged();
          return this;
        }
        
        public Builder setKeyBytes(ByteString value) {
          if (value == null)
            throw new NullPointerException(); 
          this.bitField0_ |= 0x1;
          this.key_ = value;
          onChanged();
          return this;
        }
        
        public boolean hasValue() {
          return ((this.bitField0_ & 0x2) != 0);
        }
        
        public Any getValue() {
          if (this.valueBuilder_ == null)
            return (this.value_ == null) ? Any.getDefaultInstance() : this.value_;
          return (Any)this.valueBuilder_.getMessage();
        }
        
        public Builder setValue(Any value) {
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
        
        public Builder setValue(Any.Builder builderForValue) {
          if (this.valueBuilder_ == null) {
            this.value_ = builderForValue.build();
            onChanged();
          } else {
            this.valueBuilder_.setMessage((AbstractMessage)builderForValue.build());
          } 
          this.bitField0_ |= 0x2;
          return this;
        }
        
        public Builder mergeValue(Any value) {
          if (this.valueBuilder_ == null) {
            if ((this.bitField0_ & 0x2) != 0 && this.value_ != null && this.value_ != Any.getDefaultInstance()) {
              this.value_ = Any.newBuilder(this.value_).mergeFrom(value).buildPartial();
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
        
        public Any.Builder getValueBuilder() {
          this.bitField0_ |= 0x2;
          onChanged();
          return (Any.Builder)getValueFieldBuilder().getBuilder();
        }
        
        public AnyOrBuilder getValueOrBuilder() {
          if (this.valueBuilder_ != null)
            return (AnyOrBuilder)this.valueBuilder_.getMessageOrBuilder();
          return (this.value_ == null) ? Any.getDefaultInstance() : this.value_;
        }
        
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getValueFieldBuilder() {
          if (this.valueBuilder_ == null) {
            this.valueBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getValue(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
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
      
      private static final ObjectField DEFAULT_INSTANCE = new ObjectField();
      
      public static ObjectField getDefaultInstance() {
        return DEFAULT_INSTANCE;
      }
      
      @Deprecated
      public static final Parser<ObjectField> PARSER = (Parser<ObjectField>)new AbstractParser<ObjectField>() {
          public ObjectField parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ObjectField(input, extensionRegistry);
          }
        };
      
      public static Parser<ObjectField> parser() {
        return PARSER;
      }
      
      public Parser<ObjectField> getParserForType() {
        return PARSER;
      }
      
      public ObjectField getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
      }
    }
    
    public List<ObjectField> getFldList() {
      return this.fld_;
    }
    
    public List<? extends ObjectFieldOrBuilder> getFldOrBuilderList() {
      return (List)this.fld_;
    }
    
    public int getFldCount() {
      return this.fld_.size();
    }
    
    public ObjectField getFld(int index) {
      return this.fld_.get(index);
    }
    
    public ObjectFieldOrBuilder getFldOrBuilder(int index) {
      return this.fld_.get(index);
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      for (int i = 0; i < getFldCount(); i++) {
        if (!getFld(i).isInitialized()) {
          this.memoizedIsInitialized = 0;
          return false;
        } 
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      for (int i = 0; i < this.fld_.size(); i++)
        output.writeMessage(1, (MessageLite)this.fld_.get(i)); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      for (int i = 0; i < this.fld_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(1, (MessageLite)this.fld_.get(i)); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Object))
        return super.equals(obj); 
      Object other = (Object)obj;
      if (!getFldList().equals(other.getFldList()))
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
      if (getFldCount() > 0) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getFldList().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Object parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Object)PARSER.parseFrom(data);
    }
    
    public static Object parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Object)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Object parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Object)PARSER.parseFrom(data);
    }
    
    public static Object parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Object)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Object parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Object)PARSER.parseFrom(data);
    }
    
    public static Object parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Object)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Object parseFrom(InputStream input) throws IOException {
      return 
        (Object)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Object parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Object)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Object parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Object)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Object parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Object)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Object parseFrom(CodedInputStream input) throws IOException {
      return 
        (Object)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Object parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Object)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Object prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ObjectOrBuilder {
      private int bitField0_;
      
      private List<ObjectField> fld_;
      
      private RepeatedFieldBuilderV3<ObjectField, ObjectField.Builder, ObjectFieldOrBuilder> fldBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Object_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Object_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Object.class, Builder.class);
      }
      
      private Builder() {
        this
          .fld_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.fld_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Object.alwaysUseFieldBuilders)
          getFldFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        if (this.fldBuilder_ == null) {
          this.fld_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFE;
        } else {
          this.fldBuilder_.clear();
        } 
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Object_descriptor;
      }
      
      public Object getDefaultInstanceForType() {
        return Object.getDefaultInstance();
      }
      
      public Object build() {
        Object result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Object buildPartial() {
        Object result = new Object(this);
        int from_bitField0_ = this.bitField0_;
        if (this.fldBuilder_ == null) {
          if ((this.bitField0_ & 0x1) != 0) {
            this.fld_ = Collections.unmodifiableList(this.fld_);
            this.bitField0_ &= 0xFFFFFFFE;
          } 
          result.fld_ = this.fld_;
        } else {
          result.fld_ = this.fldBuilder_.build();
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
        if (other instanceof Object)
          return mergeFrom((Object)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Object other) {
        if (other == Object.getDefaultInstance())
          return this; 
        if (this.fldBuilder_ == null) {
          if (!other.fld_.isEmpty()) {
            if (this.fld_.isEmpty()) {
              this.fld_ = other.fld_;
              this.bitField0_ &= 0xFFFFFFFE;
            } else {
              ensureFldIsMutable();
              this.fld_.addAll(other.fld_);
            } 
            onChanged();
          } 
        } else if (!other.fld_.isEmpty()) {
          if (this.fldBuilder_.isEmpty()) {
            this.fldBuilder_.dispose();
            this.fldBuilder_ = null;
            this.fld_ = other.fld_;
            this.bitField0_ &= 0xFFFFFFFE;
            this.fldBuilder_ = Object.alwaysUseFieldBuilders ? getFldFieldBuilder() : null;
          } else {
            this.fldBuilder_.addAllMessages(other.fld_);
          } 
        } 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        for (int i = 0; i < getFldCount(); i++) {
          if (!getFld(i).isInitialized())
            return false; 
        } 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Object parsedMessage = null;
        try {
          parsedMessage = (Object) Object.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Object)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      private void ensureFldIsMutable() {
        if ((this.bitField0_ & 0x1) == 0) {
          this.fld_ = new ArrayList<>(this.fld_);
          this.bitField0_ |= 0x1;
        } 
      }
      
      public List<ObjectField> getFldList() {
        if (this.fldBuilder_ == null)
          return Collections.unmodifiableList(this.fld_); 
        return this.fldBuilder_.getMessageList();
      }
      
      public int getFldCount() {
        if (this.fldBuilder_ == null)
          return this.fld_.size(); 
        return this.fldBuilder_.getCount();
      }
      
      public ObjectField getFld(int index) {
        if (this.fldBuilder_ == null)
          return this.fld_.get(index); 
        return (ObjectField)this.fldBuilder_.getMessage(index);
      }
      
      public Builder setFld(int index, ObjectField value) {
        if (this.fldBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureFldIsMutable();
          this.fld_.set(index, value);
          onChanged();
        } else {
          this.fldBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setFld(int index, ObjectField.Builder builderForValue) {
        if (this.fldBuilder_ == null) {
          ensureFldIsMutable();
          this.fld_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.fldBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addFld(ObjectField value) {
        if (this.fldBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureFldIsMutable();
          this.fld_.add(value);
          onChanged();
        } else {
          this.fldBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addFld(int index, ObjectField value) {
        if (this.fldBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureFldIsMutable();
          this.fld_.add(index, value);
          onChanged();
        } else {
          this.fldBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addFld(ObjectField.Builder builderForValue) {
        if (this.fldBuilder_ == null) {
          ensureFldIsMutable();
          this.fld_.add(builderForValue.build());
          onChanged();
        } else {
          this.fldBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addFld(int index, ObjectField.Builder builderForValue) {
        if (this.fldBuilder_ == null) {
          ensureFldIsMutable();
          this.fld_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.fldBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllFld(Iterable<? extends ObjectField> values) {
        if (this.fldBuilder_ == null) {
          ensureFldIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.fld_);
          onChanged();
        } else {
          this.fldBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearFld() {
        if (this.fldBuilder_ == null) {
          this.fld_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFE;
          onChanged();
        } else {
          this.fldBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeFld(int index) {
        if (this.fldBuilder_ == null) {
          ensureFldIsMutable();
          this.fld_.remove(index);
          onChanged();
        } else {
          this.fldBuilder_.remove(index);
        } 
        return this;
      }
      
      public ObjectField.Builder getFldBuilder(int index) {
        return (ObjectField.Builder)getFldFieldBuilder().getBuilder(index);
      }
      
      public ObjectFieldOrBuilder getFldOrBuilder(int index) {
        if (this.fldBuilder_ == null)
          return this.fld_.get(index); 
        return (ObjectFieldOrBuilder)this.fldBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends ObjectFieldOrBuilder> getFldOrBuilderList() {
        if (this.fldBuilder_ != null)
          return this.fldBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.fld_);
      }
      
      public ObjectField.Builder addFldBuilder() {
        return (ObjectField.Builder)getFldFieldBuilder().addBuilder(
            (AbstractMessage) ObjectField.getDefaultInstance());
      }
      
      public ObjectField.Builder addFldBuilder(int index) {
        return (ObjectField.Builder)getFldFieldBuilder().addBuilder(index,
            (AbstractMessage) ObjectField.getDefaultInstance());
      }
      
      public List<ObjectField.Builder> getFldBuilderList() {
        return getFldFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<ObjectField, ObjectField.Builder, ObjectFieldOrBuilder> getFldFieldBuilder() {
        if (this.fldBuilder_ == null) {
          this
            
            .fldBuilder_ = new RepeatedFieldBuilderV3(this.fld_, ((this.bitField0_ & 0x1) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.fld_ = null;
        } 
        return this.fldBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final Object DEFAULT_INSTANCE = new Object();
    
    public static Object getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Object> PARSER = (Parser<Object>)new AbstractParser<Object>() {
        public Object parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Object(input, extensionRegistry);
        }
      };
    
    public static Parser<Object> parser() {
      return PARSER;
    }
    
    public Parser<Object> getParserForType() {
      return PARSER;
    }
    
    public Object getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public static interface ObjectFieldOrBuilder extends MessageOrBuilder {
      boolean hasKey();
      
      String getKey();
      
      ByteString getKeyBytes();
      
      boolean hasValue();
      
      Any getValue();
      
      AnyOrBuilder getValueOrBuilder();
    }
  }
  
  public static interface ArrayOrBuilder extends MessageOrBuilder {
    List<Any> getValueList();
    
    Any getValue(int param1Int);
    
    int getValueCount();
    
    List<? extends AnyOrBuilder> getValueOrBuilderList();
    
    AnyOrBuilder getValueOrBuilder(int param1Int);
  }
  
  public static final class Array extends GeneratedMessageV3 implements ArrayOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int VALUE_FIELD_NUMBER = 1;
    
    private List<Any> value_;
    
    private byte memoizedIsInitialized;
    
    private Array(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Array() {
      this.memoizedIsInitialized = -1;
      this.value_ = Collections.emptyList();
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Array();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Array(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                this.value_ = new ArrayList<>();
                mutable_bitField0_ |= 0x1;
              } 
              this.value_.add(input.readMessage(Any.PARSER, extensionRegistry));
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
          this.value_ = Collections.unmodifiableList(this.value_); 
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Array_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Array_fieldAccessorTable.ensureFieldAccessorsInitialized(Array.class, Builder.class);
    }
    
    public List<Any> getValueList() {
      return this.value_;
    }
    
    public List<? extends AnyOrBuilder> getValueOrBuilderList() {
      return (List)this.value_;
    }
    
    public int getValueCount() {
      return this.value_.size();
    }
    
    public Any getValue(int index) {
      return this.value_.get(index);
    }
    
    public AnyOrBuilder getValueOrBuilder(int index) {
      return this.value_.get(index);
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      for (int i = 0; i < getValueCount(); i++) {
        if (!getValue(i).isInitialized()) {
          this.memoizedIsInitialized = 0;
          return false;
        } 
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      for (int i = 0; i < this.value_.size(); i++)
        output.writeMessage(1, (MessageLite)this.value_.get(i)); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      for (int i = 0; i < this.value_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(1, (MessageLite)this.value_.get(i)); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Array))
        return super.equals(obj); 
      Array other = (Array)obj;
      if (!getValueList().equals(other.getValueList()))
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
      if (getValueCount() > 0) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getValueList().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Array parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Array)PARSER.parseFrom(data);
    }
    
    public static Array parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Array)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Array parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Array)PARSER.parseFrom(data);
    }
    
    public static Array parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Array)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Array parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Array)PARSER.parseFrom(data);
    }
    
    public static Array parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Array)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Array parseFrom(InputStream input) throws IOException {
      return 
        (Array)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Array parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Array)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Array parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Array)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Array parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Array)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Array parseFrom(CodedInputStream input) throws IOException {
      return 
        (Array)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Array parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Array)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Array prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ArrayOrBuilder {
      private int bitField0_;
      
      private List<Any> value_;
      
      private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> valueBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Array_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Array_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Array.class, Builder.class);
      }
      
      private Builder() {
        this
          .value_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.value_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Array.alwaysUseFieldBuilders)
          getValueFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        if (this.valueBuilder_ == null) {
          this.value_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFE;
        } else {
          this.valueBuilder_.clear();
        } 
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Array_descriptor;
      }
      
      public Array getDefaultInstanceForType() {
        return Array.getDefaultInstance();
      }
      
      public Array build() {
        Array result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Array buildPartial() {
        Array result = new Array(this);
        int from_bitField0_ = this.bitField0_;
        if (this.valueBuilder_ == null) {
          if ((this.bitField0_ & 0x1) != 0) {
            this.value_ = Collections.unmodifiableList(this.value_);
            this.bitField0_ &= 0xFFFFFFFE;
          } 
          result.value_ = this.value_;
        } else {
          result.value_ = this.valueBuilder_.build();
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
        if (other instanceof Array)
          return mergeFrom((Array)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Array other) {
        if (other == Array.getDefaultInstance())
          return this; 
        if (this.valueBuilder_ == null) {
          if (!other.value_.isEmpty()) {
            if (this.value_.isEmpty()) {
              this.value_ = other.value_;
              this.bitField0_ &= 0xFFFFFFFE;
            } else {
              ensureValueIsMutable();
              this.value_.addAll(other.value_);
            } 
            onChanged();
          } 
        } else if (!other.value_.isEmpty()) {
          if (this.valueBuilder_.isEmpty()) {
            this.valueBuilder_.dispose();
            this.valueBuilder_ = null;
            this.value_ = other.value_;
            this.bitField0_ &= 0xFFFFFFFE;
            this.valueBuilder_ = Array.alwaysUseFieldBuilders ? getValueFieldBuilder() : null;
          } else {
            this.valueBuilder_.addAllMessages(other.value_);
          } 
        } 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        for (int i = 0; i < getValueCount(); i++) {
          if (!getValue(i).isInitialized())
            return false; 
        } 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Array parsedMessage = null;
        try {
          parsedMessage = (Array) Array.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Array)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      private void ensureValueIsMutable() {
        if ((this.bitField0_ & 0x1) == 0) {
          this.value_ = new ArrayList<>(this.value_);
          this.bitField0_ |= 0x1;
        } 
      }
      
      public List<Any> getValueList() {
        if (this.valueBuilder_ == null)
          return Collections.unmodifiableList(this.value_); 
        return this.valueBuilder_.getMessageList();
      }
      
      public int getValueCount() {
        if (this.valueBuilder_ == null)
          return this.value_.size(); 
        return this.valueBuilder_.getCount();
      }
      
      public Any getValue(int index) {
        if (this.valueBuilder_ == null)
          return this.value_.get(index); 
        return (Any)this.valueBuilder_.getMessage(index);
      }
      
      public Builder setValue(int index, Any value) {
        if (this.valueBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureValueIsMutable();
          this.value_.set(index, value);
          onChanged();
        } else {
          this.valueBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setValue(int index, Any.Builder builderForValue) {
        if (this.valueBuilder_ == null) {
          ensureValueIsMutable();
          this.value_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.valueBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addValue(Any value) {
        if (this.valueBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureValueIsMutable();
          this.value_.add(value);
          onChanged();
        } else {
          this.valueBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addValue(int index, Any value) {
        if (this.valueBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureValueIsMutable();
          this.value_.add(index, value);
          onChanged();
        } else {
          this.valueBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addValue(Any.Builder builderForValue) {
        if (this.valueBuilder_ == null) {
          ensureValueIsMutable();
          this.value_.add(builderForValue.build());
          onChanged();
        } else {
          this.valueBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addValue(int index, Any.Builder builderForValue) {
        if (this.valueBuilder_ == null) {
          ensureValueIsMutable();
          this.value_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.valueBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllValue(Iterable<? extends Any> values) {
        if (this.valueBuilder_ == null) {
          ensureValueIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.value_);
          onChanged();
        } else {
          this.valueBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearValue() {
        if (this.valueBuilder_ == null) {
          this.value_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFE;
          onChanged();
        } else {
          this.valueBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeValue(int index) {
        if (this.valueBuilder_ == null) {
          ensureValueIsMutable();
          this.value_.remove(index);
          onChanged();
        } else {
          this.valueBuilder_.remove(index);
        } 
        return this;
      }
      
      public Any.Builder getValueBuilder(int index) {
        return (Any.Builder)getValueFieldBuilder().getBuilder(index);
      }
      
      public AnyOrBuilder getValueOrBuilder(int index) {
        if (this.valueBuilder_ == null)
          return this.value_.get(index); 
        return (AnyOrBuilder)this.valueBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends AnyOrBuilder> getValueOrBuilderList() {
        if (this.valueBuilder_ != null)
          return this.valueBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.value_);
      }
      
      public Any.Builder addValueBuilder() {
        return (Any.Builder)getValueFieldBuilder().addBuilder(
            (AbstractMessage) Any.getDefaultInstance());
      }
      
      public Any.Builder addValueBuilder(int index) {
        return (Any.Builder)getValueFieldBuilder().addBuilder(index,
            (AbstractMessage) Any.getDefaultInstance());
      }
      
      public List<Any.Builder> getValueBuilderList() {
        return getValueFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getValueFieldBuilder() {
        if (this.valueBuilder_ == null) {
          this
            
            .valueBuilder_ = new RepeatedFieldBuilderV3(this.value_, ((this.bitField0_ & 0x1) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
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
    
    private static final Array DEFAULT_INSTANCE = new Array();
    
    public static Array getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Array> PARSER = (Parser<Array>)new AbstractParser<Array>() {
        public Array parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Array(input, extensionRegistry);
        }
      };
    
    public static Parser<Array> parser() {
      return PARSER;
    }
    
    public Parser<Array> getParserForType() {
      return PARSER;
    }
    
    public Array getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface AnyOrBuilder extends MessageOrBuilder {
    boolean hasType();
    
    Any.Type getType();
    
    boolean hasScalar();
    
    Scalar getScalar();
    
    ScalarOrBuilder getScalarOrBuilder();
    
    boolean hasObj();
    
    Object getObj();
    
    ObjectOrBuilder getObjOrBuilder();
    
    boolean hasArray();
    
    Array getArray();
    
    ArrayOrBuilder getArrayOrBuilder();
  }
  
  public static final class Any extends GeneratedMessageV3 implements AnyOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int TYPE_FIELD_NUMBER = 1;
    
    private int type_;
    
    public static final int SCALAR_FIELD_NUMBER = 2;
    
    private Scalar scalar_;
    
    public static final int OBJ_FIELD_NUMBER = 3;
    
    private Object obj_;
    
    public static final int ARRAY_FIELD_NUMBER = 4;
    
    private Array array_;
    
    private byte memoizedIsInitialized;
    
    private Any(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Any() {
      this.memoizedIsInitialized = -1;
      this.type_ = 1;
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Any();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Any(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      int mutable_bitField0_ = 0;
      UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int rawValue;
          Scalar.Builder builder1;
          Object.Builder builder;
          Array.Builder subBuilder;
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
              builder1 = null;
              if ((this.bitField0_ & 0x2) != 0)
                builder1 = this.scalar_.toBuilder(); 
              this.scalar_ = (Scalar)input.readMessage(Scalar.PARSER, extensionRegistry);
              if (builder1 != null) {
                builder1.mergeFrom(this.scalar_);
                this.scalar_ = builder1.buildPartial();
              } 
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              builder = null;
              if ((this.bitField0_ & 0x4) != 0)
                builder = this.obj_.toBuilder(); 
              this.obj_ = (Object)input.readMessage(Object.PARSER, extensionRegistry);
              if (builder != null) {
                builder.mergeFrom(this.obj_);
                this.obj_ = builder.buildPartial();
              } 
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              subBuilder = null;
              if ((this.bitField0_ & 0x8) != 0)
                subBuilder = this.array_.toBuilder(); 
              this.array_ = (Array)input.readMessage(Array.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(this.array_);
                this.array_ = subBuilder.buildPartial();
              } 
              this.bitField0_ |= 0x8;
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
      return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Any_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Any_fieldAccessorTable.ensureFieldAccessorsInitialized(Any.class, Builder.class);
    }
    
    public enum Type implements ProtocolMessageEnum {
      SCALAR(1),
      OBJECT(2),
      ARRAY(3);
      
      public static final int SCALAR_VALUE = 1;
      
      public static final int OBJECT_VALUE = 2;
      
      public static final int ARRAY_VALUE = 3;
      
      private static final Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() {
          public Type findValueByNumber(int number) {
            return Type.forNumber(number);
          }
        };
      
      private static final Type[] VALUES = values();
      
      private final int value;
      
      public final int getNumber() {
        return this.value;
      }
      
      public static Type forNumber(int value) {
        switch (value) {
          case 1:
            return SCALAR;
          case 2:
            return OBJECT;
          case 3:
            return ARRAY;
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
        return Any.getDescriptor().getEnumTypes().get(0);
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
      return (result == null) ? Type.SCALAR : result;
    }
    
    public boolean hasScalar() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public Scalar getScalar() {
      return (this.scalar_ == null) ? Scalar.getDefaultInstance() : this.scalar_;
    }
    
    public ScalarOrBuilder getScalarOrBuilder() {
      return (this.scalar_ == null) ? Scalar.getDefaultInstance() : this.scalar_;
    }
    
    public boolean hasObj() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public Object getObj() {
      return (this.obj_ == null) ? Object.getDefaultInstance() : this.obj_;
    }
    
    public ObjectOrBuilder getObjOrBuilder() {
      return (this.obj_ == null) ? Object.getDefaultInstance() : this.obj_;
    }
    
    public boolean hasArray() {
      return ((this.bitField0_ & 0x8) != 0);
    }
    
    public Array getArray() {
      return (this.array_ == null) ? Array.getDefaultInstance() : this.array_;
    }
    
    public ArrayOrBuilder getArrayOrBuilder() {
      return (this.array_ == null) ? Array.getDefaultInstance() : this.array_;
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
      if (hasScalar() && 
        !getScalar().isInitialized()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      if (hasObj() && 
        !getObj().isInitialized()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      if (hasArray() && 
        !getArray().isInitialized()) {
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
        output.writeMessage(2, (MessageLite)getScalar()); 
      if ((this.bitField0_ & 0x4) != 0)
        output.writeMessage(3, (MessageLite)getObj()); 
      if ((this.bitField0_ & 0x8) != 0)
        output.writeMessage(4, (MessageLite)getArray()); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeEnumSize(1, this.type_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeMessageSize(2, (MessageLite)getScalar()); 
      if ((this.bitField0_ & 0x4) != 0)
        size += 
          CodedOutputStream.computeMessageSize(3, (MessageLite)getObj()); 
      if ((this.bitField0_ & 0x8) != 0)
        size += 
          CodedOutputStream.computeMessageSize(4, (MessageLite)getArray()); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Any))
        return super.equals(obj); 
      Any other = (Any)obj;
      if (hasType() != other.hasType())
        return false; 
      if (hasType() && 
        this.type_ != other.type_)
        return false; 
      if (hasScalar() != other.hasScalar())
        return false; 
      if (hasScalar() && 
        
        !getScalar().equals(other.getScalar()))
        return false; 
      if (hasObj() != other.hasObj())
        return false; 
      if (hasObj() && 
        
        !getObj().equals(other.getObj()))
        return false; 
      if (hasArray() != other.hasArray())
        return false; 
      if (hasArray() && 
        
        !getArray().equals(other.getArray()))
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
      if (hasScalar()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getScalar().hashCode();
      } 
      if (hasObj()) {
        hash = 37 * hash + 3;
        hash = 53 * hash + getObj().hashCode();
      } 
      if (hasArray()) {
        hash = 37 * hash + 4;
        hash = 53 * hash + getArray().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Any parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Any)PARSER.parseFrom(data);
    }
    
    public static Any parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Any)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Any parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Any)PARSER.parseFrom(data);
    }
    
    public static Any parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Any)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Any parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Any)PARSER.parseFrom(data);
    }
    
    public static Any parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Any)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Any parseFrom(InputStream input) throws IOException {
      return 
        (Any)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Any parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Any)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Any parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Any)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Any parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Any)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Any parseFrom(CodedInputStream input) throws IOException {
      return 
        (Any)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Any parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Any)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Any prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AnyOrBuilder {
      private int bitField0_;
      
      private int type_;
      
      private Scalar scalar_;
      
      private SingleFieldBuilderV3<Scalar, Scalar.Builder, ScalarOrBuilder> scalarBuilder_;
      
      private Object obj_;
      
      private SingleFieldBuilderV3<Object, Object.Builder, ObjectOrBuilder> objBuilder_;
      
      private Array array_;
      
      private SingleFieldBuilderV3<Array, Array.Builder, ArrayOrBuilder> arrayBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Any_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Any_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Any.class, Builder.class);
      }
      
      private Builder() {
        this.type_ = 1;
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.type_ = 1;
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Any.alwaysUseFieldBuilders) {
          getScalarFieldBuilder();
          getObjFieldBuilder();
          getArrayFieldBuilder();
        } 
      }
      
      public Builder clear() {
        super.clear();
        this.type_ = 1;
        this.bitField0_ &= 0xFFFFFFFE;
        if (this.scalarBuilder_ == null) {
          this.scalar_ = null;
        } else {
          this.scalarBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFD;
        if (this.objBuilder_ == null) {
          this.obj_ = null;
        } else {
          this.objBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFB;
        if (this.arrayBuilder_ == null) {
          this.array_ = null;
        } else {
          this.arrayBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxDatatypes.internal_static_Mysqlx_Datatypes_Any_descriptor;
      }
      
      public Any getDefaultInstanceForType() {
        return Any.getDefaultInstance();
      }
      
      public Any build() {
        Any result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Any buildPartial() {
        Any result = new Any(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.type_ = this.type_;
        if ((from_bitField0_ & 0x2) != 0) {
          if (this.scalarBuilder_ == null) {
            result.scalar_ = this.scalar_;
          } else {
            result.scalar_ = (Scalar)this.scalarBuilder_.build();
          } 
          to_bitField0_ |= 0x2;
        } 
        if ((from_bitField0_ & 0x4) != 0) {
          if (this.objBuilder_ == null) {
            result.obj_ = this.obj_;
          } else {
            result.obj_ = (Object)this.objBuilder_.build();
          } 
          to_bitField0_ |= 0x4;
        } 
        if ((from_bitField0_ & 0x8) != 0) {
          if (this.arrayBuilder_ == null) {
            result.array_ = this.array_;
          } else {
            result.array_ = (Array)this.arrayBuilder_.build();
          } 
          to_bitField0_ |= 0x8;
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
        if (other instanceof Any)
          return mergeFrom((Any)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Any other) {
        if (other == Any.getDefaultInstance())
          return this; 
        if (other.hasType())
          setType(other.getType()); 
        if (other.hasScalar())
          mergeScalar(other.getScalar()); 
        if (other.hasObj())
          mergeObj(other.getObj()); 
        if (other.hasArray())
          mergeArray(other.getArray()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasType())
          return false; 
        if (hasScalar() && !getScalar().isInitialized())
          return false; 
        if (hasObj() && !getObj().isInitialized())
          return false; 
        if (hasArray() && !getArray().isInitialized())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Any parsedMessage = null;
        try {
          parsedMessage = (Any) Any.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Any)e.getUnfinishedMessage();
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
        return (result == null) ? Type.SCALAR : result;
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
        this.type_ = 1;
        onChanged();
        return this;
      }
      
      public boolean hasScalar() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public Scalar getScalar() {
        if (this.scalarBuilder_ == null)
          return (this.scalar_ == null) ? Scalar.getDefaultInstance() : this.scalar_;
        return (Scalar)this.scalarBuilder_.getMessage();
      }
      
      public Builder setScalar(Scalar value) {
        if (this.scalarBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.scalar_ = value;
          onChanged();
        } else {
          this.scalarBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder setScalar(Scalar.Builder builderForValue) {
        if (this.scalarBuilder_ == null) {
          this.scalar_ = builderForValue.build();
          onChanged();
        } else {
          this.scalarBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder mergeScalar(Scalar value) {
        if (this.scalarBuilder_ == null) {
          if ((this.bitField0_ & 0x2) != 0 && this.scalar_ != null && this.scalar_ != 
            
            Scalar.getDefaultInstance()) {
            this
              .scalar_ = Scalar.newBuilder(this.scalar_).mergeFrom(value).buildPartial();
          } else {
            this.scalar_ = value;
          } 
          onChanged();
        } else {
          this.scalarBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder clearScalar() {
        if (this.scalarBuilder_ == null) {
          this.scalar_ = null;
          onChanged();
        } else {
          this.scalarBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public Scalar.Builder getScalarBuilder() {
        this.bitField0_ |= 0x2;
        onChanged();
        return (Scalar.Builder)getScalarFieldBuilder().getBuilder();
      }
      
      public ScalarOrBuilder getScalarOrBuilder() {
        if (this.scalarBuilder_ != null)
          return (ScalarOrBuilder)this.scalarBuilder_.getMessageOrBuilder();
        return (this.scalar_ == null) ? 
          Scalar.getDefaultInstance() : this.scalar_;
      }
      
      private SingleFieldBuilderV3<Scalar, Scalar.Builder, ScalarOrBuilder> getScalarFieldBuilder() {
        if (this.scalarBuilder_ == null) {
          this
            
            .scalarBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getScalar(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.scalar_ = null;
        } 
        return this.scalarBuilder_;
      }
      
      public boolean hasObj() {
        return ((this.bitField0_ & 0x4) != 0);
      }
      
      public Object getObj() {
        if (this.objBuilder_ == null)
          return (this.obj_ == null) ? Object.getDefaultInstance() : this.obj_;
        return (Object)this.objBuilder_.getMessage();
      }
      
      public Builder setObj(Object value) {
        if (this.objBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.obj_ = value;
          onChanged();
        } else {
          this.objBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x4;
        return this;
      }
      
      public Builder setObj(Object.Builder builderForValue) {
        if (this.objBuilder_ == null) {
          this.obj_ = builderForValue.build();
          onChanged();
        } else {
          this.objBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x4;
        return this;
      }
      
      public Builder mergeObj(Object value) {
        if (this.objBuilder_ == null) {
          if ((this.bitField0_ & 0x4) != 0 && this.obj_ != null && this.obj_ != 
            
            Object.getDefaultInstance()) {
            this
              .obj_ = Object.newBuilder(this.obj_).mergeFrom(value).buildPartial();
          } else {
            this.obj_ = value;
          } 
          onChanged();
        } else {
          this.objBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x4;
        return this;
      }
      
      public Builder clearObj() {
        if (this.objBuilder_ == null) {
          this.obj_ = null;
          onChanged();
        } else {
          this.objBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFB;
        return this;
      }
      
      public Object.Builder getObjBuilder() {
        this.bitField0_ |= 0x4;
        onChanged();
        return (Object.Builder)getObjFieldBuilder().getBuilder();
      }
      
      public ObjectOrBuilder getObjOrBuilder() {
        if (this.objBuilder_ != null)
          return (ObjectOrBuilder)this.objBuilder_.getMessageOrBuilder();
        return (this.obj_ == null) ? 
          Object.getDefaultInstance() : this.obj_;
      }
      
      private SingleFieldBuilderV3<Object, Object.Builder, ObjectOrBuilder> getObjFieldBuilder() {
        if (this.objBuilder_ == null) {
          this
            
            .objBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getObj(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.obj_ = null;
        } 
        return this.objBuilder_;
      }
      
      public boolean hasArray() {
        return ((this.bitField0_ & 0x8) != 0);
      }
      
      public Array getArray() {
        if (this.arrayBuilder_ == null)
          return (this.array_ == null) ? Array.getDefaultInstance() : this.array_;
        return (Array)this.arrayBuilder_.getMessage();
      }
      
      public Builder setArray(Array value) {
        if (this.arrayBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.array_ = value;
          onChanged();
        } else {
          this.arrayBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x8;
        return this;
      }
      
      public Builder setArray(Array.Builder builderForValue) {
        if (this.arrayBuilder_ == null) {
          this.array_ = builderForValue.build();
          onChanged();
        } else {
          this.arrayBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x8;
        return this;
      }
      
      public Builder mergeArray(Array value) {
        if (this.arrayBuilder_ == null) {
          if ((this.bitField0_ & 0x8) != 0 && this.array_ != null && this.array_ != 
            
            Array.getDefaultInstance()) {
            this
              .array_ = Array.newBuilder(this.array_).mergeFrom(value).buildPartial();
          } else {
            this.array_ = value;
          } 
          onChanged();
        } else {
          this.arrayBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x8;
        return this;
      }
      
      public Builder clearArray() {
        if (this.arrayBuilder_ == null) {
          this.array_ = null;
          onChanged();
        } else {
          this.arrayBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
      }
      
      public Array.Builder getArrayBuilder() {
        this.bitField0_ |= 0x8;
        onChanged();
        return (Array.Builder)getArrayFieldBuilder().getBuilder();
      }
      
      public ArrayOrBuilder getArrayOrBuilder() {
        if (this.arrayBuilder_ != null)
          return (ArrayOrBuilder)this.arrayBuilder_.getMessageOrBuilder();
        return (this.array_ == null) ? 
          Array.getDefaultInstance() : this.array_;
      }
      
      private SingleFieldBuilderV3<Array, Array.Builder, ArrayOrBuilder> getArrayFieldBuilder() {
        if (this.arrayBuilder_ == null) {
          this
            
            .arrayBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getArray(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.array_ = null;
        } 
        return this.arrayBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final Any DEFAULT_INSTANCE = new Any();
    
    public static Any getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Any> PARSER = (Parser<Any>)new AbstractParser<Any>() {
        public Any parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Any(input, extensionRegistry);
        }
      };
    
    public static Parser<Any> parser() {
      return PARSER;
    }
    
    public Parser<Any> getParserForType() {
      return PARSER;
    }
    
    public Any getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\026mysqlx_datatypes.proto\022\020Mysqlx.Datatypes\"Æ\003\n\006Scalar\022+\n\004type\030\001 \002(\0162\035.Mysqlx.Datatypes.Scalar.Type\022\024\n\fv_signed_int\030\002 \001(\022\022\026\n\016v_unsigned_int\030\003 \001(\004\0221\n\bv_octets\030\005 \001(\0132\037.Mysqlx.Datatypes.Scalar.Octets\022\020\n\bv_double\030\006 \001(\001\022\017\n\007v_float\030\007 \001(\002\022\016\n\006v_bool\030\b \001(\b\0221\n\bv_string\030\t \001(\0132\037.Mysqlx.Datatypes.Scalar.String\032*\n\006String\022\r\n\005value\030\001 \002(\f\022\021\n\tcollation\030\002 \001(\004\032-\n\006Octets\022\r\n\005value\030\001 \002(\f\022\024\n\fcontent_type\030\002 \001(\r\"m\n\004Type\022\n\n\006V_SINT\020\001\022\n\n\006V_UINT\020\002\022\n\n\006V_NULL\020\003\022\f\n\bV_OCTETS\020\004\022\f\n\bV_DOUBLE\020\005\022\013\n\007V_FLOAT\020\006\022\n\n\006V_BOOL\020\007\022\f\n\bV_STRING\020\b\"}\n\006Object\0221\n\003fld\030\001 \003(\0132$.Mysqlx.Datatypes.Object.ObjectField\032@\n\013ObjectField\022\013\n\003key\030\001 \002(\t\022$\n\005value\030\002 \002(\0132\025.Mysqlx.Datatypes.Any\"-\n\005Array\022$\n\005value\030\001 \003(\0132\025.Mysqlx.Datatypes.Any\"Ó\001\n\003Any\022(\n\004type\030\001 \002(\0162\032.Mysqlx.Datatypes.Any.Type\022(\n\006scalar\030\002 \001(\0132\030.Mysqlx.Datatypes.Scalar\022%\n\003obj\030\003 \001(\0132\030.Mysqlx.Datatypes.Object\022&\n\005array\030\004 \001(\0132\027.Mysqlx.Datatypes.Array\")\n\004Type\022\n\n\006SCALAR\020\001\022\n\n\006OBJECT\020\002\022\t\n\005ARRAY\020\003B\031\n\027com.mysql.cj.x.protobuf" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_Mysqlx_Datatypes_Scalar_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_Mysqlx_Datatypes_Scalar_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Scalar_descriptor, new String[] { "Type", "VSignedInt", "VUnsignedInt", "VOctets", "VDouble", "VFloat", "VBool", "VString" });
    internal_static_Mysqlx_Datatypes_Scalar_String_descriptor = internal_static_Mysqlx_Datatypes_Scalar_descriptor.getNestedTypes().get(0);
    internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Scalar_String_descriptor, new String[] { "Value", "Collation" });
    internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor = internal_static_Mysqlx_Datatypes_Scalar_descriptor.getNestedTypes().get(1);
    internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor, new String[] { "Value", "ContentType" });
    internal_static_Mysqlx_Datatypes_Object_descriptor = getDescriptor().getMessageTypes().get(1);
    internal_static_Mysqlx_Datatypes_Object_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Object_descriptor, new String[] { "Fld" });
    internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor = internal_static_Mysqlx_Datatypes_Object_descriptor.getNestedTypes().get(0);
    internal_static_Mysqlx_Datatypes_Object_ObjectField_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor, new String[] { "Key", "Value" });
    internal_static_Mysqlx_Datatypes_Array_descriptor = getDescriptor().getMessageTypes().get(2);
    internal_static_Mysqlx_Datatypes_Array_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Array_descriptor, new String[] { "Value" });
    internal_static_Mysqlx_Datatypes_Any_descriptor = getDescriptor().getMessageTypes().get(3);
    internal_static_Mysqlx_Datatypes_Any_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Any_descriptor, new String[] { "Type", "Scalar", "Obj", "Array" });
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\x\protobuf\MysqlxDatatypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */