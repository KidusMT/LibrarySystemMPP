package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("../views/login/login.fxml"));
        primaryStage.setTitle("MIU Library System");
        primaryStage.getIcons().add(new Image(getClass().getResource("../common/images/miu_logo_app.png").toURI().toString()));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../common/styles/common.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}