package status;

import gui.StatusDisplay;
import javafx.scene.paint.Color;
import status.babystep.BabystepControls;
import status.tracking.Tracker;
import system.Exercise;
import vk.core.api.*;

public class Refactor extends Status{

    public Refactor(StatusDisplay statusDisplay, Exercise exercise, BabystepControls babystepControls, Tracker tracker){
        super(statusDisplay,exercise,babystepControls,tracker);
        statusDisplay.displaySwitchStatusOptions(false,true,true);
        statusDisplay.displayStatus("REFACTOR", Color.TURQUOISE);
        statusDisplay.displayClassList(exercise.getClassNames());
        currentClassframe = exercise.getClassframes()[0];
        statusDisplay.displayCode(currentClassframe.getFrameContent());
        babystepControls.restart();
        babystepControls.stop();
        exercise.saveCurrentContent();
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

    @Override
    public boolean switchToRed() {
        saveCurrentClassframe();
        CompilationUnit[] compilationUnits = exercise.getCompilationUnits();
        JavaStringCompiler compiler = CompilerFactory.getCompiler(compilationUnits);
        compiler.compileAndRunTests();
        CompilerResult compilerResult = compiler.getCompilerResult();
        if(!compilerResult.hasCompileErrors()) {
            TestResult testResult = compiler.getTestResult();
            if(testResult.getNumberOfFailedTests()==0) {
                statusDisplay.displayFeedback("NOTE: Compilation and testing successful. Therefore switching to status RED.");
                exercise.clearAllSavedContent();
                stopTimeTracking();
                return true;
            } else {
                statusDisplay.displayFeedback("ERROR: To switch to status RED all tests must be successful. Currently " + testResult.getNumberOfFailedTests() + " tests have failed.");
                return false;
            }
        } else {
            statusDisplay.displayFeedback("ERROR: Could not compile. Switching to status RED is not possible.");
            for(CompilationUnit unit: compilationUnits){
                tracker.analyseAndAddCompileErrors(compilerResult.getCompilerErrorsForCompilationUnit(unit));
            }
            return false;
        }
    }
}
