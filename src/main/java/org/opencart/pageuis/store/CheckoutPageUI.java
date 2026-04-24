package org.opencart.PageUIs.store;

import org.openqa.selenium.By;

public final class CheckoutPageUI {

    public static final By PAGE_TITLE = By.cssSelector("#content h1");
    public static final By CHECKOUT_CONTAINER = By.cssSelector("#checkout-checkout, #checkout-confirm, #content");
    public static final By ACCOUNT_SECTION = By.cssSelector("#checkout-register, #checkout-payment-address, #checkout-payment-method, #content");

    private CheckoutPageUI() {
    }
}
