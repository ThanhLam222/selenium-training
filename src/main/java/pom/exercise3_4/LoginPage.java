package pom.exercise3_4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v141.profiler.model.Profile;

public class LoginPage extends BasePage {
    private final By userNameInp = By.id("userName");
    private final By passwordInp = By.id("password");
    private final By loginBtn = By.id("login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateToLoginPage() {
        driver.get(getConfig("url") + getConfig("login.url"));
        return this;
    }

    public ProfilePage login(String username, String password) {
        sendKeys(userNameInp, username);
        sendKeys(passwordInp, password);
        click(loginBtn);
        return new ProfilePage(driver);
    }
}
