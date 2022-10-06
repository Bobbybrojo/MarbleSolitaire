package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a game of marble solitaire of the triangular variation.
 */
public class TriangleSolitaireModel extends AbstractMarbleSolitaireModel {

  /**
   * Initialize the game board with 5 rows and an empty slot at 0, 0.
   */
  public TriangleSolitaireModel() {
    this(5);
  }

  /**
   * Initialize the game board with 5 rows and an empty slot at the given position.
   *
   * @param sRow the row position of the empty slot
   * @param sCol the col position of the empty slot
   * @throws IllegalArgumentException thrown when the empty slot given is not at a valid position
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(5, sRow, sCol);
  }

  /**
   * Initializes the game board with the given row amount and the empty slot at 0, 0.
   *
   * @param dimensions the row amount of the board
   * @throws IllegalArgumentException thrown when the arm thickness is even or not positive
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {
    this(dimensions, 0, 0);
  }

  /**
   * Initializes the game board with the given row amount and empty slot at the given position.
   *
   * @param dimensions the row amount of the board
   * @param sRow         the row position of the empty slot
   * @param sCol         the col position of the empty slot
   * @throws IllegalArgumentException thrown when the arm thickness is even or not positive or
   *         when the empty slot position is not a valid position
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol)
          throws IllegalArgumentException {
    super(dimensions, sRow, sCol);
    if (!isValidSlot(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
  }

  @Override
  protected void performConstructorChecks(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness <= 0) {
      throw new IllegalArgumentException("Dimension must be positive and odd");
    }
    if (!isValidSlot(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
  }

  @Override
  protected boolean isValidSlot(int row, int col) {
    return col <= row && col >= 0 && row >= 0 && row < getBoardSize();
  }

  // Returns true if the slot has valid moves and false if not, assuming the given position is a
  // marble
  @Override
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
                    marbleBetween(row, col, row, col - 2)) ||
            (isValidSlot(row + 2, col + 2) &&
                    getSlotAt(row + 2, col + 2) == SlotState.Empty &&
                    marbleBetween(row, col, row + 2, col + 2)) ||
            (isValidSlot(row - 2, col - 2) &&
                    getSlotAt(row - 2, col - 2) == SlotState.Empty &&
                    marbleBetween(row, col, row - 2, col - 2)) ||
            (isValidSlot(row + 2, col - 2) &&
                    getSlotAt(row + 2, col - 2) == SlotState.Empty &&
                    marbleBetween(row, col, row + 2, col - 2)) ||
            (isValidSlot(row - 2, col + 2) &&
                    getSlotAt(row - 2, col + 2) == SlotState.Empty &&
                    marbleBetween(row, col, row - 2, col + 2));
  }

  // Returns true if the from and to positions are two positions away and false otherwise
  @Override
  protected boolean validMoveSpacing(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow == toRow && Math.abs(fromCol - toCol) == 2) {
      return true;
    }
    else if (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2) {
      return true;
    }
    else {
      return fromCol == toCol && Math.abs(fromRow - toRow) == 2;
    }
  }

  @Override
  public int getBoardSize() {
    return this.armThickness;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= getBoardSize() || col < 0 || col >= getBoardSize()) {
      throw new IllegalArgumentException("The row or col is outside the dimensions of the board");
    }
    return this.board.get(row).get(col);
  }
}
