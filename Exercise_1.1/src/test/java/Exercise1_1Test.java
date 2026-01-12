import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.util.Properties;

public class Exercise1_1Test {
    @Test
    public void seleniumTest() throws Exception {
        // Create Properties instance
        Properties prop = new Properties();

        // Open file config
        FileInputStream inp = new FileInputStream("src/main/resources/config.properties");

        // Load content of config file into prop
        prop.load(inp);

        // Get value of url and browser
        String url = prop.getProperty("url");
        String browser = prop.getProperty("browser");

        // Set up browser and run
        WebDriver driver;
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.get(url);

        Thread.sleep(3000);
        driver.quit();
    }
}
