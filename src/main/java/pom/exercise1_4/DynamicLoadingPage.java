package pom.exercise1_4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingPage extends BasePage {
    private static final By START_BUTTON = By.cssSelector("#start button");
    private static final By LOADING_BAR = By.id("loading");
    private static final By FINISH_TEXT = By.id("finish");

    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
    }

    // Click "Start"
    public void clickStart() {
        click(START_BUTTON);
    }

    // Wait loading bar disappear - Advanced requirements

    /**
     * Wait until the loading bar disappears before proceeding
     * This explicit wait handles dynamic loading where the element may:
     * 1. Be completely removed from DOM after loading finishes
     * 2. Still exist in DOM but becomes invisible (display: none)
     * Using findElements() avoids NoSuchElementException when the loading bar is removed,
     * while isDisplayed() covers the case where it is hidden.
     *
     */

    public void waitForLoadingDisappear() {
        wait.until(driver ->
                driver.findElements(LOADING_BAR).isEmpty()
                        || !driver.findElement(LOADING_BAR).isDisplayed()
        );
    }

    // Wait success message & get text
    public String getHelloWorldText() {
        return waitVisible(FINISH_TEXT).getText();
    }
}
