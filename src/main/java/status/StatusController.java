package status;

import gui.PopupWindow;
import gui.StatusDisplay;
import javafx.event.EventHandler;
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
        babystepper = new Babystepper(statusDisplay,this,exercise.getBabystepTime(),exercise.getBabystepStatusSwitchActivated());
        currentStatus = new Red(statusDisplay,exercise,babystepper);
    }

    public void closeExercise(){
        babystepper.stop();
        babystepper = null;
    }

    public void trySwitchToRed() {
        if(currentStatus.switchToRed()){
            switchWithoutCheck(Status.RED);
        }
    }

    public void trySwitchToGreen() {
        if(currentStatus.switchToGreen()){
            switchWithoutCheck(Status.GREEN);
        }
    }

    public void trySwitchToRefactor() {
        if(currentStatus.switchToRefactor()){
            switchWithoutCheck(Status.REFACTOR);
        }
    }

    public void changeClassframe(int index){
        currentStatus.changeClassframe(index);
    }

    public void showDescription(){
        PopupWindow.showExerciseDescriptionWindow(exercise);
    }

    protected void switchWithoutCheck(int status){
        switch (status) {
            case Status.RED:
                currentStatus = new Red(statusDisplay, exercise, babystepper); break;
            case Status.GREEN:
                currentStatus = new Green(statusDisplay, exercise, babystepper); break;
            case Status.REFACTOR:
                currentStatus = new Refactor(statusDisplay, exercise, babystepper); break;
        }

    }
    @Override
    public void handle(BabystepperEvent event) {
        switchWithoutCheck(currentStatus.timeExpired());
    }
}
