package views.admin.bookDetail;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Author;
import models.Book;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class BookDetail implements Initializable {
    private Book book;
    @FXML
    private Label label;
    public BookDetail(Book  book){
        this.book = book;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText(book.getTitle());
    }
}
