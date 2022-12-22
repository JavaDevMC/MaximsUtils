package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class CommonPattern {
  public abstract CommonMatcher matcher(CharSequence paramCharSequence);
  
  public abstract String pattern();
  
  public abstract int flags();
  
  public abstract String toString();
  
  public static CommonPattern compile(String pattern) {
    return Platform.compilePattern(pattern);
  }
  
  public static boolean isPcreLike() {
    return Platform.patternCompilerIsPcreLike();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\base\CommonPattern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */