package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingObject {
  protected abstract Object delegate();
  
  public String toString() {
    return delegate().toString();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ForwardingObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */