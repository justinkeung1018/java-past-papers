package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;

import java.util.Comparator;
import java.util.Set;

public class FewestWaitsScheduler implements Scheduler {
  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    Set<Integer> enabledThreadIds = program.getEnabledThreadIds();
    if (enabledThreadIds.isEmpty()) {
      throw new DeadlockException();
    }
    return enabledThreadIds
        .stream()
        .min(Comparator.comparingInt(id -> program.remainingStatements(id).size()))
        .get();
  }
}
