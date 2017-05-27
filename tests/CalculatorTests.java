package tests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import annotations.TestTemplate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TestTemplate
public class CalculatorTests {
    private Object calculator;

    @Before
    public void init() throws ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        Class<?> cl = Class.forName("models.Calculator");
        Constructor constructor = cl.getConstructor();
        constructor.setAccessible(true);
        this.calculator = cl.getConstructor().newInstance();
    }

    @Test
    public void testForCorrectAdditionResult() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method calculate = this.calculator.getClass().getMethod("calculate", int.class, int.class, String.class);
        calculate.setAccessible(true);

        Assert.assertEquals(10,calculate.invoke(this.calculator,5,5,"+"));
    }

    @Test
    public void testForCorrectSubtractionResult() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method calculate = this.calculator.getClass().getMethod("calculate", int.class, int.class, String.class);
        calculate.setAccessible(true);

        Assert.assertEquals(0,calculate.invoke(this.calculator,5,5,"-"));
    }

    @Test
    public void testForCorrectMultiplicationResult() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method calculate = this.calculator.getClass().getMethod("calculate", int.class, int.class, String.class);
        calculate.setAccessible(true);

        Assert.assertEquals(25,calculate.invoke(this.calculator,5,5,"*"));
    }

    @Test
    public void testForCorrectDivisionResult() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method calculate = this.calculator.getClass().getMethod("calculate", int.class, int.class, String.class);
        calculate.setAccessible(true);

        Assert.assertEquals(1,calculate.invoke(this.calculator,5,5,"/"));
    }

    @Test
    public void testForCorrectUnknownSignResult() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method calculate = this.calculator.getClass().getMethod("calculate", int.class, int.class, String.class);
        calculate.setAccessible(true);

        Assert.assertEquals(0,calculate.invoke(this.calculator,5,5,"="));
    }

}
