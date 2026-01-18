package locators.Exercise1_2;

import org.openqa.selenium.By;

    public class CommonLocators {
        // title of a page - Strategies: tagname
        public static final By PAGE_TITLE = By.tagName("h1");

        // normalize-space()
        public static final By ELEMENTS_NORMALIZE = By.xpath("//div[normalize-space()='Elements']");
    }
