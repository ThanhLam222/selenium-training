package exercise5_1;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise5_1.pages.DropdownPage;
import utils.ConfigReader;

public class DropdownTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise5_1";
    }

    @Test
    public void selectByVisibleTextTest() {
        String visibleText = ConfigReader.getProperty("text");

        String selectedOpt = new DropdownPage(driver).navigateToDropdownPage()
                .selectOptByVisibleText(visibleText)
                .getSelectedOptText();
        Assert.assertEquals(selectedOpt, visibleText);
    }

    @Test
    public void selectByValueTest() {
        String text = ConfigReader.getProperty("text");

        String selectedOpt = new DropdownPage(driver).navigateToDropdownPage()
                .selectOptByValue(ConfigReader.getProperty("value"))
                .getSelectedOptText();
        Assert.assertEquals(selectedOpt, text);
    }

    @Test
    public void selectByIndexTest() {
        String text = ConfigReader.getProperty("text");

        String selectedOpt = new DropdownPage(driver).navigateToDropdownPage()
                .selectOptByIndex(Integer.parseInt(ConfigReader.getProperty("index")))
                .getSelectedOptText();
        Assert.assertEquals(selectedOpt, text);
    }
}
