package org.comp2522.ProjectRocketMan;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.sound.SoundFile;
import processing.event.KeyEvent;

import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Runs the applet.
 * @author JeevanJot Kaur
 *
 */
public class Window extends PApplet {

  /**
   * Background music
   * */
  SoundFile gameBackground;

  /**
   * Background music for menu.
   * */
  SoundFile menuBackground;

  /**
   * Holds the manager class instance.
   */
  GameManager manager;

  /**
   * Static variable to store the instance of the Window.
   */
  static Window window;

  /**
   * A collection for Sprites
   */
  ArrayList<Sprite> sprites;


  /**
   * A collection for rocekts.
   */
  ArrayList<Rocket> rockets;

  /**
   * A collection for Moveble objects
   */
  ArrayList<Movable> moveables;

  /**
   * The image of the rocket.
   */
  PImage rocket_image;

  /**
   * The image of the rocket man
   */
  PImage rocket_man_image;
//  ArrayList<Enemy> enemies;

  /**
   * This stores the instance of the player
   */
  Player player;
  int numEnemies = 100;

  /**
   * Height of the Window.
   */
  private int heightOfWindow;

  /**
   * Width of the Window.
   */
  private int widthOfWindow;
  private int minSize;
  private int maxSize;

//  Wall wall;


  Window(int height, int width){
    this.heightOfWindow = height;
    this.widthOfWindow = width;
    sprites = new ArrayList<Sprite>();
    moveables = new ArrayList<Movable>();
  }


  public static Window getInstance(int height, int width){
    if(window == null){
      return window = new Window(height, width);
    }
    return  window;
  }



  public static Window getInstance(){
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
    manager.init(sprites, moveables);
    player = Player.getInstance();
    gameBackground = new SoundFile(this, "music/background_babbu.mp3");
    menuBackground = new SoundFile(this, "music/background.mp3");
  }

  public void init() {
    manager.init(sprites, moveables);
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
      case 0, 3, 4:
        if (gameBackground.isPlaying()) {
          gameBackground.stop();
        }
        if (!menuBackground.isPlaying()) {
          menuBackground.play();
        }
        break;
      case 1:
        if (menuBackground.isPlaying()) {
          menuBackground.stop();
        }
        if (!gameBackground.isPlaying()) {
          gameBackground.play();
        }
        break;
      case 2:
        if (gameBackground.isPlaying()) {
          gameBackground.pause();
        }
        if (!menuBackground.isPlaying()) {
          menuBackground.play();
        }
        break;
      default:
        if (gameBackground.isPlaying()) {
          gameBackground.stop();
        }
        if (menuBackground.isPlaying()) {
          menuBackground.stop();
        }
    }
  }


  @Override
  public void mouseClicked() {
    manager.mouseEvents();
  }

  public void keyPressed(KeyEvent event) {
    manager.keyEvents(event);
  }

  public void startWindow(GameManager manager){
    this.manager = manager;
    String[] appletArgs = new String[]{"eatBubbles"};
    PApplet.runSketch(appletArgs, this);
  }

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
//    String[] appletArgs = new String[]{"eatBubbles"};
//    Window eatBubbles = Window.getInstance(1000,1000);
//    PApplet.runSketch(appletArgs, eatBubbles);
  }
}