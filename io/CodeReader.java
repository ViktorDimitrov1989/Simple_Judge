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
                "import annotations.TestableMethod;\n" +
                "\n" +
                "public class Calculator {\n" +
                "\n" +
                "    public Calculator(){\n" +
                "    }\n" +
                "\n" +
                "    @TestableMethod(name = \"calculate\")\n" +
                "    public int calculate(int operand1,int operand2, String operationSymbol){\n" +
                "        switch (operationSymbol){\n" +
                "            case \"+\":\n" +
                "                return operand1 + operand2;\n" +
                "            case \"-\":\n" +
                "                return operand1 - operand2;\n" +
                "            case \"*\":\n" +
                "                return operand1 * operand2;\n" +
                "            case \"/\":\n" +
                "                return operand1 / operand2;\n" +
                "            default:\n" +
                "                return 0;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}";
    }


}
