package status;

import gui.StatusDisplay;
import javafx.scene.paint.Color;
import status.babystep.BabystepControls;
import status.tracking.Tracker;
import system.Exercise;
import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;

public class Red extends Status {

    public Red(StatusDisplay statusDisplay, Exercise exercise, BabystepControls babystepControls, Tracker tracker){
        super(statusDisplay,exercise,babystepControls,tracker);
        statusDisplay.displaySwitchStatusOptions(true,false,true);
        statusDisplay.displayStatus("RED", Color.valueOf("#FF0000"));
        statusDisplay.displayClassList(exercise.getTestNames());
        currentClassframe = exercise.getTestframes()[0];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
        babystepControls.restart();
        exercise.saveCurrentContent();
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
        JavaStringCompiler compiler = CompilerFactory.getCompiler(exercise.getCompilationUnits());
        compiler.compileAndRunTests();
        if(!compiler.getCompilerResult().hasCompileErrors()){
            TestResult testResult = compiler.getTestResult();
            if (testResult.getNumberOfFailedTests() == 1) {
                statusDisplay.displayFeedback("NOTE: Exactly one test has failed, therefore switching from RED to GREEN");
                timeTracker.end();
                tracker.addTimeToStatus(getStatus(),timeTracker);
                return true;
            } else {
                statusDisplay.displayFeedback("ERROR: Exactly one failed test is needed to switch to status GREEN. Currently " + testResult.getNumberOfFailedTests() + " tests have failed.");
                return false;
            }
        }else{
            statusDisplay.displayFeedback("NOTE: Could not compile tests, therefore switching from RED to GREEN");
            timeTracker.end();
            tracker.addTimeToStatus(getStatus(),timeTracker);
            return true;
        }
    }

    @Override
    public int timeExpired() {
        exercise.restoreSavedContent();
        statusDisplay.displayFeedback("ALERT: Time is Up! New Code was erased.");
        timeTracker.end();
        tracker.addTimeToStatus(getStatus(),timeTracker);
        return getStatus();
    }
}
