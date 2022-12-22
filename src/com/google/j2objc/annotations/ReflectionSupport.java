package com.google.j2objc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.PACKAGE})
@Retention(RetentionPolicy.CLASS)
public @interface ReflectionSupport {
  Level value();
  
  public enum Level {
    NATIVE_ONLY, FULL;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\j2objc\annotations\ReflectionSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */