package org.comp2522.ProjectRocketMan;

import processing.core.PVector;

/**
 * The abstract class Sprite represents a game sprite with a position and direction in 2D space.
 * it also contains abstract methods related to Sprite like drawable.
 *
 */
public abstract class Sprite {



  private PVector position;

  private PVector direction;

  public Sprite(PVector position, PVector direction) {
    this.direction = direction;
    this.position = position;
  }



  void draw(){}

}