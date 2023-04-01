package org.comp2522.ProjectRocketMan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;


public class GameManagerTest {
  GameManager manager;
  public Player player;
  @BeforeEach
  public void setUp() {
    manager = new GameManager();
    player = Player.getInstance(
        new PVector(5,5),
        new PVector(1,1),
        0
    );
  }

  @Test
  public void testCheckForCollisionsWithRocket() {
    Rocket rocket = new Rocket(
        new PVector(5, 5),
        new PVector(1, 1),
        0);

    Assertions.assertTrue(rocket.collided(player));
  }

  @Test
  public void testNotCheckForCollisionsWithRocket() {
    Rocket rocket = new Rocket(
        new PVector(10, 10),
        new PVector(1, 1),
        0);

    Assertions.assertFalse(rocket.collided(player));
  }
  @Test
  public void testCheckForCollisionsWithCoin() {
    Coin coin = new Coin(
        new PVector(5, 5),
        new PVector(1, 1),
        0
    );
    Assertions.assertTrue(coin.collided(player));
  }
  @Test
  public void testNotCheckForCollisionsWithCoin() {
    Coin coin = new Coin(
        new PVector(15, 15),
        new PVector(1, 1),
        0
    );
    Assertions.assertFalse(coin.collided(player));
  }
  @Test
  public void testCheckForCollisionsWithHeart() {
    Heart heart = new Heart(
        new PVector(5, 5),
        new PVector(1, 1),
        0);

    Assertions.assertTrue(heart.collided(player));

  }

  @Test
  public void testNotCheckForCollisionsWithHeart() {
    Heart heart = new Heart(
        new PVector(55, 55),
        new PVector(1, 1),
        0);

    Assertions.assertFalse(heart.collided(player));
  }
}
