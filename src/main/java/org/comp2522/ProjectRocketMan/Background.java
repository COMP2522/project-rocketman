package org.comp2522.ProjectRocketMan;

package org.comp2522.ProjectRocketMan;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public class Background extends Sprite implements Movable {
  private PImage image;

  private float x;
  private float y;
  private float speed;
  private ArrayList<Heart> coins;
  private ArrayList<Zapper> zappers;
  private Window window;

  private float scrollSpeed = 1.5f;

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

  @Override
  public void setPosition(PVector position) {
    this.position = position;
  }

  @Override
  public PVector getPosition() {
    return position;
  }

  @Override
  public void move() {
    position.add(-1 * speed,0);
  }

  @Override
  public float getSpeed() {
    return speed;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

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

  public ArrayList<Float> getZapperPositions() {
    ArrayList<Float> positions = new ArrayList<>();
    for (Zapper zapper : zappers) {
//      positions.add(zapper.getPosition());
    }
    return positions;
  }

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