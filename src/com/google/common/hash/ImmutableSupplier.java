package com.google.common.hash;

import com.google.common.base.Supplier;
import com.google.errorprone.annotations.Immutable;

@Immutable
@ElementTypesAreNonnullByDefault
interface ImmutableSupplier<T> extends Supplier<T> {}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\hash\ImmutableSupplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */