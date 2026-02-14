package exercise4_1;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise4_1.HTMLModalWindowPage;

public class ModalWindowAndUnexpectedTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise4_1";
    }

    @Test
    public void verifyHandleModalWindowAndUnexpectedPopUpCorrectly() {
        boolean isInvisible = new HTMLModalWindowPage(driver).navigateToModalWindowPage()
                .closeModalIfPresent()
                .isModalInvisible();
        Assert.assertTrue(isInvisible);
    }
}
