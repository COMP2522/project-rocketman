package org.comp2522.ProjectRocketMan;
import com.mongodb.client.model.mql.MqlNumber;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

import static processing.core.PApplet.abs;

public class Coin extends Sprite implements Movable, Destroyable, Collidable {

  private PImage image;

  private PImage[] animations;


  private int index;
  private float x;
  private float y;
  private float speed;
  private Window window;

  private float height;

  private float width;



  public Coin(PVector position, PVector direction,PImage[] animations, float speed) {
    super(position, direction);
    this.image = image;
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.window = Window.getInstance();
    this.index = (int) window.random(0,6);
    this.animations = animations;
    this.height = animations[0].width / 50f;
    this.width = animations[0].width / 50f;
  }

  public Coin(PVector position, PVector direction,float speed) {
    super(position, direction);
    this.x = x;
    this.y = y;
    this.speed = speed;
  }
  private void setupCoinAnimations(){
    animations = new PImage[6];
    for(int i = 1; i <= 6; i++){
      animations[i - 1] = window.loadImage("images/rocket_man_coins/star coin rotate " + i + ".png");
    }
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
    this.speed = speed;

  }

  @Override
  public void setDirection(PVector direction) {

  }

  public void draw() {

    window.image(animations[index % animations.length], position.x, position.y, animations[0].width / 50f, animations[0].height / 50f);
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
//    float xCoin = this.position.x;
//    float yCoin = this.position.y;
//    float xPositionOfPlayer = player.getPosition().x + player.getImage().width / 10f;
//    float yPositionOfPlayer = player.getPosition().y + player.getImage().width / 10f;
//    return xCoin < xPositionOfPlayer && xCoin > player.getPosition().x
//            && yCoin < yPositionOfPlayer && yCoin >
//            player.getPosition().y;

  }

  public float getHeight() {
    return height;
  }

  public float getWidth() {
    return width;
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