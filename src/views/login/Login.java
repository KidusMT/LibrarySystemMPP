package views.login;

import common.utils.Authorization;
import common.utils.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import views.admin.Admin;
import views.librarian.Librarian;
import views.superAdmin.SuperAdmin;

public class Login {

    @FXML
    Label errorLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    private Admin admin;
    private Librarian librarian;
    private SuperAdmin superAdmin;

    public Login() {
        admin = new Admin();
        librarian = new Librarian();
        superAdmin=new SuperAdmin();
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        errorLabel.setText("");
    }

    @FXML
    public void loginButtonController(ActionEvent event) throws Exception {
        Stage stage = new Stage();

        if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")) {
            UserSession.createInstance("Administrator", Authorization.ADMIN);
            clearFields();
            admin.start(stage);
            Main.primaryStage.hide();
        } else if (usernameField.getText().equals("member") && passwordField.getText().equals("member")) {
            UserSession.createInstance("Librarian", Authorization.LIBRARIAN);
            clearFields();
            librarian.start(stage);
            Main.primaryStage.hide();
        } else if (usernameField.getText().equals("super") && passwordField.getText().equals("super")) {
            UserSession.createInstance("SuperAdministrator",Authorization.BOTH);
            clearFields();
            admin.start(stage);
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
}
