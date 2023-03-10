package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(serializable = true)
final class NaturalOrdering extends Ordering<Comparable<?>> implements Serializable {
  static final NaturalOrdering INSTANCE = new NaturalOrdering();
  
  @CheckForNull
  private transient Ordering<Comparable<?>> nullsFirst;
  
  @CheckForNull
  private transient Ordering<Comparable<?>> nullsLast;
  
  private static final long serialVersionUID = 0L;
  
  public int compare(Comparable<?> left, Comparable<?> right) {
    Preconditions.checkNotNull(left);
    Preconditions.checkNotNull(right);
    return left.compareTo(right);
  }
  
  public <S extends Comparable<?>> Ordering<S> nullsFirst() {
    Ordering<Comparable<?>> result = this.nullsFirst;
    if (result == null)
      result = this.nullsFirst = super.<Comparable<?>>nullsFirst(); 
    return (Ordering)result;
  }
  
  public <S extends Comparable<?>> Ordering<S> nullsLast() {
    Ordering<Comparable<?>> result = this.nullsLast;
    if (result == null)
      result = this.nullsLast = super.<Comparable<?>>nullsLast(); 
    return (Ordering)result;
  }
  
  public <S extends Comparable<?>> Ordering<S> reverse() {
    return ReverseNaturalOrdering.INSTANCE;
  }
  
  private Object readResolve() {
    return INSTANCE;
  }
  
  public String toString() {
    return "Ordering.natural()";
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\NaturalOrdering.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */