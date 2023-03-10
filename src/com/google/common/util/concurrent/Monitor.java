package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
@GwtIncompatible
public final class Monitor {
  private final boolean fair;
  
  private final ReentrantLock lock;
  
  @Beta
  public static abstract class Guard {
    @Weak
    final Monitor monitor;
    
    final Condition condition;
    
    @GuardedBy("monitor.lock")
    int waiterCount = 0;
    
    @CheckForNull
    @GuardedBy("monitor.lock")
    Guard next;
    
    protected Guard(Monitor monitor) {
      this.monitor = (Monitor)Preconditions.checkNotNull(monitor, "monitor");
      this.condition = monitor.lock.newCondition();
    }
    
    public abstract boolean isSatisfied();
  }
  
  @CheckForNull
  @GuardedBy("lock")
  private Guard activeGuards = null;
  
  public Monitor() {
    this(false);
  }
  
  public Monitor(boolean fair) {
    this.fair = fair;
    this.lock = new ReentrantLock(fair);
  }
  
  public Guard newGuard(final BooleanSupplier isSatisfied) {
    Preconditions.checkNotNull(isSatisfied, "isSatisfied");
    return new Guard(this, this) {
        public boolean isSatisfied() {
          return isSatisfied.getAsBoolean();
        }
      };
  }
  
  public void enter() {
    this.lock.lock();
  }
  
  public boolean enter(Duration time) {
    return enter(Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
  }
  
  public boolean enter(long time, TimeUnit unit) {
    long timeoutNanos = toSafeNanos(time, unit);
    ReentrantLock lock = this.lock;
    if (!this.fair && lock.tryLock())
      return true; 
    boolean interrupted = Thread.interrupted();
    try {
      long startTime = System.nanoTime();
      long remainingNanos = timeoutNanos;
      while (true) {
        try {
          return lock.tryLock(remainingNanos, TimeUnit.NANOSECONDS);
        } catch (InterruptedException interrupt) {
          interrupted = true;
          remainingNanos = remainingNanos(startTime, timeoutNanos);
        } 
      } 
    } finally {
      if (interrupted)
        Thread.currentThread().interrupt(); 
    } 
  }
  
  public void enterInterruptibly() throws InterruptedException {
    this.lock.lockInterruptibly();
  }
  
  public boolean enterInterruptibly(Duration time) throws InterruptedException {
    return enterInterruptibly(Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
  }
  
  public boolean enterInterruptibly(long time, TimeUnit unit) throws InterruptedException {
    return this.lock.tryLock(time, unit);
  }
  
  public boolean tryEnter() {
    return this.lock.tryLock();
  }
  
  public void enterWhen(Guard guard) throws InterruptedException {
    if (guard.monitor != this)
      throw new IllegalMonitorStateException(); 
    ReentrantLock lock = this.lock;
    boolean signalBeforeWaiting = lock.isHeldByCurrentThread();
    lock.lockInterruptibly();
    boolean satisfied = false;
    try {
      if (!guard.isSatisfied())
        await(guard, signalBeforeWaiting); 
      satisfied = true;
    } finally {
      if (!satisfied)
        leave(); 
    } 
  }
  
  public boolean enterWhen(Guard guard, Duration time) throws InterruptedException {
    return enterWhen(guard, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
  }
  
  public boolean enterWhen(Guard guard, long time, TimeUnit unit) throws InterruptedException {
    // Byte code:
    //   0: lload_2
    //   1: aload #4
    //   3: invokestatic toSafeNanos : (JLjava/util/concurrent/TimeUnit;)J
    //   6: lstore #5
    //   8: aload_1
    //   9: getfield monitor : Lcom/google/common/util/concurrent/Monitor;
    //   12: aload_0
    //   13: if_acmpeq -> 24
    //   16: new java/lang/IllegalMonitorStateException
    //   19: dup
    //   20: invokespecial <init> : ()V
    //   23: athrow
    //   24: aload_0
    //   25: getfield lock : Ljava/util/concurrent/locks/ReentrantLock;
    //   28: astore #7
    //   30: aload #7
    //   32: invokevirtual isHeldByCurrentThread : ()Z
    //   35: istore #8
    //   37: lconst_0
    //   38: lstore #9
    //   40: aload_0
    //   41: getfield fair : Z
    //   44: ifne -> 72
    //   47: invokestatic interrupted : ()Z
    //   50: ifeq -> 61
    //   53: new java/lang/InterruptedException
    //   56: dup
    //   57: invokespecial <init> : ()V
    //   60: athrow
    //   61: aload #7
    //   63: invokevirtual tryLock : ()Z
    //   66: ifeq -> 72
    //   69: goto -> 92
    //   72: lload #5
    //   74: invokestatic initNanoTime : (J)J
    //   77: lstore #9
    //   79: aload #7
    //   81: lload_2
    //   82: aload #4
    //   84: invokevirtual tryLock : (JLjava/util/concurrent/TimeUnit;)Z
    //   87: ifne -> 92
    //   90: iconst_0
    //   91: ireturn
    //   92: iconst_0
    //   93: istore #11
    //   95: iconst_1
    //   96: istore #12
    //   98: aload_1
    //   99: invokevirtual isSatisfied : ()Z
    //   102: ifne -> 134
    //   105: aload_0
    //   106: aload_1
    //   107: lload #9
    //   109: lconst_0
    //   110: lcmp
    //   111: ifne -> 119
    //   114: lload #5
    //   116: goto -> 126
    //   119: lload #9
    //   121: lload #5
    //   123: invokestatic remainingNanos : (JJ)J
    //   126: iload #8
    //   128: invokespecial awaitNanos : (Lcom/google/common/util/concurrent/Monitor$Guard;JZ)Z
    //   131: ifeq -> 138
    //   134: iconst_1
    //   135: goto -> 139
    //   138: iconst_0
    //   139: istore #11
    //   141: iconst_0
    //   142: istore #12
    //   144: iload #11
    //   146: istore #13
    //   148: iload #11
    //   150: ifne -> 185
    //   153: iload #12
    //   155: ifeq -> 167
    //   158: iload #8
    //   160: ifne -> 167
    //   163: aload_0
    //   164: invokespecial signalNextWaiter : ()V
    //   167: aload #7
    //   169: invokevirtual unlock : ()V
    //   172: goto -> 185
    //   175: astore #14
    //   177: aload #7
    //   179: invokevirtual unlock : ()V
    //   182: aload #14
    //   184: athrow
    //   185: iload #13
    //   187: ireturn
    //   188: astore #15
    //   190: iload #11
    //   192: ifne -> 227
    //   195: iload #12
    //   197: ifeq -> 209
    //   200: iload #8
    //   202: ifne -> 209
    //   205: aload_0
    //   206: invokespecial signalNextWaiter : ()V
    //   209: aload #7
    //   211: invokevirtual unlock : ()V
    //   214: goto -> 227
    //   217: astore #16
    //   219: aload #7
    //   221: invokevirtual unlock : ()V
    //   224: aload #16
    //   226: athrow
    //   227: aload #15
    //   229: athrow
    // Line number table:
    //   Java source line number -> byte code offset
    //   #520	-> 0
    //   #521	-> 8
    //   #522	-> 16
    //   #524	-> 24
    //   #525	-> 30
    //   #526	-> 37
    //   #530	-> 40
    //   #532	-> 47
    //   #533	-> 53
    //   #535	-> 61
    //   #536	-> 69
    //   #539	-> 72
    //   #540	-> 79
    //   #541	-> 90
    //   #545	-> 92
    //   #546	-> 95
    //   #548	-> 98
    //   #549	-> 99
    //   #552	-> 107
    //   #550	-> 128
    //   #554	-> 141
    //   #555	-> 144
    //   #557	-> 148
    //   #560	-> 153
    //   #561	-> 163
    //   #564	-> 167
    //   #565	-> 172
    //   #564	-> 175
    //   #565	-> 182
    //   #555	-> 185
    //   #557	-> 188
    //   #560	-> 195
    //   #561	-> 205
    //   #564	-> 209
    //   #565	-> 214
    //   #564	-> 217
    //   #565	-> 224
    //   #567	-> 227
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   0	230	0	this	Lcom/google/common/util/concurrent/Monitor;
    //   0	230	1	guard	Lcom/google/common/util/concurrent/Monitor$Guard;
    //   0	230	2	time	J
    //   0	230	4	unit	Ljava/util/concurrent/TimeUnit;
    //   8	222	5	timeoutNanos	J
    //   30	200	7	lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   37	193	8	reentrant	Z
    //   40	190	9	startTime	J
    //   95	135	11	satisfied	Z
    //   98	132	12	threw	Z
    // Exception table:
    //   from	to	target	type
    //   98	148	188	finally
    //   153	167	175	finally
    //   175	177	175	finally
    //   188	190	188	finally
    //   195	209	217	finally
    //   217	219	217	finally
  }
  
  public void enterWhenUninterruptibly(Guard guard) {
    if (guard.monitor != this)
      throw new IllegalMonitorStateException(); 
    ReentrantLock lock = this.lock;
    boolean signalBeforeWaiting = lock.isHeldByCurrentThread();
    lock.lock();
    boolean satisfied = false;
    try {
      if (!guard.isSatisfied())
        awaitUninterruptibly(guard, signalBeforeWaiting); 
      satisfied = true;
    } finally {
      if (!satisfied)
        leave(); 
    } 
  }
  
  public boolean enterWhenUninterruptibly(Guard guard, Duration time) {
    return enterWhenUninterruptibly(guard, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
  }
  
  public boolean enterWhenUninterruptibly(Guard guard, long time, TimeUnit unit) {
    long timeoutNanos = toSafeNanos(time, unit);
    if (guard.monitor != this)
      throw new IllegalMonitorStateException(); 
    ReentrantLock lock = this.lock;
    long startTime = 0L;
    boolean signalBeforeWaiting = lock.isHeldByCurrentThread();
    boolean interrupted = Thread.interrupted();
    try {
      if (this.fair || !lock.tryLock()) {
        startTime = initNanoTime(timeoutNanos);
        long remainingNanos = timeoutNanos;
        while (true) {
          try {
            if (lock.tryLock(remainingNanos, TimeUnit.NANOSECONDS))
              break; 
            return false;
          } catch (InterruptedException interrupt) {
            interrupted = true;
            remainingNanos = remainingNanos(startTime, timeoutNanos);
          } 
        } 
      } 
      boolean satisfied = false;
      while (true) {
        try {
          if (guard.isSatisfied()) {
            satisfied = true;
          } else {
            long remainingNanos;
            if (startTime == 0L) {
              startTime = initNanoTime(timeoutNanos);
              remainingNanos = timeoutNanos;
            } else {
              remainingNanos = remainingNanos(startTime, timeoutNanos);
            } 
            satisfied = awaitNanos(guard, remainingNanos, signalBeforeWaiting);
          } 
          return satisfied;
        } catch (InterruptedException interrupt) {
          interrupted = true;
        } finally {
          if (!satisfied)
            lock.unlock(); 
        } 
      } 
    } finally {
      if (interrupted)
        Thread.currentThread().interrupt(); 
    } 
  }
  
  public boolean enterIf(Guard guard) {
    if (guard.monitor != this)
      throw new IllegalMonitorStateException(); 
    ReentrantLock lock = this.lock;
    lock.lock();
    boolean satisfied = false;
    try {
      return satisfied = guard.isSatisfied();
    } finally {
      if (!satisfied)
        lock.unlock(); 
    } 
  }
  
  public boolean enterIf(Guard guard, Duration time) {
    return enterIf(guard, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
  }
  
  public boolean enterIf(Guard guard, long time, TimeUnit unit) {
    if (guard.monitor != this)
      throw new IllegalMonitorStateException(); 
    if (!enter(time, unit))
      return false; 
    boolean satisfied = false;
    try {
      return satisfied = guard.isSatisfied();
    } finally {
      if (!satisfied)
        this.lock.unlock(); 
    } 
  }
  
  public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
    if (guard.monitor != this)
      throw new IllegalMonitorStateException(); 
    ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    boolean satisfied = false;
    try {
      return satisfied = guard.isSatisfied();
    } finally {
      if (!satisfied)
        lock.unlock(); 
    } 
  }
  
  public boolean enterIfInterruptibly(Guard guard, Duration time) throws InterruptedException {
    return enterIfInterruptibly(guard, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
  }
  
  public boolean enterIfInterruptibly(Guard guard, long time, TimeUnit unit) throws InterruptedException {
    if (guard.monitor != this)
      throw new IllegalMonitorStateException(); 
    ReentrantLock lock = this.lock;
    if (!lock.tryLock(time, unit))
      return false; 
    boolean satisfied = false;
    try {
      return satisfied = guard.isSatisfied();
    } finally {
      if (!satisfied)
        lock.unlock(); 
    } 
  }
  
  public boolean tryEnterIf(Guard guard) {
    if (guard.monitor != this)
      throw new IllegalMonitorStateException(); 
    ReentrantLock lock = this.lock;
    if (!lock.tryLock())
      return false; 
    boolean satisfied = false;
    try {
      return satisfied = guard.isSatisfied();
    } finally {
      if (!satisfied)
        lock.unlock(); 
    } 
  }
  
  public void waitFor(Guard guard) throws InterruptedException {
    if ((((guard.monitor == this) ? 1 : 0) & this.lock.isHeldByCurrentThread()) == 0)
      throw new IllegalMonitorStateException(); 
    if (!guard.isSatisfied())
      await(guard, true); 
  }
  
  public boolean waitFor(Guard guard, Duration time) throws InterruptedException {
    return waitFor(guard, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
  }
  
  public boolean waitFor(Guard guard, long time, TimeUnit unit) throws InterruptedException {
    long timeoutNanos = toSafeNanos(time, unit);
    if ((((guard.monitor == this) ? 1 : 0) & this.lock.isHeldByCurrentThread()) == 0)
      throw new IllegalMonitorStateException(); 
    if (guard.isSatisfied())
      return true; 
    if (Thread.interrupted())
      throw new InterruptedException(); 
    return awaitNanos(guard, timeoutNanos, true);
  }
  
  public void waitForUninterruptibly(Guard guard) {
    if ((((guard.monitor == this) ? 1 : 0) & this.lock.isHeldByCurrentThread()) == 0)
      throw new IllegalMonitorStateException(); 
    if (!guard.isSatisfied())
      awaitUninterruptibly(guard, true); 
  }
  
  public boolean waitForUninterruptibly(Guard guard, Duration time) {
    return waitForUninterruptibly(guard, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
  }
  
  public boolean waitForUninterruptibly(Guard guard, long time, TimeUnit unit) {
    long timeoutNanos = toSafeNanos(time, unit);
    if ((((guard.monitor == this) ? 1 : 0) & this.lock.isHeldByCurrentThread()) == 0)
      throw new IllegalMonitorStateException(); 
    if (guard.isSatisfied())
      return true; 
    boolean signalBeforeWaiting = true;
    long startTime = initNanoTime(timeoutNanos);
    boolean interrupted = Thread.interrupted();
    try {
      long remainingNanos = timeoutNanos;
      while (true) {
        try {
          return awaitNanos(guard, remainingNanos, signalBeforeWaiting);
        } catch (InterruptedException interrupt) {
          interrupted = true;
          if (guard.isSatisfied())
            return true; 
          signalBeforeWaiting = false;
          remainingNanos = remainingNanos(startTime, timeoutNanos);
        } 
      } 
    } finally {
      if (interrupted)
        Thread.currentThread().interrupt(); 
    } 
  }
  
  public void leave() {
    ReentrantLock lock = this.lock;
    try {
      if (lock.getHoldCount() == 1)
        signalNextWaiter(); 
    } finally {
      lock.unlock();
    } 
  }
  
  public boolean isFair() {
    return this.fair;
  }
  
  public boolean isOccupied() {
    return this.lock.isLocked();
  }
  
  public boolean isOccupiedByCurrentThread() {
    return this.lock.isHeldByCurrentThread();
  }
  
  public int getOccupiedDepth() {
    return this.lock.getHoldCount();
  }
  
  public int getQueueLength() {
    return this.lock.getQueueLength();
  }
  
  public boolean hasQueuedThreads() {
    return this.lock.hasQueuedThreads();
  }
  
  public boolean hasQueuedThread(Thread thread) {
    return this.lock.hasQueuedThread(thread);
  }
  
  public boolean hasWaiters(Guard guard) {
    return (getWaitQueueLength(guard) > 0);
  }
  
  public int getWaitQueueLength(Guard guard) {
    if (guard.monitor != this)
      throw new IllegalMonitorStateException(); 
    this.lock.lock();
    try {
      return guard.waiterCount;
    } finally {
      this.lock.unlock();
    } 
  }
  
  private static long toSafeNanos(long time, TimeUnit unit) {
    long timeoutNanos = unit.toNanos(time);
    return Longs.constrainToRange(timeoutNanos, 0L, 6917529027641081853L);
  }
  
  private static long initNanoTime(long timeoutNanos) {
    if (timeoutNanos <= 0L)
      return 0L; 
    long startTime = System.nanoTime();
    return (startTime == 0L) ? 1L : startTime;
  }
  
  private static long remainingNanos(long startTime, long timeoutNanos) {
    return (timeoutNanos <= 0L) ? 0L : (timeoutNanos - System.nanoTime() - startTime);
  }
  
  @GuardedBy("lock")
  private void signalNextWaiter() {
    for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
      if (isSatisfied(guard)) {
        guard.condition.signal();
        break;
      } 
    } 
  }
  
  @GuardedBy("lock")
  private boolean isSatisfied(Guard guard) {
    try {
      return guard.isSatisfied();
    } catch (Throwable throwable) {
      signalAllWaiters();
      throw throwable;
    } 
  }
  
  @GuardedBy("lock")
  private void signalAllWaiters() {
    for (Guard guard = this.activeGuards; guard != null; guard = guard.next)
      guard.condition.signalAll(); 
  }
  
  @GuardedBy("lock")
  private void beginWaitingFor(Guard guard) {
    int waiters = guard.waiterCount++;
    if (waiters == 0) {
      guard.next = this.activeGuards;
      this.activeGuards = guard;
    } 
  }
  
  @GuardedBy("lock")
  private void endWaitingFor(Guard guard) {
    int waiters = --guard.waiterCount;
    if (waiters == 0)
      for (Guard p = this.activeGuards, pred = null;; pred = p, p = p.next) {
        if (p == guard) {
          if (pred == null) {
            this.activeGuards = p.next;
          } else {
            pred.next = p.next;
          } 
          p.next = null;
          break;
        } 
      }  
  }
  
  @GuardedBy("lock")
  private void await(Guard guard, boolean signalBeforeWaiting) throws InterruptedException {
    if (signalBeforeWaiting)
      signalNextWaiter(); 
    beginWaitingFor(guard);
    try {
      do {
        guard.condition.await();
      } while (!guard.isSatisfied());
    } finally {
      endWaitingFor(guard);
    } 
  }
  
  @GuardedBy("lock")
  private void awaitUninterruptibly(Guard guard, boolean signalBeforeWaiting) {
    if (signalBeforeWaiting)
      signalNextWaiter(); 
    beginWaitingFor(guard);
    try {
      do {
        guard.condition.awaitUninterruptibly();
      } while (!guard.isSatisfied());
    } finally {
      endWaitingFor(guard);
    } 
  }
  
  @GuardedBy("lock")
  private boolean awaitNanos(Guard guard, long nanos, boolean signalBeforeWaiting) throws InterruptedException {
    boolean firstTime = true;
    try {
      while (true) {
        if (nanos <= 0L)
          return false; 
        if (firstTime) {
          if (signalBeforeWaiting)
            signalNextWaiter(); 
          beginWaitingFor(guard);
          firstTime = false;
        } 
        nanos = guard.condition.awaitNanos(nanos);
        if (guard.isSatisfied())
          return true; 
      } 
    } finally {
      if (!firstTime)
        endWaitingFor(guard); 
    } 
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\Monitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */