package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;
import static processing.core.PConstants.UP;

public class Player extends Sprite implements Movable, Destroyable{
  private PImage image;

  private Window window;


  /**
   * The speed of the player. The Player starts with the 0 speed.
   */
  private float speed = 0;

  private float acceleration = 1f;

  private float maxSpeed = -7f;

  private float gravity = 0.3f;

  private static Player player;




  private Player(PVector position, PVector direction, PImage image, float speed) {
    super(position, direction);
    this.image = image;
    this.window = Window.getInstance();
  }

  public static Player getInstance(PVector position, PVector direction, PImage image, float speed) {
    if (player == null) {
      player = new Player(position, direction, image, speed);
    }
    return player;
  }

  public float getGravity() {
    return gravity;
  }

  public void setGravity(float gravity) {
    this.gravity = gravity;
  }

  public float getAcceleration() {
    return acceleration;
  }

  public void setAcceleration(float acceleration) {
    this.acceleration = acceleration;
  }

  public float getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(float maxSpeed) {
    this.maxSpeed = maxSpeed;
  }

  public static Player getInstance(){
    return player;
  }

  private void moveOnKeyEvent(){
    PVector temp = this.getPosition();
    temp.add(0, this.getSpeed());
    this.setPosition(temp);
    setSpeed(getSpeed() - getGravity());
  }


  public PImage getImage() {
    return image;
  }

  public void setImage(PImage image) {
    this.image = image;
  }
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    System.out.println("height: " + image.height/20f);
    if(keyCode == UP && position.y > image.height/10f){
      System.out.println(position.y);
      setSpeed(getSpeed() - getAcceleration());
      if(getSpeed() < maxSpeed){
        setSpeed(maxSpeed);
      }
    } else {
      if(keyCode == UP && position.y < image.height/20f) {
        if(speed != 0){
          speed = 0;
        }

      }
    }
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

    PVector temp = new PVector(getPosition().x, getPosition().y);

    if(temp.y + getSpeed() > image.height/10f && temp.y + getSpeed() < window.height - image.height/10f ) {

      temp.add(0, getSpeed());
      setPosition(temp);
    }
    if(position.y + getSpeed() > (window.height -image.height / 10f ) && speed != 0){
      this.speed = 0;
    } else {
      if(position.y + getSpeed() < (window.height - image.height / 10f)){
        if(getSpeed() > -maxSpeed){
          setSpeed(-maxSpeed);
        } else{
          setSpeed(getSpeed() + getGravity());
        }

      }

    }

  }


  @Override
  void draw() {
    System.out.println("Speed: " + speed + "\n"
        + "Y position: " + position.y + "\n");
    float angle = PVector.angleBetween(position,new PVector(0, 1));
//    window.rotate(radians(angle));
    window.image(this.image,position.x, position.y, image.height / 10f, image.width / 10f);
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