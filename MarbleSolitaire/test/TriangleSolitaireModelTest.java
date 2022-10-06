
import org.junit.Before;
import org.junit.Test;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test the methods of the triangle solitaire model.
 */
public class TriangleSolitaireModelTest {

  MarbleSolitaireModel model5;
  MarbleSolitaireModel model6;
  MarbleSolitaireModel modelGen;

  @Before
  public void init() {
    this.model5 = new TriangleSolitaireModel();
    this.model6 = new TriangleSolitaireModel(6);
    this.modelGen = new TriangleSolitaireModel(7);

  }

  @Test
  public void testConstructors() {
    try {
      this.modelGen = new TriangleSolitaireModel(-1, 2);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,2)", e.getMessage());
    }

    try {
      this.modelGen = new TriangleSolitaireModel(5, 2, 1);
      assertEquals(14, this.modelGen.getScore());
      assertEquals(5, this.modelGen.getBoardSize());
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelGen.getSlotAt(2, 1));
    }
    catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.modelGen = new TriangleSolitaireModel(-1,1, 2);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Dimension must be positive and odd", e.getMessage());
    }

    try {
      this.modelGen = new TriangleSolitaireModel(5,2, 3);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (2,3)", e.getMessage());
    }
  }

  @Test
  public void testMoveInvalid() {
    try {
      this.model5.move(-1, 0, 3, 3);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The from or to position is invalid", e.getMessage());
    }

    try {
      this.model5.move(0, -1, 3, 3);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The from or to position is invalid", e.getMessage());
    }

    try {
      this.model5.move(2, 0, -1, 0);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The from or to position is invalid", e.getMessage());
    }

    try {
      this.model5.move(2, 0, 0, -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The from or to position is invalid", e.getMessage());
    }

    try {
      this.model5.move(0, 0, 2, 0);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("There must be a marble at the from position", e.getMessage());
    }

    this.model5.move(2, 0, 0, 0);
    try {
      this.model5.move(0, 0, 2, 0);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("There must be a marble between the from and to positions",
          e.getMessage());
    }


  }

  @Test
  public void testMoveValid() {

    this.model5.move(2, 0, 0, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(0, 0));

    this.model5.move(3, 2, 1, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(1, 0));

    this.model5.move(0, 0, 2, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(2, 0));

    this.model5.move(3, 0, 3, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(3, 2));

    this.model5.move(3, 3, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(3, 1));

    this.model5.move(1, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(3, 3));
  }

  @Test
  public void testGetScore() {
    assertEquals(14, this.model5.getScore());
    assertEquals(20, this.model6.getScore());
    assertEquals(27, this.modelGen.getScore());

    this.model5.move(2, 0, 0, 0);
    assertEquals(13, this.model5.getScore());

    this.model5.move(3, 2, 1, 0);
    assertEquals(12, this.model5.getScore());

    this.model5.move(0, 0, 2, 0);
    assertEquals(11, this.model5.getScore());
  }

  @Test
  public void isGameOver() {
    assertFalse(this.model5.isGameOver());
    assertFalse(this.model6.isGameOver());
    assertFalse(this.modelGen.isGameOver());

    this.model5.move(2, 0, 0, 0);
    assertFalse(this.model5.isGameOver());

    this.model5.move(3, 2, 1, 0);
    assertFalse(this.model5.isGameOver());

    this.model5.move(0, 0, 2, 0);
    assertFalse(this.model5.isGameOver());

    this.model5.move(2, 2, 0, 0);
    assertFalse(this.model5.isGameOver());

    this.model5.move(4, 4, 2, 2);
    assertFalse(this.model5.isGameOver());

    this.model5.move(4, 2, 4, 4);
    assertFalse(this.model5.isGameOver());

    this.model5.move(4, 1, 2, 1);
    assertFalse(this.model5.isGameOver());

    this.model5.move(3, 0, 1, 0);
    assertFalse(this.model5.isGameOver());

    this.model5.move(0, 0, 2, 0);
    assertTrue(this.model5.isGameOver());
  }




}
