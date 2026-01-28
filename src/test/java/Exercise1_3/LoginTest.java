package Exercise1_3;

import pom.exercise1_3.SuccessPage;
import pom.exercise1_3.LoginPage;
import base.BaseTest;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;
import org.testng.Assert;
import utils.ConfigReader;

public class LoginTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise1_3";
    }

    @Test(priority = 1)
    public void successMessageTest() {
        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.navigateToLoginPage();

        String userName = ConfigReader.getProperty("valid.username");
        String passWord = ConfigReader.getProperty("valid.password");

        SuccessPage successPage = loginPage.login(userName, passWord);

        //Verify success message
        // Check redirect to correct URL
        Assert.assertTrue(successPage.isAt());

        // Check user logged in
        Assert.assertTrue(successPage.isLoggedIn());

        // Check success message title
        Assert.assertEquals(successPage.getSuccessTitle(), ConfigReader.getProperty("success.title"));

        // Check success message description
        Assert.assertEquals(successPage.getSuccessDescription(), ConfigReader.getProperty("success.description"));

        // Advanced: handle multi-tabs
        // Save current tab
        String mainWindow = driver.getWindowHandle();

        // Open new tab
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(ConfigReader.getProperty("url") + ConfigReader.getProperty("login.url"));

        // Verify new tab
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigReader.getProperty("login.url")));

        // Close new tab
        driver.close();

        // Switch to original tab
        driver.switchTo().window(mainWindow);

        // Verify still is at Success page
        Assert.assertTrue(successPage.isAt());
    }

    @Test(priority = 2)
    public void invalidUserNameTest() {
        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.navigateToLoginPage();

        String userName = ConfigReader.getProperty("invalid.username");
        String passWord = ConfigReader.getProperty("valid.password");
        String errorMessage = ConfigReader.getProperty("error.username");
        loginPage.login(userName, passWord);

        // Verify message displayed and content of message
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(), errorMessage);
    }

    @Test(priority = 3)
    public void invalidPasswordTest() {
        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.navigateToLoginPage();

        String userName = ConfigReader.getProperty("valid.username");
        String passWord = ConfigReader.getProperty("invalid.password");
        String errorMessage = ConfigReader.getProperty("error.password");
        loginPage.login(userName, passWord);

        // Verify message displayed and content of message
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(), errorMessage);
    }
}

