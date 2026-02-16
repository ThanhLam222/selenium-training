package exercise4_3;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise4_3.AddRemovePage;
import pom.exercise4_3.CheckboxPage;
import pom.exercise4_3.HomePage;
import pom.exercise4_3.LoginPage;
import utils.ConfigReader;

public class LocatorsTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise4_3";
    }

    @Test(priority = 1)
    public void XpathWithNormalizeSpaceTest() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getCheckBoxItemText(),
                ConfigReader.getProperty("home.checkboxtext")
        );
    }

    @Test(priority = 2)
    public void XpathWithParentTest() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getLiOfCheckBoxItemText(),
                ConfigReader.getProperty("home.li")
        );
    }

    @Test(priority = 3)
    public void XpathWithAncestorTest() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getClassOfDivOfItems(),
                ConfigReader.getProperty("home.div.class")
        );
    }

    @Test(priority = 4)
    public void CSSChildCombinatorTest() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getNumberOfItems(),
                Integer.parseInt(ConfigReader.getProperty("home.itemsize"))
        );
    }

    @Test(priority = 5)
    public void XpathWithPositionTest() {
        boolean isSelected = new CheckboxPage(driver).navigateToCheckBoxPage()
                .clickCheckbox1()
                .isSelected();
        Assert.assertTrue(isSelected);
    }

    @Test(priority = 6)
    public void XpathWithTextTest() {
        boolean isDisplayed = new AddRemovePage(driver).navigateToAddRemovePage()
                .isAddElementBtnDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 7)
    public void XpathWithScopedTest() {
        boolean isDisplayed = new AddRemovePage(driver).navigateToAddRemovePage()
                .clickAddElementBtn()
                .isDeleteBtnDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 8)
    public void OthersXpathAndCSSTest() {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        String errorMessage = new LoginPage(driver).navigateToLoginPage()
                .LoginWithInvalidPassword(username, password)
                .getErrorMessage();
        Assert.assertTrue(errorMessage.contains(ConfigReader.getProperty("error")));
    }
}
