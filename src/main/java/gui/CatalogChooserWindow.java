package gui;
// Created by User on 27.06.2016.

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import system.Catalog;
import system.Exercise;


public class CatalogChooserWindow {
    //Erstellt ListView-Fenster
    public static Exercise createWindow(Catalog catalog) {
        Stage listStage = new Stage();
        listStage.setResizable(false);
        listStage.setTitle("Choose Exercise");
        VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setPrefWidth(250);
        root.setFillWidth(true);
        ListView<String> exerciseView = new ListView<>();
        Button loadButton = new Button("Load Exercise");
        loadButton.setOnAction((ActionEvent event)->{
            listStage.close();
        });
        loadButton.setPrefWidth(Double.MAX_VALUE);
        root.getChildren().addAll(exerciseView, loadButton);
        Scene listScene = new Scene(root);
        listStage.initModality(Modality.WINDOW_MODAL);
        listStage.initOwner(Main.primaryStage);
        exerciseView.getItems().setAll(catalog.getAllExerciseNames());
        listStage.setScene(listScene);
        listStage.sizeToScene();
        listStage.showAndWait();
        int index = exerciseView.getFocusModel().getFocusedIndex();
        return catalog.getExercise(index);
    }

}
