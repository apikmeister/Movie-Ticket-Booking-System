public class Customer {

    int id;
    String name;
    String surname;
    String street;
    int streetNumber;
    int plz;
    String city;
    String username;
    String password;
    String type;

    public Customer(String name) {
        this.name = name;
    } //temporary

    public Customer(int id) {
        this.id = id;
    }

    public Customer(String username, String type) {
        this.username = username;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setPLZ(int plz) {
        this.plz = plz;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void printCustomer() {
        System.out.print(surname + " " + name);
        System.out.println(street + " " + streetNumber);
        System.out.println(plz + " " + city);
    }
}
