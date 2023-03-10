package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class AbstractIterator<T> extends UnmodifiableIterator<T> {
  private State state = State.NOT_READY;
  
  @CheckForNull
  private T next;
  
  @CheckForNull
  protected abstract T computeNext();
  
  private enum State {
    READY, NOT_READY, DONE, FAILED;
  }
  
  @CheckForNull
  @CanIgnoreReturnValue
  protected final T endOfData() {
    this.state = State.DONE;
    return null;
  }
  
  @CanIgnoreReturnValue
  public final boolean hasNext() {
    Preconditions.checkState((this.state != State.FAILED));
    switch (this.state) {
      case DONE:
        return false;
      case READY:
        return true;
    } 
    return tryToComputeNext();
  }
  
  private boolean tryToComputeNext() {
    this.state = State.FAILED;
    this.next = computeNext();
    if (this.state != State.DONE) {
      this.state = State.READY;
      return true;
    } 
    return false;
  }
  
  @ParametricNullness
  @CanIgnoreReturnValue
  public final T next() {
    if (!hasNext())
      throw new NoSuchElementException(); 
    this.state = State.NOT_READY;
    T result = NullnessCasts.uncheckedCastNullableTToT(this.next);
    this.next = null;
    return result;
  }
  
  @ParametricNullness
  public final T peek() {
    if (!hasNext())
      throw new NoSuchElementException(); 
    return NullnessCasts.uncheckedCastNullableTToT(this.next);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\AbstractIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */