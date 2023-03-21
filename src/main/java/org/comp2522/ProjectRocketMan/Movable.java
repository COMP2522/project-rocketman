package org.comp2522.ProjectRocketMan;
import processing.core.PVector;

/**
 * The Movable interface defines the necessary methods
 * and properties for objects that can be moved around
 * the screen.
 */
public interface Movable {


  /**
   * Sets the position of the movable object to the specified x and y coordinates.
   * @param position - The position to set.
   *
   */
  void setPosition(PVector position);


  /**
   * Returns the current position of the movable object
   * as an object containing x and y coordinates.
   * @return An object containing the x and y coordinates of the movable object's position.
   */
  PVector getPosition();

  /**
   * Moves the movable object by the specified amount in the x and y directions.
   * @param dx - The distance to move the object in the x direction.
   * @param dy - The distance to move the object in the y direction.
   */
  void move(double dx, double dy);

  /**
   * Returns the speed of the movable object.
   * @return A double value containing the x and y velocities of the movable object.
   */
  double getSpeed();

  /**
   * Sets the velocity of the movable object to the specified values in the x and y directions.
   * @param speed The velocity to set in both direction.
   */
  void setSpeed(double speed);


  /**
   * Sets the direction of the movable object.
   * @param direction The direction to set.
   */
  void setDirection(PVector direction);

}

