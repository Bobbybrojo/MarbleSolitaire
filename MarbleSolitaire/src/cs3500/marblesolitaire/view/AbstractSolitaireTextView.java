package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstraction for MarbleSolitaireViews containing common methods.
 */
public abstract class AbstractSolitaireTextView implements MarbleSolitaireView {

  protected final MarbleSolitaireModelState model;
  protected final Appendable ap;

  /**
   * Creates an {@code MarbleSolitaireView} object given a model.
   *
   * @param model a {@code MarbleSolitaireModelState} object used to make the text view
   * @throws IllegalArgumentException when the model provided is null
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState model)
      throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The provided model was null");
    }
    this.model = model;
    this.ap = System.out;
  }

  /**
   * Creates an {@code MarbleSolitaireView} object given a model and an appendable as output.
   * @param model a {@code MarbleSolitaireModelState} object used to make the text view
   * @param ap appendable object used as the output for view
   * @throws IllegalArgumentException when the model or appendable is null
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState model, Appendable ap)
          throws IllegalArgumentException {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("The provided model or appendable cannot be null");
    }
    this.model = model;
    this.ap = ap;
  }

  @Override
  public abstract String toString();

  @Override
  public void renderBoard() throws IOException {
    this.ap.append(this.toString()).append("\n");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.ap.append(message);
  }
}
