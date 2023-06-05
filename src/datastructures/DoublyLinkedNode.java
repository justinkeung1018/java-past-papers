package datastructures;

public class DoublyLinkedNode<K, V> {

  private final K key;
  private DoublyLinkedNode<K, V> previous;
  private DoublyLinkedNode<K, V> next;
  private V value;

  public DoublyLinkedNode(K key, V value) {
    this.key = key;
    this.value = value;
    this.previous = null;
    this.next = null;
  }

  /**
   * Returns the key of the Node.
   *
   * @return The key of the Node.
   */
  public K getKey() {
    return key;
  }

  /**
   * Returns the value of the Node.
   *
   * @return The value of the Node.
   */
  public V getValue() {
    return value;
  }

  /**
   * Sets the value of the Node, overwriting any previous value.
   *
   * @param value The value to be set as the value of the Node.
   */
  public void setValue(V value) {
    this.value = value;
  }

  /**
   * Returns a DoublyLinkedNode pointed to by this Node's <i>next</i>.
   *
   * @return A DoublyLinkedNode pointed to by this Node's <i>next</i>.
   */
  public DoublyLinkedNode<K, V> getNext() {
    return next;
  }

  /**
   * Sets the <i>next</i> of this Node to the given Node.
   *
   * @param next A DoublyLinkedNode to be set as this Node's <i>next</i>.
   */
  public void setNext(DoublyLinkedNode<K, V> next) {
    this.next = next;
  }

  /**
   * Returns a DoublyLinkedNode pointed to by this Node's <i>previous</i>.
   *
   * @return A DoublyLinkedNode pointed to by this Node's <i>previous</i>.
   */
  public DoublyLinkedNode<K, V> getPrevious() {
    return previous;
  }

  /**
   * Sets the <i>previous</i> of this Node to the given Node.
   *
   * @param previous A DoublyLinkedNode to be set as this Node's <i>previous</i>.
   */
  public void setPrevious(DoublyLinkedNode<K, V> previous) {
    this.previous = previous;
  }
}

