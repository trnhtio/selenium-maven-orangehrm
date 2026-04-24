package org.opencart.PageObjects.store;

import org.opencart.PageUIs.store.CheckoutPageUI;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends StorePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(CheckoutPageUI.PAGE_TITLE);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isCheckoutPageDisplayed() {
        waitForElementVisible(CheckoutPageUI.CHECKOUT_CONTAINER);
        waitForElementVisible(CheckoutPageUI.ACCOUNT_SECTION);
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        String pageTitle = getPageTitle().toLowerCase();

        return currentUrl.contains("route=checkout/checkout")
                || currentUrl.contains("route=account/login")
                || pageTitle.contains("checkout")
                || pageTitle.contains("account login");
    }
}
