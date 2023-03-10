package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;
import javax.annotation.CheckForNull;

@DoNotMock("Use Maps.difference")
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface MapDifference<K, V> {
  boolean areEqual();
  
  Map<K, V> entriesOnlyOnLeft();
  
  Map<K, V> entriesOnlyOnRight();
  
  Map<K, V> entriesInCommon();
  
  Map<K, ValueDifference<V>> entriesDiffering();
  
  boolean equals(@CheckForNull Object paramObject);
  
  int hashCode();
  
  @DoNotMock("Use Maps.difference")
  public static interface ValueDifference<V> {
    @ParametricNullness
    V leftValue();
    
    @ParametricNullness
    V rightValue();
    
    boolean equals(@CheckForNull Object param1Object);
    
    int hashCode();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\MapDifference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */