import java.io.FileInputStream;
import java.util.Properties;

public class KeyHandler {
    private String connectionString;
    private String databaseName;
    private String keyFilePath;
    private Properties properties;

    public KeyHandler() {
        String keyFilePath = System.getProperty("user.home") + "/Documents/Java code stuff/API keys/MongoDB.txt";
        this.keyFilePath = keyFilePath;
        properties = loadProperties();
        connectionString = properties.getProperty("connectionString");
        databaseName = properties.getProperty("database");
    }

    // Load the properties file
    private Properties loadProperties() {
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream(keyFilePath);
            prop.load(input);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return prop;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getKeyFilePath() {
        return keyFilePath;
    }

    // Get the connection string from the properties file
    public String getConnectionStringFromFile(String keyFilePath) {
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream(System.getProperty("user.home") + keyFilePath);
            prop.load(input);
            setConnectionString(prop.getProperty("connectionString"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return connectionString;
    }

    // Get the key String from the properties file
    public String GetKey(String key) {

        return properties.getProperty(key);
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
