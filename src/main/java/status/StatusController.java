package status;

import gui.PopupWindow;
import gui.StatusDisplay;
import javafx.event.EventHandler;
import status.babystep.Babystepper;
import status.babystep.BabystepperEvent;
import status.tracking.Tracker;
import system.Exercise;

/**
 * Verwaltet die verschiedenen Statusse und speichert dazu ein StatusDisplay-Interface, eine Babystepper-Instanz, eine Aufgabe, eine Tracker-Instanz und den aktuellen Status.
 */
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

    /**
     * Stoppt den Babystep-Timer und das TimeTracking und oeffnet das Tracking-Analyse-Fenster.
     */
    public void showTracking(){
        boolean running = babystepper.running();
        babystepper.pauseTimer();
        currentStatus.stopTimeTracking();
        tracker.showData();
        currentStatus.continueTimeTracking();
        if(running)
            babystepper.continueTimer();
    }

    /**
     * @return den aktuellen Status.
     */
    public int getStatus(){
        return currentStatus.getStatus();
    }

    /**
     * Schliesst die aktuelle Aufgabe.
     */
    public void closeExercise(){
        babystepper.pauseTimer();
        babystepper = null;
    }

    /**
     * Versucht in den Status RED zu wechseln.
     */
    public void trySwitchToRed() {
        if(currentStatus.switchToRed()){
            switchWithoutCheck(Status.RED);
        }
    }

    /**
     * Versucht in den Status GREEN zu wechseln.
     */
    public void trySwitchToGreen() {
        if(currentStatus.switchToGreen()){
            switchWithoutCheck(Status.GREEN);
        }
    }

    /**
     * Versucht in den Status REFACTOR zu wechseln.
     */
    public void trySwitchToRefactor() {
        if(currentStatus.switchToRefactor()){
            switchWithoutCheck(Status.REFACTOR);
        }
    }

    /**
     * Wechselt die (Test-)Klasse die gerade bearbeitet werden soll.
     * @param index Index der (Test-)Klasse.
     */
    public void changeClassframe(int index){
        currentStatus.changeClassframe(index);
    }

    /**
     * Oeffnet das Fenster mit der Aufgabenstellung.
     */
    public void showDescription(){
        PopupWindow.showExerciseDescriptionWindow(exercise);
    }

    /**
     * Wechselt ohne Ueberpruefung den Status.
     * @param status Status, in welchen gewechselt werden soll.
     */
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

    /**
     * Wird ausgefuehrt, sobald der Babystep-Timer abgelaufen ist und wechselt entsprechend den Status.
     * @param event Babystep-Timer ist abgelaufen.
     */
    @Override
    public void handle(BabystepperEvent event) {
        switchWithoutCheck(currentStatus.timeExpired());
    }
}
