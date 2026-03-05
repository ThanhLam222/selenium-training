package pom.exercise4_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    // Xpath with contains()
    private final By usernameInp = By.xpath("//input[contains(@id, 'user')]");
    // Xpath with CSS attribute
    private final By passwordInp = By.cssSelector("input[type = 'password']");
    // Xpath with starts-with and complex xpath
    //div[contains(@class,'flash')
    private final By flashError = By.xpath("//div[starts-with(@class,'flash') and contains(@class,'error')]");
    // CSS class selector
    private final By loginBtn = By.cssSelector(".radius");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateToLoginPage() {
        driver.get(getConfig("url") + getConfig("login.url"));
        return this;
    }

    public LoginPage loginWithInvalidPassword(String username, String password) {
        sendKeys(usernameInp, username);
        sendKeys(passwordInp, password);
        click(loginBtn);
        return this;
    }

    public String getErrorMessage() {
        return getText(flashError);
    }
}
