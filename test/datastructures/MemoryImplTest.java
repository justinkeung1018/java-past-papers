package datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings({"rawtypes", "unchecked"})
public class MemoryImplTest {

  MemoryImpl<Integer, Integer> memory;

  //////////////////////////////////////////////////////////
  //////////////////// PROVIDED TESTS //////////////////////
  //////////////////////////////////////////////////////////

  @Before
  public void initializeMemory() {
    memory = new MemoryImpl(10);
  }

  /*
  TODO: Uncomment block to test write()
  @Test
  public void write() {
    assertTrue(memory.write(1, 1));
    assertEquals(1, memory.size());
    assertTrue(memory.write(2, 1));
    assertEquals(2, memory.size());
    assertTrue(memory.write(3, 1));
    assertEquals(3, memory.size());
  }
  */

  /*
  TODO: Uncomment block to test write() and read()
  @Test
  public void read() {
    assertTrue(memory.write(1, 1));
    assertTrue(memory.write(2, 1));
    assertTrue(memory.write(3, 1));

    assertTrue(memory.read(3).isPresent());
    assertTrue(memory.read(2).isPresent());
    assertTrue(memory.read(1).isPresent());
  }
  */

}