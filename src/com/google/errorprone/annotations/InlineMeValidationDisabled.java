package com.google.errorprone.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface InlineMeValidationDisabled {
  String value();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\errorprone\annotations\InlineMeValidationDisabled.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */