import java.util.Iterator;

/**
 * You must implement the <code>remove</code> and <code>PQRebuild</code> methods.
 */

public class PriorityQueue<T extends Comparable<T>> implements
    PriorityQueueInterface<T> {

  private T[] items;             //a minHeap of elements T
  private final static int max_size = 512;
  private int size;              // number of elements in the minHeap.


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
   * Returns the element with highest priority, or returns null if the priority.
   * queue is empty. The priority queue is left unchanged
   */
  public T peek() {
    T root = null;
    if (!isEmpty()) {
      root = items[0];
    }
    return root;
  }

  /**
   * Adds a new entry to the priority queue according to the priority value.
   *
   * @param newEntry the new element to add to the priority queue
   * @throws PQException an exception if the priority queue is full
   */
  public void add(T newEntry) throws PQException {
    if (size < max_size) {
      items[size] = newEntry;
      int place = size;
      int parent = (place - 1) / 2;
      while ((parent >= 0) && (items[place].compareTo(items[parent]) < 0)) {
        swap(place, parent);
        place = parent;
        parent = (place - 1) / 2;
      }
      size++;
    } else {
      throw new PQException("The priorityQueue is full");
    }
  }

  /**
   * <p> Implement this method for Question 1 </p>
   *
   * Removes the element with highest priority.
   */
  public void remove() {
    assert !isEmpty();
    items[0] = items[--size];
    items[size] = null;
    PQRebuild(0);
  }

  /**
   * <p> Implement this method for Question 1 </p>
   */
  private void PQRebuild(int root) {
    int place = root;
    int leftChild = 2 * place + 1;
    int rightChild = 2 * place + 2;
    while (items[leftChild] != null) {
      // Min heap is left complete, so the condition implies items[rightChild] != null
      int smallerChild = leftChild;
      if (items[rightChild] != null && items[leftChild].compareTo(items[rightChild]) > 0) {
        smallerChild = rightChild;
      }
      if (items[place].compareTo(items[smallerChild]) <= 0) {
        break;
      }
      swap(place, smallerChild);
      place = smallerChild;
      leftChild = 2 * place + 1;
      rightChild = 2 * place + 2;
    }
  }

  /*
  private void PQRebuildRecursive(int root) {
    if (items[root] == null) {
      return;
    }
    int leftChild = 2 * root + 1;
    int rightChild = 2 * root + 2;
    int smallerChild = items[leftChild].compareTo(items[rightChild]) <= 0 ? leftChild : rightChild;
    if (items[root].compareTo(items[smallerChild]) > 0) {
      swap(root, smallerChild);
      PQRebuildRecursive(smallerChild);
    }
  }
  */

  public Iterator<Object> iterator() {
    return new PQIterator<Object>();
  }

  private class PQIterator<T> implements Iterator<Object> {

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

  /**
   * Returns a priority queue that is a clone of the current priority queue.
   */
  public PriorityQueue<T> clone() {
    PriorityQueue<T> clone = new PriorityQueue<T>();
    clone.size = this.size;
    clone.items = (T[]) new Comparable[max_size];
    System.arraycopy(this.items, 0, clone.items, 0, size);
    return clone;
  }

  private void swap(int index1, int index2) {
    T temp = items[index1];
    items[index1] = items[index2];
    items[index2] = temp;
  }
}
