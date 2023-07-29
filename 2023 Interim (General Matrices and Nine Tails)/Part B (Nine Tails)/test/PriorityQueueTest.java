import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {

  private PriorityQueue<Integer> queue;

  @Before
  public void initializeListArrayBased() {
    queue = new PriorityQueue<>();
    assertTrue(queue.isEmpty());
  }

  // UNCOMMENT THE TESTS BELOW ONCE YOU HAVE IMPLEMENTED add() in PriorityQueue class
  /*
  @Test
  public void addSequence1() {
    // add(1), add(2), add(3), add(4)
    queue.add(1);
    assertEquals(1, queue.getSize());
    queue.add(2);
    assertEquals(2, queue.getSize());
    queue.add(3);
    assertEquals(3, queue.getSize());
    queue.add(4);
    assertEquals(4, queue.getSize());
  }

  @Test
  public void addSequence2() {
    // add(3), add(1), add(5), add(2)
    queue.add(3);
    assertEquals(1, queue.getSize());
    queue.add(1);
    assertEquals(2, queue.getSize());
    queue.add(5);
    assertEquals(3, queue.getSize());
    queue.add(2);
    assertEquals(4, queue.getSize());
  }
  */

  // UNCOMMENT THE TEST BELOW ONCE YOU HAVE IMPLEMENTED PriorityQueueRebuild()
  //      in PriorityQueue class
  /*
  @Test
  public void addRemoveAddSize() {
    // add(1), remove(1), add(1), add(2)
    queue.add(1);
    assertEquals(1, queue.getSize());

    queue.remove();
    assertEquals(0, queue.getSize());

    queue.add(1);
    assertEquals(1, queue.getSize());

    queue.add(2);
    assertEquals(2, queue.getSize());

  }
  */
}
