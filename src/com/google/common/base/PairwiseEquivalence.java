package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(serializable = true)
final class PairwiseEquivalence<E, T extends E> extends Equivalence<Iterable<T>> implements Serializable {
  final Equivalence<E> elementEquivalence;
  
  private static final long serialVersionUID = 1L;
  
  PairwiseEquivalence(Equivalence<E> elementEquivalence) {
    this.elementEquivalence = Preconditions.<Equivalence<E>>checkNotNull(elementEquivalence);
  }
  
  protected boolean doEquivalent(Iterable<T> iterableA, Iterable<T> iterableB) {
    Iterator<T> iteratorA = iterableA.iterator();
    Iterator<T> iteratorB = iterableB.iterator();
    while (iteratorA.hasNext() && iteratorB.hasNext()) {
      if (!this.elementEquivalence.equivalent((E)iteratorA.next(), (E)iteratorB.next()))
        return false; 
    } 
    return (!iteratorA.hasNext() && !iteratorB.hasNext());
  }
  
  protected int doHash(Iterable<T> iterable) {
    int hash = 78721;
    for (T element : iterable)
      hash = hash * 24943 + this.elementEquivalence.hash((E)element); 
    return hash;
  }
  
  public boolean equals(@CheckForNull Object object) {
    if (object instanceof PairwiseEquivalence) {
      PairwiseEquivalence<?, ?> that = (PairwiseEquivalence<?, ?>)object;
      return this.elementEquivalence.equals(that.elementEquivalence);
    } 
    return false;
  }
  
  public int hashCode() {
    return this.elementEquivalence.hashCode() ^ 0x46A3EB07;
  }
  
  public String toString() {
    String str = String.valueOf(this.elementEquivalence);
    return (new StringBuilder(11 + String.valueOf(str).length())).append(str).append(".pairwise()").toString();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\base\PairwiseEquivalence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */