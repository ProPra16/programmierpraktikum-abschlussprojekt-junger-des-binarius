package gui.controller;
// Created by User on 25.06.2016.

import gui.CatalogChooserWindow;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import status.Red;
import status.Status;
import system.Catalog;
import system.Exercise;

import java.util.List;


public class MainWindowController {
    private Catalog catalog;
    private Status currentstatus;
    private Exercise currentExercise;
    private Main main;
    @FXML
    private Label statusPassed, statusFailed, statusRed, statusGreen, statusRefactor;
    @FXML
    private Button switchToGreen, switchToRefactor, switchToRed;
    @FXML
    private TextArea codeArea;
    @FXML
    private ListView<String> classesListView;

    public void initialize(){
        catalog = new Catalog();
        catalog.loadCatalogFromXML();
        chooseExerciseFromCatalog();
    }

    public void chooseExerciseFromCatalog() {
        currentExercise = CatalogChooserWindow.createWindow(catalog);
        if (currentExercise!=null){
            currentstatus = new Red(this,currentExercise);
        }
    }

    public void fillCodeArea(String code){
        codeArea.setText(code);
    }

    public String getCode(){
        return codeArea.getText();
    }

    public void fillClassList(List<String> content){
        classesListView.getItems().setAll(content);
    }

    public void selectClass(){
        int index = classesListView.getFocusModel().getFocusedIndex();
        currentstatus.changeClassframe(index);
    }

    public void switchStatus(ActionEvent actionEvent){
        if(actionEvent.getSource().equals(switchToRed)){

        }
    }

}
