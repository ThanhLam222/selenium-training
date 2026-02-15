package pom.exercise4_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InternetPage extends BasePage {
    private final By clickHereBtn = By.xpath("//a[@href = '/windows/new']");
    private final By header = By.tagName("h3");

    public InternetPage(WebDriver driver) {
        super(driver);
    }

    public InternetPage navigateToInternetPage() {
        driver.get(getConfig("url") + getConfig("window.url"));
        return this;
    }

    public InternetPage clickClickHereBtn() {
        click(clickHereBtn);
        return this;
    }

    public String getHeader() {
        return getText(header);
    }
}
