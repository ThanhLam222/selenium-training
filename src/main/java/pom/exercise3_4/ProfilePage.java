package pom.exercise3_4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {
    private final By logoutBtn = By.id("submit");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoggedIn() {
        waitForVisible(logoutBtn);
        return findElement(logoutBtn).isDisplayed();
    }
}
