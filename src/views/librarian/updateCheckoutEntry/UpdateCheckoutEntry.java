package views.librarian.updateCheckoutEntry;

import javafx.event.ActionEvent;
import views.librarian.Librarian;

import java.io.IOException;

public class UpdateCheckoutEntry {

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        Librarian.routeToViewCheckouts();
    }
}
