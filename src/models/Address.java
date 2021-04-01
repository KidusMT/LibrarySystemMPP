package models;

import java.io.Serializable;

public class Address implements Serializable {
    private String state;
    private String street;
    private String city;
    private double zipCode;

    public Address(String state, String street, String city, double zipCode) {
        this.state = state;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public double getZipCode() {
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
}
