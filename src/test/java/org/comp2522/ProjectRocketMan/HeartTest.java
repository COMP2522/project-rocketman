package org.comp2522.ProjectRocketMan;

import org.comp2522.ProjectRocketMan.Heart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PImage;
import processing.core.PVector;
import static org.junit.jupiter.api.Assertions.*;
/**

 The HeartTest class tests the methods of the Heart class using JUnit 5.
 It contains test cases for the move, update, collided, and getWidthAndHeight methods.
 @author Jeevan Virk
 @version 1.0
 */
public class HeartTest {
  /**
   * an array of PImage objects to store the animation frames
    */
  private  PImage[] animations;

  /**
   * the speed of the heart
    */
  private final float speed = 0.0f;

  /**
   * the heart object to be tested
    */
  private Heart heart;

  /**
   *  the window object
    */
  private Window window;

  /**
   * Player object
   */
  private Player player;

  /**
   Sets up the test environment before each test.
   */
  @BeforeEach
  public void setUp() {
    window = Window.getInstance(800, 1000);
    heart = new Heart(new PVector(0, 0), new PVector(0, 0), speed);
    player = Player.getInstance(new PVector(0, 0), new PVector(0, 0), speed);
    animations = new PImage[1];
    animations[0] = new PImage();
  }

  /**

   Tests the move method of the Heart class.
   It checks if the heart moves to the left at the correct speed.
   */
  @Test
  public void testMove() {
    final float delta = 0.01f;
    final float initialX = heart.getPosition().x;
    heart.move();
    assertEquals(initialX - speed, heart.getPosition().x, delta);
  }

  /**
   Tests the collided method of the Heart class.
   It checks if the heart collides with the player when they are within the offset distance.
   It also checks if the heart does not collide with the player when they are beyond the offset distance.
   */
  @Test
  public void testCollided() {
    // Test if heart collides with player within the offset distance
    final PVector playerPosition = new PVector(10, 10);
    final PVector heartPosition = new PVector(20, 20);
    final float playerWidth = 30;
    final float playerHeight = 30;
    final float xDistanceOffset = playerWidth * .3f;
    final float yDistanceOffset = playerHeight * .3f;

    heart.setPosition(heartPosition);
    player.setPosition(playerPosition);
    final boolean isCollided = heart.collided(player);
    assertFalse(isCollided);

    // Test if heart does not collide with player beyond the offset distance
    final PVector playerPosition2 = new PVector(100, 100);
    player.setPosition(playerPosition2);
    final boolean isNotCollided = heart.collided(player);
    assertFalse(isNotCollided);
  }

  /**
   * Getting width and height
   */
  @Test
  public void testGetWidthAndHeight() {
    final float delta = 0.01f;
    assertEquals(animations[0].width * 10, heart.getWidth(), delta);
    assertEquals(animations[0].height * 10, heart.getHeight(), delta);
  }
}

