package com.google.common.graph;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@ElementTypesAreNonnullByDefault
final class UndirectedNetworkConnections<N, E> extends AbstractUndirectedNetworkConnections<N, E> {
  UndirectedNetworkConnections(Map<E, N> incidentEdgeMap) {
    super(incidentEdgeMap);
  }
  
  static <N, E> UndirectedNetworkConnections<N, E> of() {
    return new UndirectedNetworkConnections<>((Map<E, N>)HashBiMap.create(2));
  }
  
  static <N, E> UndirectedNetworkConnections<N, E> ofImmutable(Map<E, N> incidentEdges) {
    return new UndirectedNetworkConnections<>((Map<E, N>)ImmutableBiMap.copyOf(incidentEdges));
  }
  
  public Set<N> adjacentNodes() {
    return Collections.unmodifiableSet(((BiMap)this.incidentEdgeMap).values());
  }
  
  public Set<E> edgesConnecting(N node) {
    return new EdgesConnecting<>((Map<?, E>)((BiMap)this.incidentEdgeMap).inverse(), node);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\graph\UndirectedNetworkConnections.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */