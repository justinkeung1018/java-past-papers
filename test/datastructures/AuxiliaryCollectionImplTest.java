package datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class AuxiliaryCollectionImplTest {

  AuxiliaryCollectionI<Integer, Integer> auxiliaryCollection;

  //////////////////////////////////////////////////////////
  //////////////////// PROVIDED TESTs //////////////////////
  //////////////////////////////////////////////////////////

  @Before
  public void initializeCollection() {
    auxiliaryCollection = new AuxiliaryCollectionImpl<>();
  }

  /*
  TODO: uncomment block to test append()
  @Test
  public void appendIncreasesSize() {
    assertEquals(0, auxiliaryCollection.size());

    var n = new DoublyLinkedNode<>(1, 1);
    assertTrue(auxiliaryCollection.append(n));
    assertEquals(1, auxiliaryCollection.size());

  }
  
  @Test
  public void keysInOrder() {
    // append(56), append(48), append(56), append(56): 56->48->56->56
    assertEquals(0, auxiliaryCollection.size());

    var n = new DoublyLinkedNode<>(56, 1);
    assertTrue(auxiliaryCollection.append(n));
    assertEquals(1, auxiliaryCollection.size());

    n = new DoublyLinkedNode<>(48, 1);
    assertTrue(auxiliaryCollection.append(n));
    assertEquals(2, auxiliaryCollection.size());

    n = new DoublyLinkedNode<>(56, 1);
    assertTrue(auxiliaryCollection.append(n));
    assertEquals(3, auxiliaryCollection.size());

    n = new DoublyLinkedNode<>(56, 1);
    assertTrue(auxiliaryCollection.append(n));
    assertEquals(4, auxiliaryCollection.size());

    assertEquals(Arrays.asList(56, 48, 56, 56), auxiliaryCollection.allKeysInOrder());
  }
  */

  /*
  TODO: Uncomment block to test remove()
  @Test
  public void removeDecreasesSize() {
    assertEquals(0, auxiliaryCollection.size());

    var n = new DoublyLinkedNode<>(1, 1);
    assertTrue(auxiliaryCollection.append(n));

    var node = auxiliaryCollection.find(1);
    assertTrue(auxiliaryCollection.remove(node));
    assertEquals(0, auxiliaryCollection.size());

  }
  */

}