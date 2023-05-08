import com.mongodb.client.FindIterable;
import com.mongodb.client.*;
import org.bson.Document;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);

        MongoCollection<Document> customers;
        MongoCollection<Document> employees;

        // Create and connect to the database
        try {
            KeyHandler key = new KeyHandler();
            String connectionString = key.getConnectionStringFromFile("/Documents/Java code stuff/API keys/MongoDB.txt");
            String databaseName = key.GetKey("database");
            MongoClient client = MongoClients.create(connectionString);
            MongoDatabase mongodb = client.getDatabase(databaseName);
            customers = mongodb.getCollection("customers");
            employees = mongodb.getCollection("employees");
        } catch (Exception ex) {
            System.out.println("Error setting up the database" + ex.getMessage());
            return;
        }

        // Insert documents
        Document c1 = Document.parse("{name: 'Bilbo Bags',"
                + "age: '688',"
                + "address: 'Tolkienville',"
                + "customerId: '001'}");
        Document c2 = Document.parse("{name: 'Tony Stork',"
                + "age: '42',"
                + "address: 'Storkville',"
                + "customerId: '002'}");

        customers.insertMany(List.of(c1, c2));

        Document e1 = Document.parse("{name: 'Herman Miller',"
                + "age: '48',"
                + "address: 'Chair City',"
                + "employeeId: '101'}");
        Document e2 = Document.parse("{name: 'Gilbert May',"
                + "age: '21',"
                + "address: 'Desk City',"
                + "employeeId: '102'}");

        employees.insertMany(List.of(e1, e2));

        // Find documents (in two different ways)
        FindIterable<Document> customersIterable = customers.find();
        customersIterable.forEach(document -> System.out.println(document.toJson()));

        FindIterable<Document> employeesIterable = employees.find();
        employeesIterable.forEach(document -> System.out.println(document.toJson()));

        try {
            MongoDBPersonFacade mongoDBPersonFacade = new MongoDBPersonFacade();
            mongoDBPersonFacade.getAllCustomers().forEach(document -> System.out.println(document.toJson()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}