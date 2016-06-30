package gui;
// Created by User on 27.06.2016.

import gui.controller.WindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import system.Catalog;
import system.Exercise;


import java.io.IOException;


public class ListViewWindow {
    private Catalog catalog = new Catalog();
    public static Stage listStage;
    //Erstellt ListView-Fenster
    public Exercise createWindow() throws IOException {
        listStage = new Stage();
        VBox root = new VBox();
        ListView<String> exerciseView = new ListView<>();
        Button loadButton = new Button("Load Exercise");
        loadButton.setOnAction((ActionEvent event)->{
            listStage.close();
        });
        root.getChildren().addAll(exerciseView, loadButton);
        Scene listScene = new Scene(root);
        //Fenster
        listStage.initModality(Modality.WINDOW_MODAL);
        listStage.initOwner(Main.primaryStage);
        catalog.loadCatalogFromXML();
        Exercise[] exercises = catalog.getExercises();
        for(Exercise exercise : exercises){
            exerciseView.getItems().add(exercise.getName());
        }
        listStage.setScene(listScene);
        listStage.showAndWait();
        int index = exerciseView.getFocusModel().getFocusedIndex();
        return catalog.getExercise(index);
    }

}
