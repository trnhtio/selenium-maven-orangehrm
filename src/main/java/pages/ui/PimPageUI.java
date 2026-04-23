package pages.ui;

import org.openqa.selenium.By;

public class PimPageUI {
    public static final By PIM_MENU_ITEM = By.xpath("//span[normalize-space()='PIM']");
    public static final By ADD_EMPLOYEE_TAB = By.xpath("//a[normalize-space()='Add Employee']");
    public static final By FIRST_NAME_TEXTBOX = By.name("firstName");
    public static final By MIDDLE_NAME_TEXTBOX = By.name("middleName");
    public static final By LAST_NAME_TEXTBOX = By.name("lastName");
    public static final By EMPLOYEE_ID_TEXTBOX = By.xpath("//label[normalize-space()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    public static final By SAVE_BUTTON = By.cssSelector("button[type='submit']");
    public static final By SUCCESS_TOAST_TITLE = By.xpath("//div[contains(@class,'oxd-toast')]//*[normalize-space()='Success']");
    public static final By PERSONAL_DETAILS_HEADER = By.xpath("//h6[normalize-space()='Personal Details']");
    public static final By FORM_LOADER = By.cssSelector(".oxd-form-loader");
}
