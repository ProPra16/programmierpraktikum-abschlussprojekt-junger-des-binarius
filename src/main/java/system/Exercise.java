package system;

import vk.core.api.CompilationUnit;
import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;
import java.util.LinkedList;
import java.util.List;

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

    public List<String> getClassNames(){
        return getAllNames(classframes);
    }

    public List<String> getTestNames(){
        return getAllNames(testframes);
    }

    private List<String> getAllNames(Classframe[] frames){
        List<String> names = new LinkedList<>();
        for(Classframe frame:frames){
            names.add(frame.getName());
        }
        return names;
    }

    public JavaStringCompiler getCompiler() {
        List<CompilationUnit> compilationUnits = new LinkedList<>();
        for(Classframe classframe:classframes) {
            compilationUnits.add(classframe.getCompilationUnit());
        }
        for(Classframe testframe:testframes) {
            compilationUnits.add(testframe.getCompilationUnit());
        }
        return CompilerFactory.getCompiler(compilationUnits.toArray(new CompilationUnit[compilationUnits.size()]));
    }
}
