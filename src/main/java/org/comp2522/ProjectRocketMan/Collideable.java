package org.comp2522.ProjectRocketMan;


/**
 * Defines all the methods a colliable object will implement.
 */
public interface Collideable {

  /**
   * Checks if the class that implements collidable has collided with Player.
   *
   * @param player The Player that object might collide with.
   * @return true if collided.
   */
  boolean collided(Player player);

}
