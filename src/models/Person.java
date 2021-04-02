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
        this.address = new SimpleObjectProperty<>(address);
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
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }
    public void setAddress(Address address) {
        this.address.set(address);
    }
}
