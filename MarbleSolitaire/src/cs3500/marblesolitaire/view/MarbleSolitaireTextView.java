package cs3500.marblesolitaire.view;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;


/**
 * Displays the game board as a string representation.
 */
public class MarbleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Creates an {@code MarbleSolitaireTextView} object given a model.
   *
   * @param model a {@code MarbleSolitaireModelState} object used to make the text view
   * @throws IllegalArgumentException when the model provided is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    super(model);
  }

  /**
   * Creates an {@code MarbleSolitaireTextView} object given a model and an appendable as output.
   * @param model a {@code MarbleSolitaireModelState} object used to make the text view
   * @param ap appendable object used as the output for view
   * @throws IllegalArgumentException when the model or appendable is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable ap)
      throws IllegalArgumentException {
    super(model, ap);
  }

  @Override
  public String toString() {
    StringBuilder boardString = new StringBuilder("");
    for (int r = 0; r < model.getBoardSize(); r++) {
      for (int c = 0; c < model.getBoardSize(); c++) {
        switch (model.getSlotAt(r, c)) {
          case Invalid:
            boardString.append(" ");
            break;
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
        if (c != model.getBoardSize() - 1) {
          boardString.append(" ");
        }
      }
      int indexOfValid = Math.max(boardString.lastIndexOf("O"), boardString.lastIndexOf("_"));
      boardString.setLength(indexOfValid + 1);
      boardString.append("\n");
    }
    boardString.setLength(boardString.length() - 1);
    return boardString.toString();
  }
}
