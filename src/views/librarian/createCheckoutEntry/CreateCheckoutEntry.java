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
import models.LibraryMember;
import views.View;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CreateCheckoutEntry {

    private static LibraryMember libraryMember;
    String selectedBook = "";
    Book selectedBookCombo = null;
    BookCopy selectedBookCopyCombo = null;
    CheckoutEntityController entityController;
    BookController bookController;
    List<String> books = new ArrayList<>();
    List<Book> bookListDb = new ArrayList<>();
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

    public static void newInstance(LibraryMember record) {
        libraryMember = record;
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @FXML
    public void initialize() {
        entityController = new CheckoutEntityController();
        bookController = new BookController();
        bookListDb = bookController.getAllBooks().stream().filter(book -> book.isAvailable()).collect(Collectors.toList());
//        books
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
//        CheckoutEntityController entityController = new CheckoutEntityController();
//        BookController bookController = new BookController();

//        selectedBook = bookList.getSelectionModel().getSelectedItem().toString();
//        System.out.println(selectedBook);
//
//        for (int i = 0; i < bookListDb.size(); i++) {
//            if (selectedBook.equals(bookListDb.get(i).getTitle())) {
//                selectedBookCombo = bookListDb.get(i);
//                break;
//            }
//        }
        if (selectedBookCombo != null) {
            for (int i = 0; i < selectedBookCombo.getCopies().length; i++) {
                if (selectedBookCombo.getCopies()[i].isAvailable()) {
                    selectedBookCopyCombo = selectedBookCombo.getCopies()[i];
                    int recordId = new Random().nextInt(1000 - 1) + 1;
                    entityController.newCheckoutEntity(String.valueOf(recordId), libraryMember.getMemberId(),
                            dateBorrowed.getValue(), dueDate.getValue(), selectedBookCopyCombo);

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

    public void findBook(ActionEvent event) {
        String isbn = bookISBN.getText().trim();
        System.out.println(isbn);
        errorMessage.setText(isbn + " couldn't be found.");
    }
}
