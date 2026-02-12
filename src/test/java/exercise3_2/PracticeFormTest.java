package exercise3_2;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.exercise3_2.PracticeFormPage;
import utils.ConfigReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class PracticeFormTest extends BaseTest {
    @Override
    protected String getExerciseName() {
        return "exercise3_2";
    }

    @Test
    public void verifySubmitFormSuccessfully() {
        // Read test data inside test method to ensure config is fully loaded
        String firsName = ConfigReader.getProperty("firstname");
        String lastName = ConfigReader.getProperty("lastname");
        String email = ConfigReader.getProperty("email");
        String gender = ConfigReader.getProperty("gender");
        String phone = ConfigReader.getProperty("phone");
        String dob = ConfigReader.getProperty("dob");
        String subject = ConfigReader.getProperty("subject");
        String hobbies = ConfigReader.getProperty("hobbies");
        String address = ConfigReader.getProperty("address");
        String state = ConfigReader.getProperty("state");
        String city = ConfigReader.getProperty("city");
        String expectedSubmitMessage = ConfigReader.getProperty("submit.message");

        /* *
         * Parse DOB from config to use with custom date picker:
         * Convert DOB string to LocalDate for flexible date selection
         * */
        LocalDate date = LocalDate.parse(
                dob,
                DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)
        );

        String day = String.valueOf(date.getDayOfMonth());
        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String year = String.valueOf(date.getYear());

        // Navigate to Practice Form
        PracticeFormPage practiceFormPage = new PracticeFormPage(driver).navigateToPraticeFormPage();

        // Fill basic information
        WebElement genderRadio = practiceFormPage.enterFirstName(firsName)
                .enterLastName(lastName)
                .enterEmail(email)
                .selectGenderRadio(gender);

        // Verify correct gender options is selected
        Assert.assertTrue(genderRadio.isSelected(), gender + " radio is selected.");

        // Fill remaining form fields
        WebElement hobbyRadio = practiceFormPage.enterPhone(phone)
                .selectDateByCustomDatePicker(month, year, day)
                .selectSubjects(subject)
                .selectHobby(hobbies);

        // Verify correct hobby options is selected
        Assert.assertTrue(hobbyRadio.isSelected(), hobbies + " radio is selected");

        // Fill remaining form fields and submit
        String submittedMessage = practiceFormPage.enterCurrentAddress(address)
                .selectStateAndCity(state, city)
                .clickSubmitBtn()
                .getSubmitMessage();

        // Verify submit successfully message
        Assert.assertEquals(submittedMessage, expectedSubmitMessage);

        // Verify submitted data in confirmation modal
        Assert.assertEquals(practiceFormPage.getStudentNameOup(), firsName + " " + lastName);
        Assert.assertEquals(practiceFormPage.getStudentEmailOup(), email);
        Assert.assertEquals(practiceFormPage.getGenderOup(), gender);
        Assert.assertEquals(practiceFormPage.getMobileOup(), phone);

        // Convert text to LocalDate for comparison
        String actual = practiceFormPage.getDOBOup();

        DateTimeFormatter formatterInput =
                DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);

        DateTimeFormatter formatterUI =
                DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);

        LocalDate expectedDate = LocalDate.parse(dob, formatterInput);
        LocalDate actualDate = LocalDate.parse(actual, formatterUI);

        Assert.assertEquals(actualDate, expectedDate);

        // Verify remaining information
        Assert.assertEquals(practiceFormPage.getSubjectOup(), subject);
        Assert.assertEquals(practiceFormPage.getHobbiesOup(), hobbies);
        Assert.assertEquals(practiceFormPage.getAddressOup(), address);
        Assert.assertEquals(practiceFormPage.getStateAndCity(), state + " " + city);
    }
}
