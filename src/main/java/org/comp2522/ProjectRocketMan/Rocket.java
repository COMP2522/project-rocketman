package org.comp2522.ProjectRocketMan;


import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

import static processing.core.PApplet.hex;
import static processing.core.PApplet.radians;

public class Rocket extends Sprite implements Movable, Destroyable, Collidable {

  String shoot;

  PImage image;

  Window window;

  float speed;

  public Rocket(PVector position, PVector direction, PImage image, float speed){
    super(position, direction);
    this.image = image;
    this.window = Window.getInstance();
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
//    window.pushMatrix();
//    window.translate(window.width / 2f, window.height / 2f);
//    window.rotate(window.PI/4);
//    window.imageMode(window.CENTER);
//    window.rotate(radians(angle));
    window.image(image,position.x, position.y, image.height / 4, image.width / 4);
//    window.popMatrix();

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
    temp.add(getSpeed(), 0);
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

  public static void manageItself(ArrayList<Rocket> rockets){


  }


  @Override
  public boolean collided(Player player) {
    float xPositionOfRocket = this.position.x;
    float yPositionOfRocket = this.position.y;
    float xPositionOfPlayer = player.getPosition().x + player.getImage().width / 10f;
    float yPositionOfPlayer = player.getPosition().y + player.getImage().width / 10f;
    return xPositionOfRocket < xPositionOfPlayer && xPositionOfRocket > player.getPosition().x
            && yPositionOfRocket < yPositionOfPlayer && yPositionOfRocket >
            player.getPosition().y;

  }
}
