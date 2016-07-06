package status;

import gui.StatusDisplay;
import javafx.scene.paint.Color;
import system.Exercise;

public class Refactor extends Status{

    public Refactor(StatusDisplay statusDisplay, Exercise exercise){
        super(statusDisplay,exercise);
        statusDisplay.displaySwitchStatusOptions(false,true,true);
        statusDisplay.displayStatus("REFACTOR", Color.TURQUOISE);
        statusDisplay.displayClassList(exercise.getClassNames());
        currentClassframe = exercise.getClassframes()[0];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
    }

    @Override
    public int getStatus(){
        return Status.REFACTOR;
    }

    @Override
    public void changeClassframe(int index){
        currentClassframe.setFrameContent(statusDisplay.getCode());
        currentClassframe = exercise.getClassframes()[index];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
    }

}
