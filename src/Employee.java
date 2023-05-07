public class Employee extends Person{
    private String employeeId;
    public Employee(String name, String age, String address) {
        super(name, age, address);
    }

    public Employee(String name, String age, String address, String employeeId) {
        super(employeeId, name, age, address);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
