package views.admin.createBook;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.Author;
import views.admin.Admin;

import java.io.IOException;

public class CreateBook {
    @FXML
    TextField title;
    @FXML
    TextField isbn;
    @FXML
    ComboBox maxCheckoutLength;
    @FXML
    ListView<Author> authorListView;

    @FXML
    public void initialize() {
        maxCheckoutLength.setItems(FXCollections.observableArrayList("3 days", "7 days"));
    }


    public void createBookHandler(ActionEvent event) throws IOException {
        Admin.routeViewBooks();
    }

}
