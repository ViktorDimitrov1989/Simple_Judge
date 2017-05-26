package engine;

import helpers.ClassLoader;
import helpers.CustomClassLoader;
import helpers.FileCreator;
import io.CodeReader;

public class Runner {

    public static void main(String[] args) {
        FileCreator fileCreator = new FileCreator();
        CodeReader codeReader = new CodeReader();
        ClassLoader classLoader = new CustomClassLoader();

        Runnable engine = new Engine(fileCreator, codeReader, classLoader);

        engine.run();
    }
}
