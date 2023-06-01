import org.bson.Document;

public class Employee extends Person {
    private String employeeId;

    public Employee(String name, String age, String address, String employeeId) {
        super(name, age, address);
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public Document toDoc() {
        return new Document("name", getName())
                .append("age", getAge())
                .append("address", getAddress())
                .append("id", employeeId);
    }
}
