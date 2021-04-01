package views.librarian;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        route(FXMLLoader.load(Librarian.class.getResource("/views/librarian/viewCheckout/view-checkout.fxml")), "View checkout records");
    }

    private static void route(Parent contentRoot, String title) {
        stage.setTitle(title != null ? title : "Librarian");
        stage.setScene(new Scene(contentRoot));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/librarian/viewCheckout/view-checkout.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("MIU Library System");
        stage.getIcons().add(new Image(getClass().getResource("/views/login/miu_logo_app.png").toURI().toString()));
        scene.getStylesheets().add(getClass().getResource("/views/librarian/viewCheckout/view_checkout.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/common/styles/common.css").toExternalForm());
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.show();
    }

}
