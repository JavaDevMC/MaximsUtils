package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class CommonMatcher {
  public abstract boolean matches();
  
  public abstract boolean find();
  
  public abstract boolean find(int paramInt);
  
  public abstract String replaceAll(String paramString);
  
  public abstract int end();
  
  public abstract int start();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\base\CommonMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */