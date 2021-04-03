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
import models.LibraryMember;
import views.librarian.Librarian;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private static LibraryMember libraryMember;

    public static void newInstance(LibraryMember record){
        libraryMember = record;
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    CheckoutEntityController entityController;
    BookController bookController;
    List<String> books = new ArrayList<>();
    List<Book> bookListDb = new ArrayList<>();
    @FXML
    public void initialize() {
        entityController = new CheckoutEntityController();
        bookController = new BookController();
        bookListDb = bookController.getAllBooks();
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
        Librarian.routeToViewCheckouts();
    }

    public void createCheckoutEntity(ActionEvent event) throws IOException {
//        CheckoutEntityController entityController = new CheckoutEntityController();
//        BookController bookController = new BookController();

        String selectedBook = bookList.getSelectionModel().getSelectedItem().toString();
        System.out.println(selectedBook);
        Book selectedBookCombo = null;
        for (int i = 0; i < bookListDb.size(); i++) {
            if (selectedBook.equals(bookListDb.get(i).getTitle())) {
                selectedBookCombo = bookListDb.get(i);
                break;
            }
        }
        if (selectedBookCombo != null) {
            for (int i = 0; i < selectedBookCombo.getCopies().length; i++) {
                if(selectedBookCombo.getCopies()[i].isAvailable()){
                    int recordId = new Random().nextInt(1000 - 1) + 1;
                    entityController.newCheckoutEntity(String.valueOf(recordId), libraryMember.getMemberId(),
                            dateBorrowed.getValue(), dueDate.getValue(), selectedBookCombo.getCopies()[i]);

                    Librarian.routeToViewCheckouts();
                    break;
                }else{
                    // tell the user there is no book available
                    errorMessage.setText("No book copy found for this book.");
                }

            }
        }
    }

    public void findBook(ActionEvent event) {
        String isbn = bookISBN.getText().trim();
        System.out.println(isbn);
        errorMessage.setText(isbn+" couldn't be found.");
    }
}
