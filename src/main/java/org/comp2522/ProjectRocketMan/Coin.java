package org.comp2522.ProjectRocketMan;
import com.mongodb.client.model.mql.MqlNumber;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public class Coin extends Sprite implements Movable, Destroyable, Collidable {

  private PImage image;

  private float x;
  private float y;
  private float speed;
  private Window window;

  public Coin(PVector position, PVector direction,PImage image, float speed) {
    super(position, direction);
    this.image = image;
    this.x = x;
    this.y = y;
    this.window = Window.getInstance();
  }

  public void update(float speed) {
    x -= speed;
  }

  @Override
  public void setPosition(PVector position) {

  }

  public PVector getPosition() {
    return position;
  }

  @Override
  public void move() {
    position.add(-1 * speed,0);
  }

  @Override
  public float getSpeed() {
    return 0;
  }

  @Override
  public void setSpeed(float speed) {

  }

  @Override
  public void setDirection(PVector direction) {

  }

  public void draw() {
    window.image(image, position.x, position.y);
  }

  @Override
  public boolean collided(Player player) {
    return false;
  }

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