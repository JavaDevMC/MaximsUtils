package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class Interners {
  public static class InternerBuilder {
    private final MapMaker mapMaker = new MapMaker();
    
    private boolean strong = true;
    
    public InternerBuilder strong() {
      this.strong = true;
      return this;
    }
    
    @GwtIncompatible("java.lang.ref.WeakReference")
    public InternerBuilder weak() {
      this.strong = false;
      return this;
    }
    
    public InternerBuilder concurrencyLevel(int concurrencyLevel) {
      this.mapMaker.concurrencyLevel(concurrencyLevel);
      return this;
    }
    
    public <E> Interner<E> build() {
      if (!this.strong)
        this.mapMaker.weakKeys(); 
      return new InternerImpl<>(this.mapMaker);
    }
    
    private InternerBuilder() {}
  }
  
  public static InternerBuilder newBuilder() {
    return new InternerBuilder();
  }
  
  public static <E> Interner<E> newStrongInterner() {
    return newBuilder().strong().build();
  }
  
  @GwtIncompatible("java.lang.ref.WeakReference")
  public static <E> Interner<E> newWeakInterner() {
    return newBuilder().weak().build();
  }
  
  @VisibleForTesting
  static final class InternerImpl<E> implements Interner<E> {
    @VisibleForTesting
    final MapMakerInternalMap<E, MapMaker.Dummy, ?, ?> map;
    
    private InternerImpl(MapMaker mapMaker) {
      this
        .map = MapMakerInternalMap.createWithDummyValues(mapMaker.keyEquivalence(Equivalence.equals()));
    }
    
    public E intern(E sample) {
      while (true) {
        MapMakerInternalMap.InternalEntry entry = (MapMakerInternalMap.InternalEntry)this.map.getEntry(sample);
        if (entry != null) {
          Object canonical = entry.getKey();
          if (canonical != null) {
            E result = (E)canonical;
            return result;
          } 
        } 
        MapMaker.Dummy sneaky = this.map.putIfAbsent(sample, MapMaker.Dummy.VALUE);
        if (sneaky == null)
          return sample; 
      } 
    }
  }
  
  public static <E> Function<E, E> asFunction(Interner<E> interner) {
    return new InternerFunction<>((Interner<E>)Preconditions.checkNotNull(interner));
  }
  
  private static class InternerFunction<E> implements Function<E, E> {
    private final Interner<E> interner;
    
    public InternerFunction(Interner<E> interner) {
      this.interner = interner;
    }
    
    public E apply(E input) {
      return this.interner.intern(input);
    }
    
    public int hashCode() {
      return this.interner.hashCode();
    }
    
    public boolean equals(@CheckForNull Object other) {
      if (other instanceof InternerFunction) {
        InternerFunction<?> that = (InternerFunction)other;
        return this.interner.equals(that.interner);
      } 
      return false;
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\Interners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */