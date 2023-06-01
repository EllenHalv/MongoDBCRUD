import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import java.util.ArrayList;
import com.mongodb.ConnectionString;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.MongoClientSettings;
import org.bson.types.ObjectId;

public class MongoDBPersonFacade {
    MongoClient client;
    MongoDatabase db;
    MongoCollection<Document> collection;
    String connString = "mongodb://localhost:27017/";
    String databaseName = "shop";

    public MongoDBPersonFacade(String connString, String databaseName, MongoDatabase db, MongoCollection<Document> collection) {
        this.connString = connString;
        this.databaseName = databaseName;
        this.db = db;
        this.collection = collection;
    }

    public MongoDBPersonFacade() {
        client = MongoClients.create(connString);
        connect();
    }
    // Creates a new person in the database
    public void insertOne(Person person) {
        MongoCollection<Document> employeeCollection = db.getCollection("employees");
        MongoCollection<Document> customerCollection = db.getCollection("customers");

        Document doc = person.toDoc();

        if (person instanceof Employee) {
            employeeCollection.insertOne(doc);
        } else if (person instanceof Customer) {
            customerCollection.insertOne(doc);
        }
    }

    // Creates an index for the name field to ensure unique names and increase search speed.
    public void createIndex() {
        collection.createIndex(new Document("name", 1), new IndexOptions().unique(true));
    }

    // Finds an employee by their employeeId
    public Person findByEmployeeId(String employeeId) {
        Document doc = new Document("employeeId", employeeId);
        Document search = collection.find(doc).first();
        Person person = Person.fromDoc(search);
        return person;
    }

    // Finds a customer by their customerId
    public Person findByCustomerId(String customerId) {
        Document doc = new Document("customerId", customerId);
        Document search = collection.find(doc).first();
        Person person = Person.fromDoc(search);
        return person;
    }

    // Updates a person by their id
    public void updateName(String id, String newValue) {
        Document doc = new Document("id", id);
        Document update = new Document("$set", new Document("name", newValue));
        collection.updateOne(doc, update);
    }

    // Delete a person by their id
    public void delete(String id) {
        MongoCollection<Document> employeeCollection = db.getCollection("employees");
        MongoCollection<Document> customerCollection = db.getCollection("customers");

        Document doc = new Document("id", id);

        if (id.startsWith("1")) {
            employeeCollection.deleteOne(doc);
        } else if (id.startsWith("0")) {
            customerCollection.deleteOne(doc);
        } else {
            System.out.println("Okänt ID-format");
        }
    }

    // Find a person by their name
    public ArrayList<Person> findByName(String name) {
        Document doc = new Document("name", name);
        FindIterable<Document> result = collection.find(doc);

        ArrayList<Person> people = new ArrayList<>();
        result.forEach(person -> people.add(Person.fromDoc(person)));
        return people;
    }

    // Find a person by their objectId
    public Person findByObjectId(String id) {
        Document doc = new Document("_id", new ObjectId(id));
        Document search = collection.find(doc).first();
        Person person = Person.fromDoc(search);
        System.out.println(person);
        return person;
    }

    // Connects to the database
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
            collection = db.getCollection("customers");
        } catch (Exception e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
        try {
            createIndex(); // Skapar indexet för att säkerställa unika namn och öka sökhastigheten.
        } catch (Exception e) {
            System.out.println("Ooops! " + e.getMessage()); // Skriver ut ett meddelande om indexet inte kunde skapas.
        }
    }

    // Gets all customers from the database
    public void getAllCustomers() {
        try {
            MongoCollection<Document> collection = db.getCollection("customers");
            FindIterable<Document> customersIterable = collection.find(new Document());

            ArrayList<Person> people = new ArrayList<>();
            customersIterable.forEach(document -> {
                Person person = Person.fromDoc(document);
                System.out.println("name: " + person.getName());
                System.out.println("age: " + person.getAge());
                System.out.println("address: " + person.getAddress());
                System.out.println("id: " + person.getId());
                System.out.println();
                people.add(person);
            });

            if (people.isEmpty()) {
                System.out.println("No customers found");
            }
        } catch (Exception e) {
            System.out.println("Error getting customers: " + e.getMessage());
        }
    }



    // Gets all employees from the database
    public void getAllEmployees() {
        try {
            MongoCollection<Document> employees = db.getCollection("employees");
            FindIterable<Document> employeesIterable = employees.find(new Document());

            ArrayList<Person> people = new ArrayList<>();
            employeesIterable.forEach(document -> {
                Person person = Person.fromDoc(document);
                System.out.println(person);  // Använder toString() för utskrift
                System.out.println();
                people.add(person);
            });

            if (people.isEmpty()) {
                System.out.println("No employees found");
            }
        } catch (Exception e) {
            System.out.println("Error getting employees: " + e.getMessage());
        }
    }

    // Gets all people from the database
    public ArrayList<Person> getAllPeople() {
        try {
            MongoCollection<Document> customers = db.getCollection("customers");
            MongoCollection<Document> employees = db.getCollection("employees");
            FindIterable<Document> customersIterable = customers.find();
            FindIterable<Document> employeesIterable = employees.find();

            ArrayList<Person> people = new ArrayList<>();
            customersIterable.forEach(document -> people.add(Person.fromDoc(document)));
            employeesIterable.forEach(document -> people.add(Person.fromDoc(document)));
            return people;
        } catch (Exception e) {
            System.out.println("Error getting people: " + e.getMessage());
            return null;
        }
    }
}
