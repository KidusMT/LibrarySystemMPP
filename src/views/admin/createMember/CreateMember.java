package views.admin.createMember;

import controllers.MemberController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import views.admin.Admin;

import java.io.IOException;
import java.util.Random;

public class CreateMember {

    @FXML
    TextField firstName;

    @FXML
    Label memberFormError;

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
        if (isMemberFormValid()) {
            MemberController memberController = new MemberController();
            int memberId =new Random().nextInt(1000-1) + 1;
            memberController.addNewMember(""+memberId, firstName.getText(), lastName.getText(), telephoneNumber.getText(), state.getText(), street.getText(), city.getText(), Integer.parseInt(zipCode.getText()));
            Admin.routeToViewMembers();
        }
    }

    public boolean isMemberFormValid() {
        memberFormError.setTextFill(Color.RED);
        if (firstName.getText().trim().isEmpty() || lastName.getText().trim().isEmpty() || telephoneNumber.getText().trim().isEmpty()
            || state.getText().trim().isEmpty() || city.getText().trim().isEmpty() || street.getText().trim().isEmpty() || zipCode.getText().isEmpty()) {
            memberFormError.setText("Please fill out all fields");
            return false;
        } else {
            try {
                int zip = Integer.parseInt(zipCode.getText());
            } catch (NumberFormatException ex) {
                memberFormError.setText("Please input number for Zip code");
                return false;
            }
        }
        return true;
    }

    public void navigateToViewMembers(ActionEvent event) throws IOException {
        Admin.routeToViewMembers();
    }
}
