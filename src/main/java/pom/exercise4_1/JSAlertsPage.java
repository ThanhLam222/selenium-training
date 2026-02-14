package pom.exercise4_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JSAlertsPage extends BasePage {
    private final By jsAlertBtn = By.xpath("//button[contains(text(), 'Click for JS Alert')]");
    private final By jsConfirmBtn = By.xpath("//button[contains(text(), 'Click for JS Confirm')]");
    private final By jsPromptBtn = By.xpath("//button[contains(text(), 'Click for JS Prompt')]");
    private final By result = By.id("result");

    public JSAlertsPage(WebDriver driver) {
        super(driver);
    }

    public JSAlertsPage navigateToJSAlertPage() {
        driver.get(getConfig("url") + getConfig("js.url"));
        return this;
    }

    public JSAlertsPage clickJSAlertBtn() {
        click(jsAlertBtn);
        return this;
    }

    public JSAlertsPage clickJSConfirmBtn() {
        click(jsConfirmBtn);
        return this;
    }

    public JSAlertsPage sendKeyToJSPromptBtn(String text) {
        click(jsPromptBtn);
        sendKeysToAlert(text);
        return this;
    }

    public JSAlertsPage acceptAlerts() {
        acceptAlert();
        return this;
    }

    public JSAlertsPage dismissAlerts() {
        dismissAlert();
        return this;
    }

    public String getAlertsText() {
        return getTextAlert();
    }

    public String getResultText() {
        return getText(result);
    }
}
