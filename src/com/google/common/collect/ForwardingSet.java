package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingSet<E> extends ForwardingCollection<E> implements Set<E> {
  public boolean equals(@CheckForNull Object object) {
    return (object == this || delegate().equals(object));
  }
  
  public int hashCode() {
    return delegate().hashCode();
  }
  
  protected boolean standardRemoveAll(Collection<?> collection) {
    return Sets.removeAllImpl(this, (Collection)Preconditions.checkNotNull(collection));
  }
  
  protected boolean standardEquals(@CheckForNull Object object) {
    return Sets.equalsImpl(this, object);
  }
  
  protected int standardHashCode() {
    return Sets.hashCodeImpl(this);
  }
  
  protected abstract Set<E> delegate();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ForwardingSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */