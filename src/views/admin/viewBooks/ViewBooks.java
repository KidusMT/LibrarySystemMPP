package views.admin.viewBooks;

import common.utils.Authorization;
import common.utils.UserSession;
import controllers.BookController;
import controllers.SystemController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import main.Main;
import models.Author;
import models.Book;
import models.BookCopy;
import views.admin.Admin;
import views.librarian.Librarian;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewBooks implements Initializable {
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, String> isbnCol;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, Author> authorCol;
    @FXML
    private TableColumn<Book, Integer> copiesCol;
    @FXML
    private TableColumn<Book, Integer> availableCol;
    @FXML
    private TextField searchText;
    @FXML
    private Button checkoutButton;
    @FXML
    private ImageView checkoutImage;


    private BookController bookController;

    public ViewBooks() {
        bookController = new BookController();
    }

    public void navigateToMembersHandler(ActionEvent event) throws IOException {
        Admin.routeToViewMembers();
    }

    public void navigateToBooksHandler(ActionEvent event) throws IOException {
        Admin.routeViewBooks();
    }

    public void navigateToCreateBook(ActionEvent event) throws IOException {
        Admin.routeToCreateBook();
    }

    public void navigateToCheckout(ActionEvent event) throws Exception {
        Admin.stage.hide();
        new Librarian().start(Admin.stage);
    }

    public void navigateToLogin(ActionEvent event) throws IOException {
        UserSession.destroySession();
        Admin.stage.hide();
        Main.primaryStage.show();
    }

    public void searchBookHandler(ActionEvent event) {
        String searchString = searchText.getText();
        List<Book> books = new ArrayList<>();
        if (searchString.trim().equals(""))
            populateTable(bookController.getAllBooks());
        else {
            for (Book book : allBooks()) {
                if (book.getIsbn().equals(searchString) || book.getTitle().contains(searchString))
                    books.add(book);
            }
            populateTable(books);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserSession user = UserSession.getInstance();
        if (user.getAuthorization().equals(Authorization.ADMIN)) {
            checkoutButton.setVisible(false);
            checkoutImage.setVisible(false);
        }
        SystemController systemController = new SystemController();
        List<Book> bookList = systemController.getAllBooks();
        populateTable(bookList);
    }

    public void populateTable(List<Book> bookList) {
        isbnCol.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, Author>("authors"));
        copiesCol.setCellValueFactory(entry -> new SimpleObjectProperty<Integer>(entry.getValue().getCopies().length));
        availableCol.setCellValueFactory(entry -> {
            int count = 0;
            for (BookCopy bookCopy : entry.getValue().getCopies()) {
                if (bookCopy.isAvailable()) count++;
            }
            new SimpleObjectProperty<Boolean>(entry.getValue().isAvailable());
            return new SimpleObjectProperty<>(count);
        });
        tableView.getItems().setAll(bookList);
        tableView.setRowFactory(tv -> {
            TableRow<Book> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event -> {
                Book rowData = tableRow.getItem();
                try {
                    Admin.routeToBookDetail(rowData);
                } catch (IOException e) {
                }
            });
            return tableRow;
        });
    }

    private List<Book> allBooks() {
        List<Book> books = bookController.getAllBooks();
        return books;
    }
}
