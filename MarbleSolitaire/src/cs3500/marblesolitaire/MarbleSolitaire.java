package cs3500.marblesolitaire;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;


/**
 * This class contains the main method to run a game of Marble Solitaire with the given arguments.
 */
public final class MarbleSolitaire {

  private static final Map<String, Integer> attributes = new HashMap<>();

  /**
   * Runs a game of marble solitaire with the given command line arguments to specify the type.
   * @param args the given arguments that must start with english, european, triangular
   *             the command line can take the following arguments after the specified type of game:
   *             -size int
   *             -hole row col
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    int size;
    int row;
    int col;

    switch (args[0].toLowerCase()) {
      case "english":
        getExtraArgs(args, getExtraArgs(args, 1) + 2);
        size = attributes.getOrDefault("size", 3);
        row = attributes.getOrDefault("row", ((size * 3) - 3) / 2);
        col = attributes.getOrDefault("col", ((size * 3) - 3) / 2);
        model = new EnglishSolitaireModel(size, row, col);
        view = new MarbleSolitaireTextView(model);
        break;
      case "european":
        getExtraArgs(args, getExtraArgs(args, 1) + 2);
        size = attributes.getOrDefault("size", 3);
        row = attributes.getOrDefault("row", ((size * 3) - 3) / 2);
        col = attributes.getOrDefault("col", ((size * 3) - 3) / 2);
        model = new EuropeanSolitaireModel(size, row, col);
        view = new MarbleSolitaireTextView(model);
        break;
      case "triangular":
        getExtraArgs(args, getExtraArgs(args, 1) + 2);
        size = attributes.getOrDefault("size", 5);
        row = attributes.getOrDefault("row", 0);
        col = attributes.getOrDefault("col", 0);
        model = new TriangleSolitaireModel(size, row, col);
        view = new TriangleSolitaireTextView(model);
        break;
      default:
        //do nothing
        break;
    }
    Readable rd = new InputStreamReader(System.in);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
  }

  // adds arguments to the attributes hashmap and returns an int of how many attributes were added
  private static int getExtraArgs(String [] args, int argIndex) {
    try {
      switch (args[argIndex].toLowerCase()) {
        case "-size":
          attributes.put("size", Integer.parseInt(args[argIndex + 1]));
          return 1;
        case "-hole":
          attributes.put("row", Integer.parseInt(args[argIndex + 1]));
          attributes.put("col", Integer.parseInt(args[argIndex + 2]));
          return 2;
        default:
          // do nothing here
          break;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      return 0;
    }
    return 0;
  }

}
