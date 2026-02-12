package exercise3_2;

import base.BaseTest;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.exercise3_2.SelectMenuPage;
import utils.ConfigReader;

public class SelectMenuTest extends BaseTest {
    private SelectMenuPage selectMenuPage;

    @Override
    protected String getExerciseName() {
        return "exercise3_2";
    }

    @BeforeMethod
    public void goToSelectMenuPage() {
        selectMenuPage = new SelectMenuPage(driver).navigateToSelectMenuPage();
    }

    @Test
    public void singleSelectDropdownTest() {
        String visibleText = ConfigReader.getProperty("single.text");
        String value = ConfigReader.getProperty("single.value");
        String index = ConfigReader.getProperty("single.index");

        Select singleSelect = selectMenuPage.getSingleSelect();

        // Verify select by visible text successfully
        String actualTextByVisibleText = selectMenuPage.selectByVisibleText(singleSelect, visibleText)
                .getFirstSelectedOptText(singleSelect);
        Assert.assertEquals(actualTextByVisibleText, ConfigReader.getProperty("expected.text"));

        // Verify select by value successfully
        String actualTextByValue = selectMenuPage.selectByValue(singleSelect, value)
                .getFirstSelectedOptText(singleSelect);
        Assert.assertEquals(actualTextByValue, ConfigReader.getProperty("expected.value"));

        // Verify select by index successfully
        String actualTextByIndex = selectMenuPage.selectByIndex(singleSelect, index)
                .getFirstSelectedOptText(singleSelect);
        Assert.assertEquals(actualTextByIndex, ConfigReader.getProperty("expected.index"));
    }

    @Test
    public void multipleDropdownTest() {
        String visibleText = ConfigReader.getProperty("multiple.text");
        String value = ConfigReader.getProperty("multiple.value");
        String index = ConfigReader.getProperty("multiple.index");
        int expectedSizeAfterDelOne = Integer.parseInt(ConfigReader.getProperty("size.deleteOne"));
        int expectedSizeAfterDelAll = Integer.parseInt(ConfigReader.getProperty("size.deleteAll"));
        Select multipleSelect = selectMenuPage.getMultipleSelect();

        Assert.assertTrue(multipleSelect.isMultiple(), "Should be multiple select.");

        // Verify select by all methods and deselect by visible text successfully
        int actualSizeByVisibleText = selectMenuPage.selectByAllMethods(multipleSelect,visibleText, value, index)
                .deselectByVisibleText(multipleSelect, visibleText)
                .getNumberOfSelectedOpt(multipleSelect);
        Assert.assertEquals(actualSizeByVisibleText, expectedSizeAfterDelOne);

        // Verify select by all methods and deselect by value successfully
        int actualSizeByValue = selectMenuPage.selectByAllMethods(multipleSelect,visibleText, value, index)
                .deselectByValue(multipleSelect, value)
                .getNumberOfSelectedOpt(multipleSelect);
        Assert.assertEquals(actualSizeByValue, expectedSizeAfterDelOne);

        // Verify select by all methods and deselect by value successfully
        int actualSizeByIndex = selectMenuPage.selectByAllMethods(multipleSelect,visibleText, value, index)
                .deselectByIndex(multipleSelect, index)
                .getNumberOfSelectedOpt(multipleSelect);
        Assert.assertEquals(actualSizeByIndex, expectedSizeAfterDelOne);

        // Verify select by all methods and deselect all successfully
        int actualSizeDelAll = selectMenuPage.selectByAllMethods(multipleSelect,visibleText, value, index)
                .deselectAll(multipleSelect)
                .getNumberOfSelectedOpt(multipleSelect);
        Assert.assertEquals(actualSizeDelAll, expectedSizeAfterDelAll);
    }
}
