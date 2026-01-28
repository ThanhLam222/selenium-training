package pom.exercise1_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestLoginPage extends BasePage {
    // Locators
    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By submitBtn = By.id("submit");
    private By errorMessage = By.id("error");
    private NavBar navBar;

    public TestLoginPage(WebDriver driver) {
        super(driver);
        navBar = new NavBar(driver);

    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isErrorMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).isDisplayed();
    }

    public void navigateToLoginPage() {
        driver.get(getConfig("url") + getConfig("login.url"));
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickSubmit() {
        driver.findElement(submitBtn).click();
    }

    public LoggedInSuccessPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
        return new LoggedInSuccessPage(driver);
    }
}
