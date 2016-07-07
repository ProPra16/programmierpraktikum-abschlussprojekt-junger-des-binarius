package status;

import gui.AlertWindow;
import gui.StatusDisplay;
import javafx.scene.paint.Color;
import system.Exercise;
import vk.core.api.CompilerResult;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;

public class Green extends Status{

    public Green(StatusDisplay statusDisplay, Exercise exercise){
        super(statusDisplay,exercise);
        exercise.saveCurrentContent();
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

    @Override
    public Status switchToRed() {
        boolean confirmationResult = AlertWindow.confirmation("You are trying to switch back to status RED. \nTherefore the current progress in status GREEN will be erased.","Do you want to continue?");
        if(confirmationResult) {
            exercise.restoreSavedContent();
            statusDisplay.displayFeedback("NOTE: Switched back to status RED. New progress erased and former content restored.");
            return new Red(statusDisplay,exercise);
        } else {
            statusDisplay.displayFeedback("NOTE: Switching back to status RED cancelled.");
            return this;
        }
    }

    @Override
    public Status switchToRefactor() {
        saveCurrentClassframe();
        JavaStringCompiler compiler = exercise.getCompiler();
        compiler.compileAndRunTests();
        CompilerResult compilerResult = compiler.getCompilerResult();
        if(!compilerResult.hasCompileErrors()) {
            TestResult testResult = compiler.getTestResult();
            if(testResult.getNumberOfFailedTests()==0) {
                exercise.saveCurrentContent();
                statusDisplay.displayFeedback("NOTE: Compilation and testing successful. Therefore switching to status REFACTOR.");
                return new Refactor(statusDisplay,exercise);
            } else {
                statusDisplay.displayFeedback("ERROR: To switch to status REFACTOR all tests must be successful. Currently " + testResult.getNumberOfFailedTests() + " tests have failed.");
                return this;
            }
        } else {
            statusDisplay.displayFeedback("ERROR: Could not compile. Switching to status REFACTOR is not possible.");
            return this;
        }
    }

}
