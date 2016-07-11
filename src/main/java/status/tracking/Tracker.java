package status.tracking;

import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import status.Status;
import vk.core.api.CompileError;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Tracker {
    private long[] timeMillisInStatus;
    private static final Image trackingIcon = new Image("/images/ICON_Tracking.png");
    private HashMap<String,Integer> errors;
    public Tracker(){
        timeMillisInStatus = new long[3];
        for(int i=0;i<3;i++){
            timeMillisInStatus[i]=1;//fur den anfang ohne zeiten
        }
        errors = new HashMap<String,Integer>();

    }

    public void analyseAndAddCompileErrors(Collection<CompileError> compileErrors){
        for(CompileError error:compileErrors) {
            System.out.println(error.getMessage());
            if (error.getMessage().contains(" expected")) {
                incrementErrorCount("... expected");
            } else if (error.getMessage().contains("unclosed string literal")) {
                incrementErrorCount("unclosed string literal");
            } else if (error.getMessage().contains("illegal start of expression")) {
                incrementErrorCount("illegal start of expression");
            } else if (error.getMessage().contains("not a statement")) {
                incrementErrorCount("not a statement");
            } else if (error.getMessage().contains("cannot find symbol")) {
                incrementErrorCount("cannot find symbol");
            } else if (error.getMessage().contains("is already defined in")) {
                incrementErrorCount("is already defined in");
            } else if (error.getMessage().contains("has private access")) {
                incrementErrorCount("has private access");
            } else if (error.getMessage().contains("might not have been initialized")) {
                incrementErrorCount("might not have been initialized");
            } else if (error.getMessage().contains("cannot be applied to")) {
                incrementErrorCount("cannot be applied to");
            } else if (error.getMessage().contains("inconvertible types")) {
                incrementErrorCount("inconvertible types");
            } else if (error.getMessage().contains("missing return statement")) {
                incrementErrorCount("missing return statement");
            } else if (error.getMessage().contains("missing return value")) {
                incrementErrorCount("missing return value");
            } else if (error.getMessage().contains("cannot return a value from method whose result type is void")) {
                incrementErrorCount("cannot return from void method");
            } else if (error.getMessage().contains("invalid method declaration; return type required")) {
                incrementErrorCount("return type required");
            } else if (error.getMessage().contains("unreachable statement")) {
                incrementErrorCount("unreachable statement");
            } else if (error.getMessage().contains("non-static variable cannot be referenced from a static context")) {
                incrementErrorCount("non-static variable from a static context");
            } else if (error.getMessage().contains("non-static method cannot be referenced from a static context")) {
                incrementErrorCount("non-static method from a static context");
            } else {
                incrementErrorCount("not specified error");
            }
        }
    }

    public void incrementErrorCount(String error){
        if(errors.containsKey(error)){
            errors.put(error,errors.get(error)+1);
        }else{
            errors.put(error,1);
        }
    }

    public void addTimeToStatus(int status,TimeTracker timeTracker){
        timeMillisInStatus[status]+=timeTracker.getTimeMillis();
    }

    public void showData(){
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.primaryStage);
        stage.setTitle("Tracking Analysis");
        stage.getIcons().setAll(trackingIcon);
        HBox hbox = new HBox();

        PieChart pieChart = new PieChart();
        ObservableList<PieChart.Data> piecontent = FXCollections.observableArrayList(
                new PieChart.Data("RED " + Math.floor(timeMillisInStatus[Status.RED]/100)/10+ " sec", timeMillisInStatus[Status.RED]),
                new PieChart.Data("GREEN " + Math.floor(timeMillisInStatus[Status.GREEN]/100)/10+ " sec", timeMillisInStatus[Status.GREEN]),
                new PieChart.Data("REFACTOR " + Math.floor(timeMillisInStatus[Status.REFACTOR]/100)/10+ " sec", timeMillisInStatus[Status.REFACTOR]));
        pieChart.setData(piecontent);
        pieChart.setTitle("Time spent in statuses");
        pieChart.setPrefWidth(500);

        TextArea errorArea = new TextArea();
        errorArea.setPrefWidth(500);
        errorArea.setEditable(false);

        Set<String> keys = errors.keySet();
        if(keys.isEmpty()){
            errorArea.setText("No errors have occurred during your work. Well done!");
        }else{
            errorArea.setText("During your work the following errors have occurred:\n\n");
            errorArea.appendText("#Occurrences\t| Errors\n");
            errorArea.appendText("----------------|-----------------------------------------\n");
            for(String key:keys){
                errorArea.appendText(errors.get(key)+"\t\t| "+key+"\n");
            }
        }

        hbox.getChildren().setAll(pieChart,errorArea);
        Scene scene = new Scene(hbox);
        scene.getStylesheets().setAll(Main.stylesheet.toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.showAndWait();
    }

}
