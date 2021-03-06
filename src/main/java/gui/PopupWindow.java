package gui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import system.Catalog;
import system.Exercise;

/**
 * Stellt Methoden zur Anzeige kleinerer Fenster bereit.
 */
public class PopupWindow {


    /**
     * Erstellt ein Fenster zur Anzeige der Aufgabenstellung.
     * @param exercise Aufgabe, deren Aufgabenstellung angezeigt werden soll.
     */
    public static void showExerciseDescriptionWindow(Exercise exercise){
        Stage stage = new Stage();
        stage.getIcons().setAll(Main.CatalogIcon);
        stage.setResizable(false);
        stage.setTitle("Exercise Description");
        VBox vBox = new VBox();
        vBox.getStylesheets().add(Main.stylesheet.toExternalForm());
        TextArea descriptionArea = new TextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setWrapText(true);
        descriptionArea.setPrefSize(400,300);
        descriptionArea.setText("Exercise:\n"+exercise.getName()+"\n\n"+"Description:\n"+exercise.getDescription());
        vBox.getChildren().add(descriptionArea);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    /**
     * Erstellt ein Fenster zum Auswaehlen einer Aufgabe aus dem Katalog.
     * @param catalog Katalog, aus welchem die Aufgabe ausgewaehlt werden kann.
     * @return die selektierte Aufgabe.
     */
    public static Exercise showCatalogChooserWindow(Catalog catalog) {
        Stage listStage = new Stage();
        listStage.getIcons().setAll(Main.CatalogIcon);
        listStage.setResizable(false);
        listStage.setTitle("Choose Exercise");
        VBox root = new VBox(5);
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
        listScene.getStylesheets().add(Main.stylesheet.toExternalForm());
        listStage.initModality(Modality.WINDOW_MODAL);
        listStage.initOwner(Main.primaryStage);
        exerciseView.getItems().setAll(catalog.getAllExerciseNamesWithBabystepsInformation());
        exerciseView.getSelectionModel().select(0);
        listStage.setScene(listScene);
        listStage.sizeToScene();
        listStage.showAndWait();
        int index = exerciseView.getFocusModel().getFocusedIndex();

        return catalog.getExercise(index);
    }

    /**
     * Erstellt ein Alert-Fenster, welches den Nutzer zu einer Eingabe von OK oder ABBRECHEN auffordert.
     * @param headerText Ueberschrift.
     * @param contentText Inhalt.
     * @return ob OK ausgewaehlt wurde.
     */
    public static boolean showConfirmationWindow(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane diagPane = alert.getDialogPane();
        diagPane.getStylesheets().add(Main.stylesheet.toExternalForm());
        ((Stage)diagPane.getScene().getWindow()).getIcons().setAll(Main.AlertIcon);
        alert.setTitle("Warning");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();

        return alert.getResult()== ButtonType.OK;
    }
}
