package engine;

import annotations.TestTemplate;
import constants.Constants;
import helpers.ClassLoader;
import helpers.CmdManipulator;
import helpers.FileCreator;
import io.CodeReader;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Engine implements Runnable {
    //Run cmd queries
    private CmdManipulator cmdManipulator;
    //Manipulate string input and create .java file from it
    private FileCreator fileCreator;
    //Handle user input
    private CodeReader codeReader;
    //Load classes
    private ClassLoader classLoader;

    public Engine(CmdManipulator cmdManipulator, FileCreator fileCreator, CodeReader codeReader, ClassLoader classLoader) {
        this.cmdManipulator = cmdManipulator;
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
            this.fileCreator.createFile(Constants.MODELS_PATH, className, codeToTest);
            this.fileCreator.createTest(className);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Result result = null;

        String testsPath = Constants.PROJECT_PATH +
                Constants.SOURCE_CODE_PATH +
                Constants.TESTS_PATH;


        //--------------------------------------check junitTests result---------------------------------------------//
        File[] testFiles = new File(testsPath).listFiles();

        for (File testFile : testFiles) {
            //String name = this.removeExtension(testFile.getName());
            Class<?> c = null;

            try {
                c = this.classLoader.loadClass(testFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (c.isAnnotationPresent(TestTemplate.class)) {//TODO
                result = JUnitCore.runClasses(c);
                break;
            }
        }

        //boolean result from tests
        System.out.println(result.wasSuccessful());
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
