package views.librarian.updateCheckoutEntry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.CheckoutEntity;
import views.librarian.Librarian;

import java.io.IOException;
import java.time.LocalDate;

public class UpdateCheckoutEntry {

    private static CheckoutEntity checkoutEntity;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private DatePicker dateBorrowed;
    @FXML
    private DatePicker dueDate;

    public static void createInstance(CheckoutEntity entity) {
        checkoutEntity = entity;
    }

    //    String entryId, LocalDate date, java.time.LocalDate due_date, BookCopy bookCopy, CheckoutRecord checkoutRecord
    @FXML
    public void initialize() {
        dateBorrowed.setValue(LocalDate.now());
        if (checkoutEntity != null) {
            firstName.setText(checkoutEntity.getCheckoutRecord().getCheckedOutBy().getFirstName().get());
            lastName.setText(checkoutEntity.getCheckoutRecord().getCheckedOutBy().getLastName().get());
            firstName.setDisable(true);
            lastName.setDisable(true);
        }
    }

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        Librarian.routeToViewCheckouts();
    }

    public void updateCheckoutEntity(ActionEvent event) {

    }
}
