package pom.exercise5_1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pom.exercise5_1.base.BasePage;

public class DropdownPage extends BasePage {
    private final By dropdown = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    public DropdownPage navigateToDropdownPage() {
        driver.get(getConfig("url") + getConfig("dropdown.url"));
        return this;
    }

    public DropdownPage selectOptByVisibleText(String text) {
        selectByVisibleText(dropdown,text);
        return this;
    }

    public DropdownPage selectOptByValue(String value) {
        selectByValue(dropdown,value);
        return this;
    }

    public DropdownPage selectOptByIndex(int index) {
        selectByIndex(dropdown,index);
        return this;
    }

    public String getSelectedOptText() {
        return getText(selectDropDown(dropdown).getFirstSelectedOption());
    }
}
