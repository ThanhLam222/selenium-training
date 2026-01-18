package POM.Exercise1_3;

import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected String getConfig(String key) {
        return ConfigReader.getProperty(key);
    }
}
