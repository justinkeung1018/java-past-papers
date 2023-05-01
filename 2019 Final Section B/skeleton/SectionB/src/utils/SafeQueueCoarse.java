package utils;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class SafeQueueCoarse<E> extends SafeQueue<E> {

  private static class Node<E> {
    private final E element;
    private Node<E> next;
    private Node<E> prev;

    public Node(E element) {
      this.element = element;
      this.next = null;
      this.prev = null;
    }
  }

  private final Node<E> head;
  private final Node<E> tail;
  private final AtomicInteger size;

  public SafeQueueCoarse() {
    head = new Node<>(null);
    tail = new Node<>(null);
    head.next = tail;
    tail.prev = head;
    size = new AtomicInteger();
  }

  @Override
  public synchronized void push(E element) {
    Node<E> node = new Node<>(element);
    Node<E> after = head.next;
    node.next = after;
    node.prev = head;
    after.prev = node;
    head.next = node;
    size.incrementAndGet();
  }

  @Override
  public synchronized Optional<E> pop() {
    if (size.get() == 0) {
      return Optional.empty();
    }
    Node<E> before = tail.prev.prev;
    Node<E> node = tail.prev;
    before.next = tail;
    tail.prev = before;
    size.decrementAndGet();
    return Optional.of(node.element);
  }

  @Override
  public int size() {
    return size.get();
  }
}
