package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;
import org.bson.Document;
import processing.event.KeyEvent;


import java.util.ArrayList;

public class LeaderboardUI extends GameUI {

  protected Window window;
  protected GameManager manager;
  protected PImage background;

  private final Database database;
  private ArrayList<LeaderboardScore> scores;
  public LeaderboardUI(PVector position, PVector direction, Button[] buttons, GameManager manager, PImage background) {
    super(position, direction, buttons, manager, background);
    window = Window.getInstance();
    this.manager = manager;
    this.background = background;
    this.database = Database.getInstance();
    this.scores = new ArrayList<LeaderboardScore>();
  }

  public void updateLeaderboard() {
    ArrayList<Document> documents = database.getLeaderBoard();
    scores = new ArrayList<LeaderboardScore>();
    int y = 50;
    int lineHeight = 60;
    for (int i = 0; i < documents.size(); i++) {
      Document document = documents.get(i);
      scores.add(new LeaderboardScore(new PVector(400, y + i * lineHeight), document.get("score").toString(), document.get("coins").toString()));
    }
  }

  @Override
  protected void draw() {
    window.image(background, 0,0);
    window.textSize(20);
    for (LeaderboardScore score: scores) {
      score.draw();
    }
    for(Button button : buttons){
      button.draw();
    }
  }

  /**
   * Sets the position of the movable object to the specified x and y coordinates.
   *
   * @param position - The position to set.
   */
  @Override
  void setPosition(PVector position) {

  }

  /**
   * Returns the current position of the movable object
   * as an object containing x and y coordinates.
   *
   * @return An object containing the x and y coordinates of the movable object's position.
   */
  @Override
  PVector getPosition() {
    return null;
  }

  /**
   * Moves the movable object by its own velocity amount in the x and y directions.
   */
  @Override
  void move() {

  }

  /**
   * Returns the speed of the movable object.
   *
   * @return A double value containing the x and y velocities of the movable object.
   */
  @Override
  float getSpeed() {
    return 0;
  }

  /**
   * Sets the velocity of the movable object to the specified values in the x and y directions.
   *
   * @param speed The velocity to set in both direction.
   */
  @Override
  void setSpeed(float speed) {

  }

  @Override
  void buttonClicked(String label) {
    manager.setGameState(0);
  }

  @Override
  void keyEvent(KeyEvent keyEvent) {

  }
}
