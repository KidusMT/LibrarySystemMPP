package views.librarian.updateCheckout;

import javafx.event.ActionEvent;
import views.View;

import java.io.IOException;

public class UpdateCheckout {
    public void createCheckout(ActionEvent event) {
    }

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        View.routeToViewCheckouts();
    }
}
