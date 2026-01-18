package POM.Exercise1_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PracticePage extends BasePage {
    private NavBar navBar;
    private By getTestLinkItem(String endPoint) {
        return By.xpath("//article//a[contains(@href,'" + endPoint + "')]");
    }

    public PracticePage(WebDriver driver) {
        super(driver);
        navBar = new NavBar(driver);
    }

    public void navigateToPraticePage() {
        navBar.clickNavBarLink(getConfig("practice.url"));
    }

    public void clickTestLink(String endPoint) {
        driver.findElement(getTestLinkItem(endPoint)).click();
    }
}
