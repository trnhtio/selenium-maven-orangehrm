package org.opencart.PageObjects;

import Base.BasePage;
import org.opencart.PageUIs.LoginPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(LoginPageUI.PAGE_TITLE);
    }

    public MyAccountPage login(String email, String password) {
        waitForElementVisible(LoginPageUI.LOGIN_FORM);
        sendKeys(LoginPageUI.EMAIL_INPUT, email);
        sendKeys(LoginPageUI.PASSWORD_INPUT, password);
        WebElement loginForm = waitForElementVisible(LoginPageUI.LOGIN_FORM);
        loginForm.submit();
        waitForUrlContains("route=account/account");
        return new MyAccountPage(driver);
    }

    public String getVisibleErrorMessage() {
        if (isElementVisible(LoginPageUI.WARNING_ALERT)) {
            return getText(LoginPageUI.WARNING_ALERT);
        }
        return "";
    }
}
