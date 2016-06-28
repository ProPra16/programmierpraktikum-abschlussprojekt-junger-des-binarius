package gui;
// Created by User on 27.06.2016.



import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import system.Catalog;
import system.Exercise;

import java.util.ArrayList;


public class ListViewWindow {
    private Exercise selectedExercise;
    //Erstellt ListView-Fenster
    public void createWindow(){
        VBox listPane = new VBox();
        Label label = new Label();
        ListView<String> exerciseView = new ListView<>();
        Exercise[] exercises = loadExercises();
        for(Exercise exercise : exercises){
            exerciseView.getItems().add(exercise.getName());
        }

        Button load = new Button("Load Exercise");
        load.setOnAction((ActionEvent event) -> {
            int index = exerciseView.getFocusModel().getFocusedIndex();

            label.setText(index+"");
        });

        listPane.getChildren().add(exerciseView);
        listPane.getChildren().add(load);
        listPane.getChildren().add(label);
        Scene listScene = new Scene(listPane);
        Stage listStage = new Stage();
        //Fenster
        listStage.initModality(Modality.WINDOW_MODAL);
        listStage.initOwner(Main.primaryStage);
        listStage.setScene(listScene);
        listStage.show();

    }

    private Exercise[] loadExercises(){
        Catalog catalog = new Catalog();
        catalog.loadCatalogFromXML();
        return catalog.getExercises();
    }

    private void chooseExercise(int index){
        Catalog catalog = new Catalog();
        selectedExercise = catalog.getExercise(index);
    }

    public Exercise getSelectedExercise() {
        return selectedExercise;
    }
}
