package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.event.KeyEvent;

/**
 * StartGameUI ui for when the game starts.
 *
 * @author Jeevanjot Virk
 * @version 1.0.0
 */
public class StartGameUI extends GameUI {

  /**
   * Constructor for the StartGameUI class.
   *
   * @param buttons    buttons of the ui
   * @param manager    GameManager instance
   * @param background background image
   */
  public StartGameUI(Button[] buttons, GameManager manager, PImage background) {
    super(buttons, manager, background);
  }

  /**
   * Buttons clicked method.
   *
   * @param label a String
   */
  @Override
  void buttonClicked(String label) {
    switch (label) {
      case "Start" -> manager.setGameState(1);
      case "Leaderboard" -> manager.setGameState(4);
      case "Quit" -> window.exit();
      default -> {
      }
    }
  }

  /**
   * Key event handler.
   *
   * @param keyEvent key events
   */
  @Override
  void keyEvent(KeyEvent keyEvent) {
    //empty
  }
}
