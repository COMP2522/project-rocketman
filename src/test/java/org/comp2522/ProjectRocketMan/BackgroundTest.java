package org.comp2522.ProjectRocketMan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;

/**

 The BackgroundTest class tests the methods of the Background class.
 It tests the functionality of the setSpeed() and move() methods.
 */
public class BackgroundTest {

  /**
   *  an instance of the Background class
   */
  private Background background;

  /**
   * a PImage for the background image
   */
  private PImage image;
  /**
   * the speed of the background
   */
  private float speed = 2.0f;

  /**
   * the position of the background
   */
  private PVector position;

  /**
   * the direction of the background
   */
  private PVector direction;

  /**

   Sets up the test fixture.
   This method is called before each test.
   */
  @BeforeEach
  public void setUp() {
    PApplet applet = new PApplet();
    //image = applet.loadImage("background.png");
    position = new PVector(0, 0);
    direction = new PVector(0, 0);
    background = new Background(speed, position, direction);
  }

  /**

   Tests the setSpeed() method of the Background class.
   It sets a new speed value for the background and checks if the new value is returned correctly.
   */
  @Test
  public void testSetSpeed() {
    float newSpeed = 3.0f;
    background.setSpeed(newSpeed);
    assertEquals(newSpeed, background.getSpeed());
  }

  /**

   Tests the move() method of the Background class.
   It moves the background and checks if the position of the background has changed correctly.
   */
  @Test
  public void testMove() {
    float initialX = background.getPosition().x;
    background.move();
    assertEquals(initialX - speed, background.getPosition().x);
  }

}

