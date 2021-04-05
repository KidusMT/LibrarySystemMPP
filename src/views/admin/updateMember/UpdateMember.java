package views.admin.updateMember;

import controllers.MemberController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import models.LibraryMember;
import views.View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateMember implements Initializable {
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

    LibraryMember member;
    public void updateMember(ActionEvent event) throws IOException {
        if (isMemberFormValid()) {
            MemberController memberController = new MemberController();
            memberController.editMember(""+member.getMemberId(), firstName.getText(), lastName.getText(), telephoneNumber.getText(), state.getText(), street.getText(), city.getText(), Integer.parseInt(zipCode.getText()));
            View.routeToViewMembers();
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
        View.routeToViewMembers();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        member = MemberSingleton.getInstance().getLibraryMember();
        firstName.setText(member.getFirstName());
        lastName.setText(member.getLastName());
        telephoneNumber.setText(member.getTelephone());
        state.setText(member.getAddress().getState());
        street.setText(member.getAddress().getStreet());
        city.setText(member.getAddress().getCity());
        zipCode.setText(""+member.getAddress().getZipCode());
    }
}
