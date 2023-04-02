package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;
import java.util.ArrayList;
import static processing.core.PApplet.*;

/**
 * The Rocket class represents a rocket object that can move and collide with other objects.
 */
public class Rocket extends Sprite implements Movable, Collidable {

  /**
   * Stores the rocket image.
   */
  PImage image;

  /**
   * Stores the instance of the Window.
   */
  Window window;

  /**
   * the speed of the rocket.
   */
  float speed;

  /**
   * Constructs a Rocket object with the specified position, direction, and speed.
   *
   * @param position the initial position of the rocket
   * @param direction the initial direction of the rocket
   * @param speed the speed at which the rocket moves
   */
  public Rocket(PVector position, PVector direction,float speed){
    super(position, direction);
    this.window = Window.getInstance();
    this.speed = speed;
  }

  /**
   * Constructs a Rocket object with the specified position, direction, image, and speed.
   *
   * @param position the initial position of the rocket
   * @param direction the initial direction of the rocket
   * @param image the image to use for the rocket
   * @param speed the speed at which the rocket moves
   */
  public Rocket(PVector position, PVector direction, PImage image, float speed){
    super(position, direction);
    this.image = image;
    this.window = Window.getInstance();
    this.speed = speed;
  }

  /**
   * Draws the rocket on the window.
   */
  public void draw() {
    window.image(image, position.x, position.y, image.height / 2f, image.width / 6f);
  }

  /**
   * Sets the position of the rocket to the specified position.
   *
   * @param position the new position of the rocket
   */
  @Override
  public void setPosition(PVector position) {
    this.position = position;
  }

  /**
   * Returns the current position of the rocket.
   *
   * @return the current position of the rocket
   */
  @Override
  public PVector getPosition() {
    return position;
  }

  /**
   * Moves the rocket in its current direction at its current speed.
   */
  @Override
  public void move() {
    PVector temp = getPosition();
    temp.add(getSpeed(), 0);
    setPosition(temp);
  }

  /**
   * Returns the current speed of the rocket.
   *
   * @return the current speed of the rocket
   */
  @Override
  public float getSpeed() {
    return speed;
  }

  /**
   * Sets the speed of the rocket to the specified speed.
   *
   * @param speed the new speed of the rocket
   */
  @Override
  public void setSpeed(float speed) {
    this.speed = speed;
  }

  /**
   * Sets the direction of the rocket to the specified direction.
   *
   * @param direction the new direction of the rocket
   */
  @Override
  public void setDirection(PVector direction) {
    // This method is not implemented for the Rocket class.
  }

  /**
   * Manages a list of rockets.
   *
   * @param rockets the list of rockets to manage
   */
  public static void manageItself(ArrayList<Rocket> rockets){
    // This method is not implemented for the Rocket class.
  }

  /**
   * Checks if the rocket has collided with the specified player.
   *
   * @param player the player to check for a collision with
   * @return true if the rocket has collided with the player, false otherwise
   */
  @Override
  public boolean collided(Player player){
    float yDistance = this.position.y - player.position.y;
    float xDistance = this.position.x - player.position.x;
    float xDistanceOffset = player.getWidth()* .3f;
    float yDistanceOffset = player.getHeight()* .3f;
    return abs(yDistance) < player.getHeight() - xDistanceOffset &&
            abs(xDistance) < player.getWidth() - yDistanceOffset;
  }
}
