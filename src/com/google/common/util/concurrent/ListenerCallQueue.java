package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
final class ListenerCallQueue<L> {
  private static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
  
  private final List<PerListenerQueue<L>> listeners = Collections.synchronizedList(new ArrayList<>());
  
  public void addListener(L listener, Executor executor) {
    Preconditions.checkNotNull(listener, "listener");
    Preconditions.checkNotNull(executor, "executor");
    this.listeners.add(new PerListenerQueue<>(listener, executor));
  }
  
  public void enqueue(Event<L> event) {
    enqueueHelper(event, event);
  }
  
  public void enqueue(Event<L> event, String label) {
    enqueueHelper(event, label);
  }
  
  private void enqueueHelper(Event<L> event, Object label) {
    Preconditions.checkNotNull(event, "event");
    Preconditions.checkNotNull(label, "label");
    synchronized (this.listeners) {
      for (PerListenerQueue<L> queue : this.listeners)
        queue.add(event, label); 
    } 
  }
  
  public void dispatch() {
    for (int i = 0; i < this.listeners.size(); i++)
      ((PerListenerQueue)this.listeners.get(i)).dispatch(); 
  }
  
  private static final class PerListenerQueue<L> implements Runnable {
    final L listener;
    
    final Executor executor;
    
    @GuardedBy("this")
    final Queue<Event<L>> waitQueue = Queues.newArrayDeque();
    
    @GuardedBy("this")
    final Queue<Object> labelQueue = Queues.newArrayDeque();
    
    @GuardedBy("this")
    boolean isThreadScheduled;
    
    PerListenerQueue(L listener, Executor executor) {
      this.listener = (L)Preconditions.checkNotNull(listener);
      this.executor = (Executor)Preconditions.checkNotNull(executor);
    }
    
    synchronized void add(Event<L> event, Object label) {
      this.waitQueue.add(event);
      this.labelQueue.add(label);
    }
    
    void dispatch() {
      boolean scheduleEventRunner = false;
      synchronized (this) {
        if (!this.isThreadScheduled) {
          this.isThreadScheduled = true;
          scheduleEventRunner = true;
        } 
      } 
      if (scheduleEventRunner)
        try {
          this.executor.execute(this);
        } catch (RuntimeException e) {
          synchronized (this) {
            this.isThreadScheduled = false;
          } 
          String str1 = String.valueOf(this.listener), str2 = String.valueOf(this.executor);
          ListenerCallQueue.logger.log(Level.SEVERE, (new StringBuilder(42 + String.valueOf(str1).length() + String.valueOf(str2).length())).append("Exception while running callbacks for ").append(str1).append(" on ").append(str2).toString(), e);
          throw e;
        }  
    }
    
    public void run() {
      boolean stillRunning = true;
      try {
        while (true) {
          Event<L> nextToRun;
          Object nextLabel;
          synchronized (this) {
            Preconditions.checkState(this.isThreadScheduled);
            nextToRun = this.waitQueue.poll();
            nextLabel = this.labelQueue.poll();
            if (nextToRun == null) {
              this.isThreadScheduled = false;
              stillRunning = false;
              break;
            } 
          } 
          try {
            nextToRun.call(this.listener);
          } catch (RuntimeException e) {
            String str1 = String.valueOf(this.listener), str2 = String.valueOf(nextLabel);
            ListenerCallQueue.logger.log(Level.SEVERE, (new StringBuilder(37 + String.valueOf(str1).length() + String.valueOf(str2).length())).append("Exception while executing callback: ").append(str1).append(" ").append(str2).toString(), e);
          } 
        } 
      } finally {
        if (stillRunning)
          synchronized (this) {
            this.isThreadScheduled = false;
          }  
      } 
    }
  }
  
  static interface Event<L> {
    void call(L param1L);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\commo\\util\concurrent\ListenerCallQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */