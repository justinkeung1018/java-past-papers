package cells;

import java.util.Deque;
import java.util.LinkedList;

public class BackedUpMutableCell<T> extends MutableCell<T> implements BackedUpCell<T> {
  private final Deque<T> backups;
  private final int limit;
  private final boolean isBounded;

  public BackedUpMutableCell() {
    super();
    backups = new LinkedList<>();
    limit = -1;
    isBounded = false;
  }

  public BackedUpMutableCell(int limit) {
    super();
    if (limit < 0) {
      throw new IllegalArgumentException("Limit must be non-negative");
    }
    this.backups = new LinkedList<>();
    this.limit = limit;
    this.isBounded = true;
  }

  @Override
  public boolean hasBackup() {
    return !backups.isEmpty();
  }

  @Override
  public void revertToPrevious() {
    if (!hasBackup()) {
      throw new UnsupportedOperationException("No backups available");
    }
    super.set(backups.pollLast());
  }

  @Override
  public void set(T value) {
    if (isSet()) {
      backups.offerLast(get());
      if (isBounded && backups.size() > limit) {
        backups.pollFirst();
      }
    }
    super.set(value);
  }
}
