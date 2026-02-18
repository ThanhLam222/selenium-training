package exercise5_1;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise5_1.builders.LoginBuilder;
import pom.exercise5_1.builders.LoginData;
import pom.exercise5_1.pages.LoginPage;
import pom.exercise5_1.pages.SecureAreaPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise5_1";
    }

    @Test(priority = 1)
    public void loginSuccessWithFluentAPITest() {
        String username = ConfigReader.getProperty("username");
        String password =  ConfigReader.getProperty("password");

        SecureAreaPage secureAreaPage = new LoginPage(driver).navigateToLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickSubmit();

        Assert.assertTrue(secureAreaPage.isSuccessMessageDisplayed());
        Assert.assertTrue(secureAreaPage.getSuccessMessage()
                .contains(ConfigReader.getProperty("success"))
        );
    }

    @Test(priority = 2)
    public void loginSuccessWithBuilder() {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        LoginData data = new LoginBuilder()
                .withUsername(username)
                .withPassword(password)
                .build();

        SecureAreaPage secureAreaPage = new LoginPage(driver).login(data);

        Assert.assertTrue(secureAreaPage.isSuccessMessageDisplayed());
        Assert.assertTrue(secureAreaPage.getSuccessMessage()
                .contains(ConfigReader.getProperty("success"))
        );
    }

    @Test(priority = 3)
    public void logoutSuccessfullyAfterLoginTest() {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        LoginData data = new LoginBuilder()
                .withUsername(username)
                .withPassword(password)
                .build();

        LoginPage loginPage = new LoginPage(driver).login(data)
                .logout();
        Assert.assertTrue(loginPage.isLogoutMessageDisplayed());
        Assert.assertTrue(loginPage.getLogoutMessageText()
                .contains(ConfigReader.getProperty("message"))
        );
    }
}
