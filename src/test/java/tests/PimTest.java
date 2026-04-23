package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.objects.DashboardPage;
import pages.objects.LoginPage;
import pages.objects.PimPage;

public class PimTest extends BaseTest {

    @Test
    public void addNewEmployeeSuccessfully() {
        String uniqueValue = String.valueOf(System.currentTimeMillis());
        String employeeId = uniqueValue.substring(uniqueValue.length() - 4);
        String firstName = "Auto";
        String middleName = "QA";
        String lastName = "Employee" + uniqueValue;

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.loginToSystem("Admin", "admin123");
        Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed(), "Dashboard header should be visible after login.");

        PimPage pimPage = new PimPage(driver);
        pimPage.openPimModule();
        pimPage.createEmployee(firstName, middleName, lastName, employeeId);

        Assert.assertTrue(pimPage.isEmployeeSavedSuccessfully(), "Employee should be saved successfully.");
    }
}
