import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the methods in the triangle solitaire text view class.
 */
public class TriangleSolitaireTextViewTest {

  Appendable ap;
  MarbleSolitaireModel modelStandard;
  MarbleSolitaireView modelViewStandard;
  MarbleSolitaireModel model6Size;
  MarbleSolitaireView modelView6;

  String standardText =
          "    _\n" +
          "   O O\n" +
          "  O O O\n" +
          " O O O O\n" +
          "O O O O O";

  String standardTextMove1 =
          "    _\n" +
          "   O O\n" +
          "  O O O\n" +
          " O O O O\n" +
          "O O O O O\n" +
          "    O\n" +
          "   _ O\n" +
          "  _ O O\n" +
          " O O O O\n" +
          "O O O O O";
  String size6Text =
          "     _\n" +
          "    O O\n" +
          "   O O O\n" +
          "  O O O O\n" +
          " O O O O O\n" +
          "O O O O O O";

  @Before
  public void init() {
    this.ap = new StringBuilder();

    this.modelStandard = new TriangleSolitaireModel();
    this.modelViewStandard = new TriangleSolitaireTextView(this.modelStandard, this.ap);
    this.model6Size = new TriangleSolitaireModel(6);
    this.modelView6 = new TriangleSolitaireTextView(this.model6Size);
  }


  @Test
  public void testToString() {
    assertEquals(this.standardText, this.modelViewStandard.toString());
    assertEquals(this.size6Text, this.modelView6.toString());
  }

  @Test
  public void testRenderBoard() {
    try {
      this.modelViewStandard.renderBoard();
    }
    catch (IOException e) {
      fail();
    }

    assertEquals(this.standardText + "\n", this.ap.toString());
    this.modelStandard.move(2, 0, 0, 0);

    try {
      this.modelViewStandard.renderBoard();
    }
    catch (IOException e) {
      fail();
    }
    assertEquals(this.standardTextMove1 + "\n", this.ap.toString());
  }


}