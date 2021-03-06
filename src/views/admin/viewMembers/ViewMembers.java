package views.admin.viewMembers;

import common.utils.Authorization;
import common.utils.UserSession;
import controllers.MemberController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import main.Main;
import models.LibraryMember;
import views.View;
import views.admin.updateMember.MemberSingleton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @FXML
    private Button checkoutButton;
    @FXML
    private ImageView checkoutImage;

    private MemberController memberController = new MemberController();
    private LibraryMember selectedMember;

    public void navigateToBooksHandler() throws IOException {
        View.routeViewBooks();
    }

    public void navigateToMembersHandler(ActionEvent event) {
        List<LibraryMember> memberList = memberController.getAllMembers();
        System.out.println(memberList.isEmpty());
    }

    public void navigateToCreateMember(ActionEvent event) throws IOException {
        View.routeToCreateMember();
    }

    public void navigateToLogin(ActionEvent event) throws IOException {
        UserSession.destroySession();
        View.stage.hide();
        Main.primaryStage.show();
    }

    public void navigateToCheckout(ActionEvent event) throws Exception {
        View.routeToViewCheckouts();
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

    public void editMemberHandler(ActionEvent event) throws IOException {
        View.routeToUpdateMember();
    }

    public void deleteMemberHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(View.stage);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Selection");
        alert.setContentText("Are you sure you want to delete this member?");

        Optional<ButtonType> result = alert.showAndWait();
        if (!result.isPresent()) {
            // alert is exited, no button has been pressed.
        } else if (result.get() == ButtonType.OK) {
            //oke button is pressed
            memberController.deleteMember(selectedMember.getMemberId());
            populateTable(memberController.getAllMembers());
        } else if (result.get() == ButtonType.CANCEL) {
            // cancel button is pressed
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserSession user= UserSession.getInstance();
        if(user.getAuthorization().equals(Authorization.ADMIN)){
            checkoutButton.setVisible(false);
            checkoutImage.setVisible(false);
        }
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
                if ((!row.isEmpty())) {
                    selectedMember = row.getItem();
                    if (MemberSingleton.getInstance() == null)
                        MemberSingleton.createInstance(selectedMember);
                    else {
                        MemberSingleton.destroySession();
                        MemberSingleton.createInstance(selectedMember);
                    }
                    actionBox.setFillHeight(true);
                    actionBox.setVisible(true);
                }
            });
            return row;
        });
    }

    private List<LibraryMember> allMembers() {
        return memberController.getAllMembers();
    }
}