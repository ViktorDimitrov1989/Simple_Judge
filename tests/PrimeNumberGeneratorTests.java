package tests;

import models.PrimeNumberGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PrimeNumberGeneratorTests {
    private PrimeNumberGenerator primeNumberGenerator;

    @Before
    public void init(){
        this.primeNumberGenerator = new PrimeNumberGenerator();
    }

    @Test
    public void resultListMustBeExactLength(){
        Assert.assertEquals(25,this.primeNumberGenerator.generatePrimeNumbers(2,100).size());
    }

    @Test
    public void testForCorrectLastNumber(){
        List<Integer> result = this.primeNumberGenerator.generatePrimeNumbers(2,100);
        int lastIndexNumber = result.get(result.size() - 1);

        Assert.assertTrue(97  == lastIndexNumber);
    }

    @Test
    public void testForCorrectFirstNumber(){
        List<Integer> result = this.primeNumberGenerator.generatePrimeNumbers(4,100);
        int lastIndexNumber = result.get(0);

        Assert.assertTrue(5  == lastIndexNumber);
    }

}
