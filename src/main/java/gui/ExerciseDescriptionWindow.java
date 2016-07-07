package gui;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import system.Exercise;

import java.net.URL;

public class ExerciseDescriptionWindow {

    private static final Image icon = new Image("images/ICON_Catalog.png");

    public static void createWindow(Exercise exercise){
        URL stylesheet = Main.class.getResource("/gui/TDDT_style.css");
        Stage stage = new Stage();
        stage.getIcons().setAll(icon);
        stage.setResizable(false);
        VBox vBox = new VBox();
        vBox.getStylesheets().add(stylesheet.toExternalForm());
        TextArea descriptionArea = new TextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setText("Exercise:\n"+exercise.getName()+"\n\n"+"Description:\n"+exercise.getDescription());
        vBox.getChildren().add(descriptionArea);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
