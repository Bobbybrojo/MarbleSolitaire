
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * Tests the methods in the {@code EnglishSolitaireModel} class.
 */
public class EuropeanSolitaireModelTest {

  MarbleSolitaireModel modelOne;
  MarbleSolitaireModel modelStandard;
  MarbleSolitaireModel model5Arm;
  MarbleSolitaireModel model7Arm;

  // Initializes several models for use in testing, this method is run at the start of every test
  @Before
  public void init() {
    this.modelOne = new EuropeanSolitaireModel(1);
    this.modelStandard = new EuropeanSolitaireModel();
    this.model5Arm = new EuropeanSolitaireModel(5);
    this.model7Arm = new EuropeanSolitaireModel(7);
  }

  @Test
  public void testConstructors() {
    this.modelStandard = new EuropeanSolitaireModel();
    assertEquals(36, this.modelStandard.getScore());
    assertEquals(7, this.modelStandard.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(3, 3));

    this.modelStandard = new EuropeanSolitaireModel(3);
    assertEquals(36, this.modelStandard.getScore());
    assertEquals(7, this.modelStandard.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(3, 3));

    this.modelStandard = new EuropeanSolitaireModel(3, 4);
    assertEquals(36, this.modelStandard.getScore());
    assertEquals(7, this.modelStandard.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(3, 4));

    this.modelStandard = new EuropeanSolitaireModel(5, 6, 6);
    assertEquals(128, this.modelStandard.getScore());
    assertEquals(13, this.modelStandard.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(6, 6));

    try {
      this.modelStandard = new EuropeanSolitaireModel(-2, 3, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (3,3)", e.getMessage());
    }
    try {
      this.modelStandard = new EuropeanSolitaireModel(6, 6, 6);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness must be positive and odd", e.getMessage());
    }
    try {
      this.modelStandard = new EuropeanSolitaireModel(3, -1, 2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (" + -1 + "," + 2 + ")", e.getMessage());
    }
    try {
      this.modelStandard = new EuropeanSolitaireModel(3, 3, -2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (" + 3 + "," + -2 + ")", e.getMessage());
    }
  }

  // Tests the move method
  @Test
  public void testMove() {
    try {
      this.modelStandard.move(-1, 0, 3, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The from or to position is invalid", e.getMessage());
    }
    try {
      this.modelStandard.move(3, 5, 3, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The from or to position is invalid", e.getMessage());
    }
    try {
      this.modelStandard.move(3, 3, 3, 5);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("There must be a marble at the from position", e.getMessage());
    }
    try {
      this.modelStandard.move(0, 2, 0, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The to position must be empty", e.getMessage());
    }
    try {
      this.modelStandard.move(3, 0, 3, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The from and to positions must be two positions away", e.getMessage());
    }
    Assert.assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.modelStandard.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.modelStandard.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(3, 3));
    this.modelStandard.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.modelStandard.getSlotAt(3, 3));
    try {
      this.modelStandard.move(3, 3, 3, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("There must be a marble between the from and to positions", e.getMessage());
    }

    init();
    this.modelStandard.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.modelStandard.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(3, 2));
    this.modelStandard.move(1, 1, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.modelStandard.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(2, 1));

    init();
    this.modelStandard.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.modelStandard.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(3, 2));
    this.modelStandard.move(5, 1, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.modelStandard.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.modelStandard.getSlotAt(5, 1));


  }

  // Tests the isGameOver method
  @Test
  public void testIsGameOver() {
    assertFalse(this.modelStandard.isGameOver()); // tests standard game at the start
    assertFalse(this.model5Arm.isGameOver()); // tests arm length 5 at start
    assertTrue(this.modelOne.isGameOver()); // tests arm thickness 1 game

    // Tests that a game of marble solitaire can be played in completion resulting in a game over
    this.modelStandard.move(3, 5, 3, 3);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(5, 4, 3, 4);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(4, 6, 4, 4);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(4, 3, 4, 5);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(4, 1, 4, 3);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(6, 2, 4, 2);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(3, 2, 5, 2);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(6, 4, 6, 2);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(6, 2, 4, 2);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(2, 4, 4, 4);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(0, 4, 2, 4);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(1, 2, 3, 2);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(3, 2, 5, 2);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(5, 2, 5, 4);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(5, 4, 3, 4);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(3, 4, 1, 4);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(2, 6, 4, 6);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(4, 6, 4, 4);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(4, 4, 4, 2);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(2, 0, 2, 2);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(2, 3, 2, 1);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(4, 0, 2, 0);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(2, 0, 2, 2);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(0, 2, 0, 4);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(0, 4, 2, 4);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(2, 5, 2, 3);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(2, 3, 2, 1);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(2, 1, 4, 1);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(4, 1, 4, 3);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(4, 3, 2, 3);
    assertFalse(this.modelStandard.isGameOver());
    this.modelStandard.move(1, 3, 3, 3);
    assertTrue(this.modelStandard.isGameOver());


  }

  // Tests the getBoardSize method
  @Test
  public void testGetBoardSize() {
    assertEquals(1, this.modelOne.getBoardSize());
    assertEquals(7, this.modelStandard.getBoardSize());
    assertEquals(13, this.model5Arm.getBoardSize());
    assertEquals(19, this.model7Arm.getBoardSize());
    assertEquals(31, new EnglishSolitaireModel(11).getBoardSize());
  }

  // Tests the getSlotAt method
  @Test
  public void testGetSlotAt() {
    try {
      modelOne.getSlotAt(-1, -1);
      fail("Did not throw an exception when one was expected");
    } catch (IllegalArgumentException e) {
      assertEquals("The row or col is outside the dimensions of the board", e.getMessage());
    }
    try {
      modelOne.getSlotAt(-1, 0);
      fail("Did not throw an exception when one was expected");
    } catch (IllegalArgumentException e) {
      assertEquals("The row or col is outside the dimensions of the board", e.getMessage());
    }
    try {
      modelOne.getSlotAt(3, 0);
      fail("Did not throw an exception when one was expected");
    } catch (IllegalArgumentException e) {
      assertEquals("The row or col is outside the dimensions of the board", e.getMessage());
    }
    try {
      modelOne.getSlotAt(0, -1);
      fail("Did not throw an exception when one was expected");
    } catch (IllegalArgumentException e) {
      assertEquals("The row or col is outside the dimensions of the board", e.getMessage());
    }
    try {
      modelOne.getSlotAt(0, 3);
      fail("Did not throw an exception when one was expected");
    } catch (IllegalArgumentException e) {
      assertEquals("The row or col is outside the dimensions of the board", e.getMessage());
    }
    try {
      modelOne.getSlotAt(3, 3);
      fail("Did not throw an exception when one was expected");
    } catch (IllegalArgumentException e) {
      assertEquals("The row or col is outside the dimensions of the board", e.getMessage());
    }
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelOne.getSlotAt(0, 0));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelStandard.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelStandard.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(0, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(0, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelStandard.getSlotAt(0, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelStandard.getSlotAt(0, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelStandard.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(1, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(1, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelStandard.getSlotAt(1, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(2, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelStandard.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(3, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(4, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(4, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelStandard.getSlotAt(5, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(5, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(5, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelStandard.getSlotAt(5, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelStandard.getSlotAt(6, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelStandard.getSlotAt(6, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(6, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(6, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelStandard.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelStandard.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelStandard.getSlotAt(6, 6));
  }

  // Tests the getScore method
  @Test
  public void testGetScore() {
    // Tests the starting amount of marble on different sized models
    assertEquals(0, this.modelOne.getScore());
    assertEquals(36, this.modelStandard.getScore());
    assertEquals(128, this.model5Arm.getScore());
    assertEquals(276, this.model7Arm.getScore());

    // Tests the score at each step of a standard game until there is a score of 1
    this.modelStandard.move(3, 5, 3, 3);
    assertEquals(35, this.modelStandard.getScore());
    this.modelStandard.move(5, 4, 3, 4);
    assertEquals(34, this.modelStandard.getScore());
    this.modelStandard.move(4, 6, 4, 4);
    assertEquals(33, this.modelStandard.getScore());
    this.modelStandard.move(4, 3, 4, 5);
    assertEquals(32, this.modelStandard.getScore());
    this.modelStandard.move(4, 1, 4, 3);
    assertEquals(31, this.modelStandard.getScore());
    this.modelStandard.move(6, 2, 4, 2);
    assertEquals(30, this.modelStandard.getScore());
    this.modelStandard.move(3, 2, 5, 2);
    assertEquals(29, this.modelStandard.getScore());
    this.modelStandard.move(6, 4, 6, 2);
    assertEquals(28, this.modelStandard.getScore());
    this.modelStandard.move(6, 2, 4, 2);
    assertEquals(27, this.modelStandard.getScore());
    this.modelStandard.move(2, 4, 4, 4);
    assertEquals(26, this.modelStandard.getScore());
    this.modelStandard.move(0, 4, 2, 4);
    assertEquals(25, this.modelStandard.getScore());
    this.modelStandard.move(1, 2, 3, 2);
    assertEquals(24, this.modelStandard.getScore());
    this.modelStandard.move(3, 2, 5, 2);
    assertEquals(23, this.modelStandard.getScore());
    this.modelStandard.move(5, 2, 5, 4);
    assertEquals(22, this.modelStandard.getScore());
    this.modelStandard.move(5, 4, 3, 4);
    assertEquals(21, this.modelStandard.getScore());
    this.modelStandard.move(3, 4, 1, 4);
    assertEquals(20, this.modelStandard.getScore());
    this.modelStandard.move(2, 6, 4, 6);
    assertEquals(19, this.modelStandard.getScore());
    this.modelStandard.move(4, 6, 4, 4);
    assertEquals(18, this.modelStandard.getScore());
    this.modelStandard.move(4, 4, 4, 2);
    assertEquals(17, this.modelStandard.getScore());
    this.modelStandard.move(2, 0, 2, 2);
    assertEquals(16, this.modelStandard.getScore());
    this.modelStandard.move(2, 3, 2, 1);
    assertEquals(15, this.modelStandard.getScore());
    this.modelStandard.move(4, 0, 2, 0);
    assertEquals(14, this.modelStandard.getScore());
    this.modelStandard.move(2, 0, 2, 2);
    assertEquals(13, this.modelStandard.getScore());
    this.modelStandard.move(0, 2, 0, 4);
    assertEquals(12, this.modelStandard.getScore());
    this.modelStandard.move(0, 4, 2, 4);
    assertEquals(11, this.modelStandard.getScore());
    this.modelStandard.move(2, 5, 2, 3);
    assertEquals(10, this.modelStandard.getScore());
    this.modelStandard.move(2, 3, 2, 1);
    assertEquals(9, this.modelStandard.getScore());
    this.modelStandard.move(2, 1, 4, 1);
    assertEquals(8, this.modelStandard.getScore());
    this.modelStandard.move(4, 1, 4, 3);
    assertEquals(7, this.modelStandard.getScore());
    this.modelStandard.move(4, 3, 2, 3);
    assertEquals(6, this.modelStandard.getScore());
    this.modelStandard.move(1, 3, 3, 3);
    assertEquals(5, this.modelStandard.getScore());
  }
}