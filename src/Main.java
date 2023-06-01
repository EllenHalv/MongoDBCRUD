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
            String keyFilePath = "C:\\Users\\ellen\\Documents\\Java code stuff\\API keys\\MongoDB.txt";
            KeyHandler key = new KeyHandler(keyFilePath);
            String connectionString = key.getConnectionStringFromFile(keyFilePath);
            String databaseName = key.GetKey("database");
            MongoClient client = MongoClients.create(connectionString);
            MongoDatabase mongodb = client.getDatabase(databaseName);
            customers = mongodb.getCollection("customers");
            employees = mongodb.getCollection("employees");
        } catch (Exception ex) {
            System.out.println("Error setting up the database" + ex.getMessage());
            return;
        }

        MongoDBPersonFacade facade = new MongoDBPersonFacade();

        // Insert documents
        facade.insertOne(new Customer("Bilbo Bags", "688", "Tolkienville", "001"));
        facade.insertOne(new Customer("Tony Stork", "42", "Storkville", "002"));
        facade.insertOne(new Employee("Herman Miller", "48", "Chair City", "101"));
        facade.insertOne(new Employee("Gilbert May", "21", "Desk City", "102"));

        // Delete document
        facade.delete("001");

        // Update document
        facade.updateName("002", "Tony Stark");

        // Get documents
        facade.getAllCustomers();
        facade.getAllEmployees();
    }
}
