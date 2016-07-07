package gui;
// Created by User on 07.07.2016.

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import system.Exercise;

import java.net.URL;

public class ExerciseDescriptionWindow {

    public static void createWindow(Exercise exercise){
        URL stylesheet = Main.class.getResource("/gui/TDDT_style.css");
        Stage stage = new Stage();
        VBox vBox = new VBox();
        vBox.getStylesheets().add(stylesheet.toExternalForm());
        TextArea descriptionArea = new TextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setText("Exercise:\n"+exercise.getName()+"\n\n"+"Description:\n"+exercise.getDescription());
        vBox.getChildren().add(descriptionArea);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }
}
