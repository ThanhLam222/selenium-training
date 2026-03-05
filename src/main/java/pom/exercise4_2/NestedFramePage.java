package pom.exercise4_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NestedFramePage extends BasePage {
    private final By body = By.tagName("body");

    public NestedFramePage(WebDriver driver) {
        super(driver);
    }

    public NestedFramePage navigateToNestedFramePage() {
        driver.get(getConfig("url") + getConfig("nestedframe.url"));
        return this;
    }

    public NestedFramePage switchToTopFrame() {
        switchToFrame("frame-top");
        return this;
    }

    public NestedFramePage switchToLeftFrame() {
        switchToFrame("frame-left");
        return this;
    }

    public NestedFramePage switchToRightFrame() {
        switchToFrame("frame-right");
        return this;
    }

    public NestedFramePage switchToBottomFrame() {
        switchToFrame("frame-bottom");
        return this;
    }

    public NestedFramePage switchToMiddleFrame() {
        switchToFrame("frame-middle");
        return this;
    }

    public NestedFramePage switchToParentFrameElement() {
        switchToParentFrame();
        return this;
    }

    public String getBodyText() {
        return getText(body);
    }
}
