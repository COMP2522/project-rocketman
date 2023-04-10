package org.comp2522.ProjectRocketMan;

import processing.core.PVector;

/**
 * The abstract class Sprite represents a game sprite with a position and direction in 2D space.
 * it also contains abstract methods related to Sprite like drawable.
 *
 * @author Aditya Agrawal
 * @version 1.0.0
 */
public abstract class Sprite {

  /**
   * Position of the Sprite.
   */
  protected PVector position;

  /**
   * Direction of the Sprite.
   */
  protected PVector direction;

  /**
   * Constructor for the Sprite class.
   *
   * @param position  Position of the Sprite.
   * @param direction Direction of the Sprite.
   */
  public Sprite(PVector position, PVector direction) {
    this.direction = direction;
    this.position = position;
  }

  /**
   * Draw method called to display the Sprite on screen.
   */
  abstract void draw();

  /**
   * Returns the current position of the movable object
   * as an object containing x and y coordinates.
   *
   * @return An object containing the x and y coordinates of the movable object's position.
   */
  abstract PVector getPosition();

  /**
   * Sets the position of the movable object to the specified x and y coordinates.
   *
   * @param position - The position to set.
   */
  abstract void setPosition(PVector position);

  /**
   * Moves the movable object by its own velocity amount in the x and y directions.
   */
  abstract void move();

  /**
   * Returns the speed of the movable object.
   *
   * @return A double value containing the x and y velocities of the movable object.
   */
  abstract float getSpeed();

  /**
   * Sets the velocity of the movable object to the specified values in the x and y directions.
   *
   * @param speed The velocity to set in both direction.
   */
  abstract void setSpeed(float speed);

}