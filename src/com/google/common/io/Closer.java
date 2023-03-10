package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
@GwtIncompatible
public final class Closer implements Closeable {
  private static final Suppressor SUPPRESSOR;
  
  @VisibleForTesting
  final Suppressor suppressor;
  
  static {
    SuppressingSuppressor suppressingSuppressor = SuppressingSuppressor.tryCreate();
    SUPPRESSOR = (suppressingSuppressor == null) ? LoggingSuppressor.INSTANCE : suppressingSuppressor;
  }
  
  public static Closer create() {
    return new Closer(SUPPRESSOR);
  }
  
  private final Deque<Closeable> stack = new ArrayDeque<>(4);
  
  @CheckForNull
  private Throwable thrown;
  
  @VisibleForTesting
  Closer(Suppressor suppressor) {
    this.suppressor = (Suppressor)Preconditions.checkNotNull(suppressor);
  }
  
  @ParametricNullness
  @CanIgnoreReturnValue
  public <C extends Closeable> C register(@ParametricNullness C closeable) {
    if (closeable != null)
      this.stack.addFirst((Closeable)closeable); 
    return closeable;
  }
  
  public RuntimeException rethrow(Throwable e) throws IOException {
    Preconditions.checkNotNull(e);
    this.thrown = e;
    Throwables.propagateIfPossible(e, IOException.class);
    throw new RuntimeException(e);
  }
  
  public <X extends Exception> RuntimeException rethrow(Throwable e, Class<X> declaredType) throws IOException, X {
    Preconditions.checkNotNull(e);
    this.thrown = e;
    Throwables.propagateIfPossible(e, IOException.class);
    Throwables.propagateIfPossible(e, declaredType);
    throw new RuntimeException(e);
  }
  
  public <X1 extends Exception, X2 extends Exception> RuntimeException rethrow(Throwable e, Class<X1> declaredType1, Class<X2> declaredType2) throws IOException, X1, X2 {
    Preconditions.checkNotNull(e);
    this.thrown = e;
    Throwables.propagateIfPossible(e, IOException.class);
    Throwables.propagateIfPossible(e, declaredType1, declaredType2);
    throw new RuntimeException(e);
  }
  
  public void close() throws IOException {
    Throwable throwable = this.thrown;
    while (!this.stack.isEmpty()) {
      Closeable closeable = this.stack.removeFirst();
      try {
        closeable.close();
      } catch (Throwable e) {
        if (throwable == null) {
          throwable = e;
          continue;
        } 
        this.suppressor.suppress(closeable, throwable, e);
      } 
    } 
    if (this.thrown == null && throwable != null) {
      Throwables.propagateIfPossible(throwable, IOException.class);
      throw new AssertionError(throwable);
    } 
  }
  
  @VisibleForTesting
  static interface Suppressor {
    void suppress(Closeable param1Closeable, Throwable param1Throwable1, Throwable param1Throwable2);
  }
  
  @VisibleForTesting
  static final class LoggingSuppressor implements Suppressor {
    static final LoggingSuppressor INSTANCE = new LoggingSuppressor();
    
    public void suppress(Closeable closeable, Throwable thrown, Throwable suppressed) {
      String str = String.valueOf(closeable);
      Closeables.logger.log(Level.WARNING, (new StringBuilder(42 + String.valueOf(str).length())).append("Suppressing exception thrown when closing ").append(str).toString(), suppressed);
    }
  }
  
  @VisibleForTesting
  static final class SuppressingSuppressor implements Suppressor {
    private final Method addSuppressed;
    
    @CheckForNull
    static SuppressingSuppressor tryCreate() {
      Method addSuppressed;
      try {
        addSuppressed = Throwable.class.getMethod("addSuppressed", new Class[] { Throwable.class });
      } catch (Throwable e) {
        return null;
      } 
      return new SuppressingSuppressor(addSuppressed);
    }
    
    private SuppressingSuppressor(Method addSuppressed) {
      this.addSuppressed = addSuppressed;
    }
    
    public void suppress(Closeable closeable, Throwable thrown, Throwable suppressed) {
      if (thrown == suppressed)
        return; 
      try {
        this.addSuppressed.invoke(thrown, new Object[] { suppressed });
      } catch (Throwable e) {
        LoggingSuppressor.INSTANCE.suppress(closeable, thrown, suppressed);
      } 
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\io\Closer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */