package com.google.protobuf.compiler;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class PluginProtos {
  private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_Version_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_compiler_Version_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static interface VersionOrBuilder extends MessageOrBuilder {
    boolean hasMajor();
    
    int getMajor();
    
    boolean hasMinor();
    
    int getMinor();
    
    boolean hasPatch();
    
    int getPatch();
    
    boolean hasSuffix();
    
    String getSuffix();
    
    ByteString getSuffixBytes();
  }
  
  public static final class Version extends GeneratedMessageV3 implements VersionOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int MAJOR_FIELD_NUMBER = 1;
    
    private int major_;
    
    public static final int MINOR_FIELD_NUMBER = 2;
    
    private int minor_;
    
    public static final int PATCH_FIELD_NUMBER = 3;
    
    private int patch_;
    
    public static final int SUFFIX_FIELD_NUMBER = 4;
    
    private volatile Object suffix_;
    
    private byte memoizedIsInitialized;
    
    private Version(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private Version() {
      this.memoizedIsInitialized = -1;
      this.suffix_ = "";
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new Version();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private Version(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
            case 8:
              this.bitField0_ |= 0x1;
              this.major_ = input.readInt32();
              continue;
            case 16:
              this.bitField0_ |= 0x2;
              this.minor_ = input.readInt32();
              continue;
            case 24:
              this.bitField0_ |= 0x4;
              this.patch_ = input.readInt32();
              continue;
            case 34:
              bs = input.readBytes();
              this.bitField0_ |= 0x8;
              this.suffix_ = bs;
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
      return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return PluginProtos.internal_static_google_protobuf_compiler_Version_fieldAccessorTable.ensureFieldAccessorsInitialized(Version.class, Builder.class);
    }
    
    public boolean hasMajor() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public int getMajor() {
      return this.major_;
    }
    
    public boolean hasMinor() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public int getMinor() {
      return this.minor_;
    }
    
    public boolean hasPatch() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public int getPatch() {
      return this.patch_;
    }
    
    public boolean hasSuffix() {
      return ((this.bitField0_ & 0x8) != 0);
    }
    
    public String getSuffix() {
      Object ref = this.suffix_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      if (bs.isValidUtf8())
        this.suffix_ = s; 
      return s;
    }
    
    public ByteString getSuffixBytes() {
      Object ref = this.suffix_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.suffix_ = b;
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
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        output.writeInt32(1, this.major_); 
      if ((this.bitField0_ & 0x2) != 0)
        output.writeInt32(2, this.minor_); 
      if ((this.bitField0_ & 0x4) != 0)
        output.writeInt32(3, this.patch_); 
      if ((this.bitField0_ & 0x8) != 0)
        GeneratedMessageV3.writeString(output, 4, this.suffix_); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeInt32Size(1, this.major_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeInt32Size(2, this.minor_); 
      if ((this.bitField0_ & 0x4) != 0)
        size += 
          CodedOutputStream.computeInt32Size(3, this.patch_); 
      if ((this.bitField0_ & 0x8) != 0)
        size += GeneratedMessageV3.computeStringSize(4, this.suffix_); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof Version))
        return super.equals(obj); 
      Version other = (Version)obj;
      if (hasMajor() != other.hasMajor())
        return false; 
      if (hasMajor() && 
        getMajor() != other
        .getMajor())
        return false; 
      if (hasMinor() != other.hasMinor())
        return false; 
      if (hasMinor() && 
        getMinor() != other
        .getMinor())
        return false; 
      if (hasPatch() != other.hasPatch())
        return false; 
      if (hasPatch() && 
        getPatch() != other
        .getPatch())
        return false; 
      if (hasSuffix() != other.hasSuffix())
        return false; 
      if (hasSuffix() && 
        
        !getSuffix().equals(other.getSuffix()))
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
      if (hasMajor()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getMajor();
      } 
      if (hasMinor()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getMinor();
      } 
      if (hasPatch()) {
        hash = 37 * hash + 3;
        hash = 53 * hash + getPatch();
      } 
      if (hasSuffix()) {
        hash = 37 * hash + 4;
        hash = 53 * hash + getSuffix().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static Version parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Version)PARSER.parseFrom(data);
    }
    
    public static Version parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Version)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Version parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Version)PARSER.parseFrom(data);
    }
    
    public static Version parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Version)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Version parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Version)PARSER.parseFrom(data);
    }
    
    public static Version parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Version)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static Version parseFrom(InputStream input) throws IOException {
      return 
        (Version)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Version parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Version)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Version parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (Version)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static Version parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Version)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static Version parseFrom(CodedInputStream input) throws IOException {
      return 
        (Version)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static Version parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (Version)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Version prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements VersionOrBuilder {
      private int bitField0_;
      
      private int major_;
      
      private int minor_;
      
      private int patch_;
      
      private Object suffix_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return PluginProtos.internal_static_google_protobuf_compiler_Version_fieldAccessorTable
          .ensureFieldAccessorsInitialized(Version.class, Builder.class);
      }
      
      private Builder() {
        this.suffix_ = "";
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.suffix_ = "";
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (Version.alwaysUseFieldBuilders);
      }
      
      public Builder clear() {
        super.clear();
        this.major_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.minor_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        this.patch_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        this.suffix_ = "";
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
      }
      
      public Version getDefaultInstanceForType() {
        return Version.getDefaultInstance();
      }
      
      public Version build() {
        Version result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Version buildPartial() {
        Version result = new Version(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0) {
          result.major_ = this.major_;
          to_bitField0_ |= 0x1;
        } 
        if ((from_bitField0_ & 0x2) != 0) {
          result.minor_ = this.minor_;
          to_bitField0_ |= 0x2;
        } 
        if ((from_bitField0_ & 0x4) != 0) {
          result.patch_ = this.patch_;
          to_bitField0_ |= 0x4;
        } 
        if ((from_bitField0_ & 0x8) != 0)
          to_bitField0_ |= 0x8; 
        result.suffix_ = this.suffix_;
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
        if (other instanceof Version)
          return mergeFrom((Version)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Version other) {
        if (other == Version.getDefaultInstance())
          return this; 
        if (other.hasMajor())
          setMajor(other.getMajor()); 
        if (other.hasMinor())
          setMinor(other.getMinor()); 
        if (other.hasPatch())
          setPatch(other.getPatch()); 
        if (other.hasSuffix()) {
          this.bitField0_ |= 0x8;
          this.suffix_ = other.suffix_;
          onChanged();
        } 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        Version parsedMessage = null;
        try {
          parsedMessage = (Version) Version.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (Version)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasMajor() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public int getMajor() {
        return this.major_;
      }
      
      public Builder setMajor(int value) {
        this.bitField0_ |= 0x1;
        this.major_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearMajor() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.major_ = 0;
        onChanged();
        return this;
      }
      
      public boolean hasMinor() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public int getMinor() {
        return this.minor_;
      }
      
      public Builder setMinor(int value) {
        this.bitField0_ |= 0x2;
        this.minor_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearMinor() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.minor_ = 0;
        onChanged();
        return this;
      }
      
      public boolean hasPatch() {
        return ((this.bitField0_ & 0x4) != 0);
      }
      
      public int getPatch() {
        return this.patch_;
      }
      
      public Builder setPatch(int value) {
        this.bitField0_ |= 0x4;
        this.patch_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearPatch() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.patch_ = 0;
        onChanged();
        return this;
      }
      
      public boolean hasSuffix() {
        return ((this.bitField0_ & 0x8) != 0);
      }
      
      public String getSuffix() {
        Object ref = this.suffix_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8())
            this.suffix_ = s; 
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getSuffixBytes() {
        Object ref = this.suffix_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.suffix_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setSuffix(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x8;
        this.suffix_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearSuffix() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.suffix_ = Version.getDefaultInstance().getSuffix();
        onChanged();
        return this;
      }
      
      public Builder setSuffixBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x8;
        this.suffix_ = value;
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
    
    private static final Version DEFAULT_INSTANCE = new Version();
    
    public static Version getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<Version> PARSER = (Parser<Version>)new AbstractParser<Version>() {
        public Version parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new Version(input, extensionRegistry);
        }
      };
    
    public static Parser<Version> parser() {
      return PARSER;
    }
    
    public Parser<Version> getParserForType() {
      return PARSER;
    }
    
    public Version getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface CodeGeneratorRequestOrBuilder extends MessageOrBuilder {
    List<String> getFileToGenerateList();
    
    int getFileToGenerateCount();
    
    String getFileToGenerate(int param1Int);
    
    ByteString getFileToGenerateBytes(int param1Int);
    
    boolean hasParameter();
    
    String getParameter();
    
    ByteString getParameterBytes();
    
    List<DescriptorProtos.FileDescriptorProto> getProtoFileList();
    
    DescriptorProtos.FileDescriptorProto getProtoFile(int param1Int);
    
    int getProtoFileCount();
    
    List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList();
    
    DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int param1Int);
    
    boolean hasCompilerVersion();
    
    Version getCompilerVersion();
    
    VersionOrBuilder getCompilerVersionOrBuilder();
  }
  
  public static final class CodeGeneratorRequest extends GeneratedMessageV3 implements CodeGeneratorRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int FILE_TO_GENERATE_FIELD_NUMBER = 1;
    
    private LazyStringList fileToGenerate_;
    
    public static final int PARAMETER_FIELD_NUMBER = 2;
    
    private volatile Object parameter_;
    
    public static final int PROTO_FILE_FIELD_NUMBER = 15;
    
    private List<DescriptorProtos.FileDescriptorProto> protoFile_;
    
    public static final int COMPILER_VERSION_FIELD_NUMBER = 3;
    
    private Version compilerVersion_;
    
    private byte memoizedIsInitialized;
    
    private CodeGeneratorRequest(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private CodeGeneratorRequest() {
      this.memoizedIsInitialized = -1;
      this.fileToGenerate_ = LazyStringArrayList.EMPTY;
      this.parameter_ = "";
      this.protoFile_ = Collections.emptyList();
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new CodeGeneratorRequest();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private CodeGeneratorRequest(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      int mutable_bitField0_ = 0;
      UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          ByteString bs;
          Version.Builder subBuilder;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              bs = input.readBytes();
              if ((mutable_bitField0_ & 0x1) == 0) {
                this.fileToGenerate_ = (LazyStringList)new LazyStringArrayList();
                mutable_bitField0_ |= 0x1;
              } 
              this.fileToGenerate_.add(bs);
              continue;
            case 18:
              bs = input.readBytes();
              this.bitField0_ |= 0x1;
              this.parameter_ = bs;
              continue;
            case 26:
              subBuilder = null;
              if ((this.bitField0_ & 0x2) != 0)
                subBuilder = this.compilerVersion_.toBuilder(); 
              this.compilerVersion_ = (Version)input.readMessage(Version.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(this.compilerVersion_);
                this.compilerVersion_ = subBuilder.buildPartial();
              } 
              this.bitField0_ |= 0x2;
              continue;
            case 122:
              if ((mutable_bitField0_ & 0x4) == 0) {
                this.protoFile_ = new ArrayList<>();
                mutable_bitField0_ |= 0x4;
              } 
              this.protoFile_.add(input.readMessage(DescriptorProtos.FileDescriptorProto.PARSER, extensionRegistry));
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
          this.fileToGenerate_ = this.fileToGenerate_.getUnmodifiableView(); 
        if ((mutable_bitField0_ & 0x4) != 0)
          this.protoFile_ = Collections.unmodifiableList(this.protoFile_); 
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(CodeGeneratorRequest.class, Builder.class);
    }
    
    public ProtocolStringList getFileToGenerateList() {
      return (ProtocolStringList)this.fileToGenerate_;
    }
    
    public int getFileToGenerateCount() {
      return this.fileToGenerate_.size();
    }
    
    public String getFileToGenerate(int index) {
      return (String)this.fileToGenerate_.get(index);
    }
    
    public ByteString getFileToGenerateBytes(int index) {
      return this.fileToGenerate_.getByteString(index);
    }
    
    public boolean hasParameter() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public String getParameter() {
      Object ref = this.parameter_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      if (bs.isValidUtf8())
        this.parameter_ = s; 
      return s;
    }
    
    public ByteString getParameterBytes() {
      Object ref = this.parameter_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.parameter_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public List<DescriptorProtos.FileDescriptorProto> getProtoFileList() {
      return this.protoFile_;
    }
    
    public List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList() {
      return (List)this.protoFile_;
    }
    
    public int getProtoFileCount() {
      return this.protoFile_.size();
    }
    
    public DescriptorProtos.FileDescriptorProto getProtoFile(int index) {
      return this.protoFile_.get(index);
    }
    
    public DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int index) {
      return (DescriptorProtos.FileDescriptorProtoOrBuilder)this.protoFile_.get(index);
    }
    
    public boolean hasCompilerVersion() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public Version getCompilerVersion() {
      return (this.compilerVersion_ == null) ? Version.getDefaultInstance() : this.compilerVersion_;
    }
    
    public VersionOrBuilder getCompilerVersionOrBuilder() {
      return (this.compilerVersion_ == null) ? Version.getDefaultInstance() : this.compilerVersion_;
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      for (int i = 0; i < getProtoFileCount(); i++) {
        if (!getProtoFile(i).isInitialized()) {
          this.memoizedIsInitialized = 0;
          return false;
        } 
      } 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      int i;
      for (i = 0; i < this.fileToGenerate_.size(); i++)
        GeneratedMessageV3.writeString(output, 1, this.fileToGenerate_.getRaw(i)); 
      if ((this.bitField0_ & 0x1) != 0)
        GeneratedMessageV3.writeString(output, 2, this.parameter_); 
      if ((this.bitField0_ & 0x2) != 0)
        output.writeMessage(3, (MessageLite)getCompilerVersion()); 
      for (i = 0; i < this.protoFile_.size(); i++)
        output.writeMessage(15, (MessageLite)this.protoFile_.get(i)); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      int dataSize = 0;
      for (int j = 0; j < this.fileToGenerate_.size(); j++)
        dataSize += computeStringSizeNoTag(this.fileToGenerate_.getRaw(j)); 
      size += dataSize;
      size += 1 * getFileToGenerateList().size();
      if ((this.bitField0_ & 0x1) != 0)
        size += GeneratedMessageV3.computeStringSize(2, this.parameter_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeMessageSize(3, (MessageLite)getCompilerVersion()); 
      for (int i = 0; i < this.protoFile_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(15, (MessageLite)this.protoFile_.get(i)); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof CodeGeneratorRequest))
        return super.equals(obj); 
      CodeGeneratorRequest other = (CodeGeneratorRequest)obj;
      if (!getFileToGenerateList().equals(other.getFileToGenerateList()))
        return false; 
      if (hasParameter() != other.hasParameter())
        return false; 
      if (hasParameter() && 
        
        !getParameter().equals(other.getParameter()))
        return false; 
      if (!getProtoFileList().equals(other.getProtoFileList()))
        return false; 
      if (hasCompilerVersion() != other.hasCompilerVersion())
        return false; 
      if (hasCompilerVersion() && 
        
        !getCompilerVersion().equals(other.getCompilerVersion()))
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
      if (getFileToGenerateCount() > 0) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getFileToGenerateList().hashCode();
      } 
      if (hasParameter()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getParameter().hashCode();
      } 
      if (getProtoFileCount() > 0) {
        hash = 37 * hash + 15;
        hash = 53 * hash + getProtoFileList().hashCode();
      } 
      if (hasCompilerVersion()) {
        hash = 37 * hash + 3;
        hash = 53 * hash + getCompilerVersion().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static CodeGeneratorRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (CodeGeneratorRequest)PARSER.parseFrom(data);
    }
    
    public static CodeGeneratorRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CodeGeneratorRequest)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CodeGeneratorRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (CodeGeneratorRequest)PARSER.parseFrom(data);
    }
    
    public static CodeGeneratorRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CodeGeneratorRequest)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CodeGeneratorRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (CodeGeneratorRequest)PARSER.parseFrom(data);
    }
    
    public static CodeGeneratorRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CodeGeneratorRequest)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CodeGeneratorRequest parseFrom(InputStream input) throws IOException {
      return 
        (CodeGeneratorRequest)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CodeGeneratorRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CodeGeneratorRequest)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CodeGeneratorRequest parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (CodeGeneratorRequest)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static CodeGeneratorRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CodeGeneratorRequest)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CodeGeneratorRequest parseFrom(CodedInputStream input) throws IOException {
      return 
        (CodeGeneratorRequest)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CodeGeneratorRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CodeGeneratorRequest)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(CodeGeneratorRequest prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CodeGeneratorRequestOrBuilder {
      private int bitField0_;
      
      private LazyStringList fileToGenerate_;
      
      private Object parameter_;
      
      private List<DescriptorProtos.FileDescriptorProto> protoFile_;
      
      private RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> protoFileBuilder_;
      
      private Version compilerVersion_;
      
      private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> compilerVersionBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(CodeGeneratorRequest.class, Builder.class);
      }
      
      private Builder() {
        this.fileToGenerate_ = LazyStringArrayList.EMPTY;
        this.parameter_ = "";
        this
          .protoFile_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.fileToGenerate_ = LazyStringArrayList.EMPTY;
        this.parameter_ = "";
        this.protoFile_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (CodeGeneratorRequest.alwaysUseFieldBuilders) {
          getProtoFileFieldBuilder();
          getCompilerVersionFieldBuilder();
        } 
      }
      
      public Builder clear() {
        super.clear();
        this.fileToGenerate_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= 0xFFFFFFFE;
        this.parameter_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        if (this.protoFileBuilder_ == null) {
          this.protoFile_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFB;
        } else {
          this.protoFileBuilder_.clear();
        } 
        if (this.compilerVersionBuilder_ == null) {
          this.compilerVersion_ = null;
        } else {
          this.compilerVersionBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
      }
      
      public CodeGeneratorRequest getDefaultInstanceForType() {
        return CodeGeneratorRequest.getDefaultInstance();
      }
      
      public CodeGeneratorRequest build() {
        CodeGeneratorRequest result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public CodeGeneratorRequest buildPartial() {
        CodeGeneratorRequest result = new CodeGeneratorRequest(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((this.bitField0_ & 0x1) != 0) {
          this.fileToGenerate_ = this.fileToGenerate_.getUnmodifiableView();
          this.bitField0_ &= 0xFFFFFFFE;
        } 
        result.fileToGenerate_ = this.fileToGenerate_;
        if ((from_bitField0_ & 0x2) != 0)
          to_bitField0_ |= 0x1; 
        result.parameter_ = this.parameter_;
        if (this.protoFileBuilder_ == null) {
          if ((this.bitField0_ & 0x4) != 0) {
            this.protoFile_ = Collections.unmodifiableList(this.protoFile_);
            this.bitField0_ &= 0xFFFFFFFB;
          } 
          result.protoFile_ = this.protoFile_;
        } else {
          result.protoFile_ = this.protoFileBuilder_.build();
        } 
        if ((from_bitField0_ & 0x8) != 0) {
          if (this.compilerVersionBuilder_ == null) {
            result.compilerVersion_ = this.compilerVersion_;
          } else {
            result.compilerVersion_ = (Version)this.compilerVersionBuilder_.build();
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
        if (other instanceof CodeGeneratorRequest)
          return mergeFrom((CodeGeneratorRequest)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(CodeGeneratorRequest other) {
        if (other == CodeGeneratorRequest.getDefaultInstance())
          return this; 
        if (!other.fileToGenerate_.isEmpty()) {
          if (this.fileToGenerate_.isEmpty()) {
            this.fileToGenerate_ = other.fileToGenerate_;
            this.bitField0_ &= 0xFFFFFFFE;
          } else {
            ensureFileToGenerateIsMutable();
            this.fileToGenerate_.addAll((Collection)other.fileToGenerate_);
          } 
          onChanged();
        } 
        if (other.hasParameter()) {
          this.bitField0_ |= 0x2;
          this.parameter_ = other.parameter_;
          onChanged();
        } 
        if (this.protoFileBuilder_ == null) {
          if (!other.protoFile_.isEmpty()) {
            if (this.protoFile_.isEmpty()) {
              this.protoFile_ = other.protoFile_;
              this.bitField0_ &= 0xFFFFFFFB;
            } else {
              ensureProtoFileIsMutable();
              this.protoFile_.addAll(other.protoFile_);
            } 
            onChanged();
          } 
        } else if (!other.protoFile_.isEmpty()) {
          if (this.protoFileBuilder_.isEmpty()) {
            this.protoFileBuilder_.dispose();
            this.protoFileBuilder_ = null;
            this.protoFile_ = other.protoFile_;
            this.bitField0_ &= 0xFFFFFFFB;
            this.protoFileBuilder_ = CodeGeneratorRequest.alwaysUseFieldBuilders ? getProtoFileFieldBuilder() : null;
          } else {
            this.protoFileBuilder_.addAllMessages(other.protoFile_);
          } 
        } 
        if (other.hasCompilerVersion())
          mergeCompilerVersion(other.getCompilerVersion()); 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        for (int i = 0; i < getProtoFileCount(); i++) {
          if (!getProtoFile(i).isInitialized())
            return false; 
        } 
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        CodeGeneratorRequest parsedMessage = null;
        try {
          parsedMessage = (CodeGeneratorRequest) CodeGeneratorRequest.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (CodeGeneratorRequest)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      private void ensureFileToGenerateIsMutable() {
        if ((this.bitField0_ & 0x1) == 0) {
          this.fileToGenerate_ = (LazyStringList)new LazyStringArrayList(this.fileToGenerate_);
          this.bitField0_ |= 0x1;
        } 
      }
      
      public ProtocolStringList getFileToGenerateList() {
        return (ProtocolStringList)this.fileToGenerate_.getUnmodifiableView();
      }
      
      public int getFileToGenerateCount() {
        return this.fileToGenerate_.size();
      }
      
      public String getFileToGenerate(int index) {
        return (String)this.fileToGenerate_.get(index);
      }
      
      public ByteString getFileToGenerateBytes(int index) {
        return this.fileToGenerate_.getByteString(index);
      }
      
      public Builder setFileToGenerate(int index, String value) {
        if (value == null)
          throw new NullPointerException(); 
        ensureFileToGenerateIsMutable();
        this.fileToGenerate_.set(index, value);
        onChanged();
        return this;
      }
      
      public Builder addFileToGenerate(String value) {
        if (value == null)
          throw new NullPointerException(); 
        ensureFileToGenerateIsMutable();
        this.fileToGenerate_.add(value);
        onChanged();
        return this;
      }
      
      public Builder addAllFileToGenerate(Iterable<String> values) {
        ensureFileToGenerateIsMutable();
        AbstractMessageLite.Builder.addAll(values, (List)this.fileToGenerate_);
        onChanged();
        return this;
      }
      
      public Builder clearFileToGenerate() {
        this.fileToGenerate_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder addFileToGenerateBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        ensureFileToGenerateIsMutable();
        this.fileToGenerate_.add(value);
        onChanged();
        return this;
      }
      
      public boolean hasParameter() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public String getParameter() {
        Object ref = this.parameter_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8())
            this.parameter_ = s; 
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getParameterBytes() {
        Object ref = this.parameter_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.parameter_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setParameter(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.parameter_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearParameter() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.parameter_ = CodeGeneratorRequest.getDefaultInstance().getParameter();
        onChanged();
        return this;
      }
      
      public Builder setParameterBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.parameter_ = value;
        onChanged();
        return this;
      }
      
      private void ensureProtoFileIsMutable() {
        if ((this.bitField0_ & 0x4) == 0) {
          this.protoFile_ = new ArrayList<>(this.protoFile_);
          this.bitField0_ |= 0x4;
        } 
      }
      
      public List<DescriptorProtos.FileDescriptorProto> getProtoFileList() {
        if (this.protoFileBuilder_ == null)
          return Collections.unmodifiableList(this.protoFile_); 
        return this.protoFileBuilder_.getMessageList();
      }
      
      public int getProtoFileCount() {
        if (this.protoFileBuilder_ == null)
          return this.protoFile_.size(); 
        return this.protoFileBuilder_.getCount();
      }
      
      public DescriptorProtos.FileDescriptorProto getProtoFile(int index) {
        if (this.protoFileBuilder_ == null)
          return this.protoFile_.get(index); 
        return (DescriptorProtos.FileDescriptorProto)this.protoFileBuilder_.getMessage(index);
      }
      
      public Builder setProtoFile(int index, DescriptorProtos.FileDescriptorProto value) {
        if (this.protoFileBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureProtoFileIsMutable();
          this.protoFile_.set(index, value);
          onChanged();
        } else {
          this.protoFileBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setProtoFile(int index, DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
        if (this.protoFileBuilder_ == null) {
          ensureProtoFileIsMutable();
          this.protoFile_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.protoFileBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addProtoFile(DescriptorProtos.FileDescriptorProto value) {
        if (this.protoFileBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureProtoFileIsMutable();
          this.protoFile_.add(value);
          onChanged();
        } else {
          this.protoFileBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addProtoFile(int index, DescriptorProtos.FileDescriptorProto value) {
        if (this.protoFileBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureProtoFileIsMutable();
          this.protoFile_.add(index, value);
          onChanged();
        } else {
          this.protoFileBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addProtoFile(DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
        if (this.protoFileBuilder_ == null) {
          ensureProtoFileIsMutable();
          this.protoFile_.add(builderForValue.build());
          onChanged();
        } else {
          this.protoFileBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addProtoFile(int index, DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
        if (this.protoFileBuilder_ == null) {
          ensureProtoFileIsMutable();
          this.protoFile_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.protoFileBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllProtoFile(Iterable<? extends DescriptorProtos.FileDescriptorProto> values) {
        if (this.protoFileBuilder_ == null) {
          ensureProtoFileIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.protoFile_);
          onChanged();
        } else {
          this.protoFileBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearProtoFile() {
        if (this.protoFileBuilder_ == null) {
          this.protoFile_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFB;
          onChanged();
        } else {
          this.protoFileBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeProtoFile(int index) {
        if (this.protoFileBuilder_ == null) {
          ensureProtoFileIsMutable();
          this.protoFile_.remove(index);
          onChanged();
        } else {
          this.protoFileBuilder_.remove(index);
        } 
        return this;
      }
      
      public DescriptorProtos.FileDescriptorProto.Builder getProtoFileBuilder(int index) {
        return (DescriptorProtos.FileDescriptorProto.Builder)getProtoFileFieldBuilder().getBuilder(index);
      }
      
      public DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int index) {
        if (this.protoFileBuilder_ == null)
          return (DescriptorProtos.FileDescriptorProtoOrBuilder)this.protoFile_.get(index); 
        return (DescriptorProtos.FileDescriptorProtoOrBuilder)this.protoFileBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList() {
        if (this.protoFileBuilder_ != null)
          return this.protoFileBuilder_.getMessageOrBuilderList(); 
        return (List)Collections.unmodifiableList(this.protoFile_);
      }
      
      public DescriptorProtos.FileDescriptorProto.Builder addProtoFileBuilder() {
        return (DescriptorProtos.FileDescriptorProto.Builder)getProtoFileFieldBuilder().addBuilder(
            (AbstractMessage)DescriptorProtos.FileDescriptorProto.getDefaultInstance());
      }
      
      public DescriptorProtos.FileDescriptorProto.Builder addProtoFileBuilder(int index) {
        return (DescriptorProtos.FileDescriptorProto.Builder)getProtoFileFieldBuilder().addBuilder(index, 
            (AbstractMessage)DescriptorProtos.FileDescriptorProto.getDefaultInstance());
      }
      
      public List<DescriptorProtos.FileDescriptorProto.Builder> getProtoFileBuilderList() {
        return getProtoFileFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileFieldBuilder() {
        if (this.protoFileBuilder_ == null) {
          this
            
            .protoFileBuilder_ = new RepeatedFieldBuilderV3(this.protoFile_, ((this.bitField0_ & 0x4) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.protoFile_ = null;
        } 
        return this.protoFileBuilder_;
      }
      
      public boolean hasCompilerVersion() {
        return ((this.bitField0_ & 0x8) != 0);
      }
      
      public Version getCompilerVersion() {
        if (this.compilerVersionBuilder_ == null)
          return (this.compilerVersion_ == null) ? Version.getDefaultInstance() : this.compilerVersion_;
        return (Version)this.compilerVersionBuilder_.getMessage();
      }
      
      public Builder setCompilerVersion(Version value) {
        if (this.compilerVersionBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.compilerVersion_ = value;
          onChanged();
        } else {
          this.compilerVersionBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x8;
        return this;
      }
      
      public Builder setCompilerVersion(Version.Builder builderForValue) {
        if (this.compilerVersionBuilder_ == null) {
          this.compilerVersion_ = builderForValue.build();
          onChanged();
        } else {
          this.compilerVersionBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x8;
        return this;
      }
      
      public Builder mergeCompilerVersion(Version value) {
        if (this.compilerVersionBuilder_ == null) {
          if ((this.bitField0_ & 0x8) != 0 && this.compilerVersion_ != null && this.compilerVersion_ != 
            
            Version.getDefaultInstance()) {
            this
              .compilerVersion_ = Version.newBuilder(this.compilerVersion_).mergeFrom(value).buildPartial();
          } else {
            this.compilerVersion_ = value;
          } 
          onChanged();
        } else {
          this.compilerVersionBuilder_.mergeFrom((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x8;
        return this;
      }
      
      public Builder clearCompilerVersion() {
        if (this.compilerVersionBuilder_ == null) {
          this.compilerVersion_ = null;
          onChanged();
        } else {
          this.compilerVersionBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
      }
      
      public Version.Builder getCompilerVersionBuilder() {
        this.bitField0_ |= 0x8;
        onChanged();
        return (Version.Builder)getCompilerVersionFieldBuilder().getBuilder();
      }
      
      public VersionOrBuilder getCompilerVersionOrBuilder() {
        if (this.compilerVersionBuilder_ != null)
          return (VersionOrBuilder)this.compilerVersionBuilder_.getMessageOrBuilder();
        return (this.compilerVersion_ == null) ? 
          Version.getDefaultInstance() : this.compilerVersion_;
      }
      
      private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> getCompilerVersionFieldBuilder() {
        if (this.compilerVersionBuilder_ == null) {
          this
            
            .compilerVersionBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getCompilerVersion(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.compilerVersion_ = null;
        } 
        return this.compilerVersionBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final CodeGeneratorRequest DEFAULT_INSTANCE = new CodeGeneratorRequest();
    
    public static CodeGeneratorRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<CodeGeneratorRequest> PARSER = (Parser<CodeGeneratorRequest>)new AbstractParser<CodeGeneratorRequest>() {
        public CodeGeneratorRequest parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new CodeGeneratorRequest(input, extensionRegistry);
        }
      };
    
    public static Parser<CodeGeneratorRequest> parser() {
      return PARSER;
    }
    
    public Parser<CodeGeneratorRequest> getParserForType() {
      return PARSER;
    }
    
    public CodeGeneratorRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static interface CodeGeneratorResponseOrBuilder extends MessageOrBuilder {
    boolean hasError();
    
    String getError();
    
    ByteString getErrorBytes();
    
    List<CodeGeneratorResponse.File> getFileList();
    
    CodeGeneratorResponse.File getFile(int param1Int);
    
    int getFileCount();
    
    List<? extends CodeGeneratorResponse.FileOrBuilder> getFileOrBuilderList();
    
    CodeGeneratorResponse.FileOrBuilder getFileOrBuilder(int param1Int);
  }
  
  public static final class CodeGeneratorResponse extends GeneratedMessageV3 implements CodeGeneratorResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int ERROR_FIELD_NUMBER = 1;
    
    private volatile Object error_;
    
    public static final int FILE_FIELD_NUMBER = 15;
    
    private List<File> file_;
    
    private byte memoizedIsInitialized;
    
    private CodeGeneratorResponse(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private CodeGeneratorResponse() {
      this.memoizedIsInitialized = -1;
      this.error_ = "";
      this.file_ = Collections.emptyList();
    }
    
    protected Object newInstance(UnusedPrivateParameter unused) {
      return new CodeGeneratorResponse();
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    private CodeGeneratorResponse(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
              this.error_ = bs;
              continue;
            case 122:
              if ((mutable_bitField0_ & 0x2) == 0) {
                this.file_ = new ArrayList<>();
                mutable_bitField0_ |= 0x2;
              } 
              this.file_.add(input.readMessage(File.PARSER, extensionRegistry));
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
          this.file_ = Collections.unmodifiableList(this.file_); 
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
    }
    
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(CodeGeneratorResponse.class, Builder.class);
    }
    
    public static final class File extends GeneratedMessageV3 implements FileOrBuilder {
      private static final long serialVersionUID = 0L;
      
      private int bitField0_;
      
      public static final int NAME_FIELD_NUMBER = 1;
      
      private volatile Object name_;
      
      public static final int INSERTION_POINT_FIELD_NUMBER = 2;
      
      private volatile Object insertionPoint_;
      
      public static final int CONTENT_FIELD_NUMBER = 15;
      
      private volatile Object content_;
      
      private byte memoizedIsInitialized;
      
      private File(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
      }
      
      private File() {
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.insertionPoint_ = "";
        this.content_ = "";
      }
      
      protected Object newInstance(UnusedPrivateParameter unused) {
        return new File();
      }
      
      public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
      }
      
      private File(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                this.insertionPoint_ = bs;
                continue;
              case 122:
                bs = input.readBytes();
                this.bitField0_ |= 0x4;
                this.content_ = bs;
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
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable.ensureFieldAccessorsInitialized(File.class, Builder.class);
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
      
      public boolean hasInsertionPoint() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public String getInsertionPoint() {
        Object ref = this.insertionPoint_;
        if (ref instanceof String)
          return (String)ref; 
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8())
          this.insertionPoint_ = s; 
        return s;
      }
      
      public ByteString getInsertionPointBytes() {
        Object ref = this.insertionPoint_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.insertionPoint_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public boolean hasContent() {
        return ((this.bitField0_ & 0x4) != 0);
      }
      
      public String getContent() {
        Object ref = this.content_;
        if (ref instanceof String)
          return (String)ref; 
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8())
          this.content_ = s; 
        return s;
      }
      
      public ByteString getContentBytes() {
        Object ref = this.content_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.content_ = b;
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
        this.memoizedIsInitialized = 1;
        return true;
      }
      
      public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 0x1) != 0)
          GeneratedMessageV3.writeString(output, 1, this.name_); 
        if ((this.bitField0_ & 0x2) != 0)
          GeneratedMessageV3.writeString(output, 2, this.insertionPoint_); 
        if ((this.bitField0_ & 0x4) != 0)
          GeneratedMessageV3.writeString(output, 15, this.content_); 
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
          size += GeneratedMessageV3.computeStringSize(2, this.insertionPoint_); 
        if ((this.bitField0_ & 0x4) != 0)
          size += GeneratedMessageV3.computeStringSize(15, this.content_); 
        size += this.unknownFields.getSerializedSize();
        this.memoizedSize = size;
        return size;
      }
      
      public boolean equals(Object obj) {
        if (obj == this)
          return true; 
        if (!(obj instanceof File))
          return super.equals(obj); 
        File other = (File)obj;
        if (hasName() != other.hasName())
          return false; 
        if (hasName() && !getName().equals(other.getName()))
          return false; 
        if (hasInsertionPoint() != other.hasInsertionPoint())
          return false; 
        if (hasInsertionPoint() && !getInsertionPoint().equals(other.getInsertionPoint()))
          return false; 
        if (hasContent() != other.hasContent())
          return false; 
        if (hasContent() && !getContent().equals(other.getContent()))
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
        if (hasInsertionPoint()) {
          hash = 37 * hash + 2;
          hash = 53 * hash + getInsertionPoint().hashCode();
        } 
        if (hasContent()) {
          hash = 37 * hash + 15;
          hash = 53 * hash + getContent().hashCode();
        } 
        hash = 29 * hash + this.unknownFields.hashCode();
        this.memoizedHashCode = hash;
        return hash;
      }
      
      public static File parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (File)PARSER.parseFrom(data);
      }
      
      public static File parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (File)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static File parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (File)PARSER.parseFrom(data);
      }
      
      public static File parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (File)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static File parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (File)PARSER.parseFrom(data);
      }
      
      public static File parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (File)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static File parseFrom(InputStream input) throws IOException {
        return (File)GeneratedMessageV3.parseWithIOException(PARSER, input);
      }
      
      public static File parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (File)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
      }
      
      public static File parseDelimitedFrom(InputStream input) throws IOException {
        return (File)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
      }
      
      public static File parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (File)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }
      
      public static File parseFrom(CodedInputStream input) throws IOException {
        return (File)GeneratedMessageV3.parseWithIOException(PARSER, input);
      }
      
      public static File parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (File)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
      }
      
      public Builder newBuilderForType() {
        return newBuilder();
      }
      
      public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
      }
      
      public static Builder newBuilder(File prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }
      
      public Builder toBuilder() {
        return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder()).mergeFrom(this);
      }
      
      protected Builder newBuilderForType(BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
      }
      
      public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FileOrBuilder {
        private int bitField0_;
        
        private Object name_;
        
        private Object insertionPoint_;
        
        private Object content_;
        
        public static final Descriptors.Descriptor getDescriptor() {
          return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
        }
        
        protected FieldAccessorTable internalGetFieldAccessorTable() {
          return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable.ensureFieldAccessorsInitialized(File.class, Builder.class);
        }
        
        private Builder() {
          this.name_ = "";
          this.insertionPoint_ = "";
          this.content_ = "";
          maybeForceBuilderInitialization();
        }
        
        private Builder(BuilderParent parent) {
          super(parent);
          this.name_ = "";
          this.insertionPoint_ = "";
          this.content_ = "";
          maybeForceBuilderInitialization();
        }
        
        private void maybeForceBuilderInitialization() {
          if (File.alwaysUseFieldBuilders);
        }
        
        public Builder clear() {
          super.clear();
          this.name_ = "";
          this.bitField0_ &= 0xFFFFFFFE;
          this.insertionPoint_ = "";
          this.bitField0_ &= 0xFFFFFFFD;
          this.content_ = "";
          this.bitField0_ &= 0xFFFFFFFB;
          return this;
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
          return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
        }
        
        public File getDefaultInstanceForType() {
          return File.getDefaultInstance();
        }
        
        public File build() {
          File result = buildPartial();
          if (!result.isInitialized())
            throw newUninitializedMessageException(result); 
          return result;
        }
        
        public File buildPartial() {
          File result = new File(this);
          int from_bitField0_ = this.bitField0_;
          int to_bitField0_ = 0;
          if ((from_bitField0_ & 0x1) != 0)
            to_bitField0_ |= 0x1; 
          result.name_ = this.name_;
          if ((from_bitField0_ & 0x2) != 0)
            to_bitField0_ |= 0x2; 
          result.insertionPoint_ = this.insertionPoint_;
          if ((from_bitField0_ & 0x4) != 0)
            to_bitField0_ |= 0x4; 
          result.content_ = this.content_;
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
          if (other instanceof File)
            return mergeFrom((File)other);
          super.mergeFrom(other);
          return this;
        }
        
        public Builder mergeFrom(File other) {
          if (other == File.getDefaultInstance())
            return this; 
          if (other.hasName()) {
            this.bitField0_ |= 0x1;
            this.name_ = other.name_;
            onChanged();
          } 
          if (other.hasInsertionPoint()) {
            this.bitField0_ |= 0x2;
            this.insertionPoint_ = other.insertionPoint_;
            onChanged();
          } 
          if (other.hasContent()) {
            this.bitField0_ |= 0x4;
            this.content_ = other.content_;
            onChanged();
          } 
          mergeUnknownFields(other.unknownFields);
          onChanged();
          return this;
        }
        
        public final boolean isInitialized() {
          return true;
        }
        
        public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
          File parsedMessage = null;
          try {
            parsedMessage = (File) File.PARSER.parsePartialFrom(input, extensionRegistry);
          } catch (InvalidProtocolBufferException e) {
            parsedMessage = (File)e.getUnfinishedMessage();
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
          this.name_ = File.getDefaultInstance().getName();
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
        
        public boolean hasInsertionPoint() {
          return ((this.bitField0_ & 0x2) != 0);
        }
        
        public String getInsertionPoint() {
          Object ref = this.insertionPoint_;
          if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8())
              this.insertionPoint_ = s; 
            return s;
          } 
          return (String)ref;
        }
        
        public ByteString getInsertionPointBytes() {
          Object ref = this.insertionPoint_;
          if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.insertionPoint_ = b;
            return b;
          } 
          return (ByteString)ref;
        }
        
        public Builder setInsertionPoint(String value) {
          if (value == null)
            throw new NullPointerException(); 
          this.bitField0_ |= 0x2;
          this.insertionPoint_ = value;
          onChanged();
          return this;
        }
        
        public Builder clearInsertionPoint() {
          this.bitField0_ &= 0xFFFFFFFD;
          this.insertionPoint_ = File.getDefaultInstance().getInsertionPoint();
          onChanged();
          return this;
        }
        
        public Builder setInsertionPointBytes(ByteString value) {
          if (value == null)
            throw new NullPointerException(); 
          this.bitField0_ |= 0x2;
          this.insertionPoint_ = value;
          onChanged();
          return this;
        }
        
        public boolean hasContent() {
          return ((this.bitField0_ & 0x4) != 0);
        }
        
        public String getContent() {
          Object ref = this.content_;
          if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8())
              this.content_ = s; 
            return s;
          } 
          return (String)ref;
        }
        
        public ByteString getContentBytes() {
          Object ref = this.content_;
          if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.content_ = b;
            return b;
          } 
          return (ByteString)ref;
        }
        
        public Builder setContent(String value) {
          if (value == null)
            throw new NullPointerException(); 
          this.bitField0_ |= 0x4;
          this.content_ = value;
          onChanged();
          return this;
        }
        
        public Builder clearContent() {
          this.bitField0_ &= 0xFFFFFFFB;
          this.content_ = File.getDefaultInstance().getContent();
          onChanged();
          return this;
        }
        
        public Builder setContentBytes(ByteString value) {
          if (value == null)
            throw new NullPointerException(); 
          this.bitField0_ |= 0x4;
          this.content_ = value;
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
      
      private static final File DEFAULT_INSTANCE = new File();
      
      public static File getDefaultInstance() {
        return DEFAULT_INSTANCE;
      }
      
      @Deprecated
      public static final Parser<File> PARSER = (Parser<File>)new AbstractParser<File>() {
          public File parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new File(input, extensionRegistry);
          }
        };
      
      public static Parser<File> parser() {
        return PARSER;
      }
      
      public Parser<File> getParserForType() {
        return PARSER;
      }
      
      public File getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
      }
    }
    
    public boolean hasError() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public String getError() {
      Object ref = this.error_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      if (bs.isValidUtf8())
        this.error_ = s; 
      return s;
    }
    
    public ByteString getErrorBytes() {
      Object ref = this.error_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.error_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public List<File> getFileList() {
      return this.file_;
    }
    
    public List<? extends FileOrBuilder> getFileOrBuilderList() {
      return (List)this.file_;
    }
    
    public int getFileCount() {
      return this.file_.size();
    }
    
    public File getFile(int index) {
      return this.file_.get(index);
    }
    
    public FileOrBuilder getFileOrBuilder(int index) {
      return this.file_.get(index);
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
        GeneratedMessageV3.writeString(output, 1, this.error_); 
      for (int i = 0; i < this.file_.size(); i++)
        output.writeMessage(15, (MessageLite)this.file_.get(i)); 
      this.unknownFields.writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += GeneratedMessageV3.computeStringSize(1, this.error_); 
      for (int i = 0; i < this.file_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(15, (MessageLite)this.file_.get(i)); 
      size += this.unknownFields.getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof CodeGeneratorResponse))
        return super.equals(obj); 
      CodeGeneratorResponse other = (CodeGeneratorResponse)obj;
      if (hasError() != other.hasError())
        return false; 
      if (hasError() && 
        
        !getError().equals(other.getError()))
        return false; 
      if (!getFileList().equals(other.getFileList()))
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
      if (hasError()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getError().hashCode();
      } 
      if (getFileCount() > 0) {
        hash = 37 * hash + 15;
        hash = 53 * hash + getFileList().hashCode();
      } 
      hash = 29 * hash + this.unknownFields.hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static CodeGeneratorResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (CodeGeneratorResponse)PARSER.parseFrom(data);
    }
    
    public static CodeGeneratorResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CodeGeneratorResponse)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CodeGeneratorResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (CodeGeneratorResponse)PARSER.parseFrom(data);
    }
    
    public static CodeGeneratorResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CodeGeneratorResponse)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CodeGeneratorResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (CodeGeneratorResponse)PARSER.parseFrom(data);
    }
    
    public static CodeGeneratorResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CodeGeneratorResponse)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CodeGeneratorResponse parseFrom(InputStream input) throws IOException {
      return 
        (CodeGeneratorResponse)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CodeGeneratorResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CodeGeneratorResponse)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CodeGeneratorResponse parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (CodeGeneratorResponse)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static CodeGeneratorResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CodeGeneratorResponse)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CodeGeneratorResponse parseFrom(CodedInputStream input) throws IOException {
      return 
        (CodeGeneratorResponse)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CodeGeneratorResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CodeGeneratorResponse)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(CodeGeneratorResponse prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CodeGeneratorResponseOrBuilder {
      private int bitField0_;
      
      private Object error_;
      
      private List<File> file_;
      
      private RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> fileBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
      }
      
      protected FieldAccessorTable internalGetFieldAccessorTable() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(CodeGeneratorResponse.class, Builder.class);
      }
      
      private Builder() {
        this.error_ = "";
        this
          .file_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        this.error_ = "";
        this.file_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (CodeGeneratorResponse.alwaysUseFieldBuilders)
          getFileFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        this.error_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        if (this.fileBuilder_ == null) {
          this.file_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFD;
        } else {
          this.fileBuilder_.clear();
        } 
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
      }
      
      public CodeGeneratorResponse getDefaultInstanceForType() {
        return CodeGeneratorResponse.getDefaultInstance();
      }
      
      public CodeGeneratorResponse build() {
        CodeGeneratorResponse result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public CodeGeneratorResponse buildPartial() {
        CodeGeneratorResponse result = new CodeGeneratorResponse(this);
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0)
          to_bitField0_ |= 0x1; 
        result.error_ = this.error_;
        if (this.fileBuilder_ == null) {
          if ((this.bitField0_ & 0x2) != 0) {
            this.file_ = Collections.unmodifiableList(this.file_);
            this.bitField0_ &= 0xFFFFFFFD;
          } 
          result.file_ = this.file_;
        } else {
          result.file_ = this.fileBuilder_.build();
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
        if (other instanceof CodeGeneratorResponse)
          return mergeFrom((CodeGeneratorResponse)other);
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(CodeGeneratorResponse other) {
        if (other == CodeGeneratorResponse.getDefaultInstance())
          return this; 
        if (other.hasError()) {
          this.bitField0_ |= 0x1;
          this.error_ = other.error_;
          onChanged();
        } 
        if (this.fileBuilder_ == null) {
          if (!other.file_.isEmpty()) {
            if (this.file_.isEmpty()) {
              this.file_ = other.file_;
              this.bitField0_ &= 0xFFFFFFFD;
            } else {
              ensureFileIsMutable();
              this.file_.addAll(other.file_);
            } 
            onChanged();
          } 
        } else if (!other.file_.isEmpty()) {
          if (this.fileBuilder_.isEmpty()) {
            this.fileBuilder_.dispose();
            this.fileBuilder_ = null;
            this.file_ = other.file_;
            this.bitField0_ &= 0xFFFFFFFD;
            this.fileBuilder_ = CodeGeneratorResponse.alwaysUseFieldBuilders ? getFileFieldBuilder() : null;
          } else {
            this.fileBuilder_.addAllMessages(other.file_);
          } 
        } 
        mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        CodeGeneratorResponse parsedMessage = null;
        try {
          parsedMessage = (CodeGeneratorResponse) CodeGeneratorResponse.PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          parsedMessage = (CodeGeneratorResponse)e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null)
            mergeFrom(parsedMessage); 
        } 
        return this;
      }
      
      public boolean hasError() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public String getError() {
        Object ref = this.error_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8())
            this.error_ = s; 
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getErrorBytes() {
        Object ref = this.error_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.error_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setError(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.error_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearError() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.error_ = CodeGeneratorResponse.getDefaultInstance().getError();
        onChanged();
        return this;
      }
      
      public Builder setErrorBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.error_ = value;
        onChanged();
        return this;
      }
      
      private void ensureFileIsMutable() {
        if ((this.bitField0_ & 0x2) == 0) {
          this.file_ = new ArrayList<>(this.file_);
          this.bitField0_ |= 0x2;
        } 
      }
      
      public List<File> getFileList() {
        if (this.fileBuilder_ == null)
          return Collections.unmodifiableList(this.file_); 
        return this.fileBuilder_.getMessageList();
      }
      
      public int getFileCount() {
        if (this.fileBuilder_ == null)
          return this.file_.size(); 
        return this.fileBuilder_.getCount();
      }
      
      public File getFile(int index) {
        if (this.fileBuilder_ == null)
          return this.file_.get(index); 
        return (File)this.fileBuilder_.getMessage(index);
      }
      
      public Builder setFile(int index, File value) {
        if (this.fileBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureFileIsMutable();
          this.file_.set(index, value);
          onChanged();
        } else {
          this.fileBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setFile(int index, File.Builder builderForValue) {
        if (this.fileBuilder_ == null) {
          ensureFileIsMutable();
          this.file_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.fileBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addFile(File value) {
        if (this.fileBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureFileIsMutable();
          this.file_.add(value);
          onChanged();
        } else {
          this.fileBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addFile(int index, File value) {
        if (this.fileBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureFileIsMutable();
          this.file_.add(index, value);
          onChanged();
        } else {
          this.fileBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addFile(File.Builder builderForValue) {
        if (this.fileBuilder_ == null) {
          ensureFileIsMutable();
          this.file_.add(builderForValue.build());
          onChanged();
        } else {
          this.fileBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addFile(int index, File.Builder builderForValue) {
        if (this.fileBuilder_ == null) {
          ensureFileIsMutable();
          this.file_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.fileBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllFile(Iterable<? extends File> values) {
        if (this.fileBuilder_ == null) {
          ensureFileIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.file_);
          onChanged();
        } else {
          this.fileBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearFile() {
        if (this.fileBuilder_ == null) {
          this.file_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFD;
          onChanged();
        } else {
          this.fileBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeFile(int index) {
        if (this.fileBuilder_ == null) {
          ensureFileIsMutable();
          this.file_.remove(index);
          onChanged();
        } else {
          this.fileBuilder_.remove(index);
        } 
        return this;
      }
      
      public File.Builder getFileBuilder(int index) {
        return (File.Builder)getFileFieldBuilder().getBuilder(index);
      }
      
      public FileOrBuilder getFileOrBuilder(int index) {
        if (this.fileBuilder_ == null)
          return this.file_.get(index); 
        return (FileOrBuilder)this.fileBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends FileOrBuilder> getFileOrBuilderList() {
        if (this.fileBuilder_ != null)
          return this.fileBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.file_);
      }
      
      public File.Builder addFileBuilder() {
        return (File.Builder)getFileFieldBuilder().addBuilder(
            (AbstractMessage) File.getDefaultInstance());
      }
      
      public File.Builder addFileBuilder(int index) {
        return (File.Builder)getFileFieldBuilder().addBuilder(index,
            (AbstractMessage) File.getDefaultInstance());
      }
      
      public List<File.Builder> getFileBuilderList() {
        return getFileFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> getFileFieldBuilder() {
        if (this.fileBuilder_ == null) {
          this
            
            .fileBuilder_ = new RepeatedFieldBuilderV3(this.file_, ((this.bitField0_ & 0x2) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.file_ = null;
        } 
        return this.fileBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final CodeGeneratorResponse DEFAULT_INSTANCE = new CodeGeneratorResponse();
    
    public static CodeGeneratorResponse getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    @Deprecated
    public static final Parser<CodeGeneratorResponse> PARSER = (Parser<CodeGeneratorResponse>)new AbstractParser<CodeGeneratorResponse>() {
        public CodeGeneratorResponse parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return new CodeGeneratorResponse(input, extensionRegistry);
        }
      };
    
    public static Parser<CodeGeneratorResponse> parser() {
      return PARSER;
    }
    
    public Parser<CodeGeneratorResponse> getParserForType() {
      return PARSER;
    }
    
    public CodeGeneratorResponse getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public static interface FileOrBuilder extends MessageOrBuilder {
      boolean hasName();
      
      String getName();
      
      ByteString getNameBytes();
      
      boolean hasInsertionPoint();
      
      String getInsertionPoint();
      
      ByteString getInsertionPointBytes();
      
      boolean hasContent();
      
      String getContent();
      
      ByteString getContentBytes();
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n%google/protobuf/compiler/plugin.proto\022\030google.protobuf.compiler\032 google/protobuf/descriptor.proto\"F\n\007Version\022\r\n\005major\030\001 \001(\005\022\r\n\005minor\030\002 \001(\005\022\r\n\005patch\030\003 \001(\005\022\016\n\006suffix\030\004 \001(\t\"º\001\n\024CodeGeneratorRequest\022\030\n\020file_to_generate\030\001 \003(\t\022\021\n\tparameter\030\002 \001(\t\0228\n\nproto_file\030\017 \003(\0132$.google.protobuf.FileDescriptorProto\022;\n\020compiler_version\030\003 \001(\0132!.google.protobuf.compiler.Version\"ª\001\n\025CodeGeneratorResponse\022\r\n\005error\030\001 \001(\t\022B\n\004file\030\017 \003(\01324.google.protobuf.compiler.CodeGeneratorResponse.File\032>\n\004File\022\f\n\004name\030\001 \001(\t\022\027\n\017insertion_point\030\002 \001(\t\022\017\n\007content\030\017 \001(\tBg\n\034com.google.protobuf.compilerB\fPluginProtosZ9github.com/golang/protobuf/protoc-gen-go/plugin;plugin_go" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { DescriptorProtos.getDescriptor() });
    internal_static_google_protobuf_compiler_Version_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_google_protobuf_compiler_Version_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_compiler_Version_descriptor, new String[] { "Major", "Minor", "Patch", "Suffix" });
    internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor = getDescriptor().getMessageTypes().get(1);
    internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor, new String[] { "FileToGenerate", "Parameter", "ProtoFile", "CompilerVersion" });
    internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor = getDescriptor().getMessageTypes().get(2);
    internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor, new String[] { "Error", "File" });
    internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor = internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor.getNestedTypes().get(0);
    internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor, new String[] { "Name", "InsertionPoint", "Content" });
    DescriptorProtos.getDescriptor();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\compiler\PluginProtos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */