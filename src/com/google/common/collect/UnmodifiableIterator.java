package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.DoNotCall;
import java.util.Iterator;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class UnmodifiableIterator<E> implements Iterator<E> {
  @Deprecated
  @DoNotCall("Always throws UnsupportedOperationException")
  public final void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\UnmodifiableIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */