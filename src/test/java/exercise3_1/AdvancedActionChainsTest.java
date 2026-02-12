package exercise3_1;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise3_1.TextboxPage;
import utils.ConfigReader;

public class AdvancedActionChainsTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise3_1";
    }

    @Test
    public void verifyAdvancedActionChainsSuccessfully() {
        TextboxPage textboxPage = new TextboxPage(driver).navigateToTextboxPage();
        String fullName = ConfigReader.getProperty("fullName");
        String email = ConfigReader.getProperty("email");
        String address = ConfigReader.getProperty("address");
        textboxPage.fillForm(fullName, email,address);

        Assert.assertTrue(textboxPage.getNameOutput().contains(fullName.toUpperCase()));
        Assert.assertTrue(textboxPage.getEmailOutput().contains(email));
        Assert.assertTrue(textboxPage.getCurrentAddressOutput().contains(address));
        Assert.assertTrue(textboxPage.getPermanentAddressOutput().contains(address));
    }
}
