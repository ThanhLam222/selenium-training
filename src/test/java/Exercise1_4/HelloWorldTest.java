package Exercise1_4;


import POM.exercise1_4.DynamicLoadingPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class HelloWorldTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise1_4";
    }

    @Test
    public void verifyHelloWorldMessage() {

        DynamicLoadingPage page = new DynamicLoadingPage(driver);

        page.clickStart();
        page.waitForLoadingDisappear();

        Assert.assertEquals(page.getHelloWorldText(), ConfigReader.getProperty("text"));
    }
}
