import com.mongodb.client.FindIterable;
import com.mongodb.client.*;
import org.bson.Document;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE); // WARNING

        //Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        //mongoLogger.setLevel(Level.SEVERE); // en alternativ lösning

        MongoClient client = MongoClients.create("mongodb+srv://ellenhalvardsson:3xh6wWYcYBUkyzov@cluster0.dm1yaj2.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase db = client.getDatabase("shop");
        MongoCollection<Document> collection = db.getCollection("customers");

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