package org.comp2522.ProjectRocketMan;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Main {
  public static void main(String[] args) {

    ConnectionString connectionString = new ConnectionString("mongodb+srv://lzhu66:zhulijuan@cluster0.hllj0jt.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build())
        .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");

//    database.createCollection("students");
    Document document = new Document();
    document.append("name", "Ram");
    document.append("age", 26);
    document.append("city", "Hyderabad");

    database.getCollection("students").insertOne(document);
    Document find = database.getCollection("students").find(eq("name", "Ram")).first();
    System.out.println(find);

  }
}