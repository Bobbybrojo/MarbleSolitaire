package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Displays the game board as a string representation for triangular models.
 */
public class TriangleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Creates an {@code TriangleSolitaireTextView} object given a model.
   *
   * @param model a {@code MarbleSolitaireModelState} object used to make the text view
   * @throws IllegalArgumentException when the model provided is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
      throws IllegalArgumentException {
    super(model);
  }

  /**
   * Creates an {@code TriangleSolitaireTextView} object given a model and an appendable as output.
   * @param model a {@code MarbleSolitaireModelState} object used to make the text view
   * @param ap appendable object used as the output for view
   * @throws IllegalArgumentException when the model or appendable is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable ap)
          throws IllegalArgumentException {
    super(model, ap);
  }

  @Override
  public String toString() {
    StringBuilder boardString = new StringBuilder("");
    int spaces = this.model.getBoardSize() - 1;
    int marblesToPlace;
    for (int r = 1; r <= this.model.getBoardSize(); r++) {

      marblesToPlace = r;
      for (int i = 0; i < spaces; i++) {
        boardString.append(" ");
      }

      for (int c = 0; c < r; c++) {
        switch (model.getSlotAt(r - 1, c)) {
          case Marble:
            boardString.append("O");
            break;
          case Empty:
            boardString.append("_");
            break;
          default:
            // No action
            break;
        }
        marblesToPlace -= 1;
        if (marblesToPlace != 0) {
          boardString.append(" ");
        }
      }
      if (r != this.model.getBoardSize()) {
        boardString.append("\n");
      }

      spaces -= 1;
    }
    return boardString.toString();
  }



}
