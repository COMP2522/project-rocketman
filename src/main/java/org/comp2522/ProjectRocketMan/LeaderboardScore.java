package org.comp2522.ProjectRocketMan;

import processing.core.PVector;

/**
 * Leaderboard score class.
 *
 * @author Aditya Agrawal
 * @version 1.0.0
 */
public class LeaderboardScore {

  /**
   * Text displayed on screen.
   */
  private final String text;

  /**
   * Position of the LeaderboardScore.
   */
  private final PVector position;

  /**
   * Window instance.
   */
  private final Window window;

  /**
   * Constructor for the LeaderboardScore class.
   *
   * @param position position of the LeaderboardScore
   * @param score    score of the leaderboard
   * @param coins    number of coins to display
   */
  public LeaderboardScore(PVector position, String score, String coins) {
    this.text = "Score: " + score + "     Coins: " + coins;
    this.position = position;
    window = Window.getInstance();
  }

  /**
   * Draw method to display the leaderboard on screen.
   */
  public void draw() {
    float x = window.textWidth(text) + 20;
    float y = 50;
    window.stroke(0);
    window.fill(255);
    window.rect(position.x, position.y, window.textWidth(text) + 20, 50);
    window.fill(0);
    window.textAlign(window.CENTER, window.CENTER);
    window.text(text, position.x + x / 2, position.y + y / 2);
  }
}
