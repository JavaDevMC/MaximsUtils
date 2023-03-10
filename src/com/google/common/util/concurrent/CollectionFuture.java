package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(emulated = true)
abstract class CollectionFuture<V, C> extends AggregateFuture<V, C> {
  @CheckForNull
  private List<Present<V>> values;
  
  CollectionFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
    super(futures, allMustSucceed, true);
    List<Present<V>> values = futures.isEmpty() ? Collections.<Present<V>>emptyList() : Lists.newArrayListWithCapacity(futures.size());
    for (int i = 0; i < futures.size(); i++)
      values.add(null); 
    this.values = values;
  }
  
  final void collectOneValue(int index, @ParametricNullness V returnValue) {
    List<Present<V>> localValues = this.values;
    if (localValues != null)
      localValues.set(index, new Present<>(returnValue)); 
  }
  
  final void handleAllCompleted() {
    List<Present<V>> localValues = this.values;
    if (localValues != null)
      set(combine(localValues)); 
  }
  
  void releaseResources(ReleaseResourcesReason reason) {
    super.releaseResources(reason);
    this.values = null;
  }
  
  abstract C combine(List<Present<V>> paramList);
  
  static final class ListFuture<V> extends CollectionFuture<V, List<V>> {
    ListFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
      super(futures, allMustSucceed);
      init();
    }
    
    public List<V> combine(List<Present<V>> values) {
      List<V> result = Lists.newArrayListWithCapacity(values.size());
      for (Present<V> element : values)
        result.add((element != null) ? element.value : null); 
      return Collections.unmodifiableList(result);
    }
  }
  
  private static final class Present<V> {
    V value;
    
    Present(V value) {
      this.value = value;
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\CollectionFuture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */