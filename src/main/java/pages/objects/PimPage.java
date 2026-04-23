package pages.objects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pages.ui.PimPageUI;

public class PimPage extends BasePage {

    public PimPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens the PIM module from the side menu.
     */
    public void openPimModule() {
        click(PimPageUI.PIM_MENU_ITEM);
    }

    /**
     * Opens the Add Employee form.
     */
    public void openAddEmployeeForm() {
        click(PimPageUI.ADD_EMPLOYEE_TAB);
        waitForElementVisible(PimPageUI.FIRST_NAME_TEXTBOX);
        waitForElementInvisible(PimPageUI.FORM_LOADER);
    }

    /**
     * Enters the employee's first, middle, and last name.
     */
    public void enterEmployeeName(String firstName, String middleName, String lastName) {
        sendKeys(PimPageUI.FIRST_NAME_TEXTBOX, firstName);
        sendKeys(PimPageUI.MIDDLE_NAME_TEXTBOX, middleName);
        sendKeys(PimPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    /**
     * Replaces the generated employee ID with a custom value.
     */
    public void enterEmployeeId(String employeeId) {
        waitForElementInvisible(PimPageUI.FORM_LOADER);
        replaceText(PimPageUI.EMPLOYEE_ID_TEXTBOX, employeeId);
    }

    /**
     * Saves the employee form.
     */
    public void saveEmployee() {
        click(PimPageUI.SAVE_BUTTON);
    }

    /**
     * Creates an employee using the Add Employee form.
     */
    public void createEmployee(String firstName, String middleName, String lastName, String employeeId) {
        openAddEmployeeForm();
        enterEmployeeName(firstName, middleName, lastName);
        enterEmployeeId(employeeId);
        saveEmployee();
    }

    /**
     * Checks whether the employee save has completed successfully.
     */
    public boolean isEmployeeSavedSuccessfully() {
        return isAnyElementDisplayed(PimPageUI.SUCCESS_TOAST_TITLE, PimPageUI.PERSONAL_DETAILS_HEADER);
    }
}
