package testRunners;

import annotations.TestTemplate;
import engine.Engine;
import helpers.CmdManipulator;
import helpers.FileCreator;
import io.CodeReader;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import java.io.File;

public class TestRunner {

    /*public static void main(String[] args) throws ClassNotFoundException {
        


        File[] testFiles = new File("C:\\Users\\Atlas\\IdeaProjects\\javacExample\\src\\tests").listFiles();

        Result result = null;

        //Exit result from the program
        for (File testFile : testFiles) {
            String fileName = testFile.getName();
            String finalName = fileName.substring(0, fileName.indexOf("."));

            Class c = Class.forName("tests." + finalName);

            if (!fileName.endsWith("class") && c.isAnnotationPresent(TestTemplate.class)) {
                result = JUnitCore.runClasses(c);
                break;
            }
        }

        //boolean result from tests
        System.out.println(result.wasSuccessful());

    }*/
}

