package pom.exercise3_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DragAndDropPage extends BasePage {
    private By drag = By.id("draggable");
    private By drop = By.id("droppable");
    private By dropMessage = By.xpath(".//p");

    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }

    public DragAndDropPage navigateToDragAndDropPage() {
        driver.get(getConfig("url") + getConfig("droppable.url"));
        return this;
    }

    public DragAndDropPage dragAndDrop() {
        WebElement dragElement = findElement(drag);
        WebElement dropElement = findElement(drop);

        actions.clickAndHold(dragElement)
                .moveToElement(dropElement)
                .release()
                .perform();
        return this;
    }

    public String getDropMessage() {
        WebElement message = findElementByParent(drop, dropMessage);
        return getText(message);
    }
}
