package gui;
// Created by User on 27.06.2016.


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListViewWindow {
    public static void createWindow(){
        VBox listPane = new VBox();
        ListView<String> exerciseView = new ListView<>();
        ObservableList<String> exerciseList = FXCollections.observableArrayList("Aufgabe 1", "Aufgabe 2", "Aufgbae 3");
        exerciseView.setItems(exerciseList);
        Button load = new Button("Load Exercise");
        listPane.getChildren().add(exerciseView);
        listPane.getChildren().add(load);
        Scene listScene = new Scene(listPane);
        Stage listStage = new Stage();
        listStage.initModality(Modality.WINDOW_MODAL);
        listStage.initOwner(Main.primaryStage);
        listStage.setScene(listScene);
        listStage.show();

    }
}
