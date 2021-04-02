package views.login;
import common.utils.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import home.Main;
import views.admin.Admin;
import views.librarian.Librarian;

public class Login {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    Label errorLabel;

    private Admin admin;
    private Librarian librarian;

    public Login() {
        admin = new Admin();
        librarian = new Librarian();
    }

    public void clearFields(){
        usernameField.setText("");
        passwordField.setText("");
        errorLabel.setText("");
    }

    @FXML
    public void loginButtonController(ActionEvent event) throws Exception {
        Stage stage = new Stage();

        if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")) {
            UserSession.createInstance("admin@gmail.com", UserSession.Role.Admin);
            clearFields();
            admin.start(stage);
            Main.primaryStage.hide();
        } else if (usernameField.getText().equals("member") && passwordField.getText().equals("member")) {
            UserSession.createInstance("member@gmail.com", UserSession.Role.User);
            clearFields();
            librarian.start(stage);
            Main.primaryStage.hide();
        } else if(usernameField.getText().isEmpty() && passwordField.getText().isEmpty()){
            errorLabel.setText("Please, enter a non-empty username and password.");
        } else if(usernameField.getText().isEmpty()){
            errorLabel.setText("Please, enter a non-empty username.");
        } else if(passwordField.getText().isEmpty()){
            errorLabel.setText("Please, enter a non-empty password.");
        }else {
            errorLabel.setText("Incorrect Id/Password");
        }

    }
}
