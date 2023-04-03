package org.comp2522.ProjectRocketMan;
import com.mongodb.client.model.mql.MqlNumber;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

import static processing.core.PApplet.abs;

/**
 * A class representing a coin in a game.
 * Coins are sprites that can move, be destroyed, and collide with other objects in the game.
 */
public class Coin extends Sprite implements Movable, Destroyable, Collidable {

  private PImage image;

  private PImage[] animations;


  private int index;
  private float x;
  private float y;
  private float speed;
  private Window window;

  private float height;

  private float width;


  /**
   * Constructor for the Coin object in the game.
   * @param position the position of the coin in the game world.
   * @param direction the direction in which the coin moves.
   * @param animations an array of PImages representing the animations of the coin.
   * @param speed the speed at which the coin moves.
   */
  public Coin(PVector position, PVector direction,PImage[] animations, float speed) {
    super(position, direction);
    this.image = image;
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.window = Window.getInstance();
    this.index = (int) window.random(0,6);
    this.animations = animations;
    this.height = animations[0].width / 50f;
    this.width = animations[0].width / 50f;
  }

  public Coin(PVector position, PVector direction,float speed) {
    super(position, direction);
    this.x = x;
    this.y = y;
    this.speed = speed;
  }

  /**
   * Sets up animations for the coin object.
   */
  private void setupCoinAnimations(){
    animations = new PImage[6];
    for(int i = 1; i <= 6; i++){
      animations[i - 1] = window.loadImage("images/rocket_man_coins/star coin rotate " + i + ".png");
    }
  }
  public void update(float speed) {
    x -= speed;
  }

  /**
   * Set the position of the object.
   */
  @Override
  public void setPosition(PVector position) {
      this.position = position;
  }

  /**
   * Returns the position of the object.
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
    position.add(-1 * speed,0);
  }

  /**
   * Returns the speed of the object.
   * @return the speed of the object
   */
  @Override
  public float getSpeed() {
    return speed;
  }
  public PVector getDirection() {
    return direction;
  }
  /**
   * Set the speed of the object.
   */
  @Override
  public void setSpeed(float speed) {
    this.speed = speed;

  }

  /**
   * Set the direction of the object.
   */
  @Override
  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  /**
   * Draws the animated object on the screen with its current position and animation frame.
   */
  public void draw() {

    window.image(animations[index % animations.length], position.x, position.y, animations[0].width / 50f, animations[0].height / 50f);
    animate();
    window.stroke(0);        // set the stroke color to black
    window.fill(255, 0, 0);  // set the fill color to red
    window.ellipse(position.x,position.y, 4, 4);
  }

  private void animate(){
    if(window.frameCount % 6 == 0){
      this.index += this.speed;
    }
  }

  /**
   * Determines whether the player collides with the current object.
   * @param player The player object to check for collision with.
   * @return True if the player collides with the object, false otherwise.
   */
  @Override
  public boolean collided(Player player) {
    float yDistance = this.position.y - player.position.y;
    float xDistance = this.position.x - player.position.x;
    float xDistanceOffset = player.getWidth()* .3f;
    float yDistanceOffset = player.getHeight()* .3f;
    return abs(yDistance) < player.getHeight() - xDistanceOffset && abs(xDistance) < player.getWidth() - yDistanceOffset;
//    float xCoin = this.position.x;
//    float yCoin = this.position.y;
//    float xPositionOfPlayer = player.getPosition().x + player.getImage().width / 10f;
//    float yPositionOfPlayer = player.getPosition().y + player.getImage().width / 10f;
//    return xCoin < xPositionOfPlayer && xCoin > player.getPosition().x
//            && yCoin < yPositionOfPlayer && yCoin >
//            player.getPosition().y;

  }

  /**
   * Returns the height of the object.
   * @return the height of the object
   */
  public float getHeight() {
    return height;
  }

  /**
   * Returns the width of the object.
   * @return the width of the object
   */
  public float getWidth() {
    return width;
  }


  /**
   * Returns the coin is destroyed or not.
   * @return true is the coin is destroyed.
   */
  @Override
  public Boolean isDestroyed() {
    return null;
  }

  @Override
  public void destroy() {

  }

  @Override
  public void onDestroy() {

  }


}