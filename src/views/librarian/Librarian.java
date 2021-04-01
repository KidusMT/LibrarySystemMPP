package views.librarian;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Librarian extends Application {
    public static Stage stage;

    public static void routeToCreateCheckout() throws IOException {
        route(FXMLLoader.load(Librarian.class.getResource("/views/librarian/createCheckout/create-checkout.fxml")), "Create checkout");
    }

    public static void routeToUpdateCheckout() throws IOException {
        route(FXMLLoader.load(Librarian.class.getResource("/views/librarian/updateCheckout/update-checkout.fxml")), "Update checkout record");
    }

    public static void routeToViewCheckouts() throws IOException {
        route(FXMLLoader.load(Librarian.class.getResource("/views/librarian/viewCheckouts/view-checkout.fxml")), "View checkout records");
    }

    private static void route(Parent contentRoot, String title) {
        stage.setTitle(title != null ? title : "Librarian");
        stage.setScene(new Scene(contentRoot, 960, 640));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/librarian/viewCheckouts/view-checkout.fxml"));
        Scene scene = new Scene(root, 960, 640);
        scene.getStylesheets().add(getClass().getResource("/views/librarian/viewCheckouts/view_checkout.css").toExternalForm());
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.show();
    }

}
