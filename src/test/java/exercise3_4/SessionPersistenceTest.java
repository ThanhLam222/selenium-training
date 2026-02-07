package exercise3_4;

import base.BaseTest;
import core.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise3_4.LoginPage;
import pom.exercise3_4.ProfilePage;
import utils.ConfigReader;
import utils.CookieUtils;

import java.io.IOException;

public class SessionPersistenceTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise3_4";
    }

    @Test
    public void sessionPersistenceWithCookiesTest() throws IOException, ClassNotFoundException {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        // Login
        ProfilePage profilePage = new LoginPage(driver).navigateToLoginPage()
                .login(username, password);
        Assert.assertTrue(profilePage.isLoggedIn());

        // Save cookies to file
        CookieUtils.saveCookiesToFile(driver, "target/cookies/demoqa.cookies");

        // Close browser
        driver.quit();

        // Open new browser
        DriverManager.quitDriver();
        driver = DriverManager.initDriver();

        // Navigate to login page
        new LoginPage(driver).navigateToLoginPage();

        // Load cookies
        CookieUtils.loadCookiesFromFile(driver, "target/cookies/demoqa.cookies");
        driver.navigate().refresh();
        driver.get("https://demoqa.com/profile");
        Assert.assertTrue(new ProfilePage(driver).isLoggedIn(),
                "User should stay logged in after restoring cookies");
    }
}
