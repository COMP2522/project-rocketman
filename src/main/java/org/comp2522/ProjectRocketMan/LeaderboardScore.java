package org.comp2522.ProjectRocketMan;

import processing.core.PVector;

public class LeaderboardScore {
  private final String text;
  private PVector position;
  private Window window;

  public LeaderboardScore(PVector position, String score, String coins) {
    this.text = "Score: " + score + "     Coins: " + coins;
    this.position = position;
    window = Window.getInstance();
  }

  public void draw() {
    float dimentionsX = window.textWidth(text) + 20;
    float dimentionsY = 50;
    window.stroke(0);
    window.fill(255);
    window.rect(position.x, position.y, window.textWidth(text) + 20, 50);
    window.fill(0);
    window.textAlign(window.CENTER, window.CENTER);
    window.text(text, position.x + dimentionsX/2, position.y + dimentionsY/2);
  }
}
