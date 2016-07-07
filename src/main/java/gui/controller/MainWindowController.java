package gui.controller;

import gui.CatalogChooserWindow;
import gui.Main;
import gui.StatusDisplay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import status.Red;
import status.Status;
import system.Catalog;
import system.Exercise;

import java.util.List;


public class MainWindowController implements StatusDisplay {
    private Catalog catalog;
    private Status currentStatus;
    private Exercise currentExercise;
    private Main main;
    @FXML
    private Label statusLabel;
    @FXML
    private Button switchToGreen, switchToRefactor, switchToRed;
    @FXML
    private TextArea codeArea, outputArea;
    @FXML
    private ListView<String> classesListView;

    public void initialize(){
        catalog = new Catalog();
        catalog.loadCatalogFromXML();
        chooseExerciseFromCatalog();
    }

    public void showDescription(){

    }

    public void chooseExerciseFromCatalog() {
        currentExercise = CatalogChooserWindow.createWindow(catalog);
        if (currentExercise!=null) {
            currentStatus = new Red(this, currentExercise);
            outputArea.clear();
        }
    }

    public void displayCode(String code){
        codeArea.setText(code);
    }

    public String getCode(){
        return codeArea.getText();
    }

    public void displayClassList(List<String> content){
        classesListView.getItems().setAll(content);
    }

    public void selectClass(){
        int index = classesListView.getFocusModel().getFocusedIndex();
        currentStatus.changeClassframe(index);
    }

    public void switchStatus(ActionEvent actionEvent){
        if(actionEvent.getSource().equals(switchToRed)){
            currentStatus = currentStatus.switchToRed();
        }else if(actionEvent.getSource().equals(switchToGreen)){
            currentStatus = currentStatus.switchToGreen();
        }else if(actionEvent.getSource().equals(switchToRefactor)){
            currentStatus = currentStatus.switchToRefactor();
        }
    }

    public void displayStatus(String statusText, Color color){
        statusLabel.setTextFill(color);
        statusLabel.setText(statusText);
    }

    public void displaySwitchStatusOptions(boolean red, boolean green, boolean refactor){
        switchToRed.setDisable(red);
        switchToGreen.setDisable(green);
        switchToRefactor.setDisable(refactor);
    }

    public void displayFeedback(String text) {
        outputArea.appendText(text+"\n");
    }
}
