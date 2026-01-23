package exercise2_1;

import base.CalculatorBaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigReader;

public class CalculatorTest extends CalculatorBaseTest {
    @Test(groups = "basic")
    public void testAdd() {
        int a = Integer.parseInt(ConfigReader.getProperty("add.a"));
        int b = Integer.parseInt(ConfigReader.getProperty("add.b"));
        int expected = Integer.parseInt(ConfigReader.getProperty("add.expected"));
        Assert.assertEquals(calculator.add(a, b), expected);
    }

    @Test(groups = "basic")
    public void testSubtract() {
        int a = Integer.parseInt(ConfigReader.getProperty("subtract.a"));
        int b = Integer.parseInt(ConfigReader.getProperty("subtract.b"));
        int expected = Integer.parseInt(ConfigReader.getProperty("subtract.expected"));
        Assert.assertEquals(calculator.subtract(a, b), expected);
    }

    @Test(groups = "basic")
    public void testMultiply() {
        int a = Integer.parseInt(ConfigReader.getProperty("mul.a"));
        int b = Integer.parseInt(ConfigReader.getProperty("mul.b"));
        int expected = Integer.parseInt(ConfigReader.getProperty("mul.expected"));
        int positive = Integer.parseInt(ConfigReader.getProperty("mul.expected.positive"));

        softAssert.assertEquals(calculator.multiply(a, b), expected);
        softAssert.assertTrue(calculator.multiply(a, b) > positive);
        softAssert.assertAll();
    }

    @Test(groups = "basic")
    public void testDivide() {
        int a = Integer.parseInt(ConfigReader.getProperty("div.norm.a"));
        int b = Integer.parseInt(ConfigReader.getProperty("div.norm.b"));
        double expected = Double.parseDouble(ConfigReader.getProperty("div.norm.expected"));
        Assert.assertEquals(calculator.divide(a, b), expected);
    }

    @Test(groups = "edge")
    public void testDivideByZero() {
        int a = Integer.parseInt(ConfigReader.getProperty("div.edge.a"));
        int b = Integer.parseInt(ConfigReader.getProperty("div.edge.b"));

        Assert.assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(a, b)
        );
    }
}
