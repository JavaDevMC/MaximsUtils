package com.google.protobuf;

import java.util.List;

public interface TypeOrBuilder extends MessageOrBuilder {
  String getName();
  
  ByteString getNameBytes();
  
  List<Field> getFieldsList();
  
  Field getFields(int paramInt);
  
  int getFieldsCount();
  
  List<? extends FieldOrBuilder> getFieldsOrBuilderList();
  
  FieldOrBuilder getFieldsOrBuilder(int paramInt);
  
  List<String> getOneofsList();
  
  int getOneofsCount();
  
  String getOneofs(int paramInt);
  
  ByteString getOneofsBytes(int paramInt);
  
  List<Option> getOptionsList();
  
  Option getOptions(int paramInt);
  
  int getOptionsCount();
  
  List<? extends OptionOrBuilder> getOptionsOrBuilderList();
  
  OptionOrBuilder getOptionsOrBuilder(int paramInt);
  
  boolean hasSourceContext();
  
  SourceContext getSourceContext();
  
  SourceContextOrBuilder getSourceContextOrBuilder();
  
  int getSyntaxValue();
  
  Syntax getSyntax();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\TypeOrBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */