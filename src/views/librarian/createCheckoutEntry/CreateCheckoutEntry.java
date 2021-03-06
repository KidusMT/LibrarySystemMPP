package views.librarian.createCheckoutEntry;

import controllers.BookController;
import controllers.CheckoutEntityController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Book;
import models.BookCopy;
import models.CheckoutEntity;
import models.LibraryMember;
import views.View;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateCheckoutEntry {

    private static LibraryMember libraryMember;
    String selectedBook = "";
    Book selectedBookCombo = null;
    BookCopy selectedBookCopyCombo = null;
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
    private ComboBox bookList;
    @FXML
    private Label errorMessage;
    private static List<Book> bookListDb;
    public static void newInstance(LibraryMember record, CheckoutEntityController eController, List<Book> bookList) {
        bookListDb = bookList;
        entityController = eController;
        libraryMember = record;
    }

    @FXML
    public void initialize() {
        entityController = new CheckoutEntityController();

        for (int i = 0; i < bookListDb.size(); i++) {
            books.add(bookListDb.get(i).getTitle());
        }

        bookList.setItems(FXCollections.observableArrayList(books));

        dateBorrowed.setValue(LocalDate.now());
        firstName.setText(libraryMember.getFirstName());
        lastName.setText(libraryMember.getLastName());
        firstName.setDisable(true);
        lastName.setDisable(true);
    }

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        View.routeToViewCheckouts();
    }

    public void createCheckoutEntity(ActionEvent event) throws IOException {
        if (selectedBookCombo != null) {
            for (int i = 0; i < selectedBookCombo.getCopies().length; i++) {
                if (selectedBookCombo.getCopies()[i].isAvailable()) {
                    selectedBookCopyCombo = selectedBookCombo.getCopies()[i];
                    int recordId = new Random().nextInt(1000 - 1) + 1;

                    entityController.newCheckoutEntity(String.valueOf(recordId), libraryMember.getMemberId(),
                            dateBorrowed.getValue(), dueDate.getValue(), null, selectedBookCopyCombo, 0.0,
                            null, 0);

                    View.routeToViewCheckouts();
                    break;
                } else {
                    // tell the user there is no book available
                    errorMessage.setText("No book copy found for this book.");
                }
            }
        }
    }

    public void comboAction(ActionEvent event) {
        selectedBook = bookList.getSelectionModel().getSelectedItem().toString();
        System.out.println(selectedBook);

        for (int i = 0; i < bookListDb.size(); i++) {
            if (selectedBook.equals(bookListDb.get(i).getTitle())) {
                selectedBookCombo = bookListDb.get(i);
                break;
            }
        }

        if(selectedBookCombo!=null){
            int maxDateLength = selectedBookCombo.getMaxCheckoutLength();
            LocalDate due= dateBorrowed.getValue().plusDays(maxDateLength);
            dueDate.setValue(due);
        }
    }
}
