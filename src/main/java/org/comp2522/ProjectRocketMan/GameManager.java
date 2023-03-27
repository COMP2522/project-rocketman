package org.comp2522.ProjectRocketMan;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

  private Heart heart;

  private boolean isRunning;
  private boolean isPaused;
  private int currentLevel;
  private int leveUpCoins;
  private int currentSpeed;
  private int rocketNums;
  private int rocketSpeed;


  /* References to different images*/
  private PImage rocket_image;
  private PImage rocket_man_image;

  private PImage background_images;

  private PImage coin;

  private PImage[] coinAnimation;

  private PImage[] heartAnimaton;



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
    rocket_image = window.loadImage("images/rockect_images/rocket_3.png");
    rocket_man_image = window.loadImage("images/rocket_man_images/My project2.png");
    background_images = window.loadImage("images/rocket_man_backgrounds/AIgen.png");
    coin = window.loadImage("images/rocket_man_coins/star coin rotate 1.png");


    background = new Background(background_images,1, new PVector(0,0),
            new PVector(1, 1) );
    player = Player.getInstance(new PVector(window.width * 0.10f,window.height / 2),
            new PVector(1, 1), rocket_man_image, 0);
    rockets = new ArrayList<Rocket>();
    setupCoinAnimations();
    setupHeartAnimations();

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
  }

  private void setupCoinAnimations(){
    coinAnimation = new PImage[6];
    for(int i = 1; i <= 6; i++){
      coinAnimation[i - 1] = window.loadImage("images/rocket_man_coins/star coin rotate " + i + ".png");
    }
  }

  private void setupHeartAnimations(){
    heartAnimaton = new PImage[5];
    for(int i = 1; i <= 5; i++){
      heartAnimaton[i - 1] = window.loadImage("images/Hearts/heart0" + i + ".png");
    }
  }

  /*Code to manage Different Things*/

  public void manageTheGame(){
    draw();
    move();
    checkForCollisions();
    manageRockets();
    manageCoins();
    manageHeart();
    manageBackground();
    updatePlayerScoer();
  }


  private void draw(){
    drawSprites();
    drawInformation();

  }

  private void drawInformation() {
    window.textSize(20);
    window.textAlign(window.CENTER, window.CENTER);
    window.text("Current Score: " + player.getScore(), window.width - 100, 20);

    window.text("Coins: " + player.getNumberOfCoinsCollected(), window.width - 250, 20);
    window.text("Hearts: " + player.getHearts(), window.width - 350, 20);  window.textSize(20);
    window.textAlign(window.CENTER, window.CENTER);
    window.text("Current Score: " + player.getScore(), window.width - 100, 20);

    window.text("Coins: " + player.getNumberOfCoinsCollected(), window.width - 250, 20);
    window.text("Hearts: " + player.getHearts(), window.width - 350, 20);
  }


  private void drawSprites(){
    for (Sprite sprite : sprites) {
      sprite.draw();
    }
  }
  private void move(){
    for(Movable move : moveables){
      move.move();
    }
  }

  private void checkForCollisions(){
    ArrayList<Collidable> toRemove = new ArrayList<Collidable>();
    for (Collidable temp : collidables) {
      if (temp.collided(player)){
        if(temp instanceof Rocket){
          System.out.println("Player dead if heart zero!!\n");
          sprites.remove((Sprite) temp);
          toRemove.add(temp);
          player.setHearts(player.getHearts() - 1);
          if(player.getHearts() <= 0) {
//            player.getHearts() = 0;
          }
//          window.init();
        }else {
          if (temp instanceof Coin) {
            player.setNumberOfCoinsCollected(player.getNumberOfCoinsCollected() + 1);
            sprites.remove((Sprite) temp);
            toRemove.add(temp);
          } else {
            player.setHearts(player.getHearts() + 1);
            sprites.remove((Sprite) temp);
            toRemove.add(temp);
          }
        }

      }

    }
    collidables.removeAll(toRemove);
  }



  /*Code to Manage Player*/
  private void updatePlayerScoer(){
    player.setScore((int) (player.getScore() + background.getSpeed()));
  }


  /*Code to Manage Heart*/
  private void manageHeart() {
    if(heart == null){
      heart = new Heart(new PVector(1000, 440), new PVector(0,0), heartAnimaton, background.getSpeed());
      sprites.add(heart);
      collidables.add(heart);
      moveables.add(heart);
    }
    if(heart.getPosition().x < 50){

      heart.setPosition(new PVector(window.random(window.width, window.width * 2), window.random(0,window.height)));
      if(!sprites.contains(heart)){
        sprites.add(heart);
        collidables.add(heart);
      }
    }

    heart.setSpeed(background.getSpeed());


  }

  /*Code to manage background*/
  private void manageBackground(){
    if(window.frameCount % 200 == 0){
      background.setSpeed(Window.min(10f, background.getSpeed() + 0.2f));
      System.out.println(background.getSpeed()+ "\n");

    }
  }



  /* Code to Manage Rockets*/
  private void manageRockets(){
    ArrayList<Rocket> rocketsOutOfBound = new ArrayList<Rocket>();
    int numberofRocketsOffScreen = 0;

    for(Rocket temp : rockets){

      temp.setSpeed((background.getSpeed() * -1) - (0.4f * background.getSpeed()));

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

    if(rockets.size() == 0){

      numberofRocketsOffScreen = 2;

      addRockets(numberofRocketsOffScreen);
    }

  }


  private void addRockets(int rocketsToAdd){
    for(int i = 0; i < rocketsToAdd; i++){
      Rocket tobeAdded = new Rocket(new PVector(window.random(window.width, window.width * 2), window.random(0,window.height)),

              new PVector(window.random(-1, 1), window.random(-1,1)), rocket_image,
              (background.getSpeed() * -1) - (0.2f * background.getSpeed()));

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
    for(Coin temp : coins){
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
    if (coins.size() == 0){
      addCoins();
    }

  }

  private void addCoins() {
    Random random = new Random();
    int numberOfCoinsTobeAdded = (int) window.random(0,10);
    int typeOfPatternToPutCoinsIn = random.nextInt(3);


    switch(typeOfPatternToPutCoinsIn){
      case 0:
        makeCoinsInALine(numberOfCoinsTobeAdded);
        break;
      case 1:
        makeCoinsInZigZag(numberOfCoinsTobeAdded);
        break;
      case 2:
        makeCoinsInARectangle();
        break;
      default:
        makeCoinsScatter(numberOfCoinsTobeAdded);

    }

  }

  private void makeCoinsScatter(int numberOfCoinsTobeAdded) {
  }

  private void makeCoinsInARectangle() {
    Random random = new Random();
    int numberOfCoinsTobeAdded = (int) window.random(0,20);
    int widthOfRectangle = (int) window.random(1, numberOfCoinsTobeAdded);
    int heightOfRectangle = numberOfCoinsTobeAdded / widthOfRectangle;
    int y = random.nextInt(5) + 1;
    int x = random.nextInt(5) + 1;
    float startPositionOfLine = window.random(window.width + window.width / 10f, 2 * window.width * 2);
    float yPositionOfTheLine = window.random(10f, window.height - heightOfRectangle * (coinAnimation[0].height / 50f));
    float speedForAllCoinsInThePattern = window.random(10, 1);

    for(int i = 0; i < y; i++){
      Coin toBeAdded;
      for(int j = 0; j < x; j++){
        toBeAdded = getCoinInstance(startPositionOfLine + j * (coinAnimation[0].height / 50f), yPositionOfTheLine + i * (coinAnimation[0].height / 50f) , speedForAllCoinsInThePattern);

      }
    }


  }

  private void makeCoinsInZigZag(int numberOfCoinsTobeAdded) {
    Random random = new Random();
    float startPositionOfLine = window.random(window.width + window.width / 10f, 2 * window.width * 2);
    float yPositionOfTheLine = window.random(10f, window.height - 50);
    float speedForAllCoinsInThePattern = window.random(10, 1);
    int numberOfZigZags = (int) window.random(1, numberOfCoinsTobeAdded - 1);
    int zigZagStartUporDown = random.nextBoolean() ? 1 : -1;

    for(int i = 0; i < numberOfCoinsTobeAdded; i++){
      Coin toBeAdded = getCoinInstance(startPositionOfLine, yPositionOfTheLine, speedForAllCoinsInThePattern);
      startPositionOfLine = startPositionOfLine + toBeAdded.getWidth();
      yPositionOfTheLine = yPositionOfTheLine + (toBeAdded.getHeight() *  zigZagStartUporDown);
    }

  }

  private void makeCoinsInALine(int numberOfCoinsTobeAdded){
    float startPositionOfLine = window.random(window.width + window.width / 10f, 2 * window.width * 2);
    float yPositionOfTheLine = window.random(10f, window.height - 50);
    float speedForAllCoinsInThePattern = window.random(10, 1);

    for(int i = 0; i < numberOfCoinsTobeAdded; i++){
      Coin toBeAdded = getCoinInstance(startPositionOfLine, yPositionOfTheLine, speedForAllCoinsInThePattern);
      startPositionOfLine = startPositionOfLine + toBeAdded.getWidth();
    }

  }


  private Coin getCoinInstance(float xPosition, float yPosition, float speedOfCoins){
    Coin temp = new Coin(new PVector(xPosition, yPosition),

            new PVector(0,0), coinAnimation, speedOfCoins);

    coins.add(temp);
    sprites.add(temp);
    moveables.add(temp);
    collidables.add(temp);

    return temp;

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

//    jo.put("rocketSpeed", rocketSpeed);


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