package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
    private final String checkoutId;
    private final LibraryMember checkedOutBy;
    private final List<Fine> fines;

    public CheckoutRecord(String checkoutId, LibraryMember checkedOutBy) {
        this.checkoutId = checkoutId;
        this.checkedOutBy = checkedOutBy;
        this.fines = new ArrayList<>();
    }

    public LibraryMember getCheckedOutBy() {
        return checkedOutBy;
    }

    public LibraryMember checkedOutByProperty() {
        return checkedOutBy;
    }


    public List<Fine> finesProperty() {
        return fines;
    }

    public String getCheckoutId() {
        return checkoutId;
    }


    public List<Fine> getFines() {
        return fines;
    }

}
