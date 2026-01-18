package locators.Exercise1_2;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

public class CheckBoxPageLocators {
    // Toggle button on checkbox page - Strategies: By Chained
    public static final By TOGGLE_BUTTON = new ByChained(By.tagName("ol"), By.className("rct-collapse-btn"));

    // Advanced: Xpath
    // 1. contains(text())
    public static final By CHECK_BOX_CONTAINS = By.xpath("//span[contains(text(),'Check')]");

    // 2. text()
    public static final By CHECK_BOX_TEXT = By.xpath("//span[text()='Check Box']");
}
