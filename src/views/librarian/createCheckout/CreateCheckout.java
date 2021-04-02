package views.librarian.createCheckout;

import controllers.CheckoutRecordController;
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
import java.util.Random;

public class CreateCheckout {

    List<LibraryMember> libraryMembers = new ArrayList<>();
    @FXML
    private ComboBox membersList;

    @FXML
    public void initialize() {
        membersList.setItems(FXCollections.observableArrayList(7, 21));


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
            names.add(String.format("%s %s", libMem.getFirstName(), libMem.getLastName()));
        }

        membersList.setItems(FXCollections.observableArrayList(names));
    }

    public void createCheckout(ActionEvent event) throws IOException {
        //        if (isRecordValid()) {
        String output = membersList.getSelectionModel().getSelectedItem().toString();
        System.out.println(output);
        LibraryMember selectedLibraryMember = null;
        for (int i = 0; i < libraryMembers.size(); i++) {
            if (output.equals(libraryMembers.get(i).getFirstName() + " " + libraryMembers.get(i).getLastName())) {
                selectedLibraryMember = libraryMembers.get(i);
                break;
            }
        }
        if (selectedLibraryMember != null) {
            CheckoutRecordController recordController = new CheckoutRecordController();
            int recordId = new Random().nextInt(1000 - 1) + 1;
            recordController.newCheckoutRecord(String.valueOf(recordId), selectedLibraryMember);
            Librarian.routeToCreateCheckout();
        } else {
            // show error message saying that there is no selection for library member
            System.out.println("=====> didn't get the selected user");
        }
//        }
    }

    public void navigateToViewCheckoutRecords(ActionEvent event) throws IOException {
        Librarian.routeToViewCheckouts();
    }
}
