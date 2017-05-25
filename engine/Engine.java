package engine;

import annotations.TestTemplate;
import helpers.CmdManipulator;
import helpers.FileCreator;
import io.CodeReader;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import javax.naming.MalformedLinkException;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.ClassLoader;

public class Engine implements Runnable {
    private static final String PATH_TO_MODELS_FOLDER = "C:\\Users\\Atlas\\IdeaProjects\\javacExample\\src\\models";
    private static final String PROJECT_FOLDER_PATH = System.getProperty("user.dir");
    //Run cmd queries
    private CmdManipulator cmdManipulator;
    //Manipulate string input and create .java file from it
    private FileCreator fileCreator;
    //Handle user input
    private CodeReader codeReader;

    public Engine(CmdManipulator cmdManipulator, FileCreator fileCreator, CodeReader codeReader) {
        this.cmdManipulator = cmdManipulator;
        this.fileCreator = fileCreator;
        this.codeReader = codeReader;
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
            this.fileCreator.createFile(PATH_TO_MODELS_FOLDER, className, codeToTest);
            this.fileCreator.createTest(className);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Result result = null;

        File[] testFiles = new File(PROJECT_FOLDER_PATH + "/src/tests/").listFiles();

        for (File testFile : testFiles) {
            //String name = this.removeExtension(testFile.getName());
            Class<?> c = null;

            try {
                c = this.loadClass(testFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (c.isAnnotationPresent(TestTemplate.class)) {
                result = JUnitCore.runClasses(c);
                break;
            }
        }



        //boolean result from tests
        System.out.println(result.wasSuccessful());

    }

    private Class<?> loadClass(File file) throws Exception {
        URL[] url = new URL[0];

        try {
            url = new URL[]{file.toURI().toURL()};
        }catch (MalformedURLException e){
            System.out.println(e.getMessage());
        }

        int substringLength = file.getName().indexOf(".");

        if(substringLength != -1){
            String className = this.removeExtension(file.getName());


            URLClassLoader classLoader = new URLClassLoader(url);

            Class currentClass = null;

            try {
                currentClass = classLoader.loadClass("tests." + className);

                return currentClass;
            }catch (ClassNotFoundException e){
                System.out.println("Class " + className + " not found");
            }
        }

        return null;
    }

    private String removeExtension(String file){
        return file.replaceFirst("[.][^.]+$", "");
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
