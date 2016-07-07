package gui;
// Created by User on 07.07.2016.

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import system.Exercise;

public class ExerciseDescriptionWindow {

    public static void createWindow(Exercise exercise){
        Stage stage = new Stage();
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
        TextArea descriptionArea = new TextArea();
        descriptionArea.setEditable(false);
    }
}
