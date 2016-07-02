package status;

import gui.controller.MainWindowController;
import system.Exercise;

/**
 * Created by mayma on 02.07.2016.
 */
public class Green extends Status{

    public Green(MainWindowController mainWindow, Exercise exercise){
        super(mainWindow,exercise);
    }

    @Override
    public int getStatus(){
        return Status.RED;
    }

    @Override
    public void changeClassframe(int index){
        currentClassframe.setFrameContent(mainWindow.getCode());
        currentClassframe = exercise.getClassframes()[index];
        mainWindow.fillCodeArea(currentClassframe.getFrameContent());
    }
}
