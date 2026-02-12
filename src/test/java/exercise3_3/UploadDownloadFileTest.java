package exercise3_3;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.exercise3_3.UploadDownLoadPage;
import utils.ConfigReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UploadDownloadFileTest extends BaseTest {
    private UploadDownLoadPage uploadDownLoadPage;

    @Override
    protected String getExerciseName() {
        return "exercise3_3";
    }

    @BeforeMethod
    public void setUpTest() throws IOException {
        // 1. Clean download folder before each test
        Path downloadDir = Paths.get("target", "downloads");

        if (Files.exists(downloadDir)) {
            try (var files = Files.list(downloadDir)) {
                files.forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }

        // 2. Navigate to Upload and Download Page
        uploadDownLoadPage = new UploadDownLoadPage(driver)
                .navigateToUploadDownLoadPage();
    }

    @Test
    public void uploadAndDownloadFileSuccessfully() throws Exception {
        String fileDir = ConfigReader.getProperty("file1.dir");

        // ===== UPLOAD =====
        // Convert relative path to absolute path for sendKeys
        Path uploadFile = Paths.get(
                "src", "test", "resources", "upload", fileDir
        ).toAbsolutePath();

        uploadDownLoadPage.uploadFile(uploadFile.toString());

        Assert.assertTrue(uploadDownLoadPage.getUploadedFilePath().contains(fileDir));

        // ===== DOWNLOAD =====
        uploadDownLoadPage.clickDownloadBtn();

        Path downloadedFile = Paths.get(
                "target", "downloads", "sampleFile.jpeg"
        );

        uploadDownLoadPage.waitForFile(downloadedFile, 10);

        Assert.assertTrue(Files.exists(downloadedFile));

        Assert.assertTrue(Files.size(downloadedFile) > 0);
    }

    @Test
    public void uploadDynamicFileSuccessfully() {

        Path dynamicFile = uploadDownLoadPage.createDynamicFile(
                "dynamic-upload.txt",
                "This file is created at runtime"
        );

        uploadDownLoadPage.uploadFile(dynamicFile.toAbsolutePath().toString());

        Assert.assertTrue(uploadDownLoadPage.getUploadedFilePath().contains("dynamic-upload.txt"));
    }

    @Test
    public void uploadMultipleFilesSequentially() {
        String file1Dir = ConfigReader.getProperty("file1.dir");
        String file2Dir = ConfigReader.getProperty("file2.dir");

        List<Path> files = List.of(
                Paths.get("src", "test", "resources", "upload", file1Dir),
                Paths.get("src", "test", "resources", "upload", file2Dir)
        );

        for (Path file : files) {
            uploadDownLoadPage.uploadFile(file.toAbsolutePath().toString());

            Assert.assertTrue(
                    uploadDownLoadPage.getUploadedFilePath().contains(file.getFileName().toString())
            );
        }
    }
}
