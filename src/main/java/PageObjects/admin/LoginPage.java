package PageObjects.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameTextbox = By.name("username");
    private final By passwordTextbox = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By invalidCredentialsAlert = By.xpath("//p[normalize-space()='Invalid credentials']");
    private final By usernameRequiredMessage = By.xpath("//input[@name='username']/ancestor::div[contains(@class,'oxd-input-group')]//span[normalize-space()='Required']");
    private final By passwordRequiredMessage = By.xpath("//input[@name='password']/ancestor::div[contains(@class,'oxd-input-group')]//span[normalize-space()='Required']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Enters the provided username into the username field.
     */
    public void enterUsername(String username) {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameTextbox));
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    /**
     * Enters the provided password into the password field.
     */
    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordTextbox));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    /**
     * Clicks the Login button and submits the login form.
     */
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    /**
     * Logs into OrangeHRM with the provided username and password.
     */
    public DashboardPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new DashboardPage(driver);
    }

    /**
     * Verifies that the invalid credentials alert is visible.
     */
    public boolean isInvalidCredentialsAlertVisible() {
        return isElementVisible(invalidCredentialsAlert);
    }

    /**
     * Gets the invalid credentials alert text.
     */
    public String getInvalidCredentialsAlertText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(invalidCredentialsAlert)).getText();
    }

    /**
     * Verifies that the username Required validation message is visible.
     */
    public boolean isUsernameRequiredMessageVisible() {
        return isElementVisible(usernameRequiredMessage);
    }

    /**
     * Gets the username Required validation message text.
     */
    public String getUsernameRequiredMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameRequiredMessage)).getText();
    }

    /**
     * Verifies that the password Required validation message is visible.
     */
    public boolean isPasswordRequiredMessageVisible() {
        return isElementVisible(passwordRequiredMessage);
    }

    /**
     * Gets the password Required validation message text.
     */
    public String getPasswordRequiredMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordRequiredMessage)).getText();
    }

    /**
     * Verifies that the browser remains on the login page.
     */
    public boolean isLoginButtonVisible() {
        return isElementVisible(loginButton);
    }

    /**
     * Checks whether an element becomes visible within the configured timeout.
     */
    private boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException exception) {
            return false;
        }
    }
}
