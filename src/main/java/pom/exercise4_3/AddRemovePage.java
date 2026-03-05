package pom.exercise4_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddRemovePage extends BasePage {
    private final By addElementBtn = By.xpath("//button[text() = 'Add Element']");
    private final By deleteBtn = By.xpath("//div[@class='example']//button[text()='Delete']");

    public AddRemovePage(WebDriver driver) {
        super(driver);
    }

    public AddRemovePage navigateToAddRemovePage() {
        driver.get(getConfig("url") + getConfig("addremove.url"));
        return this;
    }

    public AddRemovePage clickAddElementBtn() {
        click(addElementBtn);
        return this;
    }

    public boolean isAddElementBtnDisplayed() {
        return isDisplayed(addElementBtn);
    }

    public boolean isDeleteBtnDisplayed() {
        return isDisplayed(deleteBtn);
    }
}
