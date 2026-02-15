package exercise4_2;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise4_2.InternetPage;
import pom.exercise4_2.NewWindowPage;
import utils.ConfigReader;

import java.util.Set;

public class WindowHandlingTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise4_2";
    }

    @Test
    public void windowHandlingTest() {
        InternetPage internetPage = new InternetPage(driver).navigateToInternetPage();

        String mainWindow = internetPage.getCurrentWindow();

        // Open and handle 3 windows dynamically
        for (int i = 0; i < 3; i++) {

            Set<String> oldWindows = internetPage.getAllWindow();

            // Click to open new window
            internetPage.clickClickHereBtn();

            // Wait and get newly opened window
            String newWindow = internetPage.waitAndGetNewWindow(oldWindows);

            // Switch to new window
            internetPage.switchToNewWindow(newWindow);

            NewWindowPage newWindowPage = new NewWindowPage(driver);

            // Assert title
            Assert.assertEquals(newWindowPage.getPageTitle(),
                    ConfigReader.getProperty("newwindow.title"));

            // Assert header
            Assert.assertEquals(newWindowPage.getHeader(),
                    ConfigReader.getProperty("newwindow.header"));

            // Close current window
            newWindowPage.closeNewWindow();

            // Switch back to main window
            internetPage.switchToMainWindow(mainWindow);
        }

        // Assert number of windows
        Assert.assertEquals(internetPage.getNumberOfWindow(),
                Integer.parseInt(ConfigReader.getProperty("originalwindow.size"))
        );
    }
}
