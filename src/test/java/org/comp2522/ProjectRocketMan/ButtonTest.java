package org.comp2522.ProjectRocketMan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;
/**

    This test class tests the Button class which represents a clickable button in a Processing sketch.
    It tests the functionality of the isClicked() method, getLabel() method, and setLabel() method.
    */
public class ButtonTest {

  /**

   The window object on which the tests will be performed.
   */
  private Window window;

  /**

   Initializes the window object before each test.
   */
  @BeforeEach
  public void setUp() {
    window = Window.getInstance(800, 1000);
  }

  /**

   Tests the functionality of the isClicked() method.
   It creates a button object and checks if it is initially not clicked, then simulates a click on the button
   and checks if the button is clicked, and then simulates a click outside the button and checks if the button is
   not clicked.
   */
  @Test
  void testIsClicked() {
    Button button = new Button(new PVector(100, 100), new PVector(50, 30), "Click me!");
    assertFalse(button.isClicked(), "Button should not be clicked initially");

    // Simulate a click on the button
    Window.getInstance().mouseX = 125;
    Window.getInstance().mouseY = 115;
    assertTrue(button.isClicked(), "Button should be clicked");

    // Simulate a click outside the button
    Window.getInstance().mouseX = 75;
    Window.getInstance().mouseY = 85;
    assertFalse(button.isClicked(), "Button should not be clicked");
  }

  /**

   Tests the functionality of the getLabel() method.
   It creates a button object and checks if the label text is correct initially.
   */
  @Test
  void testGetLabel() {
    Button button = new Button(new PVector(100, 100), new PVector(50, 30), "Click me!");
    assertEquals("Click me!", button.getLabel(), "Label should be 'Click me!' initially");
  }

  /**

   Tests the functionality of the setLabel() method.
   It creates a button object, sets a new label text using the setLabel() method and then checks if the label text
   is updated correctly.
   */
  @Test
  void testSetLabel() {
    Button button = new Button(new PVector(100, 100), new PVector(50, 30), "Click me!");
    button.setLabel("Press me!");
    assertEquals("Press me!", button.getLabel(), "Label should be 'Press me!' after calling setLabel()");
  }
}

