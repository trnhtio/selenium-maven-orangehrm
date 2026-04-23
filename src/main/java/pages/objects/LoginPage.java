package pages.objects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pages.ui.LoginPageUI;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters a username into the login form.
     */
    public void enterUsername(String username) {
        sendKeys(LoginPageUI.USERNAME_TEXTBOX, username);
    }

    /**
     * Enters a password into the login form.
     */
    public void enterPassword(String password) {
        sendKeys(LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    /**
     * Submits the login form.
     */
    public void clickLoginButton() {
        click(LoginPageUI.LOGIN_BUTTON);
    }

    /**
     * Logs in with the provided credentials.
     */
    public DashboardPage loginToSystem(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new DashboardPage(driver);
    }

    /**
     * Checks whether the invalid credentials alert is displayed.
     */
    public boolean isInvalidCredentialsAlertDisplayed() {
        return isElementDisplayed(LoginPageUI.INVALID_CREDENTIALS_ALERT);
    }

    /**
     * Gets the invalid credentials alert text.
     */
    public String getInvalidCredentialsAlertText() {
        return getText(LoginPageUI.INVALID_CREDENTIALS_ALERT);
    }

    /**
     * Checks whether the username required message is displayed.
     */
    public boolean isUsernameRequiredMessageDisplayed() {
        return isElementDisplayed(LoginPageUI.USERNAME_REQUIRED_MESSAGE);
    }

    /**
     * Gets the username required message text.
     */
    public String getUsernameRequiredMessageText() {
        return getText(LoginPageUI.USERNAME_REQUIRED_MESSAGE);
    }

    /**
     * Checks whether the password required message is displayed.
     */
    public boolean isPasswordRequiredMessageDisplayed() {
        return isElementDisplayed(LoginPageUI.PASSWORD_REQUIRED_MESSAGE);
    }

    /**
     * Gets the password required message text.
     */
    public String getPasswordRequiredMessageText() {
        return getText(LoginPageUI.PASSWORD_REQUIRED_MESSAGE);
    }

    /**
     * Checks whether the login button is displayed.
     */
    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(LoginPageUI.LOGIN_BUTTON);
    }
}
