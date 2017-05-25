package helpers;

import org.junit.runner.Result;

import java.io.*;

public class CmdManipulator {
    private String sourceFolderPath;
    private String javacCommand;
    private String javaCommand;

    public CmdManipulator(String sourceFolderPath, String javacCommand, String javaCommand) {
        this.sourceFolderPath = sourceFolderPath;
        this.javacCommand = javacCommand;
        this.javaCommand = javaCommand;
    }

    public void executeTestCommands() throws IOException, InterruptedException {
        /* String array to execute commands */
        String[] commands = new String[3];
        commands[0] = "cmd";
        commands[1] = "/c";
        /* Command you want to execute */
        commands[2] = String.format("cd %s && %s && %s", this.sourceFolderPath, this.javacCommand, this.javaCommand);

        try {
            /* Create process */
            Runtime.getRuntime().exec(commands);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
