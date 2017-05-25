package tests;
import models.MessageUtil;
import org.junit.Assert;
import org.junit.Test;
import annotations.TestTemplate;

@TestTemplate
public class MessageUtilTests {
    String message = "Hello World";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testPrintMessage() {
        Assert.assertEquals(message,messageUtil.printMessage());
    }

    @Test
    public void shouldFailOnReturningDifferentString(){
        Assert.assertNotEquals(message, messageUtil.printMessage() + "123");
    }

}
