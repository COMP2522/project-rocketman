package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;

/**
 * The Background class represents a scrolling background in a game.
 * It is used to display images that move behind the game objects.
 *
 * @author Michelle
 * @version 1.0.0
 */
public class Background extends Sprite {

  /**
   * Background image.
   * */
  private PImage image;

  /**
   * Speed of the image.
   * */
  private float speed;

  /**
   * Window instance.
   * */
  private final Window window;

  /**
   * Constructor of Background class.
   *
   * @param image the image used for the background
   * @param speed the speed at which the background moves
   * @param position the starting position of the background
   * @param direction the direction in which the background moves
   */
  public Background(PImage image, float speed, PVector position, PVector direction) {
    super(position, direction);
    this.window = Window.getInstance();
    this.image = image;
    this.speed = speed;
  }

  /**
   * Constructor of Background class without image.
   *
   * @param speed the speed at which the background moves
   * @param position the starting position of the background
   * @param direction the direction in which the background moves
   */
  public Background(float speed, PVector position, PVector direction) {
    super(position,direction);
    this.window = Window.getInstance();
    this.speed = speed;
  }

  /**
   * Sets the position of this object to the specified PVector position.
   *
   * @param position the PVector position to set
   */
  @Override
  public void setPosition(PVector position) {
    this.position = position;
  }

  /**
   * Returns the position of an object as a PVector.
   *
   * @return the position of the object as a PVector.
   */
  @Override
  public PVector getPosition() {
    return position;
  }

  /**
   * Overrides the move method from the parent class to move the object in a specific direction.
   * Moves the object horizontally to the left by the amount of the speed value.
   */
  @Override
  public void move() {
    position.add(-1 * speed,0);
  }

  /**
   * Returns the speed of an object as a float.
   *
   * @return the speed of the object as a float.
   */
  @Override
  public float getSpeed() {
    return speed;
  }

  /**
   * Sets the speed of this object to the specified number.
   *
   * @param speed the speed to set
   */
  public void setSpeed(float speed) {
    this.speed = speed;
  }

  /**
   * Draws an image on a window with a scrolling effect.
   * The image is continuously drawn and scrolled across the window.
   */
  public void draw() {
    position.y = speed;
    position.x -= speed;

    int offsetX = (int) (position.x % image.width - image.width);
    int offsetY = (int) (position.y % image.height - image.height);

    for (int x = offsetX; x < image.width; x += image.width) {
      for (int y = offsetY; y < image.height; y += image.height) {
        window.image(image, x, y);
      }
    }
  }
}