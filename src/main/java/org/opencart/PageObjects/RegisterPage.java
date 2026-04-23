package org.opencart.PageObjects;

import Base.BasePage;
import org.opencart.PageUIs.RegisterPageUI;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(RegisterPageUI.PAGE_TITLE);
    }

    public RegisterPage registerValidAccount(String firstName, String lastName, String email, String password) {
        sendKeys(RegisterPageUI.FIRST_NAME_INPUT, firstName);
        sendKeys(RegisterPageUI.LAST_NAME_INPUT, lastName);
        sendKeys(RegisterPageUI.EMAIL_INPUT, email);
        sendKeys(RegisterPageUI.PASSWORD_INPUT, password);
        click(RegisterPageUI.NEWSLETTER_TOGGLE);
        click(RegisterPageUI.PRIVACY_POLICY_CHECKBOX);
        click(RegisterPageUI.CONTINUE_BUTTON);
        waitForAnyCondition(
                RegisterPageUI.SUCCESS_TITLE,
                "Your Account Has Been Created!",
                RegisterPageUI.VALIDATION_ERRORS
        );
        return this;
    }

    public String getSuccessTitle() {
        return getText(RegisterPageUI.SUCCESS_TITLE);
    }

    public String getVisibleErrorMessage() {
        if (isElementVisible(RegisterPageUI.WARNING_ALERT)) {
            return getText(RegisterPageUI.WARNING_ALERT);
        }
        if (isElementVisible(RegisterPageUI.VALIDATION_ERRORS)) {
            return getText(RegisterPageUI.VALIDATION_ERRORS);
        }
        return "";
    }
}
