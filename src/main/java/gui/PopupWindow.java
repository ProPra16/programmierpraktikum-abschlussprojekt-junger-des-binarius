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

public class PopupWindow {
    private static final Image CatalogIcon = new Image("images/ICON_Catalog.png");
    private static final Image AlertIcon = new Image("images/ICON_Alert.png");

    public static void showExerciseDescriptionWindow(Exercise exercise){
        Stage stage = new Stage();
        stage.getIcons().setAll(CatalogIcon);
        stage.setResizable(false);
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

    public static Exercise showCatalogChooserWindow(Catalog catalog) {
        Stage listStage = new Stage();
        listStage.getIcons().setAll(CatalogIcon);
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
        exerciseView.getItems().setAll(catalog.getAllExerciseNamesWithDescription());
        exerciseView.getSelectionModel().select(0);
        listStage.setScene(listScene);
        listStage.sizeToScene();
        listStage.showAndWait();
        int index = exerciseView.getFocusModel().getFocusedIndex();

        return catalog.getExercise(index);
    }

    public static boolean showConfirmationWindow(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane diagPane = alert.getDialogPane();
        diagPane.getStylesheets().add(Main.stylesheet.toExternalForm());
        ((Stage)diagPane.getScene().getWindow()).getIcons().setAll(AlertIcon);
        alert.setTitle("Warning");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();

        return alert.getResult()== ButtonType.OK;
    }

}
