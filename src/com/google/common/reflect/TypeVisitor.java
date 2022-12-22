package com.google.common.reflect;

import com.google.common.collect.Sets;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Set;

@ElementTypesAreNonnullByDefault
abstract class TypeVisitor {
  private final Set<Type> visited = Sets.newHashSet();
  
  public final void visit(Type... types) {
    for (Type type : types) {
      if (type != null && this.visited.add(type))
        boolean succeeded = false; 
    } 
  }
  
  void visitClass(Class<?> t) {}
  
  void visitGenericArrayType(GenericArrayType t) {}
  
  void visitParameterizedType(ParameterizedType t) {}
  
  void visitTypeVariable(TypeVariable<?> t) {}
  
  void visitWildcardType(WildcardType t) {}
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\reflect\TypeVisitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */