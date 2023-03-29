package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;
import static processing.core.PConstants.UP;

/**
 * A class representing the player.
 */
public class Player extends Sprite implements Movable, Destroyable{
  /**
   * Stores the image of the player.
   */
  private PImage image;

  /**
   * Stores the instance of the Window.
   */
  private Window window;

  /**
   * The speed of the player. The Player starts with the 0 speed.
   */
  private float speed = 0;

  /**
   * The acceleration of the player.
   */
  private float acceleration = 1.5f;

  /**
   * The maximum speed of the player.
   */
  private float maxSpeed = -7f;

  /**
   * The gravity applied to the player.
   */
  private float gravity = 0.2f;

  /**
   * The only instance of the player object.
   */
  private static Player player;

  /**
   * The score of the player.
   */
  private int score;

  /**
   * The width of the player.
   */
  private float width;

  /**
   * The height of the player.
   */
  private float height;

  /**
   * The number of coins the player has collected.
   */
  private int numberOfCoinsCollected;

  /**
   * The number of lives the player has.
   */
  private int lives;

  /**
   * The number of hearts the player has.
   */
  private int hearts;

  /**
   * Private constructor to create the player object.
   * @param position The position of the player.
   * @param direction The direction of the player.
   * @param image The image of the player.
   * @param speed The speed of the player.
   */
  private Player(PVector position, PVector direction, PImage image, float speed) {
    super(position, direction);
    this.image = image;
    this.window = Window.getInstance();
    this.lives = 1;
    this.width = image.height / 10f;
    this.height = image.height / 10f;
  }

  /**
   * Creates a new instance of the player object if it doesn't exist or returns the existing one.
   * @param position The position of the player.
   * @param direction The direction of the player.
   * @param image The image of the player.
   * @param speed The speed of the player.
   * @return The only instance of the player object.
   */
  public static Player getInstance(PVector position, PVector direction, PImage image, float speed) {
    if (player == null) {
      player = new Player(position, direction, image, speed);
    }
    return player;
  }

  /**
   * Returns the gravity applied to the player.
   * @return The gravity applied to the player.
   */
  public float getGravity() {
    return gravity;
  }

  /**
   * Sets the gravity applied to the player.
   * @param gravity The gravity applied to the player.
   */
  public void setGravity(float gravity) {
    this.gravity = gravity;
  }

  /**
   * Returns the number of coins the player has collected.
   * @return The number of coins the player has collected.
   */
  public int getNumberOfCoinsCollected() {
    return numberOfCoinsCollected;
  }

  /**
   * Sets the number of coins the player has collected.
   * @param numberOfCoinsCollected The number of coins the player has collected.
   */
  public void setNumberOfCoinsCollected(int numberOfCoinsCollected) {
    this.numberOfCoinsCollected = numberOfCoinsCollected;
  }

  /**
   * Returns the acceleration of the player.
   * @return The acceleration of the player.
   */
  public float getAcceleration() {
    return acceleration;
  }

  /**

   Sets the acceleration of the player.
   @param acceleration the acceleration to set
   */
  public void setAcceleration(float acceleration) {
    this.acceleration = acceleration;
  }
  /**

   Returns the maximum speed of the player.
   @return the maximum speed
   */
  public float getMaxSpeed() {
    return maxSpeed;
  }
  /**

   Sets the maximum speed of the player.
   @param maxSpeed the maximum speed to set
   */
  public void setMaxSpeed(float maxSpeed) {
    this.maxSpeed = maxSpeed;
  }
  /**

   Returns the instance of the player.
   @return the player instance
   */
  public static Player getInstance(){
    return player;
  }
  /**

   Moves the player based on a key event.
   */
  private void moveOnKeyEvent(){
    PVector temp = this.getPosition();
    temp.add(0, this.getSpeed());
    this.setPosition(temp);
    setSpeed(getSpeed() - getGravity());
  }
  /**

   Returns the player image.
   @return the player image
   */
  public PImage getImage() {
    return image;
  }
  /**

   Sets the player image.
   @param image the player image to set
   */
  public void setImage(PImage image) {
    this.image = image;
  }
  /**

   Handles the key pressed event for the player.
   @param event the key event
   */
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    if(keyCode == UP && position.y > image.height/10f){
      setSpeed(getSpeed() * 0.8f - getAcceleration());
      if(getSpeed() < maxSpeed){
        setSpeed(maxSpeed);
      }
    } else {
      if(keyCode == UP && position.y < image.height/20f) { //put the logic of movement in move method
        if(speed != 0){
          speed = 0;
        }
      }
    }
  }
  /**

   Returns the number of lives the player has.
   @return the number of lives
   */
  public int getLives() {
    return lives;
  }
  /**

   Sets the number of lives the player has.
   @param lives the number of lives to set
   */
  public void setLives(int lives) {
    this.lives = lives;
  }
  /**

   Determines if the player is destroyed.
   @return always returns null
   */
  @Override
  public Boolean isDestroyed() {
    return null;
  }
  /**

   Destroys the player.
   */
  @Override
  public void destroy() {
  }

  /**

   Handles the player being destroyed.
   */
  @Override
  public void onDestroy() {
  }

  /**

   Sets the position of the player.
   @param position the position to set
   */
  @Override
  public void setPosition(PVector position) {
    this.position = position;
  }
  /**

   Returns the number of hearts the player has.
   @return the number of hearts
   */
  public int getHearts() {
    return hearts;
  }
  /**

   Sets the number of hearts the player has.
   @param hearts the number of hearts to set
   */
  public void setHearts(int hearts) {
    this.hearts = hearts;
  }

  /**
   * Returns the position of the object as a PVector.
   *
   * @return the position of the object as a PVector
   */
  @Override
  public PVector getPosition() {
    return position;
  }

  /**
   * Moves the object vertically according to its speed, gravity, and limits.
   * Sets the speed to 0 if the object hits the bottom limit.
   */
  @Override
  public void move() {
    PVector temp = new PVector(getPosition().x, getPosition().y);
    float bottomLimit = window.height - height - 90; // set bottom limit to 50 pixels above the bottom of the window
    if (temp.y + getSpeed() >= image.height/10f && temp.y + getSpeed() <= bottomLimit) {
      temp.add(0, getSpeed());
      setPosition(temp);
    }
    if (position.y + getSpeed() >= bottomLimit && speed != 0) {
      this.speed = 0;
    } else {
      if (position.y + getSpeed() < bottomLimit) {
        if (getSpeed() > -(maxSpeed + 2)) {
          setSpeed(-maxSpeed);
        } else {
          setSpeed(getSpeed() + getGravity());
        }
      }
    }
  }

  /**
   * Returns the score of the object.
   *
   * @return the score of the object
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets the score of the object.
   *
   * @param score the new score of the object
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Draws the object on the screen.
   */
  @Override
  void draw() {
    float angle = PVector.angleBetween(position,new PVector(0, 1));
//  window.rotate(radians(angle));
    window.image(this.image,position.x, position.y, image.height / 10f, image.width / 10f);
    window.stroke(0);        // set the stroke color to black
    window.fill(255, 0, 0);  // set the fill color to red
    window.ellipse(position.x,position.y, 4, 4);
  }

  /**
   * Returns the width of the object.
   *
   * @return the width of the object
   */
  public float getWidth() {
    return width;
  }

  /**
   * Sets the width of the object.
   *
   * @param width the new width of the object
   */
  public void setWidth(float width) {
    this.width = width;
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
   * Sets the height of the object.
   *
   * @param height the new height of the object
   */
  public void setHeight(float height) {
    this.height = height;
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
   * Sets the speed of the object.
   *
   * @param speed the new speed of the object
   */
  @Override
  public void setSpeed(float speed) {
    this.speed = speed;
  }

  /**
   * Sets the direction of the object.
   * This method is not currently implemented.
   *
   * @param direction the new direction of the object
   */
  @Override
  public void setDirection(PVector direction) {

  }
}
