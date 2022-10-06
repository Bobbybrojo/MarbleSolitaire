import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw02TypeChecks {

  /**
   * A sample main method.
   *
   * @param args the program arguments
   */
  public static void main(String[] args) {
    helper(new EnglishSolitaireModel());
    helper(new EnglishSolitaireModel(2, 2));
    helper(new EnglishSolitaireModel(5));
    helper(new EnglishSolitaireModel(3, 0, 4));
  }

  private static void helper(cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel model) {
    model.move(1, 3, 3, 3);
  }

}
