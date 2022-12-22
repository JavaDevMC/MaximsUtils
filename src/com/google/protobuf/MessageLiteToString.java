package com.google.protobuf;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

final class MessageLiteToString {
  private static final String LIST_SUFFIX = "List";
  
  private static final String BUILDER_LIST_SUFFIX = "OrBuilderList";
  
  private static final String MAP_SUFFIX = "Map";
  
  private static final String BYTES_SUFFIX = "Bytes";
  
  static String toString(MessageLite messageLite, String commentString) {
    StringBuilder buffer = new StringBuilder();
    buffer.append("# ").append(commentString);
    reflectivePrintWithIndent(messageLite, buffer, 0);
    return buffer.toString();
  }
  
  private static void reflectivePrintWithIndent(MessageLite messageLite, StringBuilder buffer, int indent) {
    Map<String, Method> nameToNoArgMethod = new HashMap<>();
    Map<String, Method> nameToMethod = new HashMap<>();
    Set<String> getters = new TreeSet<>();
    for (Method method : messageLite.getClass().getDeclaredMethods()) {
      nameToMethod.put(method.getName(), method);
      if ((method.getParameterTypes()).length == 0) {
        nameToNoArgMethod.put(method.getName(), method);
        if (method.getName().startsWith("get"))
          getters.add(method.getName()); 
      } 
    } 
    for (String getter : getters) {
      String suffix = getter.startsWith("get") ? getter.substring(3) : getter;
      if (suffix.endsWith("List") && 
        !suffix.endsWith("OrBuilderList") && 
        
        !suffix.equals("List")) {
        String str = suffix.substring(0, 1).toLowerCase() + suffix.substring(1, suffix.length() - "List".length());
        Method listMethod = nameToNoArgMethod.get(getter);
        if (listMethod != null && listMethod.getReturnType().equals(List.class)) {
          printField(buffer, indent, 
              
              camelCaseToSnakeCase(str), 
              GeneratedMessageLite.invokeOrDie(listMethod, messageLite, new Object[0]));
          continue;
        } 
      } 
      if (suffix.endsWith("Map") && 
        
        !suffix.equals("Map")) {
        String str = suffix.substring(0, 1).toLowerCase() + suffix.substring(1, suffix.length() - "Map".length());
        Method mapMethod = nameToNoArgMethod.get(getter);
        if (mapMethod != null && mapMethod
          .getReturnType().equals(Map.class) && 
          
          !mapMethod.isAnnotationPresent((Class)Deprecated.class) && 
          
          Modifier.isPublic(mapMethod.getModifiers())) {
          printField(buffer, indent, 
              
              camelCaseToSnakeCase(str), 
              GeneratedMessageLite.invokeOrDie(mapMethod, messageLite, new Object[0]));
          continue;
        } 
      } 
      Method setter = nameToMethod.get("set" + suffix);
      if (setter == null)
        continue; 
      if (suffix.endsWith("Bytes"))
        if (nameToNoArgMethod
          .containsKey("get" + suffix
            .substring(0, suffix.length() - "Bytes".length())))
          continue;  
      String camelCase = suffix.substring(0, 1).toLowerCase() + suffix.substring(1);
      Method getMethod = nameToNoArgMethod.get("get" + suffix);
      Method hasMethod = nameToNoArgMethod.get("has" + suffix);
      if (getMethod != null) {
        Object value = GeneratedMessageLite.invokeOrDie(getMethod, messageLite, new Object[0]);
        boolean hasValue = (hasMethod == null) ? (!isDefaultValue(value)) : ((Boolean)GeneratedMessageLite.invokeOrDie(hasMethod, messageLite, new Object[0])).booleanValue();
        if (hasValue)
          printField(buffer, indent, camelCaseToSnakeCase(camelCase), value); 
      } 
    } 
    if (messageLite instanceof GeneratedMessageLite.ExtendableMessage) {
      Iterator<Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object>> iter = ((GeneratedMessageLite.ExtendableMessage)messageLite).extensions.iterator();
      while (iter.hasNext()) {
        Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object> entry = iter.next();
        printField(buffer, indent, "[" + ((GeneratedMessageLite.ExtensionDescriptor)entry.getKey()).getNumber() + "]", entry.getValue());
      } 
    } 
    if (((GeneratedMessageLite)messageLite).unknownFields != null)
      ((GeneratedMessageLite)messageLite).unknownFields.printWithIndent(buffer, indent); 
  }
  
  private static boolean isDefaultValue(Object o) {
    if (o instanceof Boolean)
      return !((Boolean)o).booleanValue(); 
    if (o instanceof Integer)
      return (((Integer)o).intValue() == 0); 
    if (o instanceof Float)
      return (((Float)o).floatValue() == 0.0F); 
    if (o instanceof Double)
      return (((Double)o).doubleValue() == 0.0D); 
    if (o instanceof String)
      return o.equals(""); 
    if (o instanceof ByteString)
      return o.equals(ByteString.EMPTY); 
    if (o instanceof MessageLite)
      return (o == ((MessageLite)o).getDefaultInstanceForType()); 
    if (o instanceof java.lang.Enum)
      return (((java.lang.Enum)o).ordinal() == 0); 
    return false;
  }
  
  static final void printField(StringBuilder buffer, int indent, String name, Object object) {
    if (object instanceof List) {
      List<?> list = (List)object;
      for (Object entry : list)
        printField(buffer, indent, name, entry); 
      return;
    } 
    if (object instanceof Map) {
      Map<?, ?> map = (Map<?, ?>)object;
      for (Map.Entry<?, ?> entry : map.entrySet())
        printField(buffer, indent, name, entry); 
      return;
    } 
    buffer.append('\n');
    int i;
    for (i = 0; i < indent; i++)
      buffer.append(' '); 
    buffer.append(name);
    if (object instanceof String) {
      buffer.append(": \"").append(TextFormatEscaper.escapeText((String)object)).append('"');
    } else if (object instanceof ByteString) {
      buffer.append(": \"").append(TextFormatEscaper.escapeBytes((ByteString)object)).append('"');
    } else if (object instanceof GeneratedMessageLite) {
      buffer.append(" {");
      reflectivePrintWithIndent((GeneratedMessageLite)object, buffer, indent + 2);
      buffer.append("\n");
      for (i = 0; i < indent; i++)
        buffer.append(' '); 
      buffer.append("}");
    } else if (object instanceof Map.Entry) {
      buffer.append(" {");
      Map.Entry<?, ?> entry = (Map.Entry<?, ?>)object;
      printField(buffer, indent + 2, "key", entry.getKey());
      printField(buffer, indent + 2, "value", entry.getValue());
      buffer.append("\n");
      for (int j = 0; j < indent; j++)
        buffer.append(' '); 
      buffer.append("}");
    } else {
      buffer.append(": ").append(object.toString());
    } 
  }
  
  private static final String camelCaseToSnakeCase(String camelCase) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < camelCase.length(); i++) {
      char ch = camelCase.charAt(i);
      if (Character.isUpperCase(ch))
        builder.append("_"); 
      builder.append(Character.toLowerCase(ch));
    } 
    return builder.toString();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\MessageLiteToString.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */