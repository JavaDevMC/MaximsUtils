package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface AsyncFunction<I, O> {
  ListenableFuture<O> apply(@ParametricNullness I paramI) throws Exception;
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\AsyncFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */