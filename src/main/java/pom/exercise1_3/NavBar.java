package pom.exercise1_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavBar {

    private WebDriver driver;

    public NavBar(WebDriver driver) {
        this.driver = driver;
    }

    // Link on navbar
    private By getNavLink(String endPoint) {
        return By.xpath("//nav//a[contains(@href,'" + endPoint + "')]");
    }

    public void clickNavBarLink(String endPoint) {
        driver.findElement(getNavLink(endPoint)).click();
    }
}
