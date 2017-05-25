package engine;

import constants.Constants;
import helpers.ClassLoader;
import helpers.CmdManipulator;
import helpers.CustomClassLoader;
import helpers.FileCreator;
import io.CodeReader;

public class Runner {
    private static final String SRC_FOLDER_PATH = "/src";
    private static final String JAVAC_COMMAND = "javac -cp .;lib/junit-4.12.jar ./models/Calculator.java ./tests/CalculatorTests.java ./testRunners/TestRunner.java";
    private static final String JAVA_COMMAND = "java -cp .;lib/junit-4.12.jar.;lib/hamcrest-core-1.3.jar testRunners/TestRunner";

    public static void main(String[] args) {
        FileCreator fileCreator = new FileCreator();
        CodeReader codeReader = new CodeReader();
        CmdManipulator cmdManipulator = new CmdManipulator(Constants.PROJECT_PATH + SRC_FOLDER_PATH, JAVAC_COMMAND, JAVA_COMMAND);
        ClassLoader classLoader = new CustomClassLoader();

        Runnable engine = new Engine(cmdManipulator, fileCreator, codeReader, classLoader);

        engine.run();
    }
}
