package views.librarian.viewCheckouts;

import common.utils.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewCheckout {
    @FXML
    Label userLabel;

    public void initialize() {
        UserSession userSession = UserSession.getInstance();
        userLabel.setText("Hi @" + userSession.getEmail());
    }
}
