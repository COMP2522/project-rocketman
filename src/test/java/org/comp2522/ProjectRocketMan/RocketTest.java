package org.comp2522.ProjectRocketMan;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import processing.core.PImage;
import processing.core.PVector;
import java.util.ArrayList;

public class RocketTest {

  @Test
  public void testConstructor() {
    PVector position = new PVector(0, 0);
    PVector direction = new PVector(1, 0);
    float speed = 10;
    Rocket rocket = new Rocket(position, direction, speed);

    assertEquals(position, rocket.getPosition());
    //assertEquals(direction, rocket.getDirection());
    assertEquals(speed, rocket.getSpeed(), 0.1);
  }

  @Test
  public void testDraw() {
    PVector position = new PVector(0, 0);
    PVector direction = new PVector(1, 0);
    float speed = 10;
    PImage image = new PImage();
    Rocket rocket = new Rocket(position, direction, image, speed);

    // TODO: How to test the draw method?
  }

  @Test
  public void testSetPosition() {
    PVector position = new PVector(0, 0);
    PVector newPosition = new PVector(10, 20);
    PVector direction = new PVector(1, 0);
    float speed = 10;
    Rocket rocket = new Rocket(position, direction, speed);

    rocket.setPosition(newPosition);

    assertEquals(newPosition, rocket.getPosition());
  }

  @Test
  public void testMove() {
    PVector position = new PVector(0, 0);
    PVector direction = new PVector(1, 0);
    float speed = 10;
    Rocket rocket = new Rocket(position, direction, speed);

    rocket.move();

    assertEquals(new PVector(speed, 0), rocket.getPosition());
  }

  @Test
  public void testGetSpeed() {
    PVector position = new PVector(0, 0);
    PVector direction = new PVector(1, 0);
    float speed = 10;
    Rocket rocket = new Rocket(position, direction, speed);

    assertEquals(speed, rocket.getSpeed(), 0.1);
  }

  @Test
  public void testSetSpeed() {
    PVector position = new PVector(0, 0);
    PVector direction = new PVector(1, 0);
    float speed = 10;
    Rocket rocket = new Rocket(position, direction, speed);

    rocket.setSpeed(20);

    assertEquals(20, rocket.getSpeed(), 0.1);
  }

  @Test
  public void testCollided() {
    PVector position1 = new PVector(0, 0);
    PVector position2 = new PVector(10, 10);
    PVector direction = new PVector(1, 0);
    float speed = 10;
    PImage image = new PImage();
    Player player = Player.getInstance(position1, direction, speed);
    Rocket rocket = new Rocket(position2, direction, image, speed);

   // assertTrue(rocket.collided(player));
  }

  @Test
  public void testManageItself() {
    ArrayList<Rocket> rockets = new ArrayList<Rocket>();
    rockets.add(new Rocket(new PVector(0, 0), new PVector(1, 0), 10));
    rockets.add(new Rocket(new PVector(10, 10), new PVector(-1, 0), 20));


  }
}