package org.opencart.tests;

import Base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.opencart.PageObjects.DemoPage;
import org.opencart.PageObjects.LoginPage;
import org.opencart.PageObjects.MyAccountPage;
import org.opencart.PageObjects.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("OpenCart Storefront")
@Feature("Account Registration")
public class RegisterPageTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    @Story("Customer registers a new account")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Open the register page, submit valid account data, verify successful registration, then login with the registered account.")
    public void registerNewAccountAndLoginSuccessfully() {
        String email = "automation" + System.currentTimeMillis() + "@example.com";
        String password = "Password123!";
        DemoPage demoPage = new DemoPage(driver);

        RegisterPage registerPage = demoPage.openRegisterPage();

        Assert.assertEquals(registerPage.getPageTitle(), "Register Account", "Register page is not opened.");

        registerPage.registerValidAccount(
                "Automation",
                "User",
                email,
                password
        );

        Assert.assertEquals(
                registerPage.getSuccessTitle(),
                "Your Account Has Been Created!",
                "Account registration success page is not displayed. Error: " + registerPage.getVisibleErrorMessage()
        );

        demoPage.logout();
        LoginPage loginPage = demoPage.openLoginPage();

        MyAccountPage myAccountPage = loginPage.login(email, password);

        Assert.assertTrue(
                myAccountPage.isMyAccountPageDisplayed(),
                "Login with registered account was not successful. Error: " + loginPage.getVisibleErrorMessage()
        );
    }
}
