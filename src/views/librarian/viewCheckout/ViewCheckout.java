package views.librarian.viewCheckout;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.Main;
import models.*;
import views.librarian.Librarian;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ViewCheckout {
    private final ObservableList<CheckoutRecord> memberData = FXCollections.observableArrayList();
    private ObservableList<CheckoutEntity> checkoutEntityData = FXCollections.observableArrayList();
    @FXML
    private TableView<CheckoutRecord> memberTable;
    @FXML
    private TableColumn<CheckoutRecord, String> firstNameColumn;
    @FXML
    private TableColumn<CheckoutRecord, String> lastNameColumn;

    @FXML
    private TableView<CheckoutEntity> checkoutEntryTable;
    @FXML
    private TableColumn<CheckoutEntity, String> bookTitleColumn;
    @FXML
    private TableColumn<CheckoutEntity, String> checkoutDateColumn;
    @FXML
    private TableColumn<CheckoutEntity, String> dueDateColumn;

    public void initialize() {
//        UserSession userSession = UserSession.getInstance();
//        userLabel.setText("Hi @" + userSession.getEmail());
        preJava8();
        checkoutEntityData = FXCollections.observableArrayList();
        memberData.add(new CheckoutRecord("1001", new LibraryMember("100", "Hans", "Muster", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
        memberData.add(new CheckoutRecord("1002", new LibraryMember("101", "Ruth", "Mueller", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
        memberData.add(new CheckoutRecord("1003", new LibraryMember("102", "Heinz", "Kurz", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
        memberData.add(new CheckoutRecord("1004", new LibraryMember("103", "Cornelia", "Meier", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
        memberData.add(new CheckoutRecord("1005", new LibraryMember("104", "Werner", "Meyer", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
        memberData.add(new CheckoutRecord("1006", new LibraryMember("105", "Lydia", "Kunz", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
        memberData.add(new CheckoutRecord("1007", new LibraryMember("106", "Anna", "Best", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
        memberData.add(new CheckoutRecord("1008", new LibraryMember("107", "Stefan", "Meier", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
        memberData.add(new CheckoutRecord("1009", new LibraryMember("108", "Martin", "Mueller", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));

        // String entryId, LocalDate date, LocalDate due_date, BookCopy bookCopy, CheckoutRecord checkoutRecord
        CheckoutRecord checkoutRecord = new CheckoutRecord("1001", new LibraryMember("100", "Hans", "Muster", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557)));
        checkoutEntityData.add(new CheckoutEntity("2001", LocalDate.now(), LocalDate.now(), new BookCopy(new Book("123123123", "The Alchemist", 2, new ArrayList<>()), 2, true), checkoutRecord));
        checkoutEntityData.add(new CheckoutEntity("2001", LocalDate.now(), LocalDate.now(), new BookCopy(new Book("123123123", "The Alchemist", 3, new ArrayList<>()), 2, true), checkoutRecord));
        checkoutEntityData.add(new CheckoutEntity("2001", LocalDate.now(), LocalDate.now(), new BookCopy(new Book("123123123", "The Alchemist", 4, new ArrayList<>()), 2, true), checkoutRecord));
        checkoutEntityData.add(new CheckoutEntity("2001", LocalDate.now(), LocalDate.now(), new BookCopy(new Book("123123123", "The Alchemist", 5, new ArrayList<>()), 2, true), checkoutRecord));
        checkoutEntityData.add(new CheckoutEntity("2001", LocalDate.now(), LocalDate.now(), new BookCopy(new Book("123123123", "The Alchemist", 6, new ArrayList<>()), 2, true), checkoutRecord));

        // Add observable list data to the table
        memberTable.setItems(getMemberData());
        checkoutEntryTable.setItems(null);

        showCheckoutEntitiesTable(null);

        memberTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showCheckoutEntitiesTable(newValue));
    }

    private void showCheckoutEntitiesTable(CheckoutRecord checkoutRecord) {
        if (checkoutRecord != null) {
            checkoutEntryTable.setItems(getCheckoutEntityData());
        } else {
//            checkoutEntryTable.getItems().clear();
        }
    }

    private void preJava8() {
//        firstNameColumn.setCellValueFactory(param -> param.getValue().getCheckedOutBy().getFirstName());
//        lastNameColumn.setCellValueFactory(param -> param.getValue().getCheckedOutBy().getLastName());

        firstNameColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getCheckedOutBy().getFirstName()));
        lastNameColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getCheckedOutBy().getLastName()));
        bookTitleColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getBookCopy().getBook().getTitle()));
        checkoutDateColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDate().toString()));
        checkoutDateColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDue_date().toString()));


//        bookTitleColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBookCopy().getBook().getTitle()));
//        checkoutDateColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getDate()));
//        dueDateColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getDue_date()));
    }

    public ObservableList<CheckoutRecord> getMemberData() {
        return memberData;
    }

    public ObservableList<CheckoutEntity> getCheckoutEntityData() {
        return checkoutEntityData;
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = memberTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Librarian.stage);
            alert.setTitle("Delete");
            alert.setHeaderText("Delete Selection");
            alert.setContentText("Are you sure you want to delete this record from the table?");

            Optional<ButtonType> result = alert.showAndWait();
            if (!result.isPresent()) {
                // alert is exited, no button has been pressed.
            } else if (result.get() == ButtonType.OK) {
                //oke button is pressed
                memberTable.getItems().remove(selectedIndex);
            } else if (result.get() == ButtonType.CANCEL) {
                // cancel button is pressed
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Librarian.stage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewRecord() throws IOException {
        Librarian.routeToCreateCheckout();
    }

    @FXML
    private void handleNewEntry() throws IOException {
        CheckoutRecord checkoutEntity = memberTable.getSelectionModel().getSelectedItem();
        if (checkoutEntity != null) {
            Librarian.routeToCreateCheckoutEntry(checkoutEntity);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Librarian.stage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Checkout Record Selected");
            alert.setContentText("Please select a Record in the table first.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleUpdateEntry() throws IOException {
        CheckoutEntity checkoutEntity = checkoutEntryTable.getSelectionModel().getSelectedItem();
        if (checkoutEntity != null) {
            Librarian.routeToUpdateCheckoutEntry(checkoutEntity);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Librarian.stage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    public void navigateToLogin(ActionEvent event) throws IOException {
//        UserSession.destroySession();
        Librarian.stage.hide();
        Main.primaryStage.show();
    }
}
