package datastructures;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockableDoublyLinkedNode<K, V> extends DoublyLinkedNode<K, V> {
  private final Lock lock;

  public LockableDoublyLinkedNode(K key, V value) {
    super(key, value);
    lock = new ReentrantLock();
  }

  public void lock() {
    lock.lock();
  }

  public void unlock() {
    lock.unlock();
  }
}
