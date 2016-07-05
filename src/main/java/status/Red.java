package status;

import gui.controller.MainWindowController;
import javafx.scene.paint.Color;
import system.Exercise;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;

public class Red extends Status {

    public Red(MainWindowController mainWindow, Exercise exercise){
        super(mainWindow,exercise);
        mainWindow.setStatusButtonDisabled(true,false,true);
        mainWindow.setStatusLabel("RED", Color.valueOf("#FF0000"));
        mainWindow.fillClassList(exercise.getTestNames());
        currentClassframe = exercise.getTestframes()[0];
        mainWindow.fillCodeArea(currentClassframe.getFrameContent());
    }

    @Override
    public int getStatus() {
        return Status.RED;
    }

    @Override
    public void changeClassframe(int index) {
        saveCurrentClassframe();
        currentClassframe = exercise.getTestframes()[index];
        mainWindow.fillCodeArea(currentClassframe.getFrameContent());
    }

    @Override
    public Status switchToGreen() {
        saveCurrentClassframe();
        JavaStringCompiler compiler = exercise.getCompiler();
        compiler.compileAndRunTests();
        if(!compiler.getCompilerResult().hasCompileErrors()){
            TestResult testResult = compiler.getTestResult();
            if (testResult.getNumberOfFailedTests() == 1) {
                mainWindow.addTextLineToOutputArea("NOTATION: Exactly one test has failed, therefore switching from RED to GREEN");
                return new Green(mainWindow, exercise);
            } else {
                mainWindow.addTextLineToOutputArea("ALERT: Exactly one failed test is needed to switch to status GREEN. Currently " + testResult.getNumberOfFailedTests() + " tests have failed.");
                return this;
            }
        }else{
            mainWindow.addTextLineToOutputArea("ALERT: Could not compile tests, therefore switching from RED to GREEN");
            return new Green(mainWindow, exercise);
        }
    }
}
