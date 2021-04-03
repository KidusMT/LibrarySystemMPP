package views.librarian.viewCheckout;

import common.utils.Authorization;
import common.utils.UserSession;
import controllers.CheckoutEntityController;
import controllers.MemberController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import main.Main;
import models.CheckoutEntity;
import models.LibraryMember;
import views.View;

import java.io.IOException;

public class ViewCheckout {
    private final ObservableList<LibraryMember> memberData = FXCollections.observableArrayList();
    CheckoutEntityController entityController;
    MemberController memberController;
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

    @FXML
    ImageView checkOutImage;

    @FXML
    Button checkoutButton;

    @FXML
    ImageView membersImage;

    @FXML
    ImageView booksImage;

    @FXML
    Button bookButton;

    @FXML
    Button memberButton;

    public void navigateToCheckout(ActionEvent event) throws IOException {
        View.routeToViewCheckouts();
    }

    public void initialize() {
        UserSession userSession = UserSession.getInstance();
        if(userSession.getAuthorization().equals(Authorization.LIBRARIAN)){
            memberButton.setVisible(false);
            membersImage.setVisible(false);
            checkoutButton.setVisible(false);
            checkOutImage.setVisible(false);
            bookButton.setVisible(false);
            booksImage.setVisible(false);
        }
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
            checkoutEntityData = FXCollections.observableArrayList();
            checkoutEntityData.addAll(entityController.getCheckoutEntries(checkoutRecord.getMemberId()));
            checkoutEntryTable.setItems(getCheckoutEntityData());
        } else {
            checkoutEntryTable.setItems(checkoutEntityData);
        }
    }

    private void preJava8() {
        firstNameColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getLastName()));
        bookTitleColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getBookCopy().getBook().getTitle()));
        checkoutDateColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDate().toString()));
        dueDateColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDue_date().toString()));
    }

    public void printEntryHandler(ActionEvent event){
        CheckoutEntityController entityController = new CheckoutEntityController();
        memberTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> entityController.printCheckoutEntry(newValue.getMemberId()));

    }

    public ObservableList<LibraryMember> getMemberData() {
        return memberData;
    }

    public ObservableList<CheckoutEntity> getCheckoutEntityData() {
        return checkoutEntityData;
    }

    @FXML
    private void handleNewEntry() throws IOException {
        LibraryMember libraryMember = memberTable.getSelectionModel().getSelectedItem();
        if (libraryMember != null) {
            View.routeToCreateCheckoutEntry(libraryMember);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(View.stage);
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
            View.routeToUpdateCheckoutEntry(checkoutEntity);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(View.stage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Entry Selected");
            alert.setContentText("Please select an entry from the right side table.");

            alert.showAndWait();
        }
    }

    public void navigateToLogin(ActionEvent event) throws IOException {
        UserSession.destroySession();
        View.stage.hide();
        Main.primaryStage.show();
    }

    public void navigateToMembersHandler(ActionEvent event) throws IOException {
        View.routeToViewMembers();
    }

    public void navigateToBooksHandler(ActionEvent event) throws IOException {
        View.routeViewBooks();
    }
}
