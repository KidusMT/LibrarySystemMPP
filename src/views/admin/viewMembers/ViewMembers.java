package views.admin.viewMembers;

import javafx.event.ActionEvent;
import views.admin.Admin;

import java.io.IOException;

public class ViewMembers {
    public void navigateToBooksHandler(ActionEvent event) throws IOException {
        Admin.routeViewBooks();
    }
}