public class Customer extends Person {
    private String customerId;
    public Customer(String name, String age, String address) {
        super(name, age, address);
    }

    public Customer(String customerId, String name, String age, String address) {
        super(customerId, name, age, address);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
