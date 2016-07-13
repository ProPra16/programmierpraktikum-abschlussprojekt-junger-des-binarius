package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Initialisiert das Hauptfenster.
 */
public class Main extends Application {
    public static Stage primaryStage;
    public static final Image trackingIcon = new Image("/images/ICON_Tracking.png");
    public static final Image CatalogIcon = new Image("images/ICON_Catalog.png");
    public static final Image AlertIcon = new Image("images/ICON_Alert.png");

    public static final URL stylesheet = Main.class.getResource("/gui/TDDT_style.css");
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        this.primaryStage = primaryStage;
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
