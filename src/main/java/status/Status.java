package status;

import gui.controller.MainWindowController;
import system.Classframe;
import system.Exercise;

/**
 * Created by mayma on 02.07.2016.
 */
public class Status {
    public static final int RED=0,GREEN=1,REFACTOR=2;
    private int status;
    private Exercise exercise;
    private Exercise backup;
    private Classframe currentClassframe;
    private MainWindowController mainWindow;
    public Status(MainWindowController mainWindow,Exercise exercise){
        this.mainWindow = mainWindow;
        this.exercise = exercise;
        status = Status.RED;
        currentClassframe = exercise.getClassframes()[0];
        mainWindow.fillClassList(exercise.getTestNames());
    }

    public int getStatus(){
        return status;
    }

    public void changeStatus(int status){
        boolean success = false;
        switch (status){
            case Status.RED: success=changeToRed(); break;
            case Status.GREEN: success=changeToGreen(); break;
            case Status.REFACTOR: success=changeToRefactor(); break;
        }
    }

    private boolean changeToRed(){
        boolean success = false;
        mainWindow.fillClassList(exercise.getTestNames());
        changeClassframe(0);
        return success;
    }

    public void changeClassframe(int index){
        if(status == Status.RED){
            currentClassframe.setFrameContent(mainWindow.getCode());
            currentClassframe = exercise.getTestframes()[index];
            mainWindow.fillCodeArea(currentClassframe.getFrameContent());
        }
    }
    private boolean changeToGreen(){
        boolean success = false;


        return success;
    }
    private boolean changeToRefactor(){
        boolean success = false;


        return success;
    }

}
