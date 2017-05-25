package engine;

import helpers.CmdManipulator;
import helpers.FileCreator;
import io.CodeReader;

public class Runner {
    private static final String SRC_FOLDER_PATH = "C:\\\\Users\\\\Atlas\\\\IdeaProjects\\\\javacExample\\\\src";
    private static final String JAVAC_COMMAND = "javac -cp .;lib/junit-4.12.jar ./models/MessageUtil.java ./tests/MessageUtilTests.java ./testRunners/TestRunner.java";
    private static final String JAVA_COMMAND = "java -cp .;lib/junit-4.12.jar.;lib/hamcrest-core-1.3.jar testRunners/TestRunner";

    public static void main(String[] args) {
        FileCreator fileCreator = new FileCreator();
        CodeReader codeReader = new CodeReader();
        CmdManipulator cmdManipulator = new CmdManipulator(SRC_FOLDER_PATH, JAVAC_COMMAND, JAVA_COMMAND);

        Runnable engine = new Engine(cmdManipulator, fileCreator, codeReader);
        engine.run();
    }
}
