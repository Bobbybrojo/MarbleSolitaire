package cs3500.marblesolitaire.controller;


/**
 * This interface represents the operations of the controller
 * allowing the user to play a game of marble solitaire.
 */
public interface MarbleSolitaireController {

  /**
   * Play a new game of marble solitaire.
   * @throws IllegalStateException when the controller is unable to read input or transmit output
   */
  void playGame() throws IllegalStateException;
}
