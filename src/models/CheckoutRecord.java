package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class CheckoutRecord implements Serializable {
    private String checkoutId;
    private LibraryMember checkedOutBy;
    private List<Fine> fines;

    public CheckoutRecord(String checkoutId, LibraryMember checkedOutBy, List<Fine> fines) {
        this.checkoutId = checkoutId;
        this.checkedOutBy = checkedOutBy;
        this.fines = fines;
    }

    public String getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(String checkoutId) {
        this.checkoutId = checkoutId;
    }

    public List<Fine> getFines() {
        return fines;
    }

    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }
}
