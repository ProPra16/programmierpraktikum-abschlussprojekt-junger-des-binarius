package system;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Liest den Aufgabenkatalog ein und stellt Methoden zur Behandlung bereit.
 */
public class Catalog {
    private static final File catalogFileXML = new File("Catalog.xml");
    private Exercise[] exercises;

    /**
     * Gibt eine Aufgabe zurueck, wenn der Catalog bereits eingelesen wurde mit loadCatalogFromXML().
     * @param index Der index dieser Aufgabe.
     * @return die ausgewaehlte Aufgabe.
     */
    public Exercise getExercise(int index){
        if(exercises!=null) {
            if (index < 0 || index >= exercises.length) {
                return null;
            }
        }
        return exercises[index];
    }

    /**
     * @return alle Namen der Aufgaben in einer Liste.
     */
    public List<String> getAllExerciseNames(){
        List<String> names = new LinkedList<>();
        for(Exercise exercise : exercises){
            names.add(exercise.getName());
        }
        return names;
    }

    /**
     * @return alle Namen der Aufgaben in einer Liste mit Hinweis, ob Babysteps eingeschaltet sind.
     */
    public List<String> getAllExerciseNamesWithBabystepsInformation(){
        List<String> names = new LinkedList<>();
        for(Exercise exercise : exercises){
            names.add(exercise.getName()+(exercise.getBabystepStatusSwitchActivated()?" (B)":""));
        }
        return names;
    }

    /**
     * Liesst einen XML-Aufgabenkatalog ein und speichert die Aufgaben in einem Exercise Array.
     * @param alternativeCatalogFileXML Alternative XML-Datei zum Testen (Falls null, dann Standart-Aufgabenkatalog).
     */
    public void loadCatalogFromXML(File alternativeCatalogFileXML){
        try {
            DocumentBuilderFactory docbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = docbfac.newDocumentBuilder();
            Document document;
            if(alternativeCatalogFileXML==null) {
                document = builder.parse(catalogFileXML);
            } else {
                document = builder.parse(alternativeCatalogFileXML);
            }
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

    /**
     * Eine Aufgabe aus einer XML-Datei einlesen.
     * @param exerciseElement Aufgaben-Knoten einer XML-Datei.
     * @return eine Aufgabe.
     */
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
                classframes.add(new Classframe(oneclass.getAttribute("name"),oneclass.getTextContent(),false));
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
                testframes.add(new Classframe(onetest.getAttribute("name"),onetest.getTextContent(),true));
            }
        }
        else System.out.println("Exercise: " + name + " has no tests");
        Classframe[] testframearray = testframes.toArray(new Classframe[testframes.size()]);

        //Config
        NodeList configlist = exerciseElement.getElementsByTagName("config");
        Element config = (Element)configlist.item(0);
        NodeList babystepslist = config.getElementsByTagName("babysteps");
        Element babystep = (Element)babystepslist.item(0);
        boolean babystepStatusSwitchActivated = babystep.getAttribute("statusSwitch").equals("true");
        long babysteptime = Long.valueOf(babystep.getAttribute("time"));
        return new Exercise(name,description,classframearray,testframearray,babysteptime,babystepStatusSwitchActivated);
    }
}
