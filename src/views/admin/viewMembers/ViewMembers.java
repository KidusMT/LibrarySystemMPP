package views.admin.viewMembers;

import common.utils.UserSession;
import controllers.MemberController;
import javafx.event.ActionEvent;
import main.Main;
import models.LibraryMember;
import views.admin.Admin;

import java.io.IOException;
import java.util.List;

public class ViewMembers {
    private MemberController memberController = new MemberController();

    public void navigateToBooksHandler() throws IOException {
        Admin.routeViewBooks();
    }
    public void getAllMembersHandler(ActionEvent event) {
        List<LibraryMember> memberList = memberController.getAllMembers();
        System.out.println(memberList.isEmpty());
    }

    public void navigateToCreateMember(ActionEvent event) throws IOException {
        Admin.routeToCreateMember();
    }

    public void navigateToLogin(ActionEvent event) throws IOException {
        UserSession.destroySession();
        Admin.stage.hide();
        Main.primaryStage.show();
    }
}