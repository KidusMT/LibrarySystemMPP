package views.librarian.createCheckout;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import models.Address;
import models.LibraryMember;
import views.librarian.Librarian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateCheckout {

    @FXML
    private ComboBox membersList;

    @FXML
    public void initialize() {
        membersList.setItems(FXCollections.observableArrayList(7, 21));

        List<LibraryMember> libraryMembers = new ArrayList<>();
        libraryMembers.add(new LibraryMember("100", "Hans", "Muster", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557)));
        libraryMembers.add(new LibraryMember("101", "Ruth", "Mueller", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557)));
        libraryMembers.add(new LibraryMember("102", "Heinz", "Kurz", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557)));
        libraryMembers.add(new LibraryMember("103", "Cornelia", "Meier", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557)));
        libraryMembers.add(new LibraryMember("104", "Werner", "Meyer", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557)));
        libraryMembers.add(new LibraryMember("105", "Lydia", "Kunz", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557)));
        libraryMembers.add(new LibraryMember("106", "Anna", "Best", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557)));
        libraryMembers.add(new LibraryMember("107", "Stefan", "Meier", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557)));
        libraryMembers.add(new LibraryMember("108", "Martin", "Mueller", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557)));

        List<String> names = new ArrayList<>();
        for (LibraryMember libMem : libraryMembers) {
            names.add(String.format("%s %s", libMem.getFirstName().get(), libMem.getLastName().get()));
        }

        membersList.setItems(FXCollections.observableArrayList(names));
    }

    public void createCheckout(ActionEvent event) {
    }

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        Librarian.routeToViewCheckouts();
    }
}
