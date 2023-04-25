package alphatree;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
My fine-grained implementation uses hand-over-hand locking in a linked list.

When adding a node to the linked list, we lock the previous and current node
to avoid the race condition when another thread tries to add in between the
two locked nodes, or poll one of the locked nodes.

When polling a node, we lock the head and the node to be polled to avoid the
race condition when another thread tries to add directly before or after the
node, or poll at the same time.
 */
public class AlphaTreeQueueFine extends AlphaTreeQueue {
  private static class Node {
    private final AlphaTree t;
    private final Lock lock;
    private Node next;
    private final int key;

    public Node(int key) {
      this.key = key;
      t = null;
      lock = new ReentrantLock();
    }

    public Node(AlphaTree t) {
      this.t = t;
      key = t.freq() * AlphaFreq.NUM_CHARS + t.chars().size();
      lock = new ReentrantLock();
    }

    public void lock() {
      lock.lock();
    }

    public void unlock() {
      lock.unlock();
    }
  }

  private final Node head;
  private final Node tail;
  private final AtomicInteger size;

  public AlphaTreeQueueFine() {
    head = new Node(Integer.MIN_VALUE);
    tail = new Node(Integer.MAX_VALUE);
    head.next = tail;
    size = new AtomicInteger();
  }

  public AlphaTreeQueueFine(AlphaFreq freqs) {
    this();
    addAll(freqs);
  }

  @Override
  public void addAll(AlphaFreq freqs) {
    if (freqs == null) {
      return;
    }
    for (int i = AlphaFreq.NUM_CHARS - 1; i >= 0; i--) {
      char c = (char) (i + 'a');
      if (freqs.get(c) == 0) {
        continue;
      }
      add(new AlphaTree(c, freqs.get(c)));
    }
  }

  @Override
  public void add(AlphaTree t) {
    if (t == null || t.isEmpty()) {
      return;
    }
    Node node = new Node(t);
    Node prev = head;
    Node curr = head.next;
    prev.lock();
    curr.lock();
    while (node.key > curr.key) {
      prev.unlock();
      prev = curr;
      curr = curr.next;
      curr.lock();
    }
    prev.next = node;
    node.next = curr;
    prev.unlock();
    curr.unlock();
    size.incrementAndGet();
  }

  @Override
  public AlphaTree peek() {
    if (size.get() == 0) {
      return null;
    }
    return head.next.t;
  }

  @Override
  public AlphaTree poll() {
    if (size.get() == 0) {
      return null;
    }
    Node min = head.next;
    head.lock();
    min.lock();
    head.next = head.next.next;
    min.unlock();
    head.unlock();
    size.decrementAndGet();
    return min.t;
  }

  @Override
  public int size() {
    return size.get();
  }
}
