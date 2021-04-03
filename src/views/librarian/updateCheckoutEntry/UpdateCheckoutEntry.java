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
import java.util.ArrayList;
import java.util.List;

public class UpdateCheckoutEntry {

    private static CheckoutEntity checkoutEntity;
    private static List<Book> bookListDb;
    List<String> books = new ArrayList<>();
    private static CheckoutEntityController entityController;
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
    public static void createInstance(CheckoutEntity entity, CheckoutEntityController eController, List<Book> bookList) {
        checkoutEntity = entity;
        entityController = eController;
        bookListDb = bookList;
    }

    @FXML
    public void initialize() {
        for (int i = 0; i < bookListDb.size(); i++) {
            books.add(bookListDb.get(i).getTitle());
        }

        bookList.setItems(FXCollections.observableArrayList(books));
        dateBorrowed.setValue(checkoutEntity.getDate());
        dueDate.setValue(checkoutEntity.getDueDate());

        if (checkoutEntity != null) {
            firstName.setDisable(true);
            lastName.setDisable(true);
        }
    }

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        View.routeToViewCheckouts();
    }

    public void updateCheckoutEntity(ActionEvent event) {

    }

    public void findBook(ActionEvent event) {
        String isbn = bookISBN.getText().trim();
        System.out.println(isbn);
        errorMessage.setText(isbn + " couldn't be found.");
    }
}
