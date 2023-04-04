package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

public class DeadGameUI extends GameUI{
  public DeadGameUI(PVector position, PVector direction, Button[] buttons, GameManager manager, PImage background) {
    super(position, direction, buttons, manager, background);
  }

  @Override
  void buttonClicked(String label) {
    switch (label) {
      case "Retry" -> {
        manager.resertToStart();
        manager.setGameState(1);
      }
      case "Main Menu" -> manager.setGameState(0);
      default -> {
        ;
      }
    }

  }

  @Override
  void keyEvent(KeyEvent keyEvent) {

  }

  /**
   * Sets the position of the movable object to the specified x and y coordinates.
   *
   * @param position - The position to set.
   */
  @Override
  void setPosition(PVector position) {

  }

  /**
   * Returns the current position of the movable object
   * as an object containing x and y coordinates.
   *
   * @return An object containing the x and y coordinates of the movable object's position.
   */
  @Override
  PVector getPosition() {
    return null;
  }

  /**
   * Moves the movable object by its own velocity amount in the x and y directions.
   */
  @Override
  void move() {

  }

  /**
   * Returns the speed of the movable object.
   *
   * @return A double value containing the x and y velocities of the movable object.
   */
  @Override
  float getSpeed() {
    return 0;
  }

  /**
   * Sets the velocity of the movable object to the specified values in the x and y directions.
   *
   * @param speed The velocity to set in both direction.
   */
  @Override
  void setSpeed(float speed) {

  }
}
