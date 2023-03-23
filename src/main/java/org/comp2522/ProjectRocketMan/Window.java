package org.comp2522.ProjectRocketMan;

import com.mongodb.internal.connection.tlschannel.WouldBlockException;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;

/**
 * Lab-02 starter code.
 * Runs the applet for the Lab-02 bouncing
 * balls starter code.
 * Based on code from Keith Peters demonstrating
 * multiple-object collision.
 *
 * @author paul_bucci
 *
 */
public class Window extends PApplet {

  static Window window;
  ArrayList<Sprite> sprites;

  ArrayList<Rocket> rockets;

  ArrayList<Movable> moveables;

  PImage rocket_image;

  PImage rocket_man_image;
//  ArrayList<Enemy> enemies;
  Player player;
  int numEnemies = 100;

  private int heightOfWindow;

  private int widthOfWindow;
  private int minSize;
  private int maxSize;

//  Wall wall;


  private Window(int height, int width){
    this.heightOfWindow = height;
    this.widthOfWindow = width;
    player = Player.getInstance();
  }


  public static Window getInstance(int height, int width){
    if(window == null){
     return window = new Window(height, width);
    }
  return  window;
  }



  public Window getInstance(){
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
    this.init();
  }

  public void init() {
//    wall = new Wall(new PVector(this.width/3,this.height/8),
//            new PVector(0,1),
//            minSize + 5,
//            2,
//            new Color(0,255,0),
//            this, 350, 200);
    rockets = new ArrayList<Rocket>();
    sprites = new ArrayList<Sprite>();
    moveables = new ArrayList<Movable>();
    rocket_image = loadImage("images/rockect_images/rocket_2.png");
    rocket_man_image = loadImage("images/rocket_man_images/My project.png");
    player = Player.getInstance(new PVector(200,500),
            new PVector(random(-1, 1), random(-1,1)), rocket_man_image, this, -2);
    for(int i = 0; i < 10; i++){
      rockets.add(new Rocket(new PVector(random(0, 1000), random(0,1000)),
              new PVector(random(-1, 1), random(-1,1)), rocket_image, this,random(-10, 10)));
    }
    rockets.add(new Rocket(new PVector(0, 0),
             new PVector(random(-1, 1), random(-1,1)), rocket_image, this,1));
  sprites.addAll(rockets);
  moveables.addAll(rockets);
  sprites.add(player);
  moveables.add(player);

//    for (int i = 0; i < numEnemies; i++) {
//      enemies.add(new Enemy(
//              new PVector(random(0, this.width), random(0, this.height)),
//              new PVector(random(-1, 1), random(-1,1)),
//              random(minSize, maxSize),
//          \    random(0,2),
//              new Color(255, 0, 0),
//              this,
//              wall));
//    }
//    sprites.addAll(enemies);
//    sprites.add(player);
  }



  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */

  public void draw() {


    background(0);
    for (Sprite sprite : sprites) {
//      sprite.update();
      sprite.draw();
//      wall.draw();
//      if (wall.collided(sprite)) {
//        sprite.setDirection(sprite.getDirection().mult(-1));
      }
    for(Movable move : moveables){
      move.move();
    }




//
//    }
//    ArrayList<Enemy> toRemove = new ArrayList<Enemy>();
//    for (Enemy enemy : enemies) {
//      if (Sprite.collided(player, enemy)) {
//        toRemove.add(enemy);
//      }
//
//    }
//    for (Enemy enemy : toRemove) {
//
//      if (player.compareTo(enemy) > 0){
//        enemies.remove(enemy);
//        sprites.remove(enemy);
//      } else {
//        init();
//      }
//
//    }

  }

  public void keyPressed(KeyEvent event) {
    player.keyPressed(event);
  }


  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = Window.getInstance(1000,1000);
    PApplet.runSketch(appletArgs, eatBubbles);
  }
}