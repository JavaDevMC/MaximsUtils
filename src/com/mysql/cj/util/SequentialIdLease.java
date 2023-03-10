package com.mysql.cj.util;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SequentialIdLease {
  private Set<Integer> sequentialIdsLease = new TreeSet<>();
  
  public int allocateSequentialId() {
    int nextSequentialId = 0;
    for (Iterator<Integer> it = this.sequentialIdsLease.iterator(); it.hasNext() && nextSequentialId + 1 == ((Integer)it.next()).intValue(); nextSequentialId++);
    this.sequentialIdsLease.add(Integer.valueOf(++nextSequentialId));
    return nextSequentialId;
  }
  
  public void releaseSequentialId(int sequentialId) {
    this.sequentialIdsLease.remove(Integer.valueOf(sequentialId));
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\c\\util\SequentialIdLease.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */