import org.bson.Document;

public class Person {
    private String name;
    private String age;
    private String address;
    private String id;

    public Person(String name, String age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Person(String name, String age, String address, String id) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.id = id;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "name: " + name + "\n" +
                "age: " + age + "\n" +
                "address: " + address + "\n" +
                "id: " + id;
    }


    public static Person fromDoc(Document doc) {
        if(doc== null) {
            return new Person("", "", "", ""); // om dokumentet är null så returnerar vi null (för att inte få nullpointerexception
        }
        return new Person(
                doc.getString("name"),
                doc.getString("age"),
                doc.getString("address"),
                doc.getString("id"));
    }
    public static Person fromJson(String json) {
        Document doc = Document.parse(json); // Skapar ett bson dokument från json
        return fromDoc(doc);
    }

    public Document toDoc() {
        Document doc = new Document();
        doc.append("name", name)
                .append("age", age)
                .append("address", address)
                .append("id", id);
        return doc;
    }

    public String toJson() {
        return toDoc().toJson();
    }
}
