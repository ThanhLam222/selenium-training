package pom.exercise4_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HTMLModalWindowPage extends BasePage {
    private final By modalWindow = By.id("modal");
    private final By closeBtn = By.xpath("//p[text() = 'Close']");

    public HTMLModalWindowPage(WebDriver driver) {
        super(driver);
    }

    public HTMLModalWindowPage navigateToModalWindowPage() {
        driver.get(getConfig("url") + getConfig("modal.url"));
        return this;
    }

    public HTMLModalWindowPage closeModalIfPresent() {
        if (isDisplayed(modalWindow)) {
            click(closeBtn);
        }
        return this;
    }

    public boolean isModalInvisible() {
        return isElementInvisible(modalWindow);
    }
}
