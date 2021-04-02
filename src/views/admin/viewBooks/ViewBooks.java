package views.admin.viewBooks;

import common.utils.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import models.Author;
import models.Book;
import models.LibraryMember;
import models.User;
import views.admin.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ViewBooks implements Initializable {
    @FXML private TableView<Book> tableView;
    @FXML private TableColumn<Book, String> isbnCol;
    @FXML private TableColumn<Book,String> titleCol;
    @FXML private TextField searchText;

    public void navigateToMembersHandler(ActionEvent event) throws IOException {
        Admin.routeToViewMembers();
    }
    public void navigateToBooksHandler(ActionEvent event) throws IOException {
        Admin.routeViewBooks();
    }
    public void navigateToCreateBook(ActionEvent event) throws IOException{
        Admin.routeToCreateBook();
    }
    public void navigateToLogin(ActionEvent event) throws IOException {
        UserSession.destroySession();
        Admin.stage.hide();
        Main.primaryStage.show();
    }
    public void searchMemberHandler(ActionEvent event){
        String searchString = searchText.getText();

        List<Book> books = new ArrayList<>();
        for(Book book : allBooks()){
            if(book.getIsbn().equals(searchString) || book.getTitle().equals(searchString)) books.add(book);
        }
        populateTable(books);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable(allBooks());
    }
    public void populateTable(List<Book> bookList){
        isbnCol.setCellValueFactory(new PropertyValueFactory<Book,String >("isbn"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        tableView.getItems().setAll(bookList);
        tableView.setRowFactory(tv->{
            TableRow<Book> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event ->{
                Book rowData = tableRow.getItem();
                try {
                    Admin.routeToBookDetail(rowData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return tableRow;
        });
    }
    private List<Book> allBooks(){
        List<Book> books = Arrays.asList(new Book("123","War and Peace",21,Arrays.asList(new Author("Leo","Tolstoy",null,null,null))),
                                        new Book("234","Crime and Punishment",21,Arrays.asList(new Author("Leo","Tolstoy",null,null,null))));
        return books;
    }
}
