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
    + CRUD
        + delete(index)
        + update(index, new values)
    + findByName()
    + findById()
    + findByCity()
    + createIndex()