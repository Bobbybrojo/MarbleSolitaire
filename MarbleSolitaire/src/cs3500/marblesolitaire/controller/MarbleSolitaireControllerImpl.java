package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;


/**
 * An implementation of a marble solitaire controller which allows for a game of marble solitaire
 * to be played.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable rd;

  /**
   * Creates a new Marble Solitaire Controller with the given model, view, and readable.
   *
   * @param model the game model
   * @param view  the display view
   * @param rd    the readable input
   * @throws IllegalArgumentException when model, view, or readable is null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable rd) throws IllegalArgumentException {
    if (model == null || view == null || rd == null) {
      throw new IllegalArgumentException("The model, view, and readable must not be null");
    }
    this.model = model;
    this.view = view;
    this.rd = rd;
  }


  @Override
  public void playGame() throws IllegalStateException {
    boolean hasQuit = false;

    Scanner sc = new Scanner(this.rd);

    ArrayList<Integer> moveInputs = new ArrayList<>();

    while (!model.isGameOver() && !hasQuit) {

      try {
        renderBoardAndScore();
      } catch (IOException e) {
        throw new IllegalStateException("Unable to successfully read input or transmit output");
      }

      while (moveInputs.size() < 4) {
        if (sc.hasNext("q") || sc.hasNext("Q")) {
          hasQuit = true;
          break;
        } else if (sc.hasNextInt()) {
          int nextInt = sc.nextInt();

          if (nextInt >= 0) {
            moveInputs.add(nextInt - 1);
          } else {
            try {
              this.view.renderMessage("The input " + nextInt + " was invalid. Try again!\n");
            } catch (IOException e) {
              throw new IllegalStateException();
            }
          }
        } else {
          try {
            this.view.renderMessage("The input " + sc.next() + " was invalid. Try again!\n");
          } catch (IOException | NoSuchElementException e) {
            throw new IllegalStateException();
          }
        }
      }

      if (!hasQuit) {
        moveSequenceMessage(moveInputs);
        moveInputs.clear();
      }
    }

    if (hasQuit) {
      gameQuitMessage();
    }

    if (model.isGameOver()) {
      gameOverMessage();
    }
  }

  // Renders the board followed by the score of the game on a new line
  private void renderBoardAndScore() throws IOException {
    this.view.renderBoard();
    this.view.renderMessage("Score: " + this.model.getScore() + "\n");
  }

  // Renders the game over message sequence
  private void gameOverMessage() throws IllegalStateException {
    try {
      this.view.renderMessage("Game over!\n");
      renderBoardAndScore();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to successfully read input or transmit output");
    }
  }

  // Renders the game quit message sequence
  private void gameQuitMessage() throws IllegalStateException {
    try {
      this.view.renderMessage("Game quit!\n");
      this.view.renderMessage("State of game when quit:\n");
      renderBoardAndScore();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to successfully read input or transmit output");
    }
  }

  // Executes the move sequence given the move inputs
  private void moveSequenceMessage(ArrayList<Integer> moveInputs) throws IllegalStateException {
    try {
      this.model.move(moveInputs.get(0), moveInputs.get(1),
              moveInputs.get(2), moveInputs.get(3));
    } catch (IllegalArgumentException e) {
      try {
        System.out.println(e.getMessage());
        this.view.renderMessage("Invalid move. Try again!\n");
      } catch (IOException ioe) {
        throw new IllegalStateException("Unable to successfully read input or transmit output");
      }
    }
  }

}
