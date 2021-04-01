package views.admin.createMember;

import controllers.MemberController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Address;
import models.LibraryMember;
import views.admin.Admin;

import java.io.IOException;

public class CreateMember {

    @FXML
    TextField firstName;

    @FXML
    TextField lastName;

    @FXML
    TextField telephoneNumber;

    @FXML
    TextField city;

    @FXML
    TextField state;

    @FXML
    TextField street;

    @FXML
    TextField zipCode;

    public void createMember(ActionEvent event) throws IOException {
        // TODO: add validation
        MemberController memberController = new MemberController();
        Address address = new Address(state.getText(), street.getText(), city.getText(), Double.parseDouble(zipCode.getText()));
        LibraryMember member = new LibraryMember("1", firstName.getText(), lastName.getText(), telephoneNumber.getText(), address);
        memberController.createMember(member);
        Admin.routeToViewMembers();
    }
}
