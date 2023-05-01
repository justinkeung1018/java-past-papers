package utils;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeQueueFine<E> extends SafeQueue<E> {

  private static class LockableNode<E> {
    private final E element;
    private LockableNode<E> next;
    private LockableNode<E> prev;
    private final Lock lock;

    public LockableNode(E element) {
      this.element = element;
      this.next = null;
      this.prev = null;
      lock = new ReentrantLock();
    }

    public void lock() {
      lock.lock();
    }

    public void unlock() {
      lock.unlock();
    }
  }

  private final LockableNode<E> head;
  private final LockableNode<E> tail;
  private final AtomicInteger size;
  private final ReentrantLock lock;

  public SafeQueueFine() {
    head = new LockableNode<>(null);
    tail = new LockableNode<>(null);
    head.next = tail;
    tail.prev = head;
    size = new AtomicInteger();
    lock = new ReentrantLock();
  }

  @Override
  public void push(E element) {
    LockableNode<E> headNext = head.next;
    LockableNode<E> node = new LockableNode<>(element);
    head.lock();
    headNext.lock();
    node.next = head.next;
    node.prev = head;
    node.next.prev = node;
    head.next = node;
    size.incrementAndGet();
    head.unlock();
    headNext.unlock();
  }

  @Override
  public Optional<E> pop() {
    LockableNode<E> tailPrev = tail.prev;
    tailPrev.lock();
    tail.lock();
    if (tailPrev == head) {
      tail.unlock();
      tailPrev.unlock();
      return Optional.empty();
    }
    tailPrev.prev.next = tail;
    tail.prev = tailPrev.prev;
    size.decrementAndGet();
    tailPrev.unlock();
    tail.unlock();
    return Optional.of(tailPrev.element);
  }

  @Override
  public int size() {
    return size.get();
  }
}
