package org.comp2522.ProjectRocketMan;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;
import static processing.core.PConstants.UP;

class PlayerTest {

  private Player player;
  private PImage image;
  private PVector position;
  private PVector direction;
  private float speed;

  @BeforeEach
  void setUp() {
    image = new PImage();
    position = new PVector(0, 0);
    direction = new PVector(1, 0);
    speed = 0;
    player = Player.getInstance(position, direction, image, speed);
  }

  @Test
  void getInstanceReturnsSameObject() {
    Player player2 = Player.getInstance(position, direction, image, speed);
    assertSame(player, player2);
  }
/**
  @Test
  void moveOnKeyEventMovesPlayer() {
    float initialY = player.getPosition().y;
    player.setSpeed(1);
    player.moveOnKeyEvent();
    float newY = player.getPosition().y;
    assertTrue(newY < initialY);
  }
**/
  /**@Test
  void keyPressedIncreasesSpeed() {
    float initialSpeed = player.getSpeed();
    player.keyPressed(new KeyEvent(null, ' ', '\0', KeyEvent.VK_UP, 0));
    float newSpeed = player.getSpeed();
    assertTrue(newSpeed > initialSpeed);
  }**/

  @Test
  void getGravityReturnsGravity() {
    float gravity = 1.0f;
    player.setGravity(gravity);
    assertEquals(gravity, player.getGravity());
  }

  @Test
  void getNumberOfCoinsCollectedReturnsCoins() {
    int coins = 5;
    player.setNumberOfCoinsCollected(coins);
    assertEquals(coins, player.getNumberOfCoinsCollected());
  }

  @Test
  void getAccelerationReturnsAcceleration() {
    float acceleration = 1.5f;
    player.setAcceleration(acceleration);
    assertEquals(acceleration, player.getAcceleration());
  }

  @Test
  void getMaxSpeedReturnsMaxSpeed() {
    float maxSpeed = -7f;
    player.setMaxSpeed(maxSpeed);
    assertEquals(maxSpeed, player.getMaxSpeed());
  }
/**
  @Test
  void getImageReturnsImage() {
    assertEquals(image, player.getImage());
  }
**/
  @Test
  void setImageSetsImage() {
    PImage newImage = new PImage();
    player.setImage(newImage);
    assertEquals(newImage, player.getImage());
  }
}
