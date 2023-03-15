package cells;

import java.util.Comparator;
import java.util.Stack;

public class BackedUpCellComparator<U> implements Comparator<BackedUpCell<U>> {
  private final Comparator<U> valueComparator;

  public BackedUpCellComparator(Comparator<U> valueComparator) {
    this.valueComparator = valueComparator;
  }

  @Override
  public int compare(BackedUpCell<U> a, BackedUpCell<U> b) {
    if (!a.isSet() && !b.isSet()) {
      return 0;
    }
    if (!a.isSet()) {
      return -1;
    }
    if (!b.isSet()) {
      return 1;
    }
    int compareValues = valueComparator.compare(a.get(), b.get());
    if (compareValues != 0) {
      return compareValues;
    }
    Stack<U> aBackups = new Stack<>();
    Stack<U> bBackups = new Stack<>();
    while (a.hasBackup() && b.hasBackup()) {
      aBackups.push(a.get());
      bBackups.push(b.get());
      a.revertToPrevious();
      b.revertToPrevious();
      int compareBackups = valueComparator.compare(a.get(), b.get());
      if (compareBackups != 0) {
        return compareBackups;
      }
    }
    boolean aHasBackup = a.hasBackup();
    boolean bHasBackup = b.hasBackup();
    restoreBackups(a, aBackups);
    restoreBackups(b, bBackups);
    if (!aHasBackup && !bHasBackup) {
      return 0;
    }
    if (!aHasBackup) {
      return -1;
    }
    return 1;
  }

  private void restoreBackups(BackedUpCell<U> cell, Stack<U> backups) {
    while (!backups.isEmpty()) {
      cell.set(backups.pop());
    }
  }
}
