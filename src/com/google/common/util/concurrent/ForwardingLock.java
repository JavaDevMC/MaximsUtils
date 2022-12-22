package com.google.common.util.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@ElementTypesAreNonnullByDefault
abstract class ForwardingLock implements Lock {
  abstract Lock delegate();
  
  public void lock() {
    delegate().lock();
  }
  
  public void lockInterruptibly() throws InterruptedException {
    delegate().lockInterruptibly();
  }
  
  public boolean tryLock() {
    return delegate().tryLock();
  }
  
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    return delegate().tryLock(time, unit);
  }
  
  public void unlock() {
    delegate().unlock();
  }
  
  public Condition newCondition() {
    return delegate().newCondition();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\ForwardingLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */