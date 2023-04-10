package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;

import static processing.core.PApplet.abs;

/**
 * Heart class represents one heart on the screen during the game.
 *
 * @author Jeevanjot Virk
 * @version 1.0.0
 */
public class Heart extends Sprite implements Collideable {

  /**
   * Images to form animations of the heart.
   */
  private PImage[] animations;

  /**
   * Index of the heart.
   */
  private int index;

  /**
   * Current speed of the heart.
   */
  private float speed;

  /**
   * Window instance.
   */
  private Window window;

  /**
   * Height of the heart.
   */
  private float height;

  /**
   * Width of the heart.
   */
  private float width;

  /**
   * Constructor for the heart class.
   *
   * @param position   position of the heart on the screen
   * @param direction  direction of the heart
   * @param animations heart animations
   * @param speed      speed of the heart
   */
  public Heart(PVector position, PVector direction, PImage[] animations, float speed) {
    super(position, direction);
    this.speed = speed;
    this.window = Window.getInstance();
    this.index = (int) window.random(0, 6);
    this.animations = animations;
    this.height = animations[0].width * 10;
    this.width = animations[0].width * 10;

  }

  /**
   * Constructor for the heart class without animations.
   *
   * @param position  position of the heart on the screen
   * @param direction direction of the heart
   * @param speed     speed of the heart
   */
  public Heart(PVector position, PVector direction, float speed) {
    super(position, direction);
    this.speed = speed;
  }

  /**
   * Getter for position.
   */
  public PVector getPosition() {
    return position;
  }

  /**
   * Setter for the position.
   *
   * @param position a PVector
   */
  @Override
  public void setPosition(PVector position) {
    this.position = position;

  }

  /**
   * Method to move the heart.
   */
  @Override
  public void move() {
    position.add(-1 * speed, 0);
  }

  /**
   * Getter for speed.
   *
   * @return speed
   */
  @Override
  public float getSpeed() {
    return 0;
  }

  /**
   * Setter for speed.
   *
   * @param speed speed of the heart
   */
  @Override
  public void setSpeed(float speed) {
    this.speed = speed;

  }

  /**
   * Draw method to display heart on the screen.
   */
  public void draw() {
    window.image(animations[index % animations.length],
        position.x, position.y,
        animations[0].width / 20f,
        animations[0].height / 20f);
    animate();
    window.stroke(0);        // set the stroke color to black
    window.fill(255, 0, 0);  // set the fill color to red
    window.ellipse(position.x, position.y, 4, 4);
  }

  /**
   * Animate method to animate the heart.
   */
  private void animate() {
    if (window.frameCount % 6 == 0) {
      this.index += this.speed;
    }
  }

  /**
   * Method that is called when the player collides with the heart.
   *
   * @param player Player instance
   * @return whether collided or not
   */
  @Override
  public boolean collided(Player player) {
    float xDistance = this.position.x - player.position.x;
    float yDistance = this.position.y - player.position.y;
    float xDistanceOffset = player.getWidth() * .3f;
    float yDistanceOffset = player.getHeight() * .3f;
    return abs(yDistance)
        < player.getHeight() - xDistanceOffset && abs(xDistance)
        < player.getWidth() - yDistanceOffset;
  }

  /**
   * Getter for height.
   *
   * @return height of the heart
   */
  public float getHeight() {
    return height;
  }

  /**
   * Getter for width.
   *
   * @return width of the heart
   */
  public float getWidth() {
    return width;
  }

}