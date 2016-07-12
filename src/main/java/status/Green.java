package status;

import gui.PopupWindow;
import gui.StatusDisplay;
import javafx.scene.paint.Color;
import status.babystep.BabystepControls;
import status.tracking.Tracker;
import system.Exercise;
import vk.core.api.*;

public class Green extends Status{

    public Green(StatusDisplay statusDisplay, Exercise exercise, BabystepControls babystepControls, Tracker tracker){
        super(statusDisplay,exercise,babystepControls,tracker);
        exercise.saveCurrentContent();
        statusDisplay.displaySwitchStatusOptions(false,true,false);
        statusDisplay.displayStatus("GREEN", Color.valueOf("#00FF00"));
        statusDisplay.displayClassList(exercise.getClassNames());
        currentClassframe = exercise.getClassframes()[0];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
        babystepControls.resetTimer();
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

    @Override
    public boolean switchToRed() {
        babystepControls.pauseTimer();
        boolean confirmationResult = PopupWindow.showConfirmationWindow("You are trying to switch back to status RED. \nTherefore the current progress in status GREEN will be erased.","Do you want to continue?");
        babystepControls.continueTimer();
        if(confirmationResult) {
            exercise.restoreSavedContent();
            exercise.restoreSavedContent();
            statusDisplay.displayFeedback("NOTE: Switched back to status RED. New progress erased and former content restored.");
            stopTimeTracking();
            return true;
        } else {
            statusDisplay.displayFeedback("NOTE: Switching back to status RED cancelled.");
            return false;
        }
    }

    @Override
    public boolean switchToRefactor() {
        saveCurrentClassframe();
        CompilationUnit[] compilationUnits = exercise.getCompilationUnits();
        JavaStringCompiler compiler = CompilerFactory.getCompiler(compilationUnits);
        compiler.compileAndRunTests();
        CompilerResult compilerResult = compiler.getCompilerResult();
        if(!compilerResult.hasCompileErrors()) {
            TestResult testResult = compiler.getTestResult();
            if(testResult.getNumberOfFailedTests()==0) {
                statusDisplay.displayFeedback("NOTE: Compilation and testing successful. Therefore switching to status REFACTOR.");
                stopTimeTracking();
                return true;
            } else {
                statusDisplay.displayFeedback("ERROR: To switch to status REFACTOR all tests must be successful. Currently " + testResult.getNumberOfFailedTests() + " tests have failed.");
                tracker.incrementErrorCount("FailedTestsInGREEN");
                return false;
            }
        } else {
            statusDisplay.displayFeedback("ERROR: Could not compile. Switching to status REFACTOR is not possible.");
            for(CompilationUnit unit: compilationUnits){
                tracker.analyseAndAddCompileErrors(compilerResult.getCompilerErrorsForCompilationUnit(unit));
            }
            return false;
        }
    }

    @Override
    public int timeExpired() {
        exercise.restoreSavedContent();
        exercise.restoreSavedContent();
        statusDisplay.displayFeedback("ALERT: Time is Up! Switching back to the beginning of Red");
        stopTimeTracking();
        return Status.RED;
    }
}
