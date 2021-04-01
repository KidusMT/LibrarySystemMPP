package views.admin.viewBooks;

import javafx.event.ActionEvent;
import views.admin.Admin;

import java.io.IOException;

public class ViewBooks {

    public void createBook(ActionEvent event) throws IOException {
        Admin.routeToCreateBook();
    }
}
