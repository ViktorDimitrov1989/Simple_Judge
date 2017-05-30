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
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "public class PrimeNumberGenerator {\n" +
                "\n" +
                "    public PrimeNumberGenerator() {\n" +
                "        System.out.println(\"prime number generator is here!\");\n" +
                "    }\n" +
                "\n" +
                "    public List<Integer> generatePrimeNumbers(int start, int end){\n" +
                "        List<Integer> primes = new ArrayList<>();\n" +
                "\n" +
                "        for (int i = start; i < end; i++) {\n" +
                "            boolean isPrimeNumber = true;\n" +
                "\n" +
                "            for (int j = 2; j < i; j++) {\n" +
                "                if (i % j == 0) {\n" +
                "                    isPrimeNumber = false;\n" +
                "                    break;\n" +
                "                }\n" +
                "            }\n" +
                "\n" +
                "            if (isPrimeNumber) {\n" +
                "                primes.add(i);\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        return primes;\n" +
                "    }\n" +
                "\n" +
                "}\n";
    }


}
