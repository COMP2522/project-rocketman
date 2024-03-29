package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;

import static processing.core.PApplet.abs;

/**
 * A class representing a coin in a game.
 * Coins are sprites that can move, be destroyed, and collide with other objects in the game.
 *
 * @author Aditya Agrawal
 * @version 1.0.0
 */
public class Coin extends Sprite implements Collideable {

  /**
   * Animations.
   */
  private PImage[] animations;

  /**
   * Index of the coins.
   */
  private int index;

  /**
   * Speed.
   */
  private float speed;

  /**
   * Window instance.
   */
  private Window window;

  /**
   * Height of the coins.
   */
  private float height;

  /**
   * Width of the coins.
   */
  private float width;

  /**
   * Constructor for the Coin object in the game.
   *
   * @param position   the position of the coin in the game world.
   * @param direction  the direction in which the coin moves.
   * @param animations an array of PImages representing the animations of the coin.
   * @param speed      the speed at which the coin moves.
   */
  public Coin(PVector position, PVector direction, PImage[] animations, float speed) {
    super(position, direction);
    this.speed = speed;
    this.window = Window.getInstance();
    this.index = (int) window.random(0, 6);
    this.animations = animations;
    this.height = animations[0].width / 50f;
    this.width = animations[0].width / 50f;
  }

  /**
   * Constructor for the coin class without animations array.
   *
   * @param position  the position of the coin in the game world.
   * @param direction the direction in which the coin moves.
   * @param speed     the speed at which the coin moves.
   */
  public Coin(PVector position, PVector direction, float speed) {
    super(position, direction);
    this.speed = speed;
  }

  /**
   * Set the position of the object.
   *
   * @param position the position of the coin.
   */
  @Override
  public void setPosition(PVector position) {
    this.position = position;
  }

  /**
   * Returns the position of the object.
   *
   * @return the position of the object
   */
  public PVector getPosition() {
    return position;
  }

  /**
   * Moves the object by updating its position along the x-axis.
   */
  @Override
  public void move() {
    position.add(-1 * speed, 0);
  }

  /**
   * Returns the speed of the object.
   *
   * @return the speed of the object
   */
  @Override
  public float getSpeed() {
    return speed;
  }

  /**
   * Returns the direction of the coin object.
   *
   * @return direction
   */
  public PVector getDirection() {
    return direction;
  }

  /**
   * Set the speed of the object.
   *
   * @param speed a float
   */
  @Override
  public void setSpeed(float speed) {
    this.speed = speed;
  }

  /**
   * Draws the animated object on the screen with its current position and animation frame.
   */
  public void draw() {
    window.image(animations[index % animations.length],
        position.x, position.y,
        animations[0].width / 50f,
        animations[0].height / 50f);
    animate();
    window.stroke(0);        // set the stroke color to black
    window.fill(255, 0, 0);  // set the fill color to red
    window.ellipse(position.x, position.y, 4, 4);
  }

  /**
   * Animation function for the coin object.
   */
  private void animate() {
    if (window.frameCount % 6 == 0) {
      this.index += this.speed;
    }
  }

  /**
   * Determines whether the player collides with the current object.
   *
   * @param player The player object to check for collision with.
   * @return True if the player collides with the object, false otherwise.
   */
  @Override
  public boolean collided(Player player) {
    float xDistance = this.position.x - player.position.x;
    float yDistance = this.position.y - player.position.y;
    float xDistanceOffset = player.getWidth() * .3f;
    float yDistanceOffset = player.getHeight() * .3f;
    return abs(yDistance)
        < player.getHeight()
        - xDistanceOffset && abs(xDistance)
        < player.getWidth() - yDistanceOffset;
  }

  /**
   * Returns the height of the object.
   *
   * @return the height of the object
   */
  public float getHeight() {
    return height;
  }

  /**
   * Returns the width of the object.
   *
   * @return the width of the object
   */
  public float getWidth() {
    return width;
  }

}