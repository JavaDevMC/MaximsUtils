package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Comparators {
  @Beta
  public static <T, S extends T> Comparator<Iterable<S>> lexicographical(Comparator<T> comparator) {
    return new LexicographicalOrdering<>((Comparator<? super S>)Preconditions.checkNotNull(comparator));
  }
  
  @Beta
  public static <T> boolean isInOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
    Preconditions.checkNotNull(comparator);
    Iterator<? extends T> it = iterable.iterator();
    if (it.hasNext()) {
      T prev = it.next();
      while (it.hasNext()) {
        T next = it.next();
        if (comparator.compare(prev, next) > 0)
          return false; 
        prev = next;
      } 
    } 
    return true;
  }
  
  @Beta
  public static <T> boolean isInStrictOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
    Preconditions.checkNotNull(comparator);
    Iterator<? extends T> it = iterable.iterator();
    if (it.hasNext()) {
      T prev = it.next();
      while (it.hasNext()) {
        T next = it.next();
        if (comparator.compare(prev, next) >= 0)
          return false; 
        prev = next;
      } 
    } 
    return true;
  }
  
  public static <T> Collector<T, ?, List<T>> least(int k, Comparator<? super T> comparator) {
    CollectPreconditions.checkNonnegative(k, "k");
    Preconditions.checkNotNull(comparator);
    return Collector.of(() -> TopKSelector.least(k, comparator), TopKSelector::offer, TopKSelector::combine, TopKSelector::topK, new Collector.Characteristics[] { Collector.Characteristics.UNORDERED });
  }
  
  public static <T> Collector<T, ?, List<T>> greatest(int k, Comparator<? super T> comparator) {
    return least(k, comparator.reversed());
  }
  
  @Beta
  public static <T> Comparator<Optional<T>> emptiesFirst(Comparator<? super T> valueComparator) {
    Preconditions.checkNotNull(valueComparator);
    return Comparator.comparing(o -> o.orElse(null), 
        Comparator.nullsFirst(valueComparator));
  }
  
  @Beta
  public static <T> Comparator<Optional<T>> emptiesLast(Comparator<? super T> valueComparator) {
    Preconditions.checkNotNull(valueComparator);
    return Comparator.comparing(o -> o.orElse(null), 
        Comparator.nullsLast(valueComparator));
  }
  
  @Beta
  public static <T extends Comparable<? super T>> T min(T a, T b) {
    return (a.compareTo(b) <= 0) ? a : b;
  }
  
  @ParametricNullness
  @Beta
  public static <T> T min(@ParametricNullness T a, @ParametricNullness T b, Comparator<T> comparator) {
    return (comparator.compare(a, b) <= 0) ? a : b;
  }
  
  @Beta
  public static <T extends Comparable<? super T>> T max(T a, T b) {
    return (a.compareTo(b) >= 0) ? a : b;
  }
  
  @ParametricNullness
  @Beta
  public static <T> T max(@ParametricNullness T a, @ParametricNullness T b, Comparator<T> comparator) {
    return (comparator.compare(a, b) >= 0) ? a : b;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\Comparators.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */