package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Abstract class containing common methods for Marbl Solitaire Models.
 */
public abstract class AbstractMarbleSolitaireModel implements MarbleSolitaireModel {

  protected final ArrayList<ArrayList<SlotState>> board = new ArrayList<ArrayList<SlotState>>();
  protected final int armThickness;

  protected AbstractMarbleSolitaireModel(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    this.armThickness = armThickness;

    this.performConstructorChecks(armThickness, sRow, sCol);
    this.createBoard(sRow, sCol);

  }

  protected void performConstructorChecks(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    if (!isValidSlot(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    if (armThickness <= 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be positive and odd");
    }
  }

  protected void createBoard(int row, int col) {
    for (int r = 0; r < getBoardSize(); r++) {
      this.board.add(new ArrayList<SlotState>());
      for (int c = 0; c < getBoardSize(); c++) {
        if (isValidSlot(r, c)) {
          this.board.get(r).add(SlotState.Marble);
        } else {
          this.board.get(r).add(SlotState.Invalid);
        }
      }
    }
    this.board.get(row).set(col, SlotState.Empty);
  }


  protected abstract boolean isValidSlot(int row, int col);

  // Returns true if the slot has valid moves and false if not, assuming the given position is a
  // marble
  protected boolean hasValidMove(int row, int col) {
    return (isValidSlot(row + 2, col) &&
            getSlotAt(row + 2, col) == SlotState.Empty &&
            marbleBetween(row, col, row + 2, col)) ||
            (isValidSlot(row - 2, col) &&
                    getSlotAt(row - 2, col) == SlotState.Empty &&
                    marbleBetween(row, col, row - 2, col)) ||
            (isValidSlot(row, col + 2) &&
                    getSlotAt(row, col + 2) == SlotState.Empty &&
                    marbleBetween(row, col, row, col + 2)) ||
            (isValidSlot(row, col - 2) &&
                    getSlotAt(row, col - 2) == SlotState.Empty &&
                    marbleBetween(row, col, row, col - 2));
  }

  // Returns true if the from and to positions are two positions away and false otherwise
  protected boolean validMoveSpacing(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow == toRow && Math.abs(fromCol - toCol) == 2) {
      return true;
    }
    else {
      return fromCol == toCol && Math.abs(fromRow - toRow) == 2;
    }
  }

  // Returns true if there is a marble between the two given positions and false otherwise
  protected boolean marbleBetween(int fromRow, int fromCol, int toRow, int toCol) {
    return SlotState.Marble == getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2);
  }

  // Sets the marble between the two positions to an empty space, given the positions are 2 spaces
  // apart
  protected void setEmptySlot(int fromRow, int fromCol, int toRow, int toCol) {
    this.board.get((fromRow + toRow) / 2).set((fromCol + toCol) / 2, SlotState.Empty);
  }

  /**
   * Adds additional functionality for moves on triangle board.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException when the move is invalid
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws
          IllegalArgumentException {
    if (!this.isValidSlot(fromRow, fromCol) || !this.isValidSlot(toRow, toCol)) {
      throw new IllegalArgumentException("The from or to position is invalid");
    }
    else if (getSlotAt(fromRow, fromCol) != SlotState.Marble) {
      throw new IllegalArgumentException("There must be a marble at the from position");
    }
    else if (getSlotAt(toRow, toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("The to position must be empty");
    }
    else if (!validMoveSpacing(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("The from and to positions must be two positions away");
    }
    else if (!marbleBetween(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("There must be a marble " +
              "between the from and to positions");
    }
    this.board.get(fromRow).set(fromCol, SlotState.Empty);
    setEmptySlot(fromRow, fromCol, toRow, toCol);
    this.board.get(toRow).set(toCol, SlotState.Marble);
  }

  /**
   * Returns whether the game is over.
   * @return a boolean: true if game is over, false if not
   */
  public boolean isGameOver() {
    for (int r = 0; r < getBoardSize(); r++) {
      for (int c = 0; c < getBoardSize(); c++) {
        if (getSlotAt(r, c) == SlotState.Marble && this.hasValidMove(r, c)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Gets the board size of the model.
   * @return an int of the board size
   */
  public int getBoardSize() {
    return (3 * armThickness) - 2;
  }

  /**
   * Gets the state of the slot at the given position.
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the Slot State of the slot
   * @throws IllegalArgumentException when the slot given is invalid
   */
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= getBoardSize() || col < 0 || col >= getBoardSize()) {
      throw new IllegalArgumentException("The row or col is outside the dimensions of the board");
    }
    return this.board.get(row).get(col);
  }

  /**
   * Gets the score of the model.
   * @return an int with the current score of the game
   */
  public int getScore() {
    int score = 0;
    for (int r = 0; r < getBoardSize(); r++) {
      for (int c = 0; c < getBoardSize(); c++) {
        if (this.board.get(r).get(c) == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }

}
