package pom.exercise5_1.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;

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
        waitForVisible(locator);
        return driver.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        waitForVisible(locator);
        return driver.findElements(locator);
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

    protected String getAttribute(By locator, String attribute) {
        return findElement(locator).getAttribute(attribute);
    }

    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected Select selectDropDown(By locator) {
        WebElement element = waitForVisible(locator);
        return new Select(element);
    }

    protected void selectByVisibleText(By locator, String text) {
        selectDropDown(locator).selectByVisibleText(text);
    }

    protected void selectByValue(By locator, String value) {
        selectDropDown(locator).selectByValue(value);
    }

    protected void selectByIndex(By locator, int index) {
        selectDropDown(locator).selectByIndex(index);
    }
}
