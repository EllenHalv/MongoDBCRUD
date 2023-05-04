import com.mongodb.client.FindIterable;
import com.mongodb.client.*;
import org.bson.Document;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE); // ger endast felmeddelanden av typen severe

        /*MongoDBPersonFacade dbFacade = new MongoDBPersonFacade(); // create new instance of MongoDBPersonFacade
        Person person = new Person("John Doe", "42", "Somewhere"); // add person
        dbFacade.insertOne(person);*/

        /*KeyHandler key = new KeyHandler("MongoDB");
        MongoDBPersonFacade db = new MongoDBPersonFacade(
                key.GetKey("connectionString"),
                key.GetKey("collection"),
                key.GetKey("database"));*/

        KeyHandler key = new KeyHandler("MongoDB");
        String connectionString = key.GetKey("connectionString");
        String collectionName = key.GetKey("collection");
        String databaseName = key.GetKey("database");
        if (connectionString == null || connectionString.isEmpty()) {
            throw new IllegalArgumentException("Connection string is null or empty");
        }
        MongoClient client = MongoClients.create(connectionString);

        //MongoClient client = MongoClients.create(key.getConnectionString());
        MongoDatabase mongodb = client.getDatabase("shop");
        MongoCollection<Document> collection = mongodb.getCollection("customers");

        FindIterable<Document> result = collection.find();

        Document doc = new Document("name", "John Doe");
        long count = collection.countDocuments(doc);

        if (count == 0) {
            collection.insertOne(doc);
        }

        for(Document res : result) {
            System.out.println(res.toJson());
        }
    }
}