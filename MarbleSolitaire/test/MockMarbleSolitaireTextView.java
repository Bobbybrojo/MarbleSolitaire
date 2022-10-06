import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents a mock text view for testing purposes.
 */
public class MockMarbleSolitaireTextView implements MarbleSolitaireView {

  MarbleSolitaireModelState model;
  Appendable ap;

  /**
   * Creates a mock  {@code MarbleSolitaireTextView} .
   * @param model a {@code MarbleSolitaireModelState} object used to make the text view
   * @param ap appendable object used as the output for view
   * @throws IllegalArgumentException when the model or appendable is null
   */
  public MockMarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable ap)
      throws IllegalArgumentException {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("The provided model or appendable cannot be null");
    }
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void renderBoard() throws IOException {
    this.ap.append("Print Board\n");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.ap.append(message);
  }
}
