package base;

import core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    @Parameters("isCI")
    public void setUp(@Optional("false") String isCIFromXml) {
        // Override in subtest
        String exercise = getExerciseName();

        boolean isCI = Boolean.parseBoolean(System.getProperty("ci", isCIFromXml));

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
