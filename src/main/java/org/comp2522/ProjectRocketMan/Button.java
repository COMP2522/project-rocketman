package org.comp2522.ProjectRocketMan;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Represents a clickable button in a Processing sketch.
 */
public class Button {

  /**
   * The position of the button on the screen.
   */
  private final PVector position;

  /**
   * The dimensions of the button (width and height).
   */
  private final PVector dimensions;

  /**
   * The Processing window in which the button is drawn.
   */
  private final Window window;

  /**
   * The label text displayed on the button.
   */
  private String label;

  /**
   * Constructs a new Button object with the given position, dimensions, and label.
   * @param position The position of the button on the screen.
   * @param dimensions The dimensions of the button (width and height).
   * @param label The label text displayed on the button.
   */
  public Button(PVector position, PVector dimensions, String label){
    this.dimensions = dimensions;
    this.position = position;
    window = Window.getInstance();
    this.label = label;
  }

  /**
   * Draws the button on the screen.
   */
  public void draw(){
    window.stroke(255);
    window.fill(0);
    window.rect(position.x, position.y, dimensions.x, dimensions.y);
    window.fill(255);
    window.textAlign(window.CENTER, window.CENTER);
    window.text(label, position.x + dimensions.x/2, position.y + dimensions.y/2);
  }

  /**
   * Checks if the button is currently being clicked.
   * @return true if the button is being clicked, false otherwise.
   */
  public boolean isClicked(){

    return window.mouseX >= position.x && window.mouseX <= position.x + dimensions.x &&
        window.mouseY >= position.y && window.mouseY <= position.y + dimensions.y;
  }

  /**
   * Gets the label text displayed on the button.
   * @return The label text displayed on the button.
   */
  public String getLabel() {
    return label;
  }

  /**
   * Sets the label text displayed on the button.
   * @param label The new label text to display on the button.
   */
  public void setLabel(String label) {
    this.label = label;
  }
}
