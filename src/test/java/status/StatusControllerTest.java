package status;

import gui.StatusDisplay;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.Test;
import status.babystep.BabystepperEvent;
import system.Classframe;
import system.Exercise;

import java.util.List;
import static org.junit.Assert.*;

public class StatusControllerTest extends Application implements StatusDisplay{
    private String codeAreaText;

    private String validTest =
            "import org.junit.Test;\n" +
            "import static org.junit.Assert.*;\n"+
            "public class TTest{\n" +
            "@Test\n"+
            "public void test(){\n"+
            "TClass tclass = new TClass();\n"+
            "assertTrue(tclass.getTrue());\n"+
            "}\n"+
            "}";

    private String invalidTest = "hier konnte ihre werbung stehen";

    private String validClass =
            "public class TClass{\n"+
            "public boolean getTrue(){\n"+
            "return true;\n"+
            "}\n"+
            "}";

    private String invalidClass =
            "public class TClass{\n"+
            "public boolean getTrue(){\n"+
            "return false;\n"+
            "}\n"+
            "}";
    @Test
    public void testCompleteCycle(){
        launch();

        Classframe[] classframes = new Classframe[1];
        classframes[0] = new Classframe("TClass","",false);
        Classframe[] testframes = new Classframe[1];
        testframes[0] = new Classframe("TTest","",true);
        Exercise testExercise = new Exercise("Name","description",classframes,testframes,0,false);
        StatusController statusController = new StatusController(this,testExercise);
        assertEquals(statusController.getStatus(),Status.RED);

        //Mit falschem test muesste trotzdem in GREEN gehen, weil nicht kompilieren kann
        codeAreaText = invalidTest;
        statusController.trySwitchToGreen();
        assertEquals(Status.GREEN,statusController.getStatus());

        //Mit der richtigen Klasse aber dem falschen Test sollte es hier bei GREEN bleiben, da immernoch nicht wegen dem Test kompiliert werden kann
        codeAreaText = validClass;
        statusController.trySwitchToRefactor();
        assertEquals(Status.GREEN,statusController.getStatus());

        //Simulieren das Zeit abgelaufen ist. Da wir uns in GREEN befinden sollte hier in RED gegangen werden und auch jeglicher Code gelöscht werden
        statusController.handle(new BabystepperEvent());
        assertEquals(Status.RED,statusController.getStatus());
        assertEquals("",testExercise.getClassframes()[0].getFrameContent());
        assertEquals("",testExercise.getTestframes()[0].getFrameContent());

        //Richtigen Test schreiben dann in GREEN wechseln
        codeAreaText = validTest;
        statusController.trySwitchToGreen();
        assertEquals(Status.GREEN,statusController.getStatus());

        //Klasse schreiben die den Test nicht erfuellt. Muesste bei GREEN bleiben
        codeAreaText = invalidClass;
        statusController.trySwitchToRefactor();
        assertEquals(Status.GREEN,statusController.getStatus());

        //Richtige Klasse schreiben die Test erfuellt. Muesste damit in REFACTOR gehen
        codeAreaText = validClass;
        statusController.trySwitchToRefactor();
        assertEquals(Status.REFACTOR,statusController.getStatus());

        //In Refactor zu einer fehlerhaften Klasse umschreiben die den Test nicht erfuellt. Muesste also in REFACTOR bleiben
        codeAreaText = invalidClass;
        statusController.trySwitchToRed();
        assertEquals(Status.REFACTOR,statusController.getStatus());

        //In Refactor wieder zu richtiger Klasse umschreiben. Muesste damit wieder in RED gehen koennen und ein zyklus ist abgeschlossen
        codeAreaText = validClass;
        statusController.trySwitchToRed();
        assertEquals(Status.RED,statusController.getStatus());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Will ich nicht aber muss das Fenster einmal zeigen damit alle Funktionen wie new Image() funktionieren.
        primaryStage.show();
        primaryStage.close();
    }

    @Override
    public String getCode() {
        return codeAreaText;
    }

    @Override
    public void displayCode(String code) {}

    @Override
    public void displayClassList(List<String> content) {}

    @Override
    public void displayStatus(String statusText, Color color) {}

    @Override
    public void displaySwitchStatusOptions(boolean red, boolean green, boolean refactor) {}

    @Override
    public void displayFeedback(String text) {
    }

    @Override
    public void displayRemainingTime(double timeRemaining) {}
}
