package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class RemovalListeners {
  public static <K, V> RemovalListener<K, V> asynchronous(final RemovalListener<K, V> listener, final Executor executor) {
    Preconditions.checkNotNull(listener);
    Preconditions.checkNotNull(executor);
    return new RemovalListener<K, V>() {
        public void onRemoval(final RemovalNotification<K, V> notification) {
          executor.execute(new Runnable() {
                public void run() {
                  listener.onRemoval(notification);
                }
              });
        }
      };
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\cache\RemovalListeners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */