# MongoDB Shop

## Modules
+ mongodb-driver-sync
+ org.json

## Kod
+ Model: Person
    + name
    + age
    + address
    + toJson()
    + fromJson()
  Employee : subclass of Person
    + employeeId
  Customer : subclass of Person
    + customerId
+ Facade: MongoDBPerson
    + insertOne(Person : person) = insert a person
    + createIndex() = create index
    + findByEmployeeId(employeeId : String) = find by employeeId
    + findByCustomerId(customerId : String) = find by customerId
    + delete(id : String) = delete by id
    + updateName(index, new values) = update by id
    + findByName(name : String) = find by name
    + findByObjectId(id : String) = find by ObjectId
    + connect() = connect to database
    + getAllCustomers() = get all customers
    + getAllEmployees() = get all employees
    + getAllPeople() = get all people
  KeyHandler
    + loadProperties()
    + getConnectionStringFromFile()