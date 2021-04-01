package views.admin.createMember;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Address;
import models.LibraryMember;

public class CreateMember {

    @FXML
    TextField firstName;

    @FXML
    TextField lastName;

    @FXML
    TextField telephoneNumber;

    @FXML
    TextField address;

    @FXML
    TextField city;

    @FXML
    TextField state;

    @FXML
    TextField street;

    @FXML
    TextField zipCode;

    public void createMember(ActionEvent event) {

        Address address = new Address(state.getText(), street.getText(), city.getText(), Double.parseDouble(zipCode.getText()));
        LibraryMember member = new LibraryMember("1", firstName.getText(), lastName.getText(), telephoneNumber.getText(), address);

    }
}
