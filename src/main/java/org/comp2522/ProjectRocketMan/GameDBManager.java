package org.comp2522.ProjectRocketMan;

import org.bson.BsonObjectId;

public class GameDBManager {
  private Database database;
  private final BsonObjectId id;
  public GameDBManager() {
    database = Database.getInstance();
    id = database.createInitScoreDocument();
  }

  public void updateGameScore(int hearts, int coins, int score) {
    database.updateGameScore(id, hearts, coins, score);
  }
}
