import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * Represents a mock english solitaire model for testing purposes.
 */
public class MockEnglishSolitaireModel extends EnglishSolitaireModel {

  EnglishSolitaireModel model;
  StringBuilder log;

  /**
   * Creates a mock english solitaire model.
   * @param model an English Solitaire model that this mock builds on
   * @param log a log of what input has been passed to this model from the controller
   */
  public MockEnglishSolitaireModel(EnglishSolitaireModel model, StringBuilder log) {
    this.model = model;
    this.log = log;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    super.move(fromRow, fromCol, toRow, toCol);
    this.log.append(fromRow)
      .append(" ")
      .append(fromCol)
      .append(" ")
      .append(toRow)
      .append(" ")
      .append(toCol);
  }
}
