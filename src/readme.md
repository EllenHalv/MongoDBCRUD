# MongoDB Shop Application

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
+ Facade: MongoDBPerson
    + insertOne(Person : person)
    + createIndex()
    + findByEmployeeId(employeeId : String)
    + findByCustomerId(customerId : String)
    + delete(id : String)
    + update(index, new values)
    + findByName(name : String)
    + findByObjectId(id : String)
    + connect()
    + getAllCustomers()
    + getAllEmployees()
    + getAllPeople()