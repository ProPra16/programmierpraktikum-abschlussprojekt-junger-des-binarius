package status;

import gui.StatusDisplay;
import javafx.scene.paint.Color;
import status.babystep.BabystepControls;
import status.tracking.Tracker;
import system.Exercise;
import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;

/**
 * Repraesentiert den Status RED.
 */
public class Red extends Status {

    public Red(StatusDisplay statusDisplay, Exercise exercise, BabystepControls babystepControls, Tracker tracker){
        super(statusDisplay,exercise,babystepControls,tracker);
        statusDisplay.displaySwitchStatusOptions(true,false,true);
        statusDisplay.displayStatus("RED", Color.valueOf("#FF0000"));
        statusDisplay.displayClassList(exercise.getTestNames());
        currentClassframe = exercise.getTestframes()[0];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
        babystepControls.resetTimer();
        exercise.saveCurrentContent();
    }

    /**
     * @return Gibt den Status RED zurueck.
     */
    @Override
    public int getStatus() {
        return Status.RED;
    }

    /**
     * Wechselt zur ausgewaehlten Testklasse und stellt diese dar.
     * @param index Welche Testklasse bearbeitet werden soll.
     */
    @Override
    public void changeClassframe(int index) {
        saveCurrentClassframe();
        currentClassframe = exercise.getTestframes()[index];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
    }

    /**
     * Es wird versucht in den Status GREEN zu wechseln.
     * @return Gibt zurueck ob ein Wechsel moeglich ist.
     */
    @Override
    public boolean switchToGreen() {
        saveCurrentClassframe();
        JavaStringCompiler compiler = CompilerFactory.getCompiler(exercise.getCompilationUnits());
        compiler.compileAndRunTests();
        if(!compiler.getCompilerResult().hasCompileErrors()){
            TestResult testResult = compiler.getTestResult();
            if (testResult.getNumberOfFailedTests() == 1) {
                statusDisplay.displayFeedback("NOTE: Exactly one test has failed, therefore switching from RED to GREEN");
                stopTimeTracking();
                return true;
            } else {
                statusDisplay.displayFeedback("ERROR: Exactly one failed test is needed to switch to status GREEN. Currently " + testResult.getNumberOfFailedTests() + " tests have failed.");
                return false;
            }
        }else{
            statusDisplay.displayFeedback("NOTE: Could not compile tests, therefore switching from RED to GREEN");
            stopTimeTracking();
            return true;
        }
    }

    /**
     * Wird aufgerufen wenn Babystep-Timer abgelaufen ist.
     * Stellt den Anfangsstand des Codes aus Status RED wieder her.
     * @return  Gibt Status zurueck in den zurueckgewechselt werden soll (Hier in einer neuen Instanz des Status RED).
     */
    @Override
    public int timeExpired() {
        exercise.restoreSavedContent();
        statusDisplay.displayFeedback("ALERT: Time is Up! New Code was erased.");
        stopTimeTracking();
        return getStatus();
    }
}
