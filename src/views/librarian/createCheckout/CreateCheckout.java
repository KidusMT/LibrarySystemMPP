package views.librarian.createCheckout;

import javafx.event.ActionEvent;
import views.librarian.Librarian;

import java.io.IOException;

public class CreateCheckout {
    public void createCheckout(ActionEvent event) {
    }

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        Librarian.routeToViewCheckouts();
    }
}
