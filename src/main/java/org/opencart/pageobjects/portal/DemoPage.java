package org.opencart.PageObjects.portal;

import Base.BasePage;
import org.opencart.config.OpenCartSiteUrls;
import org.opencart.PageObjects.admin.AdministrationLoginPage;
import org.opencart.PageObjects.store.StorefrontPage;
import org.opencart.PageUIs.portal.DemoPageUI;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.opencart.utils.WindowManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoPage extends BasePage {

    private static final Pattern ADMINISTRATION_URL_PATTERN = Pattern.compile("https://demo\\.opencart\\.com/[A-Za-z0-9]+/");

    public DemoPage(WebDriver driver) {
        super(driver);
    }

    public DemoPage open() {
        driver.get(System.getProperty("baseUrl", OpenCartSiteUrls.DEMO_PAGE_URL));
        waitForElementVisible(DemoPageUI.HERO_TITLE);
        waitForTextPresent(DemoPageUI.HERO_TITLE, "OpenCart Demonstration");
        return this;
    }

    public String getHeroTitle() {
        return getText(DemoPageUI.HERO_TITLE);
    }

    public String getAdministrationPasswordHint() {
        String pageSource = getPageSource();
        if (pageSource.contains("Login &amp; Password: demo") || pageSource.contains("Login & Password: demo")) {
            return "Login & Password: demo";
        }
        return "";
    }

    public StorefrontPage openStoreFront() {
        driver.get(System.getProperty("storeUrl", OpenCartSiteUrls.DEFAULT_STOREFRONT_URL));
        return new StorefrontPage(driver);
    }

    public StorefrontPage openStorefrontPortal() {
        String currentWindowHandle = driver.getWindowHandle();
        ((JavascriptExecutor) driver).executeScript(
                "window.open(arguments[0], '_blank');",
                System.getProperty("demoStorefrontUrl", OpenCartSiteUrls.DEFAULT_STOREFRONT_URL)
        );
        new WindowManager(driver).switchToNewWindow(currentWindowHandle);
        return new StorefrontPage(driver);
    }

    public AdministrationLoginPage openAdministrationPortal() {
        String currentWindowHandle = driver.getWindowHandle();
        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", extractAdministrationPortalUrl());
        new WindowManager(driver).switchToNewWindow(currentWindowHandle);
        return new AdministrationLoginPage(driver);
    }
    private String extractAdministrationPortalUrl() {
        Matcher matcher = ADMINISTRATION_URL_PATTERN.matcher(getPageSource());
        while (matcher.find()) {
            String matchedUrl = matcher.group();
            if (!OpenCartSiteUrls.DEFAULT_STOREFRONT_URL.equals(matchedUrl)) {
                return matchedUrl;
            }
        }
        throw new IllegalStateException("Unable to find the administration portal URL on the demo landing page.");
    }
}
