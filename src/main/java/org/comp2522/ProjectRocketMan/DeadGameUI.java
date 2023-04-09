package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.event.KeyEvent;

/**
 * DeadGameUI displayed when the player dies in the game.
 *
 * @author Mangat Toor
 * @version 1.0.0
 */
public class DeadGameUI extends GameUI {

  /**
   * Constructor for DeadGameUI class.
   *
   * @param buttons    an array of the buttons used in this UI
   * @param manager    GameManager instance
   * @param background the background image
   */
  public DeadGameUI(Button[] buttons, GameManager manager, PImage background) {
    super(buttons, manager, background);
  }

  /**
   * Buttons clicked handler.
   *
   * @param label a String
   */
  @Override
  void buttonClicked(String label) {
    switch (label) {
      case "Retry" -> {
        manager.resetToStart();
        manager.setGameState(1);
      }
      case "Main Menu" -> manager.setGameState(0);
      default -> {
      }
    }

  }

  /**
   * Handles any key events happening in the game.
   *
   * @param keyEvent key events
   */
  @Override
  void keyEvent(KeyEvent keyEvent) {
    //no key events
  }
}
