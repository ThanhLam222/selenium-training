package exercise4_1;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise4_1.TimingAlertsPage;
import utils.ConfigReader;

public class TimingAlertsTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise4_1";
    }

    @Test
    public void verifyWaitAlertCorrectly() {
        TimingAlertsPage timingAlertsPage = new TimingAlertsPage(driver);
        String alertText = timingAlertsPage.navigateToTimingAlertsPage()
                .clickAlertBtn()
                .getAlertText();
        Assert.assertTrue(alertText.contains(ConfigReader.getProperty("expected.timing.text")));
        timingAlertsPage.acceptAlerts();
    }
}
