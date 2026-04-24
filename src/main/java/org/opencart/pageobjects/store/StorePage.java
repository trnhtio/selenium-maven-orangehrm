package org.opencart.PageObjects.store;

import Base.BasePage;
import org.opencart.config.OpenCartSiteUrls;
import org.openqa.selenium.WebDriver;

public abstract class StorePage extends BasePage {

    protected StorePage(WebDriver driver) {
        super(driver);
    }

    protected String getStorefrontUrl() {
        return System.getProperty("storeUrl", OpenCartSiteUrls.DEFAULT_STOREFRONT_URL);
    }

    protected String resolveStorefrontUrl(String relativePath) {
        String storefrontUrl = getStorefrontUrl();
        String normalizedStoreUrl = storefrontUrl.endsWith("/") ? storefrontUrl : storefrontUrl + "/";
        return normalizedStoreUrl + relativePath;
    }

    public RegisterPage openRegisterPage() {
        driver.get(resolveStorefrontUrl(OpenCartSiteUrls.REGISTER_PAGE_PATH));
        return new RegisterPage(driver);
    }

    public LoginPage openLoginPage() {
        driver.get(resolveStorefrontUrl(OpenCartSiteUrls.LOGIN_PAGE_PATH));
        return new LoginPage(driver);
    }

    public StorefrontPage openStorefront() {
        driver.get(getStorefrontUrl());
        return new StorefrontPage(driver);
    }

    public void logout() {
        driver.get(resolveStorefrontUrl(OpenCartSiteUrls.LOGOUT_PAGE_PATH));
    }

    public String getCurrentStoreUrl() {
        return getCurrentUrl();
    }

    public String getCurrentStoreTitle() {
        return getBrowserTitle();
    }
}
