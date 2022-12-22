package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.util.Collections;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class Absent<T> extends Optional<T> {
  static final Absent<Object> INSTANCE = new Absent();
  
  private static final long serialVersionUID = 0L;
  
  static <T> Optional<T> withType() {
    return INSTANCE;
  }
  
  public boolean isPresent() {
    return false;
  }
  
  public T get() {
    throw new IllegalStateException("Optional.get() cannot be called on an absent value");
  }
  
  public T or(T defaultValue) {
    return Preconditions.checkNotNull(defaultValue, "use Optional.orNull() instead of Optional.or(null)");
  }
  
  public Optional<T> or(Optional<? extends T> secondChoice) {
    return (Optional<T>)Preconditions.<Optional<? extends T>>checkNotNull(secondChoice);
  }
  
  public T or(Supplier<? extends T> supplier) {
    return Preconditions.checkNotNull(supplier
        .get(), "use Optional.orNull() instead of a Supplier that returns null");
  }
  
  @CheckForNull
  public T orNull() {
    return null;
  }
  
  public Set<T> asSet() {
    return Collections.emptySet();
  }
  
  public <V> Optional<V> transform(Function<? super T, V> function) {
    Preconditions.checkNotNull(function);
    return Optional.absent();
  }
  
  public boolean equals(@CheckForNull Object object) {
    return (object == this);
  }
  
  public int hashCode() {
    return 2040732332;
  }
  
  public String toString() {
    return "Optional.absent()";
  }
  
  private Object readResolve() {
    return INSTANCE;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\base\Absent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */