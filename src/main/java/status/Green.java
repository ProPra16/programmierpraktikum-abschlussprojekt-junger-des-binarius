package status;

import gui.AlertWindow;
import gui.Main;
import gui.StatusDisplay;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.paint.Color;
import system.Exercise;

import java.net.URL;

public class Green extends Status{

    public Green(StatusDisplay statusDisplay, Exercise exercise){
        super(statusDisplay,exercise);
        exercise.saveCurrentContent();
        statusDisplay.displaySwitchStatusOptions(false,true,false);
        statusDisplay.displayStatus("GREEN", Color.valueOf("#00FF00"));
        statusDisplay.displayClassList(exercise.getClassNames());
        currentClassframe = exercise.getClassframes()[0];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
    }

    @Override
    public int getStatus(){
        return Status.GREEN;
    }

    @Override
    public void changeClassframe(int index){
        currentClassframe.setFrameContent(statusDisplay.getCode());
        currentClassframe = exercise.getClassframes()[index];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
    }

    @Override
    public Status switchToRed() {
        boolean confirmationResult = AlertWindow.confirmation("You are trying to switch back to Status RED. \nTherefore the current progress in status GREEN will be erased.","Do you want to continue?");
        if(confirmationResult) {
            exercise.restoreSavedContent();
            statusDisplay.displayFeedback("NOTE: Switched back to RED. New Progress erased and former Content restored.");
            return new Red(statusDisplay,exercise);
        } else {
            statusDisplay.displayFeedback("NOTE: Switching back to RED cancelled.");
            return this;
        }
    }

    @Override
    public Status switchToRefactor() {
        saveCurrentClassframe();
        return this;
    }

}
