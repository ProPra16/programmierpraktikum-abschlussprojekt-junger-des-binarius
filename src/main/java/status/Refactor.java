package status;

import gui.controller.MainWindowController;
import system.Exercise;

/**
 * Created by mayma on 02.07.2016.
 */
public class Refactor extends Status{

    public Refactor(MainWindowController mainWindow, Exercise exercise){
        super(mainWindow,exercise);
    }

    @Override
    public int getStatus(){
        return Status.REFACTOR;
    }

    @Override
    public void changeClassframe(int index){
        currentClassframe.setFrameContent(mainWindow.getCode());
        currentClassframe = exercise.getClassframes()[index];
        mainWindow.fillCodeArea(currentClassframe.getFrameContent());
    }
}
