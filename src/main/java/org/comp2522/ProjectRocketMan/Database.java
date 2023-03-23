package org.comp2522.ProjectRocketMan;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Database {

  private static Database database;
  MongoDatabase mongoDB;

  private Database() {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://aditya:aditya123@comp-2522.jyuxhsa.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build())
        .build();
    MongoClient mongoClient = MongoClients.create(settings);
    mongoDB = mongoClient.getDatabase("test");
  }

  public void put(String key, String value) {
    Document document = new Document();
    document.append(key, value);
    new Thread(() -> mongoDB.getCollection("students").insertOne(document)).start();
  }

  public static Database getInstance() {
    if (database == null) {
      database = new Database();
    }
    return database;
  }

  public static void main(String[] args) {
    Database d = Database.getInstance();
    d.put("hello", "world");
  }
}
