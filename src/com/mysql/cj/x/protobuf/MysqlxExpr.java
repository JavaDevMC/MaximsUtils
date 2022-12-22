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

public final class MysqlxExpr {
  private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Expr_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_Expr_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Identifier_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_Identifier_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_DocumentPathItem_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_DocumentPathItem_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_ColumnIdentifier_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_FunctionCall_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_FunctionCall_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Operator_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_Operator_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Object_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_Object_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Object_ObjectField_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_Object_ObjectField_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Array_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Mysqlx_Expr_Array_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static interface ExprOrBuilder extends MessageOrBuilder {
    boolean hasType();
    
    Expr.Type getType();
    
    boolean hasIdentifier();
    
    ColumnIdentifier getIdentifier();
    
    ColumnIdentifierOrBuilder getIdentifierOrBuilder();
    
    boolean hasVariable();
    
    String getVariable();
    
    ByteString getVariableBytes();
    
    boolean hasLiteral();
    
    MysqlxDatatypes.Scalar getLiteral();
    
    MysqlxDatatypes.ScalarOrBuilder getLiteralOrBuilder();
    
    boolean hasFunctionCall();
    
    FunctionCall getFunctionCall();
    
    FunctionCallOrBuilder getFunctionCallOrBuilder();
    
    boolean hasOperator();
    
    Operator getOperator();
    
    OperatorOrBuilder getOperatorOrBuilder();
    
    boolean hasPosition();
    
    int getPosition();
    
    boolean hasObject();
    
    Object getObject();
    
    ObjectOrBuilder getObjectOrBuilder();
    
    boolean hasArray();
    
    Array getArray();
    
    ArrayOrBuilder getArrayOrBuilder();
  }
  
  public static final class Expr extends GeneratedMessageV3 implements ExprOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int TYPE_FIELD_NUMBER = 1;
    
    private int type_;
    
    public static final int IDENTIFIER_FIELD_NUMBER = 2;
    
    private ColumnIdentifier identifier_;
    
    public static final int VARIABLE_FIELD_NUMBER = 3;
    
    private volatile Object variable_;
    
    public static final int LITERAL_FIELD_NUMBER = 4;
    
    private MysqlxDatatypes.Scalar literal_;
    
    public static final int FUNCTION_CALL_FIELD_NUMBER = 5;
    
    private FunctionCall functionCall_;
    
    public static final int OPERATOR_FIELD_NUMBER = 6;
    
    private Operator operator_;
    
    public static final int POSITION_FIELD_NUMBER = 7;
    
    private int position_;
    
    public static final int OBJECT_FIELD_NUMBER = 8;
    
    private Object object_;
    
    public static final int ARRAY_FIELD_NUMBER = 9;
    
    private Array array_;
    
    private byte memoizedIsInitialized;
    
    private Expr(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Expr() {
      this.memoizedIsInitialized = -1;
      this.type_ = 1;
      this.variable_ = "";
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Expr();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Expr(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      int mutable_bitField0_ = 0;
      UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int rawValue;
          ColumnIdentifier.Builder builder4;
          ByteString bs;
          MysqlxDatatypes.Scalar.Builder builder3;
          FunctionCall.Builder builder2;
          Operator.Builder builder1;
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
              builder4 = null;
              if ((this.bitField0_ & 0x2) != 0)
                builder4 = this.identifier_.toBuilder(); 
              this.identifier_ = (ColumnIdentifier)input.readMessage(ColumnIdentifier.PARSER, extensionRegistry);
              if (builder4 != null) {
                builder4.mergeFrom(this.identifier_);
                this.identifier_ = builder4.buildPartial();
              } 
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              bs = input.readBytes();
              this.bitField0_ |= 0x4;
              this.variable_ = bs;
              continue;
            case 34:
              builder3 = null;
              if ((this.bitField0_ & 0x8) != 0)
                builder3 = this.literal_.toBuilder(); 
              this.literal_ = (MysqlxDatatypes.Scalar)input.readMessage(MysqlxDatatypes.Scalar.PARSER, extensionRegistry);
              if (builder3 != null) {
                builder3.mergeFrom(this.literal_);
                this.literal_ = builder3.buildPartial();
              } 
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              builder2 = null;
              if ((this.bitField0_ & 0x10) != 0)
                builder2 = this.functionCall_.toBuilder(); 
              this.functionCall_ = (FunctionCall)input.readMessage(FunctionCall.PARSER, extensionRegistry);
              if (builder2 != null) {
                builder2.mergeFrom(this.functionCall_);
                this.functionCall_ = builder2.buildPartial();
              } 
              this.bitField0_ |= 0x10;
              continue;
            case 50:
              builder1 = null;
              if ((this.bitField0_ & 0x20) != 0)
                builder1 = this.operator_.toBuilder(); 
              this.operator_ = (Operator)input.readMessage(Operator.PARSER, extensionRegistry);
              if (builder1 != null) {
                builder1.mergeFrom(this.operator_);
                this.operator_ = builder1.buildPartial();
              } 
              this.bitField0_ |= 0x20;
              continue;
            case 56:
              this.bitField0_ |= 0x40;
              this.position_ = input.readUInt32();
              continue;
            case 66:
              builder = null;
              if ((this.bitField0_ & 0x80) != 0)
                builder = this.object_.toBuilder(); 
              this.object_ = (Object)input.readMessage(Object.PARSER, extensionRegistry);
              if (builder != null) {
                builder.mergeFrom(this.object_);
                this.object_ = builder.buildPartial();
              } 
              this.bitField0_ |= 0x80;
              continue;
            case 74:
              subBuilder = null;
              if ((this.bitField0_ & 0x100) != 0)
                subBuilder = this.array_.toBuilder(); 
              this.array_ = (Array)input.readMessage(Array.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(this.array_);
                this.array_ = subBuilder.buildPartial();
              } 
              this.bitField0_ |= 0x100;
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
      return MysqlxExpr.internal_static_Mysqlx_Expr_Expr_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxExpr.internal_static_Mysqlx_Expr_Expr_fieldAccessorTable.ensureFieldAccessorsInitialized(Expr.class, Builder.class);
    }
    
    public enum Type implements ProtocolMessageEnum {
      IDENT(1),
      LITERAL(2),
      VARIABLE(3),
      FUNC_CALL(4),
      OPERATOR(5),
      PLACEHOLDER(6),
      OBJECT(7),
      ARRAY(8);
      
      public static final int IDENT_VALUE = 1;
      
      public static final int LITERAL_VALUE = 2;
      
      public static final int VARIABLE_VALUE = 3;
      
      public static final int FUNC_CALL_VALUE = 4;
      
      public static final int OPERATOR_VALUE = 5;
      
      public static final int PLACEHOLDER_VALUE = 6;
      
      public static final int OBJECT_VALUE = 7;
      
      public static final int ARRAY_VALUE = 8;
      
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
            return IDENT;
          case 2:
            return LITERAL;
          case 3:
            return VARIABLE;
          case 4:
            return FUNC_CALL;
          case 5:
            return OPERATOR;
          case 6:
            return PLACEHOLDER;
          case 7:
            return OBJECT;
          case 8:
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
        return Expr.getDescriptor().getEnumTypes().get(0);
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
      return (result == null) ? Type.IDENT : result;
    }
    
    public boolean hasIdentifier() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public ColumnIdentifier getIdentifier() {
      return (this.identifier_ == null) ? ColumnIdentifier.getDefaultInstance() : this.identifier_;
    }
    
    public ColumnIdentifierOrBuilder getIdentifierOrBuilder() {
      return (this.identifier_ == null) ? ColumnIdentifier.getDefaultInstance() : this.identifier_;
    }
    
    public boolean hasVariable() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public String getVariable() {
      Object ref = this.variable_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      if (bs.isValidUtf8())
        this.variable_ = s; 
      return s;
    }
    
    public ByteString getVariableBytes() {
      Object ref = this.variable_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.variable_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public boolean hasLiteral() {
      return ((this.bitField0_ & 0x8) != 0);
    }
    
    public MysqlxDatatypes.Scalar getLiteral() {
      return (this.literal_ == null) ? MysqlxDatatypes.Scalar.getDefaultInstance() : this.literal_;
    }
    
    public MysqlxDatatypes.ScalarOrBuilder getLiteralOrBuilder() {
      return (this.literal_ == null) ? MysqlxDatatypes.Scalar.getDefaultInstance() : this.literal_;
    }
    
    public boolean hasFunctionCall() {
      return ((this.bitField0_ & 0x10) != 0);
    }
    
    public FunctionCall getFunctionCall() {
      return (this.functionCall_ == null) ? FunctionCall.getDefaultInstance() : this.functionCall_;
    }
    
    public FunctionCallOrBuilder getFunctionCallOrBuilder() {
      return (this.functionCall_ == null) ? FunctionCall.getDefaultInstance() : this.functionCall_;
    }
    
    public boolean hasOperator() {
      return ((this.bitField0_ & 0x20) != 0);
    }
    
    public Operator getOperator() {
      return (this.operator_ == null) ? Operator.getDefaultInstance() : this.operator_;
    }
    
    public OperatorOrBuilder getOperatorOrBuilder() {
      return (this.operator_ == null) ? Operator.getDefaultInstance() : this.operator_;
    }
    
    public boolean hasPosition() {
      return ((this.bitField0_ & 0x40) != 0);
    }
    
    public int getPosition() {
      return this.position_;
    }
    
    public boolean hasObject() {
      return ((this.bitField0_ & 0x80) != 0);
    }
    
    public Object getObject() {
      return (this.object_ == null) ? Object.getDefaultInstance() : this.object_;
    }
    
    public ObjectOrBuilder getObjectOrBuilder() {
      return (this.object_ == null) ? Object.getDefaultInstance() : this.object_;
    }
    
    public boolean hasArray() {
      return ((this.bitField0_ & 0x100) != 0);
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
      if (hasIdentifier() && 
        !getIdentifier().isInitialized()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      if (hasLiteral() && 
        !getLiteral().isInitialized()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      if (hasFunctionCall() && 
        !getFunctionCall().isInitialized()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      if (hasOperator() && 
        !getOperator().isInitialized()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      if (hasObject() && 
        !getObject().isInitialized()) {
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
        output.writeMessage(2, (MessageLite)getIdentifier()); 
      if ((this.bitField0_ & 0x4) != 0)
        GeneratedMessageV3.writeString(output, 3, this.variable_); 
      if ((this.bitField0_ & 0x8) != 0)
        output.writeMessage(4, (MessageLite)getLiteral()); 
      if ((this.bitField0_ & 0x10) != 0)
        output.writeMessage(5, (MessageLite)getFunctionCall()); 
      if ((this.bitField0_ & 0x20) != 0)
        output.writeMessage(6, (MessageLite)getOperator()); 
      if ((this.bitField0_ & 0x40) != 0)
        output.writeUInt32(7, this.position_); 
      if ((this.bitField0_ & 0x80) != 0)
        output.writeMessage(8, (MessageLite)getObject()); 
      if ((this.bitField0_ & 0x100) != 0)
        output.writeMessage(9, (MessageLite)getArray()); 
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
          CodedOutputStream.computeMessageSize(2, (MessageLite)getIdentifier()); 
      if ((this.bitField0_ & 0x4) != 0)
        size += GeneratedMessageV3.computeStringSize(3, this.variable_); 
      if ((this.bitField0_ & 0x8) != 0)
        size += 
          CodedOutputStream.computeMessageSize(4, (MessageLite)getLiteral()); 
      if ((this.bitField0_ & 0x10) != 0)
        size += 
          CodedOutputStream.computeMessageSize(5, (MessageLite)getFunctionCall()); 
      if ((this.bitField0_ & 0x20) != 0)
        size += 
          CodedOutputStream.computeMessageSize(6, (MessageLite)getOperator()); 
      if ((this.bitField0_ & 0x40) != 0)
        size += 
          CodedOutputStream.computeUInt32Size(7, this.position_); 
      if ((this.bitField0_ & 0x80) != 0)
        size += 
          CodedOutputStream.computeMessageSize(8, (MessageLite)getObject()); 
      if ((this.bitField0_ & 0x100) != 0)
        size += 
          CodedOutputStream.computeMessageSize(9, (MessageLite)getArray()); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Expr))
        return super.equals(obj); 
      Expr other = (Expr)obj;
      if (hasType() != other.hasType())
        return false; 
      if (hasType() && 
        this.type_ != other.type_)
        return false; 
      if (hasIdentifier() != other.hasIdentifier())
        return false; 
      if (hasIdentifier() && 
        
        !getIdentifier().equals(other.getIdentifier()))
        return false; 
      if (hasVariable() != other.hasVariable())
        return false; 
      if (hasVariable() && 
        
        !getVariable().equals(other.getVariable()))
        return false; 
      if (hasLiteral() != other.hasLiteral())
        return false; 
      if (hasLiteral() && 
        
        !getLiteral().equals(other.getLiteral()))
        return false; 
      if (hasFunctionCall() != other.hasFunctionCall())
        return false; 
      if (hasFunctionCall() && 
        
        !getFunctionCall().equals(other.getFunctionCall()))
        return false; 
      if (hasOperator() != other.hasOperator())
        return false; 
      if (hasOperator() && 
        
        !getOperator().equals(other.getOperator()))
        return false; 
      if (hasPosition() != other.hasPosition())
        return false; 
      if (hasPosition() && 
        getPosition() != other
        .getPosition())
        return false; 
      if (hasObject() != other.hasObject())
        return false; 
      if (hasObject() && 
        
        !getObject().equals(other.getObject()))
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
      if (hasIdentifier()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getIdentifier().hashCode();
      } 
      if (hasVariable()) {
        hash = 37 * hash + 3;
        hash = 53 * hash + getVariable().hashCode();
      } 
      if (hasLiteral()) {
        hash = 37 * hash + 4;
        hash = 53 * hash + getLiteral().hashCode();
      } 
      if (hasFunctionCall()) {
        hash = 37 * hash + 5;
        hash = 53 * hash + getFunctionCall().hashCode();
      } 
      if (hasOperator()) {
        hash = 37 * hash + 6;
        hash = 53 * hash + getOperator().hashCode();
      } 
      if (hasPosition()) {
        hash = 37 * hash + 7;
        hash = 53 * hash + getPosition();
      } 
      if (hasObject()) {
        hash = 37 * hash + 8;
        hash = 53 * hash + getObject().hashCode();
      } 
      if (hasArray()) {
        hash = 37 * hash + 9;
        hash = 53 * hash + getArray().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Expr parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Expr)PARSER.parseFrom(data);
    }
    
    public static Expr parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Expr)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Expr parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Expr)PARSER.parseFrom(data);
    }
    
    public static Expr parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Expr)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Expr parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Expr)PARSER.parseFrom(data);
    }
    
    public static Expr parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Expr)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Expr parseFrom(InputStream input) throws IOException {
      return 
        (Expr)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Expr parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Expr)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Expr parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Expr)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Expr parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Expr)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Expr parseFrom(CodedInputStream input) throws IOException {
      return 
        (Expr)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Expr parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Expr)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Expr prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExprOrBuilder {
      private int bitField0_;
      
      private int type_;
      
      private ColumnIdentifier identifier_;
      
      private SingleFieldBuilderV3<ColumnIdentifier, ColumnIdentifier.Builder, ColumnIdentifierOrBuilder> identifierBuilder_;
      
      private Object variable_;
      
      private MysqlxDatatypes.Scalar literal_;
      
      private SingleFieldBuilderV3<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> literalBuilder_;
      
      private FunctionCall functionCall_;
      
      private SingleFieldBuilderV3<FunctionCall, FunctionCall.Builder, FunctionCallOrBuilder> functionCallBuilder_;
      
      private Operator operator_;
      
      private SingleFieldBuilderV3<Operator, Operator.Builder, OperatorOrBuilder> operatorBuilder_;
      
      private int position_;
      
      private Object object_;
      
      private SingleFieldBuilderV3<Object, Object.Builder, ObjectOrBuilder> objectBuilder_;
      
      private Array array_;
      
      private SingleFieldBuilderV3<Array, Array.Builder, ArrayOrBuilder> arrayBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Expr_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Expr_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Expr.class, Builder.class);
      }
      
      private Builder() {
        this.type_ = 1;
        this.variable_ = "";
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.type_ = 1;
        this.variable_ = "";
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Expr.alwaysUseFieldBuilders) {
          getIdentifierFieldBuilder();
          getLiteralFieldBuilder();
          getFunctionCallFieldBuilder();
          getOperatorFieldBuilder();
          getObjectFieldBuilder();
          getArrayFieldBuilder();
        } 
      }
      
      public Builder clear() {
        super.clear();
        this.type_ = 1;
        this.bitField0_ &= 0xFFFFFFFE;
        if (this.identifierBuilder_ == null) {
          this.identifier_ = null;
        } else {
          this.identifierBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFD;
        this.variable_ = "";
        this.bitField0_ &= 0xFFFFFFFB;
        if (this.literalBuilder_ == null) {
          this.literal_ = null;
        } else {
          this.literalBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFF7;
        if (this.functionCallBuilder_ == null) {
          this.functionCall_ = null;
        } else {
          this.functionCallBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFEF;
        if (this.operatorBuilder_ == null) {
          this.operator_ = null;
        } else {
          this.operatorBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFDF;
        this.position_ = 0;
        this.bitField0_ &= 0xFFFFFFBF;
        if (this.objectBuilder_ == null) {
          this.object_ = null;
        } else {
          this.objectBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFF7F;
        if (this.arrayBuilder_ == null) {
          this.array_ = null;
        } else {
          this.arrayBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFEFF;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Expr_descriptor;
      }
      
      public Expr getDefaultInstanceForType() {
        return Expr.getDefaultInstance();
      }
      
      public Expr build() {
        Expr result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Expr buildPartial() {
        Expr result = new Expr(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.type_ = this.type_;
        if ((from_bitField0_ & 0x2) != 0) {
          if (this.identifierBuilder_ == null) {
            result.identifier_ = this.identifier_;
          } else {
            result.identifier_ = (ColumnIdentifier)this.identifierBuilder_.build();
          } 
          to_bitField0_ |= 0x2;
        } 
        if ((from_bitField0_ & 0x4) != 0)
          to_bitField0_ |= 0x4; 
        result.variable_ = this.variable_;
        if ((from_bitField0_ & 0x8) != 0) {
          if (this.literalBuilder_ == null) {
            result.literal_ = this.literal_;
          } else {
            result.literal_ = (MysqlxDatatypes.Scalar)this.literalBuilder_.build();
          } 
          to_bitField0_ |= 0x8;
        } 
        if ((from_bitField0_ & 0x10) != 0) {
          if (this.functionCallBuilder_ == null) {
            result.functionCall_ = this.functionCall_;
          } else {
            result.functionCall_ = (FunctionCall)this.functionCallBuilder_.build();
          } 
          to_bitField0_ |= 0x10;
        } 
        if ((from_bitField0_ & 0x20) != 0) {
          if (this.operatorBuilder_ == null) {
            result.operator_ = this.operator_;
          } else {
            result.operator_ = (Operator)this.operatorBuilder_.build();
          } 
          to_bitField0_ |= 0x20;
        } 
        if ((from_bitField0_ & 0x40) != 0) {
          result.position_ = this.position_;
          to_bitField0_ |= 0x40;
        } 
        if ((from_bitField0_ & 0x80) != 0) {
          if (this.objectBuilder_ == null) {
            result.object_ = this.object_;
          } else {
            result.object_ = (Object)this.objectBuilder_.build();
          } 
          to_bitField0_ |= 0x80;
        } 
        if ((from_bitField0_ & 0x100) != 0) {
          if (this.arrayBuilder_ == null) {
            result.array_ = this.array_;
          } else {
            result.array_ = (Array)this.arrayBuilder_.build();
          } 
          to_bitField0_ |= 0x100;
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
        if (other instanceof Expr)
          return mergeFrom((Expr)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Expr other) {
        if (other == Expr.getDefaultInstance())
          return this; 
        if (other.hasType())
          setType(other.getType()); 
        if (other.hasIdentifier())
          mergeIdentifier(other.getIdentifier()); 
        if (other.hasVariable()) {
          this.bitField0_ |= 0x4;
          this.variable_ = other.variable_;
          onChanged();
        } 
        if (other.hasLiteral())
          mergeLiteral(other.getLiteral()); 
        if (other.hasFunctionCall())
          mergeFunctionCall(other.getFunctionCall()); 
        if (other.hasOperator())
          mergeOperator(other.getOperator()); 
        if (other.hasPosition())
          setPosition(other.getPosition()); 
        if (other.hasObject())
          mergeObject(other.getObject()); 
        if (other.hasArray())
          mergeArray(other.getArray()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasType())
          return false; 
        if (hasIdentifier() && !getIdentifier().isInitialized())
          return false; 
        if (hasLiteral() && !getLiteral().isInitialized())
          return false; 
        if (hasFunctionCall() && !getFunctionCall().isInitialized())
          return false; 
        if (hasOperator() && !getOperator().isInitialized())
          return false; 
        if (hasObject() && !getObject().isInitialized())
          return false; 
        if (hasArray() && !getArray().isInitialized())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Expr parsedMessage = null;
        try {
          parsedMessage = (Expr) Expr.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Expr)e.getUnfinishedMessage();
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
        return (result == null) ? Type.IDENT : result;
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
      
      public boolean hasIdentifier() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public ColumnIdentifier getIdentifier() {
        if (this.identifierBuilder_ == null)
          return (this.identifier_ == null) ? ColumnIdentifier.getDefaultInstance() : this.identifier_;
        return (ColumnIdentifier)this.identifierBuilder_.getMessage();
      }
      
      public Builder setIdentifier(ColumnIdentifier value) {
        if (this.identifierBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.identifier_ = value;
          onChanged();
        } else {
          this.identifierBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder setIdentifier(ColumnIdentifier.Builder builderForValue) {
        if (this.identifierBuilder_ == null) {
          this.identifier_ = builderForValue.build();
          onChanged();
        } else {
          this.identifierBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder mergeIdentifier(ColumnIdentifier value) {
        if (this.identifierBuilder_ == null) {
          if ((this.bitField0_ & 0x2) != 0 && this.identifier_ != null && this.identifier_ != ColumnIdentifier.getDefaultInstance()) {
            this.identifier_ = ColumnIdentifier.newBuilder(this.identifier_).mergeFrom(value).buildPartial();
          } else {
            this.identifier_ = value;
          } 
          onChanged();
        } else {
          this.identifierBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        return this;
      }
      
      public Builder clearIdentifier() {
        if (this.identifierBuilder_ == null) {
          this.identifier_ = null;
          onChanged();
        } else {
          this.identifierBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public ColumnIdentifier.Builder getIdentifierBuilder() {
        this.bitField0_ |= 0x2;
        onChanged();
        return (ColumnIdentifier.Builder)getIdentifierFieldBuilder().getBuilder();
      }
      
      public ColumnIdentifierOrBuilder getIdentifierOrBuilder() {
        if (this.identifierBuilder_ != null)
          return (ColumnIdentifierOrBuilder)this.identifierBuilder_.getMessageOrBuilder();
        return (this.identifier_ == null) ? ColumnIdentifier.getDefaultInstance() : this.identifier_;
      }
      
      private SingleFieldBuilderV3<ColumnIdentifier, ColumnIdentifier.Builder, ColumnIdentifierOrBuilder> getIdentifierFieldBuilder() {
        if (this.identifierBuilder_ == null) {
          this.identifierBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getIdentifier(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.identifier_ = null;
        } 
        return this.identifierBuilder_;
      }
      
      public boolean hasVariable() {
        return ((this.bitField0_ & 0x4) != 0);
      }
      
      public String getVariable() {
        Object ref = this.variable_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8())
            this.variable_ = s; 
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getVariableBytes() {
        Object ref = this.variable_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.variable_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setVariable(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x4;
        this.variable_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearVariable() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.variable_ = Expr.getDefaultInstance().getVariable();
        onChanged();
        return this;
      }
      
      public Builder setVariableBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x4;
        this.variable_ = value;
        onChanged();
        return this;
      }
      
      public boolean hasLiteral() {
        return ((this.bitField0_ & 0x8) != 0);
      }
      
      public MysqlxDatatypes.Scalar getLiteral() {
        if (this.literalBuilder_ == null)
          return (this.literal_ == null) ? MysqlxDatatypes.Scalar.getDefaultInstance() : this.literal_; 
        return (MysqlxDatatypes.Scalar)this.literalBuilder_.getMessage();
      }
      
      public Builder setLiteral(MysqlxDatatypes.Scalar value) {
        if (this.literalBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.literal_ = value;
          onChanged();
        } else {
          this.literalBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x8;
        return this;
      }
      
      public Builder setLiteral(MysqlxDatatypes.Scalar.Builder builderForValue) {
        if (this.literalBuilder_ == null) {
          this.literal_ = builderForValue.build();
          onChanged();
        } else {
          this.literalBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x8;
        return this;
      }
      
      public Builder mergeLiteral(MysqlxDatatypes.Scalar value) {
        if (this.literalBuilder_ == null) {
          if ((this.bitField0_ & 0x8) != 0 && this.literal_ != null && this.literal_ != 
            
            MysqlxDatatypes.Scalar.getDefaultInstance()) {
            this
              .literal_ = MysqlxDatatypes.Scalar.newBuilder(this.literal_).mergeFrom(value).buildPartial();
          } else {
            this.literal_ = value;
          } 
          onChanged();
        } else {
          this.literalBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x8;
        return this;
      }
      
      public Builder clearLiteral() {
        if (this.literalBuilder_ == null) {
          this.literal_ = null;
          onChanged();
        } else {
          this.literalBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
      }
      
      public MysqlxDatatypes.Scalar.Builder getLiteralBuilder() {
        this.bitField0_ |= 0x8;
        onChanged();
        return (MysqlxDatatypes.Scalar.Builder)getLiteralFieldBuilder().getBuilder();
      }
      
      public MysqlxDatatypes.ScalarOrBuilder getLiteralOrBuilder() {
        if (this.literalBuilder_ != null)
          return (MysqlxDatatypes.ScalarOrBuilder)this.literalBuilder_.getMessageOrBuilder(); 
        return (this.literal_ == null) ? 
          MysqlxDatatypes.Scalar.getDefaultInstance() : this.literal_;
      }
      
      private SingleFieldBuilderV3<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> getLiteralFieldBuilder() {
        if (this.literalBuilder_ == null) {
          this
            
            .literalBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getLiteral(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.literal_ = null;
        } 
        return this.literalBuilder_;
      }
      
      public boolean hasFunctionCall() {
        return ((this.bitField0_ & 0x10) != 0);
      }
      
      public FunctionCall getFunctionCall() {
        if (this.functionCallBuilder_ == null)
          return (this.functionCall_ == null) ? FunctionCall.getDefaultInstance() : this.functionCall_;
        return (FunctionCall)this.functionCallBuilder_.getMessage();
      }
      
      public Builder setFunctionCall(FunctionCall value) {
        if (this.functionCallBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.functionCall_ = value;
          onChanged();
        } else {
          this.functionCallBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x10;
        return this;
      }
      
      public Builder setFunctionCall(FunctionCall.Builder builderForValue) {
        if (this.functionCallBuilder_ == null) {
          this.functionCall_ = builderForValue.build();
          onChanged();
        } else {
          this.functionCallBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x10;
        return this;
      }
      
      public Builder mergeFunctionCall(FunctionCall value) {
        if (this.functionCallBuilder_ == null) {
          if ((this.bitField0_ & 0x10) != 0 && this.functionCall_ != null && this.functionCall_ != 
            
            FunctionCall.getDefaultInstance()) {
            this
              .functionCall_ = FunctionCall.newBuilder(this.functionCall_).mergeFrom(value).buildPartial();
          } else {
            this.functionCall_ = value;
          } 
          onChanged();
        } else {
          this.functionCallBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x10;
        return this;
      }
      
      public Builder clearFunctionCall() {
        if (this.functionCallBuilder_ == null) {
          this.functionCall_ = null;
          onChanged();
        } else {
          this.functionCallBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFEF;
        return this;
      }
      
      public FunctionCall.Builder getFunctionCallBuilder() {
        this.bitField0_ |= 0x10;
        onChanged();
        return (FunctionCall.Builder)getFunctionCallFieldBuilder().getBuilder();
      }
      
      public FunctionCallOrBuilder getFunctionCallOrBuilder() {
        if (this.functionCallBuilder_ != null)
          return (FunctionCallOrBuilder)this.functionCallBuilder_.getMessageOrBuilder();
        return (this.functionCall_ == null) ? 
          FunctionCall.getDefaultInstance() : this.functionCall_;
      }
      
      private SingleFieldBuilderV3<FunctionCall, FunctionCall.Builder, FunctionCallOrBuilder> getFunctionCallFieldBuilder() {
        if (this.functionCallBuilder_ == null) {
          this
            
            .functionCallBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getFunctionCall(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.functionCall_ = null;
        } 
        return this.functionCallBuilder_;
      }
      
      public boolean hasOperator() {
        return ((this.bitField0_ & 0x20) != 0);
      }
      
      public Operator getOperator() {
        if (this.operatorBuilder_ == null)
          return (this.operator_ == null) ? Operator.getDefaultInstance() : this.operator_;
        return (Operator)this.operatorBuilder_.getMessage();
      }
      
      public Builder setOperator(Operator value) {
        if (this.operatorBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.operator_ = value;
          onChanged();
        } else {
          this.operatorBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x20;
        return this;
      }
      
      public Builder setOperator(Operator.Builder builderForValue) {
        if (this.operatorBuilder_ == null) {
          this.operator_ = builderForValue.build();
          onChanged();
        } else {
          this.operatorBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x20;
        return this;
      }
      
      public Builder mergeOperator(Operator value) {
        if (this.operatorBuilder_ == null) {
          if ((this.bitField0_ & 0x20) != 0 && this.operator_ != null && this.operator_ != 
            
            Operator.getDefaultInstance()) {
            this
              .operator_ = Operator.newBuilder(this.operator_).mergeFrom(value).buildPartial();
          } else {
            this.operator_ = value;
          } 
          onChanged();
        } else {
          this.operatorBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x20;
        return this;
      }
      
      public Builder clearOperator() {
        if (this.operatorBuilder_ == null) {
          this.operator_ = null;
          onChanged();
        } else {
          this.operatorBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFDF;
        return this;
      }
      
      public Operator.Builder getOperatorBuilder() {
        this.bitField0_ |= 0x20;
        onChanged();
        return (Operator.Builder)getOperatorFieldBuilder().getBuilder();
      }
      
      public OperatorOrBuilder getOperatorOrBuilder() {
        if (this.operatorBuilder_ != null)
          return (OperatorOrBuilder)this.operatorBuilder_.getMessageOrBuilder();
        return (this.operator_ == null) ? 
          Operator.getDefaultInstance() : this.operator_;
      }
      
      private SingleFieldBuilderV3<Operator, Operator.Builder, OperatorOrBuilder> getOperatorFieldBuilder() {
        if (this.operatorBuilder_ == null) {
          this
            
            .operatorBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getOperator(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.operator_ = null;
        } 
        return this.operatorBuilder_;
      }
      
      public boolean hasPosition() {
        return ((this.bitField0_ & 0x40) != 0);
      }
      
      public int getPosition() {
        return this.position_;
      }
      
      public Builder setPosition(int value) {
        this.bitField0_ |= 0x40;
        this.position_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearPosition() {
        this.bitField0_ &= 0xFFFFFFBF;
        this.position_ = 0;
        onChanged();
        return this;
      }
      
      public boolean hasObject() {
        return ((this.bitField0_ & 0x80) != 0);
      }
      
      public Object getObject() {
        if (this.objectBuilder_ == null)
          return (this.object_ == null) ? Object.getDefaultInstance() : this.object_;
        return (Object)this.objectBuilder_.getMessage();
      }
      
      public Builder setObject(Object value) {
        if (this.objectBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.object_ = value;
          onChanged();
        } else {
          this.objectBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x80;
        return this;
      }
      
      public Builder setObject(Object.Builder builderForValue) {
        if (this.objectBuilder_ == null) {
          this.object_ = builderForValue.build();
          onChanged();
        } else {
          this.objectBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x80;
        return this;
      }
      
      public Builder mergeObject(Object value) {
        if (this.objectBuilder_ == null) {
          if ((this.bitField0_ & 0x80) != 0 && this.object_ != null && this.object_ != 
            
            Object.getDefaultInstance()) {
            this
              .object_ = Object.newBuilder(this.object_).mergeFrom(value).buildPartial();
          } else {
            this.object_ = value;
          } 
          onChanged();
        } else {
          this.objectBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x80;
        return this;
      }
      
      public Builder clearObject() {
        if (this.objectBuilder_ == null) {
          this.object_ = null;
          onChanged();
        } else {
          this.objectBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFF7F;
        return this;
      }
      
      public Object.Builder getObjectBuilder() {
        this.bitField0_ |= 0x80;
        onChanged();
        return (Object.Builder)getObjectFieldBuilder().getBuilder();
      }
      
      public ObjectOrBuilder getObjectOrBuilder() {
        if (this.objectBuilder_ != null)
          return (ObjectOrBuilder)this.objectBuilder_.getMessageOrBuilder();
        return (this.object_ == null) ? 
          Object.getDefaultInstance() : this.object_;
      }
      
      private SingleFieldBuilderV3<Object, Object.Builder, ObjectOrBuilder> getObjectFieldBuilder() {
        if (this.objectBuilder_ == null) {
          this
            
            .objectBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getObject(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.object_ = null;
        } 
        return this.objectBuilder_;
      }
      
      public boolean hasArray() {
        return ((this.bitField0_ & 0x100) != 0);
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
        this.bitField0_ |= 0x100;
        return this;
      }
      
      public Builder setArray(Array.Builder builderForValue) {
        if (this.arrayBuilder_ == null) {
          this.array_ = builderForValue.build();
          onChanged();
        } else {
          this.arrayBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x100;
        return this;
      }
      
      public Builder mergeArray(Array value) {
        if (this.arrayBuilder_ == null) {
          if ((this.bitField0_ & 0x100) != 0 && this.array_ != null && this.array_ != 
            
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
        this.bitField0_ |= 0x100;
        return this;
      }
      
      public Builder clearArray() {
        if (this.arrayBuilder_ == null) {
          this.array_ = null;
          onChanged();
        } else {
          this.arrayBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFEFF;
        return this;
      }
      
      public Array.Builder getArrayBuilder() {
        this.bitField0_ |= 0x100;
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
    
    private static final Expr DEFAULT_INSTANCE = new Expr();
    
    public static Expr getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Expr> PARSER = (Parser<Expr>)new AbstractParser<Expr>() {
        public Expr parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Expr(input, extensionRegistry);
        }
      };
    
    public static Parser<Expr> parser() {
      return PARSER;
    }
    
    public Parser<Expr> getParserForType() {
      return PARSER;
    }
    
    public Expr getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface IdentifierOrBuilder extends MessageOrBuilder {
    boolean hasName();
    
    String getName();
    
    ByteString getNameBytes();
    
    boolean hasSchemaName();
    
    String getSchemaName();
    
    ByteString getSchemaNameBytes();
  }
  
  public static final class Identifier extends GeneratedMessageV3 implements IdentifierOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int NAME_FIELD_NUMBER = 1;
    
    private volatile Object name_;
    
    public static final int SCHEMA_NAME_FIELD_NUMBER = 2;
    
    private volatile Object schemaName_;
    
    private byte memoizedIsInitialized;
    
    private Identifier(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Identifier() {
      this.memoizedIsInitialized = -1;
      this.name_ = "";
      this.schemaName_ = "";
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Identifier();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Identifier(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
              this.name_ = bs;
              continue;
            case 18:
              bs = input.readBytes();
              this.bitField0_ |= 0x2;
              this.schemaName_ = bs;
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
      return MysqlxExpr.internal_static_Mysqlx_Expr_Identifier_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxExpr.internal_static_Mysqlx_Expr_Identifier_fieldAccessorTable.ensureFieldAccessorsInitialized(Identifier.class, Builder.class);
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
    
    public boolean hasSchemaName() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public String getSchemaName() {
      Object ref = this.schemaName_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      if (bs.isValidUtf8())
        this.schemaName_ = s; 
      return s;
    }
    
    public ByteString getSchemaNameBytes() {
      Object ref = this.schemaName_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.schemaName_ = b;
        return b;
      } 
      return (ByteString)ref;
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
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        GeneratedMessageV3.writeString(output, 1, this.name_); 
      if ((this.bitField0_ & 0x2) != 0)
        GeneratedMessageV3.writeString(output, 2, this.schemaName_); 
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
        size += GeneratedMessageV3.computeStringSize(2, this.schemaName_); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Identifier))
        return super.equals(obj); 
      Identifier other = (Identifier)obj;
      if (hasName() != other.hasName())
        return false; 
      if (hasName() && 
        
        !getName().equals(other.getName()))
        return false; 
      if (hasSchemaName() != other.hasSchemaName())
        return false; 
      if (hasSchemaName() && 
        
        !getSchemaName().equals(other.getSchemaName()))
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
      if (hasSchemaName()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getSchemaName().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Identifier parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Identifier)PARSER.parseFrom(data);
    }
    
    public static Identifier parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Identifier)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Identifier parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Identifier)PARSER.parseFrom(data);
    }
    
    public static Identifier parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Identifier)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Identifier parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Identifier)PARSER.parseFrom(data);
    }
    
    public static Identifier parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Identifier)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Identifier parseFrom(InputStream input) throws IOException {
      return 
        (Identifier)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Identifier parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Identifier)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Identifier parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Identifier)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Identifier parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Identifier)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Identifier parseFrom(CodedInputStream input) throws IOException {
      return 
        (Identifier)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Identifier parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Identifier)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Identifier prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements IdentifierOrBuilder {
      private int bitField0_;
      
      private Object name_;
      
      private Object schemaName_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Identifier_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Identifier_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Identifier.class, Builder.class);
      }
      
      private Builder() {
        this.name_ = "";
        this.schemaName_ = "";
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.name_ = "";
        this.schemaName_ = "";
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Identifier.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.schemaName_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Identifier_descriptor;
      }
      
      public Identifier getDefaultInstanceForType() {
        return Identifier.getDefaultInstance();
      }
      
      public Identifier build() {
        Identifier result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Identifier buildPartial() {
        Identifier result = new Identifier(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.name_ = this.name_;
        if ((from_bitField0_ & 0x2) != 0)
          to_bitField0_ |= 0x2; 
        result.schemaName_ = this.schemaName_;
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
        if (other instanceof Identifier)
          return mergeFrom((Identifier)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Identifier other) {
        if (other == Identifier.getDefaultInstance())
          return this; 
        if (other.hasName()) {
          this.bitField0_ |= 0x1;
          this.name_ = other.name_;
          onChanged();
        } 
        if (other.hasSchemaName()) {
          this.bitField0_ |= 0x2;
          this.schemaName_ = other.schemaName_;
          onChanged();
        } 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasName())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Identifier parsedMessage = null;
        try {
          parsedMessage = (Identifier) Identifier.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Identifier)e.getUnfinishedMessage();
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
        this.name_ = Identifier.getDefaultInstance().getName();
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
      
      public boolean hasSchemaName() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public String getSchemaName() {
        Object ref = this.schemaName_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8())
            this.schemaName_ = s; 
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getSchemaNameBytes() {
        Object ref = this.schemaName_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.schemaName_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setSchemaName(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.schemaName_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearSchemaName() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.schemaName_ = Identifier.getDefaultInstance().getSchemaName();
        onChanged();
        return this;
      }
      
      public Builder setSchemaNameBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.schemaName_ = value;
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
    
    private static final Identifier DEFAULT_INSTANCE = new Identifier();
    
    public static Identifier getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Identifier> PARSER = (Parser<Identifier>)new AbstractParser<Identifier>() {
        public Identifier parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Identifier(input, extensionRegistry);
        }
      };
    
    public static Parser<Identifier> parser() {
      return PARSER;
    }
    
    public Parser<Identifier> getParserForType() {
      return PARSER;
    }
    
    public Identifier getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface DocumentPathItemOrBuilder extends MessageOrBuilder {
    boolean hasType();
    
    DocumentPathItem.Type getType();
    
    boolean hasValue();
    
    String getValue();
    
    ByteString getValueBytes();
    
    boolean hasIndex();
    
    int getIndex();
  }
  
  public static final class DocumentPathItem extends GeneratedMessageV3 implements DocumentPathItemOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int TYPE_FIELD_NUMBER = 1;
    
    private int type_;
    
    public static final int VALUE_FIELD_NUMBER = 2;
    
    private volatile Object value_;
    
    public static final int INDEX_FIELD_NUMBER = 3;
    
    private int index_;
    
    private byte memoizedIsInitialized;
    
    private DocumentPathItem(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private DocumentPathItem() {
      this.memoizedIsInitialized = -1;
      this.type_ = 1;
      this.value_ = "";
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new DocumentPathItem();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private DocumentPathItem(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      int mutable_bitField0_ = 0;
      UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int rawValue;
          ByteString bs;
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
              bs = input.readBytes();
              this.bitField0_ |= 0x2;
              this.value_ = bs;
              continue;
            case 24:
              this.bitField0_ |= 0x4;
              this.index_ = input.readUInt32();
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
      return MysqlxExpr.internal_static_Mysqlx_Expr_DocumentPathItem_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxExpr.internal_static_Mysqlx_Expr_DocumentPathItem_fieldAccessorTable.ensureFieldAccessorsInitialized(DocumentPathItem.class, Builder.class);
    }
    
    public enum Type implements ProtocolMessageEnum {
      MEMBER(1),
      MEMBER_ASTERISK(2),
      ARRAY_INDEX(3),
      ARRAY_INDEX_ASTERISK(4),
      DOUBLE_ASTERISK(5);
      
      public static final int MEMBER_VALUE = 1;
      
      public static final int MEMBER_ASTERISK_VALUE = 2;
      
      public static final int ARRAY_INDEX_VALUE = 3;
      
      public static final int ARRAY_INDEX_ASTERISK_VALUE = 4;
      
      public static final int DOUBLE_ASTERISK_VALUE = 5;
      
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
            return MEMBER;
          case 2:
            return MEMBER_ASTERISK;
          case 3:
            return ARRAY_INDEX;
          case 4:
            return ARRAY_INDEX_ASTERISK;
          case 5:
            return DOUBLE_ASTERISK;
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
        return DocumentPathItem.getDescriptor().getEnumTypes().get(0);
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
      return (result == null) ? Type.MEMBER : result;
    }
    
    public boolean hasValue() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public String getValue() {
      Object ref = this.value_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      if (bs.isValidUtf8())
        this.value_ = s; 
      return s;
    }
    
    public ByteString getValueBytes() {
      Object ref = this.value_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.value_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public boolean hasIndex() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public int getIndex() {
      return this.index_;
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
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        output.writeEnum(1, this.type_); 
      if ((this.bitField0_ & 0x2) != 0)
        GeneratedMessageV3.writeString(output, 2, this.value_); 
      if ((this.bitField0_ & 0x4) != 0)
        output.writeUInt32(3, this.index_); 
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
        size += GeneratedMessageV3.computeStringSize(2, this.value_); 
      if ((this.bitField0_ & 0x4) != 0)
        size += 
          CodedOutputStream.computeUInt32Size(3, this.index_); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof DocumentPathItem))
        return super.equals(obj); 
      DocumentPathItem other = (DocumentPathItem)obj;
      if (hasType() != other.hasType())
        return false; 
      if (hasType() && 
        this.type_ != other.type_)
        return false; 
      if (hasValue() != other.hasValue())
        return false; 
      if (hasValue() && 
        
        !getValue().equals(other.getValue()))
        return false; 
      if (hasIndex() != other.hasIndex())
        return false; 
      if (hasIndex() && 
        getIndex() != other
        .getIndex())
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
      if (hasValue()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getValue().hashCode();
      } 
      if (hasIndex()) {
        hash = 37 * hash + 3;
        hash = 53 * hash + getIndex();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static DocumentPathItem parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (DocumentPathItem)PARSER.parseFrom(data);
    }
    
    public static DocumentPathItem parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (DocumentPathItem)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static DocumentPathItem parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (DocumentPathItem)PARSER.parseFrom(data);
    }
    
    public static DocumentPathItem parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (DocumentPathItem)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static DocumentPathItem parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (DocumentPathItem)PARSER.parseFrom(data);
    }
    
    public static DocumentPathItem parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (DocumentPathItem)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static DocumentPathItem parseFrom(InputStream input) throws IOException {
      return 
        (DocumentPathItem)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static DocumentPathItem parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (DocumentPathItem)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static DocumentPathItem parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (DocumentPathItem)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static DocumentPathItem parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (DocumentPathItem)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static DocumentPathItem parseFrom(CodedInputStream input) throws IOException {
      return 
        (DocumentPathItem)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static DocumentPathItem parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (DocumentPathItem)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(DocumentPathItem prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DocumentPathItemOrBuilder {
      private int bitField0_;
      
      private int type_;
      
      private Object value_;
      
      private int index_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_DocumentPathItem_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_DocumentPathItem_fieldAccessorTable
          .ensureFieldAccessorsInitialized(DocumentPathItem.class, Builder.class);
      }
      
      private Builder() {
        this.type_ = 1;
        this.value_ = "";
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.type_ = 1;
        this.value_ = "";
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (DocumentPathItem.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        this.type_ = 1;
        this.bitField0_ &= 0xFFFFFFFE;
        this.value_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.index_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_DocumentPathItem_descriptor;
      }
      
      public DocumentPathItem getDefaultInstanceForType() {
        return DocumentPathItem.getDefaultInstance();
      }
      
      public DocumentPathItem build() {
        DocumentPathItem result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public DocumentPathItem buildPartial() {
        DocumentPathItem result = new DocumentPathItem(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.type_ = this.type_;
        if ((from_bitField0_ & 0x2) != 0)
          to_bitField0_ |= 0x2; 
        result.value_ = this.value_;
        if ((from_bitField0_ & 0x4) != 0) {
          result.index_ = this.index_;
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
        if (other instanceof DocumentPathItem)
          return mergeFrom((DocumentPathItem)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(DocumentPathItem other) {
        if (other == DocumentPathItem.getDefaultInstance())
          return this; 
        if (other.hasType())
          setType(other.getType()); 
        if (other.hasValue()) {
          this.bitField0_ |= 0x2;
          this.value_ = other.value_;
          onChanged();
        } 
        if (other.hasIndex())
          setIndex(other.getIndex()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasType())
          return false; 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        DocumentPathItem parsedMessage = null;
        try {
          parsedMessage = (DocumentPathItem) DocumentPathItem.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (DocumentPathItem)e.getUnfinishedMessage();
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
        return (result == null) ? Type.MEMBER : result;
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
      
      public boolean hasValue() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public String getValue() {
        Object ref = this.value_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8())
            this.value_ = s; 
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getValueBytes() {
        Object ref = this.value_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.value_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setValue(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.value_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearValue() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.value_ = DocumentPathItem.getDefaultInstance().getValue();
        onChanged();
        return this;
      }
      
      public Builder setValueBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.value_ = value;
        onChanged();
        return this;
      }
      
      public boolean hasIndex() {
        return ((this.bitField0_ & 0x4) != 0);
      }
      
      public int getIndex() {
        return this.index_;
      }
      
      public Builder setIndex(int value) {
        this.bitField0_ |= 0x4;
        this.index_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearIndex() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.index_ = 0;
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
    
    private static final DocumentPathItem DEFAULT_INSTANCE = new DocumentPathItem();
    
    public static DocumentPathItem getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<DocumentPathItem> PARSER = (Parser<DocumentPathItem>)new AbstractParser<DocumentPathItem>() {
        public DocumentPathItem parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new DocumentPathItem(input, extensionRegistry);
        }
      };
    
    public static Parser<DocumentPathItem> parser() {
      return PARSER;
    }
    
    public Parser<DocumentPathItem> getParserForType() {
      return PARSER;
    }
    
    public DocumentPathItem getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface ColumnIdentifierOrBuilder extends MessageOrBuilder {
    List<DocumentPathItem> getDocumentPathList();
    
    DocumentPathItem getDocumentPath(int param1Int);
    
    int getDocumentPathCount();
    
    List<? extends DocumentPathItemOrBuilder> getDocumentPathOrBuilderList();
    
    DocumentPathItemOrBuilder getDocumentPathOrBuilder(int param1Int);
    
    boolean hasName();
    
    String getName();
    
    ByteString getNameBytes();
    
    boolean hasTableName();
    
    String getTableName();
    
    ByteString getTableNameBytes();
    
    boolean hasSchemaName();
    
    String getSchemaName();
    
    ByteString getSchemaNameBytes();
  }
  
  public static final class ColumnIdentifier extends GeneratedMessageV3 implements ColumnIdentifierOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int DOCUMENT_PATH_FIELD_NUMBER = 1;
    
    private List<DocumentPathItem> documentPath_;
    
    public static final int NAME_FIELD_NUMBER = 2;
    
    private volatile Object name_;
    
    public static final int TABLE_NAME_FIELD_NUMBER = 3;
    
    private volatile Object tableName_;
    
    public static final int SCHEMA_NAME_FIELD_NUMBER = 4;
    
    private volatile Object schemaName_;
    
    private byte memoizedIsInitialized;
    
    private ColumnIdentifier(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private ColumnIdentifier() {
      this.memoizedIsInitialized = -1;
      this.documentPath_ = Collections.emptyList();
      this.name_ = "";
      this.tableName_ = "";
      this.schemaName_ = "";
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new ColumnIdentifier();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private ColumnIdentifier(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
              if ((mutable_bitField0_ & 0x1) == 0) {
                this.documentPath_ = new ArrayList<>();
                mutable_bitField0_ |= 0x1;
              } 
              this.documentPath_.add(input.readMessage(DocumentPathItem.PARSER, extensionRegistry));
              continue;
            case 18:
              bs = input.readBytes();
              this.bitField0_ |= 0x1;
              this.name_ = bs;
              continue;
            case 26:
              bs = input.readBytes();
              this.bitField0_ |= 0x2;
              this.tableName_ = bs;
              continue;
            case 34:
              bs = input.readBytes();
              this.bitField0_ |= 0x4;
              this.schemaName_ = bs;
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
          this.documentPath_ = Collections.unmodifiableList(this.documentPath_); 
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MysqlxExpr.internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxExpr.internal_static_Mysqlx_Expr_ColumnIdentifier_fieldAccessorTable.ensureFieldAccessorsInitialized(ColumnIdentifier.class, Builder.class);
    }
    
    public List<DocumentPathItem> getDocumentPathList() {
      return this.documentPath_;
    }
    
    public List<? extends DocumentPathItemOrBuilder> getDocumentPathOrBuilderList() {
      return (List)this.documentPath_;
    }
    
    public int getDocumentPathCount() {
      return this.documentPath_.size();
    }
    
    public DocumentPathItem getDocumentPath(int index) {
      return this.documentPath_.get(index);
    }
    
    public DocumentPathItemOrBuilder getDocumentPathOrBuilder(int index) {
      return this.documentPath_.get(index);
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
    
    public boolean hasTableName() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public String getTableName() {
      Object ref = this.tableName_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      if (bs.isValidUtf8())
        this.tableName_ = s; 
      return s;
    }
    
    public ByteString getTableNameBytes() {
      Object ref = this.tableName_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.tableName_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public boolean hasSchemaName() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public String getSchemaName() {
      Object ref = this.schemaName_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      if (bs.isValidUtf8())
        this.schemaName_ = s; 
      return s;
    }
    
    public ByteString getSchemaNameBytes() {
      Object ref = this.schemaName_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.schemaName_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      for (int i = 0; i < getDocumentPathCount(); i++) {
        if (!getDocumentPath(i).isInitialized()) {
          this.memoizedIsInitialized = 0;
          return false;
        } 
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      for (int i = 0; i < this.documentPath_.size(); i++)
        output.writeMessage(1, (MessageLite)this.documentPath_.get(i)); 
      if ((this.bitField0_ & 0x1) != 0)
        GeneratedMessageV3.writeString(output, 2, this.name_); 
      if ((this.bitField0_ & 0x2) != 0)
        GeneratedMessageV3.writeString(output, 3, this.tableName_); 
      if ((this.bitField0_ & 0x4) != 0)
        GeneratedMessageV3.writeString(output, 4, this.schemaName_); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      for (int i = 0; i < this.documentPath_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(1, (MessageLite)this.documentPath_.get(i)); 
      if ((this.bitField0_ & 0x1) != 0)
        size += GeneratedMessageV3.computeStringSize(2, this.name_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += GeneratedMessageV3.computeStringSize(3, this.tableName_); 
      if ((this.bitField0_ & 0x4) != 0)
        size += GeneratedMessageV3.computeStringSize(4, this.schemaName_); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof ColumnIdentifier))
        return super.equals(obj); 
      ColumnIdentifier other = (ColumnIdentifier)obj;
      if (!getDocumentPathList().equals(other.getDocumentPathList()))
        return false; 
      if (hasName() != other.hasName())
        return false; 
      if (hasName() && 
        
        !getName().equals(other.getName()))
        return false; 
      if (hasTableName() != other.hasTableName())
        return false; 
      if (hasTableName() && 
        
        !getTableName().equals(other.getTableName()))
        return false; 
      if (hasSchemaName() != other.hasSchemaName())
        return false; 
      if (hasSchemaName() && 
        
        !getSchemaName().equals(other.getSchemaName()))
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
      if (getDocumentPathCount() > 0) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getDocumentPathList().hashCode();
      } 
      if (hasName()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getName().hashCode();
      } 
      if (hasTableName()) {
        hash = 37 * hash + 3;
        hash = 53 * hash + getTableName().hashCode();
      } 
      if (hasSchemaName()) {
        hash = 37 * hash + 4;
        hash = 53 * hash + getSchemaName().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static ColumnIdentifier parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (ColumnIdentifier)PARSER.parseFrom(data);
    }
    
    public static ColumnIdentifier parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (ColumnIdentifier)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static ColumnIdentifier parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (ColumnIdentifier)PARSER.parseFrom(data);
    }
    
    public static ColumnIdentifier parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (ColumnIdentifier)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static ColumnIdentifier parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (ColumnIdentifier)PARSER.parseFrom(data);
    }
    
    public static ColumnIdentifier parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (ColumnIdentifier)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static ColumnIdentifier parseFrom(InputStream input) throws IOException {
      return 
        (ColumnIdentifier)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static ColumnIdentifier parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (ColumnIdentifier)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static ColumnIdentifier parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (ColumnIdentifier)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static ColumnIdentifier parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (ColumnIdentifier)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static ColumnIdentifier parseFrom(CodedInputStream input) throws IOException {
      return 
        (ColumnIdentifier)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static ColumnIdentifier parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (ColumnIdentifier)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(ColumnIdentifier prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ColumnIdentifierOrBuilder {
      private int bitField0_;
      
      private List<DocumentPathItem> documentPath_;
      
      private RepeatedFieldBuilderV3<DocumentPathItem, DocumentPathItem.Builder, DocumentPathItemOrBuilder> documentPathBuilder_;
      
      private Object name_;
      
      private Object tableName_;
      
      private Object schemaName_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_ColumnIdentifier_fieldAccessorTable
          .ensureFieldAccessorsInitialized(ColumnIdentifier.class, Builder.class);
      }
      
      private Builder() {
        this
          .documentPath_ = Collections.emptyList();
        this.name_ = "";
        this.tableName_ = "";
        this.schemaName_ = "";
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.documentPath_ = Collections.emptyList();
        this.name_ = "";
        this.tableName_ = "";
        this.schemaName_ = "";
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (ColumnIdentifier.alwaysUseFieldBuilders)
          getDocumentPathFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        if (this.documentPathBuilder_ == null) {
          this.documentPath_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFE;
        } else {
          this.documentPathBuilder_.clear();
        } 
        this.name_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.tableName_ = "";
        this.bitField0_ &= 0xFFFFFFFB;
        this.schemaName_ = "";
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor;
      }
      
      public ColumnIdentifier getDefaultInstanceForType() {
        return ColumnIdentifier.getDefaultInstance();
      }
      
      public ColumnIdentifier build() {
        ColumnIdentifier result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public ColumnIdentifier buildPartial() {
        ColumnIdentifier result = new ColumnIdentifier(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if (this.documentPathBuilder_ == null) {
          if ((this.bitField0_ & 0x1) != 0) {
            this.documentPath_ = Collections.unmodifiableList(this.documentPath_);
            this.bitField0_ &= 0xFFFFFFFE;
          } 
          result.documentPath_ = this.documentPath_;
        } else {
          result.documentPath_ = this.documentPathBuilder_.build();
        } 
        if ((from_bitField0_ & 0x2) != 0)
          to_bitField0_ |= 0x1; 
        result.name_ = this.name_;
        if ((from_bitField0_ & 0x4) != 0)
          to_bitField0_ |= 0x2; 
        result.tableName_ = this.tableName_;
        if ((from_bitField0_ & 0x8) != 0)
          to_bitField0_ |= 0x4; 
        result.schemaName_ = this.schemaName_;
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
        if (other instanceof ColumnIdentifier)
          return mergeFrom((ColumnIdentifier)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(ColumnIdentifier other) {
        if (other == ColumnIdentifier.getDefaultInstance())
          return this; 
        if (this.documentPathBuilder_ == null) {
          if (!other.documentPath_.isEmpty()) {
            if (this.documentPath_.isEmpty()) {
              this.documentPath_ = other.documentPath_;
              this.bitField0_ &= 0xFFFFFFFE;
            } else {
              ensureDocumentPathIsMutable();
              this.documentPath_.addAll(other.documentPath_);
            } 
            onChanged();
          } 
        } else if (!other.documentPath_.isEmpty()) {
          if (this.documentPathBuilder_.isEmpty()) {
            this.documentPathBuilder_.dispose();
            this.documentPathBuilder_ = null;
            this.documentPath_ = other.documentPath_;
            this.bitField0_ &= 0xFFFFFFFE;
            this.documentPathBuilder_ = ColumnIdentifier.alwaysUseFieldBuilders ? getDocumentPathFieldBuilder() : null;
          } else {
            this.documentPathBuilder_.addAllMessages(other.documentPath_);
          } 
        } 
        if (other.hasName()) {
          this.bitField0_ |= 0x2;
          this.name_ = other.name_;
          onChanged();
        } 
        if (other.hasTableName()) {
          this.bitField0_ |= 0x4;
          this.tableName_ = other.tableName_;
          onChanged();
        } 
        if (other.hasSchemaName()) {
          this.bitField0_ |= 0x8;
          this.schemaName_ = other.schemaName_;
          onChanged();
        } 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        for (int i = 0; i < getDocumentPathCount(); i++) {
          if (!getDocumentPath(i).isInitialized())
            return false; 
        } 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        ColumnIdentifier parsedMessage = null;
        try {
          parsedMessage = (ColumnIdentifier) ColumnIdentifier.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (ColumnIdentifier)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      private void ensureDocumentPathIsMutable() {
        if ((this.bitField0_ & 0x1) == 0) {
          this.documentPath_ = new ArrayList<>(this.documentPath_);
          this.bitField0_ |= 0x1;
        } 
      }
      
      public List<DocumentPathItem> getDocumentPathList() {
        if (this.documentPathBuilder_ == null)
          return Collections.unmodifiableList(this.documentPath_); 
        return this.documentPathBuilder_.getMessageList();
      }
      
      public int getDocumentPathCount() {
        if (this.documentPathBuilder_ == null)
          return this.documentPath_.size(); 
        return this.documentPathBuilder_.getCount();
      }
      
      public DocumentPathItem getDocumentPath(int index) {
        if (this.documentPathBuilder_ == null)
          return this.documentPath_.get(index); 
        return (DocumentPathItem)this.documentPathBuilder_.getMessage(index);
      }
      
      public Builder setDocumentPath(int index, DocumentPathItem value) {
        if (this.documentPathBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureDocumentPathIsMutable();
          this.documentPath_.set(index, value);
          onChanged();
        } else {
          this.documentPathBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setDocumentPath(int index, DocumentPathItem.Builder builderForValue) {
        if (this.documentPathBuilder_ == null) {
          ensureDocumentPathIsMutable();
          this.documentPath_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.documentPathBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addDocumentPath(DocumentPathItem value) {
        if (this.documentPathBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureDocumentPathIsMutable();
          this.documentPath_.add(value);
          onChanged();
        } else {
          this.documentPathBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addDocumentPath(int index, DocumentPathItem value) {
        if (this.documentPathBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureDocumentPathIsMutable();
          this.documentPath_.add(index, value);
          onChanged();
        } else {
          this.documentPathBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addDocumentPath(DocumentPathItem.Builder builderForValue) {
        if (this.documentPathBuilder_ == null) {
          ensureDocumentPathIsMutable();
          this.documentPath_.add(builderForValue.build());
          onChanged();
        } else {
          this.documentPathBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addDocumentPath(int index, DocumentPathItem.Builder builderForValue) {
        if (this.documentPathBuilder_ == null) {
          ensureDocumentPathIsMutable();
          this.documentPath_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.documentPathBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllDocumentPath(Iterable<? extends DocumentPathItem> values) {
        if (this.documentPathBuilder_ == null) {
          ensureDocumentPathIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.documentPath_);
          onChanged();
        } else {
          this.documentPathBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearDocumentPath() {
        if (this.documentPathBuilder_ == null) {
          this.documentPath_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFE;
          onChanged();
        } else {
          this.documentPathBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeDocumentPath(int index) {
        if (this.documentPathBuilder_ == null) {
          ensureDocumentPathIsMutable();
          this.documentPath_.remove(index);
          onChanged();
        } else {
          this.documentPathBuilder_.remove(index);
        } 
        return this;
      }
      
      public DocumentPathItem.Builder getDocumentPathBuilder(int index) {
        return (DocumentPathItem.Builder)getDocumentPathFieldBuilder().getBuilder(index);
      }
      
      public DocumentPathItemOrBuilder getDocumentPathOrBuilder(int index) {
        if (this.documentPathBuilder_ == null)
          return this.documentPath_.get(index); 
        return (DocumentPathItemOrBuilder)this.documentPathBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends DocumentPathItemOrBuilder> getDocumentPathOrBuilderList() {
        if (this.documentPathBuilder_ != null)
          return this.documentPathBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.documentPath_);
      }
      
      public DocumentPathItem.Builder addDocumentPathBuilder() {
        return (DocumentPathItem.Builder)getDocumentPathFieldBuilder().addBuilder((AbstractMessage) DocumentPathItem.getDefaultInstance());
      }
      
      public DocumentPathItem.Builder addDocumentPathBuilder(int index) {
        return (DocumentPathItem.Builder)getDocumentPathFieldBuilder().addBuilder(index, (AbstractMessage) DocumentPathItem.getDefaultInstance());
      }
      
      public List<DocumentPathItem.Builder> getDocumentPathBuilderList() {
        return getDocumentPathFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<DocumentPathItem, DocumentPathItem.Builder, DocumentPathItemOrBuilder> getDocumentPathFieldBuilder() {
        if (this.documentPathBuilder_ == null) {
          this.documentPathBuilder_ = new RepeatedFieldBuilderV3(this.documentPath_, ((this.bitField0_ & 0x1) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.documentPath_ = null;
        } 
        return this.documentPathBuilder_;
      }
      
      public boolean hasName() {
        return ((this.bitField0_ & 0x2) != 0);
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
        this.bitField0_ |= 0x2;
        this.name_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearName() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.name_ = ColumnIdentifier.getDefaultInstance().getName();
        onChanged();
        return this;
      }
      
      public Builder setNameBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.name_ = value;
        onChanged();
        return this;
      }
      
      public boolean hasTableName() {
        return ((this.bitField0_ & 0x4) != 0);
      }
      
      public String getTableName() {
        Object ref = this.tableName_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8())
            this.tableName_ = s; 
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getTableNameBytes() {
        Object ref = this.tableName_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.tableName_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setTableName(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x4;
        this.tableName_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearTableName() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.tableName_ = ColumnIdentifier.getDefaultInstance().getTableName();
        onChanged();
        return this;
      }
      
      public Builder setTableNameBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x4;
        this.tableName_ = value;
        onChanged();
        return this;
      }
      
      public boolean hasSchemaName() {
        return ((this.bitField0_ & 0x8) != 0);
      }
      
      public String getSchemaName() {
        Object ref = this.schemaName_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8())
            this.schemaName_ = s; 
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getSchemaNameBytes() {
        Object ref = this.schemaName_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.schemaName_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setSchemaName(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x8;
        this.schemaName_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearSchemaName() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.schemaName_ = ColumnIdentifier.getDefaultInstance().getSchemaName();
        onChanged();
        return this;
      }
      
      public Builder setSchemaNameBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x8;
        this.schemaName_ = value;
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
    
    private static final ColumnIdentifier DEFAULT_INSTANCE = new ColumnIdentifier();
    
    public static ColumnIdentifier getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<ColumnIdentifier> PARSER = (Parser<ColumnIdentifier>)new AbstractParser<ColumnIdentifier>() {
        public ColumnIdentifier parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new ColumnIdentifier(input, extensionRegistry);
        }
      };
    
    public static Parser<ColumnIdentifier> parser() {
      return PARSER;
    }
    
    public Parser<ColumnIdentifier> getParserForType() {
      return PARSER;
    }
    
    public ColumnIdentifier getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface FunctionCallOrBuilder extends MessageOrBuilder {
    boolean hasName();
    
    Identifier getName();
    
    IdentifierOrBuilder getNameOrBuilder();
    
    List<Expr> getParamList();
    
    Expr getParam(int param1Int);
    
    int getParamCount();
    
    List<? extends ExprOrBuilder> getParamOrBuilderList();
    
    ExprOrBuilder getParamOrBuilder(int param1Int);
  }
  
  public static final class FunctionCall extends GeneratedMessageV3 implements FunctionCallOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int NAME_FIELD_NUMBER = 1;
    
    private Identifier name_;
    
    public static final int PARAM_FIELD_NUMBER = 2;
    
    private List<Expr> param_;
    
    private byte memoizedIsInitialized;
    
    private FunctionCall(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private FunctionCall() {
      this.memoizedIsInitialized = -1;
      this.param_ = Collections.emptyList();
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new FunctionCall();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private FunctionCall(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      int mutable_bitField0_ = 0;
      UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          Identifier.Builder subBuilder;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              subBuilder = null;
              if ((this.bitField0_ & 0x1) != 0)
                subBuilder = this.name_.toBuilder(); 
              this.name_ = (Identifier)input.readMessage(Identifier.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(this.name_);
                this.name_ = subBuilder.buildPartial();
              } 
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              if ((mutable_bitField0_ & 0x2) == 0) {
                this.param_ = new ArrayList<>();
                mutable_bitField0_ |= 0x2;
              } 
              this.param_.add(input.readMessage(Expr.PARSER, extensionRegistry));
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
        if ((mutable_bitField0_ & 0x2) != 0)
          this.param_ = Collections.unmodifiableList(this.param_); 
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MysqlxExpr.internal_static_Mysqlx_Expr_FunctionCall_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxExpr.internal_static_Mysqlx_Expr_FunctionCall_fieldAccessorTable.ensureFieldAccessorsInitialized(FunctionCall.class, Builder.class);
    }
    
    public boolean hasName() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public Identifier getName() {
      return (this.name_ == null) ? Identifier.getDefaultInstance() : this.name_;
    }
    
    public IdentifierOrBuilder getNameOrBuilder() {
      return (this.name_ == null) ? Identifier.getDefaultInstance() : this.name_;
    }
    
    public List<Expr> getParamList() {
      return this.param_;
    }
    
    public List<? extends ExprOrBuilder> getParamOrBuilderList() {
      return (List)this.param_;
    }
    
    public int getParamCount() {
      return this.param_.size();
    }
    
    public Expr getParam(int index) {
      return this.param_.get(index);
    }
    
    public ExprOrBuilder getParamOrBuilder(int index) {
      return this.param_.get(index);
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
      if (!getName().isInitialized()) {
        this.memoizedIsInitialized = 0;
        return false;
      } 
      for (int i = 0; i < getParamCount(); i++) {
        if (!getParam(i).isInitialized()) {
          this.memoizedIsInitialized = 0;
          return false;
        } 
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        output.writeMessage(1, (MessageLite)getName()); 
      for (int i = 0; i < this.param_.size(); i++)
        output.writeMessage(2, (MessageLite)this.param_.get(i)); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeMessageSize(1, (MessageLite)getName()); 
      for (int i = 0; i < this.param_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(2, (MessageLite)this.param_.get(i)); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof FunctionCall))
        return super.equals(obj); 
      FunctionCall other = (FunctionCall)obj;
      if (hasName() != other.hasName())
        return false; 
      if (hasName() && 
        
        !getName().equals(other.getName()))
        return false; 
      if (!getParamList().equals(other.getParamList()))
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
      if (getParamCount() > 0) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getParamList().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static FunctionCall parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (FunctionCall)PARSER.parseFrom(data);
    }
    
    public static FunctionCall parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (FunctionCall)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static FunctionCall parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (FunctionCall)PARSER.parseFrom(data);
    }
    
    public static FunctionCall parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (FunctionCall)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static FunctionCall parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (FunctionCall)PARSER.parseFrom(data);
    }
    
    public static FunctionCall parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (FunctionCall)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static FunctionCall parseFrom(InputStream input) throws IOException {
      return 
        (FunctionCall)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static FunctionCall parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (FunctionCall)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static FunctionCall parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (FunctionCall)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static FunctionCall parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (FunctionCall)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static FunctionCall parseFrom(CodedInputStream input) throws IOException {
      return 
        (FunctionCall)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static FunctionCall parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (FunctionCall)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(FunctionCall prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FunctionCallOrBuilder {
      private int bitField0_;
      
      private Identifier name_;
      
      private SingleFieldBuilderV3<Identifier, Identifier.Builder, IdentifierOrBuilder> nameBuilder_;
      
      private List<Expr> param_;
      
      private RepeatedFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> paramBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_FunctionCall_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_FunctionCall_fieldAccessorTable
          .ensureFieldAccessorsInitialized(FunctionCall.class, Builder.class);
      }
      
      private Builder() {
        this
          .param_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.param_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (FunctionCall.alwaysUseFieldBuilders) {
          getNameFieldBuilder();
          getParamFieldBuilder();
        } 
      }
      
      public Builder clear() {
        super.clear();
        if (this.nameBuilder_ == null) {
          this.name_ = null;
        } else {
          this.nameBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFE;
        if (this.paramBuilder_ == null) {
          this.param_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFD;
        } else {
          this.paramBuilder_.clear();
        } 
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_FunctionCall_descriptor;
      }
      
      public FunctionCall getDefaultInstanceForType() {
        return FunctionCall.getDefaultInstance();
      }
      
      public FunctionCall build() {
        FunctionCall result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public FunctionCall buildPartial() {
        FunctionCall result = new FunctionCall(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0) {
          if (this.nameBuilder_ == null) {
            result.name_ = this.name_;
          } else {
            result.name_ = (Identifier)this.nameBuilder_.build();
          } 
          to_bitField0_ |= 0x1;
        } 
        if (this.paramBuilder_ == null) {
          if ((this.bitField0_ & 0x2) != 0) {
            this.param_ = Collections.unmodifiableList(this.param_);
            this.bitField0_ &= 0xFFFFFFFD;
          } 
          result.param_ = this.param_;
        } else {
          result.param_ = this.paramBuilder_.build();
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
        if (other instanceof FunctionCall)
          return mergeFrom((FunctionCall)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(FunctionCall other) {
        if (other == FunctionCall.getDefaultInstance())
          return this; 
        if (other.hasName())
          mergeName(other.getName()); 
        if (this.paramBuilder_ == null) {
          if (!other.param_.isEmpty()) {
            if (this.param_.isEmpty()) {
              this.param_ = other.param_;
              this.bitField0_ &= 0xFFFFFFFD;
            } else {
              ensureParamIsMutable();
              this.param_.addAll(other.param_);
            } 
            onChanged();
          } 
        } else if (!other.param_.isEmpty()) {
          if (this.paramBuilder_.isEmpty()) {
            this.paramBuilder_.dispose();
            this.paramBuilder_ = null;
            this.param_ = other.param_;
            this.bitField0_ &= 0xFFFFFFFD;
            this.paramBuilder_ = FunctionCall.alwaysUseFieldBuilders ? getParamFieldBuilder() : null;
          } else {
            this.paramBuilder_.addAllMessages(other.param_);
          } 
        } 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasName())
          return false; 
        if (!getName().isInitialized())
          return false; 
        for (int i = 0; i < getParamCount(); i++) {
          if (!getParam(i).isInitialized())
            return false; 
        } 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        FunctionCall parsedMessage = null;
        try {
          parsedMessage = (FunctionCall) FunctionCall.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (FunctionCall)e.getUnfinishedMessage();
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
      
      public Identifier getName() {
        if (this.nameBuilder_ == null)
          return (this.name_ == null) ? Identifier.getDefaultInstance() : this.name_;
        return (Identifier)this.nameBuilder_.getMessage();
      }
      
      public Builder setName(Identifier value) {
        if (this.nameBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.name_ = value;
          onChanged();
        } else {
          this.nameBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x1;
        return this;
      }
      
      public Builder setName(Identifier.Builder builderForValue) {
        if (this.nameBuilder_ == null) {
          this.name_ = builderForValue.build();
          onChanged();
        } else {
          this.nameBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x1;
        return this;
      }
      
      public Builder mergeName(Identifier value) {
        if (this.nameBuilder_ == null) {
          if ((this.bitField0_ & 0x1) != 0 && this.name_ != null && this.name_ != Identifier.getDefaultInstance()) {
            this.name_ = Identifier.newBuilder(this.name_).mergeFrom(value).buildPartial();
          } else {
            this.name_ = value;
          } 
          onChanged();
        } else {
          this.nameBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x1;
        return this;
      }
      
      public Builder clearName() {
        if (this.nameBuilder_ == null) {
          this.name_ = null;
          onChanged();
        } else {
          this.nameBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
      }
      
      public Identifier.Builder getNameBuilder() {
        this.bitField0_ |= 0x1;
        onChanged();
        return (Identifier.Builder)getNameFieldBuilder().getBuilder();
      }
      
      public IdentifierOrBuilder getNameOrBuilder() {
        if (this.nameBuilder_ != null)
          return (IdentifierOrBuilder)this.nameBuilder_.getMessageOrBuilder();
        return (this.name_ == null) ? Identifier.getDefaultInstance() : this.name_;
      }
      
      private SingleFieldBuilderV3<Identifier, Identifier.Builder, IdentifierOrBuilder> getNameFieldBuilder() {
        if (this.nameBuilder_ == null) {
          this.nameBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getName(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.name_ = null;
        } 
        return this.nameBuilder_;
      }
      
      private void ensureParamIsMutable() {
        if ((this.bitField0_ & 0x2) == 0) {
          this.param_ = new ArrayList<>(this.param_);
          this.bitField0_ |= 0x2;
        } 
      }
      
      public List<Expr> getParamList() {
        if (this.paramBuilder_ == null)
          return Collections.unmodifiableList(this.param_); 
        return this.paramBuilder_.getMessageList();
      }
      
      public int getParamCount() {
        if (this.paramBuilder_ == null)
          return this.param_.size(); 
        return this.paramBuilder_.getCount();
      }
      
      public Expr getParam(int index) {
        if (this.paramBuilder_ == null)
          return this.param_.get(index); 
        return (Expr)this.paramBuilder_.getMessage(index);
      }
      
      public Builder setParam(int index, Expr value) {
        if (this.paramBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureParamIsMutable();
          this.param_.set(index, value);
          onChanged();
        } else {
          this.paramBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setParam(int index, Expr.Builder builderForValue) {
        if (this.paramBuilder_ == null) {
          ensureParamIsMutable();
          this.param_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.paramBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addParam(Expr value) {
        if (this.paramBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureParamIsMutable();
          this.param_.add(value);
          onChanged();
        } else {
          this.paramBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addParam(int index, Expr value) {
        if (this.paramBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureParamIsMutable();
          this.param_.add(index, value);
          onChanged();
        } else {
          this.paramBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addParam(Expr.Builder builderForValue) {
        if (this.paramBuilder_ == null) {
          ensureParamIsMutable();
          this.param_.add(builderForValue.build());
          onChanged();
        } else {
          this.paramBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addParam(int index, Expr.Builder builderForValue) {
        if (this.paramBuilder_ == null) {
          ensureParamIsMutable();
          this.param_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.paramBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllParam(Iterable<? extends Expr> values) {
        if (this.paramBuilder_ == null) {
          ensureParamIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.param_);
          onChanged();
        } else {
          this.paramBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearParam() {
        if (this.paramBuilder_ == null) {
          this.param_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFD;
          onChanged();
        } else {
          this.paramBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeParam(int index) {
        if (this.paramBuilder_ == null) {
          ensureParamIsMutable();
          this.param_.remove(index);
          onChanged();
        } else {
          this.paramBuilder_.remove(index);
        } 
        return this;
      }
      
      public Expr.Builder getParamBuilder(int index) {
        return (Expr.Builder)getParamFieldBuilder().getBuilder(index);
      }
      
      public ExprOrBuilder getParamOrBuilder(int index) {
        if (this.paramBuilder_ == null)
          return this.param_.get(index); 
        return (ExprOrBuilder)this.paramBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends ExprOrBuilder> getParamOrBuilderList() {
        if (this.paramBuilder_ != null)
          return this.paramBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.param_);
      }
      
      public Expr.Builder addParamBuilder() {
        return (Expr.Builder)getParamFieldBuilder().addBuilder(
            (AbstractMessage) Expr.getDefaultInstance());
      }
      
      public Expr.Builder addParamBuilder(int index) {
        return (Expr.Builder)getParamFieldBuilder().addBuilder(index,
            (AbstractMessage) Expr.getDefaultInstance());
      }
      
      public List<Expr.Builder> getParamBuilderList() {
        return getParamFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> getParamFieldBuilder() {
        if (this.paramBuilder_ == null) {
          this
            
            .paramBuilder_ = new RepeatedFieldBuilderV3(this.param_, ((this.bitField0_ & 0x2) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.param_ = null;
        } 
        return this.paramBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final FunctionCall DEFAULT_INSTANCE = new FunctionCall();
    
    public static FunctionCall getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<FunctionCall> PARSER = (Parser<FunctionCall>)new AbstractParser<FunctionCall>() {
        public FunctionCall parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new FunctionCall(input, extensionRegistry);
        }
      };
    
    public static Parser<FunctionCall> parser() {
      return PARSER;
    }
    
    public Parser<FunctionCall> getParserForType() {
      return PARSER;
    }
    
    public FunctionCall getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface OperatorOrBuilder extends MessageOrBuilder {
    boolean hasName();
    
    String getName();
    
    ByteString getNameBytes();
    
    List<Expr> getParamList();
    
    Expr getParam(int param1Int);
    
    int getParamCount();
    
    List<? extends ExprOrBuilder> getParamOrBuilderList();
    
    ExprOrBuilder getParamOrBuilder(int param1Int);
  }
  
  public static final class Operator extends GeneratedMessageV3 implements OperatorOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int NAME_FIELD_NUMBER = 1;
    
    private volatile Object name_;
    
    public static final int PARAM_FIELD_NUMBER = 2;
    
    private List<Expr> param_;
    
    private byte memoizedIsInitialized;
    
    private Operator(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Operator() {
      this.memoizedIsInitialized = -1;
      this.name_ = "";
      this.param_ = Collections.emptyList();
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Operator();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Operator(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
              this.name_ = bs;
              continue;
            case 18:
              if ((mutable_bitField0_ & 0x2) == 0) {
                this.param_ = new ArrayList<>();
                mutable_bitField0_ |= 0x2;
              } 
              this.param_.add(input.readMessage(Expr.PARSER, extensionRegistry));
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
        if ((mutable_bitField0_ & 0x2) != 0)
          this.param_ = Collections.unmodifiableList(this.param_); 
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MysqlxExpr.internal_static_Mysqlx_Expr_Operator_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxExpr.internal_static_Mysqlx_Expr_Operator_fieldAccessorTable.ensureFieldAccessorsInitialized(Operator.class, Builder.class);
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
    
    public List<Expr> getParamList() {
      return this.param_;
    }
    
    public List<? extends ExprOrBuilder> getParamOrBuilderList() {
      return (List)this.param_;
    }
    
    public int getParamCount() {
      return this.param_.size();
    }
    
    public Expr getParam(int index) {
      return this.param_.get(index);
    }
    
    public ExprOrBuilder getParamOrBuilder(int index) {
      return this.param_.get(index);
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
      for (int i = 0; i < getParamCount(); i++) {
        if (!getParam(i).isInitialized()) {
          this.memoizedIsInitialized = 0;
          return false;
        } 
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        GeneratedMessageV3.writeString(output, 1, this.name_); 
      for (int i = 0; i < this.param_.size(); i++)
        output.writeMessage(2, (MessageLite)this.param_.get(i)); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += GeneratedMessageV3.computeStringSize(1, this.name_); 
      for (int i = 0; i < this.param_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(2, (MessageLite)this.param_.get(i)); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Operator))
        return super.equals(obj); 
      Operator other = (Operator)obj;
      if (hasName() != other.hasName())
        return false; 
      if (hasName() && 
        
        !getName().equals(other.getName()))
        return false; 
      if (!getParamList().equals(other.getParamList()))
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
      if (getParamCount() > 0) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getParamList().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Operator parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Operator)PARSER.parseFrom(data);
    }
    
    public static Operator parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Operator)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Operator parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Operator)PARSER.parseFrom(data);
    }
    
    public static Operator parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Operator)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Operator parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Operator)PARSER.parseFrom(data);
    }
    
    public static Operator parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Operator)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Operator parseFrom(InputStream input) throws IOException {
      return 
        (Operator)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Operator parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Operator)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Operator parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Operator)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Operator parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Operator)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Operator parseFrom(CodedInputStream input) throws IOException {
      return 
        (Operator)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Operator parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Operator)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Operator prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OperatorOrBuilder {
      private int bitField0_;
      
      private Object name_;
      
      private List<Expr> param_;
      
      private RepeatedFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> paramBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Operator_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Operator_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Operator.class, Builder.class);
      }
      
      private Builder() {
        this.name_ = "";
        this
          .param_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.name_ = "";
        this.param_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Operator.alwaysUseFieldBuilders)
          getParamFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        if (this.paramBuilder_ == null) {
          this.param_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFD;
        } else {
          this.paramBuilder_.clear();
        } 
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Operator_descriptor;
      }
      
      public Operator getDefaultInstanceForType() {
        return Operator.getDefaultInstance();
      }
      
      public Operator build() {
        Operator result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Operator buildPartial() {
        Operator result = new Operator(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.name_ = this.name_;
        if (this.paramBuilder_ == null) {
          if ((this.bitField0_ & 0x2) != 0) {
            this.param_ = Collections.unmodifiableList(this.param_);
            this.bitField0_ &= 0xFFFFFFFD;
          } 
          result.param_ = this.param_;
        } else {
          result.param_ = this.paramBuilder_.build();
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
        if (other instanceof Operator)
          return mergeFrom((Operator)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Operator other) {
        if (other == Operator.getDefaultInstance())
          return this; 
        if (other.hasName()) {
          this.bitField0_ |= 0x1;
          this.name_ = other.name_;
          onChanged();
        } 
        if (this.paramBuilder_ == null) {
          if (!other.param_.isEmpty()) {
            if (this.param_.isEmpty()) {
              this.param_ = other.param_;
              this.bitField0_ &= 0xFFFFFFFD;
            } else {
              ensureParamIsMutable();
              this.param_.addAll(other.param_);
            } 
            onChanged();
          } 
        } else if (!other.param_.isEmpty()) {
          if (this.paramBuilder_.isEmpty()) {
            this.paramBuilder_.dispose();
            this.paramBuilder_ = null;
            this.param_ = other.param_;
            this.bitField0_ &= 0xFFFFFFFD;
            this.paramBuilder_ = Operator.alwaysUseFieldBuilders ? getParamFieldBuilder() : null;
          } else {
            this.paramBuilder_.addAllMessages(other.param_);
          } 
        } 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasName())
          return false; 
        for (int i = 0; i < getParamCount(); i++) {
          if (!getParam(i).isInitialized())
            return false; 
        } 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Operator parsedMessage = null;
        try {
          parsedMessage = (Operator) Operator.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Operator)e.getUnfinishedMessage();
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
        this.name_ = Operator.getDefaultInstance().getName();
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
      
      private void ensureParamIsMutable() {
        if ((this.bitField0_ & 0x2) == 0) {
          this.param_ = new ArrayList<>(this.param_);
          this.bitField0_ |= 0x2;
        } 
      }
      
      public List<Expr> getParamList() {
        if (this.paramBuilder_ == null)
          return Collections.unmodifiableList(this.param_); 
        return this.paramBuilder_.getMessageList();
      }
      
      public int getParamCount() {
        if (this.paramBuilder_ == null)
          return this.param_.size(); 
        return this.paramBuilder_.getCount();
      }
      
      public Expr getParam(int index) {
        if (this.paramBuilder_ == null)
          return this.param_.get(index); 
        return (Expr)this.paramBuilder_.getMessage(index);
      }
      
      public Builder setParam(int index, Expr value) {
        if (this.paramBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureParamIsMutable();
          this.param_.set(index, value);
          onChanged();
        } else {
          this.paramBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setParam(int index, Expr.Builder builderForValue) {
        if (this.paramBuilder_ == null) {
          ensureParamIsMutable();
          this.param_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.paramBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addParam(Expr value) {
        if (this.paramBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureParamIsMutable();
          this.param_.add(value);
          onChanged();
        } else {
          this.paramBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addParam(int index, Expr value) {
        if (this.paramBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureParamIsMutable();
          this.param_.add(index, value);
          onChanged();
        } else {
          this.paramBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addParam(Expr.Builder builderForValue) {
        if (this.paramBuilder_ == null) {
          ensureParamIsMutable();
          this.param_.add(builderForValue.build());
          onChanged();
        } else {
          this.paramBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addParam(int index, Expr.Builder builderForValue) {
        if (this.paramBuilder_ == null) {
          ensureParamIsMutable();
          this.param_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.paramBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllParam(Iterable<? extends Expr> values) {
        if (this.paramBuilder_ == null) {
          ensureParamIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.param_);
          onChanged();
        } else {
          this.paramBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearParam() {
        if (this.paramBuilder_ == null) {
          this.param_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFD;
          onChanged();
        } else {
          this.paramBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeParam(int index) {
        if (this.paramBuilder_ == null) {
          ensureParamIsMutable();
          this.param_.remove(index);
          onChanged();
        } else {
          this.paramBuilder_.remove(index);
        } 
        return this;
      }
      
      public Expr.Builder getParamBuilder(int index) {
        return (Expr.Builder)getParamFieldBuilder().getBuilder(index);
      }
      
      public ExprOrBuilder getParamOrBuilder(int index) {
        if (this.paramBuilder_ == null)
          return this.param_.get(index); 
        return (ExprOrBuilder)this.paramBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends ExprOrBuilder> getParamOrBuilderList() {
        if (this.paramBuilder_ != null)
          return this.paramBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.param_);
      }
      
      public Expr.Builder addParamBuilder() {
        return (Expr.Builder)getParamFieldBuilder().addBuilder(
            (AbstractMessage) Expr.getDefaultInstance());
      }
      
      public Expr.Builder addParamBuilder(int index) {
        return (Expr.Builder)getParamFieldBuilder().addBuilder(index,
            (AbstractMessage) Expr.getDefaultInstance());
      }
      
      public List<Expr.Builder> getParamBuilderList() {
        return getParamFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> getParamFieldBuilder() {
        if (this.paramBuilder_ == null) {
          this
            
            .paramBuilder_ = new RepeatedFieldBuilderV3(this.param_, ((this.bitField0_ & 0x2) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.param_ = null;
        } 
        return this.paramBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final Operator DEFAULT_INSTANCE = new Operator();
    
    public static Operator getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Operator> PARSER = (Parser<Operator>)new AbstractParser<Operator>() {
        public Operator parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Operator(input, extensionRegistry);
        }
      };
    
    public static Parser<Operator> parser() {
      return PARSER;
    }
    
    public Parser<Operator> getParserForType() {
      return PARSER;
    }
    
    public Operator getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
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
      return MysqlxExpr.internal_static_Mysqlx_Expr_Object_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxExpr.internal_static_Mysqlx_Expr_Object_fieldAccessorTable.ensureFieldAccessorsInitialized(Object.class, Builder.class);
    }
    
    public static final class ObjectField extends GeneratedMessageV3 implements ObjectFieldOrBuilder {
      private static final long serialVersionUID = 0L;
      
      private int bitField0_;
      
      public static final int KEY_FIELD_NUMBER = 1;
      
      private volatile Object key_;
      
      public static final int VALUE_FIELD_NUMBER = 2;
      
      private Expr value_;
      
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
            Expr.Builder subBuilder;
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
                this.value_ = (Expr)input.readMessage(Expr.PARSER, extensionRegistry);
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
        return MysqlxExpr.internal_static_Mysqlx_Expr_Object_ObjectField_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Object_ObjectField_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectField.class, Builder.class);
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
      
      public Expr getValue() {
        return (this.value_ == null) ? Expr.getDefaultInstance() : this.value_;
      }
      
      public ExprOrBuilder getValueOrBuilder() {
        return (this.value_ == null) ? Expr.getDefaultInstance() : this.value_;
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
        
        private Expr value_;
        
        private SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> valueBuilder_;
        
        public static final Descriptors.Descriptor getDescriptor() {
          return MysqlxExpr.internal_static_Mysqlx_Expr_Object_ObjectField_descriptor;
        }
        
        protected FieldAccessorTable internalGetFieldAccessorTable() {
          return MysqlxExpr.internal_static_Mysqlx_Expr_Object_ObjectField_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectField.class, Builder.class);
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
          return MysqlxExpr.internal_static_Mysqlx_Expr_Object_ObjectField_descriptor;
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
              result.value_ = (Expr)this.valueBuilder_.build();
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
        
        public Expr getValue() {
          if (this.valueBuilder_ == null)
            return (this.value_ == null) ? Expr.getDefaultInstance() : this.value_;
          return (Expr)this.valueBuilder_.getMessage();
        }
        
        public Builder setValue(Expr value) {
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
        
        public Builder setValue(Expr.Builder builderForValue) {
          if (this.valueBuilder_ == null) {
            this.value_ = builderForValue.build();
            onChanged();
          } else {
            this.valueBuilder_.setMessage((AbstractMessage)builderForValue.build());
          } 
          this.bitField0_ |= 0x2;
          return this;
        }
        
        public Builder mergeValue(Expr value) {
          if (this.valueBuilder_ == null) {
            if ((this.bitField0_ & 0x2) != 0 && this.value_ != null && this.value_ != Expr.getDefaultInstance()) {
              this.value_ = Expr.newBuilder(this.value_).mergeFrom(value).buildPartial();
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
        
        public Expr.Builder getValueBuilder() {
          this.bitField0_ |= 0x2;
          onChanged();
          return (Expr.Builder)getValueFieldBuilder().getBuilder();
        }
        
        public ExprOrBuilder getValueOrBuilder() {
          if (this.valueBuilder_ != null)
            return (ExprOrBuilder)this.valueBuilder_.getMessageOrBuilder();
          return (this.value_ == null) ? Expr.getDefaultInstance() : this.value_;
        }
        
        private SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> getValueFieldBuilder() {
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
        return MysqlxExpr.internal_static_Mysqlx_Expr_Object_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Object_fieldAccessorTable
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
        return MysqlxExpr.internal_static_Mysqlx_Expr_Object_descriptor;
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
      
      Expr getValue();
      
      ExprOrBuilder getValueOrBuilder();
    }
  }
  
  public static interface ArrayOrBuilder extends MessageOrBuilder {
    List<Expr> getValueList();
    
    Expr getValue(int param1Int);
    
    int getValueCount();
    
    List<? extends ExprOrBuilder> getValueOrBuilderList();
    
    ExprOrBuilder getValueOrBuilder(int param1Int);
  }
  
  public static final class Array extends GeneratedMessageV3 implements ArrayOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int VALUE_FIELD_NUMBER = 1;
    
    private List<Expr> value_;
    
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
              this.value_.add(input.readMessage(Expr.PARSER, extensionRegistry));
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
      return MysqlxExpr.internal_static_Mysqlx_Expr_Array_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return MysqlxExpr.internal_static_Mysqlx_Expr_Array_fieldAccessorTable.ensureFieldAccessorsInitialized(Array.class, Builder.class);
    }
    
    public List<Expr> getValueList() {
      return this.value_;
    }
    
    public List<? extends ExprOrBuilder> getValueOrBuilderList() {
      return (List)this.value_;
    }
    
    public int getValueCount() {
      return this.value_.size();
    }
    
    public Expr getValue(int index) {
      return this.value_.get(index);
    }
    
    public ExprOrBuilder getValueOrBuilder(int index) {
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
      
      private List<Expr> value_;
      
      private RepeatedFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> valueBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Array_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return MysqlxExpr.internal_static_Mysqlx_Expr_Array_fieldAccessorTable
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
        return MysqlxExpr.internal_static_Mysqlx_Expr_Array_descriptor;
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
      
      public List<Expr> getValueList() {
        if (this.valueBuilder_ == null)
          return Collections.unmodifiableList(this.value_); 
        return this.valueBuilder_.getMessageList();
      }
      
      public int getValueCount() {
        if (this.valueBuilder_ == null)
          return this.value_.size(); 
        return this.valueBuilder_.getCount();
      }
      
      public Expr getValue(int index) {
        if (this.valueBuilder_ == null)
          return this.value_.get(index); 
        return (Expr)this.valueBuilder_.getMessage(index);
      }
      
      public Builder setValue(int index, Expr value) {
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
      
      public Builder setValue(int index, Expr.Builder builderForValue) {
        if (this.valueBuilder_ == null) {
          ensureValueIsMutable();
          this.value_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.valueBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addValue(Expr value) {
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
      
      public Builder addValue(int index, Expr value) {
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
      
      public Builder addValue(Expr.Builder builderForValue) {
        if (this.valueBuilder_ == null) {
          ensureValueIsMutable();
          this.value_.add(builderForValue.build());
          onChanged();
        } else {
          this.valueBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addValue(int index, Expr.Builder builderForValue) {
        if (this.valueBuilder_ == null) {
          ensureValueIsMutable();
          this.value_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.valueBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllValue(Iterable<? extends Expr> values) {
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
      
      public Expr.Builder getValueBuilder(int index) {
        return (Expr.Builder)getValueFieldBuilder().getBuilder(index);
      }
      
      public ExprOrBuilder getValueOrBuilder(int index) {
        if (this.valueBuilder_ == null)
          return this.value_.get(index); 
        return (ExprOrBuilder)this.valueBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends ExprOrBuilder> getValueOrBuilderList() {
        if (this.valueBuilder_ != null)
          return this.valueBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.value_);
      }
      
      public Expr.Builder addValueBuilder() {
        return (Expr.Builder)getValueFieldBuilder().addBuilder(
            (AbstractMessage) Expr.getDefaultInstance());
      }
      
      public Expr.Builder addValueBuilder(int index) {
        return (Expr.Builder)getValueFieldBuilder().addBuilder(index,
            (AbstractMessage) Expr.getDefaultInstance());
      }
      
      public List<Expr.Builder> getValueBuilderList() {
        return getValueFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> getValueFieldBuilder() {
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
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\021mysqlx_expr.proto\022\013Mysqlx.Expr\032\026mysqlx_datatypes.proto\"Ä\003\n\004Expr\022$\n\004type\030\001 \002(\0162\026.Mysqlx.Expr.Expr.Type\0221\n\nidentifier\030\002 \001(\0132\035.Mysqlx.Expr.ColumnIdentifier\022\020\n\bvariable\030\003 \001(\t\022)\n\007literal\030\004 \001(\0132\030.Mysqlx.Datatypes.Scalar\0220\n\rfunction_call\030\005 \001(\0132\031.Mysqlx.Expr.FunctionCall\022'\n\boperator\030\006 \001(\0132\025.Mysqlx.Expr.Operator\022\020\n\bposition\030\007 \001(\r\022#\n\006object\030\b \001(\0132\023.Mysqlx.Expr.Object\022!\n\005array\030\t \001(\0132\022.Mysqlx.Expr.Array\"q\n\004Type\022\t\n\005IDENT\020\001\022\013\n\007LITERAL\020\002\022\f\n\bVARIABLE\020\003\022\r\n\tFUNC_CALL\020\004\022\f\n\bOPERATOR\020\005\022\017\n\013PLACEHOLDER\020\006\022\n\n\006OBJECT\020\007\022\t\n\005ARRAY\020\b\"/\n\nIdentifier\022\f\n\004name\030\001 \002(\t\022\023\n\013schema_name\030\002 \001(\t\"Ë\001\n\020DocumentPathItem\0220\n\004type\030\001 \002(\0162\".Mysqlx.Expr.DocumentPathItem.Type\022\r\n\005value\030\002 \001(\t\022\r\n\005index\030\003 \001(\r\"g\n\004Type\022\n\n\006MEMBER\020\001\022\023\n\017MEMBER_ASTERISK\020\002\022\017\n\013ARRAY_INDEX\020\003\022\030\n\024ARRAY_INDEX_ASTERISK\020\004\022\023\n\017DOUBLE_ASTERISK\020\005\"\n\020ColumnIdentifier\0224\n\rdocument_path\030\001 \003(\0132\035.Mysqlx.Expr.DocumentPathItem\022\f\n\004name\030\002 \001(\t\022\022\n\ntable_name\030\003 \001(\t\022\023\n\013schema_name\030\004 \001(\t\"W\n\fFunctionCall\022%\n\004name\030\001 \002(\0132\027.Mysqlx.Expr.Identifier\022 \n\005param\030\002 \003(\0132\021.Mysqlx.Expr.Expr\":\n\bOperator\022\f\n\004name\030\001 \002(\t\022 \n\005param\030\002 \003(\0132\021.Mysqlx.Expr.Expr\"t\n\006Object\022,\n\003fld\030\001 \003(\0132\037.Mysqlx.Expr.Object.ObjectField\032<\n\013ObjectField\022\013\n\003key\030\001 \002(\t\022 \n\005value\030\002 \002(\0132\021.Mysqlx.Expr.Expr\")\n\005Array\022 \n\005value\030\001 \003(\0132\021.Mysqlx.Expr.ExprB\031\n\027com.mysql.cj.x.protobuf" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { MysqlxDatatypes.getDescriptor() });
    internal_static_Mysqlx_Expr_Expr_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_Mysqlx_Expr_Expr_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_Expr_descriptor, new String[] { "Type", "Identifier", "Variable", "Literal", "FunctionCall", "Operator", "Position", "Object", "Array" });
    internal_static_Mysqlx_Expr_Identifier_descriptor = getDescriptor().getMessageTypes().get(1);
    internal_static_Mysqlx_Expr_Identifier_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_Identifier_descriptor, new String[] { "Name", "SchemaName" });
    internal_static_Mysqlx_Expr_DocumentPathItem_descriptor = getDescriptor().getMessageTypes().get(2);
    internal_static_Mysqlx_Expr_DocumentPathItem_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_DocumentPathItem_descriptor, new String[] { "Type", "Value", "Index" });
    internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor = getDescriptor().getMessageTypes().get(3);
    internal_static_Mysqlx_Expr_ColumnIdentifier_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor, new String[] { "DocumentPath", "Name", "TableName", "SchemaName" });
    internal_static_Mysqlx_Expr_FunctionCall_descriptor = getDescriptor().getMessageTypes().get(4);
    internal_static_Mysqlx_Expr_FunctionCall_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_FunctionCall_descriptor, new String[] { "Name", "Param" });
    internal_static_Mysqlx_Expr_Operator_descriptor = getDescriptor().getMessageTypes().get(5);
    internal_static_Mysqlx_Expr_Operator_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_Operator_descriptor, new String[] { "Name", "Param" });
    internal_static_Mysqlx_Expr_Object_descriptor = getDescriptor().getMessageTypes().get(6);
    internal_static_Mysqlx_Expr_Object_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_Object_descriptor, new String[] { "Fld" });
    internal_static_Mysqlx_Expr_Object_ObjectField_descriptor = internal_static_Mysqlx_Expr_Object_descriptor.getNestedTypes().get(0);
    internal_static_Mysqlx_Expr_Object_ObjectField_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_Object_ObjectField_descriptor, new String[] { "Key", "Value" });
    internal_static_Mysqlx_Expr_Array_descriptor = getDescriptor().getMessageTypes().get(7);
    internal_static_Mysqlx_Expr_Array_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Mysqlx_Expr_Array_descriptor, new String[] { "Value" });
    MysqlxDatatypes.getDescriptor();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\x\protobuf\MysqlxExpr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */