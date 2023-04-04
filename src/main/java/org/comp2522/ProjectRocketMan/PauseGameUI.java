package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

public class PauseGameUI extends GameUI{


  public PauseGameUI(PVector position, PVector direction, Button[] buttons, GameManager manager, PImage background) {
    super(position, direction, buttons, manager, background);
  }

  @Override
  public void draw(){
    window.image(background, 0,0);
    drawMessage();
    for(Button button : buttons){

      button.draw();
    }


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

  private void drawMessage(){

    // Set the font size and color
    window.textSize(24);
    window.fill(255, 0, 0);

    // Draw the message
    window.text("Press P to unpause game", window.width / 2 - 150, (window.height / 2) + 50);
  }

  public void keyEvent(KeyEvent event){
    if (event.getKey() == 'p' || event.getKey() == 'P') {
      manager.setGameState(1);
    }
  }
  @Override
  void buttonClicked(String label) {
    window.exit();
  }
}
