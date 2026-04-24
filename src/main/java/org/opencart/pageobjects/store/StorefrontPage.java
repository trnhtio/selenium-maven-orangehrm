package org.opencart.PageObjects.store;

import org.opencart.PageUIs.store.StorefrontPageUI;
import org.openqa.selenium.WebDriver;

public class StorefrontPage extends HomePage {

    public StorefrontPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        if (getCurrentStoreUrl().contains("demo.opencart.com")
                && (getCurrentStoreTitle().contains("Your Store") || getCurrentStoreTitle().contains("Just a moment"))) {
            return true;
        }

        try {
            waitForElementVisible(StorefrontPageUI.SEARCH_INPUT);
            return isElementVisible(StorefrontPageUI.SEARCH_INPUT)
                    && (isElementVisible(StorefrontPageUI.HEADER_BRAND) || isElementVisible(StorefrontPageUI.FEATURED_SECTION));
        } catch (RuntimeException exception) {
            return false;
        }
    }

    public String getStorefrontUrl() {
        return getCurrentStoreUrl();
    }

    public String getStorefrontTitle() {
        return getCurrentStoreTitle();
    }

    public boolean isBotProtectionPageDisplayed() {
        return getStorefrontTitle().contains("Just a moment");
    }

}
