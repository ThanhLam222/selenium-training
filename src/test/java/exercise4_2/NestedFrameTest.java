package exercise4_2;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise4_2.NestedFramePage;
import utils.ConfigReader;

public class NestedFrameTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise4_2";
    }

    @Test
    public void nestedFrameTest() {
        String expectedLeft = ConfigReader.getProperty("left");
        String expectedMiddle = ConfigReader.getProperty("middle");
        String expectedRight = ConfigReader.getProperty("right");
        String expectedBottom = ConfigReader.getProperty("bottom");

        NestedFramePage nestedFramePage = new NestedFramePage(driver)
                .navigateToNestedFramePage();

        // 1. Switch to left frame
        String actualLeft = nestedFramePage.switchToTopFrame()
                .switchToLeftFrame()
                .getBodyText();
        Assert.assertTrue(actualLeft.contains(expectedLeft));

        // 2. Switch to middle frame
        String actualMiddle = nestedFramePage.switchToParentFrameElement()
                .switchToMiddleFrame()
                .getBodyText();
        Assert.assertTrue(actualMiddle.contains(expectedMiddle));

        // 3. Switch to right frame
        String actualRight = nestedFramePage.switchToParentFrameElement()
                .switchToRightFrame()
                .getBodyText();
        Assert.assertTrue(actualRight.contains(expectedRight));

        // 4. Switch to bottom frame
        nestedFramePage.switchToDefaultContent();
        String actualBottom = nestedFramePage.switchToBottomFrame()
                .getBodyText();
        Assert.assertTrue(actualBottom.contains(expectedBottom));
    }
}
