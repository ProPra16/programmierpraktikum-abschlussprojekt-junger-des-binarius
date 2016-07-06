package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application {
    public static Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        URL stylesheet = getClass().getResource("/gui/TDDT_style.css");
        Parent root = FXMLLoader.load(getClass().getResource("/gui/layout.fxml"));
        Scene mainScene = new Scene(root);
        mainScene.getStylesheets().add(stylesheet.toExternalForm());
        primaryStage.getIcons().setAll(new Image("/images/ICON_TDDT.png"));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("TDDT");
        primaryStage.show();
    }
}
