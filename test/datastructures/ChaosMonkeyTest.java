package datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ChaosMonkeyTest {

  static final int NUM_THREADS = 12;
  static final int NUM_KEY_INSTANCES = 500;
  static final int CAPACITY = 16;

  /*
  TODO: Uncomment to test  thread-safety
  @Parameters
  public static Object[] data() {
    return new Object[] {
            new MemoryImplCoarseGrainedSync<Integer, Integer>(CAPACITY)
    };
  }

  @Parameter
  public MemoryI<Integer, Integer> cache;

  @Test
  public void unleashChaos() {
    // create random keys to write and occasionally read
    // expect: no deadlock, unique keys only
    assertEquals(CAPACITY, cache.getCapacity());
    assertEquals(0, cache.size());

    ExecutorService executors = Executors.newFixedThreadPool(NUM_THREADS);
    CountDownLatch latch = new CountDownLatch(NUM_THREADS);

    for (int t = 0; t < NUM_THREADS; t++) {
      final var threadId = t;

      executors.submit(() -> {
        var rngKeys = new Random(threadId); // seeded with t
        rngKeys.ints(NUM_KEY_INSTANCES, 0, 127).forEach(
                k -> {
                  cache.write(k, 1);
                  try {
                    Thread.sleep(30);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  // 10% of the times, read from memory
                  if (rngKeys.nextDouble() < 0.10) {
                    cache.read(k);
                  }

                }
        );
        latch.countDown();
      });
    }

    try {
      latch.await();
      executors.shutdownNow();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    //  keys are unique in the LRU cache
    assertEquals(CAPACITY, cache.size());

    HashSet<Integer> keySet = new HashSet<>();
    for (Integer key : cache.allMemKeysInOrder()) {

      assertFalse(keySet.contains(key));
      keySet.add(key);
    }

  }
  */

}