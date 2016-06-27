package system;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;

/**
 * Created by mayma on 27.06.2016.
 */

public class Catalog {
    private static final String pathCatalog = "Catalog.xml";
    private Exercise[] exercises;
    public Catalog(){

    }
    public Exercise getExercise(int i){
        return exercises[i];
    }
    public void loadCatalogFromXML(){
        try {
            DocumentBuilderFactory docbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = docbfac.newDocumentBuilder();
            Document document = builder.parse(new File(pathCatalog));
            document.normalize();
            Element root = document.getDocumentElement();
            NodeList XMLexercises = root.getElementsByTagName("exercise");
            exercises = new Exercise[XMLexercises.getLength()];
            for(int i=0;i<XMLexercises.getLength();i++){
                exercises[i]=readExerciseFromXML((Element)XMLexercises.item(i));
            }
        }catch(Exception e){
            System.out.println("Fehler beim Katalog einlesen");
        }
    }

    private Exercise readExerciseFromXML(Element exerciseElement){
        //Name
        String name=exerciseElement.getAttribute("name");

        //Description
        NodeList descriptionlist = exerciseElement.getElementsByTagName("description");
        String description;
        if(descriptionlist.getLength()>=1) description = descriptionlist.item(0).getTextContent();
        else description= "No description found";

        //Classframes
        NodeList classeslist = exerciseElement.getElementsByTagName("classes");
        Element classes;
        LinkedList<Classframe> classframes = new LinkedList<>();
        if(classeslist.getLength()>=1){
            classes=(Element)classeslist.item(0);
            NodeList allclasses = classes.getElementsByTagName("class");
            for(int x=0;x<allclasses.getLength();x++){
                Element oneclass = (Element)allclasses.item(x);
                classframes.add(new Classframe(oneclass.getAttribute("name"),oneclass.getTextContent()));
            }
        }
        else System.out.println("Exercise: " + name + " has no classes");
        Classframe[] classframearray = classframes.toArray(new Classframe[classframes.size()]);

        //Testframes
        NodeList testlist = exerciseElement.getElementsByTagName("tests");
        LinkedList<Classframe> testframes = new LinkedList<>();
        Element tests;
        if(classeslist.getLength()>=1){
            tests=(Element)testlist.item(0);
            NodeList alltests = tests.getElementsByTagName("test");
            for(int x=0;x<alltests.getLength();x++){
                Element onetest = (Element)alltests.item(x);
                testframes.add(new Classframe(onetest.getAttribute("name"),onetest.getTextContent()));
            }
        }
        else System.out.println("Exercise: " + name + " has no tests");
        Classframe[] testframearray = testframes.toArray(new Classframe[testframes.size()]);

        return new Exercise(name,description,classframearray,testframearray);
    }
}
