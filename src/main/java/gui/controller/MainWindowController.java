package gui.controller;
// Created by User on 25.06.2016.

import gui.ListViewWindow;

import gui.Main;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import system.Catalog;
import system.Classframe;
import system.Exercise;

import java.io.IOException;


public class MainWindowController {
    private Catalog catalog;
    private Main main;
    @FXML
    private Label statusPassed, statusFailed, statusRed, statusGreen, statusRefactor;
    @FXML
    private Button switchToGreen, switchToRefactor, switchToRed;
    @FXML
    private TextArea codeArea;
    @FXML
    private ListView<String> testsView;

    public void initialize(){
        catalog = new Catalog();
        catalog.loadCatalogFromXML();
    }

    public void openListView(ActionEvent actionEvent) {
        testsView.getItems().clear();
        Exercise exercise = ListViewWindow.createWindow(catalog);
        if(exercise != null){
            for(Classframe classframe: exercise.getTestframes()){
                testsView.getItems().add(classframe.getClassname());
            }
        }
    }

    public void fillTestsView(){
        /*ObservableList<String> list = selectedExercise.getTests();
        testsView.setItems(list);
        testsView.refresh();*/
    }



}
