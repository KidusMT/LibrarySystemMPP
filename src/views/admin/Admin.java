package views.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class Admin extends Application {
    public static Stage stage;
    private Main main;

    public static void routeViewBooks() throws IOException {
        route(FXMLLoader.load(Admin.class.getResource("/views/admin/viewBooks/view-books.fxml")), "View book list");
    }

    public static void routeToBookDetail() throws IOException {
        route(FXMLLoader.load(Admin.class.getResource("/views/admin/bookDetail/book-detail.fxml")), "Book detail");
    }

    public static void routeToViewMembers() throws IOException {
        route(FXMLLoader.load(Admin.class.getResource("/views/admin/viewMembers/view-members.fxml")), "Members list");
    }

    public static void routeToMemberDetail() throws IOException {
        route(FXMLLoader.load(Admin.class.getResource("/views/admin/memberDetail/member-detail.fxml")), "Member detail");
    }

    public static void routeToCreateBook() throws IOException {
        route(FXMLLoader.load(Admin.class.getResource("/views/admin/createBook/create-book.fxml")), "Create book");
    }

    public static void routeToCreateMember() throws IOException {
        route(FXMLLoader.load(Admin.class.getResource("/views/admin/createMember/create-member.fxml")), "Create member");
    }

    public static void routeToUpdateMember() throws IOException {
        route(FXMLLoader.load(Admin.class.getResource("/views/admin/updateMember/update-member.fxml")), "Update member");
    }

    public static void routeToUpdateBook() throws IOException {
        route(FXMLLoader.load(Admin.class.getResource("/views/admin/updateBook/update-book.fxml")), "Update book");
    }

    private static void route(Parent contentRoot, String title) {
        stage.setTitle(title != null ? title : "Administrator");
        stage.setScene(new Scene(contentRoot, 960, 640));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/admin/viewMembers/view-members.fxml"));
        stage.setScene(new Scene(root, 960, 640));
        stage.setTitle("Administrator");
        stage.setFullScreenExitHint("");
        stage.show();
    }
}
