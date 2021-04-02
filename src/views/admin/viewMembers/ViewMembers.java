package views.admin.viewMembers;

import common.utils.UserSession;
import controllers.MemberController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import home.Main;
import models.LibraryMember;
import views.admin.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewMembers implements Initializable {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn idCol;
    @FXML
    private TableColumn fNameCol;
    @FXML
    private TableColumn lNameCol;
    @FXML
    private TableColumn telephoneCol;
    @FXML
    private TableColumn addressCol;
    @FXML
    private TextField searchText;

    private MemberController memberController = new MemberController();

    public void navigateToBooksHandler() throws IOException {
        Admin.routeViewBooks();
    }

    public void navigateToMembersHandler(ActionEvent event) {
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

    public void searchMemberHandler(ActionEvent event) {
        String searchString = searchText.getText();
        List<LibraryMember> members = new ArrayList<>();
        if (searchString.trim().equals(""))
            populateTable(memberController.getAllMembers());
        else {
            for (LibraryMember member : allMembers())
                if (member.getFirstName().get().contains(searchString) || member.getLastName().get().contains(searchString) || member.getMemberId().equals(searchString))
                    members.add(member);

            populateTable(members);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable(memberController.getAllMembers());
    }

    public void populateTable(List<LibraryMember> memberList) {
        idCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("memberId"));
        fNameCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("firstName"));
        lNameCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("lastName"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("telephone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("address"));
        tableView.getItems().setAll(memberList);
    }

    private List<LibraryMember> allMembers() {
        return memberController.getAllMembers();
    }
}