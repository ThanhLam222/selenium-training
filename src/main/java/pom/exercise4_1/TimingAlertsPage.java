package pom.exercise4_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TimingAlertsPage extends BasePage {
    private final By alertBtn = By.id("timerAlertButton");

    public TimingAlertsPage(WebDriver driver) {
        super(driver);
    }

    public TimingAlertsPage navigateToTimingAlertsPage() {
        driver.get(getConfig("timing.url"));
        return this;
    }

    public TimingAlertsPage clickAlertBtn() {
        click(alertBtn);
        return this;
    }

    public String getAlertText() {
        return getTextAlert();
    }

    public TimingAlertsPage acceptAlerts() {
        acceptAlert();
        return this;
    }
}
