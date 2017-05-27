package tests;

import annotations.TestTemplate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@TestTemplate
public class PrimeNumberGeneratorTests {
    private Object primeNumberGenerator;

    @Before
    public void init() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cl = Class.forName("models.PrimeNumberGenerator");
        Constructor constructor = cl.getConstructor();
        constructor.setAccessible(true);
        this.primeNumberGenerator = cl.getConstructor().newInstance();

    }

    @Test
    @SuppressWarnings("unchecked")
    public void resultListMustBeExactLength() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method generatePrimes = this.primeNumberGenerator.getClass().getMethod("generatePrimeNumbers", int.class, int.class);
        generatePrimes.setAccessible(true);

        List<Integer> res = (List<Integer>) generatePrimes.invoke(this.primeNumberGenerator, 2,100);
        Assert.assertEquals(25, res.size());
    }

}
