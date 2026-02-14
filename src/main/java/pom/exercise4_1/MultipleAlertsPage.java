package pom.exercise4_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MultipleAlertsPage extends BasePage {
    private final By doublePromtLink = By.id("double-prompt");
    private final By resultPromt1 = By.id("text1");
    private final By resultPromt2 = By.id("text2");

    public MultipleAlertsPage(WebDriver driver) {
        super(driver);
    }

    public MultipleAlertsPage navigateToMultipleAlertsPage() {
        driver.get(getConfig("multiple.url"));
        return this;
    }

    public MultipleAlertsPage sendKeyToTwoAlert(String text1, String text2) {
        click(doublePromtLink);
        sendKeysToAlert(text1);
        acceptAlert();
        sendKeysToAlert(text2);
        acceptAlert();
        return this;
    }

    public String getResultOfPrompt1() {
        return getText(resultPromt1);
    }

    public String getResultOfPrompt2() {
        return getText(resultPromt2);
    }
}
