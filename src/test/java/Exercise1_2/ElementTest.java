package Exercise1_2;

import core.DriverManager;
import locators.ElementLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ElementTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverManager.initDriver();
    }

    /**
     * TEST 1: Verify all BASIC locators
     */
    @Test(priority = 1)
    public void testBasicLocators() {
        // 1. TEXT_BOX_ITEM (id)
        driver.findElement(ElementLocators.TEXT_BOX_ITEM).click();

        // 2. TEXT_BOX_TITLE (tagName)
        Assert.assertEquals(
                driver.findElement(ElementLocators.TEXT_BOX_TITLE).getText(),
                    "Text Box"
        );

        // 3. FULL_NAME_FIELD (cssSelector)
        driver.findElement(ElementLocators.FULL_NAME_FIELD).sendKeys("Automation Tester");

        // 4. SUBMIT_BUTTON (className)
        Assert.assertTrue(
                driver.findElement(ElementLocators.SUBMIT_BUTTON).isDisplayed()
        );

        // 5. CHECK_BOX_ITEM (cssSelector)
        driver.findElement(ElementLocators.CHECK_BOX_ITEM).click();

        // 6. TOGGLE_BUTTON (ByChained)
        driver.findElement(ElementLocators.TOGGLE_BUTTON).click();

        // 7. LEFT_PANEL (className)
        Assert.assertTrue(
                driver.findElement(ElementLocators.LEFT_PANEL).isDisplayed()
        );

        // 8. MENU_LIST (cssSelector)
        Assert.assertTrue(
                driver.findElement(ElementLocators.MENU_LIST).isDisplayed()
        );

        // 9. ALL_MENU_ITEMS (tagName)
        Assert.assertTrue(
                driver.findElements(ElementLocators.ALL_MENU_ITEMS).size() > 0
        );

        // 10. ACTIVE_MENU_ITEM (cssSelector)
        Assert.assertTrue(
                driver.findElement(ElementLocators.ACTIVE_MENU_ITEM)
                            .getText().contains("Check Box")
        );
    }

        /**
         * TEST 2: Verify ADVANCED XPath locators
         */
        @Test(priority = 2)
        public void testAdvancedXPathLocators() {

            // 1. contains(text())
            Assert.assertTrue(
                    driver.findElement(ElementLocators.CHECK_BOX_CONTAINS)
                            .isDisplayed()
            );

            // 2. text()
            Assert.assertEquals(
                    driver.findElement(ElementLocators.CHECK_BOX_TEXT).getText(),
                    "Check Box"
            );

            // 3. normalize-space()
            Assert.assertTrue(
                    driver.findElement(ElementLocators.ELEMENTS_NORMALIZE)
                            .isDisplayed()
            );

            // 4. Dynamic XPath
            driver.findElement(
                    ElementLocators.menuByText("Radio Button")
            ).click();

            Assert.assertEquals(
                    driver.findElement(ElementLocators.TEXT_BOX_TITLE).getText(),
                    "Radio Button"
            );
        }

        @AfterClass
        public void tearDown() {
            DriverManager.quitDriver();
        }
    }

