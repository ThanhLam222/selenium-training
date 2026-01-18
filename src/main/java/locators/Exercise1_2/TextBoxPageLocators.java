package locators.Exercise1_2;

import org.openqa.selenium.By;

public class TextBoxPageLocators {
    // Full name field on textbox page - Strategies: cssSelector
    public static final By FULL_NAME_FIELD = By.cssSelector("input[placeholder='Full Name']");

    // Submit button on textbox page - Strategies: className
    public static final By SUBMIT_BUTTON = By.className("btn-primary");
}
