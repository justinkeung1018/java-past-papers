package datastructures;

import java.util.List;
import java.util.Optional;

public interface MemoryI<K, V> {

  Optional<V> read(K key);

  boolean write(K key, V value);

  int size();

  List<K> allMemKeysInOrder();

  int getCapacity();
}
