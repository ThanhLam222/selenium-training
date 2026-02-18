package pom.exercise5_1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pom.exercise5_1.base.BasePage;
import pom.exercise5_1.builders.LoginData;

public class LoginPage extends BasePage {
    private final By usernameInp = By.id("username");
    private final By passwordInp = By.id("password");
    private final By loginBtn = By.cssSelector("button[type = 'submit']");
    private final By logoutMessage = By.cssSelector(".flash.success");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateToLoginPage() {
        driver.get(getConfig("url") + getConfig("login.url"));
        return this;
    }

    public LoginPage enterUsername(String username) {
        sendKeys(usernameInp,username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordInp, password);
        return this;
    }

    public LoginPage loginWith(LoginData data) {
        return enterUsername(data.getUsername())
                .enterPassword(data.getPassword());
    }

    public SecureAreaPage clickSubmit() {
        click(loginBtn);
        return new SecureAreaPage(driver);
    }

    public SecureAreaPage login(LoginData data) {
        return navigateToLoginPage()
                .loginWith(data)
                .clickSubmit();
    }

    public boolean isLogoutMessageDisplayed() {
        return isDisplayed(logoutMessage);
    }

    public String getLogoutMessageText() {
        return getText(logoutMessage);
    }
}
