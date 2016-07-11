package status;

import gui.StatusDisplay;
import status.babystep.BabystepControls;
import status.tracking.TimeTracker;
import status.tracking.Tracker;
import system.Classframe;
import system.Exercise;

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

    public abstract int getStatus();

    public abstract void changeClassframe(int index);

    public void stopTimeTracking(){
        timeTracker.end();
        tracker.addTimeToStatus(getStatus(),timeTracker);
    }

    public void continueTimeTracking(){
        timeTracker.start();
    }

    public boolean switchToRed() {
        saveCurrentClassframe();
        return false;
    }

    public boolean switchToGreen() {
        saveCurrentClassframe();
        return false;
    }

    public boolean switchToRefactor() {
        saveCurrentClassframe();
        return false;
    }

    public int timeExpired(){
        saveCurrentClassframe();
        return getStatus();
    }
    protected void saveCurrentClassframe() {
        currentClassframe.setFrameContent(statusDisplay.getCode());
    }
}
