package status;

import gui.PopupWindow;
import gui.StatusDisplay;
import javafx.event.EventHandler;
import status.babystep.Babystepper;
import status.babystep.BabystepperEvent;
import status.tracking.Tracker;
import system.Exercise;

public class StatusController implements EventHandler<BabystepperEvent>{
    private StatusDisplay statusDisplay;
    private Babystepper babystepper;
    private Exercise exercise;
    private Tracker tracker;
    private Status currentStatus;

    public StatusController(StatusDisplay statusDisplay, Exercise exercise){
        this.statusDisplay = statusDisplay;
        this.exercise = exercise;
        tracker = new Tracker();
        babystepper = new Babystepper(statusDisplay,this,exercise.getBabystepTime(),exercise.getBabystepStatusSwitchActivated());
        currentStatus = new Red(statusDisplay,exercise,babystepper,tracker);
    }
    public void showTracking(){
        boolean running = babystepper.running();
        babystepper.stop();
        currentStatus.stopTimeTracking();
        tracker.showData();
        currentStatus.continueTimeTracking();
        if(running)
            babystepper.start();
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
                currentStatus = new Red(statusDisplay, exercise, babystepper,tracker); break;
            case Status.GREEN:
                currentStatus = new Green(statusDisplay, exercise, babystepper,tracker); break;
            case Status.REFACTOR:
                currentStatus = new Refactor(statusDisplay, exercise, babystepper,tracker); break;
        }

    }
    @Override
    public void handle(BabystepperEvent event) {
        switchWithoutCheck(currentStatus.timeExpired());
    }
}
