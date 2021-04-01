package views.admin.viewBooks;

import javafx.event.ActionEvent;
import views.admin.Admin;

import java.io.IOException;

public class ViewBooks {
    public void navigateToMembersHandler(ActionEvent event) throws IOException {
        Admin.routeToViewMembers();
    }
    public void navigateToCreateBook(ActionEvent event) throws IOException{
        Admin.routeToCreateBook();
    }
}
