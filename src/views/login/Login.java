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

public class Login {
    @FXML
    TextField id;

    @FXML
    PasswordField passwordField;
    @FXML
    Label errorLabel;

    private Admin admin;
    private Librarian librarian;

    public Login() {
        admin = new Admin();
        librarian = new Librarian();
    }

    public void loginHandler(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        Main.primaryStage.hide();
        if (id.getText().equals("admin") && passwordField.getText().equals("admin")) {
            UserSession.createInstance("admin@gmail.com", Authorization.ADMIN);
            admin.start(stage);
        } else if (id.getText().equals("member") && passwordField.getText().equals("member")) {
            UserSession.createInstance("member@gmail.com", Authorization.LIBRARIAN);
            librarian.start(stage);
        } else
            errorLabel.setText("Incorrect Id/Password");
    }
}
