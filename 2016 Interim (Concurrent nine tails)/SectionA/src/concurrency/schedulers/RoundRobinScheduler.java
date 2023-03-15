package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import java.util.Optional;
import java.util.Set;

public class RoundRobinScheduler implements Scheduler {
  private boolean invoked;
  private int lastScheduledThreadId;

  public RoundRobinScheduler() {
    invoked = false;
    lastScheduledThreadId = -1;
  }

  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    Set<Integer> enabledThreadIds = program.getEnabledThreadIds();
    if (enabledThreadIds.isEmpty()) {
      throw new DeadlockException();
    }
    if (invoked) {
      Optional<Integer> next = enabledThreadIds
          .stream()
          .filter(id -> id > lastScheduledThreadId)
          .min(Integer::compareTo);
      if (next.isPresent()) {
        lastScheduledThreadId = next.get();
        return lastScheduledThreadId;
      }
    }
    invoked = true;
    lastScheduledThreadId = enabledThreadIds.stream().min(Integer::compareTo).get();
    return lastScheduledThreadId;
  }
}
