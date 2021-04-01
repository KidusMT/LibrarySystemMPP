package models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
    private String checkoutId;
    private ObjectProperty<LibraryMember> checkedOutBy;
    private ObjectProperty<List<Fine>> fines;

    public CheckoutRecord(String checkoutId, LibraryMember checkedOutBy) {
        this.checkoutId = checkoutId;
        this.checkedOutBy = new SimpleObjectProperty<LibraryMember>(checkedOutBy);
        this.fines = new SimpleObjectProperty<List<Fine>>(new ArrayList<>());
    }

    public LibraryMember getCheckedOutBy() {
        return checkedOutBy.get();
    }

    public LibraryMember checkedOutByProperty() {
        return checkedOutBy.get();
    }

    public void setCheckedOutBy(LibraryMember checkedOutBy) {
        this.checkedOutBy.set(checkedOutBy);
    }

    public List<Fine> finesProperty() {
        return fines.get();
    }

    public String getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(String checkoutId) {
        this.checkoutId = checkoutId;
    }

    public List<Fine> getFines() {
        return fines.get();
    }

    public void setFines(List<Fine> fines) {
        this.fines.set(fines);
    }
}
