package models;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 7508488940058530471L;

    private String state;
    private String street;
    private String city;
    private int zipCode;

    public Address(String state, String street, String city, int zipCode) {
        this.state = state;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + state;
    }
}
