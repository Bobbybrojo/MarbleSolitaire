package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractMarbleSolitaireModel;

/**
 * Represents a game of marble solitaire of the english variation.
 */
public class EnglishSolitaireModel extends AbstractMarbleSolitaireModel {

  /**
   * Initialize the game board with arm thickness of 3 and an empty slot at the center.
   */
  public EnglishSolitaireModel() {
    this(3);
  }

  /**
   * Initialize the game board with arm thickness of 3 and an empty slot at the given position.
   *
   * @param sRow the row position of the empty slot
   * @param sCol the col position of the empty slot
   * @throws IllegalArgumentException thrown when the empty slot given is not at a valid position
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
  }

  /**
   * Initializes the game board with the given arm thickness and the empty slot at the center.
   *
   * @param armThickness the arm thickness of the board
   * @throws IllegalArgumentException thrown when the arm thickness is even or not positive
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    this(armThickness, ((armThickness * 3) - 3) / 2, ((armThickness * 3) - 3) / 2);
  }

  /**
   * Initializes the game board with the given arm thickness and empty slot at the given position.
   *
   * @param armThickness the arm thickness of the board
   * @param sRow         the row position of the empty slot
   * @param sCol         the col position of the empty slot
   * @throws IllegalArgumentException thrown when the arm thickness is even or not positive or
   *         when the empty slot position is not a valid position
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }

  //Checks if the given position is a valid position for a slot or not
  protected boolean isValidSlot(int row, int col) {
    int lowerMargin = ((getBoardSize() - this.armThickness) / 2) - 1;
    int upperMargin = getBoardSize() - 1 - lowerMargin;
    if (row < 0 || row >= getBoardSize() || col < 0 || col >= getBoardSize()) {
      return false;
    }
    else if (row <= lowerMargin || row >= upperMargin) {
      return col > lowerMargin && col < upperMargin;
    }
    return true;
  }

}
