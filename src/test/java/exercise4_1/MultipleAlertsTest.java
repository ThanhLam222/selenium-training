package exercise4_1;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise4_1.MultipleAlertsPage;
import utils.ConfigReader;

public class MultipleAlertsTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise4_1";
    }

    @Test
    public void verifyHandleMultipleAlertCorrectly() {
        MultipleAlertsPage multipleAlertsPage = new MultipleAlertsPage(driver)
                .navigateToMultipleAlertsPage();

        String text1 = ConfigReader.getProperty("text1");
        String text2 = ConfigReader.getProperty("text2");

        // Handle two chained prompts
        String resultPrompt1 = multipleAlertsPage.sendKeyToTwoAlert(text1, text2)
                .getResultOfPrompt1();
        Assert.assertEquals(resultPrompt1, text1);
        String resultPrompt2 = multipleAlertsPage.getResultOfPrompt2();
        Assert.assertEquals(resultPrompt2, text2);
    }
}
