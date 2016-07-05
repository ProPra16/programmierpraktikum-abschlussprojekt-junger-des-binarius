package status;

import gui.controller.MainWindowController;
import javafx.scene.paint.Color;
import system.Exercise;

public class Refactor extends Status{

    public Refactor(MainWindowController mainWindow, Exercise exercise){
        super(mainWindow,exercise);
        mainWindow.setStatusButtonDisabled(false,true,true);
        mainWindow.setStatusLabel("REFACTOR", Color.TURQUOISE);
        mainWindow.fillClassList(exercise.getClassNames());
        currentClassframe = exercise.getClassframes()[0];
        mainWindow.fillCodeArea(currentClassframe.getFrameContent());
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
