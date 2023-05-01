package datastructures;

import domain.Player;
import domain.producttypes.ExchangeableChemical;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Does not work
 * Does not work
 * Does not work
 */
public class StockImplCoarseGrained<E extends ExchangeableChemical> implements Stock<E> {
  private static class Node<E> {
    private final LIFO<E> stack;
    private final int key;
    private Node<E> left;
    private Node<E> right;

    public Node(int id) {
      stack = new LIFOImpl<>();
      key = id;
      left = null;
      right = null;
    }
  }

  private record Pair<K, V>(K key, V value) {
  }

  private Node<E> root;
  private final AtomicInteger size;
  private final AtomicInteger maxId;

  public StockImplCoarseGrained() {
    root = new Node<>(Integer.MIN_VALUE);
    size = new AtomicInteger(0);
    maxId = new AtomicInteger(0);
  }

  @Override
  public synchronized void push(E element, Player player) {
    if (root == null) {
      root = new Node<>(player.id);
      root.stack.push(element);
      size.incrementAndGet();
      return;
    }
    Node<E> parent = null;
    Node<E> curr = root;
    while (curr != null) {
      if (curr.key == player.id) {
        curr.stack.push(element);
        size.incrementAndGet();
        return;
      }
      parent = curr;
      if (curr.key < player.id) {
        curr = curr.right;
      } else {
        curr = curr.left;
      }
    }
    curr = new Node<>(player.id);
    curr.stack.push(element);
    if (curr.key < parent.key) {
      parent.left = curr;
    } else {
      parent.right = curr;
    }
    size.incrementAndGet();
    maxId.compareAndSet(maxId.get(), Math.max(maxId.get(), player.id));
  }

  @Override
  public synchronized Optional<E> pop() {
    if (root == null) {
      return Optional.empty();
    }
    Pair<Node<E>, Node<E>> max = findMax(root);
    Node<E> parent = max.key;
    Node<E> curr = max.value;
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
      }
    }
    size.decrementAndGet();
    return element;
  }

  private Pair<Node<E>, Node<E>> findMax(Node<E> root) {
    if (root == null) {
      return new Pair<>(null, null);
    }
    Node<E> parent = null;
    Node<E> curr = root;
    while (curr.right != null) {
      parent = curr;
      curr = curr.right;
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
  public synchronized int size() {
    return size.get();
  }
}
