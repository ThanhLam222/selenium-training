package pom.exercise3_3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

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
        findElement(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        waitForVisible(locator);
        return findElement(locator).getText();
    }

    protected String getText(WebElement element) {
        waitForVisible(element);
        return element.getText();
    }
}