package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.ListIterator;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(emulated = true)
class RegularImmutableAsList<E> extends ImmutableAsList<E> {
  private final ImmutableCollection<E> delegate;
  
  private final ImmutableList<? extends E> delegateList;
  
  RegularImmutableAsList(ImmutableCollection<E> delegate, ImmutableList<? extends E> delegateList) {
    this.delegate = delegate;
    this.delegateList = delegateList;
  }
  
  RegularImmutableAsList(ImmutableCollection<E> delegate, Object[] array) {
    this(delegate, ImmutableList.asImmutableList(array));
  }
  
  ImmutableCollection<E> delegateCollection() {
    return this.delegate;
  }
  
  ImmutableList<? extends E> delegateList() {
    return this.delegateList;
  }
  
  public UnmodifiableListIterator<E> listIterator(int index) {
    return (UnmodifiableListIterator)this.delegateList.listIterator(index);
  }
  
  @GwtIncompatible
  public void forEach(Consumer<? super E> action) {
    this.delegateList.forEach(action);
  }
  
  @GwtIncompatible
  int copyIntoArray(Object[] dst, int offset) {
    return this.delegateList.copyIntoArray(dst, offset);
  }
  
  @CheckForNull
  Object[] internalArray() {
    return this.delegateList.internalArray();
  }
  
  int internalArrayStart() {
    return this.delegateList.internalArrayStart();
  }
  
  int internalArrayEnd() {
    return this.delegateList.internalArrayEnd();
  }
  
  public E get(int index) {
    return this.delegateList.get(index);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\RegularImmutableAsList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */