package org.comp2522.ProjectRocketMan;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;
import static processing.awt.ShimAWT.loadImage;

public class ZapperTest {
  private Zapper zapper;
  private PApplet parent;
  private PImage image;

  @Before
  public void setUp() {
    parent = new PApplet();
    parent.sketchPath("./src/main/resources");
    image = parent.loadImage(getClass().getResource("zapper.png").getPath());
    zapper = new Zapper(image, 100, 100, 50, 50, true);
  }



  @Test
  public void testUpdate() {
    PApplet parent = new PApplet();
    parent.sketchPath("./src/main/resources");
    PImage image = parent.loadImage("zapper.png");
    Zapper zapper = new Zapper(image, 100, 100, 50, 50, true);
    zapper.update(1.0f);
    float expected = 99.0f;
    float actual = zapper.getPosition();
    assertEquals(expected, actual, 0.001f);
    }


  @Test
  public void testGetPosition() {
    PApplet parent = new PApplet();
    Zapper zapper = new Zapper(image, 100, 100, 50, 50, true);
    float expected = 100.0f;
    float actual = zapper.getPosition();
    assertEquals(expected, actual, 0.001f);
  }
  @Test
  public void testConstructor() {
    PApplet parent = new PApplet();
   // PImage image = parent.loadImage("zapper.png");
    Zapper zapper = new Zapper(image, 100, 100, 50, 50, true);
    assertNotNull(zapper);
    assertEquals(100, zapper.getPosition(), 0.001f);
  }



}

