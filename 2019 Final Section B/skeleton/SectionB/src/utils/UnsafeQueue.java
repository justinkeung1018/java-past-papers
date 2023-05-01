package utils;

import java.util.Optional;

public class UnsafeQueue<E> implements Queue<E> {
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
  private int size;

  public UnsafeQueue() {
    head = new Node<>(null);
    tail = new Node<>(null);
    head.next = tail;
    tail.prev = head;
    size = 0;
  }

  @Override
  public void push(E element) {
    Node<E> node = new Node<>(element);
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
    size++;
  }

  @Override
  public Optional<E> pop() {
    if (size == 0) {
      return Optional.empty();
    }
    Node<E> toPop = tail.prev;
    toPop.prev.next = tail;
    tail.prev = toPop.prev;
    size--;
    return Optional.of(toPop.element);
  }

  @Override
  public int size() {
    return size;
  }
}
