package gui;
// Created by User on 25.06.2016.

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class WindowController {
    @FXML
    private Label statusPassed, statusFailed, statusRed, statusGreen, statusRefactor;
    @FXML
    private Button switchToGreen, switchToRefactor, switchToRed;
    @FXML
    private TextArea codeArea;
    @FXML
    private ListView<String> classView;
    public void openListView(ActionEvent actionEvent) {
        ListViewWindow.createWindow();
    }
}
