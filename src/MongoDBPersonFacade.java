import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import java.util.ArrayList;

public class MongoDBPersonFacade {
    MongoClient client;
    MongoDatabase db;
    MongoCollection<Document> collection;
    String connString = "mongodb://localhost:27017";
    String collectionName = "allPeople";
    String databaseName = "shop";

    public MongoDBPersonFacade(String connString, String collectionName, String databaseName) {
        this.connString = connString;
        this.collectionName = collectionName;
        this.databaseName = databaseName;
    }

    public MongoDBPersonFacade() {
        connect();
    }

    public void insertOne(Person person) {
        Document doc = person.toDoc();
        doc.remove("id");

        var find = collection.find(doc);
        if(find.first() == null) {
            collection.insertOne(doc);
        }
    }

    public void createIndex() {
        collection.createIndex(new Document("name", 1));
        new IndexOptions().unique(true);
    }

    public Person findById(String id) {
        Document doc = new Document("id", id);
        Document search = collection.find(doc).first();
        Person person = Person.fromDoc(search);
        return person;
    }

    public void delete(String id) {
        Document doc = new Document("id", id);
        collection.deleteOne(doc);
    }

    public ArrayList<Person> find(String name) {
        Document doc = new Document("name", name);
        FindIterable<Document> result = collection.find(doc);

        // lambda version
        ArrayList<Person> people = new ArrayList<>();
        //             var -> lista.kommando(param)
        result.forEach(person -> people.add(Person.fromDoc(person)));

    /* for loop
    for(Document res : result) {
        heroes.add(Hero.fromDoc(res));
    }
    */
        return people;
    }
    public void connect() {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connString))
                .serverApi(serverApi)
                .build();

        try {
            client = MongoClients.create(settings);
            db = client.getDatabase(databaseName);
            collection = db.getCollection(collectionName);
        } catch (Exception e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}
