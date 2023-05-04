import java.io.FileInputStream;
import java.util.Properties;

public class KeyHandler {
    private String connectionString;
    private final String keyFilePath = System.getProperty("user.home") + "/Documents/Java code stuff/API keys/shop.txt";

    public KeyHandler(String connString) {

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

    public void getConnectionStringFromFile() {
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream(keyFilePath);
            prop.load(input);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }
        setConnectionString(prop.getProperty("connectionString"));
    }

    public String GetKey(String string) {
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream(keyFilePath);
            prop.load(input);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return prop.getProperty(string);
    }
}
