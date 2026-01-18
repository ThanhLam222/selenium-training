package locators.Exercise1_2;

import org.openqa.selenium.By;

public class ElementsMenuLocators {
    // This class includes: Element on menu list (left - panel)

    // Text box in menu list - Strategies: id
    public static final By TEXT_BOX_ITEM = By.id("item-0");

    // Check box item in menu list - Strategies: cssSelector
    public static final By CHECK_BOX_ITEM = By.cssSelector("ul.menu-list li:nth-child(2)");

    // Left panel - name (custom attribute)
    public static final By LEFT_PANEL = By.className("left-pannel");

    // Menu list - cssSelector
    public static final By MENU_LIST = By.cssSelector("ul.menu-list");

    // All menu items - tagName
    public static final By ALL_MENU_ITEMS = By.tagName("li");

    // Active menu item - cssSelector
    public static final By ACTIVE_MENU_ITEM = By.cssSelector("li.active");

    // Dynamic XPath
    public static By menuByText(String name) {
        return By.xpath("//span[normalize-space()='" + name + "']");
    }
}
