package pom.exercise1_3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SuccessPage extends BasePage {

    @FindBy(css = "h1.post-title")
    private WebElement successTitle;

    @FindBy(css = "div.post-content p.has-text-align-center")
    private WebElement successDescription;

    public SuccessPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        return driver.getCurrentUrl().contains(getConfig("login.success.url"));
    }

    public String getSuccessTitle() {
        return getText(successTitle);
    }

    public String getSuccessDescription() {
        return getText(successDescription);
    }

    public boolean isLoggedIn() {
        return isElementVisible(successTitle)
                && isElementVisible(successDescription);
    }
}
