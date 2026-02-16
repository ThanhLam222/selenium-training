package pom.exercise4_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By checkboxItem = By.xpath("//a[normalize-space()='Checkboxes']");
    private final By items = By.cssSelector("ul > li");
    private final By liOfCheckBoxItem = By.xpath("//a[normalize-space() = 'Checkboxes']/parent::li");
    private final By divOfCheckBoxItem = By.xpath("//a[normalize-space() = 'Checkboxes']/ancestor::div");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getCheckBoxItemText() {
        return getText(checkboxItem).trim();
    }

    public String getLiOfCheckBoxItemText() {
        return getText(liOfCheckBoxItem).trim();
    }

    public int getNumberOfItems() {
        return getSize(items);
    }

    public String getClassOfDivOfItems() {
        return getAttribute(divOfCheckBoxItem, "class");
    }
}
