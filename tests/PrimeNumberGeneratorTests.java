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
    private Method generatePrimesMethod;

    @Before
    public void init() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cl = Class.forName("models.PrimeNumberGenerator");
        Constructor constructor = cl.getConstructor();
        constructor.setAccessible(true);
        this.primeNumberGenerator = cl.getConstructor().newInstance();

        this.generatePrimesMethod = this.primeNumberGenerator.getClass().getMethod("generatePrimeNumbers", int.class, int.class);
        this.generatePrimesMethod.setAccessible(true);

    }

    @Test
    @SuppressWarnings("unchecked")
    public void correctFirstNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> res = (List<Integer>) this.generatePrimesMethod.invoke(this.primeNumberGenerator, 2,100);
        Assert.assertTrue(2 == res.get(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void correctLastNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> res = (List<Integer>) this.generatePrimesMethod.invoke(this.primeNumberGenerator, 2,100);
        int lastIndex = res.size() - 1;

        Assert.assertTrue(97 == res.get(lastIndex));
    }

}
