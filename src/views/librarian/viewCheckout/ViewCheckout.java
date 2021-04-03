package views.librarian.viewCheckout;

import common.utils.Authorization;
import common.utils.UserSession;
import controllers.BookController;
import controllers.CheckoutEntityController;
import controllers.MemberController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import main.Main;
import models.Book;
import models.CheckoutEntity;
import models.LibraryMember;
import views.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewCheckout {
    private final ObservableList<LibraryMember> memberData = FXCollections.observableArrayList();
    CheckoutEntityController entityController;
    MemberController memberController;
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
    BookController bookController;
    List<Book> bookListDb = new ArrayList<>();
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
    private TableColumn<CheckoutEntity, String> fineAmtColumn;
    @FXML
    private TableColumn<CheckoutEntity, String> paidDateColumn;

    public void navigateToCheckout(ActionEvent event) throws IOException {
        View.routeToViewCheckouts();
    }

    @FXML
    public void initialize() {
        bookController = new BookController();
        bookListDb = bookController.getAllBooks();

        UserSession userSession = UserSession.getInstance();
        if (userSession.getAuthorization().equals(Authorization.LIBRARIAN)) {
            memberButton.setVisible(false);
            membersImage.setVisible(false);
            checkoutButton.setVisible(false);
            checkOutImage.setVisible(false);
            bookButton.setVisible(false);
            booksImage.setVisible(false);
        }
        entityController = new CheckoutEntityController();
        memberController = new MemberController();

        populateTable();
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

    private void populateTable() {
        // Record Table Attributes
        firstNameColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getLastName()));

        // Record Entry Attributes
        bookTitleColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getBookCopy().getBook().getTitle()));
        checkoutDateColumn.setCellValueFactory(param -> {
            if (param.getValue().getBorrowedDate() != null)
                return new SimpleObjectProperty<>(param.getValue().getBorrowedDate().toString());
            else return new SimpleObjectProperty<>("");
        });
        dueDateColumn.setCellValueFactory(param -> {
            if (param.getValue().getDueDate() != null)
                return new SimpleObjectProperty<>(param.getValue().getDueDate().toString());
            else return new SimpleObjectProperty<>("");
        });

        fineAmtColumn.setCellValueFactory(param ->  new SimpleObjectProperty<>(String.format("$%s", param.getValue().getFineAmount())));

        paidDateColumn.setCellValueFactory(param -> {
            if (param.getValue().getPaidDate() != null)
                return new SimpleObjectProperty<>(param.getValue().getDueDate().toString());
            else return new SimpleObjectProperty<>("");
        });
    }

    public void printEntryHandler(ActionEvent event) {
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
            View.routeToCreateCheckoutEntry(libraryMember, entityController, bookListDb);
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
        LibraryMember libraryMember = memberTable.getSelectionModel().getSelectedItem();
        CheckoutEntity checkoutEntity = checkoutEntryTable.getSelectionModel().getSelectedItem();
        if (checkoutEntity != null && libraryMember!=null) {
            View.routeToUpdateCheckoutEntry(libraryMember, checkoutEntity, entityController, bookListDb);
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
