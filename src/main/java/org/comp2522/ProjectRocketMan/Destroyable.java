package org.comp2522.ProjectRocketMan;


/**
 * The Destroyable interface defines the necessary methods
 * and properties for objects that can be moved around
 * the screen.
 */
public interface Destroyable {


  /**
   * A method that checks whether the destroyable object has been destroyed.
   *
   * @return true if the object has been destroyed, false otherwise.
   */
  Boolean isDestroyed();


  /**
   * A method that destroys the object and removes it from the screen.
   */
  void destroy();


  /**
   * A method that does something when the object is destroyed.
   */
  void onDestroy();

}
