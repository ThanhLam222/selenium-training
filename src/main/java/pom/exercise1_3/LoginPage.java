package pom.exercise1_3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    // Locators
    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "submit")
    private WebElement submitBtn;

    @FindBy(id = "error")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateToLoginPage() {
        driver.get(getConfig("url") + getConfig("login.url"));
        return this;
    }

    public LoginPage enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public SuccessPage clickSubmit() {
        submitBtn.click();
        return new SuccessPage(driver);
    }

    public SuccessPage login(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickSubmit();
    }

    public boolean isErrorMessageDisplayed() {
        return isElementVisible(errorMessage);
    }

    public String getErrorMessageText() {
        return getText(errorMessage);
    }
}
