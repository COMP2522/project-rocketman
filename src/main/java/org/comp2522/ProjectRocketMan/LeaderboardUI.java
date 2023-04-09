package org.comp2522.ProjectRocketMan;

import org.bson.Document;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;
import java.util.ArrayList;

/**
 * LeaderboardUI class to show the leaderboards of the global rocket-man game.
 *
 * @author Aditya Agrawal
 * @version 1.0.0
 */
public class LeaderboardUI extends GameUI {

  /**
   * Database instance.
   */
  private final Database database;
  /**
   * Window instance.
   */
  protected Window window;
  /**
   * GameManager instance.
   */
  protected GameManager manager;
  /**
   * Background image.
   */
  protected PImage background;
  /**
   * ArrayList of the leaderboard scores.
   */
  private ArrayList<LeaderboardScore> scores;

  /**
   * Constructor for the LeaderboardUI class.
   *
   * @param buttons    buttons in the leaderboard ui
   * @param manager    GameManager instance
   * @param background background image
   */
  public LeaderboardUI(Button[] buttons, GameManager manager, PImage background) {
    super(buttons, manager, background);
    window = Window.getInstance();
    this.manager = manager;
    this.background = background;
    this.database = Database.getInstance();
    this.scores = new ArrayList<LeaderboardScore>();
  }

  /**
   * Update leader board method, called whenever the leaderboard ui is displayed.
   */
  public void updateLeaderboard() {
    ArrayList<Document> documents = database.getLeaderBoard();
    scores = new ArrayList<LeaderboardScore>();
    int y = 50;
    int lineHeight = 60;
    for (int i = 0; i < documents.size(); i++) {
      Document document = documents.get(i);
      scores.add(new LeaderboardScore(new PVector(400, y + i * lineHeight),
          document.get("score").toString(),
          document.get("coins").toString()));
    }
  }

  /**
   * Draw method to display the ui on screen.
   */
  @Override
  protected void draw() {
    window.image(background, 0, 0);
    window.textSize(20);
    for (LeaderboardScore score : scores) {
      score.draw();
    }
    for (Button button : buttons) {
      button.draw();
    }
  }

  /**
   * Button clicked method.
   *
   * @param label a String
   */
  @Override
  void buttonClicked(String label) {
    manager.setGameState(0);
  }

  /**
   * Key event method.
   *
   * @param keyEvent key events
   */
  @Override
  void keyEvent(KeyEvent keyEvent) {
    //no key events
  }
}
