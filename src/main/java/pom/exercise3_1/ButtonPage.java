package pom.exercise3_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ButtonPage extends BasePage {
    private By doubleClickBtn = By.id("doubleClickBtn");
    private By rightClickBtn = By.id("rightClickBtn");
    private By dynamicBtn = By.xpath("//button[text() = 'Click Me']");
    private By doubleClickMessage = By.id("doubleClickMessage");
    private By rightClickMessage = By.id("rightClickMessage");
    private By dynamicClickMessage = By.id("dynamicClickMessage");

    public ButtonPage(WebDriver driver) {
        super(driver);
    }

    public ButtonPage navigateToButtonPage() {
        driver.get(getConfig("url") + getConfig("button.url"));
        return this;
    }

    public ButtonPage doubleClick() {
        actions.doubleClick(findElement(doubleClickBtn)).perform();
        return this;
    }

    public ButtonPage rightClick() {
        actions.contextClick(findElement(rightClickBtn)).perform();
        return this;
    }

    public ButtonPage dynamicClick() {
        waitForClickable(dynamicBtn);
        WebElement element = findElement(dynamicBtn);
        actions.moveToElement(element).click().perform();
        return this;
    }

    public String getDoubleClickMessage() {
        return getText(doubleClickMessage);
    }

    public String getRightClickMessage() {
        return getText(rightClickMessage);
    }

    public String getDynamicMessage() {
        return getText(dynamicClickMessage);
    }
}
