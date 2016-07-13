package status;

import gui.StatusDisplay;
import status.babystep.BabystepControls;
import status.tracking.TimeTracker;
import status.tracking.Tracker;
import system.Classframe;
import system.Exercise;
/**
 * Repraesentiert die abstrakte Oberklasse der Statusse und speichert eine konstante int Variable fuer RED, GREEN und REFACTOR, eine Instanz von Tracker, TimeTracker, eine Aufgabe,
 * die aktuell zu bearbeitende (Test-)Klasse und zwei Interfaces StatusDisplay und BabyStepControls.
 */
public abstract class Status {
    public static final int RED=0,GREEN=1,REFACTOR=2;
    protected int status;
    protected Tracker tracker;
    protected TimeTracker timeTracker;
    protected Exercise exercise;
    protected Classframe currentClassframe;
    protected StatusDisplay statusDisplay;
    protected BabystepControls babystepControls;

    public Status(StatusDisplay statusDisplay, Exercise exercise, BabystepControls babystepControls, Tracker tracker){
        this.statusDisplay = statusDisplay;
        this.exercise = exercise;
        this.babystepControls = babystepControls;
        this.tracker = tracker;
        this.timeTracker = new TimeTracker();
        timeTracker.start();
    }

    /**
     * Muss von Unterklassen ueberschrieben werden.
     * @return Gibt jeweiligen Status zurueck.
     */
    public abstract int getStatus();

    /**
     * Muss von Unterklassen ueberschrieben werden.
     * Die Unterklassen entscheiden fuer welchen Classframe der Index steht.
     * @param index Uebergibt den selektierten Index.
     */
    public abstract void changeClassframe(int index);

    /**
     * Haelt das TimeTracking an und uebergibt die Zeit an Tracker.
     */
    public void stopTimeTracking(){
        timeTracker.end();
        tracker.addTimeToStatus(getStatus(),timeTracker);
    }

    /**
     * Setzt das TimeTracking fort.
     */
    public void continueTimeTracking(){
        timeTracker.start();
    }

    /**
     * Wenn es von den Unterklassen nicht ueberschrieben wird, dann ist ein Wechsel in RED nicht moeglich.
     * @return das Wechsel nicht moeglich ist.
     */
    public boolean switchToRed() {
        saveCurrentClassframe();
        return false;
    }

    /**
     * Wenn es von den Unterklassen nicht ueberschrieben wird, dann ist ein Wechsel in GREEN nicht moeglich.
     * @return das Wechsel nicht moeglich ist.
     */
    public boolean switchToGreen() {
        saveCurrentClassframe();
        return false;
    }

    /**
     * Wenn es von den Unterklassen nicht ueberschrieben wird, dann ist ein Wechsel in REFACTOR nicht moeglich.
     * @return das Wechsel nicht moeglich ist.
     */
    public boolean switchToRefactor() {
        saveCurrentClassframe();
        return false;
    }

    /**
     * Wenn es von den Unterklassen nicht ueberschrieben wird, dann wird bei Ablauf des BabyStep-Timers eine neue Instanz des gleichen Statusses erstellt.
     * @return den Status, in den gewechselt werden soll.
     */
    public int timeExpired(){
        saveCurrentClassframe();
        return getStatus();
    }

    /**
     * Speichert den aktuell dargestellten in der zugehoerigen Klasse.
     */
    protected void saveCurrentClassframe() {
        currentClassframe.setFrameContent(statusDisplay.getCode());
    }
}
