package models;

import java.io.Serializable;

final public class LibraryMember extends Person implements Serializable {
    private static final long serialVersionUID = -2226197306790714013L;
    private String memberId;


    public LibraryMember(String memberId, String firstName, String lastName, String telephoneNo, Address address) {
        super(firstName, lastName, telephoneNo, address);
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName().get() + " " + getLastName().get() +
                ", " + getTelephone() + " " + getAddress();
    }
}
