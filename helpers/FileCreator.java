package helpers;

import java.io.*;

public class FileCreator {
    private static final String JAVA_FILE_EXTENSION = ".java";
    private static final String FILE_NOT_FOUND_ERROR_MSG = "File not found";
    private static final String IO_ERROR_MSG = "IO Error occured";
    private static final String TESTS_EXTENSION = "Tests";

    public FileCreator() {
    }

    private String readText(String fileName){
        String returnValue = "";
        FileReader file;
        String line = "";

        try {
            file = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(file);
            try {
                while ((line = reader.readLine()) != null) {
                    returnValue += line + "\n";
                }
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR_MSG);
        } catch (IOException e) {
            throw new RuntimeException(IO_ERROR_MSG);
        }
        return returnValue;
    }


    public void createFile(String filePath, String fileName, String testableCode) throws IOException {
        File file = new File(filePath, fileName + JAVA_FILE_EXTENSION);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()))){
            writer.write(testableCode);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String cmdPath = "./models" + "/" + fileName + JAVA_FILE_EXTENSION;

        this.compileJavaFile(cmdPath);

    }

    private void compileJavaFile(String className){
        /* String array to execute commands */
        String[] commands = new String[3];
        commands[0] = "cmd";
        commands[1] = "/c";
        /* Command you want to execute */
        commands[2] = "cd C:\\Users\\Atlas\\IdeaProjects\\javacExample\\src && javac -cp .;lib/junit-4.12.jar " + className;

        Process process = null;

        try {
            /* Create process */
            process = Runtime.getRuntime().exec(commands);
            //wait for the process to end
            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTest(String fileName) throws IOException {
        String testsText = this.readText("C:\\Users\\Atlas\\IdeaProjects\\javacExample\\src\\unitTestTemplates\\MessageUtilTests.txt");

        this.createFile("C:\\Users\\Atlas\\IdeaProjects\\javacExample\\src\\tests", fileName + TESTS_EXTENSION, testsText);

        String pathToFile = "./tests/" + fileName + TESTS_EXTENSION + JAVA_FILE_EXTENSION;

        this.compileJavaFile(pathToFile);
    }

}
