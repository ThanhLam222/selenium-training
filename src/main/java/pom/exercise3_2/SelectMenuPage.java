package pom.exercise3_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectMenuPage extends BasePage {
    private final By oldSelectMenu = By.id("oldSelectMenu");
    private final By standardMultiSelect = By.id("cars");

    public SelectMenuPage(WebDriver driver) {
        super(driver);
    }

    public SelectMenuPage navigateToSelectMenuPage() {
        driver.get(getConfig("url") + getConfig("select.url"));
        return this;
    }

    public SelectMenuPage selectByVisibleText(Select dropdown, String text) {
        dropdown.selectByVisibleText(text);
        return this;
    }

    public SelectMenuPage selectByValue(Select dropdown, String value) {
        dropdown.selectByValue(value);
        return this;
    }

    public SelectMenuPage selectByIndex(Select dropdown, String index) {
        dropdown.selectByIndex(Integer.parseInt(index));
        return this;
    }

    public SelectMenuPage selectByAllMethods(Select dropdown, String text, String value, String index) {
        selectByVisibleText(dropdown, text)
                .selectByValue(dropdown, value)
                .selectByIndex(dropdown, index);
        return this;
    }

    public SelectMenuPage deselectByVisibleText(Select dropdown, String text) {
        dropdown.deselectByVisibleText(text);
        return this;
    }

    public SelectMenuPage deselectByValue(Select dropdown, String value) {
        dropdown.deselectByValue(value);
        return this;
    }

    public SelectMenuPage deselectByIndex(Select dropdown, String index) {
        dropdown.deselectByIndex(Integer.parseInt(index));
        return this;
    }

    public SelectMenuPage deselectAll(Select dropdown) {
        dropdown.deselectAll();
        return this;
    }

    public String getFirstSelectedOptText(Select dropdown) {
        return getText(dropdown.getFirstSelectedOption());
    }

    public int getNumberOfSelectedOpt(Select dropdown) {
        return dropdown.getAllSelectedOptions().size();
    }

    public Select getSingleSelect() {
        return new Select(findElement(oldSelectMenu));
    }

    public Select getMultipleSelect() {
        return new Select(findElement(standardMultiSelect));
    }
}
