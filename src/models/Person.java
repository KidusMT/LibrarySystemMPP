package models;

import java.io.Serializable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person implements Serializable {
    private static final long serialVersionUID = 3665880920647848288L;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty telephone;
    private ObjectProperty<Address> address;

    public Person(String firstName, String lastName, String telephoneNo, Address address) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.telephone = new SimpleStringProperty(telephoneNo);
        this.address = new SimpleObjectProperty<Address>(address);
    }

    public StringProperty getFirstName() {
        return firstName;
    }
    public StringProperty getLastName() {
        return lastName;
    }
    public StringProperty getTelephone() {
        return telephone;
    }
    public Address getAddress() {
        return address.get();
    }
    public void setFirstName(StringProperty firstName) {
        this.firstName = firstName;
    }
    public void setLastName(StringProperty lastName) {
        this.lastName = lastName;
    }
    public void setTelephone(StringProperty telephone) {
        this.telephone = telephone;
    }
    public void setAddress(Address address) {
        this.address.set(address);
    }
}
