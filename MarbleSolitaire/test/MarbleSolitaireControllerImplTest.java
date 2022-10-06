import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Tests the marble solitaire controller.
 */
public class MarbleSolitaireControllerImplTest {

  StringBuilder log;
  Readable rd;
  Appendable ap;
  Appendable corruptAp;
  EnglishSolitaireModel mock;
  MarbleSolitaireView mockView;
  MarbleSolitaireController controller;

  String instantQuit = "Print Board\n" +
          "Score: 32\n" +
          "Game quit!\n" +
          "State of game when quit:\n" +
          "Print Board\n" +
          "Score: 32\n";

  String move1Quit = "Print Board\n" +
          "Score: 32\n" +
          "Print Board\n" +
          "Score: 31\n" +
          "Game quit!\n" +
          "State of game when quit:\n" +
          "Print Board\n" +
          "Score: 31\n";

  String move6Quit = "Print Board\n" +
          "Score: 32\n" +
          "Print Board\n" +
          "Score: 31\n" +
          "Print Board\n" +
          "Score: 30\n" +
          "Print Board\n" +
          "Score: 29\n" +
          "Print Board\n" +
          "Score: 28\n" +
          "Print Board\n" +
          "Score: 27\n" +
          "Game over!\n" +
          "Print Board\n" +
          "Score: 26\n";

  String negativeInput = "Print Board\n" +
          "Score: 32\n" +
          "The input -1 was invalid. Try again!\n" +
          "The input -1 was invalid. Try again!\n" +
          "Game quit!\n" +
          "State of game when quit:\n" +
          "Print Board\n" +
          "Score: 32\n";

  String firstInvalidMove = "Print Board\n" +
          "Score: 32\n" +
          "Invalid move. Try again!\n" +
          "Print Board\n" +
          "Score: 32\n" +
          "Game quit!\n" +
          "State of game when quit:\n" +
          "Print Board\n" +
          "Score: 32\n";

  @Before
  public void init() {
    this.log = new StringBuilder();
    this.rd = new StringReader("");
    this.ap = new StringBuilder();
    this.corruptAp = new CorruptAppendableMock();
    this.mock = new MockEnglishSolitaireModel(new EnglishSolitaireModel(), this.log);
    this.mockView = new MockMarbleSolitaireTextView(this.mock, this.ap);
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);

  }

  @Test
  public void testConstructor() {
    try {
      this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, null);
    } catch (IllegalArgumentException e) {
      assertEquals("The model, view, and readable must not be null", e.getMessage());
    }

    try {
      this.controller = new MarbleSolitaireControllerImpl(this.mock, null, this.rd);
    } catch (IllegalArgumentException e) {
      assertEquals("The model, view, and readable must not be null", e.getMessage());
    }

    try {
      this.controller = new MarbleSolitaireControllerImpl(null, this.mockView, this.rd);
    } catch (IllegalArgumentException e) {
      assertEquals("The model, view, and readable must not be null", e.getMessage());
    }

  }

  @Test
  public void testConfirmInputs() {
    this.rd = new StringReader("4 6 4 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals("3 5 3 3", this.log.toString());

    this.rd = new StringReader("4 6 4\r4 1 2 q");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals("3 5 3 3", this.log.toString());

    this.rd = new StringReader("4\r6\r4\r4\rq");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals("3 5 3 3", this.log.toString());

    this.rd = new StringReader("-1 -1 -1 4 6 -2 4 -7 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals("3 5 3 3", this.log.toString());


  }

  @Test
  public void testConfirmOutputs() {
    this.rd = new StringReader("q");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals(this.instantQuit, this.ap.toString());

    init();
    this.rd = new StringReader("4 6 4 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals(this.move1Quit, this.ap.toString());

    init();
    this.rd = new StringReader("6 4 4 4 3 4 5 4 1 4 3 4 4 6 4 4 4 3 4 5 4 1 4 3 q");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals(this.move6Quit, this.ap.toString());
  }

  @Test
  public void testIllegalStateExceptions() {
    this.rd = new StringReader("4 6 4 4 q");
    this.mockView = new MockMarbleSolitaireTextView(this.mock, this.corruptAp);
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
      fail();
    } catch (IllegalStateException e) {
      assertEquals("Unable to successfully read input or transmit output", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveOutput() {
    this.rd = new StringReader("-1 -1 q");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals(this.negativeInput, this.ap.toString());

    init();
    this.rd = new StringReader("1 1 1 2 q");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals(this.firstInvalidMove, this.ap.toString());

    init();
    this.rd = new StringReader("4 4 4 6 q");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals(this.firstInvalidMove, this.ap.toString());

    init();
    this.rd = new StringReader("4 6 4 8 q");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals(this.firstInvalidMove, this.ap.toString());

    init();
    this.rd = new StringReader("4 3 4 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals(this.firstInvalidMove, this.ap.toString());

    init();
    this.rd = new StringReader("5 3 4 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.mock, this.mockView, this.rd);
    try {
      this.controller.playGame();
    } catch (IllegalStateException e) {
      fail();
    }
    assertEquals(this.firstInvalidMove, this.ap.toString());
  }
}