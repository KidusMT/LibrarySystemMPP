package views.login;

import common.utils.Authorization;
import common.utils.UserSession;
import controllers.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import models.User;
import views.View;

public class Login {

    @FXML
    Label errorLabel;
    @FXML
    private TextField userIdField;
    @FXML
    private PasswordField passwordField;
    private View view;

    public Login() {
        view = new View();
    }

    public void clearFields() {
        userIdField.setText("");
        passwordField.setText("");
        errorLabel.setText("");
    }

    @FXML
    public void loginButtonController(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        UserController userController = new UserController();
        if (isLoginFormValid()) {
            User user = userController.authenticateUser(userIdField.getText(), passwordField.getText());
            if (user == null) {
                errorLabel.setText("Incorrect Id/Password");
            } else if (user.getAuthorization().equals(Authorization.ADMIN)) {
                UserSession.createInstance("Administrator", Authorization.ADMIN);
                clearFields();
                view.start(stage);
                Main.primaryStage.hide();
            } else if (user.getAuthorization().equals(Authorization.LIBRARIAN)) {
                UserSession.createInstance("Librarian", Authorization.LIBRARIAN);
                clearFields();
                view.start(stage);
                Main.primaryStage.hide();
            } else if (user.getAuthorization().equals(Authorization.BOTH)) {
                UserSession.createInstance("SuperAdministrator", Authorization.BOTH);
                clearFields();
                view.start(stage);
                Main.primaryStage.hide();
            }
        }
    }

    public boolean isLoginFormValid() {
        if (userIdField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            errorLabel.setText("Please, enter a non-empty username and password.");
            return false;
        } else if (userIdField.getText().isEmpty()) {
            errorLabel.setText("Please, enter a non-empty username.");
            return false;
        } else if (passwordField.getText().isEmpty()) {
            errorLabel.setText("Please, enter a non-empty password.");
            return false;
        }
        return true;
    }
}
