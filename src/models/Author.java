package models;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
    private static final long serialVersionUID = 7508481940058530471L;

    private String bio;

    public Author(String firstName, String lastName, String telephoneNo, Address address, String bio) {
        super(firstName, lastName, telephoneNo, address);
        this.bio = bio;
    }

    @Override
    public String toString() {
        return getFirstName().get() + " " + getLastName().get();
    }

    public String getBio() {
        return bio;
    }
}
