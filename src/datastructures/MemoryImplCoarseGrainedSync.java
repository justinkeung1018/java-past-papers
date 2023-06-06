package datastructures;

import java.util.List;
import java.util.Optional;

public class MemoryImplCoarseGrainedSync<K, V> extends MemoryImpl<K, V> {
  public MemoryImplCoarseGrainedSync(int capacity) {
    super(capacity);
  }

  @Override
  public synchronized Optional<V> read(K key) {
    return super.read(key);
  }

  @Override
  public synchronized boolean write(K key, V value) {
    return super.write(key, value);
  }

  @Override
  public synchronized int size() {
    return super.size();
  }

  @Override
  public synchronized List<K> allMemKeysInOrder() {
    return super.allMemKeysInOrder();
  }

  @Override
  public synchronized int getCapacity() {
    return super.getCapacity();
  }
}

/*
  Fine-grained implementation:

  For the implementation of the MemoryI interface, the Map data structure
  should be replaced with a thread-safe/atomic map data structure to avoid
  race conditions when accessing the map.

  For the implementation of the AuxiliaryCollectionI interface,
  the following changes to the class design are proposed:

  1. Instead of using an int for storing the size, an AtomicInteger
  should be used instead.

  2. A lock should be added to every DoublyLinkedNode, and corresponding
  public methods, e.g. lock() and unlock(), should be implemented to
  allow the AuxiliaryCollectionI implementation to lock and unlock the nodes.
  Alternatively, add a lockable subclass of DoublyLinkedNode as an inner class
  of the AuxiliaryCollectionI implementation.

  The following changes to the method implementations are proposed:

  1. append: acquire locks on tail and tail.getPrevious() before reassigning
  pointers. Release the locks before returning from the method.

  2. remove: acquire three locks in total: toRemove, toRemove.getPrevious(),
  and toRemove.getNext(). Release the locks before returning from the method.

  3. read: acquire a lock on the map object before checking if the key
  is in the map. If the key is in the map, obtain the value of the node with
  the key and store it in a variable, then release the lock. Otherwise,
  release the lock and return from the method immediately. Since the implementation
  of AuxiliaryCollectionI is thread-safe, the call to update (private method in my
  implementation of MemoryImpl) should be thread-safe as well.

  4. write: acquire a lock on the map object before accesing it. Release the lock
  right after the last access.

  This implementation should entirely respect the LRU cache policy, as only one thread
  is allowed to access the tail end of the AuxiliaryCollectionI at any given moment.
 */
