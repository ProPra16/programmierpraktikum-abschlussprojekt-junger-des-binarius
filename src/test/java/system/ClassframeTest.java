package system;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ClassframeTest {
    @Test
    public void testSaveStack(){
        Classframe frame = new Classframe("Name","Content1",false);
        frame.saveContent();
        frame.setFrameContent("Content2");
        frame.saveContent();
        frame.setFrameContent("Content3");

        assertTrue(frame.getFrameContent().equals("Content3"));
        frame.restoreContent();
        assertTrue(frame.getFrameContent().equals("Content2"));
        frame.restoreContent();
        assertTrue(frame.getFrameContent().equals("Content1"));
    }
}
