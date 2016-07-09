package status;

import gui.StatusDisplay;
import status.babystep.BabystepControls;
import system.Classframe;
import system.Exercise;

public abstract class Status {
    public static final int RED=0,GREEN=1,REFACTOR=2;
    protected int status;
    protected Exercise exercise;
    protected Classframe currentClassframe;
    protected StatusDisplay statusDisplay;
    protected BabystepControls babystepControls;
    public Status(StatusDisplay statusDisplay, Exercise exercise, BabystepControls babystepControls){
        this.statusDisplay = statusDisplay;
        this.exercise = exercise;
        this.babystepControls = babystepControls;
    }

    public abstract int getStatus();

    public abstract void changeClassframe(int index);

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
