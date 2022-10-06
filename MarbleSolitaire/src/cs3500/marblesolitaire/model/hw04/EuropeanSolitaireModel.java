package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a game of marble solitaire of the European variation.
 */
public class EuropeanSolitaireModel extends AbstractMarbleSolitaireModel {

  /**
   * Initialize the game board with arm thickness of 3 and an empty slot at the center.
   */
  public EuropeanSolitaireModel() {
    this(3);
  }

  /**
   * Initialize the game board with arm thickness of 3 and an empty slot at the given position.
   *
   * @param sRow the row position of the empty slot
   * @param sCol the col position of the empty slot
   * @throws IllegalArgumentException thrown when the empty slot given is not at a valid position
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
  }

  /**
   * Initializes the game board with the given arm thickness and the empty slot at the center.
   *
   * @param armThickness the arm thickness of the board
   * @throws IllegalArgumentException thrown when the arm thickness is even or not positive
   */
  public EuropeanSolitaireModel(int armThickness) throws IllegalArgumentException {
    this(armThickness, ((armThickness * 3) - 3) / 2, ((armThickness * 3) - 3) / 2);
  }

  /**
   * Initializes the game board with the given arm thickness and empty slot at the given position.
   *
   * @param armThickness the arm thickness of the board
   * @param sRow         the row position of the empty slot
   * @param sCol         the col position of the empty slot
   * @throws IllegalArgumentException thrown when the arm thickness is even or not positive or
   *                                  when the empty slot position is not a valid position
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }


  @Override
  protected boolean isValidSlot(int row, int col) {
    int lowerMargin = ((getBoardSize() - this.armThickness) / 2) - 1;
    int upperMargin = getBoardSize() - 1 - lowerMargin;
    if (row < 0 || row >= getBoardSize() || col < 0 || col >= getBoardSize()) {
      return false;
    } else if ((row <= lowerMargin && col <= lowerMargin
            || row >= upperMargin && col <= lowerMargin
            || row <= lowerMargin && col >= upperMargin
            || row >= upperMargin && col >= upperMargin)) {
      if ((row + col <= lowerMargin)
              || (col - row >= upperMargin)
              || (col - row <= -upperMargin)
              || (row + col >= this.getBoardSize() + upperMargin - 1)) {
        return false;
      }
    }
    return true;
  }
}
