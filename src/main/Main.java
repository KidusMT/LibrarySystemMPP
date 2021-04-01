package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        primaryStage.setTitle("Project management system");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../common/styles/common.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}