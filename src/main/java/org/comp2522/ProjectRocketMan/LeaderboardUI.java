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
  public LeaderboardUI(Button[] buttons, GameManager manager, PImage background) {
    super(buttons, manager, background);
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

  @Override
  void buttonClicked(String label) {
    manager.setGameState(0);
  }

  @Override
  void keyEvent(KeyEvent keyEvent) {
    //no key events
  }
}
