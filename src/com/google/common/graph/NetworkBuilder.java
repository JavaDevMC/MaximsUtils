package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@Beta
public final class NetworkBuilder<N, E> extends AbstractGraphBuilder<N> {
  boolean allowsParallelEdges = false;
  
  ElementOrder<? super E> edgeOrder = ElementOrder.insertion();
  
  Optional<Integer> expectedEdgeCount = Optional.absent();
  
  private NetworkBuilder(boolean directed) {
    super(directed);
  }
  
  public static NetworkBuilder<Object, Object> directed() {
    return new NetworkBuilder<>(true);
  }
  
  public static NetworkBuilder<Object, Object> undirected() {
    return new NetworkBuilder<>(false);
  }
  
  public static <N, E> NetworkBuilder<N, E> from(Network<N, E> network) {
    return (new NetworkBuilder<>(network.isDirected()))
      .allowsParallelEdges(network.allowsParallelEdges())
      .allowsSelfLoops(network.allowsSelfLoops())
      .nodeOrder(network.nodeOrder())
      .edgeOrder(network.edgeOrder());
  }
  
  public <N1 extends N, E1 extends E> ImmutableNetwork.Builder<N1, E1> immutable() {
    NetworkBuilder<N1, E1> castBuilder = cast();
    return new ImmutableNetwork.Builder<>(castBuilder);
  }
  
  public NetworkBuilder<N, E> allowsParallelEdges(boolean allowsParallelEdges) {
    this.allowsParallelEdges = allowsParallelEdges;
    return this;
  }
  
  public NetworkBuilder<N, E> allowsSelfLoops(boolean allowsSelfLoops) {
    this.allowsSelfLoops = allowsSelfLoops;
    return this;
  }
  
  public NetworkBuilder<N, E> expectedNodeCount(int expectedNodeCount) {
    this.expectedNodeCount = Optional.of(Integer.valueOf(Graphs.checkNonNegative(expectedNodeCount)));
    return this;
  }
  
  public NetworkBuilder<N, E> expectedEdgeCount(int expectedEdgeCount) {
    this.expectedEdgeCount = Optional.of(Integer.valueOf(Graphs.checkNonNegative(expectedEdgeCount)));
    return this;
  }
  
  public <N1 extends N> NetworkBuilder<N1, E> nodeOrder(ElementOrder<N1> nodeOrder) {
    NetworkBuilder<N1, E> newBuilder = cast();
    newBuilder.nodeOrder = (ElementOrder<N>)Preconditions.checkNotNull(nodeOrder);
    return newBuilder;
  }
  
  public <E1 extends E> NetworkBuilder<N, E1> edgeOrder(ElementOrder<E1> edgeOrder) {
    NetworkBuilder<N, E1> newBuilder = cast();
    newBuilder.edgeOrder = (ElementOrder<? super E>)Preconditions.checkNotNull(edgeOrder);
    return newBuilder;
  }
  
  public <N1 extends N, E1 extends E> MutableNetwork<N1, E1> build() {
    return new StandardMutableNetwork<>(this);
  }
  
  private <N1 extends N, E1 extends E> NetworkBuilder<N1, E1> cast() {
    return this;
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\graph\NetworkBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */