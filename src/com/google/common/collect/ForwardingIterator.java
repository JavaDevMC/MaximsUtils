package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingIterator<T> extends ForwardingObject implements Iterator<T> {
  public boolean hasNext() {
    return delegate().hasNext();
  }
  
  @ParametricNullness
  @CanIgnoreReturnValue
  public T next() {
    return delegate().next();
  }
  
  public void remove() {
    delegate().remove();
  }
  
  protected abstract Iterator<T> delegate();
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ForwardingIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */