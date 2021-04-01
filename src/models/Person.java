package models;

public class Person {
    private static final long serialVersionUID = 3665880920647848288L;
    private String firstName;
    private String lastName;
    private String telephone;
    private Address address;

    public Person(String firstName, String lastName, String telephoneNo, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephoneNo;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public Address getAddress() {
        return address;
    }
}
