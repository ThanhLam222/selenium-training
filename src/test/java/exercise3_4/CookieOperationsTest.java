package exercise3_4;

import base.BaseTest;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.CookieUtils;

import java.util.Set;

public class CookieOperationsTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise3_4";
    }

    @Test
    public void basicCookieOperationsTest() {
        // Get number of initial cookies
        int initialSize = CookieUtils.getAllCookies(driver).size();

        String name1 = ConfigReader.getProperty("cookie1.name");
        String value1 = ConfigReader.getProperty("cookie1.value");
        String name2 = ConfigReader.getProperty("cookie2.name");
        String value2 = ConfigReader.getProperty("cookie2.value");
        String name3 = ConfigReader.getProperty("cookie3.name");
        String value3 = ConfigReader.getProperty("cookie3.value");

        Cookie cookie1 = new Cookie(name1, value1);
        Cookie cookie2 = new Cookie(name2, value2);
        Cookie cookie3 = new Cookie(name3, value3);

        // Add cookies
        CookieUtils.addCookie(driver, cookie1);
        CookieUtils.addCookie(driver, cookie2);
        CookieUtils.addCookie(driver, cookie3);

        // Retrieve cookie
        Cookie cookie = CookieUtils.getCookie(driver, name1);
        Assert.assertNotNull(cookie);
        Assert.assertEquals(cookie.getValue(), value1);

        // Delete one cookie
        CookieUtils.deleteCookie(driver, name1);
        Cookie deletedCookie = driver.manage().getCookieNamed(name1);
        Assert.assertNull(deletedCookie);

        Set<Cookie> cookiesListAfterDelOne = CookieUtils.getAllCookies(driver);
        Assert.assertEquals(cookiesListAfterDelOne.size(), initialSize +
                Integer.parseInt( ConfigReader.getProperty("size.deleteOne"))
        );

        // Delete all cookies
        CookieUtils.deleteAllCookies(driver);
        Set<Cookie> cookiesListAfterDelAll = CookieUtils.getAllCookies(driver);
        Assert.assertEquals(cookiesListAfterDelAll.size(),
                Integer.parseInt(ConfigReader.getProperty("size.deleteAll"))
        );
    }
}
