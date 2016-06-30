package system;

/**
 * Created by mayma on 27.06.2016.
 */
public class Classframe {
    private String classname;
    private String frameContent;
    private boolean isTest;

    public Classframe(String classname,String frameContent,boolean isTest){
        this.classname = classname;
        this.frameContent = frameContent;
        this.isTest = isTest;
    }

    public String getClassname(){
        return classname;
    }

    public String getframeContent(){
        return frameContent;
    }

    public boolean isTest(){
        return isTest;
    }
}
