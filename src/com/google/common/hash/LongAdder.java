package com.google.common.hash;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@ElementTypesAreNonnullByDefault
final class LongAdder extends Striped64 implements Serializable, LongAddable {
  private static final long serialVersionUID = 7249069246863182397L;
  
  final long fn(long v, long x) {
    return v + x;
  }
  
  public void add(long x) {
    boolean uncontended = true;
    Cell[] as;
    long b, v;
    int[] hc;
    Cell a;
    int n;
    if (((as = this.cells) != null || !casBase(b = this.base, b + x)) && ((hc = threadHashCode.get()) == null || as == null || (n = as.length) < 1 || (a = as[n - 1 & hc[0]]) == null || 
      
      !(uncontended = a.cas(v = a.value, v + x))))
      retryUpdate(x, hc, uncontended); 
  }
  
  public void increment() {
    add(1L);
  }
  
  public void decrement() {
    add(-1L);
  }
  
  public long sum() {
    long sum = this.base;
    Cell[] as = this.cells;
    if (as != null) {
      int n = as.length;
      for (int i = 0; i < n; i++) {
        Cell a = as[i];
        if (a != null)
          sum += a.value; 
      } 
    } 
    return sum;
  }
  
  public void reset() {
    internalReset(0L);
  }
  
  public long sumThenReset() {
    long sum = this.base;
    Cell[] as = this.cells;
    this.base = 0L;
    if (as != null) {
      int n = as.length;
      for (int i = 0; i < n; i++) {
        Cell a = as[i];
        if (a != null) {
          sum += a.value;
          a.value = 0L;
        } 
      } 
    } 
    return sum;
  }
  
  public String toString() {
    return Long.toString(sum());
  }
  
  public long longValue() {
    return sum();
  }
  
  public int intValue() {
    return (int)sum();
  }
  
  public float floatValue() {
    return (float)sum();
  }
  
  public double doubleValue() {
    return sum();
  }
  
  private void writeObject(ObjectOutputStream s) throws IOException {
    s.defaultWriteObject();
    s.writeLong(sum());
  }
  
  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
    s.defaultReadObject();
    this.busy = 0;
    this.cells = null;
    this.base = s.readLong();
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\hash\LongAdder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */