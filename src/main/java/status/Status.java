package status;

import gui.controller.MainWindowController;
import system.Classframe;
import system.Exercise;

public abstract class Status {
    public static final int RED=0,GREEN=1,REFACTOR=2;
    protected int status;
    protected Exercise exercise;
    protected Classframe currentClassframe;
    protected MainWindowController mainWindow;
    public Status(MainWindowController mainWindow,Exercise exercise){
        this.mainWindow = mainWindow;
        this.exercise = exercise;
    }

    public abstract int getStatus();

    public abstract void changeClassframe(int index);

    public Status switchToRed() {
        saveCurrentClassframe();
        return this;
    }

    public Status switchToGreen() {
        saveCurrentClassframe();
        return this;
    }

    public Status switchToRefactor() {
        saveCurrentClassframe();
        return this;
    }

    protected void saveCurrentClassframe() {
        currentClassframe.setFrameContent(mainWindow.getCode());
    }
}
