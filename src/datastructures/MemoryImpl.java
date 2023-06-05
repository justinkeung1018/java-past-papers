package datastructures;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MemoryImpl<K, V> implements MemoryI<K, V> {

  private final int capacity;
  HashMap<K, DoublyLinkedNode<K, V>> map;
  AuxiliaryCollectionImpl list;

  public MemoryImpl(int capacity) {
    map = new HashMap<>(capacity);
    list = new AuxiliaryCollectionImpl<>();

    this.capacity = capacity;
  }

  /**
   * The size of the LRU memory.
   *
   * @return Returns an int equal to the number of items currently in the LRU memory.
   */
  public int size() {
    return map.size();
  }

  /**
   * The maximum capacity this Memory can hold.
   *
   * @return Returns an int equal to the maximum number of items that can be held in this
   *         Memory.
   */
  @Override
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Reads an item with the given key from the LRU memory.
   *
   * @param key The key of the item to read.
   * @return Returns an empty Optional for invalid keys or the value of the item upon a successful
   *         read.
   */
  @Override
  public Optional<V> read(K key) {
    //TODO: Implement me
    return Optional.empty();
  }

  /**
   * Adds a new item with key and value to the LRU memory. Allows overwriting existing keys.
   *
   * @param key   The key of the item
   * @param value The item's value
   * @return Returns true when completed.
   */
  @Override
  public boolean write(K key, V value) {
    //TODO: Implement me
    return false;
  }

  /**
   * Finds the keys of all items currently in Memory.
   *
   * @return Returns a List of keys of all the items currently in Memory.
   */
  @Override
  public List<K> allMemKeysInOrder() {
    return list.allKeysInOrder();
  }

}
