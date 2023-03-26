package org.comp2522.ProjectRocketMan;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

  private  List<Coin> coins;

  private boolean isRunning;
  private boolean isPaused;
  private int currentLevel;
  private int leveUpCoins;
  private int currentSpeed;
  private int rocketNums;
  private int rocketSpeed;
  private int coinsPerWindow;
  private int zapperNums;




  /* References to different images*/
  private PImage rocket_image;
  private PImage rocket_man_image;

  private PImage background_images;

  private PImage coin;

  private PImage[] coinAnimation;



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
    this.coins = new ArrayList<Coin>();
    this.sprites = sprites;
    this.moveables = movables;
    this.collidables = new ArrayList<Collidable>();
    rocket_image = window.loadImage("images/rockect_images/rocket_2.png");
    rocket_man_image = window.loadImage("images/rocket_man_images/My project.png");
    background_images = window.loadImage("images/rocket_man_backgrounds/AIgen.png");
    coin = window.loadImage("images/rocket_man_coins/star coin rotate 1.png");

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

    setupCoinAnimations();
    for(int i = 0; i < 10; i++){
      coins.add(new Coin(new PVector(window.width + i * 50, i * 50), new PVector(window.width, i * 200), coinAnimation, 1));
    }
    this.sprites.add(background);
    this.moveables.add(background);

    this.sprites.addAll(coins);
    this.sprites.addAll(rockets);
    this.moveables.addAll(rockets);
    this.sprites.add(player);
    this.moveables.add(player);
    this.moveables.addAll(coins);
    this.collidables.addAll(coins);
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


  private void setupCoinAnimations(){
    coinAnimation = new PImage[6];
    for(int i = 1; i <= 6; i++){
      coinAnimation[i - 1] = window.loadImage("images/rocket_man_coins/star coin rotate " + i + ".png");
    }
  }

  public void manageTheGame(){
    checkForCollisions();
    manageRockets();
    manageCoins();
    updatePlayerScoer();

  }


  private void checkForCollisions(){
    for (Collidable temp : collidables) {
      if (temp.collided(player)){
        if(temp instanceof Rocket){
          window.exit();
        }else {
          System.out.println("set speed to zero");
          sprites.remove((Sprite) temp);
        }

      }

    }
  }



  /*Code to Manage Player*/
  private void updatePlayerScoer(){
    player.setScore((int) (player.getScore() + background.getSpeed()));
  }

  /* Code to Manage Rockets*/
  private void manageRockets(){
    ArrayList<Rocket> rocketsOutOfBound = new ArrayList<Rocket>();
    int numberofRocketsOffScreen = 0;
    System.out.println("number of rockets to be added: " + numberofRocketsOffScreen);

    for(Rocket temp : rockets){
      System.out.println("X position in manage rockets: " + temp.getPosition().x);
      if(temp.getPosition().x < -50 ){
        rocketsOutOfBound.add(temp);
        numberofRocketsOffScreen++;
      }
    }
    rockets.removeAll(rocketsOutOfBound);
    sprites.removeAll(rocketsOutOfBound);
    moveables.removeAll(rocketsOutOfBound);
    moveables.removeAll(rocketsOutOfBound);
    collidables.removeAll(rocketsOutOfBound);

    addRockets(numberofRocketsOffScreen);
  }


  private void addRockets(int rocketsToAdd){
    for(int i = 0; i < rocketsToAdd; i++){
      Rocket tobeAdded = new Rocket(new PVector(window.random(window.width, window.width * 2), window.random(0,window.height)),
              new PVector(window.random(-1, 1), window.random(-1,1)), rocket_image, window.random(-10, -1));
      rockets.add(tobeAdded);
      sprites.add(tobeAdded);
      moveables.add(tobeAdded);
      collidables.add(tobeAdded);
    }


  }










  /*Code to Manage Coins*/

  private void manageCoins(){
    ArrayList<Coin> coinsOutOfBound = new ArrayList<Coin>();
    int numberofCoinsOffScreen = 0;
    System.out.println("number of rockets to be added: " + numberofCoinsOffScreen);

    for(Coin temp : coins){
      System.out.println("X position in manage rockets: " + temp.getPosition().x);
      if(temp.getPosition().x < -50 ){
        coinsOutOfBound.add(temp);
        numberofCoinsOffScreen++;
      }
    }
    coins.removeAll(coinsOutOfBound);
    sprites.removeAll(coinsOutOfBound);
    moveables.removeAll(coinsOutOfBound);
    moveables.removeAll(coinsOutOfBound);
    collidables.removeAll(coinsOutOfBound);

    addCoins(numberofCoinsOffScreen);
  }

  private void addCoins(int numberofCoinsOffScreen) {
    for(int i = 0; i < numberofCoinsOffScreen; i++){
      Coin tobeAdded = new Coin(new PVector(window.random(window.width, window.width * 2), window.random(0,window.height)),
              new PVector(window.random(-1, 1), window.random(-1,1)), coinAnimation, window.random(10, 1));
      coins.add(tobeAdded);
      sprites.add(tobeAdded);
      moveables.add(tobeAdded);
      collidables.add(tobeAdded);
      System.out.println("Coin added");
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