package com.google.common.eventbus;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
public class DeadEvent {
  private final Object source;
  
  private final Object event;
  
  public DeadEvent(Object source, Object event) {
    this.source = Preconditions.checkNotNull(source);
    this.event = Preconditions.checkNotNull(event);
  }
  
  public Object getSource() {
    return this.source;
  }
  
  public Object getEvent() {
    return this.event;
  }
  
  public String toString() {
    return MoreObjects.toStringHelper(this).add("source", this.source).add("event", this.event).toString();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\eventbus\DeadEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */