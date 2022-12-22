package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Use Interners.new*Interner")
@ElementTypesAreNonnullByDefault
@GwtIncompatible
public interface Interner<E> {
  @CanIgnoreReturnValue
  E intern(E paramE);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\Interner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */