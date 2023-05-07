public class Customer extends Person {
    private String customerId;
    public Customer(String name, String age, String address) {
        super(name, age, address);
    }

    public Customer(String name, String age, String address, String customerId) {
        super(customerId, name, age, address);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
