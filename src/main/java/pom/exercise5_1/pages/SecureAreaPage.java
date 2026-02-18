package pom.exercise5_1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BaseAuthenticatedPage {
    private final By successMessage = By.cssSelector(".flash.success");

    public SecureAreaPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}
