package gui.controller;
// Created by User on 25.06.2016.

import gui.ListViewWindow;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import system.SelectedExercise;

import java.io.IOException;


public class WindowController {
    SelectedExercise selectedExercise = new SelectedExercise();
    @FXML
    private Label statusPassed, statusFailed, statusRed, statusGreen, statusRefactor;
    @FXML
    private Button switchToGreen, switchToRefactor, switchToRed;
    @FXML
    private TextArea codeArea;
    @FXML
    private static ListView<String> classView;
    public void openListView(ActionEvent actionEvent) {
        ListViewWindow window = new ListViewWindow();
        try {
            window.createWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
