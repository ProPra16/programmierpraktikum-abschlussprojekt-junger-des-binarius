package system;

import vk.core.api.CompilationUnit;

/**
 * Created by mayma on 27.06.2016.
 */
public class Classframe {
    private String name;
    private String frameContent;
    private boolean isTest;
    private String savedContent;

    public Classframe(String name,String frameContent,boolean isTest){
        this.name = name;
        this.frameContent = frameContent;
        saveContent();
        this.isTest = isTest;
    }

    public void saveContent(){
        savedContent = frameContent;
    }

    public void restoreContent(){
        frameContent = savedContent;
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
