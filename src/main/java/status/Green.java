package status;

import gui.controller.MainWindowController;
import javafx.scene.paint.Color;
import system.Exercise;

public class Green extends Status{

    public Green(MainWindowController mainWindow, Exercise exercise){
        super(mainWindow,exercise);
        mainWindow.setStatusButtonDisabled(false,true,false);
        mainWindow.setStatusLabel("GREEN", Color.valueOf("#00FF00"));
        mainWindow.fillClassList(exercise.getClassNames());
        currentClassframe = exercise.getClassframes()[0];
        mainWindow.fillCodeArea(currentClassframe.getFrameContent());
    }

    @Override
    public int getStatus(){
        return Status.GREEN;
    }

    @Override
    public void changeClassframe(int index){
        currentClassframe.setFrameContent(mainWindow.getCode());
        currentClassframe = exercise.getClassframes()[index];
        mainWindow.fillCodeArea(currentClassframe.getFrameContent());
    }

}
