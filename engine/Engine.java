package engine;

import annotations.TestTemplate;
import constants.Constants;
import helpers.ClassLoader;
import helpers.CustomClassLoader;
import helpers.FileCreator;
import io.CodeReader;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Engine implements Runnable {
    //Manipulate string input and create .java file from it
    private FileCreator fileCreator;
    //Handle user input
    private CodeReader codeReader;
    //Load classes
    private ClassLoader classLoader;

    public Engine(FileCreator fileCreator, CodeReader codeReader, ClassLoader classLoader) {
        this.fileCreator = fileCreator;
        this.codeReader = codeReader;
        this.classLoader = classLoader;
    }

    @Override
    public void run() {
        String codeToTest = "";

        try {
            codeToTest = this.codeReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String className = this.extractFileName(codeToTest);

        try {
            File entityFile = this.fileCreator.createFile(Constants.MODELS_PATH, className, codeToTest);
            File testsFile = this.fileCreator.createTest(className);

            Class modelClass = this.classLoader.loadClass(entityFile, "model");

            String debug = "";

            Class testCLass = this.classLoader.loadClass(testsFile, "test");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //--------------------------------------check junitTests result---------------------------------------------//
        Result result = null;

        String testsPath = Constants.PROJECT_PATH +
                Constants.SOURCE_CODE_PATH +
                Constants.TESTS_PATH;

        File[] testFiles = new File(testsPath).listFiles();

        for (File testFile : testFiles) {
            if(testFile.getName().contains(".class")){
                continue;
            }

            Class<?> c = null;
            //TODO compile only the file which is needed and get Class with Class.forName() method
            try {
                c = this.classLoader.loadClass(testFile, "test");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (c.isAnnotationPresent(TestTemplate.class)) {
                result = JUnitCore.runClasses(c);
                //boolean result from tests
                System.out.println(result.wasSuccessful());
                break;
            }
        }
    }

    private String extractFileName(String codeToTest) {
        String pattern = "(?:public class )([a-zA-Z]+)";
        Pattern regexp = Pattern.compile(pattern);
        Matcher matcher = regexp.matcher(codeToTest);

        if(matcher.find()){
            return matcher.group(1);
        }

        return null;
    }
}
