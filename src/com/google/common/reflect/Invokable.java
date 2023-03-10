package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
public abstract class Invokable<T, R> implements AnnotatedElement, Member {
  private final AccessibleObject accessibleObject;
  
  private final Member member;
  
  <M extends AccessibleObject & Member> Invokable(M member) {
    Preconditions.checkNotNull(member);
    this.accessibleObject = (AccessibleObject)member;
    this.member = (Member)member;
  }
  
  public static Invokable<?, Object> from(Method method) {
    return new MethodInvokable(method);
  }
  
  public static <T> Invokable<T, T> from(Constructor<T> constructor) {
    return new ConstructorInvokable<>(constructor);
  }
  
  public final boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
    return this.accessibleObject.isAnnotationPresent(annotationClass);
  }
  
  @CheckForNull
  public final <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
    return this.accessibleObject.getAnnotation(annotationClass);
  }
  
  public final Annotation[] getAnnotations() {
    return this.accessibleObject.getAnnotations();
  }
  
  public final Annotation[] getDeclaredAnnotations() {
    return this.accessibleObject.getDeclaredAnnotations();
  }
  
  public abstract TypeVariable<?>[] getTypeParameters();
  
  public final void setAccessible(boolean flag) {
    this.accessibleObject.setAccessible(flag);
  }
  
  public final boolean trySetAccessible() {
    try {
      this.accessibleObject.setAccessible(true);
      return true;
    } catch (RuntimeException e) {
      return false;
    } 
  }
  
  public final boolean isAccessible() {
    return this.accessibleObject.isAccessible();
  }
  
  public final String getName() {
    return this.member.getName();
  }
  
  public final int getModifiers() {
    return this.member.getModifiers();
  }
  
  public final boolean isSynthetic() {
    return this.member.isSynthetic();
  }
  
  public final boolean isPublic() {
    return Modifier.isPublic(getModifiers());
  }
  
  public final boolean isProtected() {
    return Modifier.isProtected(getModifiers());
  }
  
  public final boolean isPackagePrivate() {
    return (!isPrivate() && !isPublic() && !isProtected());
  }
  
  public final boolean isPrivate() {
    return Modifier.isPrivate(getModifiers());
  }
  
  public final boolean isStatic() {
    return Modifier.isStatic(getModifiers());
  }
  
  public final boolean isFinal() {
    return Modifier.isFinal(getModifiers());
  }
  
  public final boolean isAbstract() {
    return Modifier.isAbstract(getModifiers());
  }
  
  public final boolean isNative() {
    return Modifier.isNative(getModifiers());
  }
  
  public final boolean isSynchronized() {
    return Modifier.isSynchronized(getModifiers());
  }
  
  final boolean isVolatile() {
    return Modifier.isVolatile(getModifiers());
  }
  
  final boolean isTransient() {
    return Modifier.isTransient(getModifiers());
  }
  
  public boolean equals(@CheckForNull Object obj) {
    if (obj instanceof Invokable) {
      Invokable<?, ?> that = (Invokable<?, ?>)obj;
      return (getOwnerType().equals(that.getOwnerType()) && this.member.equals(that.member));
    } 
    return false;
  }
  
  public int hashCode() {
    return this.member.hashCode();
  }
  
  public String toString() {
    return this.member.toString();
  }
  
  public abstract boolean isOverridable();
  
  public abstract boolean isVarArgs();
  
  @CheckForNull
  @CanIgnoreReturnValue
  public final R invoke(@CheckForNull T receiver, Object... args) throws InvocationTargetException, IllegalAccessException {
    return (R)invokeInternal(receiver, (Object[])Preconditions.checkNotNull(args));
  }
  
  public final TypeToken<? extends R> getReturnType() {
    return (TypeToken)TypeToken.of(getGenericReturnType());
  }
  
  public final ImmutableList<Parameter> getParameters() {
    Type[] parameterTypes = getGenericParameterTypes();
    Annotation[][] annotations = getParameterAnnotations();
    AnnotatedType[] annotatedTypes = getAnnotatedParameterTypes();
    ImmutableList.Builder<Parameter> builder = ImmutableList.builder();
    for (int i = 0; i < parameterTypes.length; i++)
      builder.add(new Parameter(this, i, 
            
            TypeToken.of(parameterTypes[i]), annotations[i], annotatedTypes[i])); 
    return builder.build();
  }
  
  public final ImmutableList<TypeToken<? extends Throwable>> getExceptionTypes() {
    ImmutableList.Builder<TypeToken<? extends Throwable>> builder = ImmutableList.builder();
    for (Type type : getGenericExceptionTypes()) {
      TypeToken<? extends Throwable> exceptionType = (TypeToken)TypeToken.of(type);
      builder.add(exceptionType);
    } 
    return builder.build();
  }
  
  public final <R1 extends R> Invokable<T, R1> returning(Class<R1> returnType) {
    return returning(TypeToken.of(returnType));
  }
  
  public final <R1 extends R> Invokable<T, R1> returning(TypeToken<R1> returnType) {
    if (!returnType.isSupertypeOf(getReturnType())) {
      String str1 = String.valueOf(getReturnType()), str2 = String.valueOf(returnType);
      throw new IllegalArgumentException((new StringBuilder(35 + String.valueOf(str1).length() + String.valueOf(str2).length())).append("Invokable is known to return ").append(str1).append(", not ").append(str2).toString());
    } 
    Invokable<T, R1> specialized = this;
    return specialized;
  }
  
  public final Class<? super T> getDeclaringClass() {
    return (Class)this.member.getDeclaringClass();
  }
  
  public TypeToken<T> getOwnerType() {
    return TypeToken.of((Class)getDeclaringClass());
  }
  
  @CheckForNull
  abstract Object invokeInternal(@CheckForNull Object paramObject, Object[] paramArrayOfObject) throws InvocationTargetException, IllegalAccessException;
  
  abstract Type[] getGenericParameterTypes();
  
  abstract AnnotatedType[] getAnnotatedParameterTypes();
  
  abstract Type[] getGenericExceptionTypes();
  
  abstract Annotation[][] getParameterAnnotations();
  
  abstract Type getGenericReturnType();
  
  public abstract AnnotatedType getAnnotatedReturnType();
  
  static class MethodInvokable<T> extends Invokable<T, Object> {
    final Method method;
    
    MethodInvokable(Method method) {
      super(method);
      this.method = method;
    }
    
    @CheckForNull
    final Object invokeInternal(@CheckForNull Object receiver, Object[] args) throws InvocationTargetException, IllegalAccessException {
      return this.method.invoke(receiver, args);
    }
    
    Type getGenericReturnType() {
      return this.method.getGenericReturnType();
    }
    
    Type[] getGenericParameterTypes() {
      return this.method.getGenericParameterTypes();
    }
    
    AnnotatedType[] getAnnotatedParameterTypes() {
      return this.method.getAnnotatedParameterTypes();
    }
    
    public AnnotatedType getAnnotatedReturnType() {
      return this.method.getAnnotatedReturnType();
    }
    
    Type[] getGenericExceptionTypes() {
      return this.method.getGenericExceptionTypes();
    }
    
    final Annotation[][] getParameterAnnotations() {
      return this.method.getParameterAnnotations();
    }
    
    public final TypeVariable<?>[] getTypeParameters() {
      return (TypeVariable<?>[])this.method.getTypeParameters();
    }
    
    public final boolean isOverridable() {
      return (!isFinal() && 
        !isPrivate() && 
        !isStatic() && 
        !Modifier.isFinal(getDeclaringClass().getModifiers()));
    }
    
    public final boolean isVarArgs() {
      return this.method.isVarArgs();
    }
  }
  
  static class ConstructorInvokable<T> extends Invokable<T, T> {
    final Constructor<?> constructor;
    
    ConstructorInvokable(Constructor<?> constructor) {
      super(constructor);
      this.constructor = constructor;
    }
    
    final Object invokeInternal(@CheckForNull Object receiver, Object[] args) throws InvocationTargetException, IllegalAccessException {
      try {
        return this.constructor.newInstance(args);
      } catch (InstantiationException e) {
        String str = String.valueOf(this.constructor);
        throw new RuntimeException((new StringBuilder(8 + String.valueOf(str).length())).append(str).append(" failed.").toString(), e);
      } 
    }
    
    Type getGenericReturnType() {
      Class<?> declaringClass = getDeclaringClass();
      TypeVariable[] arrayOfTypeVariable = (TypeVariable[])declaringClass.getTypeParameters();
      if (arrayOfTypeVariable.length > 0)
        return Types.newParameterizedType(declaringClass, (Type[])arrayOfTypeVariable); 
      return declaringClass;
    }
    
    Type[] getGenericParameterTypes() {
      Type[] types = this.constructor.getGenericParameterTypes();
      if (types.length > 0 && mayNeedHiddenThis()) {
        Class<?>[] rawParamTypes = this.constructor.getParameterTypes();
        if (types.length == rawParamTypes.length && rawParamTypes[0] == 
          getDeclaringClass().getEnclosingClass())
          return Arrays.<Type>copyOfRange(types, 1, types.length); 
      } 
      return types;
    }
    
    AnnotatedType[] getAnnotatedParameterTypes() {
      return this.constructor.getAnnotatedParameterTypes();
    }
    
    public AnnotatedType getAnnotatedReturnType() {
      return this.constructor.getAnnotatedReturnType();
    }
    
    Type[] getGenericExceptionTypes() {
      return this.constructor.getGenericExceptionTypes();
    }
    
    final Annotation[][] getParameterAnnotations() {
      return this.constructor.getParameterAnnotations();
    }
    
    public final TypeVariable<?>[] getTypeParameters() {
      TypeVariable[] arrayOfTypeVariable1 = (TypeVariable[])getDeclaringClass().getTypeParameters();
      TypeVariable[] arrayOfTypeVariable2 = (TypeVariable[])this.constructor.getTypeParameters();
      TypeVariable[] arrayOfTypeVariable3 = new TypeVariable[arrayOfTypeVariable1.length + arrayOfTypeVariable2.length];
      System.arraycopy(arrayOfTypeVariable1, 0, arrayOfTypeVariable3, 0, arrayOfTypeVariable1.length);
      System.arraycopy(arrayOfTypeVariable2, 0, arrayOfTypeVariable3, arrayOfTypeVariable1.length, arrayOfTypeVariable2.length);
      return (TypeVariable<?>[])arrayOfTypeVariable3;
    }
    
    public final boolean isOverridable() {
      return false;
    }
    
    public final boolean isVarArgs() {
      return this.constructor.isVarArgs();
    }
    
    private boolean mayNeedHiddenThis() {
      Class<?> declaringClass = this.constructor.getDeclaringClass();
      if (declaringClass.getEnclosingConstructor() != null)
        return true; 
      Method enclosingMethod = declaringClass.getEnclosingMethod();
      if (enclosingMethod != null)
        return !Modifier.isStatic(enclosingMethod.getModifiers()); 
      return (declaringClass.getEnclosingClass() != null && 
        !Modifier.isStatic(declaringClass.getModifiers()));
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\reflect\Invokable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */