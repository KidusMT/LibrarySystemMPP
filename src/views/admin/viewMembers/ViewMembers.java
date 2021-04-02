package views.admin.viewMembers;

import common.utils.UserSession;
import controllers.ControllerInterface;
import controllers.MemberController;
import controllers.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import models.Address;
import models.Author;
import models.Book;
import models.LibraryMember;
import views.admin.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ViewMembers implements Initializable {
    @FXML private TableView tableView;
    @FXML private TableColumn idCol;
    @FXML private TableColumn fNameCol;
    @FXML private TableColumn lNameCol;
    @FXML private TableColumn telephoneCol;
    @FXML private TableColumn addressCol;
    @FXML private TextField searchText;

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
    public void searchMemberHandler(ActionEvent event){
        String searchString = searchText.getText();

        List<LibraryMember> members = new ArrayList<>();
        for(LibraryMember member : allMembers()){
            if(member.getFirstName().equals(searchString) || member.getLastName().equals(searchString) || member.getMemberId().equals(searchString)) members.add(member);
        }
        populateTable(members);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	MemberController m = new MemberController();
        populateTable(m.getAllMembers());
    }
    public void populateTable(List<LibraryMember> memberList){
        idCol.setCellValueFactory(new PropertyValueFactory<LibraryMember,String >("memberId"));
        fNameCol.setCellValueFactory(new PropertyValueFactory<LibraryMember,String>("firstName"));
        lNameCol.setCellValueFactory(new PropertyValueFactory<LibraryMember,String>("lastName"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<LibraryMember,String>("telephone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<LibraryMember,String>("address"));
        tableView.getItems().setAll(memberList);
    }
    private List<LibraryMember> allMembers(){
        MemberController memberController = new MemberController();
        return memberController.getAllMembers();
    }
}