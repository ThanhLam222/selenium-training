package base;

import core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Override in subtest
        String exercise = getExerciseName();

        boolean isCI = Boolean.parseBoolean(System.getProperty("ci", "false"));

        ConfigReader.loadConfig(exercise, isCI);
        driver = DriverManager.initDriver();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }

    /// Override in subtest
    protected String getExerciseName() {
        return "exercise1";
    }

}
