package views;

import common.utils.Authorization;
import common.utils.UserSession;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Book;
import models.CheckoutEntity;
import models.CheckoutRecord;
import views.admin.bookDetail.BookSingleton;
import views.librarian.createCheckoutEntry.CreateCheckoutEntry;
import views.librarian.updateCheckoutEntry.UpdateCheckoutEntry;

import java.io.IOException;

public class View extends Application {
    public static Stage stage;

    // Administrator routes
    public static void routeViewBooks() throws IOException {
        route(FXMLLoader.load(View.class.getResource("/views/admin/viewBooks/view-books.fxml")), "View book list");
    }

    public static void routeToBookDetail(Book book) throws IOException {
        FXMLLoader loader = new FXMLLoader(View.class.getResource("/views/admin/bookDetail/book-detail.fxml"));
        BookSingleton.createInstance(book);
        route(FXMLLoader.load(View.class.getResource("/views/admin/bookDetail/book-detail.fxml")), "Book detail");
    }

    public static void routeToViewMembers() throws IOException {
        route(FXMLLoader.load(View.class.getResource("/views/admin/viewMembers/view-members.fxml")), "Members list");
    }

    public static void routeToCreateBook() throws IOException {
        route(FXMLLoader.load(View.class.getResource("/views/admin/createBook/create-book.fxml")), "Create book");
    }

    public static void routeToCreateMember() throws IOException {
        route(FXMLLoader.load(View.class.getResource("/views/admin/createMember/create-member.fxml")), "Create member");
    }

    public static void routeToUpdateMember() throws IOException {
        route(FXMLLoader.load(View.class.getResource("/views/admin/updateMember/update-member.fxml")), "Update member");
    }

    public static void routeToUpdateBook() throws IOException {
        route(FXMLLoader.load(View.class.getResource("/views/admin/updateBook/update-book.fxml")), "Update book");
    }

    // Librarian

    public static void routeToCreateCheckout() throws IOException {
        route(FXMLLoader.load(View.class.getResource("/views/librarian/createCheckout/create-checkout.fxml")), "Create checkout");
    }

    public static void routeToUpdateCheckout() throws IOException {
        route(FXMLLoader.load(View.class.getResource("/views/librarian/updateCheckout/update-checkout.fxml")), "Update checkout record");
    }

    public static void routeToViewCheckouts() throws IOException {
        route(FXMLLoader.load(View.class.getResource("/views/librarian/viewCheckout/view-checkout.fxml")), "View checkout records");
    }

    public static void routeToCreateCheckoutEntry(CheckoutRecord checkoutEntity) throws IOException {
        CreateCheckoutEntry.newInstance(checkoutEntity);
        route(FXMLLoader.load(View.class.getResource("/views/librarian/createCheckoutEntry/create-checkout-entry.fxml")), "View checkout records");
    }

    public static void routeToUpdateCheckoutEntry(CheckoutEntity checkoutEntity) throws IOException {
        UpdateCheckoutEntry.createInstance(checkoutEntity);
        route(FXMLLoader.load(View.class.getResource("/views/librarian/updateCheckoutEntry/update-checkout-entry.fxml")), "View checkout records");
    }

    private static void route(Parent contentRoot, String title) {
        stage.setTitle(title);
        stage.setScene(new Scene(contentRoot, 1080, 720));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/admin/viewBooks/view-books.fxml"));
        UserSession userSession = UserSession.getInstance();
        if (userSession.getAuthorization().equals(Authorization.LIBRARIAN))
            root = FXMLLoader.load(getClass().getResource("/views/librarian/viewCheckout/view-checkout.fxml"));
        else if (userSession.getAuthorization().equals(Authorization.ADMIN))
            root = FXMLLoader.load(getClass().getResource("/views/admin/viewBooks/view-books.fxml"));
        else
            root = FXMLLoader.load(getClass().getResource("/views/librarian/viewCheckout/view-checkout.fxml"));
        stage.setScene(new Scene(root, 1080, 720));
        stage.setFullScreenExitHint("");
        stage.show();
    }
}
