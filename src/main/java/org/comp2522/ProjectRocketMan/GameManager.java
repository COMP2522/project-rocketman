package org.comp2522.ProjectRocketMan;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet.*;

import org.json.simple.JSONObject;
import processing.core.PImage;
import processing.core.PVector;

public class GameManager {
  // responsible for managing the game logic and data.
  // updating the game world
  // managing player input
  // handling collision detection
  // keeping track of the game score
  private Window window;
  private Background background;
  private Player player;
  private List<Rocket> rockets;

  private List<Sprite> sprites;

  private List<Collidable> collidables;

  private List<Movable> moveables;
  private boolean isRunning;
  private boolean isPaused;
  private int currentLevel;
  private int leveUpCoins;
  private int currentSpeed;
  private int rocketNums;
  private int rocketSpeed;
  private int coinsPerWindow;
  private int zapperNums;

  public GameManager() {
    isRunning = false;
    isPaused = false;
    currentLevel = 1;


//    background = new Background();
    rockets = new ArrayList<Rocket>();
  }

  public void start() {
    window = Window.getInstance(600,1000);
    window.startWindow(this);
    isRunning = true;
  }


  public void init(ArrayList<Sprite> sprites, ArrayList<Movable> movables) {

    this.sprites = sprites;
    this.moveables = movables;
    this.collidables = new ArrayList<Collidable>();
    PImage rocket_image = window.loadImage("images/rockect_images/rocket_2.png");
    PImage rocket_man_image = window.loadImage("images/rocket_man_images/My project.png");
    PImage background_images = window.loadImage("images/rocket_man_backgrounds/AIgen.png");
    PImage coin = window.loadImage("images/rocket_man_coins/star coin rotate 1.png");

    background = new Background(background_images,1, new PVector(0,0),
        new PVector(1, 1) );
    System.out.println("inside init");
    player = Player.getInstance(new PVector(window.width * 0.10f,window.height / 2),
        new PVector(1, 1), rocket_man_image, -2);
//    currentLevel = 1;
//    currentSpeed = 3;
//    rocketSpeed = 5;
//    coinsPerWindow = 6;
//    background.setSpeed(currentSpeed);
//    background.setCoinNum(coinsPerWindow);
//    background.setZapperNum(zapperNums);

    rockets = new ArrayList<Rocket>();
//    sprites = new ArrayList<Sprite>();
//    moveables = new ArrayList<Movable>();

    for(int i = 0; i < 2; i++){
      rockets.add(new Rocket(new PVector(window.width, i * 200),
          new PVector(window.random(-1, 1), window.random(-1,1)), rocket_image, window.random(-10, -1)));
    }
    this.sprites.add(background);
    this.moveables.add(background);
    this.sprites.addAll(rockets);
    this.moveables.addAll(rockets);
    this.sprites.add(player);
    this.moveables.add(player);
    this.collidables.addAll(rockets);



//    for (int i = 0; i < rocketNums; i++) {
//      PVector position = 0;
//      PVector direction = 0;
//      PImage image = "";
//      Rocket tempRocket = new Rocket(position, direction, image, window);
//      tempRocket.setSpeed(rocketSpeed);
//      rockets.add(tempRocket);
//    }
  }


  public void manageTheGame(){
    for (Collidable temp : collidables) {
      if (temp.collided(player)){
        System.out.println("Player x: " + player.getPosition().x + " Player x: " + player.getPosition().y + "\n");
        System.out.println("rocket x: " + ((Movable) temp).getPosition().x + " rocket y: " + ((Movable) temp).getPosition().y + "\n");
        if(temp instanceof Rocket){
          window.exit();
        }
      }

    }

  }

  public void gameOver() throws FileNotFoundException {
    isRunning = false;
    saveGameData("gameState.json");
    //close window
  }

//  public int update() {
//    background.update();
//    for (int i = 0; i < rocketNums; i++) {
//      Rocket rocket = rockets.get(i);
//      rocket.update();
//      if (isCollided(player, rocket)) {
//        return 0;// 0 means game over
//      }
//    }
//
//    for (int i = 0; i < zapperNums; i++) {
//      Zapper zapper = background.zappers.get(i);
//      if (player.getPosition.equalsto(zapper.getPosition())) {
//        return 0;
//      }
////      if (isCollided(player, zapper)) {
////        return 0;// 0 means game over
////      }
//    }
//
//    for (int i = 0; i < coinsPerWindow; i++) {
//      Coin coin = background.coins.get(i);
//      if (coin.getPostion().equalsto(player.getPostion())) {
//        //remove this coin; if don't use iterator,there will be problem
//        background.remove();//need discuss
//        player.currCoin++;
//      }
//    }
//
//    //if level up
//    if (player.currCoin >= leveUpCoins) {
//      currentLevel++;
//      background.setSpeed(background.getSpeed() + 1);
//    }
//
//  }
//
//  public boolean isCollided(Player player, Rocket rocket) {
//    return player.getPosition.equalsto(rocket.getPosition());
//    //System.out.println("Handling collision between " + obj1 + " and " + obj2);
//    // Handle collision between two game objects.
//  }

  public void pause() {
    isPaused = true;
    // Display pause menu, etc.
  }

  public void resume() {
    isPaused = false;
  }


  public void saveGameData(String filename) throws FileNotFoundException {
    // creating JSONObject
    JSONObject jo = new JSONObject();

    // putting data to JSONObject
    jo.put("currentLevel", currentLevel);
    jo.put("currentSpeed", currentSpeed);
    jo.put("rocketNums", rocketNums);
    jo.put("rocketSpeed", rocketSpeed);
    jo.put("coinsPerWindow", coinsPerWindow);
    jo.put("zapperNums", zapperNums);

    try {
      PrintWriter pw = new PrintWriter(filename);
      pw.write(jo.toJSONString());

      pw.flush();
      pw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadGameData(String filename) {
    try {
      FileReader reader = new FileReader(filename);
      StringBuilder jsonData = new StringBuilder();
      int c = reader.read();
      while (c != -1) {
        jsonData.append((char) c);
        c = reader.read();
      }
      reader.close();
      String gameState = jsonData.toString();
      //get current level number and speed
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    GameManager manager = new GameManager();
    manager.start();
  }
}