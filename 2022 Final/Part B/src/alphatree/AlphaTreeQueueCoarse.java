package alphatree;

import java.util.concurrent.atomic.AtomicInteger;

/*
My coarse-grained implementation uses synchronized methods to ensure
no two threads can access the queue at the same time.
 */
public class AlphaTreeQueueCoarse extends AlphaTreeQueue {
  private final AtomicInteger size;

  public AlphaTreeQueueCoarse() {
    super();
    size = new AtomicInteger();
  }

  public AlphaTreeQueueCoarse(AlphaFreq freqs) {
    this();
    addAll(freqs);
  }

  @Override
  public synchronized void addAll(AlphaFreq freqs) {
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

  @Override
  public synchronized void add(AlphaTree t) {
    if (t == null || t.isEmpty()) {
      return;
    }
    minHeap[size.get()] = t;
    for (int curr = size.get(); curr > 0; curr--) {
      if (minHeap[curr].freq() > minHeap[curr - 1].freq()) {
        break;
      }
      if (minHeap[curr].freq() == minHeap[curr - 1].freq()
          && minHeap[curr].chars().size() > minHeap[curr - 1].chars().size()) {
        break;
      }
      swap(curr, curr - 1);
    }
    size.incrementAndGet();
  }

  @Override
  public synchronized AlphaTree peek() {
    return minHeap[0];
  }

  @Override
  public synchronized AlphaTree poll() {
    if (size.get() == 0) {
      return null;
    }
    AlphaTree min = minHeap[0];
    for (int i = 1; i < size.get(); i++) {
      minHeap[i - 1] = minHeap[i];
      minHeap[i] = null;
    }
    size.decrementAndGet();
    return min;
  }

  @Override
  public synchronized int size() {
    return size.get();
  }
}
