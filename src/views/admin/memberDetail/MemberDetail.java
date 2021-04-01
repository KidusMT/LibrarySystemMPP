package views.admin.memberDetail;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Book;
import models.LibraryMember;

import java.net.URL;
import java.util.ResourceBundle;

public class MemberDetail implements Initializable {
    private LibraryMember libraryMember;
    @FXML private Label label;
    public MemberDetail(LibraryMember  libraryMember){
        this.libraryMember = libraryMember;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText(libraryMember.getFirstName() + " " + libraryMember.getLastName());
    }
}
