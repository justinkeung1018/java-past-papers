package datastructures;

import java.util.List;

public interface AuxiliaryCollectionI<K, V> {

  boolean remove(DoublyLinkedNode<K, V> item);

  boolean append(DoublyLinkedNode<K, V> item);

  DoublyLinkedNode<K, V> find(K key);

  int size();

  List<K> allKeysInOrder();
}
