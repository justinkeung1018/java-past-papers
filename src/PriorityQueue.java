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
    // TODO: Implement this method for Question 2
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
    // TODO: Implement this method for Question 2
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
