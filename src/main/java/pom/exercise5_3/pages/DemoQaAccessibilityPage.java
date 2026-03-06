package pom.exercise5_3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pom.exercise5_1.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class DemoQaAccessibilityPage extends BasePage {
    private final By fullNameInput = By.id("userName");
    private final By submitButton = By.id("submit");
    private final By pageHeading = By.tagName("h1");
    private final By formControls = By.cssSelector("form input, form textarea, form select, form button");

    public DemoQaAccessibilityPage(WebDriver driver) {
        super(driver);
    }

    public DemoQaAccessibilityPage navigateToTextBoxPage() {
        driver.get(getConfig("url") + getConfig("textbox.url"));
        waitForVisible(fullNameInput);
        return this;
    }

    public String getFullNameInputAriaLabel() {
        return findElement(fullNameInput).getAttribute("aria-label");
    }

    public String getSubmitButtonRole() {
        return findElement(submitButton).getAttribute("role");
    }

    public WebElement pressTabAndGetFocusedElement() {
        WebElement fullName = findElement(fullNameInput);
        fullName.click();
        new Actions(driver).sendKeys(Keys.TAB).perform();
        return driver.switchTo().activeElement();
    }

    public WebElement getPageHeadingElement() {
        return findElement(pageHeading);
    }

    public List<WebElement> getVisibleFormControls() {
        List<WebElement> visibleControls = new ArrayList<>();
        for (WebElement control : driver.findElements(formControls)) {
            if (control.isDisplayed()) {
                visibleControls.add(control);
            }
        }
        return visibleControls;
    }

    public boolean hasAccessibleName(WebElement element) {
        String ariaLabel = normalize(element.getAttribute("aria-label"));
        if (!ariaLabel.isEmpty()) {
            return true;
        }

        String labelledBy = normalize(element.getAttribute("aria-labelledby"));
        if (!labelledBy.isEmpty()) {
            String[] ids = labelledBy.split("\\s+");
            StringBuilder labelText = new StringBuilder();
            for (String id : ids) {
                List<WebElement> labels = driver.findElements(By.id(id));
                if (!labels.isEmpty()) {
                    labelText.append(labels.getFirst().getText()).append(" ");
                }
            }
            if (!normalize(labelText.toString()).isEmpty()) {
                return true;
            }
        }

        String id = normalize(element.getAttribute("id"));
        if (!id.isEmpty()) {
            List<WebElement> explicitLabels = driver.findElements(By.cssSelector("label[for='" + id + "']"));
            if (!explicitLabels.isEmpty() && !normalize(explicitLabels.getFirst().getText()).isEmpty()) {
                return true;
            }

            List<WebElement> idBasedLabels = driver.findElements(By.id(id + "-label"));
            if (!idBasedLabels.isEmpty() && !normalize(idBasedLabels.getFirst().getText()).isEmpty()) {
                return true;
            }
        }

        List<WebElement> wrappedLabels = element.findElements(By.xpath("./ancestor::label[1]"));
        if (!wrappedLabels.isEmpty() && !normalize(wrappedLabels.getFirst().getText()).isEmpty()) {
            return true;
        }

        if ("button".equalsIgnoreCase(element.getTagName()) && !normalize(element.getText()).isEmpty()) {
            return true;
        }

        String placeholder = normalize(element.getAttribute("placeholder"));
        if (!placeholder.isEmpty()) {
            return true;
        }

        String title = normalize(element.getAttribute("title"));
        return !title.isEmpty();
    }

    public String[] getForegroundAndBackgroundColor(WebElement element) {
        Object result = ((JavascriptExecutor) driver).executeScript(
                """
                const target = arguments[0];
                const transparent = new Set(['transparent', 'rgba(0, 0, 0, 0)', '']);
                const fg = window.getComputedStyle(target).color;
                let bg = window.getComputedStyle(target).backgroundColor;
                let current = target;
                while (transparent.has(bg) && current && current.parentElement) {
                    current = current.parentElement;
                    bg = window.getComputedStyle(current).backgroundColor;
                }
                if (transparent.has(bg)) {
                    bg = 'rgb(255, 255, 255)';
                }
                return [fg, bg];
                """,
                element
        );
        List<?> colors = (List<?>) result;
        return new String[]{String.valueOf(colors.getFirst()), String.valueOf(colors.get(1))};
    }

    private String normalize(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }
}
