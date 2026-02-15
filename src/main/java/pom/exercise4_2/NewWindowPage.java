package pom.exercise4_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewWindowPage extends BasePage {
    private final By header = By.tagName("h3");

    public NewWindowPage(WebDriver driver) {
        super(driver);
    }

    public String getHeader() {
        return getText(header);
    }
}
