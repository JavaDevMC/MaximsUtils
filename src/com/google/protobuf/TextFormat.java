package com.google.protobuf;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextFormat {
  private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
  
  @Deprecated
  public static void print(MessageOrBuilder message, Appendable output) throws IOException {
    printer().print(message, output);
  }
  
  @Deprecated
  public static void print(UnknownFieldSet fields, Appendable output) throws IOException {
    printer().print(fields, output);
  }
  
  @Deprecated
  public static void printUnicode(MessageOrBuilder message, Appendable output) throws IOException {
    printer().escapingNonAscii(false).print(message, output);
  }
  
  @Deprecated
  public static void printUnicode(UnknownFieldSet fields, Appendable output) throws IOException {
    printer().escapingNonAscii(false).print(fields, output);
  }
  
  public static String shortDebugString(MessageOrBuilder message) {
    return printer().shortDebugString(message);
  }
  
  @Deprecated
  public static String shortDebugString(Descriptors.FieldDescriptor field, Object value) {
    return printer().shortDebugString(field, value);
  }
  
  @Deprecated
  public static String shortDebugString(UnknownFieldSet fields) {
    return printer().shortDebugString(fields);
  }
  
  @Deprecated
  public static String printToString(MessageOrBuilder message) {
    return printer().printToString(message);
  }
  
  @Deprecated
  public static String printToString(UnknownFieldSet fields) {
    return printer().printToString(fields);
  }
  
  @Deprecated
  public static String printToUnicodeString(MessageOrBuilder message) {
    return printer().escapingNonAscii(false).printToString(message);
  }
  
  @Deprecated
  public static String printToUnicodeString(UnknownFieldSet fields) {
    return printer().escapingNonAscii(false).printToString(fields);
  }
  
  @Deprecated
  public static void printField(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
    printer().printField(field, value, output);
  }
  
  @Deprecated
  public static String printFieldToString(Descriptors.FieldDescriptor field, Object value) {
    return printer().printFieldToString(field, value);
  }
  
  @Deprecated
  public static void printUnicodeFieldValue(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
    printer().escapingNonAscii(false).printFieldValue(field, value, output);
  }
  
  @Deprecated
  public static void printFieldValue(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
    printer().printFieldValue(field, value, output);
  }
  
  public static void printUnknownFieldValue(int tag, Object value, Appendable output) throws IOException {
    printUnknownFieldValue(tag, value, multiLineOutput(output));
  }
  
  private static void printUnknownFieldValue(int tag, Object value, TextGenerator generator) throws IOException {
    switch (WireFormat.getTagWireType(tag)) {
      case 0:
        generator.print(unsignedToString(((Long)value).longValue()));
        return;
      case 5:
        generator.print(String.format((Locale)null, "0x%08x", new Object[] { value }));
        return;
      case 1:
        generator.print(String.format((Locale)null, "0x%016x", new Object[] { value }));
        return;
      case 2:
        try {
          UnknownFieldSet message = UnknownFieldSet.parseFrom((ByteString)value);
          generator.print("{");
          generator.eol();
          generator.indent();
          Printer.printUnknownFields(message, generator);
          generator.outdent();
          generator.print("}");
        } catch (InvalidProtocolBufferException e) {
          generator.print("\"");
          generator.print(escapeBytes((ByteString)value));
          generator.print("\"");
        } 
        return;
      case 3:
        Printer.printUnknownFields((UnknownFieldSet)value, generator);
        return;
    } 
    throw new IllegalArgumentException("Bad tag: " + tag);
  }
  
  public static Printer printer() {
    return Printer.DEFAULT;
  }
  
  public static final class Printer {
    private static final Printer DEFAULT = new Printer(true, TypeRegistry.getEmptyTypeRegistry());
    
    private final boolean escapeNonAscii;
    
    private final TypeRegistry typeRegistry;
    
    private Printer(boolean escapeNonAscii, TypeRegistry typeRegistry) {
      this.escapeNonAscii = escapeNonAscii;
      this.typeRegistry = typeRegistry;
    }
    
    public Printer escapingNonAscii(boolean escapeNonAscii) {
      return new Printer(escapeNonAscii, this.typeRegistry);
    }
    
    public Printer usingTypeRegistry(TypeRegistry typeRegistry) {
      if (this.typeRegistry != TypeRegistry.getEmptyTypeRegistry())
        throw new IllegalArgumentException("Only one typeRegistry is allowed."); 
      return new Printer(this.escapeNonAscii, typeRegistry);
    }
    
    public void print(MessageOrBuilder message, Appendable output) throws IOException {
      print(message, TextFormat.multiLineOutput(output));
    }
    
    public void print(UnknownFieldSet fields, Appendable output) throws IOException {
      printUnknownFields(fields, TextFormat.multiLineOutput(output));
    }
    
    private void print(MessageOrBuilder message, TextGenerator generator) throws IOException {
      if (message.getDescriptorForType().getFullName().equals("google.protobuf.Any") && 
        printAny(message, generator))
        return; 
      printMessage(message, generator);
    }
    
    private boolean printAny(MessageOrBuilder message, TextGenerator generator) throws IOException {
      Descriptors.Descriptor messageType = message.getDescriptorForType();
      Descriptors.FieldDescriptor typeUrlField = messageType.findFieldByNumber(1);
      Descriptors.FieldDescriptor valueField = messageType.findFieldByNumber(2);
      if (typeUrlField == null || typeUrlField
        .getType() != Descriptors.FieldDescriptor.Type.STRING || valueField == null || valueField
        
        .getType() != Descriptors.FieldDescriptor.Type.BYTES)
        return false; 
      String typeUrl = (String)message.getField(typeUrlField);
      if (typeUrl.isEmpty())
        return false; 
      Object value = message.getField(valueField);
      Message.Builder contentBuilder = null;
      try {
        Descriptors.Descriptor contentType = this.typeRegistry.getDescriptorForTypeUrl(typeUrl);
        if (contentType == null)
          return false; 
        contentBuilder = DynamicMessage.getDefaultInstance(contentType).newBuilderForType();
        contentBuilder.mergeFrom((ByteString)value);
      } catch (InvalidProtocolBufferException e) {
        return false;
      } 
      generator.print("[");
      generator.print(typeUrl);
      generator.print("] {");
      generator.eol();
      generator.indent();
      print(contentBuilder, generator);
      generator.outdent();
      generator.print("}");
      generator.eol();
      return true;
    }
    
    public String printFieldToString(Descriptors.FieldDescriptor field, Object value) {
      try {
        StringBuilder text = new StringBuilder();
        printField(field, value, text);
        return text.toString();
      } catch (IOException e) {
        throw new IllegalStateException(e);
      } 
    }
    
    public void printField(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
      printField(field, value, TextFormat.multiLineOutput(output));
    }
    
    private void printField(Descriptors.FieldDescriptor field, Object value, TextGenerator generator) throws IOException {
      if (field.isRepeated()) {
        for (Object element : value)
          printSingleField(field, element, generator); 
      } else {
        printSingleField(field, value, generator);
      } 
    }
    
    public void printFieldValue(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
      printFieldValue(field, value, TextFormat.multiLineOutput(output));
    }
    
    private void printFieldValue(Descriptors.FieldDescriptor field, Object value, TextGenerator generator) throws IOException {
      switch (field.getType()) {
        case INT32:
        case SINT32:
        case SFIXED32:
          generator.print(((Integer)value).toString());
          break;
        case INT64:
        case SINT64:
        case SFIXED64:
          generator.print(((Long)value).toString());
          break;
        case BOOL:
          generator.print(((Boolean)value).toString());
          break;
        case FLOAT:
          generator.print(((Float)value).toString());
          break;
        case DOUBLE:
          generator.print(((Double)value).toString());
          break;
        case UINT32:
        case FIXED32:
          generator.print(TextFormat.unsignedToString(((Integer)value).intValue()));
          break;
        case UINT64:
        case FIXED64:
          generator.print(TextFormat.unsignedToString(((Long)value).longValue()));
          break;
        case STRING:
          generator.print("\"");
          generator.print(this.escapeNonAscii ? 
              
              TextFormatEscaper.escapeText((String)value) : 
              TextFormat.escapeDoubleQuotesAndBackslashes((String)value).replace("\n", "\\n"));
          generator.print("\"");
          break;
        case BYTES:
          generator.print("\"");
          if (value instanceof ByteString) {
            generator.print(TextFormat.escapeBytes((ByteString)value));
          } else {
            generator.print(TextFormat.escapeBytes((byte[])value));
          } 
          generator.print("\"");
          break;
        case ENUM:
          generator.print(((Descriptors.EnumValueDescriptor)value).getName());
          break;
        case MESSAGE:
        case GROUP:
          print((Message)value, generator);
          break;
      } 
    }
    
    public String printToString(MessageOrBuilder message) {
      try {
        StringBuilder text = new StringBuilder();
        print(message, text);
        return text.toString();
      } catch (IOException e) {
        throw new IllegalStateException(e);
      } 
    }
    
    public String printToString(UnknownFieldSet fields) {
      try {
        StringBuilder text = new StringBuilder();
        print(fields, text);
        return text.toString();
      } catch (IOException e) {
        throw new IllegalStateException(e);
      } 
    }
    
    public String shortDebugString(MessageOrBuilder message) {
      try {
        StringBuilder text = new StringBuilder();
        print(message, TextFormat.singleLineOutput(text));
        return text.toString();
      } catch (IOException e) {
        throw new IllegalStateException(e);
      } 
    }
    
    public String shortDebugString(Descriptors.FieldDescriptor field, Object value) {
      try {
        StringBuilder text = new StringBuilder();
        printField(field, value, TextFormat.singleLineOutput(text));
        return text.toString();
      } catch (IOException e) {
        throw new IllegalStateException(e);
      } 
    }
    
    public String shortDebugString(UnknownFieldSet fields) {
      try {
        StringBuilder text = new StringBuilder();
        printUnknownFields(fields, TextFormat.singleLineOutput(text));
        return text.toString();
      } catch (IOException e) {
        throw new IllegalStateException(e);
      } 
    }
    
    private static void printUnknownFieldValue(int tag, Object value, TextGenerator generator) throws IOException {
      switch (WireFormat.getTagWireType(tag)) {
        case 0:
          generator.print(TextFormat.unsignedToString(((Long)value).longValue()));
          return;
        case 5:
          generator.print(String.format((Locale)null, "0x%08x", new Object[] { value }));
          return;
        case 1:
          generator.print(String.format((Locale)null, "0x%016x", new Object[] { value }));
          return;
        case 2:
          try {
            UnknownFieldSet message = UnknownFieldSet.parseFrom((ByteString)value);
            generator.print("{");
            generator.eol();
            generator.indent();
            printUnknownFields(message, generator);
            generator.outdent();
            generator.print("}");
          } catch (InvalidProtocolBufferException e) {
            generator.print("\"");
            generator.print(TextFormat.escapeBytes((ByteString)value));
            generator.print("\"");
          } 
          return;
        case 3:
          printUnknownFields((UnknownFieldSet)value, generator);
          return;
      } 
      throw new IllegalArgumentException("Bad tag: " + tag);
    }
    
    private void printMessage(MessageOrBuilder message, TextGenerator generator) throws IOException {
      for (Map.Entry<Descriptors.FieldDescriptor, Object> field : message.getAllFields().entrySet())
        printField(field.getKey(), field.getValue(), generator); 
      printUnknownFields(message.getUnknownFields(), generator);
    }
    
    private void printSingleField(Descriptors.FieldDescriptor field, Object value, TextGenerator generator) throws IOException {
      if (field.isExtension()) {
        generator.print("[");
        if (field.getContainingType().getOptions().getMessageSetWireFormat() && field
          .getType() == Descriptors.FieldDescriptor.Type.MESSAGE && field
          .isOptional() && field
          
          .getExtensionScope() == field.getMessageType()) {
          generator.print(field.getMessageType().getFullName());
        } else {
          generator.print(field.getFullName());
        } 
        generator.print("]");
      } else if (field.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
        generator.print(field.getMessageType().getName());
      } else {
        generator.print(field.getName());
      } 
      if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        generator.print(" {");
        generator.eol();
        generator.indent();
      } else {
        generator.print(": ");
      } 
      printFieldValue(field, value, generator);
      if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        generator.outdent();
        generator.print("}");
      } 
      generator.eol();
    }
    
    private static void printUnknownFields(UnknownFieldSet unknownFields, TextGenerator generator) throws IOException {
      for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFields.asMap().entrySet()) {
        int number = ((Integer)entry.getKey()).intValue();
        UnknownFieldSet.Field field = entry.getValue();
        printUnknownField(number, 0, field.getVarintList(), generator);
        printUnknownField(number, 5, field.getFixed32List(), generator);
        printUnknownField(number, 1, field.getFixed64List(), generator);
        printUnknownField(number, 2, field
            
            .getLengthDelimitedList(), generator);
        for (UnknownFieldSet value : field.getGroupList()) {
          generator.print(((Integer)entry.getKey()).toString());
          generator.print(" {");
          generator.eol();
          generator.indent();
          printUnknownFields(value, generator);
          generator.outdent();
          generator.print("}");
          generator.eol();
        } 
      } 
    }
    
    private static void printUnknownField(int number, int wireType, List<?> values, TextGenerator generator) throws IOException {
      for (Object value : values) {
        generator.print(String.valueOf(number));
        generator.print(": ");
        printUnknownFieldValue(wireType, value, generator);
        generator.eol();
      } 
    }
  }
  
  public static String unsignedToString(int value) {
    if (value >= 0)
      return Integer.toString(value); 
    return Long.toString(value & 0xFFFFFFFFL);
  }
  
  public static String unsignedToString(long value) {
    if (value >= 0L)
      return Long.toString(value); 
    return BigInteger.valueOf(value & Long.MAX_VALUE).setBit(63).toString();
  }
  
  private static TextGenerator multiLineOutput(Appendable output) {
    return new TextGenerator(output, false);
  }
  
  private static TextGenerator singleLineOutput(Appendable output) {
    return new TextGenerator(output, true);
  }
  
  private static final class TextGenerator {
    private final Appendable output;
    
    private final StringBuilder indent = new StringBuilder();
    
    private final boolean singleLineMode;
    
    private boolean atStartOfLine = false;
    
    private TextGenerator(Appendable output, boolean singleLineMode) {
      this.output = output;
      this.singleLineMode = singleLineMode;
    }
    
    public void indent() {
      this.indent.append("  ");
    }
    
    public void outdent() {
      int length = this.indent.length();
      if (length == 0)
        throw new IllegalArgumentException(" Outdent() without matching Indent()."); 
      this.indent.setLength(length - 2);
    }
    
    public void print(CharSequence text) throws IOException {
      if (this.atStartOfLine) {
        this.atStartOfLine = false;
        this.output.append(this.singleLineMode ? " " : this.indent);
      } 
      this.output.append(text);
    }
    
    public void eol() throws IOException {
      if (!this.singleLineMode)
        this.output.append("\n"); 
      this.atStartOfLine = true;
    }
  }
  
  private static final class Tokenizer {
    private final CharSequence text;
    
    private final Matcher matcher;
    
    private String currentToken;
    
    private int pos = 0;
    
    private int line = 0;
    
    private int column = 0;
    
    private int previousLine = 0;
    
    private int previousColumn = 0;
    
    private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
    
    private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
    
    private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
    
    private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
    
    private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);
    
    private Tokenizer(CharSequence text) {
      this.text = text;
      this.matcher = WHITESPACE.matcher(text);
      skipWhitespace();
      nextToken();
    }
    
    int getPreviousLine() {
      return this.previousLine;
    }
    
    int getPreviousColumn() {
      return this.previousColumn;
    }
    
    int getLine() {
      return this.line;
    }
    
    int getColumn() {
      return this.column;
    }
    
    public boolean atEnd() {
      return (this.currentToken.length() == 0);
    }
    
    public void nextToken() {
      this.previousLine = this.line;
      this.previousColumn = this.column;
      while (this.pos < this.matcher.regionStart()) {
        if (this.text.charAt(this.pos) == '\n') {
          this.line++;
          this.column = 0;
        } else {
          this.column++;
        } 
        this.pos++;
      } 
      if (this.matcher.regionStart() == this.matcher.regionEnd()) {
        this.currentToken = "";
      } else {
        this.matcher.usePattern(TOKEN);
        if (this.matcher.lookingAt()) {
          this.currentToken = this.matcher.group();
          this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
        } else {
          this.currentToken = String.valueOf(this.text.charAt(this.pos));
          this.matcher.region(this.pos + 1, this.matcher.regionEnd());
        } 
        skipWhitespace();
      } 
    }
    
    private void skipWhitespace() {
      this.matcher.usePattern(WHITESPACE);
      if (this.matcher.lookingAt())
        this.matcher.region(this.matcher.end(), this.matcher.regionEnd()); 
    }
    
    public boolean tryConsume(String token) {
      if (this.currentToken.equals(token)) {
        nextToken();
        return true;
      } 
      return false;
    }
    
    public void consume(String token) throws ParseException {
      if (!tryConsume(token))
        throw parseException("Expected \"" + token + "\"."); 
    }
    
    public boolean lookingAtInteger() {
      if (this.currentToken.length() == 0)
        return false; 
      char c = this.currentToken.charAt(0);
      return (('0' <= c && c <= '9') || c == '-' || c == '+');
    }
    
    public boolean lookingAt(String text) {
      return this.currentToken.equals(text);
    }
    
    public String consumeIdentifier() throws ParseException {
      for (int i = 0; i < this.currentToken.length(); ) {
        char c = this.currentToken.charAt(i);
        if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || c == '_' || c == '.') {
          i++;
          continue;
        } 
        throw parseException("Expected identifier. Found '" + this.currentToken + "'");
      } 
      String result = this.currentToken;
      nextToken();
      return result;
    }
    
    public boolean tryConsumeIdentifier() {
      try {
        consumeIdentifier();
        return true;
      } catch (ParseException e) {
        return false;
      } 
    }
    
    public int consumeInt32() throws ParseException {
      try {
        int result = TextFormat.parseInt32(this.currentToken);
        nextToken();
        return result;
      } catch (NumberFormatException e) {
        throw integerParseException(e);
      } 
    }
    
    public int consumeUInt32() throws ParseException {
      try {
        int result = TextFormat.parseUInt32(this.currentToken);
        nextToken();
        return result;
      } catch (NumberFormatException e) {
        throw integerParseException(e);
      } 
    }
    
    public long consumeInt64() throws ParseException {
      try {
        long result = TextFormat.parseInt64(this.currentToken);
        nextToken();
        return result;
      } catch (NumberFormatException e) {
        throw integerParseException(e);
      } 
    }
    
    public boolean tryConsumeInt64() {
      try {
        consumeInt64();
        return true;
      } catch (ParseException e) {
        return false;
      } 
    }
    
    public long consumeUInt64() throws ParseException {
      try {
        long result = TextFormat.parseUInt64(this.currentToken);
        nextToken();
        return result;
      } catch (NumberFormatException e) {
        throw integerParseException(e);
      } 
    }
    
    public boolean tryConsumeUInt64() {
      try {
        consumeUInt64();
        return true;
      } catch (ParseException e) {
        return false;
      } 
    }
    
    public double consumeDouble() throws ParseException {
      if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
        boolean negative = this.currentToken.startsWith("-");
        nextToken();
        return negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
      } 
      if (this.currentToken.equalsIgnoreCase("nan")) {
        nextToken();
        return Double.NaN;
      } 
      try {
        double result = Double.parseDouble(this.currentToken);
        nextToken();
        return result;
      } catch (NumberFormatException e) {
        throw floatParseException(e);
      } 
    }
    
    public boolean tryConsumeDouble() {
      try {
        consumeDouble();
        return true;
      } catch (ParseException e) {
        return false;
      } 
    }
    
    public float consumeFloat() throws ParseException {
      if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
        boolean negative = this.currentToken.startsWith("-");
        nextToken();
        return negative ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
      } 
      if (FLOAT_NAN.matcher(this.currentToken).matches()) {
        nextToken();
        return Float.NaN;
      } 
      try {
        float result = Float.parseFloat(this.currentToken);
        nextToken();
        return result;
      } catch (NumberFormatException e) {
        throw floatParseException(e);
      } 
    }
    
    public boolean tryConsumeFloat() {
      try {
        consumeFloat();
        return true;
      } catch (ParseException e) {
        return false;
      } 
    }
    
    public boolean consumeBoolean() throws ParseException {
      if (this.currentToken.equals("true") || this.currentToken
        .equals("True") || this.currentToken
        .equals("t") || this.currentToken
        .equals("1")) {
        nextToken();
        return true;
      } 
      if (this.currentToken.equals("false") || this.currentToken
        .equals("False") || this.currentToken
        .equals("f") || this.currentToken
        .equals("0")) {
        nextToken();
        return false;
      } 
      throw parseException("Expected \"true\" or \"false\". Found \"" + this.currentToken + "\".");
    }
    
    public String consumeString() throws ParseException {
      return consumeByteString().toStringUtf8();
    }
    
    public boolean tryConsumeString() {
      try {
        consumeString();
        return true;
      } catch (ParseException e) {
        return false;
      } 
    }
    
    public ByteString consumeByteString() throws ParseException {
      List<ByteString> list = new ArrayList<>();
      consumeByteString(list);
      while (this.currentToken.startsWith("'") || this.currentToken.startsWith("\""))
        consumeByteString(list); 
      return ByteString.copyFrom(list);
    }
    
    private void consumeByteString(List<ByteString> list) throws ParseException {
      char quote = (this.currentToken.length() > 0) ? this.currentToken.charAt(0) : Character.MIN_VALUE;
      if (quote != '"' && quote != '\'')
        throw parseException("Expected string."); 
      if (this.currentToken.length() < 2 || this.currentToken.charAt(this.currentToken.length() - 1) != quote)
        throw parseException("String missing ending quote."); 
      try {
        String escaped = this.currentToken.substring(1, this.currentToken.length() - 1);
        ByteString result = TextFormat.unescapeBytes(escaped);
        nextToken();
        list.add(result);
      } catch (InvalidEscapeSequenceException e) {
        throw parseException(e.getMessage());
      } 
    }
    
    public ParseException parseException(String description) {
      return new ParseException(this.line + 1, this.column + 1, description);
    }
    
    public ParseException parseExceptionPreviousToken(String description) {
      return new ParseException(this.previousLine + 1, this.previousColumn + 1, description);
    }
    
    private ParseException integerParseException(NumberFormatException e) {
      return parseException("Couldn't parse integer: " + e.getMessage());
    }
    
    private ParseException floatParseException(NumberFormatException e) {
      return parseException("Couldn't parse number: " + e.getMessage());
    }
    
    public UnknownFieldParseException unknownFieldParseExceptionPreviousToken(String unknownField, String description) {
      return new UnknownFieldParseException(this.previousLine + 1, this.previousColumn + 1, unknownField, description);
    }
  }
  
  public static class ParseException extends IOException {
    private static final long serialVersionUID = 3196188060225107702L;
    
    private final int line;
    
    private final int column;
    
    public ParseException(String message) {
      this(-1, -1, message);
    }
    
    public ParseException(int line, int column, String message) {
      super(Integer.toString(line) + ":" + column + ": " + message);
      this.line = line;
      this.column = column;
    }
    
    public int getLine() {
      return this.line;
    }
    
    public int getColumn() {
      return this.column;
    }
  }
  
  public static class UnknownFieldParseException extends ParseException {
    private final String unknownField;
    
    public UnknownFieldParseException(String message) {
      this(-1, -1, "", message);
    }
    
    public UnknownFieldParseException(int line, int column, String unknownField, String message) {
      super(line, column, message);
      this.unknownField = unknownField;
    }
    
    public String getUnknownField() {
      return this.unknownField;
    }
  }
  
  private static final Parser PARSER = Parser.newBuilder().build();
  
  public static Parser getParser() {
    return PARSER;
  }
  
  public static void merge(Readable input, Message.Builder builder) throws IOException {
    PARSER.merge(input, builder);
  }
  
  public static void merge(CharSequence input, Message.Builder builder) throws ParseException {
    PARSER.merge(input, builder);
  }
  
  public static <T extends Message> T parse(CharSequence input, Class<T> protoClass) throws ParseException {
    Message.Builder builder = ((Message)Internal.<Message>getDefaultInstance(protoClass)).newBuilderForType();
    merge(input, builder);
    return (T)builder.build();
  }
  
  public static void merge(Readable input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
    PARSER.merge(input, extensionRegistry, builder);
  }
  
  public static void merge(CharSequence input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
    PARSER.merge(input, extensionRegistry, builder);
  }
  
  public static <T extends Message> T parse(CharSequence input, ExtensionRegistry extensionRegistry, Class<T> protoClass) throws ParseException {
    Message.Builder builder = ((Message)Internal.<Message>getDefaultInstance(protoClass)).newBuilderForType();
    merge(input, extensionRegistry, builder);
    return (T)builder.build();
  }
  
  public static class Parser {
    private final TypeRegistry typeRegistry;
    
    private final boolean allowUnknownFields;
    
    private final boolean allowUnknownEnumValues;
    
    private final boolean allowUnknownExtensions;
    
    private final SingularOverwritePolicy singularOverwritePolicy;
    
    private TextFormatParseInfoTree.Builder parseInfoTreeBuilder;
    
    private static final int BUFFER_SIZE = 4096;
    
    public enum SingularOverwritePolicy {
      ALLOW_SINGULAR_OVERWRITES, FORBID_SINGULAR_OVERWRITES;
    }
    
    private Parser(TypeRegistry typeRegistry, boolean allowUnknownFields, boolean allowUnknownEnumValues, boolean allowUnknownExtensions, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree.Builder parseInfoTreeBuilder) {
      this.typeRegistry = typeRegistry;
      this.allowUnknownFields = allowUnknownFields;
      this.allowUnknownEnumValues = allowUnknownEnumValues;
      this.allowUnknownExtensions = allowUnknownExtensions;
      this.singularOverwritePolicy = singularOverwritePolicy;
      this.parseInfoTreeBuilder = parseInfoTreeBuilder;
    }
    
    public static Builder newBuilder() {
      return new Builder();
    }
    
    public static class Builder {
      private boolean allowUnknownFields = false;
      
      private boolean allowUnknownEnumValues = false;
      
      private boolean allowUnknownExtensions = false;
      
      private SingularOverwritePolicy singularOverwritePolicy = SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
      
      private TextFormatParseInfoTree.Builder parseInfoTreeBuilder = null;
      
      private TypeRegistry typeRegistry = TypeRegistry.getEmptyTypeRegistry();
      
      public Builder setTypeRegistry(TypeRegistry typeRegistry) {
        this.typeRegistry = typeRegistry;
        return this;
      }
      
      public Builder setAllowUnknownFields(boolean allowUnknownFields) {
        this.allowUnknownFields = allowUnknownFields;
        return this;
      }
      
      public Builder setAllowUnknownExtensions(boolean allowUnknownExtensions) {
        this.allowUnknownExtensions = allowUnknownExtensions;
        return this;
      }
      
      public Builder setSingularOverwritePolicy(SingularOverwritePolicy p) {
        this.singularOverwritePolicy = p;
        return this;
      }
      
      public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder parseInfoTreeBuilder) {
        this.parseInfoTreeBuilder = parseInfoTreeBuilder;
        return this;
      }
      
      public Parser build() {
        return new Parser(this.typeRegistry, this.allowUnknownFields, this.allowUnknownEnumValues, this.allowUnknownExtensions, this.singularOverwritePolicy, this.parseInfoTreeBuilder);
      }
    }
    
    public void merge(Readable input, Message.Builder builder) throws IOException {
      merge(input, ExtensionRegistry.getEmptyRegistry(), builder);
    }
    
    public void merge(CharSequence input, Message.Builder builder) throws ParseException {
      merge(input, ExtensionRegistry.getEmptyRegistry(), builder);
    }
    
    public void merge(Readable input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
      merge(toStringBuilder(input), extensionRegistry, builder);
    }
    
    private static StringBuilder toStringBuilder(Readable input) throws IOException {
      StringBuilder text = new StringBuilder();
      CharBuffer buffer = CharBuffer.allocate(4096);
      while (true) {
        int n = input.read(buffer);
        if (n == -1)
          break; 
        buffer.flip();
        text.append(buffer, 0, n);
      } 
      return text;
    }
    
    static final class UnknownField {
      final String message;
      
      final Type type;
      
      enum Type {
        FIELD, EXTENSION;
      }
      
      UnknownField(String message, Type type) {
        this.message = message;
        this.type = type;
      }
    }
    
    enum Type {
      FIELD, EXTENSION;
    }
    
    private void checkUnknownFields(List<UnknownField> unknownFields) throws ParseException {
      if (unknownFields.isEmpty())
        return; 
      StringBuilder msg = new StringBuilder("Input contains unknown fields and/or extensions:");
      for (UnknownField field : unknownFields)
        msg.append('\n').append(field.message); 
      if (this.allowUnknownFields) {
        TextFormat.logger.warning(msg.toString());
        return;
      } 
      int firstErrorIndex = 0;
      if (this.allowUnknownExtensions) {
        boolean allUnknownExtensions = true;
        for (UnknownField field : unknownFields) {
          if (field.type == UnknownField.Type.FIELD) {
            allUnknownExtensions = false;
            break;
          } 
          firstErrorIndex++;
        } 
        if (allUnknownExtensions) {
          TextFormat.logger.warning(msg.toString());
          return;
        } 
      } 
      String[] lineColumn = ((UnknownField)unknownFields.get(firstErrorIndex)).message.split(":");
      throw new ParseException(
          Integer.parseInt(lineColumn[0]), Integer.parseInt(lineColumn[1]), msg.toString());
    }
    
    public void merge(CharSequence input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
      Tokenizer tokenizer = new Tokenizer(input);
      MessageReflection.BuilderAdapter target = new MessageReflection.BuilderAdapter(builder);
      List<UnknownField> unknownFields = new ArrayList<>();
      while (!tokenizer.atEnd())
        mergeField(tokenizer, extensionRegistry, target, unknownFields); 
      checkUnknownFields(unknownFields);
    }
    
    private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, List<UnknownField> unknownFields) throws ParseException {
      mergeField(tokenizer, extensionRegistry, target, this.parseInfoTreeBuilder, unknownFields);
    }
    
    private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, TextFormatParseInfoTree.Builder parseTreeBuilder, List<UnknownField> unknownFields) throws ParseException {
      Descriptors.FieldDescriptor field = null;
      int startLine = tokenizer.getLine();
      int startColumn = tokenizer.getColumn();
      Descriptors.Descriptor type = target.getDescriptorForType();
      ExtensionRegistry.ExtensionInfo extension = null;
      if (tokenizer.tryConsume("[")) {
        StringBuilder name = new StringBuilder(tokenizer.consumeIdentifier());
        while (tokenizer.tryConsume(".")) {
          name.append('.');
          name.append(tokenizer.consumeIdentifier());
        } 
        extension = target.findExtensionByName(extensionRegistry, name.toString());
        if (extension == null) {
          String message = (tokenizer.getPreviousLine() + 1) + ":" + (tokenizer.getPreviousColumn() + 1) + ":\t" + type.getFullName() + ".[" + name + "]";
          unknownFields.add(new UnknownField(message, UnknownField.Type.EXTENSION));
        } else {
          if (extension.descriptor.getContainingType() != type)
            throw tokenizer.parseExceptionPreviousToken("Extension \"" + name + "\" does not extend message type \"" + type
                
                .getFullName() + "\"."); 
          field = extension.descriptor;
        } 
        tokenizer.consume("]");
      } else {
        String name = tokenizer.consumeIdentifier();
        field = type.findFieldByName(name);
        if (field == null) {
          String lowerName = name.toLowerCase(Locale.US);
          field = type.findFieldByName(lowerName);
          if (field != null && field.getType() != Descriptors.FieldDescriptor.Type.GROUP)
            field = null; 
        } 
        if (field != null && field
          .getType() == Descriptors.FieldDescriptor.Type.GROUP && 
          !field.getMessageType().getName().equals(name))
          field = null; 
        if (field == null) {
          String message = (tokenizer.getPreviousLine() + 1) + ":" + (tokenizer.getPreviousColumn() + 1) + ":\t" + type.getFullName() + "." + name;
          unknownFields.add(new UnknownField(message, UnknownField.Type.FIELD));
        } 
      } 
      if (field == null) {
        if (tokenizer.tryConsume(":") && !tokenizer.lookingAt("{") && !tokenizer.lookingAt("<")) {
          skipFieldValue(tokenizer);
        } else {
          skipFieldMessage(tokenizer);
        } 
        return;
      } 
      if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        tokenizer.tryConsume(":");
        if (parseTreeBuilder != null) {
          TextFormatParseInfoTree.Builder childParseTreeBuilder = parseTreeBuilder.getBuilderForSubMessageField(field);
          consumeFieldValues(tokenizer, extensionRegistry, target, field, extension, childParseTreeBuilder, unknownFields);
        } else {
          consumeFieldValues(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields);
        } 
      } else {
        tokenizer.consume(":");
        consumeFieldValues(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields);
      } 
      if (parseTreeBuilder != null)
        parseTreeBuilder.setLocation(field, TextFormatParseLocation.create(startLine, startColumn)); 
      if (!tokenizer.tryConsume(";"))
        tokenizer.tryConsume(","); 
    }
    
    private void consumeFieldValues(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, Descriptors.FieldDescriptor field, ExtensionRegistry.ExtensionInfo extension, TextFormatParseInfoTree.Builder parseTreeBuilder, List<UnknownField> unknownFields) throws ParseException {
      if (field.isRepeated() && tokenizer.tryConsume("[")) {
        if (!tokenizer.tryConsume("]"))
          while (true) {
            consumeFieldValue(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields);
            if (tokenizer.tryConsume("]"))
              break; 
            tokenizer.consume(",");
          }  
      } else {
        consumeFieldValue(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields);
      } 
    }
    
    private void consumeFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, Descriptors.FieldDescriptor field, ExtensionRegistry.ExtensionInfo extension, TextFormatParseInfoTree.Builder parseTreeBuilder, List<UnknownField> unknownFields) throws ParseException {
      if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && 
        !field.isRepeated()) {
        if (target.hasField(field))
          throw tokenizer.parseExceptionPreviousToken("Non-repeated field \"" + field
              .getFullName() + "\" cannot be overwritten."); 
        if (field.getContainingOneof() != null && target
          .hasOneof(field.getContainingOneof())) {
          Descriptors.OneofDescriptor oneof = field.getContainingOneof();
          throw tokenizer.parseExceptionPreviousToken("Field \"" + field
              
              .getFullName() + "\" is specified along with field \"" + target
              
              .getOneofFieldDescriptor(oneof).getFullName() + "\", another member of oneof \"" + oneof
              
              .getName() + "\".");
        } 
      } 
      Object value = null;
      if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        String endToken;
        if (tokenizer.tryConsume("<")) {
          endToken = ">";
        } else {
          tokenizer.consume("{");
          endToken = "}";
        } 
        if (field.getMessageType().getFullName().equals("google.protobuf.Any") && tokenizer
          .tryConsume("[")) {
          value = consumeAnyFieldValue(tokenizer, extensionRegistry, field, parseTreeBuilder, unknownFields);
          tokenizer.consume(endToken);
        } else {
          Message defaultInstance = (extension == null) ? null : extension.defaultInstance;
          MessageReflection.MergeTarget subField = target.newMergeTargetForField(field, defaultInstance);
          while (!tokenizer.tryConsume(endToken)) {
            if (tokenizer.atEnd())
              throw tokenizer.parseException("Expected \"" + endToken + "\"."); 
            mergeField(tokenizer, extensionRegistry, subField, parseTreeBuilder, unknownFields);
          } 
          value = subField.finish();
        } 
      } else {
        Descriptors.EnumDescriptor enumType;
        String id;
        switch (field.getType()) {
          case INT32:
          case SINT32:
          case SFIXED32:
            value = Integer.valueOf(tokenizer.consumeInt32());
            break;
          case INT64:
          case SINT64:
          case SFIXED64:
            value = Long.valueOf(tokenizer.consumeInt64());
            break;
          case UINT32:
          case FIXED32:
            value = Integer.valueOf(tokenizer.consumeUInt32());
            break;
          case UINT64:
          case FIXED64:
            value = Long.valueOf(tokenizer.consumeUInt64());
            break;
          case FLOAT:
            value = Float.valueOf(tokenizer.consumeFloat());
            break;
          case DOUBLE:
            value = Double.valueOf(tokenizer.consumeDouble());
            break;
          case BOOL:
            value = Boolean.valueOf(tokenizer.consumeBoolean());
            break;
          case STRING:
            value = tokenizer.consumeString();
            break;
          case BYTES:
            value = tokenizer.consumeByteString();
            break;
          case ENUM:
            enumType = field.getEnumType();
            if (tokenizer.lookingAtInteger()) {
              int number = tokenizer.consumeInt32();
              value = enumType.findValueByNumber(number);
              if (value == null) {
                String unknownValueMsg = "Enum type \"" + enumType.getFullName() + "\" has no value with number " + number + '.';
                if (this.allowUnknownEnumValues) {
                  TextFormat.logger.warning(unknownValueMsg);
                  return;
                } 
                throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType
                    
                    .getFullName() + "\" has no value with number " + number + '.');
              } 
              break;
            } 
            id = tokenizer.consumeIdentifier();
            value = enumType.findValueByName(id);
            if (value == null) {
              String unknownValueMsg = "Enum type \"" + enumType.getFullName() + "\" has no value named \"" + id + "\".";
              if (this.allowUnknownEnumValues) {
                TextFormat.logger.warning(unknownValueMsg);
                return;
              } 
              throw tokenizer.parseExceptionPreviousToken(unknownValueMsg);
            } 
            break;
          case MESSAGE:
          case GROUP:
            throw new RuntimeException("Can't get here.");
        } 
      } 
      if (field.isRepeated()) {
        target.addRepeatedField(field, value);
      } else {
        target.setField(field, value);
      } 
    }
    
    private Object consumeAnyFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, Descriptors.FieldDescriptor field, TextFormatParseInfoTree.Builder parseTreeBuilder, List<UnknownField> unknownFields) throws ParseException {
      String anyEndToken;
      StringBuilder typeUrlBuilder = new StringBuilder();
      while (true) {
        typeUrlBuilder.append(tokenizer.consumeIdentifier());
        if (tokenizer.tryConsume("]"))
          break; 
        if (tokenizer.tryConsume("/")) {
          typeUrlBuilder.append("/");
          continue;
        } 
        if (tokenizer.tryConsume(".")) {
          typeUrlBuilder.append(".");
          continue;
        } 
        throw tokenizer.parseExceptionPreviousToken("Expected a valid type URL.");
      } 
      tokenizer.tryConsume(":");
      if (tokenizer.tryConsume("<")) {
        anyEndToken = ">";
      } else {
        tokenizer.consume("{");
        anyEndToken = "}";
      } 
      String typeUrl = typeUrlBuilder.toString();
      Descriptors.Descriptor contentType = null;
      try {
        contentType = this.typeRegistry.getDescriptorForTypeUrl(typeUrl);
      } catch (InvalidProtocolBufferException e) {
        throw tokenizer.parseException("Invalid valid type URL. Found: " + typeUrl);
      } 
      if (contentType == null)
        throw tokenizer.parseException("Unable to parse Any of type: " + typeUrl + ". Please make sure that the TypeRegistry contains the descriptors for the given types."); 
      Message.Builder contentBuilder = DynamicMessage.getDefaultInstance(contentType).newBuilderForType();
      MessageReflection.BuilderAdapter contentTarget = new MessageReflection.BuilderAdapter(contentBuilder);
      while (!tokenizer.tryConsume(anyEndToken))
        mergeField(tokenizer, extensionRegistry, contentTarget, parseTreeBuilder, unknownFields); 
      Descriptors.Descriptor anyDescriptor = field.getMessageType();
      Message.Builder anyBuilder = DynamicMessage.getDefaultInstance(anyDescriptor).newBuilderForType();
      anyBuilder.setField(anyDescriptor.findFieldByName("type_url"), typeUrlBuilder.toString());
      anyBuilder.setField(anyDescriptor
          .findFieldByName("value"), contentBuilder.build().toByteString());
      return anyBuilder.build();
    }
    
    private void skipField(Tokenizer tokenizer) throws ParseException {
      // Byte code:
      //   0: aload_1
      //   1: ldc '['
      //   3: invokevirtual tryConsume : (Ljava/lang/String;)Z
      //   6: ifeq -> 32
      //   9: aload_1
      //   10: invokevirtual consumeIdentifier : ()Ljava/lang/String;
      //   13: pop
      //   14: aload_1
      //   15: ldc '.'
      //   17: invokevirtual tryConsume : (Ljava/lang/String;)Z
      //   20: ifne -> 9
      //   23: aload_1
      //   24: ldc ']'
      //   26: invokevirtual consume : (Ljava/lang/String;)V
      //   29: goto -> 37
      //   32: aload_1
      //   33: invokevirtual consumeIdentifier : ()Ljava/lang/String;
      //   36: pop
      //   37: aload_1
      //   38: ldc ':'
      //   40: invokevirtual tryConsume : (Ljava/lang/String;)Z
      //   43: ifeq -> 72
      //   46: aload_1
      //   47: ldc '<'
      //   49: invokevirtual lookingAt : (Ljava/lang/String;)Z
      //   52: ifne -> 72
      //   55: aload_1
      //   56: ldc '{'
      //   58: invokevirtual lookingAt : (Ljava/lang/String;)Z
      //   61: ifne -> 72
      //   64: aload_0
      //   65: aload_1
      //   66: invokespecial skipFieldValue : (Lcom/google/protobuf/TextFormat$Tokenizer;)V
      //   69: goto -> 77
      //   72: aload_0
      //   73: aload_1
      //   74: invokespecial skipFieldMessage : (Lcom/google/protobuf/TextFormat$Tokenizer;)V
      //   77: aload_1
      //   78: ldc ';'
      //   80: invokevirtual tryConsume : (Ljava/lang/String;)Z
      //   83: ifne -> 93
      //   86: aload_1
      //   87: ldc ','
      //   89: invokevirtual tryConsume : (Ljava/lang/String;)Z
      //   92: pop
      //   93: return
      // Line number table:
      //   Java source line number -> byte code offset
      //   #2123	-> 0
      //   #2126	-> 9
      //   #2127	-> 14
      //   #2128	-> 23
      //   #2130	-> 32
      //   #2139	-> 37
      //   #2140	-> 64
      //   #2142	-> 72
      //   #2146	-> 77
      //   #2147	-> 86
      //   #2149	-> 93
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   0	94	0	this	Lcom/google/protobuf/TextFormat$Parser;
      //   0	94	1	tokenizer	Lcom/google/protobuf/TextFormat$Tokenizer;
    }
    
    private void skipFieldMessage(Tokenizer tokenizer) throws ParseException {
      String delimiter;
      if (tokenizer.tryConsume("<")) {
        delimiter = ">";
      } else {
        tokenizer.consume("{");
        delimiter = "}";
      } 
      while (!tokenizer.lookingAt(">") && !tokenizer.lookingAt("}"))
        skipField(tokenizer); 
      tokenizer.consume(delimiter);
    }
    
    private void skipFieldValue(Tokenizer tokenizer) throws ParseException {
      if (tokenizer.tryConsumeString()) {
        while (tokenizer.tryConsumeString());
        return;
      } 
      if (!tokenizer.tryConsumeIdentifier() && 
        !tokenizer.tryConsumeInt64() && 
        !tokenizer.tryConsumeUInt64() && 
        !tokenizer.tryConsumeDouble() && 
        !tokenizer.tryConsumeFloat())
        throw tokenizer.parseException("Invalid field value: " + tokenizer.currentToken); 
    }
  }
  
  public static class Builder {
    private boolean allowUnknownFields;
    
    private boolean allowUnknownEnumValues;
    
    private boolean allowUnknownExtensions;
    
    private Parser.SingularOverwritePolicy singularOverwritePolicy;
    
    private TextFormatParseInfoTree.Builder parseInfoTreeBuilder;
    
    private TypeRegistry typeRegistry;
    
    public Builder() {
      this.allowUnknownFields = false;
      this.allowUnknownEnumValues = false;
      this.allowUnknownExtensions = false;
      this.singularOverwritePolicy = Parser.SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
      this.parseInfoTreeBuilder = null;
      this.typeRegistry = TypeRegistry.getEmptyTypeRegistry();
    }
    
    public Builder setTypeRegistry(TypeRegistry typeRegistry) {
      this.typeRegistry = typeRegistry;
      return this;
    }
    
    public Builder setAllowUnknownFields(boolean allowUnknownFields) {
      this.allowUnknownFields = allowUnknownFields;
      return this;
    }
    
    public Builder setAllowUnknownExtensions(boolean allowUnknownExtensions) {
      this.allowUnknownExtensions = allowUnknownExtensions;
      return this;
    }
    
    public Builder setSingularOverwritePolicy(Parser.SingularOverwritePolicy p) {
      this.singularOverwritePolicy = p;
      return this;
    }
    
    public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder parseInfoTreeBuilder) {
      this.parseInfoTreeBuilder = parseInfoTreeBuilder;
      return this;
    }
    
    public Parser build() {
      return new Parser(this.typeRegistry, this.allowUnknownFields, this.allowUnknownEnumValues, this.allowUnknownExtensions, this.singularOverwritePolicy, this.parseInfoTreeBuilder);
    }
  }
  
  public static String escapeBytes(ByteString input) {
    return TextFormatEscaper.escapeBytes(input);
  }
  
  public static String escapeBytes(byte[] input) {
    return TextFormatEscaper.escapeBytes(input);
  }
  
  public static ByteString unescapeBytes(CharSequence charString) throws InvalidEscapeSequenceException {
    ByteString input = ByteString.copyFromUtf8(charString.toString());
    byte[] result = new byte[input.size()];
    int pos = 0;
    for (int i = 0; i < input.size(); i++) {
      byte c = input.byteAt(i);
      if (c == 92) {
        if (i + 1 < input.size()) {
          i++;
          c = input.byteAt(i);
          if (isOctal(c)) {
            int code = digitValue(c);
            if (i + 1 < input.size() && isOctal(input.byteAt(i + 1))) {
              i++;
              code = code * 8 + digitValue(input.byteAt(i));
            } 
            if (i + 1 < input.size() && isOctal(input.byteAt(i + 1))) {
              i++;
              code = code * 8 + digitValue(input.byteAt(i));
            } 
            result[pos++] = (byte)code;
          } else {
            int code;
            switch (c) {
              case 97:
                result[pos++] = 7;
                break;
              case 98:
                result[pos++] = 8;
                break;
              case 102:
                result[pos++] = 12;
                break;
              case 110:
                result[pos++] = 10;
                break;
              case 114:
                result[pos++] = 13;
                break;
              case 116:
                result[pos++] = 9;
                break;
              case 118:
                result[pos++] = 11;
                break;
              case 92:
                result[pos++] = 92;
                break;
              case 39:
                result[pos++] = 39;
                break;
              case 34:
                result[pos++] = 34;
                break;
              case 120:
                code = 0;
                if (i + 1 < input.size() && isHex(input.byteAt(i + 1))) {
                  i++;
                  code = digitValue(input.byteAt(i));
                } else {
                  throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                } 
                if (i + 1 < input.size() && isHex(input.byteAt(i + 1))) {
                  i++;
                  code = code * 16 + digitValue(input.byteAt(i));
                } 
                result[pos++] = (byte)code;
                break;
              default:
                throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + (char)c + '\'');
            } 
          } 
        } else {
          throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
        } 
      } else {
        result[pos++] = c;
      } 
    } 
    return (result.length == pos) ? 
      ByteString.wrap(result) : 
      ByteString.copyFrom(result, 0, pos);
  }
  
  public static class InvalidEscapeSequenceException extends IOException {
    private static final long serialVersionUID = -8164033650142593304L;
    
    InvalidEscapeSequenceException(String description) {
      super(description);
    }
  }
  
  static String escapeText(String input) {
    return escapeBytes(ByteString.copyFromUtf8(input));
  }
  
  public static String escapeDoubleQuotesAndBackslashes(String input) {
    return TextFormatEscaper.escapeDoubleQuotesAndBackslashes(input);
  }
  
  static String unescapeText(String input) throws InvalidEscapeSequenceException {
    return unescapeBytes(input).toStringUtf8();
  }
  
  private static boolean isOctal(byte c) {
    return (48 <= c && c <= 55);
  }
  
  private static boolean isHex(byte c) {
    return ((48 <= c && c <= 57) || (97 <= c && c <= 102) || (65 <= c && c <= 70));
  }
  
  private static int digitValue(byte c) {
    if (48 <= c && c <= 57)
      return c - 48; 
    if (97 <= c && c <= 122)
      return c - 97 + 10; 
    return c - 65 + 10;
  }
  
  static int parseInt32(String text) throws NumberFormatException {
    return (int)parseInteger(text, true, false);
  }
  
  static int parseUInt32(String text) throws NumberFormatException {
    return (int)parseInteger(text, false, false);
  }
  
  static long parseInt64(String text) throws NumberFormatException {
    return parseInteger(text, true, true);
  }
  
  static long parseUInt64(String text) throws NumberFormatException {
    return parseInteger(text, false, true);
  }
  
  private static long parseInteger(String text, boolean isSigned, boolean isLong) throws NumberFormatException {
    int pos = 0;
    boolean negative = false;
    if (text.startsWith("-", pos)) {
      if (!isSigned)
        throw new NumberFormatException("Number must be positive: " + text); 
      pos++;
      negative = true;
    } 
    int radix = 10;
    if (text.startsWith("0x", pos)) {
      pos += 2;
      radix = 16;
    } else if (text.startsWith("0", pos)) {
      radix = 8;
    } 
    String numberText = text.substring(pos);
    long result = 0L;
    if (numberText.length() < 16) {
      result = Long.parseLong(numberText, radix);
      if (negative)
        result = -result; 
      if (!isLong)
        if (isSigned) {
          if (result > 2147483647L || result < -2147483648L)
            throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text); 
        } else if (result >= 4294967296L || result < 0L) {
          throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
        }  
    } else {
      BigInteger bigValue = new BigInteger(numberText, radix);
      if (negative)
        bigValue = bigValue.negate(); 
      if (!isLong) {
        if (isSigned) {
          if (bigValue.bitLength() > 31)
            throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text); 
        } else if (bigValue.bitLength() > 32) {
          throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
        } 
      } else if (isSigned) {
        if (bigValue.bitLength() > 63)
          throw new NumberFormatException("Number out of range for 64-bit signed integer: " + text); 
      } else if (bigValue.bitLength() > 64) {
        throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + text);
      } 
      result = bigValue.longValue();
    } 
    return result;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\TextFormat.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */