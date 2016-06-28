package gui.controller;
// Created by User on 28.06.2016.

import gui.ListViewWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import system.Catalog;
import system.Exercise;
import system.SelectedExercise;

public class ListViewController {
    @FXML
    private ListView<String> exerciseView;
    @FXML
    private Button load;
    private Catalog catalog = new Catalog();
    private SelectedExercise selectedExercise = new SelectedExercise();
    public void handleLoadButton(ActionEvent event){
        int index = exerciseView.getFocusModel().getFocusedIndex();
        selectedExercise.setSelectedExercise(catalog.getExercise(index));
        ListViewWindow.listStage.close();
    }

    @FXML
    private void initialize(){

        catalog.loadCatalogFromXML();
        Exercise[] exercises = catalog.getExercises();
        for(Exercise exercise : exercises){
            exerciseView.getItems().add(exercise.getName());
        }
    }
}
