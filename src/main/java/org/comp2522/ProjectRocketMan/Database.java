package org.comp2522.ProjectRocketMan;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Objects;

public class Database {
  private final static String mongoPassword = "aditya123";

  private static Database database;
  MongoDatabase mongoDB;

  private Database() {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://aditya:" + mongoPassword + "@comp-2522.jyuxhsa.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build())
        .build();
    MongoClient mongoClient = MongoClients.create(settings);
    mongoDB = mongoClient.getDatabase("test");
  }

  public BsonObjectId createInitScoreDocument() {
    Document document = new Document();
    document.append("hearts", 0);
    document.append("coins", 0);
    document.append("score", 0);
    InsertOneResult res = mongoDB.getCollection("scores").insertOne(document);
    return Objects.requireNonNull(res.getInsertedId()).asObjectId();
  }

  public void updateGameScore(BsonObjectId id, int hearts, int coins, int score) {
    Bson updates = Updates.combine(
        Updates.set("hearts", hearts),
        Updates.set("coins", coins),
        Updates.set("score", score));
    new Thread(() -> mongoDB.getCollection("scores").findOneAndUpdate(Filters.eq("_id", id), updates)).start();
  }

  public static Database getInstance() {
    if (database == null) {
      database = new Database();
    }
    return database;
  }
}
