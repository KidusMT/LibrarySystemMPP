package views.librarian.updateCheckoutEntry;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    @FXML
    private TextField bookISBN;
    @FXML
    private Label errorMessage;

    @FXML
    private ComboBox bookList;

    public static void createInstance(CheckoutEntity entity) {
        checkoutEntity = entity;
    }

    //    String entryId, LocalDate date, java.time.LocalDate due_date, BookCopy bookCopy, CheckoutRecord checkoutRecord
    @FXML
    public void initialize() {
        bookList.setItems(FXCollections.observableArrayList("The Alchemist", "Quantum Computer", "Theory of Consciousness"));

        dateBorrowed.setValue(LocalDate.now());
        if (checkoutEntity != null) {
//            firstName.setText(checkoutEntity.getCheckoutRecord().getCheckedOutBy().getFirstName());
//            lastName.setText(checkoutEntity.getCheckoutRecord().getCheckedOutBy().getLastName());
            firstName.setDisable(true);
            lastName.setDisable(true);
        }
    }

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        Librarian.routeToViewCheckouts();
    }

    public void updateCheckoutEntity(ActionEvent event) {

    }

    public void findBook(ActionEvent event) {
        String isbn = bookISBN.getText().trim();
        System.out.println(isbn);
        errorMessage.setText(isbn+" couldn't be found.");
    }
}
