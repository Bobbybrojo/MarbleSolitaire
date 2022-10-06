import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests methods in the {@code MarbleSolitaireTextView} class.
 */
public class MarbleSolitaireTextViewTest {

  String oneText = "_";
  String newBoardText =
          "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O";

  String newBoardTextMove =
          "    O O O\n" +
                  "    O O O\n" +
                  "O O O O O O O\n" +
                  "O O O O _ _ O\n" +
                  "O O O O O O O\n" +
                  "    O O O\n" +
                  "    O O O";

  String newBoard5Text =
          "        O O O O O\n" +
          "        O O O O O\n" +
          "        O O O O O\n" +
          "        O O O O O\n" +
          "O O O O O O O O O O O O O\n" +
          "O O O O O O O O O O O O O\n" +
          "O O O O O O _ O O O O O O\n" +
          "O O O O O O O O O O O O O\n" +
          "O O O O O O O O O O O O O\n" +
          "        O O O O O\n" +
          "        O O O O O\n" +
          "        O O O O O\n" +
          "        O O O O O";


  String europeanBoardText =
          "    O O O\n" +
          "  O O O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "  O O O O O\n" +
          "    O O O";

  Appendable ap;
  MarbleSolitaireModel europeanModelStandard;
  MarbleSolitaireView euroView;
  MarbleSolitaireModel modelOne;
  MarbleSolitaireView modelViewOne;
  MarbleSolitaireModel modelStandard;
  MarbleSolitaireView modelViewStandard;
  MarbleSolitaireModel model5Arm;
  MarbleSolitaireView modelView5;

  @Before
  public void init() {
    this.ap = new StringBuilder();
    this.europeanModelStandard = new EuropeanSolitaireModel();
    this.euroView = new MarbleSolitaireTextView(this.europeanModelStandard);
    this.modelOne = new EnglishSolitaireModel(1);
    this.modelViewOne = new MarbleSolitaireTextView(this.modelOne);
    this.modelStandard = new EnglishSolitaireModel();
    this.modelViewStandard = new MarbleSolitaireTextView(this.modelStandard, this.ap);
    this.model5Arm = new EnglishSolitaireModel(5);
    this.modelView5 = new MarbleSolitaireTextView(this.model5Arm);
  }

  @Test
  public void testToString() {
    assertEquals(this.oneText, this.modelViewOne.toString());
    assertEquals(this.newBoardText, this.modelViewStandard.toString());
    assertEquals(this.newBoard5Text, this.modelView5.toString());
    this.modelStandard.move(3, 5, 3, 3);
    assertEquals(this.newBoardTextMove, this.modelViewStandard.toString());
    assertEquals(this.europeanBoardText, this.euroView.toString());
  }

  @Test
  public void testConstructorInvalidArguments() {
    try {
      this.modelViewStandard = new MarbleSolitaireTextView(null);
      fail("The invalid arguments did not throw an exception");
    }
    catch (IllegalArgumentException e) {
      assertEquals("The provided model was null", e.getMessage());
    }
    try {
      this.modelViewStandard = new MarbleSolitaireTextView(this.modelStandard, null);
      fail("The invalid arguments did not throw an exception");
    }
    catch (IllegalArgumentException e) {
      assertEquals("The provided model or appendable cannot be null", e.getMessage());
    }
    try {
      this.modelViewStandard = new MarbleSolitaireTextView(null, this.ap);
      fail("The invalid arguments did not throw an exception");
    }
    catch (IllegalArgumentException e) {
      assertEquals("The provided model or appendable cannot be null", e.getMessage());
    }
  }

  @Test
  public void testConstructorValidArguments() {
    this.modelViewStandard = new MarbleSolitaireTextView(this.modelStandard);
    assertEquals(this.newBoardText, this.modelViewStandard.toString());
    this.modelViewStandard = new MarbleSolitaireTextView(this.modelStandard, this.ap);
    assertEquals(this.newBoardText, this.modelViewStandard.toString());
  }

  @Test
  public void testRenderBoard() {
    try {
      this.modelViewStandard.renderBoard();
    }
    catch (IOException e) {
      fail();
    }

    assertEquals(this.newBoardText + "\n", this.ap.toString());
    this.modelStandard.move(3, 5, 3, 3);

    try {
      this.modelViewStandard.renderBoard();
    }
    catch (IOException e) {
      fail();
    }
    assertEquals(this.newBoardText + "\n" + this.newBoardTextMove + "\n",
        this.ap.toString());

    init();
    this.modelViewStandard = new MarbleSolitaireTextView(this.modelStandard,
        new CorruptAppendableMock());
    try {
      this.modelViewStandard.renderBoard();
    }
    catch (IOException e) {
      assertEquals("Issue transmitting to appendable.", e.getMessage());
    }

    init();
    this.euroView = new MarbleSolitaireTextView(this.europeanModelStandard, this.ap);
    try {
      this.euroView.renderBoard();
    }
    catch (IOException e) {
      fail();
    }
    assertEquals(this.europeanBoardText + "\n", this.ap.toString());
  }

  @Test
  public void testRenderMessage() {
    try {
      this.modelViewStandard.renderMessage("Hello world\n");
    }
    catch (IOException e) {
      fail();
    }
    assertEquals("Hello world\n", this.ap.toString());

    try {
      this.modelViewStandard.renderMessage("message\n");
    }
    catch (IOException e) {
      fail();
    }
    assertEquals("Hello world\nmessage\n", this.ap.toString());
    init();
    try {
      this.modelViewStandard.renderMessage("\n");
    }
    catch (IOException e) {
      fail();
    }
    assertEquals("\n", this.ap.toString());
    init();
    this.modelViewStandard = new MarbleSolitaireTextView(this.modelStandard,
        new CorruptAppendableMock());
    try {
      this.modelViewStandard.renderMessage("This is the message\n");
    }
    catch (IOException e) {
      assertEquals("Issue transmitting to appendable.", e.getMessage());
    }
  }
}




