package PageObjects.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PIMPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pimMenuItem = By.xpath("//span[normalize-space()='PIM']");
    private final By addEmployeeButton = By.xpath("//a[normalize-space()='Add Employee']");
    private final By firstNameTextbox = By.name("firstName");
    private final By middleNameTextbox = By.name("middleName");
    private final By lastNameTextbox = By.name("lastName");
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By successToast = By.xpath("//div[contains(@class,'oxd-toast')]//*[normalize-space()='Success']");
    private final By personalDetailsHeader = By.xpath("//h6[normalize-space()='Personal Details']");

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Navigates to the PIM module from the left side menu.
     */
    public void navigateToPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenuItem)).click();
    }

    /**
     * Opens the Add Employee form from the PIM module.
     */
    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameTextbox));
    }

    /**
     * Enters the employee's first, middle, and last names in the Add Employee form.
     */
    public void fillEmployeeName(String firstName, String middleName, String lastName) {
        typeInto(firstNameTextbox, firstName);
        typeInto(middleNameTextbox, middleName);
        typeInto(lastNameTextbox, lastName);
    }

    /**
     * Saves the employee record.
     */
    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    /**
     * Adds a new employee with the provided names.
     */
    public void addEmployee(String firstName, String middleName, String lastName) {
        clickAddEmployee();
        fillEmployeeName(firstName, middleName, lastName);
        clickSave();
    }

    /**
     * Verifies that the employee save completed successfully.
     */
    public boolean isEmployeeSavedSuccessfully() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(successToast),
                    ExpectedConditions.visibilityOfElementLocated(personalDetailsHeader)
            ));
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    /**
     * Clears and types text into a visible input field.
     */
    private void typeInto(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }
}
