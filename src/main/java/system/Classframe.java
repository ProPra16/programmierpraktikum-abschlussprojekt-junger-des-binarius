package system;

import vk.core.api.CompilationUnit;

import java.util.Stack;

/**
 * Repraesentiert eine (Test-)Klasse und speichert Name, Inhalt, gesicherte Inhalte (Stack) und ob die Klasse eine Testklasse ist.
 */
public class Classframe {
    private String name;
    private String frameContent;
    private boolean isTest;
    private Stack<String> savedContent;

    public Classframe(String name,String frameContent,boolean isTest){
        this.name = name;
        this.frameContent = frameContent;
        this.isTest = isTest;
        savedContent = new Stack<String>();
    }

    /**
     * Loescht alle gesicherten Inhalte vom Stack.
     */
    public void clearAllSavedContent(){
        savedContent.clear();
    }

    /**
     * Sichert den aktuellen Inhalt auf dem Stack.
     */
    public void saveContent(){
        savedContent.push(frameContent);
    }

    /**
     * Stellt den letzten Stand des Inhalts wieder her und loescht diesen vom Stack.
     */
    public void restoreContent(){
        frameContent = savedContent.pop();
    }

    /**
     * Setzt den Inhalt der Klasse.
     * @param frameContent Inhalt.
     */
    public void setFrameContent(String frameContent){
        this.frameContent = frameContent;
    }

    /**
     * @return den Namen der (Test-)Klasse.
     */
    public String getName(){
        return name;
    }

    /**
     * @return den Inhalt der (Test-)Klasse.
     */
    public String getFrameContent(){
        return frameContent;
    }

    /**
     * @return ob es sich um eine Testklasse handelt.
     */
    public boolean isTest(){
        return isTest;
    }

    /**
     * Erstellt eine CompilationUnit mit den hier gespeicherten Klassenattributen.
     * @return eine CompilationUnit.
     */
    public CompilationUnit getCompilationUnit() {
        return new CompilationUnit(name, frameContent, isTest());
    }
}
