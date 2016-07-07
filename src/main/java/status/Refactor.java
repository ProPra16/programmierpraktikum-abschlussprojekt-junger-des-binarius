package status;

import gui.StatusDisplay;
import javafx.scene.paint.Color;
import system.Exercise;
import vk.core.api.CompilerResult;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;

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

    @Override
    public Status switchToRed() {
        saveCurrentClassframe();
        JavaStringCompiler compiler = exercise.getCompiler();
        compiler.compileAndRunTests();
        CompilerResult compilerResult = compiler.getCompilerResult();
        if(!compilerResult.hasCompileErrors()) {
            TestResult testResult = compiler.getTestResult();
            if(testResult.getNumberOfFailedTests()==0) {
                exercise.saveCurrentContent();
                statusDisplay.displayFeedback("NOTE: Compilation and testing successful. Therefore switching to status RED.");
                return new Red(statusDisplay,exercise);
            } else {
                statusDisplay.displayFeedback("ERROR: To switch to status RED all tests must be successful. Currently " + testResult.getNumberOfFailedTests() + " tests have failed.");
                return this;
            }
        } else {
            statusDisplay.displayFeedback("ERROR: Could not compile. Switching to status RED is not possible.");
            return this;
        }
    }
}
