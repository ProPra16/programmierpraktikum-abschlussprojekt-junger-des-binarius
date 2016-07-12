package system;

import vk.core.api.CompilationUnit;

import java.util.LinkedList;
import java.util.List;

/**
 * Repraesentiert eine Aufgabe. Speichert Name, Aufgabenstellung, alle Loesungsskelette (Inhalte) fuer Klassen und Testklassen, und alle Babystep-Parameter.
 */
public class Exercise {
    private String name;
    private String description;
    private Classframe[] classframes;
    private Classframe[] testframes;
    private long babystepTime;
    private boolean babystepStatusSwitchActivated;
    public Exercise(String name,String description,Classframe[] classframes,Classframe[] testframes,long babystepTime, boolean babystepStatusSwitchActivated){
        this.name = name;
        this.description = description;
        this.classframes = classframes;
        this.testframes = testframes;
        this.babystepTime = babystepTime;
        this.babystepStatusSwitchActivated=babystepStatusSwitchActivated;
    }

    /**
     * @return ob fuer diese Aufgabe Babysteps aktiviert ist.
     */
    public boolean getBabystepStatusSwitchActivated(){
        return babystepStatusSwitchActivated;
    }

    /**
     * @return die Zeitspanne fuer einen Babystep-Schritt in Sekunden.
     */
    public long getBabystepTime(){
        return babystepTime;
    }

    /**
     * @return den Namen der Aufgabe.
     */
    public String getName() {
        return name;
    }

    /**
     * @return die Aufgabenstellung der Aufgabe.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return alle Loesungsskelette fuer Klassen in einem Array.
     */
    public Classframe[] getClassframes() {
        return classframes;
    }

    /**
     * @return alle Loesungsskelette fuer Testklassen in einem Array.
     */
    public Classframe[] getTestframes() {
        return testframes;
    }

    /**
     * @return alle Klassennamen in einer Liste.
     */
    public List<String> getClassNames(){
        return getAllNames(classframes);
    }

    /**
     * @return alle Testklassennamen in einer Liste.
     */
    public List<String> getTestNames(){
        return getAllNames(testframes);
    }

    /**
     * Methode, welche alle Namen eines Classframe-Arrays in einer Liste zurueckgibt.
     * @param frames Classframe-Array.
     * @return alle Namen.
     */
    private List<String> getAllNames(Classframe[] frames){
        List<String> names = new LinkedList<>();
        for(Classframe frame:frames){
            names.add(frame.getName());
        }
        return names;
    }

    /**
     * Befiehlt jeder Klasse und Testklasse ihren aktuellen Inhalt auf dem Stack zu speichern.
     */
    public void saveCurrentContent() {
        for(int i=0;i<classframes.length;i++) {
            classframes[i].saveContent();
        }
        for(int j=0;j<testframes.length;j++) {
            testframes[j].saveContent();
        }
    }

    /**
     * Befiehlt jeder Klasse und Testklasse ihren letzten Inhalt wiederherzustellen und vom Stack zu loeschen.
     */
    public void restoreSavedContent() {
        for(int i=0;i<classframes.length;i++) {
            classframes[i].restoreContent();
        }
        for(int j=0;j<testframes.length;j++) {
            testframes[j].restoreContent();
        }
    }

    /**
     * Befiehlt jeder Klasse und Testklasse ihre gespeicherten Inhalte vom Stack zu loeschen.
     */
    public void clearAllSavedContent(){
        for(int i=0;i<classframes.length;i++) {
            classframes[i].clearAllSavedContent();
        }
        for(int j=0;j<testframes.length;j++) {
            testframes[j].clearAllSavedContent();
        }
    }

    /**
     * Sammelt alle CompilationUnits der Classframes fuer Klassen und Testklassen.
     * @return alle CompilationUnits in einem Array.
     */
    public CompilationUnit[] getCompilationUnits() {
        List<CompilationUnit> compilationUnits = new LinkedList<>();
        for(Classframe classframe:classframes) {
            compilationUnits.add(classframe.getCompilationUnit());
        }
        for(Classframe testframe:testframes) {
            compilationUnits.add(testframe.getCompilationUnit());
        }
        return compilationUnits.toArray(new CompilationUnit[compilationUnits.size()]);
    }
}
