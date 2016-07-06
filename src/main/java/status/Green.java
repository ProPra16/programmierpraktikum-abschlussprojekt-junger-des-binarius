package status;

import gui.StatusDisplay;
import javafx.scene.paint.Color;
import system.Exercise;

public class Green extends Status{

    public Green(StatusDisplay statusDisplay, Exercise exercise){
        super(statusDisplay,exercise);
        statusDisplay.displaySwitchStatusOptions(false,true,false);
        statusDisplay.displayStatus("GREEN", Color.valueOf("#00FF00"));
        statusDisplay.displayClassList(exercise.getClassNames());
        currentClassframe = exercise.getClassframes()[0];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
    }

    @Override
    public int getStatus(){
        return Status.GREEN;
    }

    @Override
    public void changeClassframe(int index){
        currentClassframe.setFrameContent(statusDisplay.getCode());
        currentClassframe = exercise.getClassframes()[index];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
    }

}
