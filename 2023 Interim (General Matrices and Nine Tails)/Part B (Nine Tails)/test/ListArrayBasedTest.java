import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ListArrayBasedTest {

  private ListArrayBased<Integer> list;

  @Before
  public void initializeListArrayBased() {
    list = new ListArrayBased<>();
  }

  @Test
  public void listEmpty() {
    assertEquals(0, list.size());
  }

  // UNCOMMENT THE TESTS BELOW ONCE YOU HAVE IMPLEMENTED add() in ListArrayBased class
  /*
  @Test
  public void listAddSimple() {
    assertEquals(0, list.size());
    list.add(1, 1);
    assertEquals(1, list.size());
    list.remove(1);
    assertEquals(0, list.size());

    list.add(1, 1);
    assertEquals(1, list.size());
    list.add(2, 2);
    assertEquals(2, list.size());
    list.add(3, 3);
    assertEquals(3, list.size());

    list.remove(3);
    list.remove(2);
    list.remove(1);
    assertEquals(0, list.size());
  }

  @Test
  public void positionRangeNoZero() {
    try {
      list.add(0, 0);
      assertNotEquals(1, list.size());
    } catch (IndexOutOfBoundsException e) {
      assertEquals(0, list.size());
    }

  }

  @Test
  public void listDoubleArray() {
    for (int i = 1; i <= 1024; i++) {
      list.add(i, i);
      assertEquals(list.size(), i);
    }

    for (int i = 1; i <= 1024; i++) {
      list.remove(1);
    }
    assertEquals(0, list.size());
  }
   */
}
