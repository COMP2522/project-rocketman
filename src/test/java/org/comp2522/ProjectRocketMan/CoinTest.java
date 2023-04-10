package org.comp2522.ProjectRocketMan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import java.util.ArrayList;
/**

  The CoinTest class contains unit tests for the Coin class.
  It tests the functionality of the Coin class's constructor, move method, and collided method.
  @author Jeevan Virk
  @version 1.0
*/
public class CoinTest {
  /**
   * the coin object
   */
  private Coin coin;
  /**

   Sets up the initial state for each test method by creating a new Coin object with a position,
   direction, and speed.
   */
  @BeforeEach
  public void setUp() {
    PVector position = new PVector(100, 200);
    PVector direction = new PVector(1, 0);
    float speed = 2;
    coin = new Coin(position, direction, speed);
  }
  /**

   Tests the constructor of the Coin class by checking that the position, direction, and speed are
   set correctly.
   */
  @Test
  public void testConstructor() {
    PVector position = coin.getPosition();
    PVector direction = coin.getDirection();
    float speed = coin.getSpeed();
    Assertions.assertEquals(100, position.x);
    Assertions.assertEquals(200, position.y);
    Assertions.assertEquals(1, direction.x);
    Assertions.assertEquals(0, direction.y);
    Assertions.assertEquals(2, speed);
    //Assertions.assertNotNull(coin.getAnimations());
  }
  /**

   Tests the move method of the Coin class by checking that the coin's position changes correctly.
   */
  @Test
  public void testMove() {
    coin.move();
    PVector position = coin.getPosition();
    Assertions.assertEquals(98, position.x);
    Assertions.assertEquals(200, position.y);
  }
  /**

   Tests the collided method of the Coin class by checking that it returns false when a player is
   not colliding with the coin, and false when a player is colliding with the coin.
   */
  @Test
  public void testCollided() {
    Player player = new Player(new PVector(90, 200), new PVector(1, 0), 1 );
    boolean collided = coin.collided(player);
    Assertions.assertFalse(collided);

    player = new Player(new PVector(90, 200), new PVector(1, 0),  1);
    collided = coin.collided(player);
    Assertions.assertFalse(collided);

    player = new Player(new PVector(50, 200), new PVector(1, 0),  1);
    collided = coin.collided(player);
    Assertions.assertFalse(collided);

    player = new Player(new PVector(110, 200), new PVector(1, 0), 1);
    collided = coin.collided(player);
    Assertions.assertFalse(collided);

    player = new Player(new PVector(100, 150), new PVector(1, 0), 1);
    collided = coin.collided(player);
    Assertions.assertFalse(collided);

    player = new Player(new PVector(100, 250), new PVector(1, 0), 1);
    collided = coin.collided(player);
    Assertions.assertFalse(collided);
  }
}
