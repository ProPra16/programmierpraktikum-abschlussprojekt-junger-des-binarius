package system;

import vk.core.api.CompilationUnit;

import java.util.Stack;

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

    public void clearAllSavedContent(){
        savedContent.clear();
    }

    public void saveContent(){
        savedContent.push(frameContent);
    }

    public void restoreContent(){
        frameContent = savedContent.pop();
    }

    public void setFrameContent(String frameContent){
        this.frameContent = frameContent;
    }

    public String getName(){
        return name;
    }

    public String getFrameContent(){
        return frameContent;
    }

    public boolean isTest(){
        return isTest;
    }

    public CompilationUnit getCompilationUnit() {
        return new CompilationUnit(name, frameContent, isTest);
    }
}
