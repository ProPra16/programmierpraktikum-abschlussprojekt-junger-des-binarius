package system;

import org.junit.Test;
import vk.core.api.CompilerFactory;
import vk.core.api.CompilerResult;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ExerciseTest {
    @Test
    public void testCompileWithExercise(){
        Catalog catalog = new Catalog();
        catalog.loadCatalogFromXML(new File(getClass().getClassLoader().getResource("TestExerciseCatalog.xml").getFile()));
        Exercise testExercise = catalog.getExercise(0);
        JavaStringCompiler compiler = CompilerFactory.getCompiler(testExercise.getCompilationUnits());
        compiler.compileAndRunTests();
        CompilerResult cresult = compiler.getCompilerResult();
        assertFalse(cresult.hasCompileErrors());
        TestResult tresult = compiler.getTestResult();
        assertEquals(tresult.getNumberOfSuccessfulTests(),2);
    }
}
