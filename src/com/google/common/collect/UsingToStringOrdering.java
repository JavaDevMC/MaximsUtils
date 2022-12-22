package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(serializable = true)
final class UsingToStringOrdering extends Ordering<Object> implements Serializable {
  static final UsingToStringOrdering INSTANCE = new UsingToStringOrdering();
  
  private static final long serialVersionUID = 0L;
  
  public int compare(Object left, Object right) {
    return left.toString().compareTo(right.toString());
  }
  
  private Object readResolve() {
    return INSTANCE;
  }
  
  public String toString() {
    return "Ordering.usingToString()";
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\UsingToStringOrdering.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */