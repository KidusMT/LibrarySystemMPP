package views.admin.viewMembers;

import common.utils.UserSession;
import controllers.MemberController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import main.Main;
import models.LibraryMember;
import views.admin.Admin;

import javax.swing.*;
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
    @FXML
    private HBox actionBox;

    private MemberController memberController = new MemberController();
    private LibraryMember selectedMember;

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
                if (member.getFirstName().contains(searchString) || member.getLastName().contains(searchString) || member.getMemberId().equals(searchString))
                    members.add(member);

            populateTable(members);
        }
    }
    public void editMemberHandler(ActionEvent event){
    }
    public void deleteMemberHandler(ActionEvent event){
        memberController.deleteMember(selectedMember.getMemberId());
        populateTable(memberController.getAllMembers());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actionBox.setVisible(false);
        actionBox.setMaxHeight(0);
        populateTable(memberController.getAllMembers());

    }

    public void populateTable(List<LibraryMember> memberList) {
        idCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("memberId"));
        fNameCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("firstName"));
        lNameCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("lastName"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("telephone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("address"));
        tableView.getItems().setAll(memberList);
        tableView.setRowFactory(tv -> {
            TableRow<LibraryMember> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ((! row.isEmpty()) ) {
                     selectedMember = row.getItem();
                    System.out.println(selectedMember);
                    actionBox.setFillHeight(true);
                    actionBox.setVisible(true);
                }
            });
            return row ;
        });
    }

    private List<LibraryMember> allMembers() {
        return memberController.getAllMembers();
    }
}