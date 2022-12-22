package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Implement with a lambda, or use GraphBuilder to build a Graph with the desired edges")
@ElementTypesAreNonnullByDefault
@Beta
public interface SuccessorsFunction<N> {
  Iterable<? extends N> successors(N paramN);
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\graph\SuccessorsFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */