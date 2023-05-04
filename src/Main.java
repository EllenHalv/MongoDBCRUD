import com.mongodb.client.FindIterable;
import com.mongodb.client.*;
import org.bson.Document;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE); // ger endast felmeddelanden av typen severe

        KeyHandler key = new KeyHandler("MongoDB");
        String connectionString = key.GetKey("connectionString");
        String collectionName = key.GetKey("collection");
        String databaseName = key.GetKey("database");
        if (connectionString == null || connectionString.isEmpty()) {
            throw new IllegalArgumentException("Connection string is null or empty");
        }
        MongoClient client = MongoClients.create(connectionString);

        MongoDatabase mongodb = client.getDatabase("shop");
        MongoCollection<Document> collection = mongodb.getCollection("customers");

        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Lana Del Roy", "22", "Angel City")); // skapar en person
        people.add(new Person("Clark Knot", "18", "Metropolis")); // skapar en person
        people.add(new Person("Clark Knot", "Superman", "Metropolis")); // skapar en person
        people.add(new Person("Clark Knot", "Superman", "Metropolis")); // skapar en person
        //collection.insertOne(person.toDoc());
        for (Person person : people) {
            collection.insertOne(person.toDoc());
        }

        FindIterable<Document> result = collection.find();

        for(Document res : result) {
            System.out.println(res.toJson());
        }
    }
}