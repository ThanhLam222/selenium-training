package utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Set;

public class CookieUtils {
    public static void addCookie(WebDriver driver, Cookie cookie) {
        driver.manage().addCookie(cookie);
    }

    public static Cookie getCookie(WebDriver driver, String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.manage().getCookieNamed(name) != null);
        return driver.manage().getCookieNamed(name);
    }

    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static void deleteCookie(WebDriver driver, String name) {
        driver.manage().deleteCookieNamed(name);
    }

    public static void deleteAllCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    public static void saveCookiesToFile(WebDriver driver, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        Set<Cookie> cookies = getAllCookies(driver);
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(cookies);
        }
    }

    public static void loadCookiesFromFile(WebDriver driver, String filePath) throws IOException,
            ClassNotFoundException {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            throw new RuntimeException("Cookie file not found: " + filePath);
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(filePath))) {

            Set<Cookie> cookies = (Set<Cookie>) ois.readObject();
            for (Cookie cookie : cookies) {
                driver.manage().addCookie(cookie);
            }
        }
    }
}
