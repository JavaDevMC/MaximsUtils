package com.google.gson.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PreJava9DateFormatProvider {
  public static DateFormat getUSDateFormat(int style) {
    return new SimpleDateFormat(getDateFormatPattern(style), Locale.US);
  }
  
  public static DateFormat getUSDateTimeFormat(int dateStyle, int timeStyle) {
    String pattern = getDatePartOfDateTimePattern(dateStyle) + " " + getTimePartOfDateTimePattern(timeStyle);
    return new SimpleDateFormat(pattern, Locale.US);
  }
  
  private static String getDateFormatPattern(int style) {
    switch (style) {
      case 3:
        return "M/d/yy";
      case 2:
        return "MMM d, y";
      case 1:
        return "MMMM d, y";
      case 0:
        return "EEEE, MMMM d, y";
    } 
    throw new IllegalArgumentException("Unknown DateFormat style: " + style);
  }
  
  private static String getDatePartOfDateTimePattern(int dateStyle) {
    switch (dateStyle) {
      case 3:
        return "M/d/yy";
      case 2:
        return "MMM d, yyyy";
      case 1:
        return "MMMM d, yyyy";
      case 0:
        return "EEEE, MMMM d, yyyy";
    } 
    throw new IllegalArgumentException("Unknown DateFormat style: " + dateStyle);
  }
  
  private static String getTimePartOfDateTimePattern(int timeStyle) {
    switch (timeStyle) {
      case 3:
        return "h:mm a";
      case 2:
        return "h:mm:ss a";
      case 0:
      case 1:
        return "h:mm:ss a z";
    } 
    throw new IllegalArgumentException("Unknown DateFormat style: " + timeStyle);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\gson\internal\PreJava9DateFormatProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */