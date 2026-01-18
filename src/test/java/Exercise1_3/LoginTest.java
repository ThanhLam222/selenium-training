package Exercise1_3;

import POM.Exercise1_3.LoggedInSuccessPage;
import POM.Exercise1_3.TestLoginPage;
import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.Assert;
import utils.ConfigReader;

public class LoginTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise1_3";
    }

    @Test
    public void successMessageTest() {
        // Login
        TestLoginPage loginPage = new TestLoginPage(driver);

        loginPage.navigateToLoginPage();

        String userName = ConfigReader.getProperty("username");
        String passWord = ConfigReader.getProperty("password");

        LoggedInSuccessPage successPage = loginPage.login(userName, passWord);

        //Verify success message
        // Check redirect to correct URL
        Assert.assertTrue(successPage.isAt());

        // Check success message title
        Assert.assertEquals(successPage.getSuccessTitle(), ConfigReader.getProperty("success.title"));

        // Check success message description
        Assert.assertEquals(successPage.getSuccessDescription(), ConfigReader.getProperty("success.description"));

        // Advanced: handle multi-tabs
        // Save current tab
        String mainWindow = driver.getWindowHandle();

        // Open new tab
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open(arguments[0])", ConfigReader.getProperty("login.url"));

        // Switch to new tab
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // Verify new tab
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigReader.getProperty("login.url")));

        // Close new tab
        driver.close();

        // Switch to original tab
        driver.switchTo().window(mainWindow);

        // Verify still is at Success page
        Assert.assertTrue(successPage.isAt());
    }
}

