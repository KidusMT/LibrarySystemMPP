package views.admin.bookDetail;

import controllers.BookController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import models.Author;
import models.Book;
import views.admin.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookDetail implements Initializable {
    @FXML
    Label title;

    @FXML
    Label isbn;

    @FXML
    Label noOfBookCopies;

    @FXML
    Label maxCheckoutLength;

    @FXML
    VBox authorList;

    Book book = BookSingleton.getInstance().getBook();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setText(book.getTitle());
        isbn.setText(book.getIsbn());
        maxCheckoutLength.setText(book.getMaxCheckoutLength() + " days");
        noOfBookCopies.setText("" + book.getCopies().length);
        for (Author author : book.getAuthors()) {
            Label name = new Label(author.getFirstName() + " " + author.getLastName() + ", " + author.getAddress().getCity() + " " + author.getAddress().getState());
            name.setStyle("-fx-font-weight: bold");
            authorList.getChildren().add(name);
            authorList.getChildren().add(new Label(author.getBio()));
        }
    }

    public void cancelBook(ActionEvent event) throws IOException {
        BookSingleton.destroySession();
        Admin.routeViewBooks();
    }

    public void addBookCopyHandler(ActionEvent event) {
        BookController bookController = new BookController();
        bookController.addBookCopy(BookSingleton.getInstance().getBook());
        noOfBookCopies.setText(""+book.getCopies().length);
    }
}
