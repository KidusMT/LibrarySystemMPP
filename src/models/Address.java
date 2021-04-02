package models;

import javafx.beans.property.*;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 7508488940058530471L;

    private StringProperty state;
    private StringProperty street;
    private StringProperty city;
    private SimpleIntegerProperty zipCode;

    public Address(String state, String street, String city, int zipCode) {
        this.state = new SimpleStringProperty(state);
        this.street = new SimpleStringProperty(street);
        this.city = new SimpleStringProperty(city);
        this.zipCode = new SimpleIntegerProperty(zipCode);
    }

    public SimpleIntegerProperty getZipCode() {
        return zipCode;
    }

    public StringProperty getState() {
        return state;
    }

    public StringProperty getCity() {
        return city;
    }

    public StringProperty getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return street+", "+city+", "+state;
    }
}
