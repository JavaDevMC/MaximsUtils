package com.google.common.graph;

import com.google.common.base.Optional;

@ElementTypesAreNonnullByDefault
abstract class AbstractGraphBuilder<N> {
  final boolean directed;
  
  boolean allowsSelfLoops = false;
  
  ElementOrder<N> nodeOrder = ElementOrder.insertion();
  
  ElementOrder<N> incidentEdgeOrder = ElementOrder.unordered();
  
  Optional<Integer> expectedNodeCount = Optional.absent();
  
  AbstractGraphBuilder(boolean directed) {
    this.directed = directed;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\graph\AbstractGraphBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */