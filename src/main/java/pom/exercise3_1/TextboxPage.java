package pom.exercise3_1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class TextboxPage extends BasePage {
    private By userName = By.id("userName");
    private By submitBtn = By.id("submit");
    private By output = By.id("output");
    private By nameOutput = By.xpath(".//p[@id = 'name']");
    private By emailOutput = By.xpath(".//p[@id = 'email']");
    private By currentAddressOutput = By.xpath(".//p[@id = 'currentAddress']");
    private By permanentAddressOutput = By.xpath(".//p[@id = 'permanentAddress']");

    public TextboxPage(WebDriver driver) {
        super(driver);
    }

    public TextboxPage navigateToTextboxPage() {
        driver.get(ConfigReader.getProperty("url") + ConfigReader.getProperty("textbox.url"));
        return this;
    }

    public TextboxPage fillForm(String fullName, String email, String currentAddress) {
        actions.click(findElement(userName))
                .keyDown(Keys.SHIFT)
                .sendKeys(fullName)
                .keyUp(Keys.SHIFT)
                .sendKeys(Keys.TAB)
                .sendKeys(email)
                .sendKeys(Keys.TAB)
                .sendKeys(currentAddress)
                .keyDown(Keys.CONTROL).sendKeys("a").sendKeys("c").keyUp(Keys.CONTROL)
                .sendKeys(Keys.TAB)
                .keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL)
                .perform();
        safeClick(submitBtn);
        return this;
    }

    public String getNameOutput() {
        return getText(findElementByParent(output, nameOutput));
    }

    public String getEmailOutput() {
        return getText(findElementByParent(output, emailOutput));
    }

    public String getCurrentAddressOutput() {
        return getText(findElementByParent(output, currentAddressOutput));
    }

    public String getPermanentAddressOutput() {
        return getText(findElementByParent(output, permanentAddressOutput));
    }
}
