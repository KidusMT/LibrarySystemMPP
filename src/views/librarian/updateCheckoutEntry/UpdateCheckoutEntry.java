package views.librarian.updateCheckoutEntry;

import javafx.event.ActionEvent;
import models.CheckoutEntity;
import models.CheckoutRecord;
import views.librarian.Librarian;

import java.io.IOException;
import java.time.LocalDate;

public class UpdateCheckoutEntry {

    private static CheckoutEntity checkoutEntity;

    public static void createInstance(CheckoutEntity entity){
        checkoutEntity = entity;
    }
//    String entryId, LocalDate date, java.time.LocalDate due_date, BookCopy bookCopy, CheckoutRecord checkoutRecord
    public UpdateCheckoutEntry() {

    }

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        Librarian.routeToViewCheckouts();
    }
}
