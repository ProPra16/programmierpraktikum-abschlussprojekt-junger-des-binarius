package system;

/**
 * Created by mayma on 27.06.2016.
 */
public class Classframe {
    private String classname;
    private String frameContent;

    public Classframe(String classname,String frameContent){
        this.classname = classname;
        this.frameContent = frameContent;
    }

    public String getClassname(){
        return classname;
    }

    public String getframeContent(){
        return frameContent;
    }
}
