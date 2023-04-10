package org.comp2522.ProjectRocketMan;

import org.bson.BsonObjectId;

/**
 * GameDBManager class manages the current score in the db and keeps track of the document id to
 * update the current score in the database.
 *
 * @author Aditya Agrawal
 * @version 1.0.0
 */
public class GameDBManager {

  /**
   * Database instance.
   */
  private final Database database;

  /**
   * Document id.
   */
  private final BsonObjectId id;

  /**
   * Constructor for the GameDBManager class.
   */
  public GameDBManager() {
    database = Database.getInstance();
    id = database.createInitScoreDocument();
  }

  /**
   * Updates the game score in the database as the game proceeds.
   *
   * @param hearts the number of hearts currently
   * @param coins  the number of coins currently
   * @param score  the current score
   */
  public void updateGameScore(int hearts, int coins, int score) {
    database.updateGameScore(id, hearts, coins, score);
  }
}
