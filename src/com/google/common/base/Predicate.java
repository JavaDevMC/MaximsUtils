package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.function.Predicate;
import javax.annotation.CheckForNull;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Predicate<T> extends Predicate<T> {
  @CanIgnoreReturnValue
  boolean apply(@ParametricNullness T paramT);
  
  boolean equals(@CheckForNull Object paramObject);
  
  default boolean test(@ParametricNullness T input) {
    return apply(input);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\base\Predicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */