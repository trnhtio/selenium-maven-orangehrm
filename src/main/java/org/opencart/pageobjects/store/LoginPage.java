package org.opencart.PageObjects.store;

import org.opencart.PageUIs.store.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPage extends StorePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(LoginPageUI.PAGE_TITLE);
    }

    public boolean isLoaded() {
        if (isBotProtectionPageDisplayed()) {
            return true;
        }

        try {
            waitForElementVisible(LoginPageUI.EMAIL_INPUT, 10);
            return isElementVisible(LoginPageUI.EMAIL_INPUT)
                    && isElementVisible(LoginPageUI.PASSWORD_INPUT)
                    && isElementVisible(LoginPageUI.LOGIN_BUTTON);
        } catch (RuntimeException exception) {
            return false;
        }
    }

    public boolean isBotProtectionPageDisplayed() {
        return getCurrentStoreTitle().contains("Just a moment");
    }

    public MyAccountPage login(String email, String password) {
        waitForElementVisible(LoginPageUI.EMAIL_INPUT, 45);
        sendKeys(LoginPageUI.EMAIL_INPUT, email);
        sendKeys(LoginPageUI.PASSWORD_INPUT, password);
        click(LoginPageUI.LOGIN_BUTTON);
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
