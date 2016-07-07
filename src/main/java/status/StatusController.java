package status;

import gui.ExerciseDescriptionWindow;
import gui.StatusDisplay;
import javafx.event.EventHandler;
import status.babystep.BabystepControls;
import status.babystep.Babystepper;
import status.babystep.BabystepperEvent;
import system.Exercise;

public class StatusController implements EventHandler<BabystepperEvent>{
    private StatusDisplay statusDisplay;
    private Babystepper babystepper;
    private Exercise exercise;
    private Status currentStatus;

    public StatusController(StatusDisplay statusDisplay, Exercise exercise){
        this.statusDisplay = statusDisplay;
        this.exercise = exercise;
        babystepper = new Babystepper(statusDisplay,this,exercise.getBabystepTime());
        currentStatus = new Red(statusDisplay,exercise,babystepper);
    }

    public void closeExercise(){
        babystepper.stop();
        babystepper = null;
    }

    public void switchToRed() {
        if(currentStatus.switchToRed()){
            currentStatus = new Red(statusDisplay,exercise,babystepper);
        }
    }

    public void switchToGreen() {
        if(currentStatus.switchToGreen()){
            currentStatus = new Green(statusDisplay,exercise,babystepper);
        }
    }

    public void switchToRefactor() {
        if(currentStatus.switchToRefactor()){
            currentStatus = new Refactor(statusDisplay,exercise,babystepper);
        }
    }

    public void changeClassframe(int index){
        currentStatus.changeClassframe(index);
    }

    public void showDescription(){
        ExerciseDescriptionWindow.createWindow(exercise);
    }

    @Override
    public void handle(BabystepperEvent event) {
        currentStatus = currentStatus.timeExpired();
    }
}
