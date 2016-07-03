package status;

import gui.controller.MainWindowController;
import system.Exercise;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;

public class Red extends Status {

    public Red(MainWindowController mainWindow, Exercise exercise){
        super(mainWindow,exercise);
    }

    @Override
    public int getStatus(){
        return Status.RED;
    }

    @Override
    public void changeClassframe(int index) {
        saveCurrentClassframe();
        currentClassframe = exercise.getTestframes()[index];
        mainWindow.fillCodeArea(currentClassframe.getFrameContent());
    }
    @Override
    public Status switchToRed() {
        saveCurrentClassframe();

        return new Red(mainWindow, exercise);
    }
    @Override
    public Status switchToGreen() {
        saveCurrentClassframe();
        JavaStringCompiler compiler = exercise.getCompiler();
        compiler.compileAndRunTests();
        if(!compiler.getCompilerResult().hasCompileErrors()){
            TestResult testResult = compiler.getTestResult();
            if (testResult.getNumberOfFailedTests() == 1) {
                return new Green(mainWindow, exercise);
            } else {
                mainWindow.addTextLineToOutputArea("ALERT: Exactly one failed test is needed to switch to status GREEN. Currently " + testResult.getNumberOfFailedTests() + " tests have failed.");
                return this;
            }
        }else{
            mainWindow.addTextLineToOutputArea("ALERT: Could not compile tests");
            return new Green(mainWindow, exercise);
        }
    }
    @Override
    public Status switchToRefactor() {
        saveCurrentClassframe();

        return new Refactor(mainWindow, exercise);
    }
}
