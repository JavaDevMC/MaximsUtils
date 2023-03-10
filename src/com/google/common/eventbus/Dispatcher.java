package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@ElementTypesAreNonnullByDefault
abstract class Dispatcher {
  static Dispatcher perThreadDispatchQueue() {
    return new PerThreadQueuedDispatcher();
  }
  
  static Dispatcher legacyAsync() {
    return new LegacyAsyncDispatcher();
  }
  
  static Dispatcher immediate() {
    return ImmediateDispatcher.INSTANCE;
  }
  
  abstract void dispatch(Object paramObject, Iterator<Subscriber> paramIterator);
  
  private static final class PerThreadQueuedDispatcher extends Dispatcher {
    private final ThreadLocal<Queue<Event>> queue = new ThreadLocal<Queue<Event>>(this) {
        protected Queue<Event> initialValue() {
          return Queues.newArrayDeque();
        }
      };
    
    private final ThreadLocal<Boolean> dispatching = new ThreadLocal<Boolean>(this) {
        protected Boolean initialValue() {
          return Boolean.valueOf(false);
        }
      };
    
    void dispatch(Object event, Iterator<Subscriber> subscribers) {
      Preconditions.checkNotNull(event);
      Preconditions.checkNotNull(subscribers);
      Queue<Event> queueForThread = this.queue.get();
      queueForThread.offer(new Event(event, subscribers));
      if (!((Boolean)this.dispatching.get()).booleanValue()) {
        this.dispatching.set(Boolean.valueOf(true));
        try {
          Event nextEvent;
          while ((nextEvent = queueForThread.poll()) != null) {
            while (nextEvent.subscribers.hasNext())
              ((Subscriber)nextEvent.subscribers.next()).dispatchEvent(nextEvent.event); 
          } 
        } finally {
          this.dispatching.remove();
          this.queue.remove();
        } 
      } 
    }
    
    private PerThreadQueuedDispatcher() {}
    
    private static final class Event {
      private final Object event;
      
      private final Iterator<Subscriber> subscribers;
      
      private Event(Object event, Iterator<Subscriber> subscribers) {
        this.event = event;
        this.subscribers = subscribers;
      }
    }
  }
  
  private static final class LegacyAsyncDispatcher extends Dispatcher {
    private final ConcurrentLinkedQueue<EventWithSubscriber> queue = Queues.newConcurrentLinkedQueue();
    
    void dispatch(Object event, Iterator<Subscriber> subscribers) {
      Preconditions.checkNotNull(event);
      while (subscribers.hasNext())
        this.queue.add(new EventWithSubscriber(event, subscribers.next())); 
      EventWithSubscriber e;
      while ((e = this.queue.poll()) != null)
        e.subscriber.dispatchEvent(e.event); 
    }
    
    private LegacyAsyncDispatcher() {}
    
    private static final class EventWithSubscriber {
      private final Object event;
      
      private final Subscriber subscriber;
      
      private EventWithSubscriber(Object event, Subscriber subscriber) {
        this.event = event;
        this.subscriber = subscriber;
      }
    }
  }
  
  private static final class ImmediateDispatcher extends Dispatcher {
    private static final ImmediateDispatcher INSTANCE = new ImmediateDispatcher();
    
    void dispatch(Object event, Iterator<Subscriber> subscribers) {
      Preconditions.checkNotNull(event);
      while (subscribers.hasNext())
        ((Subscriber)subscribers.next()).dispatchEvent(event); 
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\eventbus\Dispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */