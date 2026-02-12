package exercise3_1;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.exercise3_1.ButtonPage;
import utils.ConfigReader;

public class ClickActionsTest extends BaseTest {
    ButtonPage buttonPage;

    @Override
    protected String getExerciseName() {
        return "exercise3_1";
    }

    @BeforeMethod
    public void navigateToButtonPage() {
        buttonPage = new ButtonPage(driver);
        buttonPage.navigateToButtonPage();
    }

    @Test
    public void verifyDynamicClickSuccess() {
        String message = buttonPage.dynamicClick()
                .getDynamicMessage();
        Assert.assertEquals(message, ConfigReader.getProperty("dynamic.message"));
    }

    @Test
    public void verifyRightClickSuccess() {
        String message = buttonPage.rightClick()
                .getRightClickMessage();
        Assert.assertEquals(message, ConfigReader.getProperty("right.message"));
    }

    @Test
    public void verifyDoubleClickSuccessfully() {
        String message = buttonPage.doubleClick()
                .getDoubleClickMessage();
        Assert.assertEquals(message, ConfigReader.getProperty("double.message"));
    }
}
