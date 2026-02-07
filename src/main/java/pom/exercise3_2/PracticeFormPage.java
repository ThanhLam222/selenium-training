package pom.exercise3_2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.ConfigReader;

public class PracticeFormPage extends BasePage {
    private final By firstNameInp = By.id("firstName");
    private final By lastNameInp = By.id("lastName");
    private final By emailInp = By.id("userEmail");
    private final By phoneInp = By.id("userNumber");
    private final By dateOfBirthInp = By.id("dateOfBirthInput");
    private final By subjectInp = By.id("subjectsInput");
    private final By currentAddressInp = By.id("currentAddress");
    private final By stateDropdown = By.id("state");
    private final By cityDropdown = By.id("city");
    private final By submitBtn = By.id("submit");
    private final By submitMessage = By.id("example-modal-sizes-title-lg");
    private final By studentNameOutput = By.xpath("//td[contains(text(), 'Student Name')]/following-sibling::td");
    private final By studentEmailOutput = By.xpath("//td[contains(text(), 'Student Email')]/following-sibling::td");
    private final By genderOutput = By.xpath("//td[contains(text(), 'Gender')]/following-sibling::td");
    private final By mobileOutput = By.xpath("//td[contains(text(), 'Mobile')]/following-sibling::td");
    private final By dobOutput = By.xpath("//td[contains(text(), 'Date of Birth')]/following-sibling::td");
    private final By subjectOutput = By.xpath("//td[contains(text(), 'Subjects')]/following-sibling::td");
    private final By hobbiesOutput = By.xpath("//td[contains(text(), 'Hobbies')]/following-sibling::td");
    private final By addressOutput = By.xpath("//td[contains(text(), 'Address')]/following-sibling::td");
    private final By stateAndCityOutput = By.xpath("//td[contains(text(), 'State and City')]/following-sibling::td");

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    public PracticeFormPage navigateToPraticeFormPage() {
        driver.get(getConfig("url") + getConfig("practice.url"));
        return this;
    }

    public PracticeFormPage enterFirstName(String firstName) {
        sendKeys(firstNameInp, firstName);
        return this;
    }

    public PracticeFormPage enterLastName(String lastName) {
        sendKeys(lastNameInp, lastName);
        return this;
    }

    public PracticeFormPage enterEmail(String email) {
        sendKeys(emailInp, email);
        return this;
    }

    public WebElement selectGenderRadio(String gender) {
        String xpath = "//label[@class = 'custom-control-label' and contains(normalize-space(.), '" + gender + "')]";
        WebElement label = findElement(By.xpath(xpath));
        String forValue = label.getAttribute("for");
        WebElement radioInput = findElement(By.id(forValue));

        click(By.xpath(xpath));
        return radioInput;
    }

    public PracticeFormPage enterPhone(String phone) {
        sendKeys(phoneInp, phone);
        return this;
    }

    public PracticeFormPage selectDateByCustomDatePicker(String month, String year, String day) {
        // Open calendar
        click(dateOfBirthInp);

        // Choose month
        WebElement monthXpath = findElement(By.xpath("//select[@class = 'react-datepicker__month-select']"));
        Select selectMonth = new Select(monthXpath);
        selectMonth.selectByVisibleText(month);

        // Choose year
        WebElement yearXpath = findElement(By.xpath("//select[@class = 'react-datepicker__year-select']"));
        Select selectYear = new Select(yearXpath);
        selectYear.selectByVisibleText(year);

        // Choose day
        String dayXpath = String.format("//div[contains(@class,'react-datepicker__day--0%s') " +
                "and not(contains(@class,'outside-month'))]", day);
        click(By.xpath(dayXpath));
        return this;
    }

    public PracticeFormPage selectSubjects(String subject) {
        sendKeys(subjectInp, subject);
        findElement(subjectInp).sendKeys(Keys.ENTER);
        return this;
    }

    public WebElement selectHobby(String hobby) {
        String xpath = "//label[@class = 'custom-control-label' and contains(normalize-space(.), '" + hobby + "')]";
        WebElement label = findElement(By.xpath(xpath));
        String forValue = label.getAttribute("for");
        WebElement radioInput = findElement(By.id(forValue));

        click(By.xpath(xpath));
        return radioInput;
    }

    public PracticeFormPage enterCurrentAddress(String currentAddress) {
        sendKeys(currentAddressInp, currentAddress);
        return this;
    }

    public PracticeFormPage selectStateAndCity(String state, String city) {
        // Choose state
        click(stateDropdown);
        String stateXpath = "//div[text()='" + state + "']";
        click(By.xpath(stateXpath));

        // Choose city
        click(cityDropdown);
        String cityXpath = "//div[text()='" + city + "']";
        click(By.xpath(cityXpath));
        click(By.xpath(cityXpath));

        return this;
    }

    public PracticeFormPage clickSubmitBtn() {
        click(submitBtn);
        return this;
    }

    public String getSubmitMessage() {
        return getText(submitMessage);
    }

    public String getStudentNameOup() {
        return getText(studentNameOutput);
    }

    public String getStudentEmailOup() {
        return getText(studentEmailOutput);
    }

    public String getGenderOup() {
        return getText(genderOutput);
    }

    public String getMobileOup() {
        return getText(mobileOutput);
    }

    public String getDOBOup() {
        return getText(dobOutput);
    }

    public String getSubjectOup() {
        return getText(subjectOutput);
    }

    public String getHobbiesOup() {
        return getText(hobbiesOutput);
    }

    public String getAddressOup() {
        return getText(addressOutput);
    }

    public String getStateAndCity() {
        return getText(stateAndCityOutput);
    }
}
