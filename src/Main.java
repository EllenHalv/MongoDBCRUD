import com.mongodb.client.FindIterable;
import com.mongodb.client.*;
import org.bson.Document;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);

        MongoCollection<Document> customers;
        MongoCollection<Document> employees;

        try {
            KeyHandler key = new KeyHandler(System.getProperty("user.home) + /Documents/Java code stuff/API keys/MongoDB.txt"));

            String connectionString = key.GetKey("connectionString"); //hämtar connectionstring från filen
            System.out.println("ConnectionString retrieved successfully");

            String databaseName = key.GetKey("database"); // hämtar database name från filen
            System.out.println("Database name retrieved successfully");

            MongoClient client = MongoClients.create(connectionString); // skapar en ny client
            System.out.println("Client created successfully");

            MongoDatabase mongodb = client.getDatabase(databaseName); // skapar en ny database
            System.out.println("Database created successfully");

            customers = mongodb.getCollection("customers"); // skapar en ny collection
            System.out.println("Collection created successfully");

            employees = mongodb.getCollection("employees"); // skapar en ny collection
            System.out.println("Collection created successfully");
        } catch (Exception ex) {
            System.out.println("Error setting up the database" + ex.getMessage());
            return;
        }

        Document c1 = Document.parse("{name: 'Bilbo Bags',"
                + "age: '688',"
                + "address: 'Tolkienville',"
                + "customerId: '001'}");

        Document c2 = Document.parse("{name: 'Tony Stork',"
                + "age: '42',"
                + "address: 'Storkville',"
                + "customerId: '002'}");

        customers.insertMany(List.of(c1, c2)); // lägger till alla customers i collection i DB

        Document e1 = Document.parse("{name: 'Herman Miller',"
                + "age: '48',"
                + "address: 'Chair City',"
                + "employeeId: '101'}");

        Document e2 = Document.parse("{name: 'Gilbert May',"
                + "age: '21',"
                + "address: 'Desk City',"
                + "employeeId: '102'}");

        employees.insertMany(List.of(e1, e2)); // lägger till alla employees i collection i DB

        FindIterable<Document> customersIterable = customers.find();
        customersIterable.forEach(document -> System.out.println(document.toJson())); // skriver ut alla customers i DB

        FindIterable<Document> employeesIterable = employees.find();
        employeesIterable.forEach(document -> System.out.println(document.toJson())); // skriver ut alla employees i DB
        /*try {
            MongoDBPersonFacade mongoDBPersonFacade = new MongoDBPersonFacade();
            mongoDBPersonFacade.insertOne(new Customer("Bilbo Bags", "688", "Tolkienville", "001"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        try {
            MongoDBPersonFacade mongoDBPersonFacade = new MongoDBPersonFacade(); // skapar en ny MongoDBPersonFacade
            mongoDBPersonFacade.getAllCustomers().forEach(document -> System.out.println(document.toJson())); // skriver ut alla customers i DB
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}