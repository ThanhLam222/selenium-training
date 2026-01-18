package Exercise1_2;

import base.BaseTest;
import locators.Exercise1_2.CheckBoxPageLocators;
import locators.Exercise1_2.CommonLocators;
import locators.Exercise1_2.TextBoxPageLocators;
import locators.Exercise1_2.ElementsMenuLocators;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigReader;

public class ElementTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise1_2";
    }

    /**
     * TEST 1: Verify all BASIC locators
     */
    @Test(priority = 1)
    public void testBasicLocators() {
        // 1. TEXT_BOX_ITEM (id)
        driver.findElement(ElementsMenuLocators.TEXT_BOX_ITEM).click();

        // 2. TEXT_BOX_TITLE (tagName)
        Assert.assertEquals(
                driver.findElement(CommonLocators.PAGE_TITLE).getText(),
                    ConfigReader.getProperty("textbox.title")
        );

        // 3. FULL_NAME_FIELD (cssSelector)
        driver.findElement(TextBoxPageLocators.FULL_NAME_FIELD)
                .sendKeys(ConfigReader.getProperty("textbox.fullname"));

        // 4. SUBMIT_BUTTON (className)
        Assert.assertTrue(
                driver.findElement(TextBoxPageLocators.SUBMIT_BUTTON).isDisplayed()
        );

        // 5. CHECK_BOX_ITEM (cssSelector)
        driver.findElement(ElementsMenuLocators.CHECK_BOX_ITEM).click();

        // 6. TOGGLE_BUTTON (ByChained)
        driver.findElement(CheckBoxPageLocators.TOGGLE_BUTTON).click();

        // 7. LEFT_PANEL (className)
        Assert.assertTrue(
                driver.findElement(ElementsMenuLocators.LEFT_PANEL).isDisplayed()
        );

        // 8. MENU_LIST (cssSelector)
        Assert.assertTrue(
                driver.findElement(ElementsMenuLocators.MENU_LIST).isDisplayed()
        );

        // 9. ALL_MENU_ITEMS (tagName)
        Assert.assertTrue(
                driver.findElements(ElementsMenuLocators.ALL_MENU_ITEMS).size() > 0
        );

        // 10. ACTIVE_MENU_ITEM (cssSelector)
        Assert.assertTrue(
                driver.findElement(ElementsMenuLocators.ACTIVE_MENU_ITEM).getText()
                        .contains(ConfigReader.getProperty("checkbox.title"))
        );
    }

        /**
         * TEST 2: Verify ADVANCED XPath locators
         */
        @Test(priority = 2)
        public void testAdvancedXPathLocators() {

            // 1. contains(text())
            Assert.assertTrue(
                    driver.findElement(CheckBoxPageLocators.CHECK_BOX_CONTAINS).isDisplayed()
            );

            // 2. text()
            Assert.assertEquals(
                    driver.findElement(CheckBoxPageLocators.CHECK_BOX_TEXT).getText(),
                    ConfigReader.getProperty("menu.checkbox.text")
            );

            // 3. normalize-space()
            Assert.assertTrue(
                    driver.findElement(CommonLocators.ELEMENTS_NORMALIZE).isDisplayed()
            );

            // 4. Dynamic XPath
            driver.findElement(
                    ElementsMenuLocators.menuByText(ConfigReader.getProperty("menu.radio.text"))
            ).click();

            Assert.assertEquals(
                    driver.findElement(CommonLocators.PAGE_TITLE).getText(),
                    ConfigReader.getProperty("radiobutton.title")
            );
        }

    }

