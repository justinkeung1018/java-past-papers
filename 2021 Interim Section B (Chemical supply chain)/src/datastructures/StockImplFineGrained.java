package datastructures;

import domain.Player;
import domain.producttypes.ExchangeableChemical;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Does not work
 * Does not work
 * Does not work
 */
public class StockImplFineGrained<E extends ExchangeableChemical> implements Stock<E> {
  private static class Node<E> {
    private final LIFO<E> stack;
    private final int key;
    private final Lock lock;
    private Node<E> left;
    private Node<E> right;

    public Node(int id) {
      stack = new LIFOImpl<>();
      key = id;
      lock = new ReentrantLock();
      left = null;
      right = null;
    }

    public void lock() {
      lock.lock();
    }

    public void unlock() {
      lock.unlock();
    }
  }

  private record Pair<K, V>(K key, V value) {
  }

  private Node<E> root;
  private final AtomicInteger size;
  private final Lock rootLock;

  public StockImplFineGrained() {
    root = null;
    size = new AtomicInteger(0);
    rootLock = new ReentrantLock();
  }

  @Override
  public void push(E element, Player player) {
    rootLock.lock();
    Node<E> parent = null;
    Node<E> curr = root;
    if (root == null) {
      root = new Node<>(player.id);
      root.stack.push(element);
      size.incrementAndGet();
      rootLock.unlock();
      return;
    }
    curr.lock();
    rootLock.unlock();
    while (curr != null) {
      if (curr.key == player.id) {
        curr.stack.push(element);
        size.incrementAndGet();
        curr.unlock();
        return;
      }
      parent = curr;
      if (curr.key < player.id) {
        curr = curr.right;
      } else {
        curr = curr.left;
      }
      if (curr != null) {
        curr.lock();
      }
      parent.unlock();
    }
    curr = new Node<>(player.id);
    curr.stack.push(element);
    if (curr.key < parent.key) {
      parent.left = curr;
    } else {
      parent.right = curr;
    }
    size.incrementAndGet();
  }

  @Override
  public Optional<E> pop() {
    rootLock.lock();
    Pair<Node<E>, Node<E>> max = findMax(root);
    rootLock.unlock();
    Node<E> parent = max.key;
    Node<E> curr = max.value;
    if (curr == null) {
      if (parent != null) {
        parent.unlock();
      }
      return Optional.empty();
    }
    Optional<E> element = curr.stack.pop();
//    assert element.isPresent();
    if (curr.stack.size() == 0) {
      if (curr.left == null) {
        if (parent == null) {
          // Curr is the root node and it has no children
          root = null;
        } else {
          replaceChild(parent, curr, null);
        }
      } else {
        Pair<Node<E>, Node<E>> maxInLeftSubtree = findMax(curr.left);
        Node<E> maxParent = maxInLeftSubtree.key;
        Node<E> maxCurr = maxInLeftSubtree.value;
        replaceChild(maxParent, maxCurr, null);
        if (parent == null) {
          // Curr is the root node and it has a left child
          root = maxCurr;
        } else {
          replaceChild(parent, curr, maxCurr);
        }
        maxCurr.unlock();
        if (maxParent != null) {
          maxParent.unlock();
        }
      }
    }
    size.decrementAndGet();
    if (parent != null) {
      parent.unlock();
    }
    curr.unlock();
    return element;
  }

  private Pair<Node<E>, Node<E>> findMax(Node<E> root) {
    if (root == null) {
      return new Pair<>(null, null);
    }
    Node<E> parent = null;
    Node<E> curr = root;
    curr.lock();
    while (curr.right != null) {
      if (parent != null) {
        parent.unlock();
      }
      parent = curr;
      curr = curr.right;
      curr.lock();
    }
    return new Pair<>(parent, curr);
  }

  private void replaceChild(Node<E> parent, Node<E> child, Node<E> replacement) {
    if (parent == null) {
      return;
    }
    if (parent.left == child) {
      parent.left = replacement;
    } else if (parent.right == child) {
      parent.right = replacement;
    } else {
      throw new IllegalArgumentException("Child is not a valid child");
    }
    if (replacement == null) {
      return;
    } else if (replacement.key < child.key) {
      child.left = replacement;
    } else {
      child.right = replacement;
    }
  }

  @Override
  public int size() {
    return size.get();
  }
}
