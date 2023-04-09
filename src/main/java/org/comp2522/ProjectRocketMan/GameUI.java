package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.event.KeyEvent;

/**
 * GameUI abstract class.
 *
 * @author Mangat Toor
 * @version 1.0.0
 */
public abstract class GameUI {

  /**
   * Buttons array.
   */
  protected Button[] buttons;

  /**
   * Window instance.
   */
  protected Window window;

  /**
   * GameManager instance.
   */
  protected GameManager manager;

  /**
   * Background image.
   */
  protected PImage background;

  /**
   * Constructor for the GameUI abstract class.
   *
   * @param buttons    buttons array
   * @param manager    GameManager instance
   * @param background background image
   */
  public GameUI(Button[] buttons, GameManager manager, PImage background) {
    window = Window.getInstance();
    this.manager = manager;
    this.buttons = buttons;
    this.background = background;
  }

  /**
   * Draw method.
   */
  protected void draw() {
    window.image(background, 0, 0);
    for (Button button : buttons) {
      button.draw();
    }
  }

  /**
   * Check for clicks function.
   */
  protected void checkForClicks() {
    for (Button button : buttons) {
      if (button.isClicked()) {
        buttonClicked(button.getLabel());
      }
    }
  }

  /**
   * Buttons clicked method.
   */
  abstract void buttonClicked(String label);

  /**
   * Key event method.
   */
  abstract void keyEvent(KeyEvent keyEvent);
}
