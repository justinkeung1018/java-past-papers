package alphatree;

public class AlphaTreeQueue {
  protected final AlphaTree[] minHeap;
  private int size;

  public AlphaTreeQueue() {
    minHeap = new AlphaTree[AlphaFreq.NUM_CHARS];
    size = 0;
  }

  public AlphaTreeQueue(AlphaFreq freqs) {
    this();
    addAll(freqs);
  }

  private void sink(int root) {
    int left = 2 * root + 1;
    if (left >= size) {
      return;
    }
    int right = 2 * root + 2;
    int smaller;
    if (right >= size) {
      smaller = left;
    } else {
      smaller = minHeap[left].freq() < minHeap[right].freq() ? left : right;
    }
    if (minHeap[root].freq() > minHeap[smaller].freq()) {
      swap(root, smaller);
      sink(smaller);
    }
  }

  private void swim(int index) {
    if (index == 0) {
      return;
    }
    int parent = (index - 1) / 2;
    if (minHeap[index].freq() <= minHeap[parent].freq()) {
      swap(parent, index);
      swim(parent);
    }
  }

  protected void swap(int index1, int index2) {
    AlphaTree temp = minHeap[index1];
    minHeap[index1] = minHeap[index2];
    minHeap[index2] = temp;
  }

  public void addAll(AlphaFreq freqs) {
    if (freqs == null) {
      return;
    }
    for (int i = AlphaFreq.NUM_CHARS - 1; i >= 0; i--) {
      char c = (char) (i + 'a');
      if (freqs.get(c) == 0) {
        continue;
      }
      add(new AlphaTree(c, freqs.get(c)));
    }
  }

  public void add(AlphaTree t) {
    if (t == null || t.isEmpty()) {
      return;
    }
    /*
    minHeap[size] = t;
    swim(size);
    size++;
    */
    minHeap[size] = t;
    for (int curr = size; curr > 0; curr--) {
      if (minHeap[curr].freq() > minHeap[curr - 1].freq()) {
        break;
      }
      if (minHeap[curr].freq() == minHeap[curr - 1].freq()
          && minHeap[curr].chars().size() > minHeap[curr - 1].chars().size()) {
        break;
      }
      swap(curr, curr - 1);
    }
    size++;
  }

  public AlphaTree peek() {
    return minHeap[0];
  }

  public AlphaTree poll() {
    if (size == 0) {
      return null;
    }
    AlphaTree min = minHeap[0];
    /*
    size--;
    minHeap[0] = minHeap[size];
    minHeap[size] = null;
    sink(0);
    */
    for (int i = 1; i < size; i++) {
      minHeap[i - 1] = minHeap[i];
      minHeap[i] = null;
    }
    size--;
    return min;
  }

  public int size() {
    return size;
  }
}
