package pom.exercise5_1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pom.exercise5_1.base.BasePage;

public class BaseAuthenticatedPage extends BasePage {
    private final By logoutBtn = By.cssSelector("a.button.secondary.radius");

    public BaseAuthenticatedPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage logout() {
        click(logoutBtn);
        return new LoginPage(driver);
    }
}
