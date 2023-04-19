import java.util.Iterator;

/**
 * You must implement the <code>add</code> and <code>PQRebuild</code> methods.
 */

public class PriorityQueue<T extends Comparable<T>> implements
    PriorityQueueInterface<T> {

  private static final int max_size = 512;
  private T[] items;             //a priority queue of elements T
  private int size;              // number of elements in the priority queue.


  @SuppressWarnings({"unchecked", "rawtypes"})
  public PriorityQueue() {
    items = (T[]) new Comparable[max_size];
    size = 0;
  }

  /**
   * Returns true if the priority queue is empty. False otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the size of the priority queue.
   */
  public int getSize() {
    return size;
  }

  /**
   * Returns the element with highest priority or null if the priority. queue is empty. The priority
   * queue is left unchanged
   */
  public T peek() {
    T root = null;
    if (!isEmpty()) {
      root = items[0];
    }
    return root;
  }

  /**
   * <strong>Implement this method for Question 2</strong>
   * Adds a new entry to the priority queue according to the priority value.
   *
   * @param newEntry the new element to add to the priority queue
   * @throws PriorityQueueException if the priority queue is full
   */
  public void add(T newEntry) throws PriorityQueueException {
    if (size == max_size) {
      throw new PriorityQueueException("Priority queue is full");
    }
    items[size] = newEntry;
    swim(size);
    size++;
  }

  /**
   * "Swims" (or percolates up) the item at the given index
   * such that the min heap property is attained.
   *
   * @param index The index of the item to swim upwards.
   */
  private void swim(int index) {
    int parent = (index - 1) / 2;
    if (items[index].compareTo(items[parent]) < 0) {
      swap(index, parent);
      swim(parent);
    }
  }

  /**
   * Swaps the two items at the specified indices.
   *
   * @param index1 The first index.
   * @param index2 The second index.
   */
  private void swap(int index1, int index2) {
    T temp = items[index1];
    items[index1] = items[index2];
    items[index2] = temp;
  }

  /**
   * Removes the element with highest priority.
   */
  public void remove() {
    if (!isEmpty()) {
      items[0] = items[size - 1];
      size--;
      priorityQueueRebuild(0);
    }
  }

  /**
   * <strong>Implement this method for Question 2.</strong>
   */
  private void priorityQueueRebuild(int root) {
    int left = 2 * root + 1;
    int right = 2 * root + 2;
    if (items[left] == null) {
      // This implies items[right] == null as well since min heaps are left complete,
      // which implies root has no children
      return;
    }
    int smaller = (items[right] == null || items[left].compareTo(items[right]) < 0) ? left : right;
    if (items[root].compareTo(items[smaller]) > 0) {
      swap(root, smaller);
      priorityQueueRebuild(smaller);
    }
  }

  public Iterator<Object> iterator() {
    return new PriorityQueueIterator<Object>();
  }

  /**
   * Returns a priority queue that is a clone of the current priority queue.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public PriorityQueue<T> clone() {
    PriorityQueue<T> clone = new PriorityQueue<T>();
    clone.size = this.size;
    clone.items = (T[]) new Comparable[max_size];
    System.arraycopy(this.items, 0, clone.items, 0, size);
    return clone;
  }

  private class PriorityQueueIterator<T> implements Iterator<Object> {

    private int position = 0;

    public boolean hasNext() {
      return position < size;
    }

    public Object next() {
      Object temp = items[position];
      position++;
      return temp;
    }

    public void remove() {
      throw new IllegalStateException();
    }

  }

}
