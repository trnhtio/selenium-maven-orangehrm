package orangehrm.admin;

import PageObjects.admin.DashboardPage;
import PageObjects.admin.LoginPage;
import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    /**
     * Verifies that a user can log in with valid OrangeHRM credentials.
     */
    @Test
    public void TC_001_loginWithValidCredentialsShouldShowDashboard() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.loginAs("Admin", "admin123");

        Assert.assertTrue(dashboardPage.isDashboardHeaderVisible(), "Dashboard header should be visible after valid login.");
    }

    /**
     * Verifies that invalid credentials show the expected error message and keep the user on the login page.
     */
    @Test
    public void TC002_loginWithInvalidCredentialsShouldShowInvalidCredentialsMessage() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginAs("Admin", "wrongPassword");

        Assert.assertTrue(loginPage.isInvalidCredentialsAlertVisible(), "Invalid credentials alert should be visible.");
        Assert.assertEquals(loginPage.getInvalidCredentialsAlertText(), "Invalid credentials", "Invalid login alert text should match.");
        Assert.assertTrue(loginPage.isLoginButtonVisible(), "Login button should remain visible after invalid login.");
    }

    /**
     * Verifies that submitting blank username and password fields shows Required validation messages.
     */
    @Test
    public void TC003_loginWithBlankUsernameAndPasswordShouldShowRequiredMessages() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isUsernameRequiredMessageVisible(), "Username Required message should be visible.");
        Assert.assertEquals(loginPage.getUsernameRequiredMessageText(), "Required", "Username validation text should match.");
        Assert.assertTrue(loginPage.isPasswordRequiredMessageVisible(), "Password Required message should be visible.");
        Assert.assertEquals(loginPage.getPasswordRequiredMessageText(), "Required", "Password validation text should match.");
    }

    /**
     * Verifies that submitting a blank username with a populated password shows only the username Required message.
     */
    @Test
    public void TC004_loginWithBlankUsernameShouldShowUsernameRequiredMessage() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isUsernameRequiredMessageVisible(), "Username Required message should be visible.");
        Assert.assertEquals(loginPage.getUsernameRequiredMessageText(), "Required", "Username validation text should match.");
        Assert.assertTrue(loginPage.isLoginButtonVisible(), "Login button should remain visible after username validation failure.");
    }

    /**
     * Verifies that submitting a blank password with a populated username shows only the password Required message.
     */
    @Test
    public void TC005_loginWithBlankPasswordShouldShowPasswordRequiredMessage() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("Admin");
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isPasswordRequiredMessageVisible(), "Password Required message should be visible.");
        Assert.assertEquals(loginPage.getPasswordRequiredMessageText(), "Required", "Password validation text should match.");
        Assert.assertTrue(loginPage.isLoginButtonVisible(), "Login button should remain visible after password validation failure.");
    }
}
