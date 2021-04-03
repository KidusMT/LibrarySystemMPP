package views.librarian.updateCheckoutEntry;

import controllers.CheckoutEntityController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Book;
import models.CheckoutEntity;
import views.View;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class UpdateCheckoutEntry {

    private static CheckoutEntity checkoutEntity;
    private static List<Book> bookListDb;
    private static CheckoutEntityController entityController;
    List<String> books = new ArrayList<>();
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
    private TextField fineAmount;
    @FXML
    private TextField overdue;
    @FXML
    private ComboBox bookList;
    private long overdueDays = 0;

    public static void createInstance(CheckoutEntity entity, CheckoutEntityController eController, List<Book> bookList) {
        checkoutEntity = entity;
        entityController = eController;
        bookListDb = bookList;
    }

    @FXML
    public void initialize() {
        int selectedBook = 0;
        for (int i = 0; i < bookListDb.size(); i++) {
            if (checkoutEntity.getBookCopy().getBook().getIsbn().equals(bookListDb.get(i).getIsbn())) {
                selectedBook = i;
            }
            books.add(bookListDb.get(i).getTitle());
        }

        bookList.setItems(FXCollections.observableArrayList(books));
        dateBorrowed.setValue(checkoutEntity.getBorrowedDate());
        dueDate.setValue(checkoutEntity.getDueDate());

        bookList.getSelectionModel().select(selectedBook);

        if (checkoutEntity != null) {
            firstName.setDisable(true);
            lastName.setDisable(true);
        }

        // overdue
        overdueDays = DAYS.between(LocalDate.now(), dueDate.getValue());
        overdue.setText(String.format("%d %s", overdueDays, overdueDays > 0 ? "days" : "day"));

        // fine amount
        fineAmount.setText(String.format("$%a", overdueDays * CheckoutEntity.FINE_RATE));
    }

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        View.routeToViewCheckouts();
    }

    public void updateCheckoutEntity(ActionEvent event) {

    }
}
