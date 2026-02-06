package exercise3_1;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise3_1.DragAndDropPage;
import utils.ConfigReader;

public class DropAndDragTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise3_1";
    }

    @Test
    public void verifyDragAndDropSuccessfully() {
        DragAndDropPage dropAndDragPage = new DragAndDropPage(driver);
        String message = dropAndDragPage.navigateToDragAndDropPage()
                .dragAndDrop()
                .getDropMessage();

        Assert.assertEquals(message, ConfigReader.getProperty("drop.message"));
    }
}
