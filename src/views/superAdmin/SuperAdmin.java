package views.superAdmin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;

public class SuperAdmin extends Application {
    public static Stage stage;
    private Main main;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/admin/viewBooks/view-books.fxml"));
        stage.setScene(new Scene(root, 1080, 720));
        stage.setTitle("Super Administrator");
        stage.setFullScreenExitHint("");
        stage.show();
    }
}
