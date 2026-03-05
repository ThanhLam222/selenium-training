package pom.exercise5_1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SecureAreaPage extends BaseAuthenticatedPage {
    private final By successMessage = By.cssSelector(".flash.success");

    public SecureAreaPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSuccessMessageDisplayed(boolean expectSuccess) {
        // If expected success, the test will wait for success message visible to avoid flaky test
        if (expectSuccess) {
            try {
                WebElement msg = waitForVisible(successMessage);
                return msg.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        } else {
            return isDisplayed(successMessage);
        }
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}
