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
        idCol.setCellValueFactory(new PropertyValueFactory<Book,String >("memberId"));
        fNameCol.setCellValueFactory(new PropertyValueFactory<Book,String>("firstName"));
        lNameCol.setCellValueFactory(new PropertyValueFactory<Book,String>("lastName"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<Book,String>("telephone"));
        tableView.getItems().setAll(memberList);
        tableView.setRowFactory(tv->{
            TableRow<LibraryMember> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event ->{
                LibraryMember rowData = tableRow.getItem();
                System.out.println(rowData.getFirstName());
                try {
                    Admin.routeToMemberDetail(rowData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return tableRow;
        });
    }
    private List<LibraryMember> allMembers(){
//        List<LibraryMember> members = Arrays.asList(new LibraryMember("121","John","Doe","641 123 123",null),
//                    new LibraryMember("121","Jane","Doe","641 123 456",null));

        ControllerInterface memberController = new SystemController();
        return memberController.getAllMembers();
    }
}