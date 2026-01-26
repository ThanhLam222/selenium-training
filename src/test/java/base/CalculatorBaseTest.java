package base;

import exercise2_1.Calculator;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

public class CalculatorBaseTest {
    protected Calculator calculator;
    protected SoftAssert softAssert;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.out.println("=== Before Suite ===");
        boolean isCI = Boolean.parseBoolean(System.getProperty("ci", "false"));
        ConfigReader.loadConfig("exercise2_1", isCI);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("=== After Suite ===");
    }

//    @BeforeClass
//    public void beforeClass() {
//        System.out.println("=== Before Class ===");
//    }

//    @AfterClass
//    public void afterClass() {
//        System.out.println("=== After Class ===");
//    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        calculator = new Calculator();
        softAssert = new SoftAssert();
        System.out.println("=== Before Method ===");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        System.out.println("=== After Method ===");
    }
}
