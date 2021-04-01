package views.admin.viewBooks;

import common.utils.UserSession;
import javafx.event.ActionEvent;
import main.Main;
import views.admin.Admin;

import java.io.IOException;

public class ViewBooks {
    public void navigateToMembersHandler(ActionEvent event) throws IOException {
        Admin.routeToViewMembers();
    }

    public void navigateToCreateBook(ActionEvent event) throws IOException {
        Admin.routeToCreateBook();
    }

    public void navigateToLogin(ActionEvent event) throws IOException {
        UserSession.destroySession();
        Admin.stage.hide();
        Main.primaryStage.show();
    }
}
