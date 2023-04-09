package org.comp2522.ProjectRocketMan;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.sound.SoundFile;

/**
 * Runs the applet.
 *
 * @author Jeevanjot Virk
 * @version 1.0.0
 */
public class Window extends PApplet {

  /**
   * Static variable to store the instance of the Window.
   */
  static Window window;

  /**
   * Height of the Window.
   */
  private final int heightOfWindow;

  /**
   * Width of the Window.
   */
  private final int widthOfWindow;

  /**
   * Background music.
   */
  SoundFile gameBackground;

  /**
   * Background music for menu.
   */
  SoundFile menuBackground;

  /**
   * Holds the manager class instance.
   */
  GameManager manager;

  /**
   * This stores the instance of the player.
   */
  Player player;

  /**
   * Constructor for the Window class.
   *
   * @param height Height of the Window.
   * @param width  Width of the Window.
   */
  private Window(int height, int width) {
    this.heightOfWindow = height;
    this.widthOfWindow = width;
  }

  /**
   * Get a window instance.
   *
   * @param height Height of the Window.
   * @param width  Width of the Window.
   * @return Window instance
   */
  public static Window getInstance(int height, int width) {
    if (window == null) {
      return window = new Window(height, width);
    }
    return window;
  }

  /**
   * Get a window instance.
   *
   * @return Window instance.
   */
  public static Window getInstance() {
    return window;
  }

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(widthOfWindow, heightOfWindow);
  }

  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
    manager.init();
    player = Player.getInstance();
    gameBackground = new SoundFile(this, "music/background_babbu.mp3");
    menuBackground = new SoundFile(this, "music/background.mp3");
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    manager.manageTheGame();
    int gameState = manager.getGameState();
    switch (gameState) {
      case 0, 3, 4 -> {
        if (gameBackground.isPlaying()) {
          gameBackground.stop();
        }
        if (!menuBackground.isPlaying()) {
          menuBackground.play();
        }
      }
      case 1 -> {
        if (menuBackground.isPlaying()) {
          menuBackground.stop();
        }
        if (!gameBackground.isPlaying()) {
          gameBackground.play();
        }
      }
      case 2 -> {
        if (gameBackground.isPlaying()) {
          gameBackground.pause();
        }
        if (!menuBackground.isPlaying()) {
          menuBackground.play();
        }
      }
      default -> {
        if (gameBackground.isPlaying()) {
          gameBackground.stop();
        }
        if (menuBackground.isPlaying()) {
          menuBackground.stop();
        }
      }
    }
  }

  /**
   * Mouse clicked event handler.
   */
  @Override
  public void mouseClicked() {
    manager.mouseEvents();
  }

  /**
   * Key pressed event handler.
   *
   * @param event key events
   */
  public void keyPressed(KeyEvent event) {
    manager.keyEvents(event);
  }

  /**
   * Start window method.
   *
   * @param manager GameManager
   */
  public void startWindow(GameManager manager) {
    this.manager = manager;
    String[] appletArgs = new String[]{"eatBubbles"};
    PApplet.runSketch(appletArgs, this);
  }
}