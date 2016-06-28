package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import system.Catalog;

/**
 * Created by cansc on 23.06.2016.
 */
public class Main extends Application {
    public static Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/gui/layout.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setMaximized(true);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("TDDT");
        primaryStage.show();
    }
}
