package org.comp2522.ProjectRocketMan;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * The Background class represents a scrolling background in a game.
 * It is used to display images that move behind the game objects.
 */
public class Background extends Sprite implements Movable {
  private PImage image;

  private float x;
  private float y;
  private float speed;
  private ArrayList<Heart> coins;
  private ArrayList<Zapper> zappers;
  private Window window;

  private float scrollSpeed = 1.5f;

  /**
   * Constructor of Background class.
   @param image the image used for the background
   @param speed the speed at which the background moves
   @param position the starting position of the background
   @param direction the direction in which the background moves
   */

  public Background(PImage image, float speed, PVector position, PVector direction) {
    super(position,direction);
    this.window = Window.getInstance();
    this.image = image;
    this.speed = speed;
    this.x = 0;
    this.y = 0;
    this.coins = new ArrayList<>();
    this.zappers = new ArrayList<>();
  }

  /**
   * Sets the position of this object to the specified PVector position.
   * @param position the PVector position to set
   */
  @Override
  public void setPosition(PVector position) {
    this.position = position;
  }

  /**
   * Returns the position of an object as a PVector.
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
   * @return the speed of the object as a float.
   */
  @Override
  public float getSpeed() {
    return speed;
  }

  /**
   * Sets the speed of this object to the specified number.
   * @param speed the speed to set
   */
  public void setSpeed(float speed) {
    this.speed = speed;
  }

  /**
   * Sets the direction of this object to the specified PVector.
   * @param direction the direction to set
   */
  @Override
  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  public void setCoinNum(int numCoins) {
    // TODO: Implement logic to generate `numCoins` coins in random positions on the screen
  }

  public void setZapperNum(int numZappers) {
    // TODO: Implement logic to generate `numZappers` zappers in random positions on the screen
  }

  /**
   * Updates the game state by moving the background and updating the positions of the coins and zappers.
   */
  public void update() {
    // Move the background
    x -= speed;
    if (x <= -image.width) {
      x += image.width;
    }

    // Update the positions of the coins and zappers
    for (Heart coin : coins) {
      coin.update(speed);
    }
    for (Zapper zapper : zappers) {
//      zapper.update(speed);
    }
  }

//  public ArrayList<Float> getCoinPositions() {
//    ArrayList<Float> positions = new ArrayList<>();
//    for (Coin coin : coins) {
//      positions.add(coin.getPosition());
//    }
//    return positions;
//  }

  /**
   * Returns a list of positions of all the zappers.
   * @return an ArrayList of type Float containing the positions of all the zappers
   */
  public ArrayList<Float> getZapperPositions() {
    ArrayList<Float> positions = new ArrayList<>();
    for (Zapper zapper : zappers) {
//      positions.add(zapper.getPosition());
    }
    return positions;
  }

  /**
   * Draws an image on a window with a scrolling effect.
   *
   * The image is continuously drawn and scrolled across the window.
   */
  public void draw() {

    position.y = speed;
    position.x -= speed;
//    position.y = scrollSpeed * 16;


    int offsetX = (int) (position.x % image.width - image.width);
    int offsetY = (int) (position.y % image.height - image.height);


    for (int x = offsetX; x < image.width; x += image.width) {
      for (int y = offsetY; y < image.height; y += image.height) {
        window.image(image, x, y);
      }

    }
  }




  // Draw the background image
//    window.image(image, position.x, position.y);
//    if (x < 0) {
//      parent.image(image, x + image.width, y);
////    }
//
//    // Draw the coins and zappers
//    for (Coin coin : coins) {
//      coin.draw(window);
//    }
//    for (Zapper zapper : zappers) {
//      zapper.draw(window);
//    }
}