package views.librarian.viewCheckout;

import controllers.CheckoutEntityController;
import controllers.MemberController;
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
    private final ObservableList<LibraryMember> memberData = FXCollections.observableArrayList();
    private ObservableList<CheckoutEntity> checkoutEntityData = FXCollections.observableArrayList();
    @FXML
    private TableView<LibraryMember> memberTable;
    @FXML
    private TableColumn<LibraryMember, String> firstNameColumn;
    @FXML
    private TableColumn<LibraryMember, String> lastNameColumn;

    @FXML
    private TableView<CheckoutEntity> checkoutEntryTable;
    @FXML
    private TableColumn<CheckoutEntity, String> bookTitleColumn;
    @FXML
    private TableColumn<CheckoutEntity, String> checkoutDateColumn;
    @FXML
    private TableColumn<CheckoutEntity, String> dueDateColumn;

    CheckoutEntityController entityController;
    MemberController memberController;

    public void initialize() {
        entityController = new CheckoutEntityController();
        memberController = new MemberController();

        preJava8();
        checkoutEntityData = FXCollections.observableArrayList();
        memberData.addAll(memberController.getAllMembers());

        // Add observable list data to the table
        memberTable.setItems(getMemberData());
        checkoutEntryTable.setItems(null);

        showCheckoutEntitiesTable(null);

        memberTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showCheckoutEntitiesTable(newValue));
    }

    private void showCheckoutEntitiesTable(LibraryMember checkoutRecord) {
        if (checkoutRecord != null) {
            checkoutEntityData.addAll(entityController.getCheckoutEntries(checkoutRecord.getMemberId()));
            checkoutEntryTable.setItems(getCheckoutEntityData());
        } else {
//            checkoutEntryTable.getItems().clear();
        }
    }

    private void preJava8() {
//        firstNameColumn.setCellValueFactory(param -> param.getValue().getCheckedOutBy().getFirstName());
//        lastNameColumn.setCellValueFactory(param -> param.getValue().getCheckedOutBy().getLastName());

        firstNameColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getLastName()));
        bookTitleColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getBookCopy().getBook().getTitle()));
        checkoutDateColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDate().toString()));
        checkoutDateColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDue_date().toString()));


//        bookTitleColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBookCopy().getBook().getTitle()));
//        checkoutDateColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getDate()));
//        dueDateColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getDue_date()));
    }

    public ObservableList<LibraryMember> getMemberData() {
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
        LibraryMember libraryMember = memberTable.getSelectionModel().getSelectedItem();
        if (libraryMember != null) {
            Librarian.routeToCreateCheckoutEntry(libraryMember);
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
