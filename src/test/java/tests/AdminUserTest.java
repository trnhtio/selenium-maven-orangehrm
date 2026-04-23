package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.objects.AdminPage;
import pages.objects.DashboardPage;
import pages.objects.LoginPage;
import pages.objects.PimPage;

public class AdminUserTest extends BaseTest {

    @Test(groups = "smoke")
    public void createAdminUserThenEditRoleSuccessfully() {
        String uniqueValue = String.valueOf(System.currentTimeMillis());
        String employeeId = uniqueValue.substring(uniqueValue.length() - 4);
        String firstName = "Auto";
        String middleName = "Admin";
        String lastName = "User" + uniqueValue;
        String employeeName = firstName + " " + middleName + " " + lastName;
        String username = "auto.admin." + uniqueValue;
        String password = "Orange@12345";

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.loginToSystem("Admin", "admin123");
        Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed(), "Dashboard header should be visible after login.");

        PimPage pimPage = new PimPage(driver);
        pimPage.openPimModule();
        pimPage.createEmployee(firstName, middleName, lastName, employeeId);
        Assert.assertTrue(pimPage.isEmployeeSavedSuccessfully(), "Employee should be saved successfully before user creation.");

        AdminPage adminPage = new AdminPage(driver);
        adminPage.openSystemUsersPage();
        adminPage.createSystemUser("Admin", employeeName, "Enabled", username, password);
        adminPage.searchUserByUsername(username);

        Assert.assertTrue(adminPage.isUserDisplayed(username), "Created Admin user should be visible in search results.");
        Assert.assertEquals(adminPage.getUserRole(username), "Admin", "Created user role should be Admin.");

        adminPage.editUserRole(username, "ESS");
        adminPage.searchUserByUsername(username);

        Assert.assertEquals(adminPage.getUserRole(username), "ESS", "Edited user role should be ESS.");
    }
}
