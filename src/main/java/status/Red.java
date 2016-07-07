package status;

import gui.StatusDisplay;
import javafx.scene.paint.Color;
import status.babystep.BabystepControls;
import system.Exercise;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;

public class Red extends Status {

    public Red(StatusDisplay statusDisplay, Exercise exercise, BabystepControls babystepControls){
        super(statusDisplay,exercise,babystepControls);
        statusDisplay.displaySwitchStatusOptions(true,false,true);
        statusDisplay.displayStatus("RED", Color.valueOf("#FF0000"));
        statusDisplay.displayClassList(exercise.getTestNames());
        currentClassframe = exercise.getTestframes()[0];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
        babystepControls.restart();
    }

    @Override
    public int getStatus() {
        return Status.RED;
    }

    @Override
    public void changeClassframe(int index) {
        saveCurrentClassframe();
        currentClassframe = exercise.getTestframes()[index];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
    }

    @Override
    public boolean switchToGreen() {
        saveCurrentClassframe();
        JavaStringCompiler compiler = exercise.getCompiler();
        compiler.compileAndRunTests();
        if(!compiler.getCompilerResult().hasCompileErrors()){
            TestResult testResult = compiler.getTestResult();
            if (testResult.getNumberOfFailedTests() == 1) {
                statusDisplay.displayFeedback("NOTE: Exactly one test has failed, therefore switching from RED to GREEN");
                return true;
            } else {
                statusDisplay.displayFeedback("ERROR: Exactly one failed test is needed to switch to status GREEN. Currently " + testResult.getNumberOfFailedTests() + " tests have failed.");
                return false;
            }
        }else{
            statusDisplay.displayFeedback("NOTE: Could not compile tests, therefore switching from RED to GREEN");
            return true;
        }
    }
}
