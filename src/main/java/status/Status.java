package status;

import gui.controller.MainWindowController;
import system.Classframe;
import system.Exercise;

/**
 * Created by mayma on 02.07.2016.
 */
public abstract class Status {
    public static final int RED=0,GREEN=1,REFACTOR=2;
    protected int status;
    protected Exercise exercise;
    protected Exercise backup;
    protected Classframe currentClassframe;
    protected MainWindowController mainWindow;
    public Status(MainWindowController mainWindow,Exercise exercise){
        this.mainWindow = mainWindow;
        this.exercise = exercise;
        status = Status.RED;
        currentClassframe = exercise.getClassframes()[0];
        mainWindow.fillClassList(exercise.getTestNames());
    }

    public abstract int getStatus();

    public abstract void changeClassframe(int index);
}
