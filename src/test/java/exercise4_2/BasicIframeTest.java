package exercise4_2;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.exercise4_2.IframePage;
import utils.ConfigReader;

public class BasicIframeTest extends BaseTest {
    private IframePage iframePage;
    private String mainContentHeader;
    private String iframeText;

    @Override
    protected String getExerciseName() {
        return "exercise4_2";
    }

    @BeforeMethod
    public void goToIframePage() {
        iframePage = new IframePage(driver).navigateToIframePage();
        mainContentHeader = ConfigReader.getProperty("iframe.header");
        iframeText = ConfigReader.getProperty("iframe.text");
    }

    @Test
    public void switchToIframeByIndexTest() {
        // Switch to iframe by index
        iframePage.switchToIframeByIndex();
        Assert.assertEquals(iframePage.getTextOfIframe(), iframeText);

        // Switch to main content
        iframePage.switchToDefaultContent();
        Assert.assertEquals(iframePage.getHeaderOfMainContent(), mainContentHeader);
    }

    @Test
    public void switchToIframeByNameOrIdTest() {
        // Switch to iframe by name or id
        iframePage.switchToIframeByNameOrId();
        Assert.assertEquals(iframePage.getTextOfIframe(), iframeText);

        // Switch to main content
        iframePage.switchToDefaultContent();
        Assert.assertEquals(iframePage.getHeaderOfMainContent(), mainContentHeader);
    }

    @Test
    public void switchToIframeByWebElement() {
        // Switch to iframe by web element
        iframePage.switchToIframeByWebElem();
        Assert.assertEquals(iframePage.getTextOfIframe(), iframeText);

        // Switch to main content
        iframePage.switchToDefaultContent();
        Assert.assertEquals(iframePage.getHeaderOfMainContent(), mainContentHeader);
    }
}
