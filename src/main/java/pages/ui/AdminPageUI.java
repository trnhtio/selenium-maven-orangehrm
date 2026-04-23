package pages.ui;

import org.openqa.selenium.By;

public class AdminPageUI {
    public static final By ADD_USER_BUTTON = By.xpath("//button[normalize-space()='Add']");
    public static final By SAVE_BUTTON = By.cssSelector("button[type='submit']");
    public static final By SEARCH_BUTTON = By.xpath("//button[normalize-space()='Search']");
    public static final By RESET_BUTTON = By.xpath("//button[normalize-space()='Reset']");
    public static final By SUCCESS_TOAST_TITLE = By.xpath("//div[contains(@class,'oxd-toast')]//*[normalize-space()='Success']");
    public static final By SYSTEM_USERS_HEADER = By.xpath("//h5[normalize-space()='System Users']");
    public static final By ADD_USER_HEADER = By.xpath("//h6[normalize-space()='Add User']");
    public static final By FORM_LOADER = By.cssSelector(".oxd-form-loader");

    public static By textboxByLabel(String label) {
        return By.xpath("//label[normalize-space()='" + label + "']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    }

    public static By passwordTextboxByLabel(String label) {
        return By.xpath("//label[normalize-space()='" + label + "']/ancestor::div[contains(@class,'oxd-input-group')]//input[@type='password']");
    }

    public static By dropdownByLabel(String label) {
        return By.xpath("//label[normalize-space()='" + label + "']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]");
    }

    public static By dropdownOptionByText(String optionText) {
        return By.xpath("//div[@role='option']//span[normalize-space()='" + optionText + "']");
    }

    public static By employeeAutocompleteOptionByText(String employeeName) {
        return By.xpath("//div[@role='option']//span[contains(normalize-space(),'" + employeeName + "')]");
    }

    public static By systemUserRowByUsername(String username) {
        return By.xpath("//div[@role='row'][.//div[@role='cell']//div[normalize-space()='" + username + "']]");
    }

    public static By userRoleCellByUsername(String username) {
        return By.xpath("//div[@role='row'][.//div[@role='cell']//div[normalize-space()='" + username + "']]//div[@role='cell'][3]//div");
    }

    public static By editButtonByUsername(String username) {
        return By.xpath("//div[@role='row'][.//div[@role='cell']//div[normalize-space()='" + username + "']]//button[.//i[contains(@class,'bi-pencil-fill')]]");
    }
}
