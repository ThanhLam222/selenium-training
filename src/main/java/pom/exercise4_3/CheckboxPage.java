package pom.exercise4_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckboxPage extends BasePage {
    private final By checkbox1 = By.xpath("(//input[@type = 'checkbox'])[1]");

    public CheckboxPage(WebDriver driver) {
        super(driver);
    }

    public CheckboxPage navigateToCheckBoxPage() {
        driver.get(getConfig("url") + getConfig("checkbox.url"));
        return this;
    }

    public CheckboxPage clickCheckbox1() {
        click(checkbox1);
        return this;
    }

    public boolean isSelected() {
        return findElement(checkbox1).isSelected();
    }
}
