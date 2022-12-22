package com.google.common.reflect;

import com.google.common.base.Preconditions;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@ElementTypesAreNonnullByDefault
abstract class TypeCapture<T> {
  final Type capture() {
    Type superclass = getClass().getGenericSuperclass();
    Preconditions.checkArgument(superclass instanceof ParameterizedType, "%s isn't parameterized", superclass);
    return ((ParameterizedType)superclass).getActualTypeArguments()[0];
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\reflect\TypeCapture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */