package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.AbstractMap;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class RemovalNotification<K, V> extends AbstractMap.SimpleImmutableEntry<K, V> {
  private final RemovalCause cause;
  
  private static final long serialVersionUID = 0L;
  
  public static <K, V> RemovalNotification<K, V> create(@CheckForNull K key, @CheckForNull V value, RemovalCause cause) {
    return new RemovalNotification<>(key, value, cause);
  }
  
  private RemovalNotification(@CheckForNull K key, @CheckForNull V value, RemovalCause cause) {
    super(key, value);
    this.cause = (RemovalCause)Preconditions.checkNotNull(cause);
  }
  
  public RemovalCause getCause() {
    return this.cause;
  }
  
  public boolean wasEvicted() {
    return this.cause.wasEvicted();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\cache\RemovalNotification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */