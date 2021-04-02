package views.admin.createBook;

import controllers.BookController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import models.Address;
import models.Author;
import views.admin.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateBook {

    @FXML
    TextField title;
    @FXML
    Label bookErrorLabel;
    @FXML
    TextField isbn;
    @FXML
    ComboBox maxCheckoutLength;
    @FXML
    ListView<String> authorListView;
    Dialog<String> dialog = new Dialog<>();
    Label authorFormError = new Label();
    TextField firstName = new TextField();
    TextField lastName = new TextField();
    TextField telephoneNo = new TextField();
    TextField street = new TextField();
    TextField state = new TextField();
    TextField city = new TextField();
    TextArea bio = new TextArea();
    TextField zipCode = new TextField();
    private List<Author> authorList = new ArrayList<>();

    @FXML
    public void initialize() {
        maxCheckoutLength.setItems(FXCollections.observableArrayList(7, 21));
        authorForm();
    }


    public void createBookHandler(ActionEvent event) throws IOException {
        if (isBookValid()) {
            BookController bookController = new BookController();
            bookController.newBook(isbn.getText(), title.getText(), (int) maxCheckoutLength.getValue(), authorList);
            Admin.routeViewBooks();
        }
    }

    public boolean isBookValid() {
        bookErrorLabel.setTextFill(Color.RED);
        if (isbn.getText().trim().isEmpty() || title.getText().trim().isEmpty()) {
            bookErrorLabel.setText("Please fill all fields");
            return false;
        } else if (authorList.isEmpty()) {
            bookErrorLabel.setText("Book must have at least one author");
            return false;
        }
        return true;
    }

    public void navigateToViewBooks(ActionEvent event) throws IOException {
        Admin.routeViewBooks();
    }

    public void addAuthorDialog(ActionEvent event) {
        dialog.setWidth(620);
        dialog.setHeight(450);
        dialog.getDialogPane().setPadding(new Insets(20));
        dialog.setTitle("Add Author");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && isAuthorFormValid())
            authorFormHandler();
    }

    public void authorForm() {
        Label formTitle = new Label("Add Author");
        formTitle.setFont(new Font(20));

        Label personalInformation = new Label("Personal Information");
        firstName.setPromptText("First Name");
        firstName.setMinWidth(350);

        lastName.setPromptText("Last Name");
        lastName.setMinWidth(350);

        bio.setMinWidth(350);
        bio.setPrefRowCount(4);
        bio.setPromptText("Bio..");

        telephoneNo.setPromptText("Telephone number");
        telephoneNo.setMinWidth(350);

        Label addressInformation = new Label("Address Information");
        street.setPromptText("Street");
        street.setMinWidth(350);


        state.setPromptText("State");
        state.setMinWidth(350);

        city.setPromptText("City");
        city.setMinWidth(350);

        zipCode.setPromptText("Zip code");
        zipCode.setMinWidth(350);

        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(formTitle, personalInformation, firstName, lastName, bio, telephoneNo, addressInformation, street, state, city, zipCode, authorFormError);
        vBox.setSpacing(10);

        dialog.getDialogPane().setContent(vBox);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE));
    }

    public boolean isAuthorFormValid() {
        authorFormError.setTextFill(Color.RED);
        if (firstName.getText().trim().isEmpty() || lastName.getText().trim().isEmpty() || telephoneNo.getText().trim().isEmpty() ||
                state.getText().trim().isEmpty() || street.getText().trim().isEmpty() || city.getText().trim().isEmpty() || zipCode.getText().trim().isEmpty()) {
            authorFormError.setText("Please fill out all fields");
            return false;
        } else {
            try {
                int zip = Integer.parseInt(zipCode.getText());
            } catch (NumberFormatException ex) {
                authorFormError.setText("Please input number for Zip code");
                return false;
            }
        }
        return true;
    }

    public void authorFormHandler() {
        if (isAuthorFormValid()) {
            Address address = new Address(state.getText(), street.getText(), city.getText(), Integer.parseInt(zipCode.getText()));
            Author author = new Author(firstName.getText(), lastName.getText(), telephoneNo.getText(), address, bio.getText());
            authorList.add(author);
            authorListView.getItems().add("" + author.getFirstName() + " " + author.getLastName());
        }
    }
}
