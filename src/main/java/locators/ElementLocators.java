package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

public class ElementLocators {
    // Basic requirements
    // 1. Text box in menu list - Strategies: id
    public static final By TEXT_BOX_ITEM = By.id("item-0");

    // 2. Text box title - Strategies: tagname
    public static final By PAGE_TITLE = By.tagName("h1");

    // 3. Full name field on textbox page - Strategies: cssSelector
    public static final By FULL_NAME_FIELD = By.cssSelector("input[placeholder='Full Name']");

    // 4. Submit button on textbox page - Strategies: className
    public static final By SUBMIT_BUTTON = By.className("btn-primary");

    // 5. Check box item in menu list - Strategies: cssSelector
    public static final By CHECK_BOX_ITEM = By.cssSelector("ul.menu-list li:nth-child(2)");

    // 6. Toggle button on checkbox page - Strategies: By Chained
    public static final By TOGGLE_BUTTON = new ByChained(By.tagName("ol"), By.className("rct-collapse-btn"));

    // 7. Left panel - name (custom attribute)
    public static final By LEFT_PANEL = By.className("left-pannel");

    // 8. Menu list - cssSelector
    public static final By MENU_LIST = By.cssSelector("ul.menu-list");

    // 9. All menu items - tagName
    public static final By ALL_MENU_ITEMS = By.tagName("li");

    // 10. Active menu item - cssSelector
    public static final By ACTIVE_MENU_ITEM = By.cssSelector("li.active");

    // Advanced: Xpath
    // 1. contains(text())
    public static final By CHECK_BOX_CONTAINS = By.xpath("//span[contains(text(),'Check')]");

    // 2. text()
    public static final By CHECK_BOX_TEXT = By.xpath("//span[text()='Check Box']");

    // 3. normalize-space()
    public static final By ELEMENTS_NORMALIZE = By.xpath("//div[normalize-space()='Elements']");

    // 4. Dynamic XPath
    public static By menuByText(String name) {
        return By.xpath("//span[normalize-space()='" + name + "']");
    }
}
