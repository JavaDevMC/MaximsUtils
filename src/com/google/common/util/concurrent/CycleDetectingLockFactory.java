package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
@CanIgnoreReturnValue
@GwtIncompatible
public class CycleDetectingLockFactory {
  @Beta
  public enum Policies implements Policy {
    THROW {
      public void handlePotentialDeadlock(PotentialDeadlockException e) {
        throw e;
      }
    },
    WARN {
      public void handlePotentialDeadlock(PotentialDeadlockException e) {
        CycleDetectingLockFactory.logger.log(Level.SEVERE, "Detected potential deadlock", e);
      }
    },
    DISABLED {
      public void handlePotentialDeadlock(PotentialDeadlockException e) {}
    };
  }
  
  public static CycleDetectingLockFactory newInstance(Policy policy) {
    return new CycleDetectingLockFactory(policy);
  }
  
  public ReentrantLock newReentrantLock(String lockName) {
    return newReentrantLock(lockName, false);
  }
  
  public ReentrantLock newReentrantLock(String lockName, boolean fair) {
    return (this.policy == Policies.DISABLED) ? 
      new ReentrantLock(fair) : 
      new CycleDetectingReentrantLock(new LockGraphNode(lockName), fair);
  }
  
  public ReentrantReadWriteLock newReentrantReadWriteLock(String lockName) {
    return newReentrantReadWriteLock(lockName, false);
  }
  
  public ReentrantReadWriteLock newReentrantReadWriteLock(String lockName, boolean fair) {
    return (this.policy == Policies.DISABLED) ? 
      new ReentrantReadWriteLock(fair) : 
      new CycleDetectingReentrantReadWriteLock(new LockGraphNode(lockName), fair);
  }
  
  private static final ConcurrentMap<Class<? extends Enum<?>>, Map<? extends Enum<?>, LockGraphNode>> lockGraphNodesPerType = (new MapMaker()).weakKeys().makeMap();
  
  public static <E extends Enum<E>> WithExplicitOrdering<E> newInstanceWithExplicitOrdering(Class<E> enumClass, Policy policy) {
    Preconditions.checkNotNull(enumClass);
    Preconditions.checkNotNull(policy);
    Map<E, LockGraphNode> lockGraphNodes = (Map)getOrCreateNodes(enumClass);
    return new WithExplicitOrdering<>(policy, lockGraphNodes);
  }
  
  private static <E extends Enum<E>> Map<? extends E, LockGraphNode> getOrCreateNodes(Class<E> clazz) {
    Map<E, LockGraphNode> existing = (Map<E, LockGraphNode>)lockGraphNodesPerType.get(clazz);
    if (existing != null)
      return existing; 
    Map<E, LockGraphNode> created = createNodes(clazz);
    existing = (Map<E, LockGraphNode>)lockGraphNodesPerType.putIfAbsent(clazz, created);
    return (Map<? extends E, LockGraphNode>)MoreObjects.firstNonNull(existing, created);
  }
  
  @VisibleForTesting
  static <E extends Enum<E>> Map<E, LockGraphNode> createNodes(Class<E> clazz) {
    EnumMap<E, LockGraphNode> map = Maps.newEnumMap(clazz);
    Enum[] arrayOfEnum = (Enum[])clazz.getEnumConstants();
    int numKeys = arrayOfEnum.length;
    ArrayList<LockGraphNode> nodes = Lists.newArrayListWithCapacity(numKeys);
    for (Enum<?> enum_ : arrayOfEnum) {
      LockGraphNode node = new LockGraphNode(getLockName(enum_));
      nodes.add(node);
      map.put((E)enum_, node);
    } 
    int i;
    for (i = 1; i < numKeys; i++)
      ((LockGraphNode)nodes.get(i)).checkAcquiredLocks(Policies.THROW, nodes.subList(0, i)); 
    for (i = 0; i < numKeys - 1; i++)
      ((LockGraphNode)nodes.get(i)).checkAcquiredLocks(Policies.DISABLED, nodes.subList(i + 1, numKeys)); 
    return Collections.unmodifiableMap(map);
  }
  
  private static String getLockName(Enum<?> rank) {
    String str1 = rank.getDeclaringClass().getSimpleName(), str2 = rank.name();
    return (new StringBuilder(1 + String.valueOf(str1).length() + String.valueOf(str2).length())).append(str1).append(".").append(str2).toString();
  }
  
  @Beta
  public static final class WithExplicitOrdering<E extends Enum<E>> extends CycleDetectingLockFactory {
    private final Map<E, LockGraphNode> lockGraphNodes;
    
    @VisibleForTesting
    WithExplicitOrdering(Policy policy, Map<E, LockGraphNode> lockGraphNodes) {
      super(policy);
      this.lockGraphNodes = lockGraphNodes;
    }
    
    public ReentrantLock newReentrantLock(E rank) {
      return newReentrantLock(rank, false);
    }
    
    public ReentrantLock newReentrantLock(E rank, boolean fair) {
      return (this.policy == Policies.DISABLED) ?
        new ReentrantLock(fair) : 
        
        new CycleDetectingReentrantLock(Objects.<LockGraphNode>requireNonNull(this.lockGraphNodes.get(rank)), fair);
    }
    
    public ReentrantReadWriteLock newReentrantReadWriteLock(E rank) {
      return newReentrantReadWriteLock(rank, false);
    }
    
    public ReentrantReadWriteLock newReentrantReadWriteLock(E rank, boolean fair) {
      return (this.policy == Policies.DISABLED) ?
        new ReentrantReadWriteLock(fair) : 
        
        new CycleDetectingReentrantReadWriteLock(
          Objects.<LockGraphNode>requireNonNull(this.lockGraphNodes.get(rank)), fair);
    }
  }
  
  private static final Logger logger = Logger.getLogger(CycleDetectingLockFactory.class.getName());
  
  final Policy policy;
  
  private CycleDetectingLockFactory(Policy policy) {
    this.policy = (Policy)Preconditions.checkNotNull(policy);
  }
  
  private static final ThreadLocal<ArrayList<LockGraphNode>> acquiredLocks = new ThreadLocal<ArrayList<LockGraphNode>>() {
      protected ArrayList<LockGraphNode> initialValue() {
        return Lists.newArrayListWithCapacity(3);
      }
    };
  
  private static class ExampleStackTrace extends IllegalStateException {
    static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];
    
    static final ImmutableSet<String> EXCLUDED_CLASS_NAMES = ImmutableSet.of(CycleDetectingLockFactory.class
        .getName(), ExampleStackTrace.class
        .getName(), LockGraphNode.class
        .getName());
    
    ExampleStackTrace(LockGraphNode node1, LockGraphNode node2) {
      super((new StringBuilder(4 + String.valueOf(str1).length() + String.valueOf(str2).length())).append(str1).append(" -> ").append(str2).toString());
      StackTraceElement[] origStackTrace = getStackTrace();
      for (int i = 0, n = origStackTrace.length; i < n; i++) {
        if (WithExplicitOrdering.class.getName().equals(origStackTrace[i].getClassName())) {
          setStackTrace(EMPTY_STACK_TRACE);
          break;
        } 
        if (!EXCLUDED_CLASS_NAMES.contains(origStackTrace[i].getClassName())) {
          setStackTrace(Arrays.<StackTraceElement>copyOfRange(origStackTrace, i, n));
          break;
        } 
      } 
    }
  }
  
  @Beta
  public static final class PotentialDeadlockException extends ExampleStackTrace {
    private final ExampleStackTrace conflictingStackTrace;
    
    private PotentialDeadlockException(LockGraphNode node1, LockGraphNode node2, ExampleStackTrace conflictingStackTrace) {
      super(node1, node2);
      this.conflictingStackTrace = conflictingStackTrace;
      initCause(conflictingStackTrace);
    }
    
    public ExampleStackTrace getConflictingStackTrace() {
      return this.conflictingStackTrace;
    }
    
    public String getMessage() {
      StringBuilder message = new StringBuilder(Objects.<String>requireNonNull(super.getMessage()));
      for (Throwable t = this.conflictingStackTrace; t != null; t = t.getCause())
        message.append(", ").append(t.getMessage()); 
      return message.toString();
    }
  }
  
  private static class LockGraphNode {
    final Map<LockGraphNode, ExampleStackTrace> allowedPriorLocks = (new MapMaker())
      .weakKeys().makeMap();
    
    final Map<LockGraphNode, PotentialDeadlockException> disallowedPriorLocks = (new MapMaker())
      .weakKeys().makeMap();
    
    final String lockName;
    
    LockGraphNode(String lockName) {
      this.lockName = (String)Preconditions.checkNotNull(lockName);
    }
    
    String getLockName() {
      return this.lockName;
    }
    
    void checkAcquiredLocks(Policy policy, List<LockGraphNode> acquiredLocks) {
      for (LockGraphNode acquiredLock : acquiredLocks)
        checkAcquiredLock(policy, acquiredLock); 
    }
    
    void checkAcquiredLock(Policy policy, LockGraphNode acquiredLock) {
      Preconditions.checkState((this != acquiredLock), "Attempted to acquire multiple locks with the same rank %s", acquiredLock
          
          .getLockName());
      if (this.allowedPriorLocks.containsKey(acquiredLock))
        return; 
      PotentialDeadlockException previousDeadlockException = this.disallowedPriorLocks.get(acquiredLock);
      if (previousDeadlockException != null) {
        PotentialDeadlockException exception = new PotentialDeadlockException(acquiredLock, this, previousDeadlockException.getConflictingStackTrace());
        policy.handlePotentialDeadlock(exception);
        return;
      } 
      Set<LockGraphNode> seen = Sets.newIdentityHashSet();
      ExampleStackTrace path = acquiredLock.findPathTo(this, seen);
      if (path == null) {
        this.allowedPriorLocks.put(acquiredLock, new ExampleStackTrace(acquiredLock, this));
      } else {
        PotentialDeadlockException exception = new PotentialDeadlockException(acquiredLock, this, path);
        this.disallowedPriorLocks.put(acquiredLock, exception);
        policy.handlePotentialDeadlock(exception);
      } 
    }
    
    @CheckForNull
    private CycleDetectingLockFactory.ExampleStackTrace findPathTo(LockGraphNode node, Set<LockGraphNode> seen) {
      if (!seen.add(this))
        return null; 
      ExampleStackTrace found = this.allowedPriorLocks.get(node);
      if (found != null)
        return found; 
      for (Map.Entry<LockGraphNode, ExampleStackTrace> entry : this.allowedPriorLocks.entrySet()) {
        LockGraphNode preAcquiredLock = entry.getKey();
        found = preAcquiredLock.findPathTo(node, seen);
        if (found != null) {
          ExampleStackTrace path = new ExampleStackTrace(preAcquiredLock, this);
          path.setStackTrace(((ExampleStackTrace)entry.getValue()).getStackTrace());
          path.initCause(found);
          return path;
        } 
      } 
      return null;
    }
  }
  
  private void aboutToAcquire(CycleDetectingLock lock) {
    if (!lock.isAcquiredByCurrentThread()) {
      ArrayList<LockGraphNode> acquiredLockList = acquiredLocks.get();
      LockGraphNode node = lock.getLockGraphNode();
      node.checkAcquiredLocks(this.policy, acquiredLockList);
      acquiredLockList.add(node);
    } 
  }
  
  private static void lockStateChanged(CycleDetectingLock lock) {
    if (!lock.isAcquiredByCurrentThread()) {
      ArrayList<LockGraphNode> acquiredLockList = acquiredLocks.get();
      LockGraphNode node = lock.getLockGraphNode();
      for (int i = acquiredLockList.size() - 1; i >= 0; i--) {
        if (acquiredLockList.get(i) == node) {
          acquiredLockList.remove(i);
          break;
        } 
      } 
    } 
  }
  
  final class CycleDetectingReentrantLock extends ReentrantLock implements CycleDetectingLock {
    private final LockGraphNode lockGraphNode;
    
    private CycleDetectingReentrantLock(LockGraphNode lockGraphNode, boolean fair) {
      super(fair);
      this.lockGraphNode = (LockGraphNode)Preconditions.checkNotNull(lockGraphNode);
    }
    
    public LockGraphNode getLockGraphNode() {
      return this.lockGraphNode;
    }
    
    public boolean isAcquiredByCurrentThread() {
      return isHeldByCurrentThread();
    }
    
    public void lock() {
      CycleDetectingLockFactory.this.aboutToAcquire(this);
      try {
        super.lock();
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this);
      } 
    }
    
    public void lockInterruptibly() throws InterruptedException {
      CycleDetectingLockFactory.this.aboutToAcquire(this);
      try {
        super.lockInterruptibly();
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this);
      } 
    }
    
    public boolean tryLock() {
      CycleDetectingLockFactory.this.aboutToAcquire(this);
      try {
        return super.tryLock();
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this);
      } 
    }
    
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
      CycleDetectingLockFactory.this.aboutToAcquire(this);
      try {
        return super.tryLock(timeout, unit);
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this);
      } 
    }
    
    public void unlock() {
      try {
        super.unlock();
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this);
      } 
    }
  }
  
  final class CycleDetectingReentrantReadWriteLock extends ReentrantReadWriteLock implements CycleDetectingLock {
    private final CycleDetectingReentrantReadLock readLock;
    
    private final CycleDetectingReentrantWriteLock writeLock;
    
    private final LockGraphNode lockGraphNode;
    
    private CycleDetectingReentrantReadWriteLock(CycleDetectingLockFactory this$0, LockGraphNode lockGraphNode, boolean fair) {
      super(fair);
      this.readLock = new CycleDetectingReentrantReadLock(this);
      this.writeLock = new CycleDetectingReentrantWriteLock(this);
      this.lockGraphNode = (LockGraphNode)Preconditions.checkNotNull(lockGraphNode);
    }
    
    public ReadLock readLock() {
      return this.readLock;
    }
    
    public WriteLock writeLock() {
      return this.writeLock;
    }
    
    public LockGraphNode getLockGraphNode() {
      return this.lockGraphNode;
    }
    
    public boolean isAcquiredByCurrentThread() {
      return (isWriteLockedByCurrentThread() || getReadHoldCount() > 0);
    }
  }
  
  private class CycleDetectingReentrantReadLock extends ReentrantReadWriteLock.ReadLock {
    @Weak
    final CycleDetectingReentrantReadWriteLock readWriteLock;
    
    CycleDetectingReentrantReadLock(CycleDetectingReentrantReadWriteLock readWriteLock) {
      this.readWriteLock = readWriteLock;
    }
    
    public void lock() {
      CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
      try {
        super.lock();
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
      } 
    }
    
    public void lockInterruptibly() throws InterruptedException {
      CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
      try {
        super.lockInterruptibly();
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
      } 
    }
    
    public boolean tryLock() {
      CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
      try {
        return super.tryLock();
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
      } 
    }
    
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
      CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
      try {
        return super.tryLock(timeout, unit);
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
      } 
    }
    
    public void unlock() {
      try {
        super.unlock();
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
      } 
    }
  }
  
  private class CycleDetectingReentrantWriteLock extends ReentrantReadWriteLock.WriteLock {
    @Weak
    final CycleDetectingReentrantReadWriteLock readWriteLock;
    
    CycleDetectingReentrantWriteLock(CycleDetectingReentrantReadWriteLock readWriteLock) {
      this.readWriteLock = readWriteLock;
    }
    
    public void lock() {
      CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
      try {
        super.lock();
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
      } 
    }
    
    public void lockInterruptibly() throws InterruptedException {
      CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
      try {
        super.lockInterruptibly();
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
      } 
    }
    
    public boolean tryLock() {
      CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
      try {
        return super.tryLock();
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
      } 
    }
    
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
      CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
      try {
        return super.tryLock(timeout, unit);
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
      } 
    }
    
    public void unlock() {
      try {
        super.unlock();
      } finally {
        CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
      } 
    }
  }
  
  private static interface CycleDetectingLock {
    LockGraphNode getLockGraphNode();
    
    boolean isAcquiredByCurrentThread();
  }
  
  @Beta
  public static interface Policy {
    void handlePotentialDeadlock(PotentialDeadlockException param1PotentialDeadlockException);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\CycleDetectingLockFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */