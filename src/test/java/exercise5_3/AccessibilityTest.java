package exercise5_3;

import base.BaseTest;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.exercise5_3.pages.DemoQaAccessibilityPage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessibilityTest extends BaseTest {
    private static final Pattern RGB_PATTERN = Pattern.compile("\\d+");
    private static final double WCAG_AA_NORMAL_TEXT_RATIO = 4.5;
    private DemoQaAccessibilityPage accessibilityPage;

    @Override
    protected String getExerciseName() {
        return "exercise5_3";
    }

    @BeforeMethod
    public void setupPage() {
        accessibilityPage = new DemoQaAccessibilityPage(driver);
        accessibilityPage.navigateToTextBoxPage();
    }

    @Test
    public void verifyAriaLabelExists() {
        String ariaLabel = accessibilityPage.getFullNameInputAriaLabel();
        Assert.assertTrue(
                ariaLabel != null && !ariaLabel.isBlank(),
                "Full Name input should have non-empty aria-label"
        );
    }

    @Test
    public void verifyButtonRole() {
        String role = accessibilityPage.getSubmitButtonRole();
        Assert.assertEquals(role, "button", "Submit button should expose role='button'");
    }

    @Test
    public void verifyKeyboardNavigation() {
        WebElement focused = accessibilityPage.pressTabAndGetFocusedElement();

        Assert.assertEquals(
                focused.getAttribute("id"),
                "userEmail",
                "TAB should move focus from Full Name to Email"
        );
    }

    @Test
    public void verifyScreenReaderSupport() {
        List<WebElement> controls = accessibilityPage.getVisibleFormControls();
        Assert.assertFalse(controls.isEmpty(), "Expected form controls on Text Box page");

        for (WebElement control : controls) {
            Assert.assertTrue(
                    accessibilityPage.hasAccessibleName(control),
                    "Missing accessible name for element: " + control.getTagName()
                            + " id=" + control.getAttribute("id")
            );
        }
    }

    @Test
    public void verifyColorContrastMeetsWcagAA() {
        WebElement heading = accessibilityPage.getPageHeadingElement();
        String[] colors = accessibilityPage.getForegroundAndBackgroundColor(heading);
        double ratio = contrastRatio(colors[0], colors[1]);

        Assert.assertTrue(
                ratio >= WCAG_AA_NORMAL_TEXT_RATIO,
                "Contrast ratio should be >= " + WCAG_AA_NORMAL_TEXT_RATIO + " but was " + ratio
        );
    }

    @Test
    public void verifyComprehensiveAccessibilityWithAxe() {
        AxeBuilder builder = new AxeBuilder().include(".main-container");
        Results results = builder.analyze(driver);
        List<Rule> violations = results.getViolations();

        Assert.assertTrue(
                violations.isEmpty(),
                "Found accessibility violations: " + violations
        );
    }

    private double contrastRatio(String foregroundColor, String backgroundColor) {
        double fgLuminance = relativeLuminance(parseRgb(foregroundColor));
        double bgLuminance = relativeLuminance(parseRgb(backgroundColor));
        double lighter = Math.max(fgLuminance, bgLuminance);
        double darker = Math.min(fgLuminance, bgLuminance);
        return (lighter + 0.05) / (darker + 0.05);
    }

    private int[] parseRgb(String cssColor) {
        Matcher matcher = RGB_PATTERN.matcher(cssColor);
        int[] rgb = new int[3];
        int i = 0;

        while (matcher.find() && i < 3) {
            rgb[i++] = Integer.parseInt(matcher.group());
        }

        if (i < 3) {
            throw new IllegalArgumentException("Cannot parse color value: " + cssColor);
        }
        return rgb;
    }

    private double relativeLuminance(int[] rgb) {
        double r = linearize(rgb[0] / 255.0);
        double g = linearize(rgb[1] / 255.0);
        double b = linearize(rgb[2] / 255.0);
        return (0.2126 * r) + (0.7152 * g) + (0.0722 * b);
    }

    private double linearize(double value) {
        if (value <= 0.03928) {
            return value / 12.92;
        }
        return Math.pow((value + 0.055) / 1.055, 2.4);
    }
}
