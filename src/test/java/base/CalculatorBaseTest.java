package base;

import exercise2_1.Calculator;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

public class CalculatorBaseTest {
    protected Calculator calculator;
    protected SoftAssert softAssert;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("=== Before Suite ===");
        boolean isCI = Boolean.parseBoolean(System.getProperty("ci", "false"));
        ConfigReader.loadConfig("exercise2_1", isCI);
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("=== After Suite ===");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("=== Before Class ===");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("=== After Class ===");
    }

    @BeforeMethod
    public void setUp() {
        calculator = new Calculator();
        softAssert = new SoftAssert();
        System.out.println("=== Before Method ===");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("=== After Method ===");
    }
}
