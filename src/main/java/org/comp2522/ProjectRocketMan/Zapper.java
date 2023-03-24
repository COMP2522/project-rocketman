package org.comp2522.ProjectRocketMan;

import processing.core.PApplet;
import processing.core.PImage;

public class Zapper {
  private PImage image;
  private float x;
  private float y;
  private float width;
  private float height;
  private boolean isMovingUp;

  public Zapper(PImage image, float x, float y, float width, float height, boolean isMovingUp) {
    this.image = image;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.isMovingUp = isMovingUp;
  }

  public void update(float speed) {
    x -= speed;
    if (isMovingUp) {
      y -= speed;
    } else {
      y += speed;
    }
  }

  public float getPosition() {
    return x;
  }

  public void draw(PApplet parent) {
    parent.image(image, x, y, width, height);
  }
}