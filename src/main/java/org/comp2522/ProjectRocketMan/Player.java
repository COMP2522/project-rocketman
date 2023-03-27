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

  private float acceleration = 1.5f;

  private float maxSpeed = -7f;

  private float gravity = 0.2f;

  private static Player player;

  private int score;

  private float width;

  private float height;


  private int numberOfCoinsCollected;


  private int lives;

  private int hearts;



  private Player(PVector position, PVector direction, PImage image, float speed) {
    super(position, direction);
    this.image = image;
    this.window = Window.getInstance();
    this.lives = 1;
    this.width = image.height / 10f;
    this.height = image.height / 10f;
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

  public int getNumberOfCoinsCollected() {
    return numberOfCoinsCollected;
  }

  public void setNumberOfCoinsCollected(int numberOfCoinsCollected) {
    this.numberOfCoinsCollected = numberOfCoinsCollected;
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
    if(keyCode == UP && position.y > image.height/10f){
      setSpeed(getSpeed() * 0.8f - getAcceleration());
      if(getSpeed() < maxSpeed){
        setSpeed(maxSpeed);
      }
    } else {
      if(keyCode == UP && position.y < image.height/20f) { //put the logic of movement in move method
        if(speed != 0){
          speed = 0;
        }

      }
    }
  }

  public int getLives() {
    return lives;
  }

  public void setLives(int lives) {
    this.lives = lives;
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

  public int getHearts() {
    return hearts;
  }

  public void setHearts(int hearts) {
    this.hearts = hearts;
  }

  @Override
  public PVector getPosition() {
    return position;
  }

  @Override
  public void move() {
    PVector temp = new PVector(getPosition().x, getPosition().y);
    float bottomLimit = window.height - height - 90; // set bottom limit to 50 pixels above the bottom of the window
    if (temp.y + getSpeed() >= image.height/10f && temp.y + getSpeed() <= bottomLimit) {
      temp.add(0, getSpeed());
      setPosition(temp);
    }
    if (position.y + getSpeed() >= bottomLimit && speed != 0) {
      this.speed = 0;
    } else {
      if (position.y + getSpeed() < bottomLimit) {
        if (getSpeed() > -(maxSpeed + 2)) {
          setSpeed(-maxSpeed);
        } else {
          setSpeed(getSpeed() + getGravity());
        }
      }
    }


  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  @Override
  void draw() {

    float angle = PVector.angleBetween(position,new PVector(0, 1));
//    window.rotate(radians(angle));
    window.image(this.image,position.x, position.y, image.height / 10f, image.width / 10f);
    window.stroke(0);        // set the stroke color to black
    window.fill(255, 0, 0);  // set the fill color to red
    window.ellipse(position.x,position.y, 4, 4);
  }


  public float getWidth() {
    return width;
  }

  public void setWidth(float width) {
    this.width = width;
  }

  public float getHeight() {
    return height;
  }

  public void setHeight(float height) {
    this.height = height;
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