package pom.exercise4_2;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;
import java.util.Set;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    protected String getConfig(String key) {
        return ConfigReader.getProperty(key);
    }

    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void scrollToCenter(WebElement element) {
        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );
    }

    protected void click(By locator) {
        WebElement element = waitForClickable(locator);
        scrollToCenter(element);
        element.click();
    }

    protected void sendKeys(By locator, String text) {
        WebElement element = findElement(locator);
        waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        waitForVisible(locator);
        return findElement(locator).getText();
    }

    protected String getText(WebElement element) {
        waitForVisible(element);
        return element.getText();
    }

    public void waitForExpectedWindowSize(int expectedSize) {
        wait.until(driver -> getNumberOfWindow() == expectedSize);
    }

    public String waitAndGetNewWindow(Set<String> oldWindows) {
        wait.until(driver -> driver.getWindowHandles().size() > oldWindows.size());

        Set<String> newWindows = driver.getWindowHandles();
        newWindows.removeAll(oldWindows);

        return newWindows.iterator().next();
    }

    public String getCurrentWindow() {
        return driver.getWindowHandle();
    }

    public Set<String> getAllWindow() {
        return driver.getWindowHandles();
    }

    public int getNumberOfWindow() {
        return driver.getWindowHandles().size();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void switchToNewWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public void switchToMainWindow(String mainWindow) {
        driver.switchTo().window(mainWindow);
    }

    public void closeNewWindow() {
        driver.close();
    }

    protected void switchToFrame(int index) {
        driver.switchTo().frame(index);
    }

    protected void switchToFrame(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    protected void switchToFrame(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
