package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CodeReader implements Reader {
    private BufferedReader reader;

    public CodeReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String read() throws IOException {
        //return this.reader.readLine();
        return "package models;\n" +
                "public class MessageUtil {\n" +
                "    private String message;\n" +
                "\n" +
                "    public MessageUtil(String message){\n" +
                "        this.message = message;\n" +
                "    }\n" +
                "\n" +
                "    public String printMessage(){\n" +
                "        System.out.println(message);\n" +
                "        return message;\n" +
                "    }\n" +
                "\n" +
                "}";
    }


}
