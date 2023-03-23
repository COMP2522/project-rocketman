package org.comp2522.ProjectRocketMan;
import processing.core.PApplet;
import processing.core.PImage;

public class Coin {
  private PImage image;
  private float x;
  private float y;
  private float size;

  public Coin(PImage image, float x, float y, float size) {
    this.image = image;
    this.x = x;
    this.y = y;
    this.size = size;
  }

  public void update(float speed) {
    x -= speed;
  }

  public float getPosition() {
    return x;
  }

  public void draw(PApplet parent) {
    parent.image(image, x, y, size, size);
  }
}
