package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.event.KeyEvent;

/**
 * PauseGameUI displayed when the game is paused.
 *
 * @author Mangat Toor
 * @version 1.0.0
 */
public class PauseGameUI extends GameUI {

  /**
   * Constructor for the PauseGameUI class.
   *
   * @param buttons    buttons in the ui
   * @param manager    GameManager instance
   * @param background background image
   */
  public PauseGameUI(Button[] buttons, GameManager manager, PImage background) {
    super(buttons, manager, background);
  }

  /**
   * Draw method to display the ui on the screen.
   */
  @Override
  public void draw() {
    window.image(background, 0, 0);
    drawMessage();
    for (Button button : buttons) {
      button.draw();
    }
  }

  /**
   * Draws the message on the screen.
   */
  private void drawMessage() {
    // Set the font size and color
    window.textSize(24);
    window.fill(255, 0, 0);

    // Draw the message
    window.text("Press P to unpause game",
        (float) window.width / 2 - 150,
        ((float) window.height / 2) + 50);
  }

  /**
   * Key event method.
   *
   * @param event key events
   */
  @Override
  void keyEvent(KeyEvent event) {
    if (event.getKey() == 'p' || event.getKey() == 'P') {
      manager.setGameState(1);
    }
  }

  /**
   * Buttons clicked method.
   *
   * @param label a String
   */
  @Override
  void buttonClicked(String label) {
    window.exit();
  }
}
