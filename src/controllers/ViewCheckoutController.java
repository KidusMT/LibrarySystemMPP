package controllers;

import common.utils.UserSession;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import models.Address;
import models.Person;

public class ViewCheckoutController {
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    @FXML
    private TableView<Person> memberTable;
//    @FXML
//    private TableColumn<Person, String> bookTitleColumn;
//    @FXML
//    private TableColumn<Person, String> checkoutDateColumn;
//    private TableColumn<Person, String> Column;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;


//    @FXML
//    private Label firstNameLabel;
//    @FXML
//    private Label lastNameLabel;
//    @FXML
//    private Label streetLabel;
//    @FXML
//    private Label postalCodeLabel;
//    @FXML
//    private Label cityLabel;
//    @FXML
//    private Label birthdayLabel;

    public void initialize() {
//        UserSession userSession = UserSession.getInstance();
//        userLabel.setText("Hi @" + userSession.getEmail());
//        firstName = f;
//        lastName = l;
//        telephone = t;
//        address = a;

        preJava8();

        personData.add(new Person("Hans", "Muster", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", "52557")));
        personData.add(new Person("Ruth", "Mueller", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", "52557")));
        personData.add(new Person("Heinz", "Kurz", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", "52557")));
        personData.add(new Person("Cornelia", "Meier", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", "52557")));
        personData.add(new Person("Werner", "Meyer", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", "52557")));
        personData.add(new Person("Lydia", "Kunz", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", "52557")));
        personData.add(new Person("Anna", "Best", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", "52557")));
        personData.add(new Person("Stefan", "Meier", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", "52557")));
        personData.add(new Person("Martin", "Mueller", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", "52557")));

        // Add observable list data to the table
        memberTable.setItems(getPersonData());
    }

    private void preJava8() {
        firstNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getFirstName();
            }
        });

        lastNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getLastName();
            }
        });
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = memberTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            memberTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
//            alert.setTitle("No Selection");
//            alert.setHeaderText("No Person Selected");
//            alert.setContentText("Please select a person in the table.");
//
//            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
//        Person tempPerson = new Person();
//        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
//        if (okClicked) {
//            mainApp.getPersonData().add(tempPerson);
//        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
//        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
//        if (selectedPerson != null) {
//            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
//            if (okClicked) {
//                showPersonDetails(selectedPerson);
//            }
//
//        } else {
//            // Nothing selected.
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
//            alert.setTitle("No Selection");
//            alert.setHeaderText("No Person Selected");
//            alert.setContentText("Please select a person in the table.");
//
//            alert.showAndWait();
//        }
    }
}
