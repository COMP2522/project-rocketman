package org.comp2522.ProjectRocketMan;
import processing.core.PImage;
import processing.core.PVector;

import static processing.core.PApplet.abs;

public class Heart extends Sprite implements Collideable {

  private PImage image;

  private PImage[] animations;

  private int index;
  private float speed;
  private Window window;

  private float height;

  private float width;



  public Heart(PVector position, PVector direction, PImage[] animations, float speed) {
    super(position, direction);
    this.speed = speed;
    this.window = Window.getInstance();
    this.index = (int) window.random(0,6);
    this.animations = animations;
    this.height = animations[0].width * 10;
    this.width = animations[0].width * 10;

  }
  public Heart(PVector position, PVector direction, float speed) {
    super(position, direction);
    this.speed = speed;
  }

  @Override
  public void setPosition(PVector position) {
    this.position = position;

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
    this.speed = speed;

  }

  public void draw() {
    window.image(animations[index % animations.length], position.x, position.y, animations[0].width / 20f , animations[0].height / 20f);
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

  @Override
  public boolean collided(Player player) {
    float yDistance = this.position.y - player.position.y;
    float xDistance = this.position.x - player.position.x;
    float xDistanceOffset = player.getWidth()* .3f;
    float yDistanceOffset = player.getHeight()* .3f;
    return abs(yDistance) < player.getHeight() - xDistanceOffset && abs(xDistance) < player.getWidth() - yDistanceOffset;
  }

  public float getHeight() {
    return height;
  }

  public float getWidth() {
    return width;
  }


}