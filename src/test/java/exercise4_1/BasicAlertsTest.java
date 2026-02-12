package exercise4_1;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.exercise4_1.JSAlertsPage;
import utils.ConfigReader;

public class BasicAlertsTest extends BaseTest {
    private JSAlertsPage jsAlertsPage;

    @Override
    protected String getExerciseName() {
        return "exercise4_1";
    }

    @BeforeMethod
    public void goToAlertPage() {
        jsAlertsPage = new JSAlertsPage(driver).navigateToJSAlertPage();
    }

    @Test
    public void verifyJSAlertWorkCorrectly() {
        String alertText = jsAlertsPage.clickJSAlertBtn().getAlertsText();
        Assert.assertTrue(alertText.contains(ConfigReader.getProperty("expected.alert.text")));

        String result = jsAlertsPage.acceptAlerts().getResultText();
        Assert.assertEquals(result, ConfigReader.getProperty("expected.alert.result"));
    }

    @Test
    public void verifyJSConfirmWorkCorrectly() {
        String confirmText = jsAlertsPage.clickJSConfirmBtn().getAlertsText();
        Assert.assertTrue(confirmText.contains(ConfigReader.getProperty("expected.confirm.text")));

        // Accept confirm
        String acceptResultText = jsAlertsPage.acceptAlerts().getResultText();
        Assert.assertEquals(acceptResultText, ConfigReader.getProperty("expected.confirm.accept"));

        // Dismiss confirm
        String dismissResultText = jsAlertsPage.clickJSConfirmBtn()
                .dismissAlerts()
                .getResultText();
        Assert.assertEquals(dismissResultText, ConfigReader.getProperty("expected.confirm.dismiss"));
    }

    @Test
    public void verifyJSPromptWorkCorrectly() {
        String inputText = ConfigReader.getProperty("text");

        String promptText = jsAlertsPage.sendKeyToJSPromptBtn(inputText)
                .getAlertsText();
        Assert.assertTrue(promptText.contains(ConfigReader.getProperty("expected.prompt.text")));

        // Accept Prompt
        String acceptResultText = jsAlertsPage.acceptAlerts().getResultText();
        Assert.assertEquals(acceptResultText,
                ConfigReader.getProperty("expected.prompt.result") + " "
                        + inputText
        );

        // Dismiss Prompt
        String dismissResultText = jsAlertsPage.sendKeyToJSPromptBtn(inputText)
                .dismissAlerts()
                .getResultText();
        Assert.assertEquals(dismissResultText,
                ConfigReader.getProperty("expected.prompt.result") + " "
                        + ConfigReader.getProperty("expected.prompt.dismiss"));
    }
}
