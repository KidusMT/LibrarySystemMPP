package views.login;

import common.utils.Authorization;
import common.utils.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import views.admin.Admin;
import views.librarian.Librarian;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    Label errorLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    private Admin admin;
    private Librarian librarian;

    public Login() {
        admin = new Admin();
        librarian = new Librarian();
    }

    @FXML
    public void loginButtonController(ActionEvent event) throws Exception {
        Stage stage = new Stage();

        if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")) {
            UserSession.createInstance("admin@gmail.com", Authorization.ADMIN);
            admin.start(stage);
            Main.primaryStage.hide();
        } else if (usernameField.getText().equals("member") && passwordField.getText().equals("member")) {
            UserSession.createInstance("member@gmail.com", Authorization.LIBRARIAN);
            librarian.start(stage);
            Main.primaryStage.hide();
        } else if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            errorLabel.setText("Please, enter a non-empty username and password.");
        } else if (usernameField.getText().isEmpty()) {
            errorLabel.setText("Please, enter a non-empty username.");
        } else if (passwordField.getText().isEmpty()) {
            errorLabel.setText("Please, enter a non-empty password.");
        } else {
            errorLabel.setText("Incorrect Id/Password");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameField.setText("");
        passwordField.setText("");
    }
}
