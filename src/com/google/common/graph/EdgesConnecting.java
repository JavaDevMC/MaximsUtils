package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class EdgesConnecting<E> extends AbstractSet<E> {
  private final Map<?, E> nodeToOutEdge;
  
  private final Object targetNode;
  
  EdgesConnecting(Map<?, E> nodeToEdgeMap, Object targetNode) {
    this.nodeToOutEdge = (Map<?, E>)Preconditions.checkNotNull(nodeToEdgeMap);
    this.targetNode = Preconditions.checkNotNull(targetNode);
  }
  
  public UnmodifiableIterator<E> iterator() {
    E connectingEdge = getConnectingEdge();
    return (connectingEdge == null) ? 
      ImmutableSet.of().iterator() : 
      Iterators.singletonIterator(connectingEdge);
  }
  
  public int size() {
    return (getConnectingEdge() == null) ? 0 : 1;
  }
  
  public boolean contains(@CheckForNull Object edge) {
    E connectingEdge = getConnectingEdge();
    return (connectingEdge != null && connectingEdge.equals(edge));
  }
  
  @CheckForNull
  private E getConnectingEdge() {
    return this.nodeToOutEdge.get(this.targetNode);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\graph\EdgesConnecting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */