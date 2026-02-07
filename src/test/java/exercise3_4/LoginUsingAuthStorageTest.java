package exercise3_4;

import base.BaseTest;
import core.DriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise3_4.LoginPage;
import pom.exercise3_4.ProfilePage;
import utils.ConfigReader;
import utils.CookieUtils;

public class LoginUsingAuthStorageTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise3_4";
    }

    @Test
    public void verifyLoginUsingAuthCookiesOnly() {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        // 1. Login once to obtain cookies
        new LoginPage(driver)
                .navigateToLoginPage()
                .login(username, password);

        Cookie token = CookieUtils.getCookie(driver, "token");
        Cookie userId = CookieUtils.getCookie(driver,"userID");
        Cookie userName = CookieUtils.getCookie(driver,"userName");
        Cookie expires = CookieUtils.getCookie(driver,"expires");

        // 2. New session
        DriverManager.quitDriver();
        driver = DriverManager.initDriver();

        // 3. Open domain
        driver.get(ConfigReader.getProperty("url"));

        // 4. Inject auth cookies
        CookieUtils.addCookie(driver, token);
        CookieUtils.addCookie(driver, userId);
        CookieUtils.addCookie(driver, userName);
        CookieUtils.addCookie(driver, expires);

        // 5. Refresh to activate session
        driver.navigate().refresh();

        // 6. Go to profile
        driver.get("https://demoqa.com/profile");

        // 7. Verify
        Assert.assertTrue(new ProfilePage(driver).isLoggedIn());
    }
}
