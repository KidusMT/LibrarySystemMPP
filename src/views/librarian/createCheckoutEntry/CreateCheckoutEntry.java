package views.librarian.createCheckoutEntry;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.CheckoutRecord;
import views.librarian.Librarian;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateCheckoutEntry {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private DatePicker dateBorrowed;
    @FXML
    private DatePicker dueDate;

    @FXML
    private ComboBox bookList;

    @FXML
    private TextField bookISBN;
    @FXML
    private Label errorMessage;

    private static CheckoutRecord checkoutRecord;

    public static void newInstance(CheckoutRecord record){
        checkoutRecord = record;
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @FXML
    public void initialize() {
        bookList.setItems(FXCollections.observableArrayList("The Alchemist", "Quantum Computer", "Theory of Consciousness"));

        dateBorrowed.setValue(LocalDate.now());
        firstName.setText(checkoutRecord.getCheckedOutBy().getFirstName().get());
        lastName.setText(checkoutRecord.getCheckedOutBy().getLastName().get());
        firstName.setDisable(true);
        lastName.setDisable(true);
    }

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        Librarian.routeToViewCheckouts();
    }

    public void createCheckoutEntity(ActionEvent event) {
        System.out.println("Creating checkout entity");
    }

    public void findBook(ActionEvent event) {
        String isbn = bookISBN.getText().trim();
        System.out.println(isbn);
        errorMessage.setText(isbn+" couldn't be found.");
    }
}
