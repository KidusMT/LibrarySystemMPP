package models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
	private static final long serialVersionUID = 3665880920647848288L;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty telephone;
	private ObjectProperty<Address> address;
	public Person(String f, String l, String t, Address a) {
		firstName = new SimpleStringProperty(f);
		lastName = new SimpleStringProperty(l);
		telephone = new SimpleStringProperty(t);
		address = new SimpleObjectProperty<Address>(a);
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
