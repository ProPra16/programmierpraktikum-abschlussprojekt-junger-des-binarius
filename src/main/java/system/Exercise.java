package system;

/**
 * Created by mayma on 27.06.2016.
 */
public class Exercise {
    private String name;
    private String description;
    private Classframe[] classframes;
    private Classframe[] testframes;

    public Exercise(String name,String description,Classframe[] classframes,Classframe[] testframes){
        this.name = name;
        this.description = description;
        this.classframes = classframes;
        this.testframes = testframes;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Classframe[] getClassframes() {
        return classframes;
    }

    public Classframe[] getTestframes() {
        return testframes;
    }
}
