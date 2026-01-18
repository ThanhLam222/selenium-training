package POM.Exercise1_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LoggedInSuccessPage extends BasePage {
    private By successTitle = By.cssSelector("h1.post-title");
    private By successDescription = By.cssSelector("div.post-content p.has-text-align-center");

    public LoggedInSuccessPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        return driver.getCurrentUrl().contains(getConfig("login.success.url"));
    }

    public String getSuccessTitle() {
        return driver.findElement(successTitle).getText();
    }

    public String getSuccessDescription() {
        return driver.findElement(successDescription).getText();
    }
}
