package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

public final class UnknownFieldSet implements MessageLite {
  private UnknownFieldSet() {
    this.fields = null;
    this.fieldsDescending = null;
  }
  
  public static Builder newBuilder() {
    return Builder.create();
  }
  
  public static Builder newBuilder(UnknownFieldSet copyFrom) {
    return newBuilder().mergeFrom(copyFrom);
  }
  
  public static UnknownFieldSet getDefaultInstance() {
    return defaultInstance;
  }
  
  public UnknownFieldSet getDefaultInstanceForType() {
    return defaultInstance;
  }
  
  private static final UnknownFieldSet defaultInstance = new UnknownFieldSet(
      
      Collections.emptyMap(), Collections.emptyMap());
  
  private final Map<Integer, Field> fields;
  
  private final Map<Integer, Field> fieldsDescending;
  
  UnknownFieldSet(Map<Integer, Field> fields, Map<Integer, Field> fieldsDescending) {
    this.fields = fields;
    this.fieldsDescending = fieldsDescending;
  }
  
  public boolean equals(Object other) {
    if (this == other)
      return true; 
    return (other instanceof UnknownFieldSet && this.fields.equals(((UnknownFieldSet)other).fields));
  }
  
  public int hashCode() {
    return this.fields.hashCode();
  }
  
  public Map<Integer, Field> asMap() {
    return this.fields;
  }
  
  public boolean hasField(int number) {
    return this.fields.containsKey(Integer.valueOf(number));
  }
  
  public Field getField(int number) {
    Field result = this.fields.get(Integer.valueOf(number));
    return (result == null) ? Field.getDefaultInstance() : result;
  }
  
  public void writeTo(CodedOutputStream output) throws IOException {
    for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
      Field field = entry.getValue();
      field.writeTo(((Integer)entry.getKey()).intValue(), output);
    } 
  }
  
  public String toString() {
    return TextFormat.printer().printToString(this);
  }
  
  public ByteString toByteString() {
    try {
      ByteString.CodedBuilder out = ByteString.newCodedBuilder(getSerializedSize());
      writeTo(out.getCodedOutput());
      return out.build();
    } catch (IOException e) {
      throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e);
    } 
  }
  
  public byte[] toByteArray() {
    try {
      byte[] result = new byte[getSerializedSize()];
      CodedOutputStream output = CodedOutputStream.newInstance(result);
      writeTo(output);
      output.checkNoSpaceLeft();
      return result;
    } catch (IOException e) {
      throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
    } 
  }
  
  public void writeTo(OutputStream output) throws IOException {
    CodedOutputStream codedOutput = CodedOutputStream.newInstance(output);
    writeTo(codedOutput);
    codedOutput.flush();
  }
  
  public void writeDelimitedTo(OutputStream output) throws IOException {
    CodedOutputStream codedOutput = CodedOutputStream.newInstance(output);
    codedOutput.writeRawVarint32(getSerializedSize());
    writeTo(codedOutput);
    codedOutput.flush();
  }
  
  public int getSerializedSize() {
    int result = 0;
    for (Map.Entry<Integer, Field> entry : this.fields.entrySet())
      result += ((Field)entry.getValue()).getSerializedSize(((Integer)entry.getKey()).intValue()); 
    return result;
  }
  
  public void writeAsMessageSetTo(CodedOutputStream output) throws IOException {
    for (Map.Entry<Integer, Field> entry : this.fields.entrySet())
      ((Field)entry.getValue()).writeAsMessageSetExtensionTo(((Integer)entry.getKey()).intValue(), output); 
  }
  
  void writeTo(Writer writer) throws IOException {
    if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
      for (Map.Entry<Integer, Field> entry : this.fieldsDescending.entrySet())
        ((Field)entry.getValue()).writeTo(((Integer)entry.getKey()).intValue(), writer); 
    } else {
      for (Map.Entry<Integer, Field> entry : this.fields.entrySet())
        ((Field)entry.getValue()).writeTo(((Integer)entry.getKey()).intValue(), writer); 
    } 
  }
  
  void writeAsMessageSetTo(Writer writer) throws IOException {
    if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
      for (Map.Entry<Integer, Field> entry : this.fieldsDescending.entrySet())
        ((Field)entry.getValue()).writeAsMessageSetExtensionTo(((Integer)entry.getKey()).intValue(), writer); 
    } else {
      for (Map.Entry<Integer, Field> entry : this.fields.entrySet())
        ((Field)entry.getValue()).writeAsMessageSetExtensionTo(((Integer)entry.getKey()).intValue(), writer); 
    } 
  }
  
  public int getSerializedSizeAsMessageSet() {
    int result = 0;
    for (Map.Entry<Integer, Field> entry : this.fields.entrySet())
      result += ((Field)entry.getValue()).getSerializedSizeAsMessageSetExtension(((Integer)entry.getKey()).intValue()); 
    return result;
  }
  
  public boolean isInitialized() {
    return true;
  }
  
  public static UnknownFieldSet parseFrom(CodedInputStream input) throws IOException {
    return newBuilder().mergeFrom(input).build();
  }
  
  public static UnknownFieldSet parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).build();
  }
  
  public static UnknownFieldSet parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).build();
  }
  
  public static UnknownFieldSet parseFrom(InputStream input) throws IOException {
    return newBuilder().mergeFrom(input).build();
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public Builder toBuilder() {
    return newBuilder().mergeFrom(this);
  }
  
  public static final class Builder implements MessageLite.Builder {
    private Map<Integer, Field> fields;
    
    private int lastFieldNumber;
    
    private Field.Builder lastField;
    
    private static Builder create() {
      Builder builder = new Builder();
      builder.reinitialize();
      return builder;
    }
    
    private Field.Builder getFieldBuilder(int number) {
      if (this.lastField != null) {
        if (number == this.lastFieldNumber)
          return this.lastField; 
        addField(this.lastFieldNumber, this.lastField.build());
      } 
      if (number == 0)
        return null; 
      Field existing = this.fields.get(Integer.valueOf(number));
      this.lastFieldNumber = number;
      this.lastField = Field.newBuilder();
      if (existing != null)
        this.lastField.mergeFrom(existing); 
      return this.lastField;
    }
    
    public UnknownFieldSet build() {
      UnknownFieldSet result;
      getFieldBuilder(0);
      if (this.fields.isEmpty()) {
        result = UnknownFieldSet.getDefaultInstance();
      } else {
        Map<Integer, Field> descendingFields = null;
        descendingFields = Collections.unmodifiableMap(((TreeMap<? extends Integer, ? extends Field>)this.fields).descendingMap());
        result = new UnknownFieldSet(Collections.unmodifiableMap(this.fields), descendingFields);
      } 
      this.fields = null;
      return result;
    }
    
    public UnknownFieldSet buildPartial() {
      return build();
    }
    
    public Builder clone() {
      getFieldBuilder(0);
      Map<Integer, Field> descendingFields = null;
      descendingFields = Collections.unmodifiableMap(((TreeMap<? extends Integer, ? extends Field>)this.fields).descendingMap());
      return UnknownFieldSet.newBuilder().mergeFrom(new UnknownFieldSet(this.fields, descendingFields));
    }
    
    public UnknownFieldSet getDefaultInstanceForType() {
      return UnknownFieldSet.getDefaultInstance();
    }
    
    private void reinitialize() {
      this.fields = Collections.emptyMap();
      this.lastFieldNumber = 0;
      this.lastField = null;
    }
    
    public Builder clear() {
      reinitialize();
      return this;
    }
    
    public Builder clearField(int number) {
      if (number == 0)
        throw new IllegalArgumentException("Zero is not a valid field number."); 
      if (this.lastField != null && this.lastFieldNumber == number) {
        this.lastField = null;
        this.lastFieldNumber = 0;
      } 
      if (this.fields.containsKey(Integer.valueOf(number)))
        this.fields.remove(Integer.valueOf(number)); 
      return this;
    }
    
    public Builder mergeFrom(UnknownFieldSet other) {
      if (other != UnknownFieldSet.getDefaultInstance())
        for (Map.Entry<Integer, Field> entry : (Iterable<Map.Entry<Integer, Field>>)other.fields.entrySet())
          mergeField(((Integer)entry.getKey()).intValue(), entry.getValue());  
      return this;
    }
    
    public Builder mergeField(int number, Field field) {
      if (number == 0)
        throw new IllegalArgumentException("Zero is not a valid field number."); 
      if (hasField(number)) {
        getFieldBuilder(number).mergeFrom(field);
      } else {
        addField(number, field);
      } 
      return this;
    }
    
    public Builder mergeVarintField(int number, int value) {
      if (number == 0)
        throw new IllegalArgumentException("Zero is not a valid field number."); 
      getFieldBuilder(number).addVarint(value);
      return this;
    }
    
    public Builder mergeLengthDelimitedField(int number, ByteString value) {
      if (number == 0)
        throw new IllegalArgumentException("Zero is not a valid field number."); 
      getFieldBuilder(number).addLengthDelimited(value);
      return this;
    }
    
    public boolean hasField(int number) {
      if (number == 0)
        throw new IllegalArgumentException("Zero is not a valid field number."); 
      return (number == this.lastFieldNumber || this.fields.containsKey(Integer.valueOf(number)));
    }
    
    public Builder addField(int number, Field field) {
      if (number == 0)
        throw new IllegalArgumentException("Zero is not a valid field number."); 
      if (this.lastField != null && this.lastFieldNumber == number) {
        this.lastField = null;
        this.lastFieldNumber = 0;
      } 
      if (this.fields.isEmpty())
        this.fields = new TreeMap<>(); 
      this.fields.put(Integer.valueOf(number), field);
      return this;
    }
    
    public Map<Integer, Field> asMap() {
      getFieldBuilder(0);
      return Collections.unmodifiableMap(this.fields);
    }
    
    public Builder mergeFrom(CodedInputStream input) throws IOException {
      int tag;
      do {
        tag = input.readTag();
      } while (tag != 0 && mergeFieldFrom(tag, input));
      return this;
    }
    
    public boolean mergeFieldFrom(int tag, CodedInputStream input) throws IOException {
      Builder subBuilder;
      int number = WireFormat.getTagFieldNumber(tag);
      switch (WireFormat.getTagWireType(tag)) {
        case 0:
          getFieldBuilder(number).addVarint(input.readInt64());
          return true;
        case 1:
          getFieldBuilder(number).addFixed64(input.readFixed64());
          return true;
        case 2:
          getFieldBuilder(number).addLengthDelimited(input.readBytes());
          return true;
        case 3:
          subBuilder = UnknownFieldSet.newBuilder();
          input.readGroup(number, subBuilder, ExtensionRegistry.getEmptyRegistry());
          getFieldBuilder(number).addGroup(subBuilder.build());
          return true;
        case 4:
          return false;
        case 5:
          getFieldBuilder(number).addFixed32(input.readFixed32());
          return true;
      } 
      throw InvalidProtocolBufferException.invalidWireType();
    }
    
    public Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
      try {
        CodedInputStream input = data.newCodedInput();
        mergeFrom(input);
        input.checkLastTagWas(0);
        return this;
      } catch (InvalidProtocolBufferException e) {
        throw e;
      } catch (IOException e) {
        throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e);
      } 
    }
    
    public Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException {
      try {
        CodedInputStream input = CodedInputStream.newInstance(data);
        mergeFrom(input);
        input.checkLastTagWas(0);
        return this;
      } catch (InvalidProtocolBufferException e) {
        throw e;
      } catch (IOException e) {
        throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e);
      } 
    }
    
    public Builder mergeFrom(InputStream input) throws IOException {
      CodedInputStream codedInput = CodedInputStream.newInstance(input);
      mergeFrom(codedInput);
      codedInput.checkLastTagWas(0);
      return this;
    }
    
    public boolean mergeDelimitedFrom(InputStream input) throws IOException {
      int firstByte = input.read();
      if (firstByte == -1)
        return false; 
      int size = CodedInputStream.readRawVarint32(firstByte, input);
      InputStream limitedInput = new AbstractMessageLite.Builder.LimitedInputStream(input, size);
      mergeFrom(limitedInput);
      return true;
    }
    
    public boolean mergeDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return mergeDelimitedFrom(input);
    }
    
    public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return mergeFrom(input);
    }
    
    public Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return mergeFrom(data);
    }
    
    public Builder mergeFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
      try {
        CodedInputStream input = CodedInputStream.newInstance(data, off, len);
        mergeFrom(input);
        input.checkLastTagWas(0);
        return this;
      } catch (InvalidProtocolBufferException e) {
        throw e;
      } catch (IOException e) {
        throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e);
      } 
    }
    
    public Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return mergeFrom(data);
    }
    
    public Builder mergeFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return mergeFrom(data, off, len);
    }
    
    public Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return mergeFrom(input);
    }
    
    public Builder mergeFrom(MessageLite m) {
      if (m instanceof UnknownFieldSet)
        return mergeFrom((UnknownFieldSet)m); 
      throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
    
    public boolean isInitialized() {
      return true;
    }
  }
  
  public static final class Field {
    private Field() {}
    
    public static Builder newBuilder() {
      return Builder.create();
    }
    
    public static Builder newBuilder(Field copyFrom) {
      return newBuilder().mergeFrom(copyFrom);
    }
    
    public static Field getDefaultInstance() {
      return fieldDefaultInstance;
    }
    
    private static final Field fieldDefaultInstance = newBuilder().build();
    
    private List<Long> varint;
    
    private List<Integer> fixed32;
    
    private List<Long> fixed64;
    
    private List<ByteString> lengthDelimited;
    
    private List<UnknownFieldSet> group;
    
    public List<Long> getVarintList() {
      return this.varint;
    }
    
    public List<Integer> getFixed32List() {
      return this.fixed32;
    }
    
    public List<Long> getFixed64List() {
      return this.fixed64;
    }
    
    public List<ByteString> getLengthDelimitedList() {
      return this.lengthDelimited;
    }
    
    public List<UnknownFieldSet> getGroupList() {
      return this.group;
    }
    
    public boolean equals(Object other) {
      if (this == other)
        return true; 
      if (!(other instanceof Field))
        return false; 
      return Arrays.equals(getIdentityArray(), ((Field)other).getIdentityArray());
    }
    
    public int hashCode() {
      return Arrays.hashCode(getIdentityArray());
    }
    
    private Object[] getIdentityArray() {
      return new Object[] { this.varint, this.fixed32, this.fixed64, this.lengthDelimited, this.group };
    }
    
    public ByteString toByteString(int fieldNumber) {
      try {
        ByteString.CodedBuilder out = ByteString.newCodedBuilder(getSerializedSize(fieldNumber));
        writeTo(fieldNumber, out.getCodedOutput());
        return out.build();
      } catch (IOException e) {
        throw new RuntimeException("Serializing to a ByteString should never fail with an IOException", e);
      } 
    }
    
    public void writeTo(int fieldNumber, CodedOutputStream output) throws IOException {
      for (null = this.varint.iterator(); null.hasNext(); ) {
        long value = ((Long)null.next()).longValue();
        output.writeUInt64(fieldNumber, value);
      } 
      for (null = (Iterator)this.fixed32.iterator(); null.hasNext(); ) {
        int value = ((Integer)null.next()).intValue();
        output.writeFixed32(fieldNumber, value);
      } 
      for (null = this.fixed64.iterator(); null.hasNext(); ) {
        long value = ((Long)null.next()).longValue();
        output.writeFixed64(fieldNumber, value);
      } 
      for (ByteString value : this.lengthDelimited)
        output.writeBytes(fieldNumber, value); 
      for (UnknownFieldSet value : this.group)
        output.writeGroup(fieldNumber, value); 
    }
    
    public int getSerializedSize(int fieldNumber) {
      int result = 0;
      for (null = this.varint.iterator(); null.hasNext(); ) {
        long value = ((Long)null.next()).longValue();
        result += CodedOutputStream.computeUInt64Size(fieldNumber, value);
      } 
      for (null = (Iterator)this.fixed32.iterator(); null.hasNext(); ) {
        int value = ((Integer)null.next()).intValue();
        result += CodedOutputStream.computeFixed32Size(fieldNumber, value);
      } 
      for (null = this.fixed64.iterator(); null.hasNext(); ) {
        long value = ((Long)null.next()).longValue();
        result += CodedOutputStream.computeFixed64Size(fieldNumber, value);
      } 
      for (ByteString value : this.lengthDelimited)
        result += CodedOutputStream.computeBytesSize(fieldNumber, value); 
      for (UnknownFieldSet value : this.group)
        result += CodedOutputStream.computeGroupSize(fieldNumber, value); 
      return result;
    }
    
    public void writeAsMessageSetExtensionTo(int fieldNumber, CodedOutputStream output) throws IOException {
      for (ByteString value : this.lengthDelimited)
        output.writeRawMessageSetExtension(fieldNumber, value); 
    }
    
    void writeTo(int fieldNumber, Writer writer) throws IOException {
      writer.writeInt64List(fieldNumber, this.varint, false);
      writer.writeFixed32List(fieldNumber, this.fixed32, false);
      writer.writeFixed64List(fieldNumber, this.fixed64, false);
      writer.writeBytesList(fieldNumber, this.lengthDelimited);
      if (writer.fieldOrder() == Writer.FieldOrder.ASCENDING) {
        for (int i = 0; i < this.group.size(); i++) {
          writer.writeStartGroup(fieldNumber);
          ((UnknownFieldSet)this.group.get(i)).writeTo(writer);
          writer.writeEndGroup(fieldNumber);
        } 
      } else {
        for (int i = this.group.size() - 1; i >= 0; i--) {
          writer.writeEndGroup(fieldNumber);
          ((UnknownFieldSet)this.group.get(i)).writeTo(writer);
          writer.writeStartGroup(fieldNumber);
        } 
      } 
    }
    
    private void writeAsMessageSetExtensionTo(int fieldNumber, Writer writer) throws IOException {
      if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
        ListIterator<ByteString> iter = this.lengthDelimited.listIterator(this.lengthDelimited.size());
        while (iter.hasPrevious())
          writer.writeMessageSetItem(fieldNumber, iter.previous()); 
      } else {
        for (ByteString value : this.lengthDelimited)
          writer.writeMessageSetItem(fieldNumber, value); 
      } 
    }
    
    public int getSerializedSizeAsMessageSetExtension(int fieldNumber) {
      int result = 0;
      for (ByteString value : this.lengthDelimited)
        result += CodedOutputStream.computeRawMessageSetExtensionSize(fieldNumber, value); 
      return result;
    }
    
    public static final class Builder {
      private Field result;
      
      private static Builder create() {
        Builder builder = new Builder();
        builder.result = new Field();
        return builder;
      }
      
      public Field build() {
        if (this.result.varint == null) {
          this.result.varint = Collections.emptyList();
        } else {
          this.result.varint = Collections.unmodifiableList(this.result.varint);
        } 
        if (this.result.fixed32 == null) {
          this.result.fixed32 = Collections.emptyList();
        } else {
          this.result.fixed32 = Collections.unmodifiableList(this.result.fixed32);
        } 
        if (this.result.fixed64 == null) {
          this.result.fixed64 = Collections.emptyList();
        } else {
          this.result.fixed64 = Collections.unmodifiableList(this.result.fixed64);
        } 
        if (this.result.lengthDelimited == null) {
          this.result.lengthDelimited = Collections.emptyList();
        } else {
          this.result.lengthDelimited = Collections.unmodifiableList(this.result.lengthDelimited);
        } 
        if (this.result.group == null) {
          this.result.group = Collections.emptyList();
        } else {
          this.result.group = Collections.unmodifiableList(this.result.group);
        } 
        Field returnMe = this.result;
        this.result = null;
        return returnMe;
      }
      
      public Builder clear() {
        this.result = new Field();
        return this;
      }
      
      public Builder mergeFrom(Field other) {
        if (!other.varint.isEmpty()) {
          if (this.result.varint == null)
            this.result.varint = new ArrayList(); 
          this.result.varint.addAll(other.varint);
        } 
        if (!other.fixed32.isEmpty()) {
          if (this.result.fixed32 == null)
            this.result.fixed32 = new ArrayList(); 
          this.result.fixed32.addAll(other.fixed32);
        } 
        if (!other.fixed64.isEmpty()) {
          if (this.result.fixed64 == null)
            this.result.fixed64 = new ArrayList(); 
          this.result.fixed64.addAll(other.fixed64);
        } 
        if (!other.lengthDelimited.isEmpty()) {
          if (this.result.lengthDelimited == null)
            this.result.lengthDelimited = new ArrayList(); 
          this.result.lengthDelimited.addAll(other.lengthDelimited);
        } 
        if (!other.group.isEmpty()) {
          if (this.result.group == null)
            this.result.group = new ArrayList(); 
          this.result.group.addAll(other.group);
        } 
        return this;
      }
      
      public Builder addVarint(long value) {
        if (this.result.varint == null)
          this.result.varint = new ArrayList(); 
        this.result.varint.add(Long.valueOf(value));
        return this;
      }
      
      public Builder addFixed32(int value) {
        if (this.result.fixed32 == null)
          this.result.fixed32 = new ArrayList(); 
        this.result.fixed32.add(Integer.valueOf(value));
        return this;
      }
      
      public Builder addFixed64(long value) {
        if (this.result.fixed64 == null)
          this.result.fixed64 = new ArrayList(); 
        this.result.fixed64.add(Long.valueOf(value));
        return this;
      }
      
      public Builder addLengthDelimited(ByteString value) {
        if (this.result.lengthDelimited == null)
          this.result.lengthDelimited = new ArrayList(); 
        this.result.lengthDelimited.add(value);
        return this;
      }
      
      public Builder addGroup(UnknownFieldSet value) {
        if (this.result.group == null)
          this.result.group = new ArrayList(); 
        this.result.group.add(value);
        return this;
      }
    }
  }
  
  public static final class Parser extends AbstractParser<UnknownFieldSet> {
    public UnknownFieldSet parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      Builder builder = UnknownFieldSet.newBuilder();
      try {
        builder.mergeFrom(input);
      } catch (InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (IOException e) {
        throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(builder.buildPartial());
      } 
      return builder.buildPartial();
    }
  }
  
  private static final Parser PARSER = new Parser();
  
  public final Parser getParserForType() {
    return PARSER;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\UnknownFieldSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */