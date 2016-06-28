package gui;
// Created by User on 27.06.2016.

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import system.Catalog;


import java.io.IOException;


public class ListViewWindow {
    private Catalog catalog = new Catalog();

    //Erstellt ListView-Fenster
    public void createWindow() throws IOException {
        Stage listStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/listView.fxml"));
        Scene listScene = new Scene(root);
        //Fenster
        listStage.initModality(Modality.WINDOW_MODAL);
        listStage.initOwner(Main.primaryStage);
        listStage.setScene(listScene);
        listStage.show();

    }

}
