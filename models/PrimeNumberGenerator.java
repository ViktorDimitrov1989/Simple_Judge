package models;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberGenerator {

    public PrimeNumberGenerator() {
    }

    public List<Integer> generatePrimeNumbers(int start, int end){
        List<Integer> primes = new ArrayList<>();

        for (int i = start; i < end; i++) {
            boolean isPrimeNumber = true;

            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrimeNumber = false;
                    break;
                }
            }

            if (isPrimeNumber) {
                primes.add(i);
            }
        }

        return primes;
    }

}
