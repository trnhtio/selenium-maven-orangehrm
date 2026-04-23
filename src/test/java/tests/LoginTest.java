package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.objects.DashboardPage;
import pages.objects.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void TC_001_loginWithValidCredentialsShouldShowDashboard() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.loginToSystem("Admin", "admin123");

        Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed(), "Dashboard header should be visible after valid login.");
    }

    @Test
    public void TC002_loginWithInvalidCredentialsShouldShowInvalidCredentialsMessage() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginToSystem("Admin", "wrongPassword");

        Assert.assertTrue(loginPage.isInvalidCredentialsAlertDisplayed(), "Invalid credentials alert should be visible.");
        Assert.assertEquals(loginPage.getInvalidCredentialsAlertText(), "Invalid credentials", "Invalid login alert text should match.");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should remain visible after invalid login.");
    }

    @Test
    public void TC003_loginWithBlankUsernameAndPasswordShouldShowRequiredMessages() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isUsernameRequiredMessageDisplayed(), "Username Required message should be visible.");
        Assert.assertEquals(loginPage.getUsernameRequiredMessageText(), "Required", "Username validation text should match.");
        Assert.assertTrue(loginPage.isPasswordRequiredMessageDisplayed(), "Password Required message should be visible.");
        Assert.assertEquals(loginPage.getPasswordRequiredMessageText(), "Required", "Password validation text should match.");
    }

    @Test
    public void TC004_loginWithBlankUsernameShouldShowUsernameRequiredMessage() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isUsernameRequiredMessageDisplayed(), "Username Required message should be visible.");
        Assert.assertEquals(loginPage.getUsernameRequiredMessageText(), "Required", "Username validation text should match.");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should remain visible after username validation failure.");
    }

    @Test
    public void TC005_loginWithBlankPasswordShouldShowPasswordRequiredMessage() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("Admin");
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isPasswordRequiredMessageDisplayed(), "Password Required message should be visible.");
        Assert.assertEquals(loginPage.getPasswordRequiredMessageText(), "Required", "Password validation text should match.");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should remain visible after password validation failure.");
    }
}
