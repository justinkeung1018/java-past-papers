package datastructures;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class AuxiliaryCollectionImpl<K, V> implements AuxiliaryCollectionI<K, V> {

  private DoublyLinkedNode<K, V> head;
  private DoublyLinkedNode<K, V> tail;
  private int size;

  public AuxiliaryCollectionImpl() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Removes the given node from the Collection. It assumes that the input is either null or a node
   * which exists in the Collection.
   *
   * @param toRemove The DoublyLinkedNode to remove.
   * @return Returns false if the input is null or true if the removal is successful.
   */
  @Override
  public boolean remove(DoublyLinkedNode toRemove) {
    //TODO: Implement me
    return false;
  }

  /**
   * Appends the given node to the end of the Collection.
   *
   * @param toAdd The DoublyLinkedNode to be appended.
   * @return Returns false if the node cannot be added or true otherwise.
   */
  @Override
  public boolean append(DoublyLinkedNode toAdd) {
    //TODO: Implement me
    return false;
  }

  /**
   * Searches for a node in the Collection.
   *
   * @param key The key of the DoublyLinkedNode to look for.
   * @return Returns a DoublyLinkedNode which has a key equal the input key or null if such a node
   *         was not found.
   */
  @Override
  public DoublyLinkedNode<K, V> find(K key) {
    DoublyLinkedNode<K, V> curr = head;

    while (curr != null) {
      if (curr.getKey() == key) {
        return curr;
      }
      curr = curr.getNext();
    }

    return null;
  }

  /**
   * The size of the Collection.
   *
   * @return Returns an int representing the number of items currently in the Collection.
   */
  public int size() {
    return size;
  }

  /**
   * Returns the head of the Collection.
   *
   * @return A DoublyLinkedNode which is currently the head of the Collection, or null if the
   *         Collection is empty.
   */
  public DoublyLinkedNode<K, V> getHead() {
    return this.head;
  }

  /**
   * Traverses the Collection and stores the keys of all items currently in the Collection.
   *
   * @return Returns a List of keys of all the items currently in the Memory.
   */
  @Override
  public List<K> allKeysInOrder() {
    var collector = new ArrayList<K>();
    var curr = head;

    while (curr != null) {
      collector.add(curr.getKey());
      curr = curr.getNext();
    }

    return collector;
  }
}
