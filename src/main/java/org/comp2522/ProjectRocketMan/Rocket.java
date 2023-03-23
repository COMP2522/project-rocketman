package org.comp2522.ProjectRocketMan;


import processing.core.PImage;
import processing.core.PVector;

import static processing.core.PApplet.radians;

public class Rocket extends Sprite implements Movable, Destroyable{

  String shoot;

  PImage image;

  Window window;

  float speed;

  public Rocket(PVector position, PVector direction, PImage image, Window window, float speed){
    super(position, direction);
    this.image = image;
    this.window = window;
    this.speed = speed;
  }




  public void shoot(){
    //pew pew
  }

  public void speed(){
    //zoooming
  }


  public void draw(){
    float angle = PVector.angleBetween(position,new PVector(0, 1));
//    window.rotate(radians(angle));
    window.image(image,position.x, position.y, image.height / 4, image.width / 4);

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
    PVector temp = getPosition();
    temp.add(getSpeed(), getSpeed());
    setPosition(temp);

  }

  @Override
  public float getSpeed() {
    return speed;
  }

  @Override
  public void setSpeed(float speed) {
    this.speed = speed;

  }

  @Override
  public void setDirection(PVector direction) {

  }
}
