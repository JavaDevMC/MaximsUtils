package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
public interface MutableValueGraph<N, V> extends ValueGraph<N, V> {
  @CanIgnoreReturnValue
  boolean addNode(N paramN);
  
  @CheckForNull
  @CanIgnoreReturnValue
  V putEdgeValue(N paramN1, N paramN2, V paramV);
  
  @CheckForNull
  @CanIgnoreReturnValue
  V putEdgeValue(EndpointPair<N> paramEndpointPair, V paramV);
  
  @CanIgnoreReturnValue
  boolean removeNode(N paramN);
  
  @CheckForNull
  @CanIgnoreReturnValue
  V removeEdge(N paramN1, N paramN2);
  
  @CheckForNull
  @CanIgnoreReturnValue
  V removeEdge(EndpointPair<N> paramEndpointPair);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\graph\MutableValueGraph.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */