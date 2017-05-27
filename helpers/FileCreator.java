package helpers;

import constants.Constants;

import java.io.*;

public class FileCreator {
    private static final String JAVA_FILE_EXTENSION = ".java";
    private static final String FILE_NOT_FOUND_ERROR_MSG = "File not found";
    private static final String IO_ERROR_MSG = "IO Error occured";
    private static final String TESTS_EXTENSION = "Tests";
    private static final String TXT_EXTENSION = ".txt";

    public FileCreator() {
    }

    private String readText(String fileName) {
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


    public File createFile(String filePath, String fileName, String testableCode) throws IOException {
        String fullPath = Constants.PROJECT_PATH + Constants.SOURCE_CODE_PATH + filePath;

        File file = new File(fullPath, fileName + JAVA_FILE_EXTENSION);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()))) {
            writer.write(testableCode);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    private void compileJavaFile(String className) {
        String[] commands = new String[3];
        commands[0] = "cmd";
        commands[1] = "/c";
        commands[2] = "cd " + Constants.PROJECT_PATH + Constants.SOURCE_CODE_PATH + " && javac -cp .;lib/junit-4.12.jar " + className;

        Process process = null;

        try {
            process = Runtime.getRuntime().exec(commands);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File createTest(String fileName) throws IOException {
        String pathToText = Constants.PROJECT_PATH +
                Constants.SOURCE_CODE_PATH +
                Constants.UNIT_TEST_TEMPLATES_PATH
                + fileName + TESTS_EXTENSION + TXT_EXTENSION;

        String testsText = this.readText(pathToText);

        String filePath = Constants.TESTS_PATH;

        String file = fileName + TESTS_EXTENSION;

        /*String pathToFile = "./tests/" + fileName + TESTS_EXTENSION + JAVA_FILE_EXTENSION;
        this.compileJavaFile(pathToFile);*/

        return this.createFile(filePath,
                file, testsText);
    }

}
