package system;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class CatalogTest {
    @Test
    public void testXMLReading() throws IOException {
        Catalog catalog = new Catalog();
        catalog.loadCatalogFromXML(new File(getClass().getClassLoader().getResource("TestCatalog.xml").getFile()));
        assertTrue(catalog.getAllExerciseNames().get(0).equals("Exercise1"));
        assertTrue(catalog.getAllExerciseNames().get(1).equals("Exercise2"));
        Exercise exercise1 = catalog.getExercise(0);
        Exercise exercise2 = catalog.getExercise(1);
        assertEquals(exercise1.getDescription(),"Description1");
        assertEquals(exercise2.getDescription(),"Description2");
        assertTrue(exercise1.getClassframes()[0].getFrameContent().equals("Classframe11"));
        assertTrue(exercise1.getClassframes()[1].getFrameContent().equals("Classframe12"));
        assertTrue(exercise2.getClassframes()[0].getFrameContent().equals("Classframe21"));
        assertTrue(exercise2.getClassframes()[1].getFrameContent().equals("Classframe22"));
        assertTrue(exercise1.getTestframes()[0].getFrameContent().equals("Testframe11"));
        assertTrue(exercise1.getTestframes()[1].getFrameContent().equals("Testframe12"));
        assertTrue(exercise2.getTestframes()[0].getFrameContent().equals("Testframe21"));
        assertTrue(exercise2.getTestframes()[1].getFrameContent().equals("Testframe22"));
        assertTrue(exercise1.getBabystepTime()==1);
        assertTrue(exercise2.getBabystepTime()==2);
        assertFalse(exercise1.getBabystepStatusSwitchActivated());
        assertTrue(exercise2.getBabystepStatusSwitchActivated());
        assertEquals(exercise1.getClassNames().get(0), "Class11");
        assertEquals(exercise1.getClassNames().get(1), "Class12");
        assertEquals(exercise2.getClassNames().get(0), "Class21");
        assertEquals(exercise2.getClassNames().get(1), "Class22");
        assertEquals(exercise1.getTestNames().get(0), "Class11Test");
        assertEquals(exercise1.getTestNames().get(1), "Class12Test");
        assertEquals(exercise2.getTestNames().get(0), "Class21Test");
        assertEquals(exercise2.getTestNames().get(1), "Class22Test");
    }
}
