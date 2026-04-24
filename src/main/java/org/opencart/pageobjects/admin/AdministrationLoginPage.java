package org.opencart.PageObjects.admin;

import org.opencart.PageUIs.admin.AdministrationLoginPageUI;
import org.openqa.selenium.WebDriver;

public class AdministrationLoginPage extends AdminPage {

    public AdministrationLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        if (getCurrentAdminUrl().contains("demo.opencart.com")
                && (getCurrentAdminTitle().contains("Administration") || getCurrentAdminTitle().contains("Just a moment"))) {
            return true;
        }

        try {
            waitForElementVisible(AdministrationLoginPageUI.USERNAME_INPUT);
            return isElementVisible(AdministrationLoginPageUI.USERNAME_INPUT)
                    && isElementVisible(AdministrationLoginPageUI.PASSWORD_INPUT)
                    && isElementVisible(AdministrationLoginPageUI.LOGIN_BUTTON);
        } catch (RuntimeException exception) {
            return false;
        }
    }

    public String getUsernameValue() {
        return waitForElementVisible(AdministrationLoginPageUI.USERNAME_INPUT).getDomProperty("value");
    }

    public String getPasswordValue() {
        return waitForElementVisible(AdministrationLoginPageUI.PASSWORD_INPUT).getDomProperty("value");
    }

    public String getCurrentPageUrl() {
        return getCurrentAdminUrl();
    }

    public String getCurrentPageTitle() {
        return getCurrentAdminTitle();
    }

    public String getPageHeader() {
        return getText(AdministrationLoginPageUI.PAGE_HEADER);
    }

    public boolean hasLoginForm() {
        return isElementVisible(AdministrationLoginPageUI.USERNAME_INPUT)
                && isElementVisible(AdministrationLoginPageUI.PASSWORD_INPUT)
                && isElementVisible(AdministrationLoginPageUI.LOGIN_BUTTON);
    }

    public boolean isBotProtectionPageDisplayed() {
        return getCurrentPageTitle().contains("Just a moment");
    }
}
