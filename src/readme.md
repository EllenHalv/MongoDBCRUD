# MongoDB Shop

## Beskrivning
MongoDB Shop är ett Java-projekt som tillhandahåller en MongoDB-fasad för att interagera med en MongoDB-databas. Fasaden ger enkla metoder för att hantera Person-objekt och deras subklasser Employee och Customer.

## Installation och användning
Följ dessa steg för att använda MongoDB Shop:

1. Klona projektet från [[projektlänk](https://github.com/dangernooodle/MongoDBCRUD.git)].
2. Importera projektet i din Java-IDE.
3. Se till att du har följande beroenden i ditt projekt:
   - org.mongodb:mongodb-driver-sync [4.9.1]
   - googlecode.sli4j:sli4j-acl [2.0]
   - org.mongodb:mongodb-jdbc [2.0.2]
   Du kan antingen ladda ner biblioteken manuellt och lägga till dem i ditt projekt eller använda en hanterare för beroenden som Maven eller Gradle.

## Modulberoenden
- org.mongodb:mongodb-driver-sync [4.9.1]: Används för att hantera kommunikationen mellan Java och MongoDB-databasen.
- googlecode.sli4j:sli4j-acl [2.0]: Används för att tillhandahålla loggningsfunktioner.
- org.mongodb:mongodb-jdbc [2.0.2]: Används för att möjliggöra anslutning till MongoDB-databasen via JDBC-gränssnittet.

## Kodstruktur
- Model: Person
  - name: Namnet på personen.
  - age: Åldern på personen.
  - address: Adressen för personen.
  - toJson(): Konverterar personobjektet till JSON-format.
  - fromJson(): Skapar ett personobjekt från JSON-data.
- Subklasser till Person:
  - Employee: Underklass av Person med tillägg av employeeId.
  - Customer: Underklass av Person med tillägg av customerId.
- Facade: MongoDBPerson
  - insertOne(Person: person): Lägger till en person i databasen.
  - createIndex(): Skapar en index i databasen.
  - findByEmployeeId(employeeId: String): Söker efter en person med hjälp av employeeId.
  - findByCustomerId(customerId: String): Söker efter en person med hjälp av customerId.
  - delete(id: String): Tar bort en person från databasen med hjälp av id.
  - updateName(index: String, newValues: Map<String, Object>): Uppdaterar namnet på en person med hjälp av id.
  - findByName(name: String): Söker efter personer med hjälp av namn.
  - findByObjectId(id: String): Söker efter en person med hjälp av ObjectId.
  - connect(): Upprättar en anslutning till databasen.
  - getAllCustomers(): Hämtar alla kunder från databasen.
  - getAllEmployees(): Hämtar alla anställda från databasen.
  - getAllPeople(): Hämtar alla personer från databasen.

## Anslutningshantering
För att ansluta till databasen använder MongoDBPerson-fasaden metoden `connect()`. Se till att du har korrekta anslutningsuppgifter för din MongoDB-server i koden.
