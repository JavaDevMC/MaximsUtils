package com.google.j2objc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface LoopTranslation {
  LoopStyle value();
  
  public enum LoopStyle {
    JAVA_ITERATOR, FAST_ENUMERATION;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\j2objc\annotations\LoopTranslation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */