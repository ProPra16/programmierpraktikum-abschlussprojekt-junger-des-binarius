package gui.controller;

import gui.Main;
import gui.PopupWindow;
import gui.StatusDisplay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import status.StatusController;
import system.Catalog;
import system.Exercise;

import java.util.List;

/**
 * Klar von Logik getrennte grafische Benutzeroberflaeche. Implementiert Methoden des Interfaces StatusDisplay, diese sind selbsterklaerend.
 */
public class MainWindowController implements StatusDisplay {
    private Catalog catalog;
    private StatusController statusController;
    private Main main;
    @FXML
    private Label statusLabel,countdownLabel;
    @FXML
    private Button switchToGreen, switchToRefactor, switchToRed;
    @FXML
    private TextArea codeArea, outputArea;
    @FXML
    private ListView<String> classesListView;

    public void initialize(){
        catalog = new Catalog();
        catalog.loadCatalogFromXML(null);
        chooseExerciseFromCatalog();
    }

    public void showDescription(){
        statusController.showDescription();
    }

    public void showTracking(){
        statusController.showTracking();
    }

    public void chooseExerciseFromCatalog() {
        Exercise exercise = PopupWindow.showCatalogChooserWindow(catalog);
        if (exercise!=null) {
            if(statusController!=null)statusController.closeExercise();
            statusController = new StatusController(this,exercise);
            outputArea.clear();
        }
    }

    public void selectClass(){
        int index = classesListView.getFocusModel().getFocusedIndex();
        statusController.changeClassframe(index);
    }

    public void switchStatus(ActionEvent actionEvent){
        if(actionEvent.getSource().equals(switchToRed)){
            statusController.trySwitchToRed();
        }else if(actionEvent.getSource().equals(switchToGreen)){
            statusController.trySwitchToGreen();
        }else if(actionEvent.getSource().equals(switchToRefactor)){
            statusController.trySwitchToRefactor();
        }
    }

    @Override
    public void displayCode(String code){
        codeArea.setText(code);
    }

    @Override
    public String getCode(){
        return codeArea.getText();
    }

    @Override
    public void displayClassList(List<String> content){
        classesListView.getItems().setAll(content);
        classesListView.getSelectionModel().select(0);
    }

    @Override
    public void displayStatus(String statusText, Color color){
        statusLabel.setTextFill(color);
        statusLabel.setText(statusText);
    }

    @Override
    public void displaySwitchStatusOptions(boolean red, boolean green, boolean refactor){
        switchToRed.setDisable(red);
        switchToGreen.setDisable(green);
        switchToRefactor.setDisable(refactor);
    }

    @Override
    public void displayFeedback(String text) {
        outputArea.appendText(text+"\n");
    }

    @Override
    public void displayRemainingTime(double timeRemaining){
        countdownLabel.setText("Remaining Time: "+ timeRemaining + " sec");
    }
}
