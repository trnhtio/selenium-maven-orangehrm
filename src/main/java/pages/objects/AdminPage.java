package pages.objects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pages.ui.AdminPageUI;

public class AdminPage extends BasePage {
    private static final String SYSTEM_USERS_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens the System Users page in the Admin module.
     */
    public void openSystemUsersPage() {
        openUrl(SYSTEM_USERS_URL);
        waitForElementVisibleOrUrlContains(AdminPageUI.SYSTEM_USERS_HEADER, "/admin/viewSystemUsers");
        waitForElementClickable(AdminPageUI.ADD_USER_BUTTON);
    }

    /**
     * Creates a system user from the Add User form.
     */
    public void createSystemUser(String role, String employeeName, String status, String username, String password) {
        click(AdminPageUI.ADD_USER_BUTTON);
        waitForElementVisible(AdminPageUI.ADD_USER_HEADER);
        waitForElementInvisible(AdminPageUI.FORM_LOADER);

        selectDropdownOption("User Role", role);
        selectEmployee(employeeName);
        selectDropdownOption("Status", status);
        replaceText(AdminPageUI.textboxByLabel("Username"), username);
        replaceText(AdminPageUI.passwordTextboxByLabel("Password"), password);
        replaceText(AdminPageUI.passwordTextboxByLabel("Confirm Password"), password);
        saveAndWaitForSuccess();
    }

    /**
     * Searches for a system user by username.
     */
    public void searchUserByUsername(String username) {
        click(AdminPageUI.RESET_BUTTON);
        replaceText(AdminPageUI.textboxByLabel("Username"), username);
        click(AdminPageUI.SEARCH_BUTTON);
        waitForElementVisible(AdminPageUI.systemUserRowByUsername(username));
    }

    /**
     * Checks whether a user row is displayed.
     */
    public boolean isUserDisplayed(String username) {
        return isElementDisplayed(AdminPageUI.systemUserRowByUsername(username));
    }

    /**
     * Gets a user's role from the search results grid.
     */
    public String getUserRole(String username) {
        return getText(AdminPageUI.userRoleCellByUsername(username));
    }

    /**
     * Edits an existing user's role.
     */
    public void editUserRole(String username, String newRole) {
        click(AdminPageUI.editButtonByUsername(username));
        waitForElementVisible(AdminPageUI.textboxByLabel("Username"));
        waitForElementInvisible(AdminPageUI.FORM_LOADER);
        selectDropdownOption("User Role", newRole);
        saveAndWaitForSuccess();
    }

    /**
     * Selects a dropdown option by its field label and visible option text.
     */
    private void selectDropdownOption(String label, String option) {
        waitForElementInvisible(AdminPageUI.FORM_LOADER);
        click(AdminPageUI.dropdownByLabel(label));
        click(AdminPageUI.dropdownOptionByText(option));
    }

    /**
     * Selects an employee from the autocomplete field.
     */
    private void selectEmployee(String employeeName) {
        waitForElementInvisible(AdminPageUI.FORM_LOADER);
        replaceText(AdminPageUI.textboxByLabel("Employee Name"), employeeName);
        click(AdminPageUI.employeeAutocompleteOptionByText(employeeName));
    }

    /**
     * Saves the current form and waits for the success state.
     */
    private void saveAndWaitForSuccess() {
        click(AdminPageUI.SAVE_BUTTON);
        waitForElementVisibleOrUrlContains(AdminPageUI.SUCCESS_TOAST_TITLE, "/admin/viewSystemUsers");
    }
}
