package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class ConcurrentHashMultiset<E> extends AbstractMultiset<E> implements Serializable {
  private final transient ConcurrentMap<E, AtomicInteger> countMap;
  
  private static final long serialVersionUID = 1L;
  
  private static class FieldSettersHolder {
    static final Serialization.FieldSetter<ConcurrentHashMultiset> COUNT_MAP_FIELD_SETTER = Serialization.getFieldSetter(ConcurrentHashMultiset.class, "countMap");
  }
  
  public static <E> ConcurrentHashMultiset<E> create() {
    return new ConcurrentHashMultiset<>(new ConcurrentHashMap<>());
  }
  
  public static <E> ConcurrentHashMultiset<E> create(Iterable<? extends E> elements) {
    ConcurrentHashMultiset<E> multiset = create();
    Iterables.addAll(multiset, elements);
    return multiset;
  }
  
  @Beta
  public static <E> ConcurrentHashMultiset<E> create(ConcurrentMap<E, AtomicInteger> countMap) {
    return new ConcurrentHashMultiset<>(countMap);
  }
  
  @VisibleForTesting
  ConcurrentHashMultiset(ConcurrentMap<E, AtomicInteger> countMap) {
    Preconditions.checkArgument(countMap.isEmpty(), "the backing map (%s) must be empty", countMap);
    this.countMap = countMap;
  }
  
  public int count(@CheckForNull Object element) {
    AtomicInteger existingCounter = Maps.<AtomicInteger>safeGet(this.countMap, element);
    return (existingCounter == null) ? 0 : existingCounter.get();
  }
  
  public int size() {
    long sum = 0L;
    for (AtomicInteger value : this.countMap.values())
      sum += value.get(); 
    return Ints.saturatedCast(sum);
  }
  
  public Object[] toArray() {
    return snapshot().toArray();
  }
  
  public <T> T[] toArray(T[] array) {
    return snapshot().toArray(array);
  }
  
  private List<E> snapshot() {
    List<E> list = Lists.newArrayListWithExpectedSize(size());
    for (Entry<E> entry : (Iterable<Entry<E>>)entrySet()) {
      E element = entry.getElement();
      for (int i = entry.getCount(); i > 0; i--)
        list.add(element); 
    } 
    return list;
  }
  
  @CanIgnoreReturnValue
  public int add(E element, int occurrences) {
    AtomicInteger existingCounter, newCounter;
    Preconditions.checkNotNull(element);
    if (occurrences == 0)
      return count(element); 
    CollectPreconditions.checkPositive(occurrences, "occurrences");
    do {
      existingCounter = Maps.<AtomicInteger>safeGet(this.countMap, element);
      if (existingCounter == null) {
        existingCounter = this.countMap.putIfAbsent(element, new AtomicInteger(occurrences));
        if (existingCounter == null)
          return 0; 
      } 
      while (true) {
        int oldValue = existingCounter.get();
        if (oldValue != 0) {
          try {
            int newValue = IntMath.checkedAdd(oldValue, occurrences);
            if (existingCounter.compareAndSet(oldValue, newValue))
              return oldValue; 
          } catch (ArithmeticException overflow) {
            throw new IllegalArgumentException((new StringBuilder(65)).append("Overflow adding ").append(occurrences).append(" occurrences to a count of ").append(oldValue).toString());
          } 
          continue;
        } 
        break;
      } 
      newCounter = new AtomicInteger(occurrences);
    } while (this.countMap.putIfAbsent(element, newCounter) != null && 
      !this.countMap.replace(element, existingCounter, newCounter));
    return 0;
  }
  
  @CanIgnoreReturnValue
  public int remove(@CheckForNull Object element, int occurrences) {
    if (occurrences == 0)
      return count(element); 
    CollectPreconditions.checkPositive(occurrences, "occurrences");
    AtomicInteger existingCounter = Maps.<AtomicInteger>safeGet(this.countMap, element);
    if (existingCounter == null)
      return 0; 
    while (true) {
      int oldValue = existingCounter.get();
      if (oldValue != 0) {
        int newValue = Math.max(0, oldValue - occurrences);
        if (existingCounter.compareAndSet(oldValue, newValue)) {
          if (newValue == 0)
            this.countMap.remove(element, existingCounter); 
          return oldValue;
        } 
        continue;
      } 
      break;
    } 
    return 0;
  }
  
  @CanIgnoreReturnValue
  public boolean removeExactly(@CheckForNull Object element, int occurrences) {
    if (occurrences == 0)
      return true; 
    CollectPreconditions.checkPositive(occurrences, "occurrences");
    AtomicInteger existingCounter = Maps.<AtomicInteger>safeGet(this.countMap, element);
    if (existingCounter == null)
      return false; 
    while (true) {
      int oldValue = existingCounter.get();
      if (oldValue < occurrences)
        return false; 
      int newValue = oldValue - occurrences;
      if (existingCounter.compareAndSet(oldValue, newValue)) {
        if (newValue == 0)
          this.countMap.remove(element, existingCounter); 
        return true;
      } 
    } 
  }
  
  @CanIgnoreReturnValue
  public int setCount(E element, int count) {
    Preconditions.checkNotNull(element);
    CollectPreconditions.checkNonnegative(count, "count");
    label26: while (true) {
      AtomicInteger existingCounter = Maps.<AtomicInteger>safeGet(this.countMap, element);
      if (existingCounter == null) {
        if (count == 0)
          return 0; 
        existingCounter = this.countMap.putIfAbsent(element, new AtomicInteger(count));
        if (existingCounter == null)
          return 0; 
      } 
      while (true) {
        int oldValue = existingCounter.get();
        if (oldValue == 0) {
          if (count == 0)
            return 0; 
          AtomicInteger newCounter = new AtomicInteger(count);
          if (this.countMap.putIfAbsent(element, newCounter) == null || this.countMap
            .replace(element, existingCounter, newCounter))
            return 0; 
          continue label26;
        } 
        if (existingCounter.compareAndSet(oldValue, count)) {
          if (count == 0)
            this.countMap.remove(element, existingCounter); 
          return oldValue;
        } 
      } 
      break;
    } 
  }
  
  @CanIgnoreReturnValue
  public boolean setCount(E element, int expectedOldCount, int newCount) {
    Preconditions.checkNotNull(element);
    CollectPreconditions.checkNonnegative(expectedOldCount, "oldCount");
    CollectPreconditions.checkNonnegative(newCount, "newCount");
    AtomicInteger existingCounter = Maps.<AtomicInteger>safeGet(this.countMap, element);
    if (existingCounter == null) {
      if (expectedOldCount != 0)
        return false; 
      if (newCount == 0)
        return true; 
      return (this.countMap.putIfAbsent(element, new AtomicInteger(newCount)) == null);
    } 
    int oldValue = existingCounter.get();
    if (oldValue == expectedOldCount) {
      if (oldValue == 0) {
        if (newCount == 0) {
          this.countMap.remove(element, existingCounter);
          return true;
        } 
        AtomicInteger newCounter = new AtomicInteger(newCount);
        return (this.countMap.putIfAbsent(element, newCounter) == null || this.countMap
          .replace(element, existingCounter, newCounter));
      } 
      if (existingCounter.compareAndSet(oldValue, newCount)) {
        if (newCount == 0)
          this.countMap.remove(element, existingCounter); 
        return true;
      } 
    } 
    return false;
  }
  
  Set<E> createElementSet() {
    final Set<E> delegate = this.countMap.keySet();
    return new ForwardingSet<E>(this) {
        protected Set<E> delegate() {
          return delegate;
        }
        
        public boolean contains(@CheckForNull Object object) {
          return (object != null && Collections2.safeContains(delegate, object));
        }
        
        public boolean containsAll(Collection<?> collection) {
          return standardContainsAll(collection);
        }
        
        public boolean remove(@CheckForNull Object object) {
          return (object != null && Collections2.safeRemove(delegate, object));
        }
        
        public boolean removeAll(Collection<?> c) {
          return standardRemoveAll(c);
        }
      };
  }
  
  Iterator<E> elementIterator() {
    throw new AssertionError("should never be called");
  }
  
  @Deprecated
  public Set<Entry<E>> createEntrySet() {
    return new EntrySet();
  }
  
  int distinctElements() {
    return this.countMap.size();
  }
  
  public boolean isEmpty() {
    return this.countMap.isEmpty();
  }
  
  Iterator<Entry<E>> entryIterator() {
    final Iterator<Entry<E>> readOnlyIterator = new AbstractIterator<Entry<E>>() {
        private final Iterator<Map.Entry<E, AtomicInteger>> mapEntries = ConcurrentHashMultiset.this
          .countMap.entrySet().iterator();
        
        @CheckForNull
        protected Multiset.Entry<E> computeNext() {
          while (true) {
            if (!this.mapEntries.hasNext())
              return endOfData(); 
            Map.Entry<E, AtomicInteger> mapEntry = this.mapEntries.next();
            int count = ((AtomicInteger)mapEntry.getValue()).get();
            if (count != 0)
              return Multisets.immutableEntry(mapEntry.getKey(), count); 
          } 
        }
      };
    return new ForwardingIterator<Entry<E>>() {
        @CheckForNull
        private Multiset.Entry<E> last;
        
        protected Iterator<Entry<E>> delegate() {
          return readOnlyIterator;
        }
        
        public Entry<E> next() {
          this.last = super.next();
          return this.last;
        }
        
        public void remove() {
          Preconditions.checkState((this.last != null), "no calls to next() since the last call to remove()");
          ConcurrentHashMultiset.this.setCount(this.last.getElement(), 0);
          this.last = null;
        }
      };
  }
  
  public Iterator<E> iterator() {
    return Multisets.iteratorImpl(this);
  }
  
  public void clear() {
    this.countMap.clear();
  }
  
  private class EntrySet extends AbstractMultiset<E>.EntrySet {
    private EntrySet() {}
    
    ConcurrentHashMultiset<E> multiset() {
      return ConcurrentHashMultiset.this;
    }
    
    public Object[] toArray() {
      return snapshot().toArray();
    }
    
    public <T> T[] toArray(T[] array) {
      return snapshot().toArray(array);
    }
    
    private List<Entry<E>> snapshot() {
      List<Entry<E>> list = Lists.newArrayListWithExpectedSize(size());
      Iterators.addAll(list, iterator());
      return list;
    }
  }
  
  private void writeObject(ObjectOutputStream stream) throws IOException {
    stream.defaultWriteObject();
    stream.writeObject(this.countMap);
  }
  
  private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
    stream.defaultReadObject();
    ConcurrentMap<E, Integer> deserializedCountMap = (ConcurrentMap<E, Integer>)stream.readObject();
    FieldSettersHolder.COUNT_MAP_FIELD_SETTER.set(this, deserializedCountMap);
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\common\collect\ConcurrentHashMultiset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */