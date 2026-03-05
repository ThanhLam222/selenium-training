package exercise5_2;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise5_1.builders.LoginBuilder;
import pom.exercise5_1.builders.LoginData;
import pom.exercise5_1.pages.LoginPage;
import pom.exercise5_1.pages.SecureAreaPage;
import utils.DataProviderUtil;

public class LoginDataDrivenTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise5_1";
    }

    @Test(dataProvider = "csvData", dataProviderClass = DataProviderUtil.class)
    public void loginCSVTest(String username, String password, boolean expected) {
        LoginData data = new LoginBuilder()
                .withUsername(username)
                .withPassword(password)
                .build();

        SecureAreaPage secureAreaPage = new LoginPage(driver).login(data);

        boolean actual = secureAreaPage.isSuccessMessageDisplayed(expected);

        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "excelData", dataProviderClass = DataProviderUtil.class)
    public void loginExcelTest(String username, String password, boolean expected) {
        LoginData data = new LoginBuilder()
                .withUsername(username)
                .withPassword(password)
                .build();

        SecureAreaPage secureAreaPage = new LoginPage(driver).login(data);

        boolean actual = secureAreaPage.isSuccessMessageDisplayed(expected);

        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "jsonData", dataProviderClass = DataProviderUtil.class)
    public void loginJsonTest(String username, String password, boolean expected) {
        LoginData data = new LoginBuilder()
                .withUsername(username)
                .withPassword(password)
                .build();

        SecureAreaPage secureAreaPage = new LoginPage(driver).login(data);

        boolean actual = secureAreaPage.isSuccessMessageDisplayed(expected);

        Assert.assertEquals(actual, expected);
    }
}
