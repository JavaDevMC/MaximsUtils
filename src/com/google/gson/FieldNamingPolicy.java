package com.google.gson;

import java.lang.reflect.Field;
import java.util.Locale;

public enum FieldNamingPolicy implements FieldNamingStrategy {
  IDENTITY {
    public String translateName(Field f) {
      return f.getName();
    }
  },
  UPPER_CAMEL_CASE {
    public String translateName(Field f) {
      return null.upperCaseFirstLetter(f.getName());
    }
  },
  UPPER_CAMEL_CASE_WITH_SPACES {
    public String translateName(Field f) {
      return null.upperCaseFirstLetter(null.separateCamelCase(f.getName(), " "));
    }
  },
  LOWER_CASE_WITH_UNDERSCORES {
    public String translateName(Field f) {
      return null.separateCamelCase(f.getName(), "_").toLowerCase(Locale.ENGLISH);
    }
  },
  LOWER_CASE_WITH_DASHES {
    public String translateName(Field f) {
      return null.separateCamelCase(f.getName(), "-").toLowerCase(Locale.ENGLISH);
    }
  },
  LOWER_CASE_WITH_DOTS {
    public String translateName(Field f) {
      return null.separateCamelCase(f.getName(), ".").toLowerCase(Locale.ENGLISH);
    }
  };
  
  static String separateCamelCase(String name, String separator) {
    StringBuilder translation = new StringBuilder();
    for (int i = 0, length = name.length(); i < length; i++) {
      char character = name.charAt(i);
      if (Character.isUpperCase(character) && translation.length() != 0)
        translation.append(separator); 
      translation.append(character);
    } 
    return translation.toString();
  }
  
  static String upperCaseFirstLetter(String name) {
    int firstLetterIndex = 0;
    int limit = name.length() - 1;
    for (; !Character.isLetter(name.charAt(firstLetterIndex)) && firstLetterIndex < limit; firstLetterIndex++);
    char firstLetter = name.charAt(firstLetterIndex);
    if (Character.isUpperCase(firstLetter))
      return name; 
    char uppercased = Character.toUpperCase(firstLetter);
    if (firstLetterIndex == 0)
      return uppercased + name.substring(1); 
    return name.substring(0, firstLetterIndex) + uppercased + name.substring(firstLetterIndex + 1);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\gson\FieldNamingPolicy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */