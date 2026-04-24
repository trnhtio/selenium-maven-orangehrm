package org.opencart.tests.portal;

import Base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.opencart.config.OpenCartSiteUrls;
import org.opencart.PageObjects.admin.AdministrationLoginPage;
import org.opencart.PageObjects.portal.DemoPage;
import org.opencart.PageObjects.store.StorefrontPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("OpenCart Demo")
@Feature("Portal Navigation")
public class DemoPagePortalNavigationTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    @Story("User opens the storefront demo from the landing page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Open the OpenCart demo landing page, launch the storefront in a new tab, and verify the storefront is displayed.")
    public void shouldOpenStorefrontPortalFromDemoLandingPage() {
        DemoPage demoPage = new DemoPage(driver).open();
        String landingWindow = windowManager.getCurrentWindowHandle();

        Assert.assertEquals(demoPage.getHeroTitle(), "OpenCart Demonstration", "The demo landing page did not load correctly.");

        StorefrontPage storefrontPage = demoPage.openStorefrontPortal();

        Assert.assertTrue(windowManager.waitForWindowCount(2), "A new storefront browser tab was not opened.");
        Assert.assertTrue(storefrontPage.isLoaded(), "The storefront portal did not load successfully.");
        Assert.assertTrue(
                storefrontPage.getStorefrontUrl().contains("demo.opencart.com"),
                "Unexpected storefront URL: " + storefrontPage.getStorefrontUrl()
        );
        Assert.assertTrue(
                storefrontPage.getStorefrontTitle().contains("Your Store")
                        || storefrontPage.isBotProtectionPageDisplayed(),
                "Unexpected storefront page title: " + storefrontPage.getStorefrontTitle()
        );

        windowManager.closeCurrentWindowAndSwitchBack(landingWindow);
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseUrl", OpenCartSiteUrls.DEMO_PAGE_URL));
    }

    @Test(groups = {"smoke", "regression"})
    @Story("User opens the administration demo from the landing page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Open the OpenCart demo landing page, launch the administration portal in a new tab, and verify the admin login page is displayed.")
    public void shouldOpenAdministrationPortalFromDemoLandingPage() {
        DemoPage demoPage = new DemoPage(driver).open();
        String landingWindow = windowManager.getCurrentWindowHandle();

        Assert.assertTrue(
                demoPage.getAdministrationPasswordHint().contains("demo"),
                "The administration password hint was not displayed on the landing page."
        );

        AdministrationLoginPage administrationLoginPage = demoPage.openAdministrationPortal();

        Assert.assertTrue(windowManager.waitForWindowCount(2), "A new administration browser tab was not opened.");
        Assert.assertTrue(administrationLoginPage.isLoaded(), "The administration login page did not load successfully.");
        Assert.assertTrue(
                administrationLoginPage.getCurrentPageUrl().contains("demo.opencart.com"),
                "Unexpected administration URL: " + administrationLoginPage.getCurrentPageUrl()
        );
        Assert.assertTrue(
                administrationLoginPage.getCurrentPageTitle().contains("Administration")
                        || administrationLoginPage.isBotProtectionPageDisplayed(),
                "Unexpected administration page title: " + administrationLoginPage.getCurrentPageTitle()
        );
        if (administrationLoginPage.hasLoginForm()) {
            Assert.assertEquals(administrationLoginPage.getUsernameValue(), "demo", "Unexpected admin username.");
            Assert.assertEquals(administrationLoginPage.getPasswordValue(), "demo", "Unexpected admin password.");
        } else {
            Assert.assertTrue(
                    administrationLoginPage.isBotProtectionPageDisplayed()
                            || administrationLoginPage.getCurrentPageTitle().contains("Administration"),
                    "The administration portal did not expose the expected demo login state."
            );
        }

        windowManager.closeCurrentWindowAndSwitchBack(landingWindow);
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseUrl", OpenCartSiteUrls.DEMO_PAGE_URL));
    }
}
