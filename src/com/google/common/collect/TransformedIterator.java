package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Iterator;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class TransformedIterator<F, T> implements Iterator<T> {
  final Iterator<? extends F> backingIterator;
  
  TransformedIterator(Iterator<? extends F> backingIterator) {
    this.backingIterator = (Iterator<? extends F>)Preconditions.checkNotNull(backingIterator);
  }
  
  @ParametricNullness
  abstract T transform(@ParametricNullness F paramF);
  
  public final boolean hasNext() {
    return this.backingIterator.hasNext();
  }
  
  @ParametricNullness
  public final T next() {
    return transform(this.backingIterator.next());
  }
  
  public final void remove() {
    this.backingIterator.remove();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\TransformedIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */