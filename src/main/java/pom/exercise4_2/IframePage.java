package pom.exercise4_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IframePage extends BasePage {
    private final By headerOfMainContent = By.tagName("h3");
    private final By textOfIframe = By.xpath("//body[@id = 'tinymce']/p");
    private final By iframe = By.xpath("//iframe[@id = 'mce_0_ifr']");

    public IframePage(WebDriver driver) {
        super(driver);
    }

    public IframePage navigateToIframePage() {
        driver.get(getConfig("url") + getConfig("iframe.url"));
        return this;
    }

    public IframePage switchToIframeByIndex() {
        switchToFrame(0);
        return this;
    }

    public IframePage switchToIframeByNameOrId() {
        switchToFrame("mce_0_ifr");
        return this;
    }

    public IframePage switchToIframeByWebElem() {
        WebElement element = findElement(iframe);
        switchToFrame(element);
        return this;
    }

    public String getHeaderOfMainContent() {
        return getText(headerOfMainContent);
    }

    public String getTextOfIframe() {
        return getText(textOfIframe);
    }
}
