package org.comp2522.ProjectRocketMan;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;

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

    player = new Player();
    window = new Window();
    background = new Background();
    rockets = new ArrayList<Rocket>();
  }

  public void start() {
    init();
    isRunning = true;
    window.draw();
  }


  public void init() {
    currentLevel = 1;
    currentSpeed = 3;
    rocketSpeed = 5;
    coinsPerWindow = 6;
    background.setSpeed(currentSpeed);
    background.setCoinNum(coinsPerWindow);
    background.setZapperNum(zapperNums);
    for (int i = 0; i < rocketNums; i++) {
      PVector position = 0;
      PVector direction = 0;
      PImage image = "";
      Rocket tempRocket = new Rocket(position, direction, image, window);
      tempRocket.setSpeed(rocketSpeed);
      rockets.add(tempRocket);
    }
  }

  public void gameOver() throws FileNotFoundException {
    isRunning = false;
    saveGameData("gameState.json");
    //close window
  }

  public int update() {
    background.update();
    for (int i = 0; i < rocketNums; i++) {
      Rocket rocket = rockets.get(i);
      rocket.update();
      if (isCollided(player, rocket)) {
        return 0;// 0 means game over
      }
    }

    for (int i = 0; i < zapperNums; i++) {
      Zapper zapper = background.zappers.get(i);
      if (player.getPosition.equalsto(zapper.getPosition())) {
        return 0;
      }
//      if (isCollided(player, zapper)) {
//        return 0;// 0 means game over
//      }
    }

    for (int i = 0; i < coinsPerWindow; i++) {
      Coin coin = background.coins.get(i);
      if (coin.getPostion().equalsto(player.getPostion())) {
        //remove this coin; if don't use iterator,there will be problem
        background.remove();//need discuss
        player.currCoin++;
      }
    }

    //if level up
    if (player.currCoin >= leveUpCoins) {
      currentLevel++;
      background.setSpeed(background.getSpeed() + 1);
    }

  }

  public boolean isCollided(Player player, Rocket rocket) {
    return player.getPosition.equalsto(rocket.getPosition());
    //System.out.println("Handling collision between " + obj1 + " and " + obj2);
    // Handle collision between two game objects.
  }

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