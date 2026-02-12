package pom.exercise3_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class UploadDownLoadPage extends BasePage {
    private final By uploadInp = By.id("uploadFile");
    private final By uploadedFilePath = By.id("uploadedFilePath");
    private final By downloadBtn = By.id("downloadButton");

    public UploadDownLoadPage(WebDriver driver) {
        super(driver);
    }

    public UploadDownLoadPage navigateToUploadDownLoadPage() {
        driver.get(getConfig("url") + getConfig("file.url"));
        return this;
    }

    public UploadDownLoadPage uploadFile(String absoluteFilePath) {
        sendKeys(uploadInp, absoluteFilePath);
        return this;
    }

    public String getUploadedFilePath() {
        return getText(uploadedFilePath);
    }

    public UploadDownLoadPage clickDownloadBtn() {
        click(downloadBtn);
        return this;
    }

    public UploadDownLoadPage waitForFile(Path filePath, int timeoutSeconds) {
        Wait<Path> wait = new FluentWait<>(filePath)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);

        wait.until(path -> Files.exists(path));

        return this;
    }

    public Path createDynamicFile(String fileName, String content) {
        try {
            Path dir = Paths.get("target", "upload-temp");
            Files.createDirectories(dir);

            Path filePath = dir.resolve(fileName);
            Files.writeString(filePath, content);

            return filePath;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
