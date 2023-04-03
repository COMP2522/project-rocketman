package org.comp2522.ProjectRocketMan;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import processing.event.KeyEvent;
import org.json.simple.JSONObject;
import processing.core.PImage;
import processing.core.PVector;
import processing.sound.SoundFile;

/**
 * The GameManager class represents a game manager object that manages the game state and elements.
 * @author Lisa Zhu
 * @version 1.0  2023-03-30
 */
public class GameManager {
  // responsible for managing the game logic and data.
  // updating the game world
  // managing player input
  // handling collision detection
  // keeping track of the game score
  private Window window;
  private Background background;

  private SoundFile coinSound;

  private SoundFile heartSound;

  private SoundFile rocketSound;

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
  private int coinsPerWindow;
  private int zapperNums;



  /* References to different images*/
  private PImage rocket_image;
  private PImage rocket_man_image;

  private PImage background_images;

  private PImage menu_background;

  private PImage pause_background;

  private PImage coin;

  private PImage[] coinAnimation;

  private PImage[] heartAnimaton;


  private GameUI[] gameUIS;


  /**
   * Stores the game state of the Game.x
   * State:
   *    0: Start State
   *    1: Game running state
   *    2: Game Pause
   *    3: Player Dead
   */
  private int gameState;

  private GameDBManager dbManager;

  LeaderboardUI leaderboard;


  /**
   * This constructor initializes the game state variables and
   * creates an empty ArrayList for rockets..
   */
  public GameManager() {
    isRunning = false;
    isPaused = false;
    currentLevel = 1;

//    background = new Background();
    rockets = new ArrayList<Rocket>();
  }

  /**
   * This method starts the program by initializing a new instance of the Window class
   * and starting the window. It also sets the isRunning flag to true.
   */
  public void start() {
    window = Window.getInstance(600,1000);
    window.startWindow(this);
    isRunning = true;
  }


  /**
   * Initializes the game with necessary objects and images.
   * @param sprites The ArrayList of Sprites to add the game objects to.
   * @param movables The ArrayList of Movable objects to add the movable game objects to.
   */
  public void init(ArrayList<Sprite> sprites, ArrayList<Movable> movables) {
    this.coins        = new ArrayList<Coin>();
    this.sprites      = sprites;
    this.moveables    = movables;
    this.collidables  = new ArrayList<Collidable>();
    rocket_image      = window.loadImage("images/rockect_images/rocket_3.png");
    rocket_man_image  = window.loadImage("images/rocket_man_images/My project2.png");
    background_images = window.loadImage("images/rocket_man_backgrounds/AIgen.png");
    menu_background   = window.loadImage("images/rocket_man_backgrounds/Start.png");
    pause_background   = window.loadImage("images/rocket_man_backgrounds/Start.png");
    coin              = window.loadImage("images/rocket_man_coins/star coin rotate 1.png");
    coinSound         = new SoundFile(window, "music/coin.wav");
    heartSound        = new SoundFile(window, "music/heart.wav");
    rocketSound       = new SoundFile(window, "music/rocket.wav");
    gameState         = 0;


    background = new Background(
        background_images,
        1,
        new PVector(0,0),
        new PVector(1,1)
    );

    player = Player.getInstance(
        new PVector(window.width * 0.10f,window.height / 2),
        new PVector(1,1),
        rocket_man_image,
        0
    );

    rockets = new ArrayList<Rocket>();
    setUpGameUIs();

    setupCoinAnimations();
    setupHeartAnimations();
    this.sprites.    add(background);
    this.moveables.  add(background);
    this.sprites.    add(player);
    this.moveables.  add(player);

 // *** redundant commented out code
//    this.sprites.    addAll(coins);
//    this.sprites.    addAll(rockets);
//    this.moveables.  addAll(rockets);

//    this.moveables.  addAll(coins);
//    this.collidables.addAll(coins);
//    this.collidables.addAll(rockets);
  }


  /**
   * Sets up the coin animations for the RocketMan game.
   * This method loads a series of images of a rotating star coin and stores them in an array.
   */
  private void setupCoinAnimations(){
    coinAnimation = new PImage[6];
    for(int i = 1; i <= 6; i++){
      coinAnimation[i - 1] = window.loadImage("images/rocket_man_coins/star coin rotate " + i + ".png");
    }
  }

  /**
   * Initializes an array of PImages for heart animations.
   * Loads images from the "images/Hearts/" directory, with filenames
   * "heart01.png" to "heart05.png".
   */
  private void setupHeartAnimations(){
    heartAnimaton = new PImage[5];
    for(int i = 1; i <= 5; i++){
      heartAnimaton[i - 1] = window.loadImage("images/Hearts/heart0" + i + ".png");
    }
  }

  /**
   * Sets up the game user interfaces.
   * This method initializes three game user interfaces: StartGameUI, PauseGameUI, and DeadGameUI.
   * It also creates buttons for each user interface and assigns them to their respective UI.
   */
  private void setUpGameUIs(){

    //Setup Start UI
    gameUIS    = new GameUI[4];
    //set up buttons
    Button[] startButtons = new Button[3];
    startButtons[0] = new Button(new PVector(window.width /2 , 200), new PVector(100, 50),"Start");
    startButtons[1] = new Button(new PVector(window.width /2 , 300), new PVector(100, 50),"Leaderboard");
    startButtons[2] = new Button(new PVector(window.width /2 , 400), new PVector(100, 50),"Quit");

    //set up buttons
    Button[] pauseButtons = new Button[1];
    pauseButtons[0] = new Button(new PVector(window.width /2 , 400), new PVector(100, 50),"Quit");

    gameUIS[0] = new StartGameUI(new PVector(window.width /2 , 200), new PVector(100, 50), startButtons, this, menu_background);
    gameUIS[1] = new PauseGameUI(new PVector(window.width /2 , 600), new PVector(100, 50), pauseButtons, this, menu_background);

    Button[] deadButtons = new Button[2];
    deadButtons[0] = new Button(new PVector(window.width /2 , 300), new PVector(100, 50),"Retry");
    deadButtons[1] = new Button(new PVector(window.width /2 , 450), new PVector(100, 50),"Main Menu");

    gameUIS[2] = new DeadGameUI(new PVector(window.width /2 , 600), new PVector(100, 50), deadButtons, this, menu_background);

    Button[] leaderboardButtons = new Button[1];
    leaderboardButtons[0] = new Button(new PVector(window.width / 2, 400), new PVector(100, 50), "Main Menu");
    leaderboard = new LeaderboardUI(new PVector(window.width / 2, 600), new PVector(100, 50), leaderboardButtons, this, menu_background);
    gameUIS[3] = leaderboard;
  }


  /**
   * This method manages the game based on the current game state.
   * It performs different actions depending on the value of the gameState variable.
   */
  public void manageTheGame(){
    switch(gameState){
      case 0:
        gameUIS[0].draw();
        break;
      case 1:
        //State when the game is running
        draw();
        move();
        checkForCollisions();
        manageRockets();
        manageCoins();
        manageHeart();
        manageBackground();
        updatePlayerScoer();
        break;
      case 2:
        System.out.println("Inside case 2");
        gameUIS[1].draw();
        break;
        //State when the game is paused;
      case 3:
        // state when player has died
        gameUIS[2].draw();
        break;
      case 4:
        gameUIS[3].draw();
      default:
        //Game has ended.
        ;
    }
  }

  /**
   * Resets the game to the beginning to re-run the game.
   */
  public void resertToStart(){
    sprites.    clear();
    moveables.  clear();
    collidables.clear();
    rockets.    clear();
    coins.      clear();
    collidables.clear();
    this.sprites.    add(background);
    this.moveables.  add(background);
    this.sprites.    add(player);
    this.moveables.  add(player);
    background.setSpeed(0f);
    player.setScore(0);
    player.setNumberOfCoinsCollected(0);
    player.setHearts(0);
    heart. setPosition(new PVector(window.random(window.width, window.width * 2), window.random(0,window.height)));
    sprites.    add(heart);
    collidables.add(heart);
    moveables.  add(heart);
  }

  /**
   * This method draws sprites and information.
   */
  private void draw(){
    drawSprites();
    drawInformation();
  }

  /**
   * Draws the player's current score, number of coins collected, and remaining hearts on the screen.
   */
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

  /**
   * Draws all sprites in the list of sprites.
   */
  private void drawSprites(){
    for (Sprite sprite : sprites) {
      sprite.draw();
    }
  }

  /**
   * This method moves each object in the list of moveables.
   */
  private void move(){
    for(Movable move : moveables){
      move.move();
    }
  }

  /**
   * Checks for collisions between player and collidables, and updates the game state accordingly.
   * If the player collides with a Rocket, the player loses a heart and
   * if the player runs out of hearts, the game state is set to 3.
   * If the player collides with a Coin, the player's number of collected coins is incremented.
   * If the player collides with a Heart, the player gains a heart.
   */
  public void checkForCollisions(){
    ArrayList<Collidable> toRemove = new ArrayList<Collidable>();
    for (Collidable temp : collidables) {
      if (temp.collided(player)){
        if(temp instanceof Rocket){
          rocketSound.play();
          dbManager.updateGameScore(player.getHearts(), player.getNumberOfCoinsCollected(), player.getScore());
          sprites.remove((Sprite) temp);
          toRemove.add(temp);
          player.setHearts(player.getHearts() - 1);
          if(player.getHearts() < 0){
            gameState = 3;
          }
        } else {
          if (temp instanceof Coin) {
            coinSound.play();
            player.setNumberOfCoinsCollected(player.getNumberOfCoinsCollected() + 1);
            sprites.remove((Sprite) temp);
            toRemove.add(temp);
          } else {
            heartSound.play();
            player.setHearts(player.getHearts() + 1);
            sprites.remove((Sprite) temp);
            toRemove.add(temp);
          }
        }

      }

    }
    collidables.removeAll(toRemove);
  }


  /**
   * This method handles mouse events based on the current state of the game.
   */
  public void mouseEvents(){
    switch(gameState){
      case 0:
        gameUIS[0].checkForClicks();
        break;
      case 1:
        //State when the game is running
        break;
      case 2:
        gameUIS[1].checkForClicks();
        //State when the game is paused;
        break;
      case 4:
        gameUIS[3].checkForClicks();
      default:
        //Player is dead .
        gameUIS[2].checkForClicks();
        ;
    }
  }

  /**
   * Handles key events for the game.
   * @param event the KeyEvent object representing the key event that occurred
   */
  public void keyEvents(KeyEvent event){
    switch(gameState){
      case 0:
        gameUIS[0].checkForClicks();
        break;
      case 1:
        player.keyPressed(event);
        if (event.getKey() == 'p' || event.getKey() == 'P') {
          gameState = 2;
        }
        break;
      case 2:
        player.keyPressed(event);
        gameUIS[1].keyEvent(event);
        break;
      default:;
    }
  }


  /**
   * Updates the player's score based on the current speed of the background.
   */
  private void updatePlayerScoer(){
    player.setScore((int) (player.getScore() + background.getSpeed()));
  }


  /**
   * This method manages the behavior of the Heart sprite in the game.
   * If the Heart sprite does not exist, it is created and added to the relevant lists.
   * If the Heart sprite moves out of bounds, it is repositioned and added back to the relevant lists.
   * The Heart sprite's speed is set to match the game's background speed.
   */
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

  /**
   * This method manages the speed of the background based on the frame count.
   */
  private void manageBackground(){
    if(window.frameCount % 200 == 0){
      background.setSpeed(Window.min(10f, background.getSpeed() + 0.2f));
      System.out.println(background.getSpeed()+ "\n");

    }
  }


  /**
   * This method manages the rockets in the game, by updating their speed and
   * removing any rockets that have gone out of bounds.
   * If there are no rockets left on the screen, this method will add a new
   * set of rockets to the game.
   */
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

  /**
   * Adds a specified number of rockets to the game.
   * @param rocketsToAdd the number of rockets to add to the game
   */
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

  /**
   * Manages the coins that are currently in play, removing any that are offscreen
   * and adding new ones if necessary.
   */
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

  /**
   * Adds a random number of coins to the game board in different patterns.
   * The pattern in which the coins will be placed is chosen randomly from 4 options:
   * placing coins in a line, in a zigzag, in a rectangle or scattered.
   */
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

  /**
   * Scatters a given number of coins across the game area.
   * @param numberOfCoinsTobeAdded the number of coins to be scattered.
   */
  private void makeCoinsScatter(int numberOfCoinsTobeAdded) {
  }

  /**
   * Generates a rectangular pattern of coins within the game board.
   */
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

  /**
   * Adds the specified number of coins to the current game board in a zig-zag pattern.
   * @param numberOfCoinsTobeAdded the number of coins to add to the board
   */
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


  /**
   * Adds a specified number of coins in a line on the game board.
   * @param numberOfCoinsTobeAdded the number of coins to be added in a line
   */
  private void makeCoinsInALine(int numberOfCoinsTobeAdded){
    float startPositionOfLine = window.random(window.width + window.width / 10f, 2 * window.width * 2);
    float yPositionOfTheLine = window.random(10f, window.height - 50);
    float speedForAllCoinsInThePattern = window.random(10, 1);

    for(int i = 0; i < numberOfCoinsTobeAdded; i++){
      Coin toBeAdded = getCoinInstance(startPositionOfLine, yPositionOfTheLine, speedForAllCoinsInThePattern);
      startPositionOfLine = startPositionOfLine + toBeAdded.getWidth();
    }

  }


  /**
   * Creates a new instance of the Coin class with the specified parameters.
   * @param xPosition the x position of the coin on the screen
   * @param yPosition the y position of the coin on the screen
   * @param speedOfCoins the speed at which the coin moves
   * @return a new instance of the Coin class with the specified parameters
   */
  private Coin getCoinInstance(float xPosition, float yPosition, float speedOfCoins){
    Coin temp = new Coin(new PVector(xPosition, yPosition),
        new PVector(0,0), coinAnimation, speedOfCoins);
    coins.add(temp);
    sprites.add(temp);
    moveables.add(temp);
    collidables.add(temp);

    return temp;

  }

  /**
   * Returns the current state of the game.
   * @return an integer representing the current state of the game.
   *    Possible values include
   *    0: Start State
   *    1: Game running state
   *    2: Game Pause
   *    3: Player Dead
   */
  public int getGameState() {
    return gameState;
  }

  /**
   * Sets the current game state to the specified value.
   * @param gameState the new game state to be set
   */
  public void setGameState(int gameState) {
    boolean fromPause = this.gameState == 2;
    this.gameState = gameState;
    if (!fromPause) {
      dbManager = new GameDBManager();
    }
    if (gameState == 4) {
      leaderboard.updateLeaderboard();
    }
//    System.out.println(System.getenv("MONGODB_URL"));
  }

  /**
   * Called when the game is over.
   * @throws FileNotFoundException if the file to write to cannot be found
   */
  public void gameOver() throws FileNotFoundException {
    isRunning = false;
    saveGameData("gameState.json");
    //close window
  }

  /**
   * Pauses the game.
   * This method pauses the game by setting the game state to "paused".
   */
  public void pause() {
    isPaused = true;
    // Display pause menu, etc.
  }

  /**
   * Resumes the paused game, if any.
   * This method continues the game from where it was paused.
   * If the game was not paused, this method has no effect.
   */
  public void resume() {
    isPaused = false;
  }

  /**
   * Saves the current game data to a file with the specified filename.
   * @param filename the name of the file to save the game data to
   * @throws FileNotFoundException if the specified file cannot be found or created
   */
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

  /**
   * Loads game data from the specified file.
   * @param filename the name of the file containing the game data to be loaded
   */
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
      //get current leveFl number and speed
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * The main entry point for the application.
   * Creates an instance of GameManager and calls its start method.
   * @param args command line arguments (not used in this implementation)
   */
  public static void main(String[] args) {
    GameManager manager = new GameManager();
    manager.start();
  }
}