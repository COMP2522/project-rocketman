package org.comp2522.ProjectRocketMan;


import processing.core.PImage;
import processing.core.PVector;

import static processing.core.PApplet.radians;

public class Rocket extends Sprite implements Movable, Destroyable{

  String shoot;

  PImage image;

  Window_temp window;

  public Rocket(PVector position, PVector direction, PImage image, Window_temp window){
    super(position, direction);
    this.image = image;
    this.window = window;

  }




  public void shoot(){
    //pew pew
  }

  public void speed(){
    //zoooming
  }


  public void draw(){
    float angle = PVector.angleBetween(position,new PVector(0, 1));
    window.rotate(radians(angle));
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

  }

  @Override
  public PVector getPosition() {
    return null;
  }

  @Override
  public void move() {

  }

  @Override
  public double getSpeed() {
    return 0;
  }

  @Override
  public void setSpeed(double speed) {

  }

  @Override
  public void setDirection(PVector direction) {

  }
}
