package orangehrm.admin;

import PageObjects.admin.DashboardPage;
import PageObjects.admin.LoginPage;
import PageObjects.admin.PIMPage;
import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PIMTest extends BaseTest {

    /**
     * Validates login, dashboard visibility, PIM navigation, and successful employee creation.
     */
    @Test
    public void addNewEmployeeSuccessfully() {
        String uniqueValue = String.valueOf(System.currentTimeMillis());
        String firstName = "Auto";
        String middleName = "QA";
        String lastName = "Employee" + uniqueValue;

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.loginAs("Admin", "admin123");

        Assert.assertTrue(dashboardPage.isDashboardHeaderVisible(), "Dashboard header should be visible after login.");

        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();
        pimPage.addEmployee(firstName, middleName, lastName);

        Assert.assertTrue(pimPage.isEmployeeSavedSuccessfully(), "Employee should be saved successfully.");
    }
}
