import org.bson.Document;

public class Customer extends Person {
    private String customerId;

    public Customer(String name, String age, String address, String customerId) {
        super(name, age, address);
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public Document toDoc() {
        return new Document("name", getName())
                .append("age", getAge())
                .append("address", getAddress())
                .append("id", customerId);
    }
}
