package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class AlertWindow {
    private static final Image icon = new Image("images/ICON_Alert.png");
    //Erstellt Alert-Fenster
    public static boolean confirmation(String headerText, String contentText) {
        URL stylesheet = Main.class.getResource("/gui/TDDT_style.css");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane diagPane = alert.getDialogPane();
        diagPane.getStylesheets().add(stylesheet.toExternalForm());
        ((Stage)diagPane.getScene().getWindow()).getIcons().setAll(icon);
        alert.setTitle("Warning");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();

        return alert.getResult()== ButtonType.OK;
    }
}
