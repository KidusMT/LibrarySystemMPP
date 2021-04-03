package views.librarian.updateCheckoutEntry;

import controllers.CheckoutEntityController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Book;
import models.CheckoutEntity;
import models.LibraryMember;
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
    private static LibraryMember libraryMember;
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
    private TextField fineAmount;
    @FXML
    private TextField overdue;
    @FXML
    private DatePicker returnDate;
    @FXML
    private ComboBox bookList;
    private long overdueDays = 0;
    private final double fineAmt = 0;
    @FXML
    private CheckBox paid;

    public static void createInstance(LibraryMember record, CheckoutEntity entity, CheckoutEntityController eController, List<Book> bookList) {
        checkoutEntity = entity;
        entityController = eController;
        bookListDb = bookList;
        libraryMember = record;
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

        firstName.setText(libraryMember.getFirstName());
        lastName.setText(libraryMember.getLastName());

        if(checkoutEntity.getReturnDate()!=null){
            returnDate.setValue(checkoutEntity.getReturnDate());
        }
        // fine amount
        if (returnDate.getValue() == null) {
            // display from today
            // if the date is different for return then calculate when that entered automatically
            if (LocalDate.now().isAfter(dueDate.getValue())) {
                long fnDays = DAYS.between(LocalDate.now(), dueDate.getValue());
                overdueDays = Math.abs(fnDays);
                overdue.setText(String.format("%d %s", Math.abs(fnDays), Math.abs(fnDays) > 0 ? "days" : "day"));
                fineAmount.setText(String.format("$%s", (Math.abs(fnDays) * CheckoutEntity.FINE_RATE)));
            } else {
                overdueDays = 0;
                overdue.setText(String.format("$%s day", 0));
                fineAmount.setText(String.format("$%s", 0));
            }
        } else {
            if (returnDate.getValue().isAfter(dueDate.getValue())) {
                long fnDays = DAYS.between(returnDate.getValue(), dueDate.getValue());
                overdueDays = Math.abs(fnDays);
                overdue.setText(String.format("%d %s", Math.abs(fnDays), Math.abs(fnDays) > 0 ? "days" : "day"));
                fineAmount.setText(String.format("$%s", (Math.abs(fnDays) * CheckoutEntity.FINE_RATE)));
            } else {
                overdueDays = 0;
                fineAmount.setText(String.format("$%s", 0));
                overdue.setText(String.format("$%s day", 0));
            }
        }

        paid.setIndeterminate(overdueDays <= 0);

        returnDate.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue.isAfter(dueDate.getValue())) {
                paid.setIndeterminate(false);
                long fnDays = DAYS.between(newValue, dueDate.getValue());
                overdueDays = Math.abs(fnDays);
                overdue.setText(String.format("%d %s", Math.abs(fnDays), Math.abs(fnDays) > 0 ? "days" : "day"));
                fineAmount.setText(String.format("$%s", (Math.abs(fnDays) * CheckoutEntity.FINE_RATE)));
            } else {
                overdueDays = 0;
                fineAmount.setText(String.format("$%s", 0));
                overdue.setText(String.format("$%s day", 0));
            }
        });
    }

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        View.routeToViewCheckouts();
    }

    public void updateCheckoutEntity(ActionEvent event) throws IOException {
        // todo validation of the inputs
        checkoutEntity.setBorrowedDate(dateBorrowed.getValue());
        checkoutEntity.setDueDate(dueDate.getValue());
        if (returnDate.getValue() == null) {
            // display from today
            // if the date is different for return then calculate when that entered automatically
            if (LocalDate.now().isAfter(dueDate.getValue())) {
                long fnDays = DAYS.between(LocalDate.now(), dueDate.getValue());
                overdueDays = Math.abs(fnDays);
                overdue.setText(String.format("%d %s", Math.abs(fnDays), Math.abs(fnDays) > 0 ? "days" : "day"));
                checkoutEntity.setFineAmount((Math.abs(fnDays) * CheckoutEntity.FINE_RATE));
            } else {
                overdueDays = 0;
                checkoutEntity.setFineAmount(0);
            }
        } else {
            if (returnDate.getValue().isAfter(dueDate.getValue())) {
                long fnDays = DAYS.between(returnDate.getValue(), dueDate.getValue());
                overdueDays = Math.abs(fnDays);
                overdue.setText(String.format("%d %s", Math.abs(fnDays), Math.abs(fnDays) > 0 ? "days" : "day"));
                checkoutEntity.setFineAmount((Math.abs(fnDays) * CheckoutEntity.FINE_RATE));
            } else {
                overdueDays = 0;
                checkoutEntity.setFineAmount(0);
            }
        }
        checkoutEntity.setReturnDate(returnDate.getValue());
        entityController.updateCheckoutEntity(
                checkoutEntity.getEntryId(),
                checkoutEntity.getMemberId(),
                checkoutEntity.getBorrowedDate(),
                checkoutEntity.getDueDate(),
                checkoutEntity.getReturnDate(),
                checkoutEntity.getBookCopy(),
                checkoutEntity.getFineAmount(),
                paid.isSelected() ? LocalDate.now() : null,
                overdueDays);
        View.routeToViewCheckouts();
    }
}
